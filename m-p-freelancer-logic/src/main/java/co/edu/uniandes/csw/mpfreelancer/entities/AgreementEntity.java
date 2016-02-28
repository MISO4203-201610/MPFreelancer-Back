package co.edu.uniandes.csw.mpfreelancer.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * @generated
 */
@Entity
public class AgreementEntity extends BaseEntity implements Serializable {

    private Integer price;
    
    private Double rate;
    
    private Boolean selected;
    
    @PodamExclude
    @ManyToOne
    private FreelancerEntity freelancer;

    @PodamExclude
    @OneToOne
    private ProjectEntity project;
        
    /**
     * @generated
     */
    public ProjectEntity getProject(){
        return project;
    }
    
     /**
     * @generated
     */
    public void setProject(ProjectEntity project){
        this.project = project;
    }
    
    /**
     * @generated
     */
    public FreelancerEntity getFreelancer(){
        return freelancer;
    }
    
     /**
     * @generated
     */
    public void setFreelancer(FreelancerEntity freelancer){
        this.freelancer = freelancer;
    }
    
    
    /**
     * @generated
     */
    public Double getRate(){
        return rate;
    }

    /**
     * @generated
     */
    public void setRate(Double rate){
        this.rate = rate;
    }
    
    /**
     * @generated
     */
    public Boolean getSelected(){
        return selected;
    }

    /**
     * @generated
     */
    public void setSelected(Boolean selected){
        this.selected = selected;
    }
    
    /**
     * @generated
     */
    public Integer getPrice(){
        return price;
    }

    /**
     * @generated
     */
    public void setPrice(Integer price){
        this.price = price;
    }
}
