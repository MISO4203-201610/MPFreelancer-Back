package co.edu.uniandes.csw.mpfreelancer.dtos;

import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;
import java.util.List;
import java.util.ArrayList;

/**
 * @generated
 */
@XmlRootElement
public class ProjectSponsorDTO {

    private Long id;
    private String name;
    private String company;
    private String picture;
    @PodamExclude
    private List<ProjectDTO> projects = new ArrayList<>();

    /**
     * Obtiene el id de un Project Sponsor
     * 
     * @return 
     * @generated
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el id de un Project Sponsor
     * 
     * @param id
     * @generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de un Project Sponsor
     * 
     * @return 
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre de un Project Sponsor
     * 
     * @param name
     * @generated
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene la compañia de un Project Sponsor
     * 
     * @return 
     * @generated
     */
    public String getCompany() {
        return company;
    }

    /**
     * Establece la compañia de un Project Sponsor
     * 
     * @param company
     * @generated
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * Obtiene la fotografía de un Project Sponsor
     * 
     * @return 
     * @generated
     */
    public String getPicture() {
        return picture;
    }

    /**
     * Establece la fotografía de un Project Sponsor
     * 
     * @param picture
     * @generated
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * Obtiene los proyectos de un Project Sponsor
     * 
     * @return 
     * @generated
     */
    public List<ProjectDTO> getProjects() {
        return projects;
    }

    /**
     * Establece los proyectos de un Project Sponsor
     * 
     * @param projects
     * @generated
     */
    public void setProjects(List<ProjectDTO> projects) {
        this.projects = projects;
    }

}
