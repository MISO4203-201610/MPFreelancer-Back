/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.test.persistence;
import co.edu.uniandes.csw.mpfreelancer.entities.MailEntity;
import co.edu.uniandes.csw.mpfreelancer.persistence.MailPersistence;
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
public class MailPersistenceTest {
    
    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MailEntity.class.getPackage())
                .addPackage(MailPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private MailPersistence mailPersistence;

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
        em.createQuery("delete from MailEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<MailEntity> data = new ArrayList<MailEntity>();

    /**
     * @generated
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            MailEntity entity = factory.manufacturePojo(MailEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * @generated
     */
    @Test
    public void createMailTest() {
        PodamFactory factory = new PodamFactoryImpl();
        MailEntity newEntity = factory.manufacturePojo(MailEntity.class);
        MailEntity result = mailPersistence.create(newEntity);

        Assert.assertNotNull(result);

        MailEntity entity = em.find(MailEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getMessge(), entity.getMessge());
        
    }

    /**
     * @generated
     */
    @Test
    public void getCMailsTest() {
        List<MailEntity> list = mailPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (MailEntity ent : list) {
            boolean found = false;
            for (MailEntity entity : data) {
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
    public void getMailTest() {
        MailEntity entity = data.get(0);
        MailEntity newEntity = mailPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getMessge(), newEntity.getMessge());
        
    }

    /**
     * @generated
     */
    @Test
    public void deleteMailTest() {
        MailEntity entity = data.get(0);
        mailPersistence.delete(entity.getId());
        MailEntity deleted = em.find(MailEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateMailTest() {
        MailEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        MailEntity newEntity = factory.manufacturePojo(MailEntity.class);

        newEntity.setId(entity.getId());

        mailPersistence.update(newEntity);

        MailEntity resp = em.find(MailEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getMessge(), resp.getMessge());
        
    }
    
}
