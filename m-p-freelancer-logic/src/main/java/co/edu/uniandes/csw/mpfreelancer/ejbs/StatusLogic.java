package co.edu.uniandes.csw.mpfreelancer.ejbs;

import co.edu.uniandes.csw.mpfreelancer.api.IStatusLogic;
import co.edu.uniandes.csw.mpfreelancer.entities.StatusEntity;
import co.edu.uniandes.csw.mpfreelancer.persistence.StatusPersistence;
import co.edu.uniandes.csw.mpfreelancer.entities.ProjectEntity;
import co.edu.uniandes.csw.mpfreelancer.api.IProjectLogic;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @generated
 */
@Stateless
public class StatusLogic implements IStatusLogic {

    @Inject private StatusPersistence persistence;

    @Inject private IProjectLogic projectLogic;

    /**
     * @generated
     */
    @Override
    public int countStatuss() {
        return persistence.count();
    }

    /**
     * @generated
     */
    @Override
    public List<StatusEntity> getStatuss() {
        return persistence.findAll();
    }

    /**
     * @generated
     */
    @Override
    public List<StatusEntity> getStatuss(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }
    /**
     * @generated
     */
    @Override
    public StatusEntity getStatus(Long id) {
        return persistence.find(id);
    }

    /**
     * @generated
     */
    @Override
    public StatusEntity createStatus(StatusEntity entity) {
        persistence.create(entity);
        return entity;
    }

    /**
     * @generated
     */
    @Override
    public StatusEntity updateStatus(StatusEntity entity) {
        StatusEntity newEntity = entity;
        StatusEntity oldEntity = persistence.find(entity.getId());
        newEntity.setProject(oldEntity.getProject());
        return persistence.update(newEntity);
    }

    /**
     * @generated
     */
    @Override
    public void deleteStatus(Long id) {
        persistence.delete(id);
    }

    /**
     * @generated
     */
    @Override
    public List<ProjectEntity> listProject(Long statusId) {
        return persistence.find(statusId).getProject();
    }

    /**
     * @generated
     */
    @Override
    public ProjectEntity getProject(Long statusId, Long projectId) {
        List<ProjectEntity> list = persistence.find(statusId).getProject();
        //ProjectEntity projectEntity = new ProjectEntity();
        //projectEntity.setId(projectId);
        //int index = list.indexOf(projectEntity);
        for (int i = 0 ; i < list.size(); i++)
        {
            ProjectEntity projectEntity = list.get(i);
            if (projectEntity.getId().equals(projectId))
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
    public ProjectEntity addProject(Long statusId, Long projectId) {
        StatusEntity statusEntity = persistence.find(statusId);
        ProjectEntity projectEntity = projectLogic.getProject(projectId);
        projectEntity.setStatus(statusEntity);
        return projectEntity;
    }

    /**
     * @generated
     */
    @Override
    public List<ProjectEntity> replaceProject(Long statusId, List<ProjectEntity> list) {
        StatusEntity statusEntity = persistence.find(statusId);
        List<ProjectEntity> projectList = projectLogic.getProjects();
        for (ProjectEntity project : projectList) {
            if (list.contains(project)) {
                project.setStatus(statusEntity);
            } else {
                if (project.getStatus() != null && project.getStatus().equals(statusEntity)) {
                    project.setStatus(null);
                }
            }
        }
        statusEntity.setProject(list);
        return statusEntity.getProject();
    }

    /**
     * @generated
     */
    @Override
    public void removeProject(Long statusId, Long projectId) {
        ProjectEntity entity = projectLogic.getProject(projectId);
        entity.setStatus(null);
    }
}
