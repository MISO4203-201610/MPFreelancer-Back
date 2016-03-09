package co.edu.uniandes.csw.mpfreelancer.test.logic;

import co.edu.uniandes.csw.mpfreelancer.ejbs.ProjectLogic;
import co.edu.uniandes.csw.mpfreelancer.api.IProjectLogic;
import co.edu.uniandes.csw.mpfreelancer.entities.ProjectEntity;
import co.edu.uniandes.csw.mpfreelancer.persistence.ProjectPersistence;
import co.edu.uniandes.csw.mpfreelancer.entities.StatusEntity;
import co.edu.uniandes.csw.mpfreelancer.entities.CategoryEntity;
import co.edu.uniandes.csw.mpfreelancer.entities.ProjectSponsorEntity;
import co.edu.uniandes.csw.mpfreelancer.entities.SkillEntity;
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
public class ProjectLogicTest {

    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * @generated
     */
    @Inject
    private IProjectLogic projectLogic;

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
    private List<ProjectEntity> data = new ArrayList<ProjectEntity>();

    /**
     * @generated
     */
    private List<StatusEntity> statusData = new ArrayList<>();

    /**
     * @generated
     */
    private List<CategoryEntity> categoryData = new ArrayList<>();

    /**
     * @generated
     */
    private List<ProjectSponsorEntity> sponsorData = new ArrayList<>();

    /**
     * @generated
     */
    private List<SkillEntity> expectedskillsData = new ArrayList<>();

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ProjectEntity.class.getPackage())
                .addPackage(ProjectLogic.class.getPackage())
                .addPackage(IProjectLogic.class.getPackage())
                .addPackage(ProjectPersistence.class.getPackage())
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
        em.createQuery("delete from StatusEntity").executeUpdate();
        em.createQuery("delete from CategoryEntity").executeUpdate();
        em.createQuery("delete from ProjectSponsorEntity").executeUpdate();
        em.createQuery("delete from SkillEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            StatusEntity status = factory.manufacturePojo(StatusEntity.class);
            em.persist(status);
            statusData.add(status);
        }

        for (int i = 0; i < 3; i++) {
            CategoryEntity category = factory.manufacturePojo(CategoryEntity.class);
            em.persist(category);
            categoryData.add(category);
        }

        for (int i = 0; i < 3; i++) {
            ProjectSponsorEntity sponsor = factory.manufacturePojo(ProjectSponsorEntity.class);
            em.persist(sponsor);
            sponsorData.add(sponsor);
        }

        for (int i = 0; i < 3; i++) {
            SkillEntity expectedskills = factory.manufacturePojo(SkillEntity.class);
            em.persist(expectedskills);
            expectedskillsData.add(expectedskills);
        }

        for (int i = 0; i < 3; i++) {
            ProjectEntity entity = factory.manufacturePojo(ProjectEntity.class);

            entity.setStatus(statusData.get(0));

            entity.setCategory(categoryData.get(0));

            entity.setSponsor(sponsorData.get(0));

            entity.getExpectedskills().add(expectedskillsData.get(0));

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * @generated
     */
    @Test
    public void createProjectTest() {
        ProjectEntity entity = factory.manufacturePojo(ProjectEntity.class);
        ProjectEntity result = projectLogic.createProject(entity);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), entity.getId());
        Assert.assertEquals(result.getName(), entity.getName());
        Assert.assertEquals(result.getDescription(), entity.getDescription());
        Assert.assertEquals(result.getPrice(), entity.getPrice());
        Assert.assertEquals(result.getDeadLine(), entity.getDeadLine());
        Assert.assertEquals(result.getPublicationDate(), entity.getPublicationDate());
        Assert.assertEquals(result.getStartDate(), entity.getStartDate());
    }

    /**
     * @generated
     */
    @Test
    public void getProjectsTest() {
        List<ProjectEntity> list = projectLogic.getProjects();
        Assert.assertEquals(data.size(), list.size());
        for (ProjectEntity entity : list) {
            boolean found = false;
            for (ProjectEntity storedEntity : data) {
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
    public void getProjectTest() {
        ProjectEntity entity = data.get(0);
        ProjectEntity resultEntity = projectLogic.getProject(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getDescription(), resultEntity.getDescription());
        Assert.assertEquals(entity.getPrice(), resultEntity.getPrice());
        Assert.assertEquals(entity.getDeadLine(), resultEntity.getDeadLine());
        Assert.assertEquals(entity.getPublicationDate(), resultEntity.getPublicationDate());
        Assert.assertEquals(entity.getStartDate(), resultEntity.getStartDate());
    }

    /**
     * @generated
     */
    @Test
    public void deleteProjectTest() {
        ProjectEntity entity = data.get(1);
        projectLogic.deleteProject(entity.getId());
        ProjectEntity deleted = em.find(ProjectEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateProjectTest() {
        ProjectEntity entity = data.get(0);
        ProjectEntity pojoEntity = factory.manufacturePojo(ProjectEntity.class);

        pojoEntity.setId(entity.getId());

        projectLogic.updateProject(pojoEntity);

        ProjectEntity resp = em.find(ProjectEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getDescription(), resp.getDescription());
        Assert.assertEquals(pojoEntity.getPrice(), resp.getPrice());
        Assert.assertEquals(pojoEntity.getDeadLine(), resp.getDeadLine());
        Assert.assertEquals(pojoEntity.getPublicationDate(), resp.getPublicationDate());
        Assert.assertEquals(pojoEntity.getStartDate(), resp.getStartDate());
    }

    /**
     * @generated
     */
    @Test
    public void getExpectedskillsTest() {
        ProjectEntity entity = data.get(0);
        SkillEntity skillEntity = expectedskillsData.get(0);
        SkillEntity response = projectLogic.getExpectedskills(entity.getId(), skillEntity.getId());

        Assert.assertEquals(skillEntity.getId(), response.getId());
        Assert.assertEquals(skillEntity.getName(), response.getName());
        Assert.assertEquals(skillEntity.getDescription(), response.getDescription());
    }

    /**
     * @generated
     */
    @Test
    public void listExpectedskillsTest() {
        List<SkillEntity> list = projectLogic.listExpectedskills(data.get(0).getId());
        Assert.assertEquals(1, list.size());
    }

    /**
     * @generated
     */
    @Test
    public void addExpectedskillsTest() {
        ProjectEntity entity = data.get(0);
        SkillEntity skillEntity = expectedskillsData.get(1);
        SkillEntity response = projectLogic.addExpectedskills(entity.getId(), skillEntity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(skillEntity.getId(), response.getId());
    }

    /**
     * @generated
     */
    @Test
    public void replaceExpectedskillsTest() {
        ProjectEntity entity = data.get(0);
        List<SkillEntity> list = expectedskillsData.subList(1, 3);
        projectLogic.replaceExpectedskills(entity.getId(), list);

        entity = projectLogic.getProject(entity.getId());
        Assert.assertFalse(entity.getExpectedskills().contains(expectedskillsData.get(0)));
        Assert.assertTrue(entity.getExpectedskills().contains(expectedskillsData.get(1)));
        Assert.assertTrue(entity.getExpectedskills().contains(expectedskillsData.get(2)));
    }

    /**
     * @generated
     */
    @Test
    public void removeExpectedskillsTest() {
        projectLogic.removeExpectedskills(data.get(0).getId(), expectedskillsData.get(0).getId());
        SkillEntity response = projectLogic.getExpectedskills(data.get(0).getId(), expectedskillsData.get(0).getId());
        Assert.assertNull(response);
    }
}
