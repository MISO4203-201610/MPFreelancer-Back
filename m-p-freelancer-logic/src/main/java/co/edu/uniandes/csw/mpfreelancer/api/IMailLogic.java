/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.api;

import co.edu.uniandes.csw.mpfreelancer.entities.MailEntity;
import java.util.List;

/**
 *
 * @author mf.calderon
 */
public interface IMailLogic {
    
    public int countMails();
    public List<MailEntity> getMails();
    public List<MailEntity> getMails(Integer page, Integer maxRecords);
    public MailEntity getMails(Long id);
    public MailEntity createMails(MailEntity entity);
    public MailEntity updateMails(MailEntity entity);
    public void deleteMails(Long id);
    
}
