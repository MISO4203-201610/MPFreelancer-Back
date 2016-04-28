/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.dtos;


import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;
import java.util.List;



/**
 *
 * @author mf.calderon
 */
@XmlRootElement
public class ConversationDTO {
    
    private Long id;
    
    @PodamExclude
    private FreelancerDTO freelancer;
    
    @PodamExclude
    private ProjectDTO project;
    
    @PodamExclude
    private List<MailDTO> mails;
    
    private String subject;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public FreelancerDTO getFreelancer(){
        return freelancer;
    }
    
    public void setFreelancer(FreelancerDTO freelancer){
        this.freelancer = freelancer;
    }
    
    public ProjectDTO getProject(){
        return project;
    }
    
    public void setProject(ProjectDTO project){
        this.project = project;
    }
        
    public List<MailDTO> getMails() {
        return mails;
    }

    public void setMails(List<MailDTO> mails) {
        this.mails = mails;
    }
    
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    
}
