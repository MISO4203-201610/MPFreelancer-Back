package co.edu.uniandes.csw.mpfreelancer.test.persistence;

import co.edu.uniandes.csw.mpfreelancer.entities.BlogEntryEntity;
import co.edu.uniandes.csw.mpfreelancer.entities.FreelancerEntity;
import co.edu.uniandes.csw.mpfreelancer.entities.EducationEntity;
import co.edu.uniandes.csw.mpfreelancer.persistence.BlogEntryPersistence;
import co.edu.uniandes.csw.mpfreelancer.persistence.FreelancerPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
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
public class FreelancerPersistenceTest {

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FreelancerEntity.class.getPackage())
                .addPackage(FreelancerPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private FreelancerPersistence freelancerPersistence;
    @Inject
    private BlogEntryPersistence blogEntryPersistence;

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
        em.createQuery("delete from FreelancerEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<FreelancerEntity> data = new ArrayList<FreelancerEntity>();

    /**
     * @generated
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            FreelancerEntity entity = factory.manufacturePojo(FreelancerEntity.class);
            for (EducationEntity item : entity.getTitles()) {
                item.setFreelancer(entity);
            }
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * @generated
     */
    @Test
    public void createFreelancerTest() {
		PodamFactory factory = new PodamFactoryImpl();
        FreelancerEntity newEntity = factory.manufacturePojo(FreelancerEntity.class);
        FreelancerEntity result = freelancerPersistence.create(newEntity);

        Assert.assertNotNull(result);

        FreelancerEntity entity = em.find(FreelancerEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getRate(), entity.getRate());
        Assert.assertEquals(newEntity.getPicture(), entity.getPicture());
    }
    
    @Test
    public void createBlogEntryTest() {
	PodamFactory factory = new PodamFactoryImpl();
        BlogEntryEntity newEntity = factory.manufacturePojo(BlogEntryEntity.class);
        BlogEntryEntity result = blogEntryPersistence.create(newEntity);

        BlogEntryEntity entity = em.find(BlogEntryEntity.class, result.getId());

        Assert.assertEquals(newEntity.getTitle(), entity.getTitle());
    }

    /**
     * @generated
     */
    @Test
    public void getFreelancersTest() {
        List<FreelancerEntity> list = freelancerPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (FreelancerEntity ent : list) {
            boolean found = false;
            for (FreelancerEntity entity : data) {
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
    public void getFreelancerTest() {
        FreelancerEntity entity = data.get(0);
        FreelancerEntity newEntity = freelancerPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getRate(), newEntity.getRate());
        Assert.assertEquals(entity.getBithday(), newEntity.getBithday());
        Assert.assertEquals(entity.getPicture(), newEntity.getPicture());
    }

    /**
     * @generated
     */
    @Test
    public void deleteFreelancerTest() {
        FreelancerEntity entity = data.get(0);
        freelancerPersistence.delete(entity.getId());
        FreelancerEntity deleted = em.find(FreelancerEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateFreelancerTest() {
        FreelancerEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        FreelancerEntity newEntity = factory.manufacturePojo(FreelancerEntity.class);

        newEntity.setId(entity.getId());

        freelancerPersistence.update(newEntity);

        FreelancerEntity resp = em.find(FreelancerEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getRate(), resp.getRate());
        Assert.assertEquals(newEntity.getPicture(), resp.getPicture());
    }
}
