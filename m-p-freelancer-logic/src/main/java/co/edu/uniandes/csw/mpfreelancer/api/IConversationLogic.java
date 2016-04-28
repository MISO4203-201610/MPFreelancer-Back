/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.api;

import co.edu.uniandes.csw.mpfreelancer.entities.ConversationEntity;
import java.util.List;

/**
 *
 * @author mf.calderon
 */
public interface IConversationLogic {
    
    public int countConversations();
    public List<ConversationEntity> getConversations();
    public List<ConversationEntity> getConversations(Integer page, Integer maxRecords);
    public ConversationEntity getConversations(Long id);
    public ConversationEntity createConversations(ConversationEntity entity);
    public ConversationEntity updateConversations(ConversationEntity entity);
    public void deleteConversations(Long id);
    
}
