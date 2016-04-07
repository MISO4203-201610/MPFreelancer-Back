/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.tests;

import co.edu.uniandes.csw.auth.model.UserDTO;
import co.edu.uniandes.csw.auth.security.JWT;
import co.edu.uniandes.csw.mpfreelancer.dtos.WorkExperienceDTO;
import co.edu.uniandes.csw.mpfreelancer.dtos.FreelancerDTO;
//import co.edu.uniandes.csw.mpfreelancer.services.ProjectSprintService;
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
public class WorkExperienceTest {
    
    private final int Ok = Status.OK.getStatusCode();
    private final int Created = Status.CREATED.getStatusCode();
    private final int OkWithoutContent = Status.NO_CONTENT.getStatusCode();
    private final String workExperiencePath = "experiences";
    private final static List<WorkExperienceDTO> oraculo = new ArrayList<>();
    private final static List<FreelancerDTO> oraculoFreelancer = new ArrayList<>();
    private WebTarget target;
    private final String apiPath = "api";
    private final String username = System.getenv("USERNAME_USER");
    private final String password = System.getenv("PASSWORD_USER");

    @ArquillianResource
    private URL deploymentURL;

    @Deployment(testable = false)
    public static WebArchive createDeployment() 
    {
        return ShrinkWrap.create(WebArchive.class)
                // Se agrega la dependencia a la logica con el nombre groupid:artefactid:version (GAV)
                .addAsLibraries(Maven.resolver()
                        .resolve("co.edu.uniandes.csw.mpfreelancer:m-p-freelancer-logic:0.1.0")
                        .withTransitivity().asFile())
                .addAsLibraries(Maven.resolver()
                        .resolve("co.edu.uniandes.csw:auth-utils:0.1.0")
                        .withTransitivity().asFile())
                // El archivo que contiene la configuracion a la base de datos.
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                // El archivo beans.xml es necesario para injeccion de dependencias.
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"))
                // El archivo shiro.ini es necesario para injeccion de dependencias
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/shiro.ini"))
                // El archivo web.xml es necesario para el despliegue de los servlets
                .setWebXML(new File("src/main/webapp/WEB-INF/web.xml"));
    }

    private WebTarget createWebTarget() 
    {
        ClientConfig config = new ClientConfig();
        config.register(LoggingFilter.class);
        return ClientBuilder.newClient(config).target(deploymentURL.toString()).path(apiPath);
    }

    @BeforeClass
    public static void setUp() 
    {
        insertData();
    }

    public static void insertData() 
    {
        for (int i = 0; i < 5; i++) {
            PodamFactory factory = new PodamFactoryImpl();
            WorkExperienceDTO workExperience = factory.manufacturePojo(WorkExperienceDTO.class);
            workExperience.setId(i + 1L);

            oraculo.add(workExperience);

            FreelancerDTO freelancers = factory.manufacturePojo(FreelancerDTO.class);
            freelancers.setId(i + 1L);
            oraculoFreelancer.add(freelancers);
        }
    }

    public Cookie login(String username, String password) 
    {
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
    public void setUpTest() 
    {
        target = createWebTarget();
    }

    @Test
    @InSequence(1)
    public void createWorkExperienceTest() throws IOException 
    {
//        WorkExperienceDTO workExperience = oraculo.get(0);
//        Cookie cookieSessionId = login(username, password);
//        
//        Response response = target.path(workExperiencePath)
//                .request().cookie(cookieSessionId)
//                .post(Entity.entity(workExperience, MediaType.APPLICATION_JSON));
//        WorkExperienceDTO  workExperienceTest = (WorkExperienceDTO) response.readEntity(WorkExperienceDTO.class);
//        Assert.assertEquals(workExperience.getId(), workExperienceTest.getId());
//        Assert.assertEquals(workExperience.getProjectName(), workExperienceTest.getProjectName());
//        Assert.assertEquals(workExperience.getProjectDescription(),workExperienceTest.getProjectDescription());
//        Assert.assertEquals(Created, response.getStatus());
    }
//
//    @Test
//    @InSequence(2)
//    public void getWorkExperienceById() 
//    {
//        Cookie cookieSessionId = login(username, password);
//        WorkExperienceDTO workExperienceTest = target.path(workExperiencePath)
//                .path(oraculo.get(0).getId().toString())
//                .request().cookie(cookieSessionId).get(WorkExperienceDTO.class);
//        Assert.assertEquals(workExperienceTest.getId(), oraculo.get(0).getId());
//        Assert.assertEquals(workExperienceTest.getProjectName(), oraculo.get(0).getProjectName());
//        Assert.assertEquals(workExperienceTest.getProjectDescription(), oraculo.get(0).getProjectDescription());
//    }
//
//    @Test
//    @InSequence(3)
//    public void listWorkExperienceTest() throws IOException 
//    {
//        Cookie cookieSessionId = login(username, password);
//        Response response = target.path(workExperiencePath)
//                .request().cookie(cookieSessionId).get();
//        String listWorkExperiences = response.readEntity(String.class);
//        List<WorkExperienceDTO> listWorkExperiencesTest = new ObjectMapper().readValue(listWorkExperiences, List.class);
//        Assert.assertEquals(Ok, response.getStatus());
//        Assert.assertEquals(1, listWorkExperiencesTest.size());
//    }
//
//    @Test
//    @InSequence(4)
//    public void updateWorkExperienceTest() throws IOException {
//        Cookie cookieSessionId = login(username, password);
//        WorkExperienceDTO workExperience = oraculo.get(0);
//        PodamFactory factory = new PodamFactoryImpl();
//        WorkExperienceDTO workExperienceChanged = factory.manufacturePojo(WorkExperienceDTO.class);
//        workExperience.setProjectName(workExperienceChanged.getProjectName());
//       workExperience.setProjectDescription(workExperience.getProjectDescription());
//        Response response = target.path(workExperiencePath).path(workExperience.getId().toString())
//                .request().cookie(cookieSessionId).put(Entity.entity(workExperience, MediaType.APPLICATION_JSON));
//        WorkExperienceDTO workExperienceTest = (WorkExperienceDTO) response.readEntity(WorkExperienceDTO.class);
//        Assert.assertEquals(Ok, response.getStatus());
//        Assert.assertEquals(workExperience.getProjectName(), workExperienceTest.getProjectName());
//        Assert.assertEquals(workExperience.getProjectDescription(), workExperienceTest.getProjectDescription());
//    }
//
//    @Test
//    @InSequence(9)
//    public void deleteWorkExperienceTest() {
//        Cookie cookieSessionId = login(username, password);
//        WorkExperienceDTO workExperience = oraculo.get(0);
//        Response response = target.path(workExperiencePath).path(workExperience.getId().toString())
//                .request().cookie(cookieSessionId).delete();
//        Assert.assertEquals(OkWithoutContent, response.getStatus());
//    }
    
}
