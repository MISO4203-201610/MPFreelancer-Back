package co.edu.uniandes.csw.mpfreelancer.test.logic;

import co.edu.uniandes.csw.mpfreelancer.ejbs.EducationLogic;
import co.edu.uniandes.csw.mpfreelancer.api.IEducationLogic;
import co.edu.uniandes.csw.mpfreelancer.entities.EducationEntity;
import co.edu.uniandes.csw.mpfreelancer.persistence.EducationPersistence;
import co.edu.uniandes.csw.mpfreelancer.entities.FreelancerEntity;
import co.edu.uniandes.csw.mpfreelancer.entities.ProjectEntity;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class EducationLogicTest {

    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * @generated
     */
    @Inject
    private IEducationLogic educationLogic;

    /**
     * @generated
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * @generated
     */
    @Inject
    private UserTransaction utx;

    /**
     * @generated
     */
    private List<EducationEntity> data = new ArrayList<EducationEntity>();

    /**
     * @generated
     */
    private List<FreelancerEntity> freelancersData = new ArrayList<>();

    /**
     * @generated
     */
    private List<ProjectEntity> projectsData = new ArrayList<>();

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EducationEntity.class.getPackage())
                .addPackage(EducationLogic.class.getPackage())
                .addPackage(IEducationLogic.class.getPackage())
                .addPackage(EducationPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * @generated
     */
    private void clearData() {
        em.createQuery("delete from ProjectEntity").executeUpdate();
        em.createQuery("delete from EducationEntity").executeUpdate();
        em.createQuery("delete from FreelancerEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private void insertData() {

        for (int i = 0; i < 3; i++) {
            FreelancerEntity freelancers = factory.manufacturePojo(FreelancerEntity.class);
            em.persist(freelancers);
            freelancersData.add(freelancers);
        }
        
        for (int i = 0; i < 3; i++) {
            ProjectEntity projects = factory.manufacturePojo(ProjectEntity.class);
            em.persist(projects);
            projectsData.add(projects);
        }
        
        em.createQuery("delete from EducationEntity").executeUpdate();
        for (int i = 0; i < 3; i++) {
            EducationEntity entity = factory.manufacturePojo(EducationEntity.class);

            entity.setFreelancer(freelancersData.get(0));

            em.persist(entity);
            data.add(entity);

        }
    }

    /**
     * @generated
     */
    @Test
    public void createEducationTest() {
        
        EducationEntity entity = factory.manufacturePojo(EducationEntity.class);
        EducationEntity result = educationLogic.createTitles(entity);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), entity.getId());
        Assert.assertEquals(result.getName(), entity.getName());
        Assert.assertEquals(result.getStartDate(), entity.getStartDate());
        Assert.assertEquals(result.getEndDate(), entity.getEndDate());
        Assert.assertEquals(result.getInstitution(), entity.getInstitution());
        Assert.assertEquals(result.getTitle(), entity.getTitle());
        Assert.assertEquals(result.getDescription(), entity.getDescription());
     }

    /**
     * @generated
     */
    @Test
    public void getEducationsTest() {
        List<EducationEntity> list = educationLogic.getTitles();
        Assert.assertEquals(data.size(), list.size());
        for (EducationEntity entity : list) {
            boolean found = false;
            for (EducationEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * @generated
     */
    @Test
    public void getEducationTest() {
        EducationEntity entity = data.get(0);
        EducationEntity resultEntity = educationLogic.getTitles(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getStartDate(), resultEntity.getStartDate());
        Assert.assertEquals(entity.getEndDate(), resultEntity.getEndDate());
        Assert.assertEquals(entity.getInstitution(), resultEntity.getInstitution());
        Assert.assertEquals(entity.getTitle(), resultEntity.getTitle());
        Assert.assertEquals(entity.getDescription(), resultEntity.getDescription());
    
    }

    /**
     * @generated
     */
    @Test
    public void deleteEducationTest() {
        EducationEntity entity = data.get(1);
        educationLogic.deleteTitles(entity.getId());
        EducationEntity deleted = em.find(EducationEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateEducationTest() {
        EducationEntity entity = data.get(0);
        EducationEntity pojoEntity = factory.manufacturePojo(EducationEntity.class);

        pojoEntity.setId(entity.getId());

        educationLogic.updateTitles(pojoEntity);

        EducationEntity resp = em.find(EducationEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getStartDate(), resp.getStartDate());
        Assert.assertEquals(pojoEntity.getEndDate(), resp.getEndDate());
        Assert.assertEquals(pojoEntity.getInstitution(), resp.getInstitution());
        Assert.assertEquals(pojoEntity.getTitle(), resp.getTitle());
        Assert.assertEquals(pojoEntity.getDescription(), resp.getDescription());
    }
}