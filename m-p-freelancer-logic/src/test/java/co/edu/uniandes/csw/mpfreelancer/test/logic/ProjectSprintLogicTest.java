package co.edu.uniandes.csw.mpfreelancer.test.logic;

import co.edu.uniandes.csw.mpfreelancer.ejbs.ProjectSprintLogic;
import co.edu.uniandes.csw.mpfreelancer.api.IProjectSprintLogic;
import co.edu.uniandes.csw.mpfreelancer.entities.ProjectSprintEntity;
import co.edu.uniandes.csw.mpfreelancer.persistence.ProjectSprintPersistence;
import co.edu.uniandes.csw.mpfreelancer.entities.ProjectEntity;
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
 * @generated
 */
@RunWith(Arquillian.class)
public class ProjectSprintLogicTest 
{

    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * @generated
     */
    @Inject
    private IProjectSprintLogic projectSprintLogic;

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
    private List<ProjectSprintEntity> data = new ArrayList<ProjectSprintEntity>();

    /**
     * @generated
     */
    private List<ProjectEntity> projectsData = new ArrayList<>();

    /**
     * Default class logger
     */
    private static final Logger logger = Logger.getLogger( ProjectSprintLogicTest.class.getName() );
    
    /**
     * @generated
     * @return new deployment file
     */
    @Deployment
    public static JavaArchive createDeployment() 
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ProjectSprintEntity.class.getPackage())
                .addPackage(ProjectSprintLogic.class.getPackage())
                .addPackage(IProjectSprintLogic.class.getPackage())
                .addPackage(ProjectSprintPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Before
    public void configTest() 
    {
        try 
        {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } 
        catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | NotSupportedException | SystemException e) 
        {
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
        ProjectSprintEntity entity = factory.manufacturePojo(ProjectSprintEntity.class);
        ProjectSprintEntity result = projectSprintLogic.createProjectSprint(entity);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), entity.getId());
        Assert.assertEquals(result.getName(), entity.getName());
        Assert.assertEquals(result.getDescription(), entity.getDescription());
    }

    /**
     * @generated
     */
    @Test
    public void getProjectSprintTest() 
    {
        ProjectSprintEntity entity = data.get(0);
        ProjectSprintEntity resultEntity = projectSprintLogic.getProjectSprint(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getDescription(), resultEntity.getDescription());
    }

    /**
     * @generated
     */
    @Test
    public void deleteProjectSprintTest() 
    {
        ProjectSprintEntity entity = data.get(1);
        projectSprintLogic.deleteProjectSprint(entity.getId());
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
        ProjectSprintEntity pojoEntity = factory.manufacturePojo(ProjectSprintEntity.class);

        pojoEntity.setId(entity.getId());

        projectSprintLogic.updateProjectSprint(pojoEntity);

        ProjectSprintEntity resp = em.find(ProjectSprintEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getDescription(), resp.getDescription());
    }
}
