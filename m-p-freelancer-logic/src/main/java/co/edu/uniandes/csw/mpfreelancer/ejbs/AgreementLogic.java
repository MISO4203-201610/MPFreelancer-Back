/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.ejbs;

import co.edu.uniandes.csw.mpfreelancer.api.IAgreementLogic;
import co.edu.uniandes.csw.mpfreelancer.entities.AgreementEntity;
import co.edu.uniandes.csw.mpfreelancer.persistence.AgreementPersistence;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author jc.nino11
 */
public class AgreementLogic implements IAgreementLogic{
    
    @Inject private AgreementPersistence persistence;

    @Override
    public int countAgreements() {
        return persistence.count();
    }

    @Override
    public List<AgreementEntity> getAgreements() {
        return persistence.findAll();
    }

    @Override
    public List<AgreementEntity> getAgreements(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }

    @Override
    public AgreementEntity getAgreement(Long id) {
        return persistence.find(id);
    }

    @Override
    public AgreementEntity createAgreement(AgreementEntity entity) {
        persistence.create(entity);
        return entity;
    }

    @Override
    public AgreementEntity updateAgreement(AgreementEntity entity) {
        AgreementEntity newEntity = entity;
        AgreementEntity oldEntity = persistence.find(entity.getId());
        return persistence.update(newEntity);
    }

    @Override
    public void deleteAgreement(Long id) {
        persistence.delete(id);
    }
}
