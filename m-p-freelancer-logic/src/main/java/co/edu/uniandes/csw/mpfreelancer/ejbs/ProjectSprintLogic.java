/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.ejbs;

import co.edu.uniandes.csw.mpfreelancer.api.IArtifactLogic;
import co.edu.uniandes.csw.mpfreelancer.api.IProjectSprintLogic;
import co.edu.uniandes.csw.mpfreelancer.entities.ArtifactEntity;
import co.edu.uniandes.csw.mpfreelancer.entities.ProjectSprintEntity;
import co.edu.uniandes.csw.mpfreelancer.persistence.ProjectSprintPersistence;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Alex Vicente Chacon (av.chacon10@uniandes.edu.co)
 */
public class ProjectSprintLogic implements IProjectSprintLogic {
    
    @Inject private ProjectSprintPersistence persistence;

    @Inject private IArtifactLogic artifactLogic;
    
    @Override
    public int countProjectSprints(Long projectId) {
        return persistence.count(projectId);
    }

    @Override
    public List<ProjectSprintEntity> getProjectSprints(Long projectId) {
        return persistence.findAll(projectId);
    }

    @Override
    public List<ProjectSprintEntity> getProjectSprints(Long projectId, Integer page, Integer maxRecords) {
        return persistence.findAll(projectId, page, maxRecords);
    }

    @Override
    public ProjectSprintEntity getProjectSprint(Long id) {
        return persistence.find(id);
    }

    @Override
    public ProjectSprintEntity createProjectSprint(ProjectSprintEntity entity) {
        persistence.create(entity);
        return entity;
    }

    @Override
    public ProjectSprintEntity updateProjectSprint(ProjectSprintEntity entity) {
        ProjectSprintEntity newEntity = entity;
        ProjectSprintEntity oldEntity = persistence.find(entity.getId());
        newEntity.setArtifacts(oldEntity.getArtifacts());
        return persistence.update(newEntity);
    }

    @Override
    public void deleteProjectSprint(Long id) {
         persistence.delete(id);
    }

    @Override
    public List<ArtifactEntity> listArtifacts(Long projectSprintId) {
        return persistence.find(projectSprintId).getArtifacts();
    }

    @Override
    public ArtifactEntity getArtifacts(Long projectSprintId, Long artifactsId) {
        return persistence.find(projectSprintId).getArtifacts()
                .stream()
                .filter(a -> a.getId().equals(artifactsId))
                .findFirst()
                .get();
    }

    @Override
    public ArtifactEntity addArtifacts(Long projectSprintId, Long artifactsId) {
        ProjectSprintEntity projectSprintEntity = persistence.find(projectSprintId);
        ArtifactEntity artefactEntity = artifactLogic.getArtifact(artifactsId);
        artefactEntity.setProjectSprint(projectSprintEntity);
        return artefactEntity;
    }

    @Override
    public void removeArtifacts(Long projectSprintId, Long artifactsId) {
        ProjectSprintEntity entity = persistence.find(projectSprintId);
        ArtifactEntity artifactEntity = new ArtifactEntity();
        artifactEntity.setId(artifactsId);
        entity.getArtifacts().remove(artifactEntity);
    }
}