/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.test.logic;

import co.edu.uniandes.csw.mpfreelancer.ejbs.MailLogic;
import co.edu.uniandes.csw.mpfreelancer.api.IMailLogic;
import co.edu.uniandes.csw.mpfreelancer.entities.MailEntity;
import co.edu.uniandes.csw.mpfreelancer.persistence.MailPersistence;
import co.edu.uniandes.csw.mpfreelancer.entities.ConversationEntity;
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
public class MailLogicTest {
    
    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * @generated
     */
    @Inject
    private IMailLogic mailLogic;

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
    private List<MailEntity> data = new ArrayList<MailEntity>();

    /**
     * @generated
     */
    private List<ConversationEntity> conversationData = new ArrayList<>();

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MailEntity.class.getPackage())
                .addPackage(MailLogic.class.getPackage())
                .addPackage(IMailLogic.class.getPackage())
                .addPackage(MailPersistence.class.getPackage())
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
        em.createQuery("delete from MailEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private void insertData() {

        for (int i = 0; i < 3; i++) {
            ConversationEntity conversation = factory.manufacturePojo(ConversationEntity.class);
            em.persist(conversation);
            conversationData.add(conversation);
        }
        
                
        em.createQuery("delete from MailEntity").executeUpdate();
        for (int i = 0; i < 3; i++) {
            MailEntity entity = factory.manufacturePojo(MailEntity.class);

            entity.setConversation(conversationData.get(0));

            em.persist(entity);
            data.add(entity);

        }
    }

    /**
     * @generated
     */
    @Test
    public void createMailTest() {
        
        MailEntity entity = factory.manufacturePojo(MailEntity.class);
        MailEntity result = mailLogic.createMails(entity);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), entity.getId());
        Assert.assertEquals(result.getName(), entity.getName());
     }

    /**
     * @generated
     */
    @Test
    public void getCurriculumsTest() {
        List<MailEntity> list = mailLogic.getMails();
        Assert.assertEquals(data.size(), list.size());
        for (MailEntity entity : list) {
            boolean found = false;
            for (MailEntity storedEntity : data) {
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
        MailEntity entity = data.get(0);
        MailEntity resultEntity = mailLogic.getMails(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
    }

    /**
     * @generated
     */
    @Test
    public void deleteCurriculumTest() {
        MailEntity entity = data.get(1);
        mailLogic.deleteMails(entity.getId());
        MailEntity deleted = em.find(MailEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateCurriculumTest() {
        MailEntity entity = data.get(0);
        MailEntity pojoEntity = factory.manufacturePojo(MailEntity.class);

        pojoEntity.setId(entity.getId());

        mailLogic.updateMails(pojoEntity);

        MailEntity resp = em.find(MailEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
    }
    
}
