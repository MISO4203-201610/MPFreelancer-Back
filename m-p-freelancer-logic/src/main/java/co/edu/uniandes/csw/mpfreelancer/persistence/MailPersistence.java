/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.persistence;

import co.edu.uniandes.csw.crud.spi.persistence.CrudPersistence;
import co.edu.uniandes.csw.mpfreelancer.entities.MailEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 *
 * @author mf.calderon
 */
@Stateless
public class MailPersistence extends CrudPersistence<MailEntity> {
    
    @PersistenceContext(unitName="MPFreelancerPU")
    protected EntityManager em;

    /**
     * @generated
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * @generated
     */
    @Override
    protected Class<MailEntity> getEntityClass() {
        return MailEntity.class;
    }
    
    public List<MailEntity> getByConversation(Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id",  id );
        return executeListNamedQuery("Mail.getByConversation", params);
    }
    
}
