package co.edu.uniandes.csw.mpfreelancer.tests;

import co.edu.uniandes.csw.auth.model.UserDTO;
import co.edu.uniandes.csw.auth.security.JWT;
import co.edu.uniandes.csw.mpfreelancer.dtos.BlogEntryDTO;
import co.edu.uniandes.csw.mpfreelancer.dtos.CurriculumDTO;
import co.edu.uniandes.csw.mpfreelancer.dtos.WorkExperienceDTO;
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
public class FreelancerTest {

    private final int Ok = Status.OK.getStatusCode();
    private final int Created = Status.CREATED.getStatusCode();
    private final int Update = 200;
    private final int Delete = 204;
    private final int OkWithoutContent = Status.NO_CONTENT.getStatusCode();
    private final String freelancerPath = "freelancers";
    private final static List<FreelancerDTO> oraculo = new ArrayList<>();
    private final String skillsPath = "skills";
    private final static List<SkillDTO> oraculoSkills = new ArrayList<>();
    private final static List<BlogEntryDTO> oraculoBlogEntries = new ArrayList<>();
    private final static List<WorkExperienceDTO> oraculoWorkExperience = new ArrayList<>();
    private final String curriculumPath = "curriculums";
    private final static List<CurriculumDTO> oraculoCurriculums = new ArrayList<>();
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

            SkillDTO skills = factory.manufacturePojo(SkillDTO.class);
            skills.setId(i + 1L);
            oraculoSkills.add(skills);
           
            // Se crean datos aleatorios para oraculoBlogEntries
            
            BlogEntryDTO blogEntries = factory.manufacturePojo(BlogEntryDTO.class);
            blogEntries.setId(i + 1L);
            oraculoBlogEntries.add(blogEntries);

            CurriculumDTO curriculum = factory.manufacturePojo(CurriculumDTO.class);
            curriculum.setId(i + 1L);
            oraculoCurriculums.add(curriculum);
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
    @InSequence(6)
    public void addSkillsTest() {
        Cookie cookieSessionId = login(username, password);

        SkillDTO skills = oraculoSkills.get(0);
        FreelancerDTO freelancer = oraculo.get(0);


        Response response = target.path("skills")
                .request().cookie(cookieSessionId)
                .post(Entity.entity(skills, MediaType.APPLICATION_JSON));

        SkillDTO skillsTest = (SkillDTO) response.readEntity(SkillDTO.class);
        Assert.assertEquals(skills.getId(), skillsTest.getId());
        Assert.assertEquals(skills.getName(), skillsTest.getName());
        Assert.assertEquals(skills.getDescription(), skillsTest.getDescription());
        Assert.assertEquals(Created, response.getStatus());

        response = target.path(freelancerPath).path(freelancer.getId().toString())
                .path(skillsPath).path(skills.getId().toString())
                .request().cookie(cookieSessionId)
                .post(Entity.entity(skills, MediaType.APPLICATION_JSON));

        skillsTest = (SkillDTO) response.readEntity(SkillDTO.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(skills.getId(), skillsTest.getId());
    }

    @Test
    @InSequence(7)
    public void listSkillsTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        FreelancerDTO freelancer = oraculo.get(0);

        Response response = target.path(freelancerPath)
                .path(freelancer.getId().toString())
                .path(skillsPath)
                .request().cookie(cookieSessionId).get();

        String skillsList = response.readEntity(String.class);
        List<SkillDTO> skillsListTest = new ObjectMapper().readValue(skillsList, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(1, skillsListTest.size());
    }

    @Test
    @InSequence(8)
    public void getSkillsTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        SkillDTO skills = oraculoSkills.get(0);
        FreelancerDTO freelancer = oraculo.get(0);

        SkillDTO skillsTest = target.path(freelancerPath)
                .path(freelancer.getId().toString()).path(skillsPath)
                .path(skills.getId().toString())
                .request().cookie(cookieSessionId).get(SkillDTO.class);

        Assert.assertEquals(skills.getId(), skillsTest.getId());
        Assert.assertEquals(skills.getName(), skillsTest.getName());
        Assert.assertEquals(skills.getDescription(), skillsTest.getDescription());
    }

    @Test
    @InSequence(9)
    public void removeSkillsTest() {
        Cookie cookieSessionId = login(username, password);

        SkillDTO skills = oraculoSkills.get(0);
        FreelancerDTO freelancer = oraculo.get(0);

        Response response = target.path(freelancerPath).path(freelancer.getId().toString())
                .path(skillsPath).path(skills.getId().toString())
                .request().cookie(cookieSessionId).delete();
        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }
    
    /*
    Este test prueba la creación de un blog en un freelancer.
     */
    @Test
    @InSequence(10)
    public void addBlogEntryTest() {
        Cookie cookieSessionId = login(username, password);

        // Se preparan los datos de entrada creando un freelancer con un BlogEntry
        
        List<BlogEntryDTO> tmpOraculoBlogEntry = new ArrayList<>();
        tmpOraculoBlogEntry.add(oraculoBlogEntries.get(0));
        FreelancerDTO freelancer = oraculo.get(0);
        freelancer.setBlogEntries(tmpOraculoBlogEntry);

        // Se realiza llamado a freelancer enviando entidad de blogEntries
        
        Response response = target.path("freelancers").path(freelancer.getId().toString())
                .request().cookie(cookieSessionId)
                .put(Entity.entity(freelancer, MediaType.APPLICATION_JSON));
        
        // Se realiza comparación entre lo enviado y lo recibido del request

        FreelancerDTO freelancerTest = (FreelancerDTO) response.readEntity(FreelancerDTO.class);
        Assert.assertEquals(freelancer.getBlogEntries().get(0).getContent(), freelancerTest.getBlogEntries().get(0).getContent());
        Assert.assertEquals(Ok, response.getStatus());

    }
    
    /*
    Este test realiza prueba modificando un BlogEntry de un Freelancer.
     */
    @Test
    @InSequence(11)
    public void updateBlogEntryTest() {
        //Se realiza el login de un usuario
        Cookie cookieSessionId = login(username, password);
        // Se obtiene el Freelancer
        FreelancerDTO freelancer = oraculo.get(0);
        //Se obtiene el FreelancerTest
        FreelancerDTO freelancerTestResponse = target.path(freelancerPath)
                .path(oraculo.get(0).getId().toString())
                .request().cookie(cookieSessionId).get(FreelancerDTO.class);
        // Se obtiene la entrada del blog que se creo para Freelancer
        List<BlogEntryDTO> blogEntryFreelancer = freelancer.getBlogEntries();
        // Se modifica la entrada
        blogEntryFreelancer.get(0).setContent("Nuevo");
        // Se agrega al freelancer
        freelancer.setBlogEntries(blogEntryFreelancer);
        // Se envía la modificación para su revisión
        Response response = target.path("freelancers").path(freelancer.getId().toString())
                .request().cookie(cookieSessionId)
                .put(Entity.entity(freelancer, MediaType.APPLICATION_JSON));
        // Se compara que la modificación se encuentre correcta
        FreelancerDTO freelancerTest = (FreelancerDTO) response.readEntity(FreelancerDTO.class);
        Assert.assertEquals("Nuevo", freelancerTest.getBlogEntries().get(0).getContent());
        Assert.assertEquals(1, freelancerTest.getBlogEntries().size());
        Assert.assertEquals(Ok, response.getStatus());
    }
    
    /*
    Este test realiza prueba eliminando un BlogEntry de un Freelancer.
     */
    @Test
    @InSequence(12)
    public void deleteBlogEntryTest() {
        //Se realiza el login de un usuario
        Cookie cookieSessionId = login(username, password);
        // Se obtiene el Freelancer
        FreelancerDTO freelancer = oraculo.get(0);
        // Se le agregan dos elementos EntryBlog
        List<BlogEntryDTO> tmpOraculoBlogEntry = new ArrayList<>();
        tmpOraculoBlogEntry.add(oraculoBlogEntries.get(0));
        tmpOraculoBlogEntry.add(oraculoBlogEntries.get(1));
        freelancer.setBlogEntries(tmpOraculoBlogEntry);
        // Crea el freelancer con dos objetos EntryBlog
        Response response = target.path("freelancers").path(freelancer.getId().toString())
                .request().cookie(cookieSessionId)
                .put(Entity.entity(freelancer, MediaType.APPLICATION_JSON));
        // Se verifica que el freelancer que se creó tenga dos entidades
        FreelancerDTO freelancerTest = (FreelancerDTO) response.readEntity(FreelancerDTO.class);
        Assert.assertEquals(2, freelancerTest.getBlogEntries().size());
        // Se elimina una de las entidades
        tmpOraculoBlogEntry.remove(0);
        freelancer.setBlogEntries(tmpOraculoBlogEntry);
        // Se envia nuevamente la petición 
        response = target.path("freelancers").path(freelancer.getId().toString())
                .request().cookie(cookieSessionId)
                .put(Entity.entity(freelancer, MediaType.APPLICATION_JSON));
        // Se compara que la modificación se encuentre correcta
        freelancerTest = (FreelancerDTO) response.readEntity(FreelancerDTO.class);
        Assert.assertEquals(1, freelancerTest.getBlogEntries().size());
        Assert.assertEquals(Ok, response.getStatus());
    }
 
   /*
    Este test prueba la creación de un curriculum en un freelancer.
     */
    @Test
    @InSequence(13)
    public void addCurriculumTest() {
        Cookie cookieSessionId = login(username, password);

        // Se preparan los datos de entrada creando un freelancer con un BlogEntry
        
        List<CurriculumDTO> tmpOraculoCurriculums = new ArrayList<>();
        tmpOraculoCurriculums.add(oraculoCurriculums.get(0));
        FreelancerDTO freelancer = oraculo.get(0);
        freelancer.setCurriculums(tmpOraculoCurriculums);

        // Se realiza llamado a freelancer enviando entidad de blogEntries
        
        Response response = target.path("freelancers").path(freelancer.getId().toString())
                .request().cookie(cookieSessionId)
                .put(Entity.entity(freelancer, MediaType.APPLICATION_JSON));
        
        // Se realiza comparación entre lo enviado y lo recibido del request

        FreelancerDTO freelancerTest = (FreelancerDTO) response.readEntity(FreelancerDTO.class);
        Assert.assertEquals(freelancer.getCurriculums().get(0).getId(), freelancerTest.getCurriculums().get(0).getId());
        Assert.assertEquals(freelancer.getCurriculums().get(0).getProfile(), freelancerTest.getCurriculums().get(0).getProfile());
        Assert.assertEquals(freelancer.getCurriculums().get(0).getIdentification(), freelancerTest.getCurriculums().get(0).getIdentification());
        Assert.assertEquals(freelancer.getCurriculums().get(0).getEmail(), freelancerTest.getCurriculums().get(0).getEmail());
        Assert.assertEquals(freelancer.getCurriculums().get(0).getMobile(), freelancerTest.getCurriculums().get(0).getMobile());
        Assert.assertEquals(Ok, response.getStatus());
    }
 /*
    Este test realiza prueba modificando un Curriculum de un Freelancer.
     */
    @Test
    @InSequence(14)
    public void updateCurriculum() {
        //Se realiza el login de un usuario
        Cookie cookieSessionId = login(username, password);
        // Se obtiene el Freelancer
        FreelancerDTO freelancer = oraculo.get(0);
        //Se obtiene el FreelancerTest
        FreelancerDTO freelancerTestResponse = target.path(freelancerPath)
                .path(oraculo.get(0).getId().toString())
                .request().cookie(cookieSessionId).get(FreelancerDTO.class);
        // Se obtiene la entrada del blog que se creo para Freelancer
        List<CurriculumDTO> curriculumFreelancer = freelancer.getCurriculums();
        // Se modifica la entrada
        curriculumFreelancer.get(0).setProfile("Update_Profile");
        curriculumFreelancer.get(0).setIdentification("Update_Identification");
        curriculumFreelancer.get(0).setEmail("Update_Email");    
        curriculumFreelancer.get(0).setMobile("Update_Mobile");    
        
        // Se agrega al freelancer
        freelancer.setCurriculums(curriculumFreelancer);
        
        // Se envía la modificación para su revisión
        Response response = target.path("freelancers").path(freelancer.getId().toString())
                .request().cookie(cookieSessionId)
                .put(Entity.entity(freelancer, MediaType.APPLICATION_JSON));
        
        // Se compara que la modificación se encuentre correcta
        FreelancerDTO freelancerTest = (FreelancerDTO) response.readEntity(FreelancerDTO.class);
        Assert.assertEquals("Update_Profile", freelancerTest.getCurriculums().get(0).getProfile());
        Assert.assertEquals("Update_Identification", freelancerTest.getCurriculums().get(0).getIdentification());
        Assert.assertEquals("Update_Email", freelancerTest.getCurriculums().get(0).getEmail());
        Assert.assertEquals("Update_Mobile", freelancerTest.getCurriculums().get(0).getMobile());        
        Assert.assertEquals(1, freelancerTest.getCurriculums().size());
        Assert.assertEquals(Ok, response.getStatus());
    }
    /*
    Este test realiza prueba borrando un Curriculum de un Freelancer.
    */
    @Test
    @InSequence(15)
    public void deleteCurriculum() {
        //Se realiza el login de un usuario
        Cookie cookieSessionId = login(username, password);
        // Se obtiene el Freelancer
        FreelancerDTO freelancer = oraculo.get(0);
        // Se le agregan dos elementos EntryBlog
        List<CurriculumDTO> tmpOraculoCurriculum = new ArrayList<>();
        tmpOraculoCurriculum.add(oraculoCurriculums.get(0));
        tmpOraculoCurriculum.add(oraculoCurriculums.get(1));
        freelancer.setCurriculums(tmpOraculoCurriculum);
        // Crea el freelancer con dos objetos EntryBlog
        Response response = target.path("freelancers").path(freelancer.getId().toString())
                .request().cookie(cookieSessionId)
                .put(Entity.entity(freelancer, MediaType.APPLICATION_JSON));
        // Se verifica que el freelancer que se creó tenga dos entidades
        FreelancerDTO freelancerTest = (FreelancerDTO) response.readEntity(FreelancerDTO.class);
        Assert.assertEquals(2, freelancerTest.getCurriculums().size());
        // Se elimina una de las entidades
        tmpOraculoCurriculum.remove(0);
        freelancer.setCurriculums(tmpOraculoCurriculum);
        // Se envia nuevamente la petición 
        response = target.path("freelancers").path(freelancer.getId().toString())
                .request().cookie(cookieSessionId)
                .put(Entity.entity(freelancer, MediaType.APPLICATION_JSON));
        // Se compara que la modificación se encuentre correcta
        freelancerTest = (FreelancerDTO) response.readEntity(FreelancerDTO.class);
        Assert.assertEquals(1, freelancerTest.getCurriculums().size());
        Assert.assertEquals(Ok, response.getStatus());
    }


    @Test
    @InSequence(16)
    public void addWorkExperienceTest() {
        Cookie cookieSessionId = login(username, password);

        // Se preparan los datos de entrada creando un freelancer con un WorkExperience
        
        List<WorkExperienceDTO> tmpOraculoWorkExperience = new ArrayList<>();
        tmpOraculoWorkExperience.add(oraculoWorkExperience.get(0));
        FreelancerDTO freelancer = oraculo.get(0);
        freelancer.setExperience(tmpOraculoWorkExperience);

        // Se realiza llamado a freelancer enviando entidad de WorkExperience
        
        Response response = target.path("freelancers").path(freelancer.getId().toString())
                .request().cookie(cookieSessionId)
                .put(Entity.entity(freelancer, MediaType.APPLICATION_JSON));
        
        // Se realiza comparación entre lo enviado y lo recibido del request

        FreelancerDTO freelancerTest = (FreelancerDTO) response.readEntity(FreelancerDTO.class);
        Assert.assertEquals(freelancer.getExperiences().get(0).getProjectName(), freelancerTest.getExperiences().get(0).getProjectName());
        Assert.assertEquals(freelancer.getExperiences().get(0).getProjectDescription(), freelancerTest.getExperiences().get(0).getProjectDescription());
        Assert.assertEquals(freelancer.getExperiences().get(0).getStartDate(), freelancerTest.getExperiences().get(0).getStartDate());
        Assert.assertEquals(freelancer.getExperiences().get(0).getEndDate(), freelancerTest.getExperiences().get(0).getEndDate());
        Assert.assertEquals(freelancer.getExperiences().get(0).getSponsorCompany(), freelancerTest.getExperiences().get(0).getSponsorCompany());
        Assert.assertEquals(freelancer.getExperiences().get(0).getPrice(), freelancerTest.getExperiences().get(0).getPrice());
        Assert.assertEquals(freelancer.getExperiences().get(0).getRate(), freelancerTest.getExperiences().get(0).getRate());
        Assert.assertEquals(freelancer.getExperiences().get(0).getUrl(), freelancerTest.getExperiences().get(0).getUrl());
        Assert.assertEquals(Ok, response.getStatus());

    }
    
    /*
    Este test realiza prueba modificando un WorkExperience de un Freelancer.
     */
    @Test
    @InSequence(17)
    public void deleteWorkExperience() {
        //Se realiza el login de un usuario
        Cookie cookieSessionId = login(username, password);
        // Se obtiene el Freelancer
        FreelancerDTO freelancer = oraculo.get(0);
        // Se le agregan dos elementos WorkExperience
        List<WorkExperienceDTO> tmpOraculoworkExperience = new ArrayList<>();
        tmpOraculoworkExperience.add(oraculoWorkExperience.get(0));
        tmpOraculoworkExperience.add(oraculoWorkExperience.get(1));
        freelancer.setExperience(tmpOraculoworkExperience);
        // Crea el freelancer con dos objetos WorkExperience
        Response response = target.path("freelancers").path(freelancer.getId().toString())
                .request().cookie(cookieSessionId)
                .put(Entity.entity(freelancer, MediaType.APPLICATION_JSON));
        // Se verifica que el freelancer que se creó tenga dos entidades
        FreelancerDTO freelancerTest = (FreelancerDTO) response.readEntity(FreelancerDTO.class);
        Assert.assertEquals(2, freelancerTest.getExperiences().size());
        // Se elimina una de las entidades
        tmpOraculoworkExperience.remove(0);
        freelancer.setExperience(tmpOraculoworkExperience);
        // Se envia nuevamente la petición 
        response = target.path("freelancers").path(freelancer.getId().toString())
                .request().cookie(cookieSessionId)
                .put(Entity.entity(freelancer, MediaType.APPLICATION_JSON));
        // Se compara que la modificación se encuentre correcta
        freelancerTest = (FreelancerDTO) response.readEntity(FreelancerDTO.class);
        Assert.assertEquals(1, freelancerTest.getExperiences().size());
        Assert.assertEquals(Ok, response.getStatus());
    }
    
    @Test
    @InSequence(18)
    public void deleteFreelancerTest() {
        Cookie cookieSessionId = login(username, password);
        FreelancerDTO freelancer = oraculo.get(0);
        Response response = target.path(freelancerPath).path(freelancer.getId().toString())
                .request().cookie(cookieSessionId).delete();
        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }

}
