package co.edu.uniandes.csw.mpfreelancer.tests;

import co.edu.uniandes.csw.auth.model.UserDTO;
import co.edu.uniandes.csw.auth.security.JWT;
import co.edu.uniandes.csw.mpfreelancer.dtos.AgreementDTO;
import co.edu.uniandes.csw.mpfreelancer.dtos.ProjectDTO;
import co.edu.uniandes.csw.mpfreelancer.dtos.FreelancerDTO;
import co.edu.uniandes.csw.mpfreelancer.dtos.ProjectSponsorDTO;
import co.edu.uniandes.csw.mpfreelancer.services.AgreementService;
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
public class AgreementTest {

    private final int Ok = Status.OK.getStatusCode();
    private final int Created = Status.CREATED.getStatusCode();
    private final int OkWithoutContent = Status.NO_CONTENT.getStatusCode();
    private final String agreementPath = "agreements";
    private final static List<AgreementDTO> oraculo = new ArrayList<>();
    private final String projectsPath = "projects";
    private final static List<ProjectDTO> oraculoProjects = new ArrayList<>();
    private final String freelancersPath = "freelancers";
    private final static List<FreelancerDTO> oraculoFreelancers = new ArrayList<>();
    private WebTarget target;
    private final String apiPath = "api";
    private final String username = System.getenv("USERNAME_USER");
    private final String password = System.getenv("PASSWORD_USER");
    private final Integer invitado = 1;
    private final Integer aceptado = 2;
    private final Integer rechazado = 3;
    private final Integer seleccionado = 4;
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
                .addPackage(AgreementService.class.getPackage())
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
            
            AgreementDTO agreement = factory.manufacturePojo(AgreementDTO.class);
            agreement.setId(i + 1L);

            oraculo.add(agreement);

            ProjectDTO projects = factory.manufacturePojo(ProjectDTO.class);
            projects.setId(i + 1L);
            oraculoProjects.add(projects);
     
            FreelancerDTO freelancers = factory.manufacturePojo(FreelancerDTO.class);
            freelancers.setId(i + 1L);
            oraculoFreelancers.add(freelancers);
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
    public void createAgreementTest() throws IOException {

        ProjectDTO project = oraculoProjects.get(0);
        FreelancerDTO freelancer = oraculoFreelancers.get(0);
        AgreementDTO agreement = oraculo.get(0);
        agreement.setFreelancer(freelancer);
        agreement.setProject(project);
   
        Cookie cookieSessionId = login(username, password);
        
        PodamFactory factory = new PodamFactoryImpl();
        ProjectSponsorDTO projectSponsor = factory.manufacturePojo(ProjectSponsorDTO.class);
        projectSponsor.setId(1L);
        
        Response response = target.path("projectSponsors")
                .request().cookie(cookieSessionId)
                .post(Entity.entity(projectSponsor, MediaType.APPLICATION_JSON));
                
                 response = target.path("projects")
                .request().cookie(cookieSessionId)
                .post(Entity.entity(project, MediaType.APPLICATION_JSON));
                
                response = target.path("freelancers")
                .request().cookie(cookieSessionId)
                .post(Entity.entity(freelancer, MediaType.APPLICATION_JSON));
        
                response = target.path(agreementPath)
                .request().cookie(cookieSessionId)
                .post(Entity.entity(agreement, MediaType.APPLICATION_JSON));
                
        AgreementDTO  agreementTest = (AgreementDTO) response.readEntity(AgreementDTO.class);
        Assert.assertEquals(agreement.getId(), agreementTest.getId());
        Assert.assertEquals(agreement.getName(), agreementTest.getName());
        Assert.assertEquals(agreement.getPrice(), agreementTest.getPrice());
        Assert.assertEquals(agreement.getRate(), agreementTest.getRate());
        Assert.assertEquals(agreement.getSelected(), agreementTest.getSelected());
        Assert.assertEquals(agreement.getStatus(), agreementTest.getStatus());
        Assert.assertEquals(agreement.getFreelancer().getId(), agreementTest.getFreelancer().getId());
        Assert.assertEquals(agreement.getProject().getId(), agreementTest.getProject().getId());
        Assert.assertEquals(Created, response.getStatus());
        
                AgreementDTO agreement2 = oraculo.get(1);
                ProjectDTO project2 = oraculoProjects.get(1);
                agreement2.setFreelancer(freelancer);
                agreement2.setProject(project2);

                response = target.path("projects")
                .request().cookie(cookieSessionId)
                .post(Entity.entity(project2, MediaType.APPLICATION_JSON));
                
                response = target.path(agreementPath)
                .request().cookie(cookieSessionId)
                .post(Entity.entity(agreement2, MediaType.APPLICATION_JSON));
    }

    @Test
    @InSequence(2)
    public void getAgreementById() {
        Cookie cookieSessionId = login(username, password);
        AgreementDTO agreementTest = target.path(agreementPath)
                .path(oraculo.get(0).getId().toString())
                .request().cookie(cookieSessionId).get(AgreementDTO.class);
        Assert.assertEquals(agreementTest.getId(), oraculo.get(0).getId());
        Assert.assertEquals(agreementTest.getName(), oraculo.get(0).getName());
        Assert.assertEquals(agreementTest.getPrice(), oraculo.get(0).getPrice());
        Assert.assertEquals(agreementTest.getRate(), oraculo.get(0).getRate());
        Assert.assertEquals(agreementTest.getSelected(), oraculo.get(0).getSelected());
        Assert.assertEquals(agreementTest.getStatus(), oraculo.get(0).getStatus());
    }

    @Test
    @InSequence(3)
    public void listAgreementTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        Response response = target.path(agreementPath)
                .request().cookie(cookieSessionId).get();
        String listAgreement = response.readEntity(String.class);
        List<AgreementDTO> listAgreementTest = new ObjectMapper().readValue(listAgreement, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(2, listAgreementTest.size());
    }

    @Test
    @InSequence(4)
    public void updateAgreementTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        AgreementDTO agreement = oraculo.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        AgreementDTO agreementChanged = factory.manufacturePojo(AgreementDTO.class);
        agreement.setName(agreementChanged.getName());
        agreement.setPrice(agreementChanged.getPrice());
        agreement.setRate(agreementChanged.getRate());
        agreement.setSelected(agreementChanged.getSelected());
        agreement.setStatus(agreementChanged.getStatus());
        Response response = target.path(agreementPath).path(agreement.getId().toString())
                .request().cookie(cookieSessionId).put(Entity.entity(agreement, MediaType.APPLICATION_JSON));
        AgreementDTO agreementTest = (AgreementDTO) response.readEntity(AgreementDTO.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(agreement.getName(), agreementTest.getName());
        Assert.assertEquals(agreement.getPrice(), agreementTest.getPrice());
        Assert.assertEquals(agreement.getRate(), agreementTest.getRate
        ());
    }

    @Test
    @InSequence(5)
    public void deleteAgreementTest() {
        Cookie cookieSessionId = login(username, password);
        AgreementDTO agreement = oraculo.get(0);
        Response response = target.path(agreementPath).path(agreement.getId().toString())
                .request().cookie(cookieSessionId).delete();
        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }

    @Test
    @InSequence(6)
    public void getAgreementsFreelancerTest() throws IOException {

        FreelancerDTO freelancer = oraculoFreelancers.get(1);
   
        Cookie cookieSessionId = login(username, password);
        
        PodamFactory factory = new PodamFactoryImpl();
        ProjectSponsorDTO projectSponsor = factory.manufacturePojo(ProjectSponsorDTO.class);
        projectSponsor.setId(1L);
    
        Response response = target.path("projectSponsors")
                .request().cookie(cookieSessionId)
                .post(Entity.entity(projectSponsor, MediaType.APPLICATION_JSON));
        
                 response = target.path("freelancers")
                .request().cookie(cookieSessionId)
                .post(Entity.entity(freelancer, MediaType.APPLICATION_JSON));
        
    // Creacion Agreement_1 para Freelancer Oraculo(1)

        ProjectDTO project = oraculoProjects.get(1);
        AgreementDTO agreement1 = oraculo.get(1);
        agreement1.setFreelancer(freelancer);
        agreement1.setProject(project);
                
                 response = target.path("projects")
                .request().cookie(cookieSessionId)
                .post(Entity.entity(project, MediaType.APPLICATION_JSON));
                
               
                 response = target.path(agreementPath)
                .request().cookie(cookieSessionId)
                .post(Entity.entity(agreement1, MediaType.APPLICATION_JSON));
    
        //  Valida el metodo el freelancer existe y tiene un agreement           
                 response = target.path(agreementPath).path(freelancer.getId().toString()).path("agreementsFreelancer")
                .request().cookie(cookieSessionId)
                .get();
                
        String listAgreement = response.readEntity(String.class);
        List<AgreementDTO> listAgreementTest = new ObjectMapper().readValue(listAgreement, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(1, listAgreementTest.size());
        
        //  Valida el metodo para freelancer (4) No Tiene Agreements asociados
        
                freelancer = oraculoFreelancers.get(4);
                response = target.path(agreementPath).path(freelancer.getId().toString()).path("agreementsFreelancer")
                .request().cookie(cookieSessionId)
                .get();
                
                listAgreement = response.readEntity(String.class);
                listAgreementTest = new ObjectMapper().readValue(listAgreement, List.class);
                Assert.assertEquals(Ok, response.getStatus());
                 Assert.assertEquals(0, listAgreementTest.size());
                 
        // Creacion Agreement_2 para Freelancer (1)

                     freelancer = oraculoFreelancers.get(1);
                     project = oraculoProjects.get(2);
        AgreementDTO agreement2 = oraculo.get(2);
                     agreement2.setFreelancer(freelancer);
                     agreement2.setProject(project);
                
                    response = target.path("projects")
                    .request().cookie(cookieSessionId)
                    .post(Entity.entity(project, MediaType.APPLICATION_JSON));
                
                    response = target.path(agreementPath)
                    .request().cookie(cookieSessionId)
                    .post(Entity.entity(agreement2, MediaType.APPLICATION_JSON));         
                 
       //  Valida el metodo el freelancer (1) existe y tiene dos agreements            
                 response = target.path(agreementPath).path(freelancer.getId().toString()).path("agreementsFreelancer")
                .request().cookie(cookieSessionId)
                .get();
                 
                listAgreement = response.readEntity(String.class);
                listAgreementTest = new ObjectMapper().readValue(listAgreement, List.class);
                Assert.assertEquals(Ok, response.getStatus());
                Assert.assertEquals(2, listAgreementTest.size());
    }
    
    @Test
    @InSequence(7)
    public void getAgreementsProjectTest() throws IOException {

        ProjectDTO project = oraculoProjects.get(2);
   
        Cookie cookieSessionId = login(username, password);
        
        //  Valida el metodo el project Oraculo (1) existe y tiene un agreement            
        
        Response response = target.path(agreementPath).path(project.getId().toString()).path("agreementsProject")
                .request().cookie(cookieSessionId)
                .get();
                 
        String agreementList = response.readEntity(String.class);
        
        List<AgreementDTO> agreementListTest = new ObjectMapper().readValue(agreementList, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(1, agreementListTest.size());
        
        //  Valida el metodo el project oraculo (3) No Tiene Agreements asociados
        
                project = oraculoProjects.get(3);
                response = target.path(agreementPath).path(project.getId().toString()).path("agreementsFreelancer")
                .request().cookie(cookieSessionId)
                .get();
                
                agreementList = response.readEntity(String.class);
        
                agreementListTest = new ObjectMapper().readValue(agreementList, List.class);
                Assert.assertEquals(Ok, response.getStatus());
                Assert.assertEquals(0, agreementListTest.size());        
    }
    
    @Test
    @InSequence(8)
    public void AgreementsInvitedTest() throws IOException {

        //  Se crea un Proyecto y un Frealance  
        //  Se crea el Agreement
                
        ProjectDTO project = oraculoProjects.get(3);
        FreelancerDTO freelancer = oraculoFreelancers.get(4);

        Cookie cookieSessionId = login(username, password);
        
        Response response = target.path("projects")
                .request().cookie(cookieSessionId)
                .post(Entity.entity(project, MediaType.APPLICATION_JSON));
        
        ProjectDTO  projectTest = (ProjectDTO) response.readEntity(ProjectDTO.class);
        
             pId = projectTest.getId();
        
                 response = target.path("freelancers")
                .request().cookie(cookieSessionId)
                .post(Entity.entity(freelancer, MediaType.APPLICATION_JSON));
        
        FreelancerDTO  freelancerTest = (FreelancerDTO) response.readEntity(FreelancerDTO.class);
                 
             fId = freelancerTest.getId();
        
        //  Valida el metodo el project Oraculo (1) existe y tiene un agreement            
        
                 response = target.path(agreementPath).path(fId.toString()).path("agreementsInvited")
                .path(pId.toString())
                .request().cookie(cookieSessionId)
                .post(Entity.entity(null, MediaType.APPLICATION_JSON));
        
        AgreementDTO  agreementTest = (AgreementDTO) response.readEntity(AgreementDTO.class);

            aId=agreementTest.getId();
        
        Assert.assertTrue(200==response.getStatus() || 201==response.getStatus());
        Assert.assertEquals(invitado, agreementTest.getStatus());
                
    }
    
    @Test
    @InSequence(9)
    public void getAgreementsStatus1Test() throws IOException {
        
        Cookie cookieSessionId = login(username, password);
        
        //  Valida el metodo el freelancer fId existe y tiene un agreement en status 1            
        
        Response response = target.path(agreementPath).path(fId.toString()).path("agreementsStatus1")
                .request().cookie(cookieSessionId)
                .get();
                 
        String agreementList = response.readEntity(String.class);
        
        List<AgreementDTO> agreementListTest = new ObjectMapper().readValue(agreementList, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(1, agreementListTest.size());
        
        //  Valida el metodo: el freelancer oraculo (5) No Tiene Agreements en status 1
        
        FreelancerDTO freelancer = oraculoFreelancers.get(5);
                response = target.path(agreementPath).path(freelancer.getId().toString()).path("agreementsStatus1")
                .request().cookie(cookieSessionId)
                .get();
                
                agreementList = response.readEntity(String.class);
        
                agreementListTest = new ObjectMapper().readValue(agreementList, List.class);
                Assert.assertEquals(Ok, response.getStatus());
                Assert.assertEquals(0, agreementListTest.size());        
    }
  
    @Test
    @InSequence(10)
    public void AgreementsAceptedTest() throws IOException {

        //  Valor de cotizacion 
        
        Integer priceTest=12000;
        AgreementDTO agreement= new AgreementDTO();

        Cookie cookieSessionId = login(username, password);
        
 //  Valida el metodo el project Oraculo (1) existe y tiene un agreement            
        
        Response response = target.path(agreementPath).path(aId.toString()).path(priceTest.toString()).path("agreementsAcept")
                .request().cookie(cookieSessionId)
                .put(Entity.entity(agreement, MediaType.APPLICATION_JSON));  
               
        AgreementDTO  agreementTest = (AgreementDTO) response.readEntity(AgreementDTO.class);
               
        Assert.assertTrue(200==response.getStatus() || 201==response.getStatus());
        Assert.assertEquals(aceptado, agreementTest.getStatus());
        Assert.assertEquals(priceTest, agreementTest.getPrice());                
    }
    
    @Test
    @InSequence(11)
    public void getAgreementsProjectAcceptedTest() throws IOException {
        
        Cookie cookieSessionId = login(username, password);
        
        //  Valida el metodo el freelancer fId existe y tiene un agreement en status 1            
        
        Response response = target.path(agreementPath).path(pId.toString()).path("agreementsProjectAcepted")
                .request().cookie(cookieSessionId)
                .get();
                 
        String agreementList = response.readEntity(String.class);
        
        List<AgreementDTO> agreementListTest = new ObjectMapper().readValue(agreementList, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(1, agreementListTest.size());
        
        //  Valida el metodo: el project oraculo (5) No Tiene Agreements en status 1
        
        ProjectDTO project = oraculoProjects.get(5);
                response = target.path(agreementPath).path(project.getId().toString()).path("agreementsProjectAcepted")
                .request().cookie(cookieSessionId)
                .get();
                
                agreementList = response.readEntity(String.class);
        
                agreementListTest = new ObjectMapper().readValue(agreementList, List.class);
                Assert.assertEquals(Ok, response.getStatus());
                Assert.assertEquals(0, agreementListTest.size());        
    }
    
    @Test
    @InSequence(12)
    public void getAgreementsStatus2Test() throws IOException {
        
        Cookie cookieSessionId = login(username, password);
        
        //  Valida el metodo el freelancer fId existe y tiene un agreement en status 2            
        
        Response response = target.path(agreementPath).path(fId.toString()).path("agreementsStatus2")
                .request().cookie(cookieSessionId)
                .get();
                 
        String agreementList = response.readEntity(String.class);
        
        List<AgreementDTO> agreementListTest = new ObjectMapper().readValue(agreementList, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(1, agreementListTest.size());
        
        //  Valida el metodo: el freelancer oraculo (5) No Tiene Agreements en status 2
        
        FreelancerDTO freelancer = oraculoFreelancers.get(5);
                response = target.path(agreementPath).path(freelancer.getId().toString()).path("agreementsStatus2")
                .request().cookie(cookieSessionId)
                .get();
                
                agreementList = response.readEntity(String.class);
        
                agreementListTest = new ObjectMapper().readValue(agreementList, List.class);
                Assert.assertEquals(Ok, response.getStatus());
                Assert.assertEquals(0, agreementListTest.size());        
    }
    
    @Test
    @InSequence(13)
    public void AgreementsRejectedTest() throws IOException {

        //  Actualiza a Rechazado 

        AgreementDTO agreement= new AgreementDTO();

        Cookie cookieSessionId = login(username, password);
        
 //  Actualiza el Agreement            
        
        Response response = target.path(agreementPath).path(aId.toString()).path("agreementsReject")
                .request().cookie(cookieSessionId)
                .put(Entity.entity(agreement, MediaType.APPLICATION_JSON));  
               
        AgreementDTO  agreementTest = (AgreementDTO) response.readEntity(AgreementDTO.class);
               
        Assert.assertTrue(200==response.getStatus() || 201==response.getStatus());
        Assert.assertEquals(rechazado, agreementTest.getStatus());                
    }
   
    @Test
    @InSequence(14)
    public void getAgreementsStatus3Test() throws IOException {
        
        Cookie cookieSessionId = login(username, password);
        
        //  Valida el metodo el freelancer fId existe y tiene un agreement en status 2            
        
        Response response = target.path(agreementPath).path(fId.toString()).path("agreementsStatus3")
                .request().cookie(cookieSessionId)
                .get();
                 
        String agreementList = response.readEntity(String.class);
        
        List<AgreementDTO> agreementListTest = new ObjectMapper().readValue(agreementList, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(1, agreementListTest.size());
        
        //  Valida el metodo: el freelancer oraculo (5) No Tiene Agreements en status 2
        
        FreelancerDTO freelancer = oraculoFreelancers.get(5);
                response = target.path(agreementPath).path(freelancer.getId().toString()).path("agreementsStatus3")
                .request().cookie(cookieSessionId)
                .get();
                
                agreementList = response.readEntity(String.class);
        
                agreementListTest = new ObjectMapper().readValue(agreementList, List.class);
                Assert.assertEquals(Ok, response.getStatus());
                Assert.assertEquals(0, agreementListTest.size());        
    }
 
    @Test
    @InSequence(15)
    public void AgreementsSelectedTest() throws IOException {

        //  Actualiza a Rechazado 

        AgreementDTO agreement= new AgreementDTO();

        Cookie cookieSessionId = login(username, password);
        
 //  Actualiza el Agreement            
        
        Response response = target.path(agreementPath).path(aId.toString()).path("agreementsSelected")
                .request().cookie(cookieSessionId)
                .put(Entity.entity(agreement, MediaType.APPLICATION_JSON));  
               
        AgreementDTO  agreementTest = (AgreementDTO) response.readEntity(AgreementDTO.class);
               
        Assert.assertTrue(200==response.getStatus() || 201==response.getStatus());
        Assert.assertEquals(seleccionado, agreementTest.getStatus());                
    }
   
    @Test
    @InSequence(16)
    public void getAgreementsStatus4Test() throws IOException {
        
        Cookie cookieSessionId = login(username, password);
        
        //  Valida el metodo el freelancer fId existe y tiene un agreement en status 2            
        
        Response response = target.path(agreementPath).path(fId.toString()).path("agreementsStatus4")
                .request().cookie(cookieSessionId)
                .get();
                 
        String agreementList = response.readEntity(String.class);
        
        List<AgreementDTO> agreementListTest = new ObjectMapper().readValue(agreementList, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(1, agreementListTest.size());
        
        //  Valida el metodo: el freelancer oraculo (5) No Tiene Agreements en status 2
        
        FreelancerDTO freelancer = oraculoFreelancers.get(5);
                response = target.path(agreementPath).path(freelancer.getId().toString()).path("agreementsStatus4")
                .request().cookie(cookieSessionId)
                .get();
                
                agreementList = response.readEntity(String.class);
        
                agreementListTest = new ObjectMapper().readValue(agreementList, List.class);
                Assert.assertEquals(Ok, response.getStatus());
                Assert.assertEquals(0, agreementListTest.size());        
    }

}