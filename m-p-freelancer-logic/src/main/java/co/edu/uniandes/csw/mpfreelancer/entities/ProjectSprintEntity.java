package co.edu.uniandes.csw.mpfreelancer.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamStrategyValue;
import co.edu.uniandes.csw.crud.api.podam.strategy.DateStrategy;
import uk.co.jemos.podam.common.PodamExclude;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * @generated
 */
@Entity
public class ProjectSprintEntity extends BaseEntity implements Serializable {

    private String description;

    private Integer price;

    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date deadLine;

    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date startDate;

    @PodamExclude
    @OneToMany
    private List<ArtifactEntity> artifacts = new ArrayList<>();

    @PodamExclude
    @ManyToOne
    private ProjectEntity project;

    /**
     * @generated
     */
    public List<ArtifactEntity> getArtifacts() {
        return artifacts;
    }

    /**
     * @generated
     */
    public void setArtifacts(List<ArtifactEntity> artifacts) {
        this.artifacts = artifacts;
    }
    
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
    public String getDescription(){
        return description;
    }

    /**
     * @generated
     */
    public void setDescription(String description){
        this.description = description;
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
    public Date getDeadLine(){
        return deadLine;
    }

    /**
     * @generated
     */
    public void setDeadLine(Date deadLine){
        this.deadLine = deadLine;
    }

    /**
     * @generated
     */
    public Date getStartDate(){
        return startDate;
    }

    /**
     * @generated
     */
    public void setStartDate(Date startDate){
        this.startDate = startDate;
    }
    
}
