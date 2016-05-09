package co.edu.uniandes.csw.mpfreelancer.dtos;

import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;
import java.util.Date;
import uk.co.jemos.podam.common.PodamStrategyValue;
import co.edu.uniandes.csw.crud.api.podam.strategy.DateStrategy;
import java.util.List;
import java.util.ArrayList;

/**
 * @generated
 */
@XmlRootElement
public class ProjectDTO {

    private Long id;
    private String name;
    private String description;
    private Integer price;
    @PodamStrategyValue(DateStrategy.class)
    private Date deadLine;
    @PodamStrategyValue(DateStrategy.class)
    private Date publicationDate;
    @PodamStrategyValue(DateStrategy.class)
    private Date startDate;
    @PodamExclude
    private List<SkillDTO> expectedskills = new ArrayList<>();
    @PodamExclude
    private CategoryDTO category;
    @PodamExclude
    private ProjectSponsorDTO sponsor;
    @PodamExclude
    private StatusDTO status;    
    @PodamExclude
    private List<AgreementDTO> agreements;
    @PodamExclude
    private List<ProjectSprintDTO> projectSprints = new ArrayList<>();
    @PodamExclude
    private FreelancerDTO freelancer;
    
    /**
     * Obtiene el id de un proyecto.
     * 
     * @return 
     * @generated
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el id de un proyecto.
     * 
     * @param id
     * @generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de un proyecto.
     * 
     * @return 
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre de un proyecto.
     * 
     * @param name
     * @generated
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene la descripción de un proyecto.
     * 
     * @return 
     * @generated
     */
    public String getDescription() {
        return description;
    }

    /**
     * Establece la descripción de un proyecto.
     * 
     * @param description
     * @generated
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Obtiene el precio de un proyecto.
     * 
     * @return 
     * @generated
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * Establece el precio de un proyecto.
     * 
     * @param price
     * @generated
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * Obtiene la fecha límite para un proyecto.
     * 
     * @return 
     * @generated
     */
    public Date getDeadLine() {
        return deadLine;
    }

    /**
     * Establece la fecha límite para un proyecto.
     * 
     * @param deadline
     * @generated
     */
    public void setDeadLine(Date deadline) {
        this.deadLine = deadline;
    }

    /**
     * Obtiene la fecha de publicación para un proyecto.
     * 
     * @return 
     * @generated
     */
    public Date getPublicationDate() {
        return publicationDate;
    }

    /**
     * Establece la fecha de publicación para un proyecto.
     * 
     * @param publicationdate
     * @generated
     */
    public void setPublicationDate(Date publicationdate) {
        this.publicationDate = publicationdate;
    }

    /**
     * Obtiene la fecha de inicio para un proyecto.
     * 
     * @return 
     * @generated
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Establece la fecha de inicio para un proyecto.
     * 
     * @param startdate
     * @generated
     */
    public void setStartDate(Date startdate) {
        this.startDate = startdate;
    }

    /**
     * Obtiene la categoría de un proyecto.
     * 
     * @return 
     * @generated
     */
    public CategoryDTO getCategory() {
        return category;
    }

    /**
     * Establece la categoría de un proyecto.
     * 
     * @param category
     * @generated
     */
    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    /**
     * Obtiene el sponsor de un proyecto.
     * 
     * @return 
     * @generated
     */
    public ProjectSponsorDTO getSponsor() {
        return sponsor;
    }

    /**
     * Establece el sponsor de un proyecto.
     * 
     * @param sponsor
     * @generated
     */
    public void setSponsor(ProjectSponsorDTO sponsor) {
        this.sponsor = sponsor;
    }

    /**
     * Obtiene el estado de un proyecto.
     * 
     * @return 
     * @generated
     */
    public StatusDTO getStatus() {
        return status;
    }

    /**
     * Establece el estado de un proyecto.
     * 
     * @param status
     * @generated
     */
    public void setStatus(StatusDTO status) {
        this.status = status;
    }

    /**
     * Obtiene las habilidades necesarias para el desarrollo de un proyecto.
     * 
     * @return 
     * @generated
     */
    public List<SkillDTO> getExpectedskills() {
        return expectedskills;
    }

    /**
     * Establece las habilidades necesarias para el desarrollo de un proyecto.
     * 
     * @param expectedskills
     * @generated
     */
    public void setExpectedskills(List<SkillDTO> expectedskills) {
        this.expectedskills = expectedskills;
    }

    public List<AgreementDTO> getAgreements() {
        return agreements;
    }

    public void setAgreements(List<AgreementDTO> agreements) {
        this.agreements = agreements;
    }

    /**
     * Obtiene los sprints de un proyecto.
     * 
     * @return 
     * @generated
     */
    public List<ProjectSprintDTO> getProjectSprints() {
        return projectSprints;
    }

    /**
     * Establece los sprints de un proyecto.
     * 
     * @param projectSprints
     * @generated
     */
    public void setProjectSprints(List<ProjectSprintDTO> projectSprints) {
        this.projectSprints = projectSprints;
    }   
    
    /**
     * Obtiene el freelancer de un proyecto.
     * 
     * @return 
     * @generated
     */
    public FreelancerDTO getFreelancer() {
        return freelancer;
    }

    /**
     * Establece el freelancer de un proyecto.
     * 
     * @param freelancer
     * @generated
     */
    public void setFreelancer(FreelancerDTO freelancer) {
        this.freelancer = freelancer;
    }   
}
