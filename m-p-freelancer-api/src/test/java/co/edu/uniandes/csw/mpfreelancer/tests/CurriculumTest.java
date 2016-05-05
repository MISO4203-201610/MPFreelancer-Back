package co.edu.uniandes.csw.mpfreelancer.tests;

import co.edu.uniandes.csw.auth.model.UserDTO;
import co.edu.uniandes.csw.auth.security.JWT;
import co.edu.uniandes.csw.mpfreelancer.dtos.CurriculumDTO;
import co.edu.uniandes.csw.mpfreelancer.dtos.FreelancerDTO;
import co.edu.uniandes.csw.mpfreelancer.dtos.SkillDTO;
import co.edu.uniandes.csw.mpfreelancer.dtos.EducationDTO;
import co.edu.uniandes.csw.mpfreelancer.services.FreelancerService;
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
public class CurriculumTest {

    private final int Ok = Status.OK.getStatusCode();
    private final int Created = Status.CREATED.getStatusCode();
    private final int Update = 200;
    private final int Delete = 204;
    private final int OkWithoutContent = Status.NO_CONTENT.getStatusCode();
    private final String freelancerPath = "freelancers";
    private final static List<FreelancerDTO> oraculo = new ArrayList<>();
    private final String skillsPath = "skills";
    private final static List<SkillDTO> oraculoSkills = new ArrayList<>();
    private final String curriculumPath = "curriculums";
    private final static List<CurriculumDTO> oraculoCurriculum = new ArrayList<>();
    private WebTarget target;
    private final String apiPath = "api";
    private final String username = System.getenv("USERNAME_USER");
    private final String password = System.getenv("PASSWORD_USER");

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
                .addPackage(FreelancerService.class.getPackage())
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
        for (int i = 0; i < 5; i++) {
            PodamFactory factory = new PodamFactoryImpl();
            FreelancerDTO freelancer = factory.manufacturePojo(FreelancerDTO.class);
            freelancer.setId(i + 1L);
            List<EducationDTO> titlesList = new ArrayList<>();
            for (int j = 0; j < 5; j++)
            {
                EducationDTO titles = factory.manufacturePojo(EducationDTO.class);
                titles.setId(i + 1L);
                titlesList.add(titles);
            }

            freelancer.setTitles(titlesList);

            oraculo.add(freelancer);

            CurriculumDTO curriculum = factory.manufacturePojo(CurriculumDTO.class);
            curriculum.setId(i + 1L);
            oraculoCurriculum.add(curriculum);
            
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
    public void createFreelancerTest() throws IOException {
        FreelancerDTO freelancer = oraculo.get(0);
        Cookie cookieSessionId = login(username, password);
        Response response = target.path(freelancerPath)
                .request().cookie(cookieSessionId)
                .post(Entity.entity(freelancer, MediaType.APPLICATION_JSON));
        FreelancerDTO  freelancerTest = (FreelancerDTO) response.readEntity(FreelancerDTO.class);
        Assert.assertEquals(freelancer.getId(), freelancerTest.getId());
        Assert.assertEquals(freelancer.getName(), freelancerTest.getName());
        Assert.assertEquals(freelancer.getRate(), freelancerTest.getRate());
        Assert.assertEquals(freelancer.getBithday(), freelancerTest.getBithday());
        Assert.assertEquals(freelancer.getPicture(), freelancerTest.getPicture());
        Assert.assertEquals(Created, response.getStatus());
    }

    @Test
    @InSequence(2)
    public void getFreelancerById() {
        Cookie cookieSessionId = login(username, password);
        FreelancerDTO freelancerTest = target.path(freelancerPath)
                .path(oraculo.get(0).getId().toString())
                .request().cookie(cookieSessionId).get(FreelancerDTO.class);
        Assert.assertEquals(freelancerTest.getId(), oraculo.get(0).getId());
        Assert.assertEquals(freelancerTest.getName(), oraculo.get(0).getName());
        Assert.assertEquals(freelancerTest.getRate(), oraculo.get(0).getRate());
        Assert.assertEquals(freelancerTest.getBithday(), oraculo.get(0).getBithday());
        Assert.assertEquals(freelancerTest.getPicture(), oraculo.get(0).getPicture());
    }
    
    @Test
    @InSequence(3)
    public void getCurrentFreelancer() {
        Cookie cookieSessionId = login(username, password);
        FreelancerDTO freelancerTest = target.path(freelancerPath)
                .path("current")
                .request().cookie(cookieSessionId).get(FreelancerDTO.class);
        Assert.assertEquals(freelancerTest.getId(), oraculo.get(0).getId());
        Assert.assertEquals(freelancerTest.getName(), oraculo.get(0).getName());
        Assert.assertEquals(freelancerTest.getRate(), oraculo.get(0).getRate());
        Assert.assertEquals(freelancerTest.getBithday(), oraculo.get(0).getBithday());
        Assert.assertEquals(freelancerTest.getPicture(), oraculo.get(0).getPicture());
    }

    @Test
    @InSequence(4)
    public void listFreelancerTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        Response response = target.path(freelancerPath)
                .request().cookie(cookieSessionId).get();
        String listFreelancer = response.readEntity(String.class);
        List<FreelancerDTO> listFreelancerTest = new ObjectMapper().readValue(listFreelancer, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(1, listFreelancerTest.size());
    }

    @Test
    @InSequence(5)
    public void updateFreelancerTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        FreelancerDTO freelancer = oraculo.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        FreelancerDTO freelancerChanged = factory.manufacturePojo(FreelancerDTO.class);
        freelancer.setName(freelancerChanged.getName());
        freelancer.setRate(freelancerChanged.getRate());
        freelancer.setBithday(freelancerChanged.getBithday());
        freelancer.setPicture(freelancerChanged.getPicture());
        Response response = target.path(freelancerPath).path(freelancer.getId().toString())
                .request().cookie(cookieSessionId).put(Entity.entity(freelancer, MediaType.APPLICATION_JSON));
        FreelancerDTO freelancerTest = (FreelancerDTO) response.readEntity(FreelancerDTO.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(freelancer.getName(), freelancerTest.getName());
        Assert.assertEquals(freelancer.getRate(), freelancerTest.getRate());
        Assert.assertEquals(freelancer.getBithday(), freelancerTest.getBithday());
        Assert.assertEquals(freelancer.getPicture(), freelancerTest.getPicture());
    }

    @Test
    @InSequence(9)
    public void deleteFreelancerTest() {
        Cookie cookieSessionId = login(username, password);
        FreelancerDTO freelancer = oraculo.get(0);
        Response response = target.path(freelancerPath).path(freelancer.getId().toString())
                .request().cookie(cookieSessionId).delete();
        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }

    @Test
    @InSequence(6)
    public void addCurriculumTest() {
        Cookie cookieSessionId = login(username, password);

        CurriculumDTO curriculum = oraculoCurriculum.get(0);

//  Ciclo 1 Validación del servicio de creación de Curriculums sin asociación con Freelancer.
//          De acuerdo con el alcance definido para el Ciclo 1, se crea Curriculum sin asociación a Freelancer.

//  Creación de Curriculum

        Response response = target.path("curriculums")
                .request().cookie(cookieSessionId)
                .post(Entity.entity(curriculum, MediaType.APPLICATION_JSON));

        CurriculumDTO curriculumTest = (CurriculumDTO) response.readEntity(CurriculumDTO.class);
        Assert.assertEquals(curriculum.getId(), curriculumTest.getId());
        Assert.assertEquals(curriculum.getProfile(), curriculumTest.getProfile());
        Assert.assertEquals(curriculum.getIdentification(), curriculumTest.getIdentification());
        Assert.assertEquals(curriculum.getEmail(), curriculumTest.getEmail());
        Assert.assertEquals(curriculum.getMobile(), curriculumTest.getMobile());        
        Assert.assertEquals(Created, response.getStatus());
        
//        response = target.path(freelancerPath).path(freelancer.getId().toString())
//                .path(skillsPath).path(skills.getId().toString())
//                .request().cookie(cookieSessionId)
//                .post(Entity.entity(skills, MediaType.APPLICATION_JSON));

//        skillsTest = (SkillDTO) response.readEntity(SkillDTO.class);
//        Assert.assertEquals(Ok, response.getStatus());
//        Assert.assertEquals(skills.getId(), skillsTest.getId());
    }
    @Test
    @InSequence(7)
    public void updateCurriculumTest() {
        Cookie cookieSessionId = login(username, password);

        CurriculumDTO curriculum = oraculoCurriculum.get(0);

//  Ciclo 1 Validación del servicio de actualización de Curriculums sin asociación con Freelancer.
//          De acuerdo con el alcance definido para el Ciclo 1, se crea Curriculum sin asociación a Freelancer.
        
//  Actualización de Curriculum

        curriculum.setProfile("Update_Profile");
        curriculum.setIdentification("Update_Identification");
        curriculum.setEmail("Update_Email");    
        curriculum.setMobile("Update_Mobile");    
        
        Response response = target.path("curriculums").path(curriculum.getId().toString())
                .request().cookie(cookieSessionId)
                .put(Entity.entity(curriculum, MediaType.APPLICATION_JSON));

        CurriculumDTO curriculumTest = (CurriculumDTO) response.readEntity(CurriculumDTO.class);
        Assert.assertEquals(curriculum.getId(), curriculumTest.getId());
        Assert.assertEquals(curriculum.getProfile(), curriculumTest.getProfile());
        Assert.assertEquals(curriculum.getIdentification(), curriculumTest.getIdentification());
        Assert.assertEquals(curriculum.getEmail(), curriculumTest.getEmail());
        Assert.assertEquals(curriculum.getMobile(), curriculumTest.getMobile());        
        Assert.assertEquals(Update, response.getStatus());    
    }
    @Test
    @InSequence(8)
    public void deleteCurriculumTest() {
        Cookie cookieSessionId = login(username, password);

        CurriculumDTO curriculum = oraculoCurriculum.get(0);

//  Ciclo 1 Validación del servicio de borrado de Curriculum sin asociación con Freelancer.
//          De acuerdo con el alcance definido para el Ciclo 1, se crea Curriculum sin asociación a Freelancer.
    
//  Borrado de Curriculum
        
        Response response = target.path("curriculums").path(curriculum.getId().toString())
                .request().cookie(cookieSessionId)
                .delete();
        Assert.assertEquals(Delete, response.getStatus());
        
    }
}