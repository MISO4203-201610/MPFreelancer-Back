package co.edu.uniandes.csw.mpfreelancer.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.ManyToMany;
import java.util.List;
import java.util.ArrayList;

/**
 * @generated
 */
@Entity
public class SkillEntity extends BaseEntity implements Serializable {

    private String description;
    private String skillLevel;

    @PodamExclude
    @ManyToMany
    private List<FreelancerEntity> freelancers = new ArrayList<>();

    @PodamExclude
    @ManyToMany(mappedBy = "expectedskills")
    private List<ProjectEntity> projects = new ArrayList<>();
    
    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
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
    public List<FreelancerEntity> getFreelancers() {
        return freelancers;
    }

    /**
     * @generated
     */
    public void setFreelancers(List<FreelancerEntity> freelancers) {
        this.freelancers = freelancers;
    }

    /**
     * @generated
     */
    public List<ProjectEntity> getProjects() {
        return projects;
    }

    /**
     * @generated
     */
    public void setProjects(List<ProjectEntity> projects) {
        this.projects = projects;
    }
}
