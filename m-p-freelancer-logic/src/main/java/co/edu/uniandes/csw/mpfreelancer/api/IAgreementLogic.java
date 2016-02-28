/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.api;

import co.edu.uniandes.csw.mpfreelancer.entities.AgreementEntity;
import co.edu.uniandes.csw.mpfreelancer.entities.CategoryEntity;
import co.edu.uniandes.csw.mpfreelancer.entities.ProjectEntity;
import java.util.List;

/**
 *
 * @author jc.nino11
 */
public interface IAgreementLogic {
    public int countAgreements();
    public List<AgreementEntity> getAgreements();
    public List<AgreementEntity> getAgreements(Integer page, Integer maxRecords);
    public AgreementEntity getAgreement(Long id);
    public AgreementEntity createAgreement(AgreementEntity entity);
    public AgreementEntity updateAgreement(AgreementEntity entity);
    public void deleteAgreement(Long id); 
}
