/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.ejbs;

import co.edu.uniandes.csw.mpfreelancer.api.IConversationLogic;
import co.edu.uniandes.csw.mpfreelancer.entities.ConversationEntity;
import co.edu.uniandes.csw.mpfreelancer.persistence.ConversationPersistence;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author mf.calderon
 */
public class ConversationLogic implements IConversationLogic {
    
     @Inject private ConversationPersistence persistence;

    @Override
    public int countConversations() {
        return persistence.count();
    }

    @Override
    public List<ConversationEntity> getConversations() {
        return persistence.findAll();
    }

    @Override
    public List<ConversationEntity> getConversations(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }

    @Override
    public ConversationEntity getConversations(Long id) {
        return persistence.find(id);
    }

    @Override
    public ConversationEntity createConversations(ConversationEntity entity) {
        persistence.create(entity);
        return entity;
    }

    @Override
    public ConversationEntity updateConversations(ConversationEntity entity) {
        ConversationEntity newEntity = entity;
        ConversationEntity oldEntity = persistence.find(entity.getId());
        return persistence.update(newEntity);
    }

    @Override
    public void deleteConversations(Long id) {
        persistence.delete(id);
    }
    
}
