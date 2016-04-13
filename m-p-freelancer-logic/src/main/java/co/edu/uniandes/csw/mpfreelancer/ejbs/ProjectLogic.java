package co.edu.uniandes.csw.mpfreelancer.ejbs;

import co.edu.uniandes.csw.mp.ann.MPLoCAnn;
import co.edu.uniandes.csw.mpfreelancer.api.IProjectLogic;
import co.edu.uniandes.csw.mpfreelancer.api.IProjectSprintLogic;
import co.edu.uniandes.csw.mpfreelancer.entities.AgreementEntity;
import co.edu.uniandes.csw.mpfreelancer.entities.ProjectEntity;
import co.edu.uniandes.csw.mpfreelancer.entities.ProjectSprintEntity;
import co.edu.uniandes.csw.mpfreelancer.persistence.ProjectPersistence;
import co.edu.uniandes.csw.mpfreelancer.entities.SkillEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @generated
 */
@Stateless
public class ProjectLogic implements IProjectLogic {

    @Inject private ProjectPersistence persistence;

    @Inject private IProjectSprintLogic projectSprintLogic;
    
    /**
     * @generated
     */
    @Override
    public int countProjects() {
        return persistence.count();
    }

    /**
     * @generated
     */
    @Override
    public List<ProjectEntity> getProjects() {
        return persistence.findAll();
    }

    /**
     * @generated
     */
    @Override
    public List<ProjectEntity> getProjects(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }
    /**
     * @generated
     */
    @Override
    public ProjectEntity getProject(Long id) {
        return persistence.find(id);
    }

    /**
     * @generated
     */
    @MPLoCAnn(tier = "Backend", reqId = "R008")
    @Override
    public ProjectEntity createProject(ProjectEntity entity) {
        persistence.create(entity);
        return entity;
    }

    /**
     * @generated
     */
    @MPLoCAnn(tier = "Backend", reqId = "R009")
    @Override
    public ProjectEntity updateProject(ProjectEntity entity) {
        ProjectEntity newEntity = entity;
        ProjectEntity oldEntity = persistence.find(entity.getId());
        newEntity.setExpectedskills(oldEntity.getExpectedskills());
        return persistence.update(newEntity);
    }

    /**
     * @generated
     */
    @MPLoCAnn(tier = "Backend", reqId = "R010")
    @Override
    public void deleteProject(Long id) {
        persistence.delete(id);
    }

    /**
     * @generated
     */
    @Override
    public List<SkillEntity> listExpectedskills(Long projectId) {
        return persistence.find(projectId).getExpectedskills();
    }

    /**
     * @generated
     */
    @Override
    public List<ProjectSprintEntity> listSprints(Long projectId) {
        return persistence.find(projectId).getProjectSprints();
    }
    
    /**
     * @generated
     */
    @Override
    public SkillEntity getExpectedskills(Long projectId, Long expectedskillsId) {
        List<SkillEntity> list = persistence.find(projectId).getExpectedskills();
        SkillEntity expectedskillsEntity = new SkillEntity();
        expectedskillsEntity.setId(expectedskillsId);
        int index = list.indexOf(expectedskillsEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }

    /**
     * @generated
     */
    @Override
    public SkillEntity addExpectedskills(Long projectId, Long expectedskillsId) {
        ProjectEntity projectEntity = persistence.find(projectId);
        SkillEntity expectedskillsEntity = new SkillEntity();
        expectedskillsEntity.setId(expectedskillsId);
        projectEntity.getExpectedskills().add(expectedskillsEntity);
        persistence.update(projectEntity);////cambio se debe persistir el cambio
        return getExpectedskills(projectId, expectedskillsId);
    }

    /**
     * @generated
     */
    @Override
    public List<SkillEntity> replaceExpectedskills(Long projectId, List<SkillEntity> list) {
        ProjectEntity projectEntity = persistence.find(projectId);
        projectEntity.setExpectedskills(list);
        return projectEntity.getExpectedskills();
    }

    /**
     * @generated
     */
    @Override
    public void removeExpectedskills(Long projectId, Long expectedskillsId) {
        ProjectEntity entity = persistence.find(projectId);
        SkillEntity expectedskillsEntity = new SkillEntity();
        expectedskillsEntity.setId(expectedskillsId);
        entity.getExpectedskills().remove(expectedskillsEntity);
    }
    
    @Override
    public List<ProjectSprintEntity> listProjectSprints(Long projectId) {
        return persistence.find(projectId).getProjectSprints();
    }

    @Override
    public ProjectSprintEntity getProjectSprints(Long projectId, Long projectSprintId) {
        return persistence.find(projectId).getProjectSprints()
                .stream()
                .filter(a -> a.getId().equals(projectSprintId))
                .findFirst()
                .get();
    }

    @Override
    public ProjectSprintEntity addProjectSprints(Long projectId, Long projectSprintId) {
        ProjectEntity projectEntity = persistence.find(projectId);
        ProjectSprintEntity projectSprintEntity = projectSprintLogic.getProjectSprint(projectSprintId);
        projectSprintEntity.setProject(projectEntity);
        return projectSprintEntity;
    }

    @Override
    public void removeProjectSprints(Long projectId, Long projectSprintId) {
        ProjectEntity entity = persistence.find(projectId);
        ProjectSprintEntity projectSprintEntity = new ProjectSprintEntity();
        projectSprintEntity.setId(projectSprintId);
        entity.getProjectSprints().remove(projectSprintEntity);
    }
    
    @Override
    public List<ProjectEntity> listProjectsApplied(Long freelancerId){
        List<ProjectEntity> projectsApplied = new ArrayList<>();
        for (ProjectEntity project : persistence.findAll()){
            for (AgreementEntity agreement: project.getAgreements()){
                if (freelancerId.equals(agreement.getFreelancer().getId())){
                    projectsApplied.add(project);
                    break;
                }
            }
        }
        
        return projectsApplied;
    }
}
