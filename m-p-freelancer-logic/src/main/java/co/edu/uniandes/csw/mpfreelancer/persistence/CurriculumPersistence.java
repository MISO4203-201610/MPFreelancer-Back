/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.persistence;

import co.edu.uniandes.csw.crud.spi.persistence.CrudPersistence;
import co.edu.uniandes.csw.mpfreelancer.entities.AgreementEntity;
import co.edu.uniandes.csw.mpfreelancer.entities.CurriculumEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Fernando
 */
@Stateless
public class CurriculumPersistence extends CrudPersistence<CurriculumEntity>{
    @PersistenceContext(unitName="MPFreelancerPU")
    protected EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected Class<CurriculumEntity> getEntityClass() {
        return CurriculumEntity.class;
    }

    public List<CurriculumEntity> getByFreelancer(Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id",  id );
        return executeListNamedQuery("Curriculum.getByFreelancer", params);
    }
}