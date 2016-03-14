package co.edu.uniandes.csw.mpfreelancer.tests;

import co.edu.uniandes.csw.auth.model.UserDTO;
import co.edu.uniandes.csw.auth.security.JWT;
import co.edu.uniandes.csw.mpfreelancer.dtos.ProjectSprintDTO;
import co.edu.uniandes.csw.mpfreelancer.dtos.ProjectDTO;
import co.edu.uniandes.csw.mpfreelancer.services.ProjectSprintService;
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

@RunWith(Arquillian.class)
public class ProjectSprintTest 
{
    private final int Ok = Status.OK.getStatusCode();
    private final int Created = Status.CREATED.getStatusCode();
    private final int OkWithoutContent = Status.NO_CONTENT.getStatusCode();
    private final String projectSprintPath = "sprints";
    private final static List<ProjectSprintDTO> oraculo = new ArrayList<>();
    private final static List<ProjectDTO> oraculoProjects = new ArrayList<>();
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
                // Se agregan los compilados de los paquetes de servicios
                .addPackage(ProjectSprintService.class.getPackage())
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
            ProjectSprintDTO projectSprint = factory.manufacturePojo(ProjectSprintDTO.class);
            projectSprint.setId(i + 1L);

            oraculo.add(projectSprint);

            ProjectDTO projects = factory.manufacturePojo(ProjectDTO.class);
            projects.setId(i + 1L);
            oraculoProjects.add(projects);
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
    public void createProjectSprintTest() throws IOException 
    {
        ProjectSprintDTO projectSprint = oraculo.get(0);
        Cookie cookieSessionId = login(username, password);
        
        Response response = target.path(projectSprintPath)
                .request().cookie(cookieSessionId)
                .post(Entity.entity(projectSprint, MediaType.APPLICATION_JSON));
        ProjectSprintDTO  projectSprintTest = (ProjectSprintDTO) response.readEntity(ProjectSprintDTO.class);
        Assert.assertEquals(projectSprint.getId(), projectSprintTest.getId());
        Assert.assertEquals(projectSprint.getName(), projectSprintTest.getName());
        Assert.assertEquals(projectSprint.getDescription(), projectSprintTest.getDescription());
        Assert.assertEquals(Created, response.getStatus());
    }

    @Test
    @InSequence(2)
    public void getProjectSprintById() 
    {
        Cookie cookieSessionId = login(username, password);
        ProjectSprintDTO projectSprintTest = target.path(projectSprintPath)
                .path(oraculo.get(0).getId().toString())
                .request().cookie(cookieSessionId).get(ProjectSprintDTO.class);
        Assert.assertEquals(projectSprintTest.getId(), oraculo.get(0).getId());
        Assert.assertEquals(projectSprintTest.getName(), oraculo.get(0).getName());
        Assert.assertEquals(projectSprintTest.getDescription(), oraculo.get(0).getDescription());
    }

    @Test
    @InSequence(3)
    public void listProjectSprintTest() throws IOException 
    {
        Cookie cookieSessionId = login(username, password);
        Response response = target.path(projectSprintPath)
                .request().cookie(cookieSessionId).get();
        String listProjectSprint = response.readEntity(String.class);
        List<ProjectSprintDTO> listProjectSprintTest = new ObjectMapper().readValue(listProjectSprint, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(1, listProjectSprintTest.size());
    }

    @Test
    @InSequence(4)
    public void updateProjectSprintTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        ProjectSprintDTO projectSprint = oraculo.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ProjectSprintDTO projectSprintChanged = factory.manufacturePojo(ProjectSprintDTO.class);
        projectSprint.setName(projectSprintChanged.getName());
        projectSprint.setDescription(projectSprintChanged.getDescription());
        Response response = target.path(projectSprintPath).path(projectSprint.getId().toString())
                .request().cookie(cookieSessionId).put(Entity.entity(projectSprint, MediaType.APPLICATION_JSON));
        ProjectSprintDTO projectSprintTest = (ProjectSprintDTO) response.readEntity(ProjectSprintDTO.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(projectSprint.getName(), projectSprintTest.getName());
        Assert.assertEquals(projectSprint.getDescription(), projectSprintTest.getDescription());
    }

    @Test
    @InSequence(9)
    public void deleteProjectSprintTest() {
        Cookie cookieSessionId = login(username, password);
        ProjectSprintDTO projectSprint = oraculo.get(0);
        Response response = target.path(projectSprintPath).path(projectSprint.getId().toString())
                .request().cookie(cookieSessionId).delete();
        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }
}
