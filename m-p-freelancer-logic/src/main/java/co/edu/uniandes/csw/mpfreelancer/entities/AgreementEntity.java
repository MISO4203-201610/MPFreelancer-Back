package co.edu.uniandes.csw.mpfreelancer.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@Entity
/*
    Querys
    Agreement.getByFreelancer  Selecciona los agreements para un Freelancer
    Agreement.getByProject  Selecciona los agreements para un proyecto
*/
@NamedQueries({
    @NamedQuery(name = "Agreement.getByFreelancer", query = "select u from AgreementEntity u Where u.freelancer.id=:id"),
    @NamedQuery(name = "Agreement.getByProject", query = "select u from AgreementEntity u Where u.project.id=:id"),
    @NamedQuery(name = "Agreement.getByStatus1", query = "select u from AgreementEntity u where u.freelancer.id=:id and u.status=1"),
    @NamedQuery(name = "Agreement.getByStatus2", query = "select u from AgreementEntity u where u.freelancer.id=:id and u.status=2"),
    @NamedQuery(name = "Agreement.getByStatus3", query = "select u from AgreementEntity u where u.freelancer.id=:id and u.status=3"),
    @NamedQuery(name = "Agreement.getByStatus4", query = "select u from AgreementEntity u where u.freelancer.id=:id and u.status=4")
    
}) 
public class AgreementEntity extends BaseEntity implements Serializable {

    private Integer price;
    
    private Double rate;
    
    private Boolean selected;

    private Integer status;
    
    @PodamExclude
    @ManyToOne
    private FreelancerEntity freelancer;

    @PodamExclude
    @ManyToOne
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
    
    /**
     * @generated
     */
    public Integer getStatus(){
        return status;
    }

    /**
     * @generated
     */
    public void setStatus(Integer status){
        this.status = status;
    }
}
