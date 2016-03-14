package co.edu.uniandes.csw.mpfreelancer.test.persistence;

import co.edu.uniandes.csw.mpfreelancer.entities.ProjectEntity;
import co.edu.uniandes.csw.mpfreelancer.entities.ProjectSprintEntity;
import co.edu.uniandes.csw.mpfreelancer.persistence.ProjectSprintPersistence;
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
 * My first project sprint persistence test set
 * @generated
 */
@RunWith(Arquillian.class)
public class ProjectSprintPersistenceTest 
{
    /**
     * @generated
     */
    private List<ProjectEntity> projectsData = new ArrayList<>();
    
    /**
     * @generated
     */
    private List<ProjectSprintEntity> data = new ArrayList<ProjectSprintEntity>();
    
    /**
     * Default class logger
     */
    private static final Logger logger = Logger.getLogger( ProjectSprintPersistenceTest.class.getName() );
    
    /**
     * @generated
     * @return new deployment file
     */
    @Deployment
    public static JavaArchive createDeployment() 
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ProjectSprintEntity.class.getPackage())
                .addPackage(ProjectSprintPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private ProjectSprintPersistence projectSprintPersistence;

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
        em.createQuery("delete from ProjectSprintEntity").executeUpdate();
        em.createQuery("delete from ProjectEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private void insertData() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        
        for (int i = 0; i < 3; i++) 
        {
            ProjectEntity projects = factory.manufacturePojo(ProjectEntity.class);
            em.persist(projects);
            projectsData.add(projects);
        }

        for (int i = 0; i < 3; i++) 
        {
            ProjectSprintEntity entity = factory.manufacturePojo(ProjectSprintEntity.class);
            entity.setProject(projectsData.get(i));
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * @generated
     */
    @Test
    public void createProjectSprintTest() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        ProjectSprintEntity newEntity = factory.manufacturePojo(ProjectSprintEntity.class);
        ProjectSprintEntity result = projectSprintPersistence.create(newEntity);

        Assert.assertNotNull(result);

        ProjectSprintEntity entity = em.find(ProjectSprintEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getDescription(), entity.getDescription());
    }

    /**
     * @generated
     */
    @Test
    public void getProjectSprintTest() 
    {
        ProjectSprintEntity entity = data.get(0);
        ProjectSprintEntity newEntity = projectSprintPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getDescription(), newEntity.getDescription());
    }

    /**
     * @generated
     */
    @Test
    public void deleteProjectSprintTest() 
    {
        ProjectSprintEntity entity = data.get(0);
        projectSprintPersistence.delete(entity.getId());
        ProjectSprintEntity deleted = em.find(ProjectSprintEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateProjectSprintTest() 
    {
        ProjectSprintEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ProjectSprintEntity newEntity = factory.manufacturePojo(ProjectSprintEntity.class);

        newEntity.setId(entity.getId());

        projectSprintPersistence.update(newEntity);

        ProjectSprintEntity resp = em.find(ProjectSprintEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getDescription(), resp.getDescription());
    }
}
