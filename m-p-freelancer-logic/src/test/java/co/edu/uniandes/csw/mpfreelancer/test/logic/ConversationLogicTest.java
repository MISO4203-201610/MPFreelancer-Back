/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.test.logic;

import co.edu.uniandes.csw.mpfreelancer.ejbs.ConversationLogic;
import co.edu.uniandes.csw.mpfreelancer.api.IConversationLogic;
import co.edu.uniandes.csw.mpfreelancer.entities.ConversationEntity;
import co.edu.uniandes.csw.mpfreelancer.persistence.ConversationPersistence;
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
public class ConversationLogicTest {
    
    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * @generated
     */
    @Inject
    private IConversationLogic conversationLogic;

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
    private List<ConversationEntity> data = new ArrayList<ConversationEntity>();

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
                .addPackage(ConversationEntity.class.getPackage())
                .addPackage(ConversationLogic.class.getPackage())
                .addPackage(IConversationLogic.class.getPackage())
                .addPackage(ConversationPersistence.class.getPackage())
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
        em.createQuery("delete from ConversationEntity").executeUpdate();
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
        
        em.createQuery("delete from ConversationEntity").executeUpdate();
        for (int i = 0; i < 3; i++) {
            ConversationEntity entity = factory.manufacturePojo(ConversationEntity.class);

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
        
        ConversationEntity entity = factory.manufacturePojo(ConversationEntity.class);
        ConversationEntity result = conversationLogic.createConversations(entity);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), entity.getId());
        Assert.assertEquals(result.getName(), entity.getName());
     }

    /**
     * @generated
     */
    @Test
    public void getCurriculumsTest() {
        List<ConversationEntity> list = conversationLogic.getConversations();
        Assert.assertEquals(data.size(), list.size());
        for (ConversationEntity entity : list) {
            boolean found = false;
            for (ConversationEntity storedEntity : data) {
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
        ConversationEntity entity = data.get(0);
        ConversationEntity resultEntity = conversationLogic.getConversations(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
    }

    /**
     * @generated
     */
    @Test
    public void deleteCurriculumTest() {
        ConversationEntity entity = data.get(1);
        conversationLogic.deleteConversations(entity.getId());
        ConversationEntity deleted = em.find(ConversationEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateCurriculumTest() {
        ConversationEntity entity = data.get(0);
        ConversationEntity pojoEntity = factory.manufacturePojo(ConversationEntity.class);

        pojoEntity.setId(entity.getId());

        conversationLogic.updateConversations(pojoEntity);

        ConversationEntity resp = em.find(ConversationEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
    }
    
}
