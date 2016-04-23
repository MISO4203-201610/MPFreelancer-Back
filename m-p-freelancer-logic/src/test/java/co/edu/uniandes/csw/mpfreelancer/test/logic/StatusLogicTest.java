package co.edu.uniandes.csw.mpfreelancer.test.logic;

import co.edu.uniandes.csw.mpfreelancer.ejbs.StatusLogic;
import co.edu.uniandes.csw.mpfreelancer.api.IStatusLogic;
import co.edu.uniandes.csw.mpfreelancer.entities.StatusEntity;
import co.edu.uniandes.csw.mpfreelancer.persistence.StatusPersistence;
import co.edu.uniandes.csw.mpfreelancer.entities.ProjectEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
public class StatusLogicTest {

    private static final Logger log = Logger.getLogger("jdk.nashorn.internal.runtime.logging.Logger");
    
    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * @generated
     */
    @Inject
    private IStatusLogic statusLogic;

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
    private List<StatusEntity> data = new ArrayList<StatusEntity>();

    /**
     * @generated
     */
    private List<ProjectEntity> projectData = new ArrayList<>();

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(StatusEntity.class.getPackage())
                .addPackage(StatusLogic.class.getPackage())
                .addPackage(IStatusLogic.class.getPackage())
                .addPackage(StatusPersistence.class.getPackage())
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
            log.log(Level.WARNING, e.getMessage());
            try {
                utx.rollback();
            } catch (Exception e1) {
                log.log(Level.WARNING, e.getMessage());
                throw new RuntimeException(e1);
            }
            
            throw new RuntimeException(e);
        }
    }

    /**
     * @generated
     */
    private void clearData() {
        em.createQuery("delete from ProjectEntity").executeUpdate();
        em.createQuery("delete from StatusEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ProjectEntity project = factory.manufacturePojo(ProjectEntity.class);
            em.persist(project);
            projectData.add(project);
        }

        for (int i = 0; i < 3; i++) {
            StatusEntity entity = factory.manufacturePojo(StatusEntity.class);

            em.persist(entity);
            data.add(entity);

            if (i == 0) {
                projectData.get(i).setStatus(entity);
            }
        }
    }

    /**
     * @generated
     */
    @Test
    public void createStatusTest() {
        StatusEntity entity = factory.manufacturePojo(StatusEntity.class);
        StatusEntity result = statusLogic.createStatus(entity);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), entity.getId());
        Assert.assertEquals(result.getName(), entity.getName());
    }

    /**
     * @generated
     */
    @Test
    public void getStatussTest() {
        List<StatusEntity> list = statusLogic.getStatuss();
        Assert.assertEquals(data.size(), list.size());
        for (StatusEntity entity : list) {
            boolean found = false;
            for (StatusEntity storedEntity : data) {
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
    public void getStatusTest() {
        StatusEntity entity = data.get(0);
        StatusEntity resultEntity = statusLogic.getStatus(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
    }

    /**
     * @generated
     */
    @Test
    public void deleteStatusTest() {
        StatusEntity entity = data.get(1);
        statusLogic.deleteStatus(entity.getId());
        StatusEntity deleted = em.find(StatusEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateStatusTest() {
        StatusEntity entity = data.get(0);
        StatusEntity pojoEntity = factory.manufacturePojo(StatusEntity.class);

        pojoEntity.setId(entity.getId());

        statusLogic.updateStatus(pojoEntity);

        StatusEntity resp = em.find(StatusEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
    }

    /**
     * @generated
     */
    @Test
    public void getProjectTest() {
        StatusEntity entity = data.get(0);
        ProjectEntity projectEntity = projectData.get(0);
        ProjectEntity response = statusLogic.getProject(entity.getId(), projectEntity.getId());

        Assert.assertEquals(projectEntity.getId(), response.getId());
        Assert.assertEquals(projectEntity.getName(), response.getName());
        Assert.assertEquals(projectEntity.getDescription(), response.getDescription());
        Assert.assertEquals(projectEntity.getPrice(), response.getPrice());
        Assert.assertEquals(projectEntity.getDeadLine(), response.getDeadLine());
        Assert.assertEquals(projectEntity.getPublicationDate(), response.getPublicationDate());
        Assert.assertEquals(projectEntity.getStartDate(), response.getStartDate());
    }

    /**
     * @generated
     */
    @Test
    public void listProjectTest() {
        List<ProjectEntity> list = statusLogic.listProject(data.get(0).getId());
        Assert.assertEquals(1, list.size());
    }

    /**
     * @generated
     */
    @Test
    public void addProjectTest() {
        StatusEntity entity = data.get(0);
        ProjectEntity projectEntity = projectData.get(1);
        ProjectEntity response = statusLogic.addProject(entity.getId(), projectEntity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(projectEntity.getId(), response.getId());
    }

    /**
     * @generated
     */
    @Test
    public void replaceProjectTest() {
        StatusEntity entity = data.get(0);
        List<ProjectEntity> list = projectData.subList(1, 3);
        statusLogic.replaceProject(entity.getId(), list);

        entity = statusLogic.getStatus(entity.getId());
        Assert.assertFalse(entity.getProject().contains(projectData.get(0)));
        Assert.assertTrue(entity.getProject().contains(projectData.get(1)));
        Assert.assertTrue(entity.getProject().contains(projectData.get(2)));
    }

    /**
     * @generated
     */
    @Test
    public void removeProjectTest() {
        statusLogic.removeProject(data.get(0).getId(), projectData.get(0).getId());
        ProjectEntity response = statusLogic.getProject(data.get(0).getId(), projectData.get(0).getId());
        Assert.assertNull(response);
    }
}
