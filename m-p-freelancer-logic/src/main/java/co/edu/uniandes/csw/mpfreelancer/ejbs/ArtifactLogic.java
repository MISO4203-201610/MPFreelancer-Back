/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.ejbs;

import co.edu.uniandes.csw.mpfreelancer.api.IArtifactLogic;
import co.edu.uniandes.csw.mpfreelancer.entities.ArtifactEntity;
import co.edu.uniandes.csw.mpfreelancer.persistence.ArtifactPersistence;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Alex Vicente Chacon (av.chacon10@uniandes.edu.co)
 */
public class ArtifactLogic implements IArtifactLogic {
    
    @Inject private ArtifactPersistence persistence;

    @Override
    public int countArtifacts(Long projectSprintId) {
        return persistence.count(projectSprintId);
    }

    @Override
    public List<ArtifactEntity> getArtifacts(Long projectSprintId) {
        return persistence.findAll(projectSprintId);
    }

    @Override
    public List<ArtifactEntity> getArtifacts(Long projectSprintId, Integer page, Integer maxRecords) {
        return persistence.findAll(projectSprintId, page, maxRecords);
    }

    @Override
    public ArtifactEntity getArtifact(Long id) {
        return persistence.find(id);
    }

    @Override
    public ArtifactEntity createArtifact(ArtifactEntity entity) {
        persistence.create(entity);
        return entity;
    }

    @Override
    public ArtifactEntity updateArtifact(ArtifactEntity entity) {
        return persistence.update(entity);
    }

    @Override
    public void deleteArtifact(Long id) {
        persistence.delete(id);
    }
}