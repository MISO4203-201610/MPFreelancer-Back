/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.tests;

import co.edu.uniandes.csw.auth.model.UserDTO;
import co.edu.uniandes.csw.auth.security.JWT;
import co.edu.uniandes.csw.mpfreelancer.dtos.ConversationDTO;
import co.edu.uniandes.csw.mpfreelancer.dtos.ProjectDTO;
import co.edu.uniandes.csw.mpfreelancer.dtos.FreelancerDTO;
import co.edu.uniandes.csw.mpfreelancer.dtos.MailDTO;
import co.edu.uniandes.csw.mpfreelancer.services.ConversationService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.codehaus.jackson.map.ObjectMapper;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;


/**
 *
 * @author mf.calderon
 */

@RunWith(Arquillian.class)
public class ConversationTest {
    
    
    
    private final int Ok = Status.OK.getStatusCode();
    private final int Created = Status.CREATED.getStatusCode();
    private final int OkWithoutContent = Status.NO_CONTENT.getStatusCode();
    private final String conversationPath = "conversations";
    private final static List<ConversationDTO> oraculo = new ArrayList<>();
    private final static List<ProjectDTO> oraculoProjects = new ArrayList<>();
    private final static List<FreelancerDTO> oraculoFreelancers = new ArrayList<>();
    private final String projectPath = "projects";
    private WebTarget target;
    private final String apiPath = "api";
    private final String username = System.getenv("USERNAME_USER");
    private final String password = System.getenv("PASSWORD_USER");
    static Long pId;
    static Long fId;
    static Long aId;
    
    @ArquillianResource
    private URL deploymentURL;

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                // Se agrega la dependencia a la logica con el nombre groupid:artefactid:version (GAV)
                .addAsLibraries(Maven.resolver()
                        .resolve("co.edu.uniandes.csw.mpfreelancer:m-p-freelancer-logic:0.1.0")
                        .withTransitivity().asFile())
                .addAsLibraries(Maven.resolver()
                        .resolve("co.edu.uniandes.csw:auth-utils:0.1.0")
                        .withTransitivity().asFile())
                // Se agregan los compilados de los paquetes de servicios
                .addPackage(ConversationService.class.getPackage())
                // El archivo que contiene la configuracion a la base de datos.
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                // El archivo beans.xml es necesario para injeccion de dependencias.
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"))
                // El archivo shiro.ini es necesario para injeccion de dependencias
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/shiro.ini"))
                // El archivo web.xml es necesario para el despliegue de los servlets
                .setWebXML(new File("src/main/webapp/WEB-INF/web.xml"));
    }

    private WebTarget createWebTarget() {
        ClientConfig config = new ClientConfig();
        config.register(LoggingFilter.class);
        return ClientBuilder.newClient(config).target(deploymentURL.toString()).path(apiPath);
    }

    @BeforeClass
    public static void setUp() {
        insertData();
    }

    public static void insertData() {
        for (int i = 0; i < 10; i++) {
            PodamFactory factory = new PodamFactoryImpl();
            
            ConversationDTO conversation = factory.manufacturePojo(ConversationDTO.class);
            conversation.setId(i + 1L);

            oraculo.add(conversation);

            ProjectDTO projects = factory.manufacturePojo(ProjectDTO.class);
            projects.setId(i + 1L);
            oraculoProjects.add(projects);
     
            FreelancerDTO freelancers = factory.manufacturePojo(FreelancerDTO.class);
            freelancers.setId(i + 1L);
            oraculoFreelancers.add(freelancers);
            
            List<MailDTO> mailList = new ArrayList<>();
            for (int j = 0; j < 5; j++)
            {
                MailDTO mails = factory.manufacturePojo(MailDTO.class);
                mails.setId(i + 1L);
                mailList.add(mails);
            }
        }
    }

    public Cookie login(String username, String password) {
        UserDTO user = new UserDTO();
        user.setUserName(username);
        user.setPassword(password);
        user.setRememberMe(true);
        Response response = target.path("users").path("login").request()
                .post(Entity.entity(user, MediaType.APPLICATION_JSON));
        if (response.getStatus() == Ok) {
            return response.getCookies().get(JWT.cookieName);
        } else {
            return null;
        }
    }

    @Before
    public void setUpTest() {
        target = createWebTarget();
    }

    @Test
    @InSequence(1)
    public void createConversationTest() throws IOException {

        ProjectDTO project = oraculoProjects.get(0);
        FreelancerDTO freelancer = oraculoFreelancers.get(0);
        ConversationDTO conversation = oraculo.get(0);
        conversation.setFreelancer(freelancer);
        conversation.setProject(project);
   
        Cookie cookieSessionId = login(username, password);
        
                
        Response response = target.path("projects")
                .request().cookie(cookieSessionId)
                .post(Entity.entity(project, MediaType.APPLICATION_JSON));
                
                response = target.path("freelancers")
                .request().cookie(cookieSessionId)
                .post(Entity.entity(freelancer, MediaType.APPLICATION_JSON));
        
                response = target.path(conversationPath)
                .request().cookie(cookieSessionId)
                .post(Entity.entity(conversation, MediaType.APPLICATION_JSON));
                
        ConversationDTO  conversationTest = (ConversationDTO) response.readEntity(ConversationDTO.class);
        Assert.assertEquals(conversation.getId(), conversationTest.getId());
        Assert.assertEquals(conversation.getSubject(), conversationTest.getSubject());
        Assert.assertEquals(conversation.getFreelancer().getId(), conversationTest.getFreelancer().getId());
        Assert.assertEquals(conversation.getProject().getId(), conversationTest.getProject().getId());
        Assert.assertEquals(Created, response.getStatus());
        
                ConversationDTO conversation2 = oraculo.get(1);
                ProjectDTO project2 = oraculoProjects.get(1);
                conversation2.setFreelancer(freelancer);
                conversation2.setProject(project2);

                response = target.path(projectPath)
                .request().cookie(cookieSessionId)
                .post(Entity.entity(project2, MediaType.APPLICATION_JSON));
                
                response = target.path(conversationPath)
                .request().cookie(cookieSessionId)
                .post(Entity.entity(conversation2, MediaType.APPLICATION_JSON));
    }

    @Test
    @InSequence(2)
    public void getConversationById() {
        Cookie cookieSessionId = login(username, password);
        ConversationDTO conversationTest = target.path(conversationPath)
                .path(oraculo.get(0).getId().toString())
                .request().cookie(cookieSessionId).get(ConversationDTO.class);
        Assert.assertEquals(conversationTest.getId(), oraculo.get(0).getId());
        Assert.assertEquals(conversationTest.getSubject(), oraculo.get(0).getSubject());
    }

    @Test
    @InSequence(3)
    public void listConversationTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        Response response = target.path(conversationPath)
                .request().cookie(cookieSessionId).get();
        String listConversation = response.readEntity(String.class);
        List<ConversationDTO> listConversationTest = new ObjectMapper().readValue(listConversation, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(2, listConversationTest.size());
    }

    @Test
    @InSequence(4)
    public void updateConversationTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        ConversationDTO conversation = oraculo.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ConversationDTO conversationChanged = factory.manufacturePojo(ConversationDTO.class);
        conversation.setSubject(conversationChanged.getSubject());
        Response response = target.path(conversationPath).path(conversation.getId().toString())
                .request().cookie(cookieSessionId).put(Entity.entity(conversation, MediaType.APPLICATION_JSON));
        ConversationDTO conversationTest = (ConversationDTO) response.readEntity(ConversationDTO.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(conversation.getSubject(), conversationTest.getSubject());
       
    }

    @Test
    @InSequence(5)
    public void deleteAgreementTest() {
        Cookie cookieSessionId = login(username, password);
        ConversationDTO conversation = oraculo.get(0);
        Response response = target.path(conversationPath).path(conversation.getId().toString())
                .request().cookie(cookieSessionId).delete();
        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }

    
}
