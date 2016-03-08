package co.edu.uniandes.csw.mpfreelancer.dtos;

import uk.co.jemos.podam.common.PodamExclude;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @generated
 */
@XmlRootElement
public class ArtifactDTO {

    private Long id;
    private String name;
    
    private String description;
    
    private String path;
    
    @PodamExclude
    private ProjectSprintDTO projectSprint;

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
    public void setDescription(String description){
        this.description = description;
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
    public ProjectSprintDTO getProjectSprint(){
        return projectSprint;
    }

    /**
     * @generated
     */
    public void setProjectSprint(ProjectSprintDTO projectSprint){
        this.projectSprint = projectSprint;
    }
}
