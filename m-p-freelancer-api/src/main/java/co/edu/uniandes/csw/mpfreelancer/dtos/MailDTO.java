/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.dtos;

import co.edu.uniandes.csw.crud.api.podam.strategy.DateStrategy;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;
import java.util.Date;


/**
 *
 * @author mf.calderon
 */
@XmlRootElement
public class MailDTO {
    
    private Long id;
    
    @PodamExclude
    private ConversationDTO coversation;
    
    private Integer who;
    
    private String message;
    
    @PodamStrategyValue(DateStrategy.class)
    private Date datemail;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
     public ConversationDTO getConversation(){
        return coversation;
    }
    
    public void setConversation (ConversationDTO conversation){
        this.coversation=conversation;
    }
    
    public Integer getWho (){
        return who;
    }
    
    public void setWho (Integer who){
        this.who=who;
    }
    
    public String getMessge(){
        return message;
    }
    
    public void setMessage (String message){
        this.message=message;
    }
    
    public Date getDatemail(){
        return datemail;
    }
    
    public void setDatemail (Date datemail){
        this.datemail=datemail;
    }
}
