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
    private AgreementDTO agreement;

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
    public String getDescription() {
        return description;
    }

    /**
     * @generated
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @generated
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * @generated
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * @generated
     */
    public Date getDeadLine() {
        return deadLine;
    }

    /**
     * @generated
     */
    public void setDeadLine(Date deadline) {
        this.deadLine = deadline;
    }

    /**
     * @generated
     */
    public Date getPublicationDate() {
        return publicationDate;
    }

    /**
     * @generated
     */
    public void setPublicationDate(Date publicationdate) {
        this.publicationDate = publicationdate;
    }

    /**
     * @generated
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @generated
     */
    public void setStartDate(Date startdate) {
        this.startDate = startdate;
    }

    /**
     * @generated
     */
    public CategoryDTO getCategory() {
        return category;
    }

    /**
     * @generated
     */
    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    /**
     * @generated
     */
    public ProjectSponsorDTO getSponsor() {
        return sponsor;
    }

    /**
     * @generated
     */
    public void setSponsor(ProjectSponsorDTO sponsor) {
        this.sponsor = sponsor;
    }

    /**
     * @generated
     */
    public StatusDTO getStatus() {
        return status;
    }

    /**
     * @generated
     */
    public void setStatus(StatusDTO status) {
        this.status = status;
    }

    /**
     * @generated
     */
    public List<SkillDTO> getExpectedskills() {
        return expectedskills;
    }

    /**
     * @generated
     */
    public void setExpectedskills(List<SkillDTO> expectedskills) {
        this.expectedskills = expectedskills;
    }

    public AgreementDTO getAgreement() {
        return agreement;
    }

    public void setAgreement(AgreementDTO agreement) {
        this.agreement = agreement;
    }    
}
