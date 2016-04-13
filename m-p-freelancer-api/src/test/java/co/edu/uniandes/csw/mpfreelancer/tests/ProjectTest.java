package co.edu.uniandes.csw.mpfreelancer.tests;

import co.edu.uniandes.csw.auth.model.UserDTO;
import co.edu.uniandes.csw.auth.security.JWT;
import co.edu.uniandes.csw.mpfreelancer.dtos.ProjectDTO;
import co.edu.uniandes.csw.mpfreelancer.dtos.ProjectSponsorDTO;
import co.edu.uniandes.csw.mpfreelancer.dtos.SkillDTO;
import co.edu.uniandes.csw.mpfreelancer.services.ProjectService;
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
public class ProjectTest {

    private final static int Ok = Status.OK.getStatusCode();
    private final int Created = Status.CREATED.getStatusCode();
    private final int OkWithoutContent = Status.NO_CONTENT.getStatusCode();
    private final String projectPath = "projects";
    private final static List<ProjectDTO> oraculo = new ArrayList<>();
    private final String expectedskillsPath = "expectedskills";
    private final static List<SkillDTO> oraculoExpectedskills = new ArrayList<>();
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
                .addPackage(ProjectService.class.getPackage())
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
            ProjectDTO project = factory.manufacturePojo(ProjectDTO.class);
            project.setId(i + 1L);

            oraculo.add(project);

            SkillDTO expectedskills = factory.manufacturePojo(SkillDTO.class);
            expectedskills.setId(i + 1L);
            oraculoExpectedskills.add(expectedskills);
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
    public void createProjectTest() throws IOException {
        ProjectDTO project = oraculo.get(0);
        Cookie cookieSessionId = login(username, password);
        
        PodamFactory factory = new PodamFactoryImpl();
        ProjectSponsorDTO projectSponsor = factory.manufacturePojo(ProjectSponsorDTO.class);
        projectSponsor.setId(1L);
        
        Response response = target.path("projectSponsors")
                .request().cookie(cookieSessionId)
                .post(Entity.entity(projectSponsor, MediaType.APPLICATION_JSON));
        
        response = target.path(projectPath)
                .request().cookie(cookieSessionId)
                .post(Entity.entity(project, MediaType.APPLICATION_JSON));
        ProjectDTO  projectTest = (ProjectDTO) response.readEntity(ProjectDTO.class);
        Assert.assertEquals(project.getId(), projectTest.getId());
        Assert.assertEquals(project.getName(), projectTest.getName());
        Assert.assertEquals(project.getDescription(), projectTest.getDescription());
        Assert.assertEquals(project.getPrice(), projectTest.getPrice());
        Assert.assertEquals(project.getDeadLine(), projectTest.getDeadLine());
        Assert.assertEquals(project.getPublicationDate(), projectTest.getPublicationDate());
        Assert.assertEquals(project.getStartDate(), projectTest.getStartDate());
        Assert.assertEquals(Created, response.getStatus());
    }

    @Test
    @InSequence(2)
    public void getProjectById() {
        Cookie cookieSessionId = login(username, password);
        ProjectDTO projectTest = target.path(projectPath)
                .path(oraculo.get(0).getId().toString())
                .request().cookie(cookieSessionId).get(ProjectDTO.class);
        Assert.assertEquals(projectTest.getId(), oraculo.get(0).getId());
        Assert.assertEquals(projectTest.getName(), oraculo.get(0).getName());
        Assert.assertEquals(projectTest.getDescription(), oraculo.get(0).getDescription());
        Assert.assertEquals(projectTest.getPrice(), oraculo.get(0).getPrice());
        Assert.assertEquals(projectTest.getDeadLine(), oraculo.get(0).getDeadLine());
        Assert.assertEquals(projectTest.getPublicationDate(), oraculo.get(0).getPublicationDate());
        Assert.assertEquals(projectTest.getStartDate(), oraculo.get(0).getStartDate());
    }

    @Test
    @InSequence(3)
    public void listProjectTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        ProjectDTO projects = oraculo.get(0);
        
        
        
        
        Response response = target.path("projectSponsors").path("1")
                .path(projectPath).path(projects.getId().toString())
                .request().cookie(cookieSessionId)
                .post(Entity.entity(projects, MediaType.APPLICATION_JSON));
        
        response = target.path(projectPath)
                .request().cookie(cookieSessionId).get();
        String listProject = response.readEntity(String.class);
        List<ProjectDTO> listProjectTest = new ObjectMapper().readValue(listProject, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        //Assert.assertEquals(1, listProjectTest.size());
    }
    
    @Test
    @InSequence(4)
    public void listAllProjectTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        ProjectDTO projects = oraculo.get(0);
        
        Response response = target.path(projectPath).path("all")
                .request().cookie(cookieSessionId).get();
        String listProject = response.readEntity(String.class);
        List<ProjectDTO> listProjectTest = new ObjectMapper().readValue(listProject, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(1, listProjectTest.size());
    }

    @Test
    @InSequence(5)
    public void updateProjectTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        ProjectDTO project = oraculo.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ProjectDTO projectChanged = factory.manufacturePojo(ProjectDTO.class);
        project.setName(projectChanged.getName());
        project.setDescription(projectChanged.getDescription());
        project.setPrice(projectChanged.getPrice());
        project.setDeadLine(projectChanged.getDeadLine());
        project.setPublicationDate(projectChanged.getPublicationDate());
        project.setStartDate(projectChanged.getStartDate());
        Response response = target.path(projectPath).path(project.getId().toString())
                .request().cookie(cookieSessionId).put(Entity.entity(project, MediaType.APPLICATION_JSON));
        ProjectDTO projectTest = (ProjectDTO) response.readEntity(ProjectDTO.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(project.getName(), projectTest.getName());
        Assert.assertEquals(project.getDescription(), projectTest.getDescription());
        Assert.assertEquals(project.getPrice(), projectTest.getPrice());
        Assert.assertEquals(project.getDeadLine(), projectTest.getDeadLine());
        Assert.assertEquals(project.getPublicationDate(), projectTest.getPublicationDate());
        Assert.assertEquals(project.getStartDate(), projectTest.getStartDate());
    }

    @Test
    @InSequence(10)
    public void deleteProjectTest() {
        Cookie cookieSessionId = login(username, password);
        ProjectDTO project = oraculo.get(0);
        Response response = target.path(projectPath).path(project.getId().toString())
                .request().cookie(cookieSessionId).delete();
        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }

    @Test
    @InSequence(6)
    public void addExpectedskillsTest() {
        Cookie cookieSessionId = login(username, password);

        SkillDTO expectedskills = oraculoExpectedskills.get(0);
        ProjectDTO project = oraculo.get(0);


        Response response = target.path("skills")
                .request().cookie(cookieSessionId)
                .post(Entity.entity(expectedskills, MediaType.APPLICATION_JSON));

        SkillDTO expectedskillsTest = (SkillDTO) response.readEntity(SkillDTO.class);
        Assert.assertEquals(expectedskills.getId(), expectedskillsTest.getId());
        Assert.assertEquals(expectedskills.getName(), expectedskillsTest.getName());
        Assert.assertEquals(expectedskills.getDescription(), expectedskillsTest.getDescription());
        Assert.assertEquals(Created, response.getStatus());

        response = target.path(projectPath).path(project.getId().toString())
                .path(expectedskillsPath).path(expectedskills.getId().toString())
                .request().cookie(cookieSessionId)
                .post(Entity.entity(expectedskills, MediaType.APPLICATION_JSON));

        expectedskillsTest = (SkillDTO) response.readEntity(SkillDTO.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(expectedskills.getId(), expectedskillsTest.getId());
    }

    @Test
    @InSequence(7)
    public void listExpectedskillsTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        ProjectDTO project = oraculo.get(0);

        Response response = target.path(projectPath)
                .path(project.getId().toString())
                .path(expectedskillsPath)
                .request().cookie(cookieSessionId).get();

        String expectedskillsList = response.readEntity(String.class);
        List<SkillDTO> expectedskillsListTest = new ObjectMapper().readValue(expectedskillsList, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(1, expectedskillsListTest.size());
    }

    @Test
    @InSequence(8)
    public void getExpectedskillsTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        SkillDTO expectedskills = oraculoExpectedskills.get(0);
        ProjectDTO project = oraculo.get(0);

        SkillDTO expectedskillsTest = target.path(projectPath)
                .path(project.getId().toString()).path(expectedskillsPath)
                .path(expectedskills.getId().toString())
                .request().cookie(cookieSessionId).get(SkillDTO.class);

        Assert.assertEquals(expectedskills.getId(), expectedskillsTest.getId());
        Assert.assertEquals(expectedskills.getName(), expectedskillsTest.getName());
        Assert.assertEquals(expectedskills.getDescription(), expectedskillsTest.getDescription());
    }

    @Test
    @InSequence(9)
    public void removeExpectedskillsTest() {
        Cookie cookieSessionId = login(username, password);

        SkillDTO expectedskills = oraculoExpectedskills.get(0);
        ProjectDTO project = oraculo.get(0);

        Response response = target.path(projectPath).path(project.getId().toString())
                .path(expectedskillsPath).path(expectedskills.getId().toString())
                .request().cookie(cookieSessionId).delete();
        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }
}
