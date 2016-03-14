package co.edu.uniandes.csw.mpfreelancer.ejbs;

import co.edu.uniandes.csw.mp.ann.MPLoCAnn;
import co.edu.uniandes.csw.mpfreelancer.api.ISkillLogic;
import co.edu.uniandes.csw.mpfreelancer.entities.SkillEntity;
import co.edu.uniandes.csw.mpfreelancer.persistence.SkillPersistence;
import co.edu.uniandes.csw.mpfreelancer.entities.FreelancerEntity;
import co.edu.uniandes.csw.mpfreelancer.entities.ProjectEntity;
import co.edu.uniandes.csw.mpfreelancer.api.IProjectLogic;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @generated
 */
@Stateless
public class SkillLogic implements ISkillLogic {

    @Inject private SkillPersistence persistence;

    @Inject private IProjectLogic projectLogic;

    /**
     * @generated
     */
    @Override
    public int countSkills() {
        return persistence.count();
    }

    /**
     * @generated
     */
    @Override
    public List<SkillEntity> getSkills() {
        return persistence.findAll();
    }

    /**
     * @generated
     */
    @Override
    public List<SkillEntity> getSkills(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }
    /**
     * @generated
     */
    @Override
    public SkillEntity getSkill(Long id) {
        return persistence.find(id);
    }

    /**
     * @generated
     */
    @MPLoCAnn(tier = "Backend", reqId = "R005")
    @Override
    public SkillEntity createSkill(SkillEntity entity) {
        persistence.create(entity);
        return entity;
    }

    /**
     * @generated
     */
    @MPLoCAnn(tier = "Backend", reqId = "R006")
    @Override
    public SkillEntity updateSkill(SkillEntity entity) {
        SkillEntity newEntity = entity;
        SkillEntity oldEntity = persistence.find(entity.getId());
        newEntity.setFreelancers(oldEntity.getFreelancers());
        newEntity.setProjects(oldEntity.getProjects());
        return persistence.update(newEntity);
    }

    /**
     * @generated
     */
    @MPLoCAnn(tier = "Backend", reqId = "R007")
    @Override
    public void deleteSkill(Long id) {
        persistence.delete(id);
    }

    /**
     * @generated
     */
    @Override
    public List<FreelancerEntity> listFreelancers(Long skillId) {
        return persistence.find(skillId).getFreelancers();
    }

    /**
     * @generated
     */
    @Override
    public FreelancerEntity getFreelancers(Long skillId, Long freelancersId) {
        List<FreelancerEntity> list = persistence.find(skillId).getFreelancers();
        FreelancerEntity freelancersEntity = new FreelancerEntity();
        freelancersEntity.setId(freelancersId);
        int index = list.indexOf(freelancersEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }

    /**
     * @generated
     */
    @Override
    public FreelancerEntity addFreelancers(Long skillId, Long freelancersId) {
        SkillEntity skillEntity = persistence.find(skillId);
        FreelancerEntity freelancersEntity = new FreelancerEntity();
        freelancersEntity.setId(freelancersId);
        skillEntity.getFreelancers().add(freelancersEntity);
        return getFreelancers(skillId, freelancersId);
    }

    /**
     * @generated
     */
    @Override
    public List<FreelancerEntity> replaceFreelancers(Long skillId, List<FreelancerEntity> list) {
        SkillEntity skillEntity = persistence.find(skillId);
        skillEntity.setFreelancers(list);
        return skillEntity.getFreelancers();
    }

    /**
     * @generated
     */
    @Override
    public void removeFreelancers(Long skillId, Long freelancersId) {
        SkillEntity entity = persistence.find(skillId);
        FreelancerEntity freelancersEntity = new FreelancerEntity();
        freelancersEntity.setId(freelancersId);
        entity.getFreelancers().remove(freelancersEntity);
    }

    /**
     * @generated
     */
    @Override
    public List<ProjectEntity> listProjects(Long skillId) {
        return persistence.find(skillId).getProjects();
    }

    /**
     * @generated
     */
    @Override
    public ProjectEntity getProjects(Long skillId, Long projectsId) {
        List<ProjectEntity> list = persistence.find(skillId).getProjects();
        //ProjectEntity projectsEntity = new ProjectEntity();
        //projectsEntity.setId(projectsId);
        //int index = list.indexOf(projectsEntity);
        
        for (int i = 0 ; i < list.size(); i++)
        {
            ProjectEntity projectEntity = list.get(i);
            if (projectEntity.getId().equals(projectsId))
                return projectEntity;
        }
        
        /*if (index >= 0) {
            return list.get(index);
        }*/
        return null;
    }

    /**
     * @generated
     */
    @Override
    public ProjectEntity addProjects(Long skillId, Long projectsId) {
        projectLogic.addExpectedskills(projectsId, skillId);
        return projectLogic.getProject(projectsId);
    }

    /**
     * @generated
     */
    @Override
    public List<ProjectEntity> replaceProjects(Long skillId, List<ProjectEntity> list) {
        SkillEntity skillEntity = persistence.find(skillId);
        List<ProjectEntity> projectList = projectLogic.getProjects();
        for (ProjectEntity project : projectList) {
            if (list.contains(project)) {
                if (!project.getExpectedskills().contains(skillEntity)) {
                    projectLogic.addExpectedskills(project.getId(), skillId);
                }
            } else {
                projectLogic.removeExpectedskills(project.getId(), skillId);
            }
        }
        skillEntity.setProjects(list);
        return skillEntity.getProjects();
    }

    /**
     * @generated
     */
    @Override
    public void removeProjects(Long skillId, Long projectsId) {
        projectLogic.removeExpectedskills(projectsId, skillId);
    }
}
