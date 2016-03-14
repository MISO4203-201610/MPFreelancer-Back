package co.edu.uniandes.csw.mpfreelancer.test.logic;

import co.edu.uniandes.csw.mpfreelancer.ejbs.SkillLogic;
import co.edu.uniandes.csw.mpfreelancer.api.ISkillLogic;
import co.edu.uniandes.csw.mpfreelancer.entities.SkillEntity;
import co.edu.uniandes.csw.mpfreelancer.persistence.SkillPersistence;
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
 * @generated
 */
@RunWith(Arquillian.class)
public class SkillLogicTest {

    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * @generated
     */
    @Inject
    private ISkillLogic skillLogic;

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
    private List<SkillEntity> data = new ArrayList<SkillEntity>();

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
                .addPackage(SkillEntity.class.getPackage())
                .addPackage(SkillLogic.class.getPackage())
                .addPackage(ISkillLogic.class.getPackage())
                .addPackage(SkillPersistence.class.getPackage())
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
        em.createQuery("delete from SkillEntity").executeUpdate();
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

        for (int i = 0; i < 3; i++) {
            SkillEntity entity = factory.manufacturePojo(SkillEntity.class);

            entity.getFreelancers().add(freelancersData.get(0));

            em.persist(entity);
            data.add(entity);

            projectsData.get(0).getExpectedskills().add(entity);
        }
    }

    /**
     * @generated
     */
    @Test
    public void createSkillTest() {
        SkillEntity entity = factory.manufacturePojo(SkillEntity.class);
        SkillEntity result = skillLogic.createSkill(entity);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), entity.getId());
        Assert.assertEquals(result.getName(), entity.getName());
        Assert.assertEquals(result.getSkillLevel(), entity.getSkillLevel());
        Assert.assertEquals(result.getDescription(), entity.getDescription());
    }

    /**
     * @generated
     */
    @Test
    public void getSkillsTest() {
        List<SkillEntity> list = skillLogic.getSkills();
        Assert.assertEquals(data.size(), list.size());
        for (SkillEntity entity : list) {
            boolean found = false;
            for (SkillEntity storedEntity : data) {
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
    public void getSkillTest() {
        SkillEntity entity = data.get(0);
        SkillEntity resultEntity = skillLogic.getSkill(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getSkillLevel(), resultEntity.getSkillLevel());
        Assert.assertEquals(entity.getDescription(), resultEntity.getDescription());
    }

    /**
     * @generated
     */
    @Test
    public void deleteSkillTest() {
        SkillEntity entity = data.get(1);
        skillLogic.deleteSkill(entity.getId());
        SkillEntity deleted = em.find(SkillEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateSkillTest() {
        SkillEntity entity = data.get(0);
        SkillEntity pojoEntity = factory.manufacturePojo(SkillEntity.class);

        pojoEntity.setId(entity.getId());

        skillLogic.updateSkill(pojoEntity);

        SkillEntity resp = em.find(SkillEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getSkillLevel(), resp.getSkillLevel());
        Assert.assertEquals(pojoEntity.getDescription(), resp.getDescription());
    }

    /**
     * @generated
     */
    @Test
    public void getFreelancersTest() {
        SkillEntity entity = data.get(0);
        FreelancerEntity freelancerEntity = freelancersData.get(0);
        FreelancerEntity response = skillLogic.getFreelancers(entity.getId(), freelancerEntity.getId());

        Assert.assertEquals(freelancerEntity.getId(), response.getId());
        Assert.assertEquals(freelancerEntity.getName(), response.getName());
        Assert.assertEquals(freelancerEntity.getRate(), response.getRate());
        Assert.assertEquals(freelancerEntity.getBithday(), response.getBithday());
        Assert.assertEquals(freelancerEntity.getPicture(), response.getPicture());
    }

    /**
     * @generated
     */
    @Test
    public void listFreelancersTest() {
        List<FreelancerEntity> list = skillLogic.listFreelancers(data.get(0).getId());
        Assert.assertEquals(1, list.size());
    }

    /**
     * @generated
     */
    @Test
    public void addFreelancersTest() {
        SkillEntity entity = data.get(0);
        FreelancerEntity freelancerEntity = freelancersData.get(1);
        FreelancerEntity response = skillLogic.addFreelancers(entity.getId(), freelancerEntity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(freelancerEntity.getId(), response.getId());
    }

    /**
     * @generated
     */
    @Test
    public void replaceFreelancersTest() {
        SkillEntity entity = data.get(0);
        List<FreelancerEntity> list = freelancersData.subList(1, 3);
        skillLogic.replaceFreelancers(entity.getId(), list);

        entity = skillLogic.getSkill(entity.getId());
        Assert.assertFalse(entity.getFreelancers().contains(freelancersData.get(0)));
        Assert.assertTrue(entity.getFreelancers().contains(freelancersData.get(1)));
        Assert.assertTrue(entity.getFreelancers().contains(freelancersData.get(2)));
    }

    /**
     * @generated
     */
    @Test
    public void removeFreelancersTest() {
        skillLogic.removeFreelancers(data.get(0).getId(), freelancersData.get(0).getId());
        FreelancerEntity response = skillLogic.getFreelancers(data.get(0).getId(), freelancersData.get(0).getId());
        Assert.assertNull(response);
    }

    /**
     * @generated
     */
    @Test
    public void getProjectsTest() {
        SkillEntity entity = data.get(0);
        ProjectEntity projectEntity = projectsData.get(0);
        ProjectEntity response = skillLogic.getProjects(entity.getId(), projectEntity.getId());

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
        List<ProjectEntity> list = skillLogic.listProjects(data.get(0).getId());
        Assert.assertEquals(1, list.size());
    }

    /**
     * @generated
     */
    @Test
    public void addProjectsTest() {
        SkillEntity entity = data.get(0);
        ProjectEntity projectEntity = projectsData.get(1);
        ProjectEntity response = skillLogic.addProjects(entity.getId(), projectEntity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(projectEntity.getId(), response.getId());
    }

    /**
     * @generated
     */
    @Test
    public void replaceProjectsTest() {
        SkillEntity entity = data.get(0);
        List<ProjectEntity> list = projectsData.subList(1, 3);
        skillLogic.replaceProjects(entity.getId(), list);

        entity = skillLogic.getSkill(entity.getId());
        Assert.assertFalse(entity.getProjects().contains(projectsData.get(0)));
        Assert.assertTrue(entity.getProjects().contains(projectsData.get(1)));
        Assert.assertTrue(entity.getProjects().contains(projectsData.get(2)));
    }

    /**
     * @generated
     */
    @Test
    public void removeProjectsTest() {
        skillLogic.removeProjects(data.get(0).getId(), projectsData.get(0).getId());
        ProjectEntity response = skillLogic.getProjects(data.get(0).getId(), projectsData.get(0).getId());
        Assert.assertNull(response);
    }
}
