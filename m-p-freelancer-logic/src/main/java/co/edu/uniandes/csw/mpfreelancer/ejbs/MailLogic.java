/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.ejbs;

import co.edu.uniandes.csw.mpfreelancer.api.IMailLogic;
import co.edu.uniandes.csw.mpfreelancer.entities.MailEntity;
import co.edu.uniandes.csw.mpfreelancer.persistence.MailPersistence;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author mf.calderon
 */
public class MailLogic implements IMailLogic{
    
      @Inject private MailPersistence persistence;

    @Override
    public int countMails() {
        return persistence.count();
    }

    @Override
    public List<MailEntity> getMails() {
        return persistence.findAll();
    }

    @Override
    public List<MailEntity> getMails (Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }

    @Override
    public MailEntity getMails(Long id) {
        return persistence.find(id);
    }

    @Override
    public MailEntity createMails(MailEntity entity) {
        persistence.create(entity);
        return entity;
    }

    @Override
    public MailEntity updateMails(MailEntity entity) {
        MailEntity newEntity = entity;
        MailEntity oldEntity = persistence.find(entity.getId());
        return persistence.update(newEntity);
    }

    @Override
    public void deleteMails(Long id) {
        persistence.delete(id);
    }
    
    @Override
    public List<MailEntity> getByConversation(Long id) {
        return persistence.getByConversation(id);
    }
    
}
