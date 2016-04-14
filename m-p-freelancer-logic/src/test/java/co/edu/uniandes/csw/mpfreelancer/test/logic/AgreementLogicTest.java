package co.edu.uniandes.csw.mpfreelancer.test.logic;

import co.edu.uniandes.csw.mpfreelancer.ejbs.AgreementLogic;
import co.edu.uniandes.csw.mpfreelancer.api.IAgreementLogic;
import co.edu.uniandes.csw.mpfreelancer.entities.AgreementEntity;
import co.edu.uniandes.csw.mpfreelancer.persistence.AgreementPersistence;
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
public class AgreementLogicTest {

    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * @generated
     */
    @Inject
    private IAgreementLogic agreementLogic;

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
    private List<AgreementEntity> data = new ArrayList<AgreementEntity>();

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
                .addPackage(AgreementEntity.class.getPackage())
                .addPackage(AgreementLogic.class.getPackage())
                .addPackage(IAgreementLogic.class.getPackage())
                .addPackage(AgreementPersistence.class.getPackage())
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
        em.createQuery("delete from AgreementEntity").executeUpdate();
        em.createQuery("delete from FreelancerEntity").executeUpdate();
        em.createQuery("delete from ProjectEntity").executeUpdate();
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
        
        em.createQuery("delete from AgreementEntity").executeUpdate();
        for (int i = 0; i < 3; i++) {
            AgreementEntity entity = factory.manufacturePojo(AgreementEntity.class);

            entity.setFreelancer(freelancersData.get(i));
            entity.setProject(projectsData.get(i));

            em.persist(entity);
            data.add(entity);

        }
    }

    /**
     * @generated
     */
    @Test
    public void createAgreementTest() {
        
        AgreementEntity entity = factory.manufacturePojo(AgreementEntity.class);
        AgreementEntity result = agreementLogic.createAgreement(entity);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), entity.getId());
        Assert.assertEquals(result.getName(), entity.getName());
        Assert.assertEquals(result.getPrice(), entity.getPrice());
        Assert.assertEquals(result.getRate(), entity.getRate());
        Assert.assertEquals(result.getSelected(), entity.getSelected());
        Assert.assertEquals(result.getStatus(), entity.getStatus());
     }

    /**
     * @generated
     */
    @Test
    public void getAgreementsTest() {
        List<AgreementEntity> list = agreementLogic.getAgreements();
        Assert.assertEquals(data.size(), list.size());
        for (AgreementEntity entity : list) {
            boolean found = false;
            for (AgreementEntity storedEntity : data) {
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
    public void getAgreementTest() {
        AgreementEntity entity = data.get(0);
        AgreementEntity resultEntity = agreementLogic.getAgreement(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
    }

    /**
     * @generated
     */
    @Test
    public void deleteAgreementTest() {
        AgreementEntity entity = data.get(1);
        agreementLogic.deleteAgreement(entity.getId());
        AgreementEntity deleted = em.find(AgreementEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateAgreementTest() {
        AgreementEntity entity = data.get(0);
        AgreementEntity pojoEntity = factory.manufacturePojo(AgreementEntity.class);

        pojoEntity.setId(entity.getId());

        agreementLogic.updateAgreement(pojoEntity);

        AgreementEntity resp = em.find(AgreementEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getPrice(), resp.getPrice());
                Assert.assertEquals(pojoEntity.getPrice(), resp.getPrice());
    }
}