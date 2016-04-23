package co.edu.uniandes.csw.mpfreelancer.test.persistence;

import co.edu.uniandes.csw.mpfreelancer.entities.AgreementEntity;
import co.edu.uniandes.csw.mpfreelancer.persistence.AgreementPersistence;
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
public class AgreementPersistenceTest {

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AgreementEntity.class.getPackage())
                .addPackage(AgreementPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private AgreementPersistence agreementPersistence;

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
        em.createQuery("delete from AgreementEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<AgreementEntity> data = new ArrayList<AgreementEntity>();

    /**
     * @generated
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            AgreementEntity entity = factory.manufacturePojo(AgreementEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * @generated
     */
    @Test
    public void createAgreementTest() {
        PodamFactory factory = new PodamFactoryImpl();
        AgreementEntity newEntity = factory.manufacturePojo(AgreementEntity.class);
        AgreementEntity result = agreementPersistence.create(newEntity);

        Assert.assertNotNull(result);

        AgreementEntity entity = em.find(AgreementEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getPrice(), entity.getPrice());
        Assert.assertEquals(newEntity.getRate(), entity.getRate());
        Assert.assertEquals(newEntity.getSelected(), entity.getSelected());
        Assert.assertEquals(newEntity.getStatus(), entity.getStatus());
    }

    /**
     * @generated
     */
    @Test
    public void getAgreementsTest() {
        List<AgreementEntity> list = agreementPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (AgreementEntity ent : list) {
            boolean found = false;
            for (AgreementEntity entity : data) {
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
    public void getAgreementTest() {
        AgreementEntity entity = data.get(0);
        AgreementEntity newEntity = agreementPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getPrice(), newEntity.getPrice());
        Assert.assertEquals(entity.getRate(), newEntity.getRate());
        Assert.assertEquals(entity.getSelected(), newEntity.getSelected());
        Assert.assertEquals(entity.getStatus(), newEntity.getStatus());
    }

    /**
     * @generated
     */
    @Test
    public void deleteAgreementTest() {
        AgreementEntity entity = data.get(0);
        agreementPersistence.delete(entity.getId());
        AgreementEntity deleted = em.find(AgreementEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateAgreementTest() {
        AgreementEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        AgreementEntity newEntity = factory.manufacturePojo(AgreementEntity.class);

        newEntity.setId(entity.getId());

        agreementPersistence.update(newEntity);

        AgreementEntity resp = em.find(AgreementEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getPrice(), resp.getPrice());
        Assert.assertEquals(newEntity.getRate(), resp.getRate());
        Assert.assertEquals(newEntity.getSelected(), resp.getSelected());
        Assert.assertEquals(newEntity.getStatus(), resp.getStatus());
    }
}