package co.edu.uniandes.csw.mpfreelancer.test.logic;

import co.edu.uniandes.csw.mpfreelancer.ejbs.FreelancerLogic;
import co.edu.uniandes.csw.mpfreelancer.api.IFreelancerLogic;
import co.edu.uniandes.csw.mpfreelancer.entities.CurriculumEntity;
import co.edu.uniandes.csw.mpfreelancer.entities.FreelancerEntity;
import co.edu.uniandes.csw.mpfreelancer.persistence.FreelancerPersistence;
import co.edu.uniandes.csw.mpfreelancer.entities.EducationEntity;
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
public class FreelancerLogicTest {

    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * @generated
     */
    @Inject
    private IFreelancerLogic freelancerLogic;

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
    private List<FreelancerEntity> data = new ArrayList<FreelancerEntity>();

    /**
     * @generated
     */
    private List<SkillEntity> skillsData = new ArrayList<>();

    /**
     * @generated
     */
    private List<CurriculumEntity> curriculumsData = new ArrayList<>();

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FreelancerEntity.class.getPackage())
                .addPackage(FreelancerLogic.class.getPackage())
                .addPackage(IFreelancerLogic.class.getPackage())
                .addPackage(FreelancerPersistence.class.getPackage())
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
        em.createQuery("delete from EducationEntity").executeUpdate();
        em.createQuery("delete from SkillEntity").executeUpdate();
        em.createQuery("delete from CurriculumEntity").executeUpdate();
        em.createQuery("delete from FreelancerEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            SkillEntity skills = factory.manufacturePojo(SkillEntity.class);
            em.persist(skills);
            skillsData.add(skills);
        }
        
        for (int i = 0; i < 3; i++) {
            CurriculumEntity curriculums = factory.manufacturePojo(CurriculumEntity.class);
            em.persist(curriculums);
            curriculumsData.add(curriculums);
        }

        for (int i = 0; i < 3; i++) {
            FreelancerEntity entity = factory.manufacturePojo(FreelancerEntity.class);

            for (EducationEntity item : entity.getTitles()) {
                item.setFreelancer(entity);
            }

            em.persist(entity);
            data.add(entity);

            skillsData.get(0).getFreelancers().add(entity);
            
            if (i==0)
            curriculumsData.get(0).setFreelancer(entity);
        }
    }

    /**
     * @generated
     */
    @Test
    public void createFreelancerTest() {
        FreelancerEntity entity = factory.manufacturePojo(FreelancerEntity.class);
        FreelancerEntity result = freelancerLogic.createFreelancer(entity);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), entity.getId());
        Assert.assertEquals(result.getName(), entity.getName());
        Assert.assertEquals(result.getRate(), entity.getRate());
        Assert.assertEquals(result.getBithday(), entity.getBithday());
        Assert.assertEquals(result.getPicture(), entity.getPicture());
    }

    /**
     * @generated
     */
    @Test
    public void getFreelancersTest() {
        List<FreelancerEntity> list = freelancerLogic.getFreelancers();
        Assert.assertEquals(data.size(), list.size());
        for (FreelancerEntity entity : list) {
            boolean found = false;
            for (FreelancerEntity storedEntity : data) {
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
    public void getFreelancerTest() {
        FreelancerEntity entity = data.get(0);
        FreelancerEntity resultEntity = freelancerLogic.getFreelancer(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getRate(), resultEntity.getRate());
        Assert.assertEquals(entity.getBithday(), resultEntity.getBithday());
        Assert.assertEquals(entity.getPicture(), resultEntity.getPicture());
    }

    /**
     * @generated
     */
    @Test
    public void deleteFreelancerTest() {
        FreelancerEntity entity = data.get(1);
        freelancerLogic.deleteFreelancer(entity.getId());
        FreelancerEntity deleted = em.find(FreelancerEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateFreelancerTest() {
        FreelancerEntity entity = data.get(0);
        FreelancerEntity pojoEntity = factory.manufacturePojo(FreelancerEntity.class);

        pojoEntity.setId(entity.getId());

        freelancerLogic.updateFreelancer(pojoEntity);

        FreelancerEntity resp = em.find(FreelancerEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getRate(), resp.getRate());
        Assert.assertEquals(pojoEntity.getBithday(), resp.getBithday());
        Assert.assertEquals(pojoEntity.getPicture(), resp.getPicture());
    }

    /**
     * @generated
     */
    @Test
    public void getSkillsTest() {
        FreelancerEntity entity = data.get(0);
        SkillEntity skillEntity = skillsData.get(0);
        SkillEntity response = freelancerLogic.getSkills(entity.getId(), skillEntity.getId());

        Assert.assertEquals(skillEntity.getId(), response.getId());
        Assert.assertEquals(skillEntity.getName(), response.getName());
        Assert.assertEquals(skillEntity.getDescription(), response.getDescription());
    }

    /**
     * @generated
     */
    @Test
    public void listSkillsTest() {
        List<SkillEntity> list = freelancerLogic.listSkills(data.get(0).getId());
        Assert.assertEquals(1, list.size());
    }

    /**
     * @generated
     */
    @Test
    public void addSkillsTest() {
        FreelancerEntity entity = data.get(0);
        SkillEntity skillEntity = skillsData.get(1);
        SkillEntity response = freelancerLogic.addSkills(entity.getId(), skillEntity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(skillEntity.getId(), response.getId());
    }

    /**
     * @generated
     */
    @Test
    public void replaceSkillsTest() {
        FreelancerEntity entity = data.get(0);
        List<SkillEntity> list = skillsData.subList(1, 3);
        freelancerLogic.replaceSkills(entity.getId(), list);

        entity = freelancerLogic.getFreelancer(entity.getId());
        Assert.assertFalse(entity.getSkills().contains(skillsData.get(0)));
        Assert.assertTrue(entity.getSkills().contains(skillsData.get(1)));
        Assert.assertTrue(entity.getSkills().contains(skillsData.get(2)));
    }

    /**
     * @generated
     */
    @Test
    public void removeSkillsTest() {
        freelancerLogic.removeSkills(data.get(0).getId(), skillsData.get(0).getId());
        SkillEntity response = freelancerLogic.getSkills(data.get(0).getId(), skillsData.get(0).getId());
        Assert.assertNull(response);
    }
    
        /**
     * @generated
     */
    @Test
    public void getCurriculumTest() {
        FreelancerEntity entity = data.get(0);
        CurriculumEntity curriculumEntity = curriculumsData.get(0);
        CurriculumEntity response = freelancerLogic.getFreelancer(entity.getId()).getCurriculums().get(0);

        Assert.assertEquals(curriculumEntity.getId(), response.getId());
        Assert.assertEquals(curriculumEntity.getName(), response.getName());
        Assert.assertEquals(curriculumEntity.getProfile(), response.getProfile());
    }
    /**
     * @generated
     */
    @Test
    public void listCurriculumsTest() {
        List<CurriculumEntity> list = freelancerLogic.getFreelancer(data.get(0).getId()).getCurriculums();
        Assert.assertEquals(1, list.size());
    }

    /**
     * @generated
     */
    @Test
    public void addCurriculumsTest() {
        FreelancerEntity entity = data.get(0);
        CurriculumEntity curriculumEntity = curriculumsData.get(1);
        boolean response = freelancerLogic.getFreelancer(entity.getId()).getCurriculums().add(curriculumEntity);
 
        Assert.assertEquals(true, response);
    }
    /**
     * @generated
     */
}