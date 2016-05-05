/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.tests;

import co.edu.uniandes.csw.auth.model.UserDTO;
import co.edu.uniandes.csw.auth.security.JWT;
import co.edu.uniandes.csw.mpfreelancer.dtos.MailDTO;
import co.edu.uniandes.csw.mpfreelancer.dtos.ConversationDTO;
import co.edu.uniandes.csw.mpfreelancer.services.MailService;
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
public class MailTest {
        
        /*
    
    private final static int Ok = Response.Status.OK.getStatusCode();
    private final int Created = Response.Status.CREATED.getStatusCode();
    private final int OkWithoutContent = Response.Status.NO_CONTENT.getStatusCode();
    private final String mailPath = "mails";
    private final static List<MailDTO> oraculo = new ArrayList<>();
    private final String conversationPath = "conversations";
    private final static List<ConversationDTO> oraculoConversation = new ArrayList<>();
    private static WebTarget target;
    private final static String apiPath = "api";
    private final static String username = System.getenv("USERNAME_USER");
    private final static String password = System.getenv("PASSWORD_USER");

    @ArquillianResource
    private static URL deploymentURL;

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
                .addPackage(MailService.class.getPackage())
                // El archivo que contiene la configuracion a la base de datos.
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                // El archivo beans.xml es necesario para injeccion de dependencias.
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"))
                // El archivo shiro.ini es necesario para injeccion de dependencias
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/shiro.ini"))
                // El archivo web.xml es necesario para el despliegue de los servlets
                .setWebXML(new File("src/main/webapp/WEB-INF/web.xml"));
    }

    private static WebTarget createWebTarget() {
        ClientConfig config = new ClientConfig();
        config.register(LoggingFilter.class);
        return ClientBuilder.newClient(config).target(deploymentURL.toString()).path(apiPath);
    }

    @BeforeClass
    public static void setUp() {
        
        insertData();
        
    }

    public static void insertData() {
        for (int i = 0; i < 5; i++) {
            PodamFactory factory = new PodamFactoryImpl();
            MailDTO mail = factory.manufacturePojo(MailDTO.class);
            mail.setId(i + 1L);

            oraculo.add(mail);
            
            ConversationDTO conversations = factory.manufacturePojo(ConversationDTO.class);
            conversations.setId(i + 1L);
            oraculoConversation.add(conversations);

            }
        
        
        
    }

    public static Cookie login(String username, String password) {
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
    public void createMailTest() throws IOException {
        MailDTO mail = oraculo.get(0);
        Cookie cookieSessionId = login(username, password);
        
        PodamFactory factory = new PodamFactoryImpl();
        ConversationDTO conversation = factory.manufacturePojo(ConversationDTO.class);
        conversation.setId(1L);
        
        Response response = target.path(conversationPath)
                .request().cookie(cookieSessionId)
                .post(Entity.entity(conversation, MediaType.APPLICATION_JSON));
        
        response = target.path(mailPath)
                .request().cookie(cookieSessionId)
                .post(Entity.entity(mail, MediaType.APPLICATION_JSON));
        MailDTO  mailTest = (MailDTO) response.readEntity(MailDTO.class);
        Assert.assertEquals(mail.getId(), mailTest.getId());
        Assert.assertEquals(mail.getWho(), mailTest.getWho());
        Assert.assertEquals(mail.getMessge(), mailTest.getMessge());
        Assert.assertEquals(mail.getDatemail(), mailTest.getDatemail());
        Assert.assertEquals(Created, response.getStatus());
    }

    @Test
    @InSequence(2)
    public void getMailById() {
        Cookie cookieSessionId = login(username, password);
        MailDTO mailTest = target.path(mailPath)
                .path(oraculo.get(0).getId().toString())
                .request().cookie(cookieSessionId).get(MailDTO.class);
        Assert.assertEquals(mailTest.getId(), oraculo.get(0).getId());
        Assert.assertEquals(mailTest.getWho(), oraculo.get(0).getWho());
        Assert.assertEquals(mailTest.getMessge(), oraculo.get(0).getMessge());
        Assert.assertEquals(mailTest.getDatemail(), oraculo.get(0).getDatemail());

    }

    @Test
    @InSequence(3)
    public void listMailTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        MailDTO projects = oraculo.get(0);
        
        
        Response response = target.path(conversationPath).path("1")
                .path(mailPath).path(projects.getId().toString())
                .request().cookie(cookieSessionId)
                .post(Entity.entity(projects, MediaType.APPLICATION_JSON));
        
        response = target.path(mailPath)
                .request().cookie(cookieSessionId).get();
        String listProject = response.readEntity(String.class);
        List<MailDTO> listMailTest = new ObjectMapper().readValue(listProject, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        //Assert.assertEquals(1, listProjectTest.size());
    }
    
    @Test
    @InSequence(4)
    public void listAllMailTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        MailDTO mails = oraculo.get(0);
        
        Response response = target.path(mailPath).path("all")
                .request().cookie(cookieSessionId).get();
        String listMail = response.readEntity(String.class);
        List<MailDTO> listMailTest = new ObjectMapper().readValue(listMail, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(1, listMailTest.size());
    }

    @Test
    @InSequence(5)
    public void updateMailTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        MailDTO mail = oraculo.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        MailDTO mailChanged = factory.manufacturePojo(MailDTO.class);
        mail.setWho(mailChanged.getWho());
        mail.setMessage(mailChanged.getMessge());
        mail.setDatemail(mailChanged.getDatemail());
        
        Response response = target.path(mailPath).path(mail.getId().toString())
                .request().cookie(cookieSessionId).put(Entity.entity(mail, MediaType.APPLICATION_JSON));
        MailDTO mailTest = (MailDTO) response.readEntity(MailDTO.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(mail.getWho(), mailTest.getWho());
        Assert.assertEquals(mail.getMessge(), mailTest.getMessge());
        Assert.assertEquals(mail.getDatemail(), mailTest.getDatemail());
        
    }

    @Test
    @InSequence(10)
    public void deleteProjectTest() {
        Cookie cookieSessionId = login(username, password);
        MailDTO project = oraculo.get(0);
        Response response = target.path(mailPath).path(project.getId().toString())
                .request().cookie(cookieSessionId).delete();
        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }

*/

 

   
    
}
