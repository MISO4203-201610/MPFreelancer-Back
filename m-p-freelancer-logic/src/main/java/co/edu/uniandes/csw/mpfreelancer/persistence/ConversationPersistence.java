/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import co.edu.uniandes.csw.mpfreelancer.entities.ConversationEntity;
import co.edu.uniandes.csw.crud.spi.persistence.CrudPersistence;
import co.edu.uniandes.csw.mpfreelancer.entities.AgreementEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author mf.calderon
 */
@Stateless
public class ConversationPersistence extends CrudPersistence<ConversationEntity> {
    
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
    protected Class<ConversationEntity> getEntityClass() {
        return ConversationEntity.class;
    }
    
    public List<ConversationEntity> getByFreelancer(Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id",  id );
        return executeListNamedQuery("Conversation.getByFreelancer", params);
    }
    
    public List<ConversationEntity> getByProject(Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id",  id );
        return executeListNamedQuery("Conversation.getByProject", params);
    }
    
}
