package co.edu.uniandes.csw.mpfreelancer.test.logic;

import co.edu.uniandes.csw.mpfreelancer.ejbs.CurriculumLogic;
import co.edu.uniandes.csw.mpfreelancer.api.ICurriculumLogic;
import co.edu.uniandes.csw.mpfreelancer.entities.CurriculumEntity;
import co.edu.uniandes.csw.mpfreelancer.persistence.CurriculumPersistence;
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
public class CurriculumLogicTest {

    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * @generated
     */
    @Inject
    private ICurriculumLogic curriculumLogic;

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
    private List<CurriculumEntity> data = new ArrayList<CurriculumEntity>();

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
                .addPackage(CurriculumEntity.class.getPackage())
                .addPackage(CurriculumLogic.class.getPackage())
                .addPackage(ICurriculumLogic.class.getPackage())
                .addPackage(CurriculumPersistence.class.getPackage())
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
        em.createQuery("delete from CurriculumEntity").executeUpdate();
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
            CurriculumEntity entity = factory.manufacturePojo(CurriculumEntity.class);

            entity.setFreelancer(freelancersData.get(0));

            em.persist(entity);
            data.add(entity);

        }
    }

    /**
     * @generated
     */
    @Test
    public void createCurriculumTest() {
        
        CurriculumEntity entity = factory.manufacturePojo(CurriculumEntity.class);
        CurriculumEntity result = curriculumLogic.createCurriculum(entity);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), entity.getId());
        Assert.assertEquals(result.getName(), entity.getName());
     }

    /**
     * @generated
     */
    @Test
    public void getCurriculumsTest() {
        List<CurriculumEntity> list = curriculumLogic.getCurriculums();
        Assert.assertEquals(data.size(), list.size());
        for (CurriculumEntity entity : list) {
            boolean found = false;
            for (CurriculumEntity storedEntity : data) {
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
    public void getCurriculumTest() {
        CurriculumEntity entity = data.get(0);
        CurriculumEntity resultEntity = curriculumLogic.getCurriculum(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
    }

    /**
     * @generated
     */
    @Test
    public void deleteCurriculumTest() {
        CurriculumEntity entity = data.get(1);
        curriculumLogic.deleteCurriculum(entity.getId());
        CurriculumEntity deleted = em.find(CurriculumEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateCurriculumTest() {
        CurriculumEntity entity = data.get(0);
        CurriculumEntity pojoEntity = factory.manufacturePojo(CurriculumEntity.class);

        pojoEntity.setId(entity.getId());

        curriculumLogic.updateCurriculum(pojoEntity);

        CurriculumEntity resp = em.find(CurriculumEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
    }
}