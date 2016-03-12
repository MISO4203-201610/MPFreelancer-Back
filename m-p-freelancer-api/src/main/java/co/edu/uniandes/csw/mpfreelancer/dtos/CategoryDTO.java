package co.edu.uniandes.csw.mpfreelancer.dtos;

import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;
import java.util.List;
import java.util.ArrayList;

/**
 * @generated
 */
@XmlRootElement
public class CategoryDTO {

    private Long id;
    private String name;
    private String description;
    @PodamExclude
    private List<ProjectDTO> projects = new ArrayList<>();

    /**
     * Establece el id de una categoría.
     * 
     * @param id
     * @generated
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Obtiene el id de una categoría.
     * 
     * @return 
     * @generated
     */
    public Long getId() {
        return id;
    }
    
    /**
     * Establece el nombre de una categoría.
     * 
     * @param name
     * @generated
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene el nombre de una categoría.
     * 
     * @return 
     * @generated
     */
    public String getName() {
        return name;
    }
    
    /**
     * Establece la descripción de una categoría.
     * 
     * @param description
     * @generated
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Obtiene la descripción de una categoría.
     * 
     * @return 
     * @generated
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Estalece la lista de proyectos de una categoría.
     * 
     * @param projects
     * @generated
     */
    public void setProjects(List<ProjectDTO> projects) {
        this.projects = projects;
    }

    /**
     * Obtiene la lista de proyectos de una categoría.
     * 
     * @return 
     * @generated
     */
    public List<ProjectDTO> getProjects() {
        return projects;
    }
}
