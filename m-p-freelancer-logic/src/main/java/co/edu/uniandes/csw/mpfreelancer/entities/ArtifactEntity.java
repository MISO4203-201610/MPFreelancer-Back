package co.edu.uniandes.csw.mpfreelancer.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.ManyToOne;

/**
 * @generated
 */
@Entity
public class ArtifactEntity extends BaseEntity implements Serializable {

    private String description;
    
    private String path;
    
    @PodamExclude
    @ManyToOne
    private ProjectSprintEntity projectSprint;

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
    public String getPath(){
        return path;
    }

    /**
     * @generated
     */
    public void setPath(String path){
        this.path = path;
    }
    
    /**
     * @generated
     */
    public ProjectSprintEntity getProjectSprint(){
        return projectSprint;
    }

    /**
     * @generated
     */
    public void setProjectSprint(ProjectSprintEntity name){
        this.projectSprint = projectSprint;
    }
}
