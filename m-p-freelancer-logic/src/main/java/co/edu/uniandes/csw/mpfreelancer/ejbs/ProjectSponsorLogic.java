package co.edu.uniandes.csw.mpfreelancer.ejbs;

import co.edu.uniandes.csw.mpfreelancer.api.IProjectSponsorLogic;
import co.edu.uniandes.csw.mpfreelancer.entities.ProjectSponsorEntity;
import co.edu.uniandes.csw.mpfreelancer.persistence.ProjectSponsorPersistence;
import co.edu.uniandes.csw.mpfreelancer.entities.ProjectEntity;
import co.edu.uniandes.csw.mpfreelancer.api.IProjectLogic;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @generated
 */
@Stateless
public class ProjectSponsorLogic implements IProjectSponsorLogic {

    @Inject private ProjectSponsorPersistence persistence;

    @Inject private IProjectLogic projectLogic;

    /**
     * @generated
     */
    @Override
    public int countProjectSponsors() {
        return persistence.count();
    }

    /**
     * @generated
     */
    @Override
    public List<ProjectSponsorEntity> getProjectSponsors() {
        return persistence.findAll();
    }

    /**
     * @generated
     */
    @Override
    public List<ProjectSponsorEntity> getProjectSponsors(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }
    /**
     * @generated
     */
    @Override
    public ProjectSponsorEntity getProjectSponsor(Long id) {
        return persistence.find(id);
    }

    /**
     * @generated
     */
    @Override
    public ProjectSponsorEntity createProjectSponsor(ProjectSponsorEntity entity) {
        persistence.create(entity);
        return entity;
    }

    /**
     * @generated
     */
    @Override
    public ProjectSponsorEntity updateProjectSponsor(ProjectSponsorEntity entity) {
        ProjectSponsorEntity newEntity = entity;
        ProjectSponsorEntity oldEntity = persistence.find(entity.getId());
        newEntity.setProjects(oldEntity.getProjects());
        return persistence.update(newEntity);
    }

    /**
     * @generated
     */
    @Override
    public void deleteProjectSponsor(Long id) {
        persistence.delete(id);
    }

    /**
     * @generated
     */
    @Override
    public List<ProjectEntity> listProjects(Long projectSponsorId) {
        return persistence.find(projectSponsorId).getProjects();
    }

    /**
     * @generated
     */
    @Override
    public ProjectEntity getProjects(Long projectSponsorId, Long projectsId) {
        List<ProjectEntity> list = persistence.find(projectSponsorId).getProjects();
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
    public ProjectEntity addProjects(Long projectSponsorId, Long projectsId) {
        ProjectSponsorEntity projectSponsorEntity = persistence.find(projectSponsorId);
        ProjectEntity projectsEntity = projectLogic.getProject(projectsId);
        projectsEntity.setSponsor(projectSponsorEntity);
        return projectsEntity;
    }

    /**
     * @generated
     */
    @Override
    public List<ProjectEntity> replaceProjects(Long projectSponsorId, List<ProjectEntity> list) {
        ProjectSponsorEntity projectSponsorEntity = persistence.find(projectSponsorId);
        List<ProjectEntity> projectList = projectLogic.getProjects();
        for (ProjectEntity project : projectList) {
            if (list.contains(project)) {
                project.setSponsor(projectSponsorEntity);
            } else {
                if (project.getSponsor() != null && project.getSponsor().equals(projectSponsorEntity)) {
                    project.setSponsor(null);
                }
            }
        }
        projectSponsorEntity.setProjects(list);
        return projectSponsorEntity.getProjects();
    }

    /**
     * @generated
     */
    @Override
    public void removeProjects(Long projectSponsorId, Long projectsId) {
        ProjectEntity entity = projectLogic.getProject(projectsId);
        entity.setSponsor(null);
    }
}
