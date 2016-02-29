package co.edu.uniandes.csw.mpfreelancer.dtos;

import java.util.Date;
import uk.co.jemos.podam.common.PodamStrategyValue;
import co.edu.uniandes.csw.crud.api.podam.strategy.DateStrategy;
import uk.co.jemos.podam.common.PodamExclude;
import java.util.List;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @generated
 */
@XmlRootElement
public class ProjectSprintDTO {

    private Long id;
    
    private String name;
    
    private String description;

    private Integer price;
    
    @PodamStrategyValue(DateStrategy.class)
    private Date deadLine;

    @PodamStrategyValue(DateStrategy.class)
    private Date startDate;

    @PodamExclude
    private List<ArtifactDTO> artifacts = new ArrayList<>();

    @PodamExclude
    private ProjectDTO project;

    /**
     * @generated
     */
    public Long getId() {
        return id;
    }

    /**
     * @generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * @generated
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * @generated
     */
    public List<ArtifactDTO> getArtifacts() {
        return artifacts;
    }

    /**
     * @generated
     */
    public void setArtifacts(List<ArtifactDTO> artifacts) {
        this.artifacts = artifacts;
    }
    
    /**
     * @generated
     */
    public ProjectDTO getProject(){
        return project;
    }

    /**
     * @generated
     */
    public void setProject(ProjectDTO project){
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
