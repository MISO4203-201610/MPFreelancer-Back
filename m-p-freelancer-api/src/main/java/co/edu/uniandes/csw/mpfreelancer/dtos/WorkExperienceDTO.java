/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.dtos;

import co.edu.uniandes.csw.crud.api.podam.strategy.DateStrategy;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 *
 * @author mf.calderon
 */
@XmlRootElement
public class WorkExperienceDTO {
    private Long id;
    private String projectNamer;
    private String projectDescription;
    @PodamStrategyValue(DateStrategy.class)
    private Date startDate;
    @PodamStrategyValue(DateStrategy.class)
    private Date endDate;
    @PodamExclude
    private List<SkillDTO> expectedskills = new ArrayList<>();
    @PodamExclude
    private CategoryDTO category;
    @PodamExclude
    private ProjectSponsorDTO sponsor;
    @PodamExclude
    private StatusDTO status;

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
    
}
