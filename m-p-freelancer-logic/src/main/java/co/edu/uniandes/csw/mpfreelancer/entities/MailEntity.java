/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.entities;

import co.edu.uniandes.csw.crud.api.podam.strategy.DateStrategy;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 *
 * @author mf.calderon
 */
@Entity
public class MailEntity extends BaseEntity implements Serializable{
    
    @PodamExclude
    @ManyToOne
    private ConversationEntity coversation;
    
    private Integer who;
    
    private String message;
    
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date datemail;
    
    public ConversationEntity getConversation(){
        return coversation;
    }
    
    public void setConversation (ConversationEntity conversation){
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
