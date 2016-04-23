/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.ejbs;

import co.edu.uniandes.csw.mpfreelancer.api.ICurriculumLogic;
import co.edu.uniandes.csw.mpfreelancer.entities.CurriculumEntity;
import co.edu.uniandes.csw.mpfreelancer.persistence.CurriculumPersistence;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author asistente
 */
public class CurriculumLogic implements ICurriculumLogic {
    
    @Inject private CurriculumPersistence persistence;

    @Override
    public int countCurriculums() {
        return persistence.count();
    }

    @Override
    public List<CurriculumEntity> getCurriculums() {
        return persistence.findAll();
    }

    @Override
    public List<CurriculumEntity> getCurriculums(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }

    @Override
    public CurriculumEntity getCurriculum(Long id) {
        return persistence.find(id);
    }

    @Override
    public CurriculumEntity createCurriculum(CurriculumEntity entity) {
        persistence.create(entity);
        return entity;
    }

    @Override
    public CurriculumEntity updateCurriculum(CurriculumEntity entity) {
        CurriculumEntity newEntity = entity;
        CurriculumEntity oldEntity = persistence.find(entity.getId());
        return persistence.update(newEntity);
    }

    @Override
    public void deleteCurriculum(Long id) {
        persistence.delete(id);
    }

    @Override
    public List<CurriculumEntity> getByFreelancer(Long id) {
        return persistence.getByFreelancer(id);
    }
}