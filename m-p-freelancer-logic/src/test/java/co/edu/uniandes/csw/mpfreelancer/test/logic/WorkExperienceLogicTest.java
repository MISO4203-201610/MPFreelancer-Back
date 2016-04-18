package co.edu.uniandes.csw.mpfreelancer.test.logic;

import co.edu.uniandes.csw.mpfreelancer.ejbs.WorkExperienceLogic;
import co.edu.uniandes.csw.mpfreelancer.api.IWorkExperienceLogic;
import co.edu.uniandes.csw.mpfreelancer.entities.WorkExperienceEntity;
import co.edu.uniandes.csw.mpfreelancer.persistence.WorkExperiencePersistence;
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
 *
 * @author mf.calderon
 */
@RunWith(Arquillian.class)
public class WorkExperienceLogicTest {

    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * @generated
     */
    @Inject
    private IWorkExperienceLogic workExperienceLogic;

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
    private List<WorkExperienceEntity> data = new ArrayList<WorkExperienceEntity>();

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
                .addPackage(WorkExperienceEntity.class.getPackage())
                .addPackage(WorkExperienceLogic.class.getPackage())
                .addPackage(IWorkExperienceLogic.class.getPackage())
                .addPackage(WorkExperiencePersistence.class.getPackage())
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
        em.createQuery("delete from WorkExperienceEntity").executeUpdate();
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
        
        em.createQuery("delete from CurriculumEntity").executeUpdate();
        for (int i = 0; i < 3; i++) {
            WorkExperienceEntity entity = factory.manufacturePojo(WorkExperienceEntity.class);

            entity.setFreelancer(freelancersData.get(0));

            em.persist(entity);
            data.add(entity);

        }
    }

    /**
     * @generated
     */
    @Test
    public void createWorkExperienceTest() {
        
        WorkExperienceEntity entity = factory.manufacturePojo(WorkExperienceEntity.class);
        WorkExperienceEntity result = workExperienceLogic.createExperience(entity);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), entity.getId());
        Assert.assertEquals(result.getName(), entity.getName());
     }

    /**
     * @generated
     */
    @Test
    public void getCurriculumsTest() {
        List<WorkExperienceEntity> list = workExperienceLogic.getExperience();
        Assert.assertEquals(data.size(), list.size());
        for (WorkExperienceEntity entity : list) {
            boolean found = false;
            for (WorkExperienceEntity storedEntity : data) {
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
    public void getWorkExperienceTest() {
        WorkExperienceEntity entity = data.get(0);
        WorkExperienceEntity resultEntity = workExperienceLogic.getExperience(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
    }

    /**
     * @generated
     */
    @Test
    public void deleteWorkExperienceTest() {
        WorkExperienceEntity entity = data.get(1);
        workExperienceLogic.deleteExperience(entity.getId());
        WorkExperienceEntity deleted = em.find(WorkExperienceEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateWorkExperienceTest() {
        WorkExperienceEntity entity = data.get(0);
        WorkExperienceEntity pojoEntity = factory.manufacturePojo(WorkExperienceEntity.class);

        pojoEntity.setId(entity.getId());

        workExperienceLogic.updateExperience(pojoEntity);

        WorkExperienceEntity resp = em.find(WorkExperienceEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
    }
}
