package co.edu.uniandes.csw.mpfreelancer.test.persistence;

import co.edu.uniandes.csw.mpfreelancer.entities.CurriculumEntity;
import co.edu.uniandes.csw.mpfreelancer.persistence.CurriculumPersistence;
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
public class CurriculumPersistenceTest {

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CurriculumEntity.class.getPackage())
                .addPackage(CurriculumPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private CurriculumPersistence curriculumPersistence;

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
        em.createQuery("delete from CurriculumEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<CurriculumEntity> data = new ArrayList<CurriculumEntity>();

    /**
     * @generated
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            CurriculumEntity entity = factory.manufacturePojo(CurriculumEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * @generated
     */
    @Test
    public void createCurriculumTest() {
        PodamFactory factory = new PodamFactoryImpl();
        CurriculumEntity newEntity = factory.manufacturePojo(CurriculumEntity.class);
        CurriculumEntity result = curriculumPersistence.create(newEntity);

        Assert.assertNotNull(result);

        CurriculumEntity entity = em.find(CurriculumEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getProfile(), entity.getProfile());
        Assert.assertEquals(newEntity.getIdentification(), entity.getIdentification());
    }

    /**
     * @generated
     */
    @Test
    public void getCurriculumsTest() {
        List<CurriculumEntity> list = curriculumPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (CurriculumEntity ent : list) {
            boolean found = false;
            for (CurriculumEntity entity : data) {
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
    public void getCurriculumTest() {
        CurriculumEntity entity = data.get(0);
        CurriculumEntity newEntity = curriculumPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getProfile(), newEntity.getProfile());
        Assert.assertEquals(entity.getIdentification(), newEntity.getIdentification());
    }

    /**
     * @generated
     */
    @Test
    public void deleteCurriculumTest() {
        CurriculumEntity entity = data.get(0);
        curriculumPersistence.delete(entity.getId());
        CurriculumEntity deleted = em.find(CurriculumEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateCurriculumTest() {
        CurriculumEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CurriculumEntity newEntity = factory.manufacturePojo(CurriculumEntity.class);

        newEntity.setId(entity.getId());

        curriculumPersistence.update(newEntity);

        CurriculumEntity resp = em.find(CurriculumEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getProfile(), resp.getProfile());
        Assert.assertEquals(newEntity.getIdentification(), resp.getIdentification());
    }
}