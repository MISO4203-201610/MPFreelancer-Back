package co.edu.uniandes.csw.mpfreelancer.test.logic;

import co.edu.uniandes.csw.mpfreelancer.ejbs.CategoryLogic;
import co.edu.uniandes.csw.mpfreelancer.api.ICategoryLogic;
import co.edu.uniandes.csw.mpfreelancer.entities.CategoryEntity;
import co.edu.uniandes.csw.mpfreelancer.persistence.CategoryPersistence;
import co.edu.uniandes.csw.mpfreelancer.entities.ProjectEntity;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import static org.glassfish.resourcebase.resources.util.ResourceUtil.LOGGER;

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
public class CategoryLogicTest {

    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * @generated
     */
    @Inject
    private ICategoryLogic categoryLogic;

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
    private List<CategoryEntity> data = new ArrayList<CategoryEntity>();

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
                .addPackage(CategoryEntity.class.getPackage())
                .addPackage(CategoryLogic.class.getPackage())
                .addPackage(ICategoryLogic.class.getPackage())
                .addPackage(CategoryPersistence.class.getPackage())
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
            
    //        e.printStackTrace();
            
            try {
                utx.rollback();
            } catch (Exception e1) {
                  throw new RuntimeException(e1);
    //            e1.printStackTrace();
            }
            throw new RuntimeException(e);
        }
    }

    /**
     * @generated
     */
    private void clearData() {
        em.createQuery("delete from ProjectEntity").executeUpdate();
        em.createQuery("delete from CategoryEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ProjectEntity projects = factory.manufacturePojo(ProjectEntity.class);
            em.persist(projects);
            projectsData.add(projects);
        }

        for (int i = 0; i < 3; i++) {
            CategoryEntity entity = factory.manufacturePojo(CategoryEntity.class);

            em.persist(entity);
            data.add(entity);
            
            if (i == 0) {
                projectsData.get(i).setCategory(entity);
            }
        }
    }

    /**
     * @generated
     */
    @Test
    public void createCategoryTest() {
        CategoryEntity entity = factory.manufacturePojo(CategoryEntity.class);
        CategoryEntity result = categoryLogic.createCategory(entity);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), entity.getId());
        Assert.assertEquals(result.getName(), entity.getName());
        Assert.assertEquals(result.getDescription(), entity.getDescription());
    }

    /**
     * @generated
     */
    @Test
    public void getCategorysTest() {
        List<CategoryEntity> list = categoryLogic.getCategorys();
        Assert.assertEquals(data.size(), list.size());
        for (CategoryEntity entity : list) {
            boolean found = false;
            for (CategoryEntity storedEntity : data) {
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
    public void getCategoryTest() {
        CategoryEntity entity = data.get(0);
        CategoryEntity resultEntity = categoryLogic.getCategory(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getDescription(), resultEntity.getDescription());
    }

    /**
     * @generated
     */
    @Test
    public void deleteCategoryTest() {
        CategoryEntity entity = data.get(1);
        categoryLogic.deleteCategory(entity.getId());
        CategoryEntity deleted = em.find(CategoryEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateCategoryTest() {
        CategoryEntity entity = data.get(0);
        CategoryEntity pojoEntity = factory.manufacturePojo(CategoryEntity.class);

        pojoEntity.setId(entity.getId());

        categoryLogic.updateCategory(pojoEntity);

        CategoryEntity resp = em.find(CategoryEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getDescription(), resp.getDescription());
    }

    /**
     * @generated
     */
    @Test
    public void getProjectsTest() {
        CategoryEntity entity = data.get(0);
        ProjectEntity projectEntity = projectsData.get(0);
        ProjectEntity response = categoryLogic.getProjects(entity.getId(), projectEntity.getId());
        
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
    public void listProjectsTest() {
        List<ProjectEntity> list = categoryLogic.listProjects(data.get(0).getId());
        Assert.assertEquals(1, list.size());
    }

    /**
     * @generated
     */
    @Test
    public void addProjectsTest() {
        CategoryEntity entity = data.get(0);
        ProjectEntity projectEntity = projectsData.get(1);
        ProjectEntity response = categoryLogic.addProjects(entity.getId(), projectEntity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(projectEntity.getId(), response.getId());
    }

    /**
     * @generated
     */
    @Test
    public void replaceProjectsTest() {
        CategoryEntity entity = data.get(0);
        List<ProjectEntity> list = projectsData.subList(1, 3);
        categoryLogic.replaceProjects(entity.getId(), list);

        entity = categoryLogic.getCategory(entity.getId());
        Assert.assertFalse(entity.getProjects().contains(projectsData.get(0)));
        Assert.assertTrue(entity.getProjects().contains(projectsData.get(1)));
        Assert.assertTrue(entity.getProjects().contains(projectsData.get(2)));
    }

    /**
     * @generated
     */
    @Test
    public void removeProjectsTest() {
        categoryLogic.removeProjects(data.get(0).getId(), projectsData.get(0).getId());
        ProjectEntity response = categoryLogic.getProjects(data.get(0).getId(), projectsData.get(0).getId());
        Assert.assertNull(response);
    }
}
