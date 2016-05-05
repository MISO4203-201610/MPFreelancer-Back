/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.test.persistence;
import co.edu.uniandes.csw.mpfreelancer.entities.ConversationEntity;
import co.edu.uniandes.csw.mpfreelancer.persistence.ConversationPersistence;
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
public class ConversationPersistenceTest {
    
    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ConversationEntity.class.getPackage())
                .addPackage(ConversationPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private ConversationPersistence conversationPersistence;

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
        em.createQuery("delete from ConversationEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<ConversationEntity> data = new ArrayList<ConversationEntity>();

    /**
     * @generated
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ConversationEntity entity = factory.manufacturePojo(ConversationEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * @generated
     */
    @Test
    public void createConversationTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ConversationEntity newEntity = factory.manufacturePojo(ConversationEntity.class);
        ConversationEntity result = conversationPersistence.create(newEntity);

        Assert.assertNotNull(result);

        ConversationEntity entity = em.find(ConversationEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getSubject(), entity.getSubject());
        
    }

    /**
     * @generated
     */
    @Test
    public void getConversationsTest() {
        List<ConversationEntity> list = conversationPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ConversationEntity ent : list) {
            boolean found = false;
            for (ConversationEntity entity : data) {
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
    public void getConversationTest() {
        ConversationEntity entity = data.get(0);
        ConversationEntity newEntity = conversationPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getSubject(), newEntity.getSubject());
        
    }

    /**
     * @generated
     */
    @Test
    public void deleteConversationTest() {
        ConversationEntity entity = data.get(0);
        conversationPersistence.delete(entity.getId());
        ConversationEntity deleted = em.find(ConversationEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateConversationTest() {
        ConversationEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ConversationEntity newEntity = factory.manufacturePojo(ConversationEntity.class);

        newEntity.setId(entity.getId());

        conversationPersistence.update(newEntity);

        ConversationEntity resp = em.find(ConversationEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getSubject(), resp.getSubject());
        
    }
    
}
