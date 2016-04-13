/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.ejbs;

import co.edu.uniandes.csw.mpfreelancer.api.IWorkExperienceLogic;
import co.edu.uniandes.csw.mpfreelancer.entities.WorkExperienceEntity;
import co.edu.uniandes.csw.mpfreelancer.persistence.WorkExperiencePersistence;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author mf.calderon
 */
public class WorkExperienceLogic implements IWorkExperienceLogic {
    
    @Inject private WorkExperiencePersistence persistence;

    @Override
    public int countExperience() {
        return persistence.count();
    }

    @Override
    public List<WorkExperienceEntity> getExperience() {
        return persistence.findAll();
    }

    @Override
    public List<WorkExperienceEntity> getExperience(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }

    @Override
    public WorkExperienceEntity getExperience(Long id) {
        return persistence.find(id);
    }

    @Override
    public WorkExperienceEntity createExperience(WorkExperienceEntity entity) {
        persistence.create(entity);
        return entity;
    }

    @Override
    public WorkExperienceEntity updateExperience(WorkExperienceEntity entity) {
        WorkExperienceEntity newEntity = entity;
        WorkExperienceEntity oldEntity = persistence.find(entity.getId());
        return persistence.update(newEntity);
    }

    @Override
    public void deleteExperience(Long id) {
        persistence.delete(id);
    }
    
    @Override
    public List<WorkExperienceEntity> getByFreelancer(Long id) {
        return persistence.getByFreelancer(id);
    }
    
}
