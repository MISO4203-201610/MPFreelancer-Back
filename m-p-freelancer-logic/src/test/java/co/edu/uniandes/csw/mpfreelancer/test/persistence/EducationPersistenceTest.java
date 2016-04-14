package co.edu.uniandes.csw.mpfreelancer.test.persistence;

import co.edu.uniandes.csw.mpfreelancer.entities.EducationEntity;
import co.edu.uniandes.csw.mpfreelancer.persistence.EducationPersistence;
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
public class EducationPersistenceTest {

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EducationEntity.class.getPackage())
                .addPackage(EducationPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private EducationPersistence educationPersistence;

    /**
     * @generated
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * @generated
     */
    @Inject
    UserTransaction utx;

    /**
     * @generated
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
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
        em.createQuery("delete from EducationEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<EducationEntity> data = new ArrayList<EducationEntity>();

    /**
     * @generated
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            EducationEntity entity = factory.manufacturePojo(EducationEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * @generated
     */
    @Test
    public void createEducationTest() {
        PodamFactory factory = new PodamFactoryImpl();
        EducationEntity newEntity = factory.manufacturePojo(EducationEntity.class);
        EducationEntity result = educationPersistence.create(newEntity);

        Assert.assertNotNull(result);

        EducationEntity entity = em.find(EducationEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getStartDate(), entity.getStartDate());
        Assert.assertEquals(newEntity.getEndDate(), entity.getEndDate());
        Assert.assertEquals(newEntity.getInstitution(), entity.getInstitution());
        Assert.assertEquals(newEntity.getTitle(), entity.getTitle());
        Assert.assertEquals(newEntity.getDescription(), entity.getDescription());
    }

    /**
     * @generated
     */
    @Test
    public void getEducationsTest() {
        List<EducationEntity> list = educationPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (EducationEntity ent : list) {
            boolean found = false;
            for (EducationEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
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
        EducationEntity newEntity = educationPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getStartDate(), newEntity.getStartDate());
        Assert.assertEquals(entity.getEndDate(), newEntity.getEndDate());
        Assert.assertEquals(entity.getInstitution(), newEntity.getInstitution());
        Assert.assertEquals(entity.getTitle(), newEntity.getTitle());
        Assert.assertEquals(entity.getDescription(), newEntity.getDescription());
    }

    /**
     * @generated
     */
    @Test
    public void deleteEducationTest() {
        EducationEntity entity = data.get(0);
        educationPersistence.delete(entity.getId());
        EducationEntity deleted = em.find(EducationEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateEducationTest() {
        EducationEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        EducationEntity newEntity = factory.manufacturePojo(EducationEntity.class);

        newEntity.setId(entity.getId());

        educationPersistence.update(newEntity);

        EducationEntity resp = em.find(EducationEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getStartDate(), resp.getStartDate());
        Assert.assertEquals(newEntity.getEndDate(), resp.getEndDate());
        Assert.assertEquals(newEntity.getInstitution(), resp.getInstitution());
        Assert.assertEquals(newEntity.getTitle(), resp.getTitle());
        Assert.assertEquals(newEntity.getDescription(), resp.getDescription());
    }
}