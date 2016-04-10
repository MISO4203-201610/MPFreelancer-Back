/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.persistence;

import co.edu.uniandes.csw.crud.spi.persistence.CrudPersistence;
import co.edu.uniandes.csw.mpfreelancer.entities.AgreementEntity;
import co.edu.uniandes.csw.mpfreelancer.entities.FreelancerEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.enterprise.inject.New;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jc.nino11
 */
@Stateless
public class AgreementPersistence extends CrudPersistence<AgreementEntity>{
     
    @PersistenceContext(unitName="MPFreelancerPU")
    protected EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

   @Override
    protected Class<AgreementEntity> getEntityClass() {
        return AgreementEntity.class;
    }   

    public List<AgreementEntity> getByFreelancer(Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id",  id );
        return executeListNamedQuery("Agreement.getByFreelancer", params);
    }
    
    public List<AgreementEntity> getByProject(Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id",  id );
        return executeListNamedQuery("Agreement.getByProject", params);
    }
}
