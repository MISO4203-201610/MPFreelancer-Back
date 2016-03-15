/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.test.persistence;

import co.edu.uniandes.csw.mpfreelancer.entities.FreelancerEntity;
import co.edu.uniandes.csw.mpfreelancer.entities.WorkExperienceEntity;
import co.edu.uniandes.csw.mpfreelancer.persistence.WorkExperiencePersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
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
public class WorkExperiencePersitenceTest {
    
    /**
     * @generated
     */
    private List<FreelancerEntity> freelancerData = new ArrayList<>();
    
    /**
     * @generated
     */
    private List<WorkExperienceEntity> data = new ArrayList<WorkExperienceEntity>();
    
    /**
     * Default class logger
     */
    private static final Logger logger = Logger.getLogger( WorkExperiencePersitenceTest.class.getName() );
    
    /**
     * @generated
     * @return new deployment file
     */
    @Deployment
    public static JavaArchive createDeployment() 
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(WorkExperienceEntity.class.getPackage())
                .addPackage(WorkExperiencePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private WorkExperiencePersistence workExperiencePersistence;

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
    public void configTest() 
    {
        try 
        {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } 
        catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | NotSupportedException | SystemException e) 
        {
            logger.log( Level.SEVERE, e.toString(), e);
            try 
            {
                utx.rollback();
            } 
            catch (IllegalStateException | SecurityException | SystemException e1) 
            {
                logger.log( Level.SEVERE, e.toString(), e1);
            } 
        }
        
    }

    /**
     * @generated
     */
    private void clearData() 
    {
        em.createQuery("delete from WorkExperienceEntity").executeUpdate();
        em.createQuery("delete from FreelancerEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private void insertData() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        
        for (int i = 0; i < 3; i++) 
        {
            FreelancerEntity freelancers = factory.manufacturePojo(FreelancerEntity.class);
            em.persist(freelancers);
            freelancerData.add(freelancers);
        }

        for (int i = 0; i < 3; i++) 
        {
            WorkExperienceEntity entity = factory.manufacturePojo(WorkExperienceEntity.class);
            entity.setFreelancer(freelancerData.get(i));
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * @generated
     */
    @Test
    public void createWorkExperienceTest() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        WorkExperienceEntity newEntity = factory.manufacturePojo(WorkExperienceEntity.class);
        WorkExperienceEntity result = workExperiencePersistence.create(newEntity);

        Assert.assertNotNull(result);

        WorkExperienceEntity entity = em.find(WorkExperienceEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getProjectName(), entity.getProjectName());
    }

    /**
     * @generated
     */
    @Test
    public void getWorkExperienceTest() 
    {
        WorkExperienceEntity entity = data.get(0);
        WorkExperienceEntity newEntity = workExperiencePersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getProjectName(), newEntity.getProjectName());
    }

    /**
     * @generated
     */
    @Test
    public void deleteWorkExperienceTest() 
    {
        WorkExperienceEntity entity = data.get(0);
        workExperiencePersistence.delete(entity.getId());
        WorkExperienceEntity deleted = em.find(WorkExperienceEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateWorkExperienceTest() 
    {
        WorkExperienceEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        WorkExperienceEntity newEntity = factory.manufacturePojo(WorkExperienceEntity.class);

        newEntity.setId(entity.getId());

        workExperiencePersistence.update(newEntity);

        WorkExperienceEntity resp = em.find(WorkExperienceEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getProjectName(), resp.getProjectName());
    }
    
}
