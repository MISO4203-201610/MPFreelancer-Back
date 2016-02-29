/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamStrategyValue;
import co.edu.uniandes.csw.crud.api.podam.strategy.DateStrategy;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author mf.calderon
 */
@Entity
public class WorkExperienceEntity extends BaseEntity implements Serializable{
    
    private String projectName;
    
    private String projectDescription;
    
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date endDate;

    private String sponsorCompany;

    @PodamExclude
    @OneToOne (mappedBy = "experiences")
    private FreelancerEntity freelancerExperience;
    
    
    public String getProjectName(){
        return projectName;
    }
    
    public void setProjectName(String projectName){
        this.projectName=projectName;
    }
    
    public String getProjectDescription(){
        return projectDescription;
    }
    
    public void setProjectDescription(String projectDescription){
        this.projectDescription= projectDescription;
    }
    
    public Date getStartDate(){
        return startDate;
    }
   
    public void setStartDate(Date startDate){
        this.startDate = startDate;
    }
    
    public Date getEndDate(){
        return endDate;
    }
   
    public void setEndtDate(Date endDate){
        this.endDate = endDate;
    }
    
    public String getSponsorCompany(){
        return sponsorCompany;
    }
    
    public void setSponsorCompany(String sponsorCompany){
        this.sponsorCompany=sponsorCompany;
    }
    
    public FreelancerEntity getFreelancerExperience(){
        return freelancerExperience;
    }
    
    public void setWorkExperience(FreelancerEntity freelancerExperience){
        this.freelancerExperience=freelancerExperience;
    }
    
    
    
}
