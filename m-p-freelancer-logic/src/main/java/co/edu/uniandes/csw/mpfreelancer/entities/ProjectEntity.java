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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;
import java.util.ArrayList;

/**
 * @generated
 */
@Entity
public class ProjectEntity extends BaseEntity implements Serializable {

    private String description;

    private Integer price;

    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date deadLine;

    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date publicationDate;

    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date startDate;

    @PodamExclude
    @ManyToOne
    private StatusEntity status;

    @PodamExclude
    @ManyToOne
    private CategoryEntity category;

    @PodamExclude
    @ManyToOne
    private ProjectSponsorEntity sponsor;

    @PodamExclude
    @ManyToMany
    private List<SkillEntity> expectedskills = new ArrayList<>();   

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
    public Date getPublicationDate(){
        return publicationDate;
    }

    /**
     * @generated
     */
    public void setPublicationDate(Date publicationDate){
        this.publicationDate = publicationDate;
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

    /**
     * @generated
     */
    public StatusEntity getStatus() {
        return status;
    }

    /**
     * @generated
     */
    public void setStatus(StatusEntity status) {
        this.status = status;
    }

    /**
     * @generated
     */
    public CategoryEntity getCategory() {
        return category;
    }

    /**
     * @generated
     */
    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    /**
     * @generated
     */
    public ProjectSponsorEntity getSponsor() {
        return sponsor;
    }

    /**
     * @generated
     */
    public void setSponsor(ProjectSponsorEntity sponsor) {
        this.sponsor = sponsor;
    }

    /**
     * @generated
     */
    public List<SkillEntity> getExpectedskills() {
        return expectedskills;
    }

    /**
     * @generated
     */
    public void setExpectedskills(List<SkillEntity> expectedskills) {
        this.expectedskills = expectedskills;
    }
}
