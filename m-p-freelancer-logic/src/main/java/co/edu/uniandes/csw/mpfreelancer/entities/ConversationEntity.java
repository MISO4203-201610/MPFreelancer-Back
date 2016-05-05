/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.entities;

import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;
import java.util.List;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author mf.calderon
 */
@Entity

@NamedQueries({
    @NamedQuery(name = "Conversation.getByFreelancer", query = "select u from ConversationEntity u Where u.freelancer.id=:id"),
    @NamedQuery(name = "Conversation.getByProject", query = "select u from ConversationEntity u Where u.project.id=:id")
}) 
public class ConversationEntity extends BaseEntity implements Serializable {
      
    
    @PodamExclude
    @ManyToOne
    private FreelancerEntity freelancer;

    @PodamExclude
    @ManyToOne
    private ProjectEntity project;
    
    @PodamExclude
    @OneToMany
    private List<MailEntity> mails;
    
    private String subject;
    
    public ProjectEntity getProject(){
        return project;
    }
    
    public void setProject(ProjectEntity project){
        this.project = project;
    }
    
    public FreelancerEntity getFreelancer(){
        return freelancer;
    }
    
    public void setFreelancer(FreelancerEntity freelancer){
        this.freelancer = freelancer;
    }
    
    public List<MailEntity> getMails() {
        return mails;
    }

    public void setMails(List<MailEntity> mails) {
        this.mails = mails;
    }
    
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    
}
