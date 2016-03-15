/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.dtos;

import co.edu.uniandes.csw.crud.api.podam.strategy.DateStrategy;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 *
 * @author mf.calderon
 */
@XmlRootElement
public class WorkExperienceDTO {
    private Long id;
    private String projectName;
    private String projectDescription;
    @PodamStrategyValue(DateStrategy.class)
    private Date startDate;
    @PodamStrategyValue(DateStrategy.class)
    private Date endDate;
    private String sponsorCompany;
    private Integer price;
    private Double rate;
    private String url;
    @PodamExclude
    private FreelancerDTO freelancerExperience;
   

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getProjectName(){
        return projectName;
    }
    
    public void setProjectName(String projectName){
        this.projectName= projectName;
    }
    
    public String getProjectDescription(){
        return projectDescription;
    }
    
    public void setProjectDescription(String projetDescription){
        this.projectDescription=projetDescription;
    }
   
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startdate) {
        this.startDate = startdate;
    }
    
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getSponsorCompany() {
        return sponsorCompany;
    }

    public void setSponsorCompany(String sponsorCompany) {
        this.sponsorCompany = sponsorCompany;
    }
    
     public Integer getPrice(){
        return price;
    }
    
    public void setPrice(Integer price){
        this.price=price;
    }
    
    public Double getRate(){
        return rate;
    }
    
    public void setRate(Double rate){
        this.rate=rate;
    }
    
    public String getUrl(){
        return url;
    }
    
    public void setUrl(String url){
        this.url=url;
    }

    public FreelancerDTO getFreelancerExperience(){
        return freelancerExperience;
    }
    
    public void setFreelancerExperience(FreelancerDTO freelancerExperience){
        this.freelancerExperience= freelancerExperience;
    }
    
}
