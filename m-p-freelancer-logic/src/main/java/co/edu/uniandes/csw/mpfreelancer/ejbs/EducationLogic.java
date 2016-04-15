/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.ejbs;

import co.edu.uniandes.csw.mpfreelancer.api.IEducationLogic;
import co.edu.uniandes.csw.mpfreelancer.entities.EducationEntity;
import co.edu.uniandes.csw.mpfreelancer.persistence.EducationPersistence;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author mf.calderon
 */

public class EducationLogic implements IEducationLogic{
    
    @Inject private EducationPersistence persistence;

    @Override
    public int countTitles() {
        return persistence.count();
    }

    @Override
    public List<EducationEntity> getTitles() {
        return persistence.findAll();
    }

    @Override
    public List<EducationEntity> getTitles(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }

    @Override
    public EducationEntity getTitles(Long id) {
        return persistence.find(id);
    }

    @Override
    public EducationEntity createTitles(EducationEntity entity) {
        persistence.create(entity);
        return entity;
    }

    @Override
    public EducationEntity updateTitles(EducationEntity entity) {
        EducationEntity newEntity = entity;
        EducationEntity oldEntity = persistence.find(entity.getId());
        return persistence.update(newEntity);
    }

    @Override
    public void deleteTitles(Long id) {
        persistence.delete(id);
    }
    
    @Override
    public List<EducationEntity> getByFreelancer(Long id) {
        return persistence.getByFreelancer(id);
    }
    
}
