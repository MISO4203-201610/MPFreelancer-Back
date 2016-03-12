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
import javax.persistence.OneToMany;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.OneToOne;

/**
 * @generated
 */
@Entity
public class FreelancerEntity extends BaseEntity implements Serializable {

    private Integer rate;

    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date bithday;

    private String picture;

    @OneToMany(mappedBy = "freelancer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EducationEntity> titles = new ArrayList<>();

    @OneToMany(mappedBy = "freelancer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BlogEntryEntity> blogEntries = new ArrayList<>();

    @PodamExclude
    @ManyToMany(mappedBy = "freelancers")
    private List<SkillEntity> skills = new ArrayList<>();

    @PodamExclude
    @ManyToMany
    private List<FreelancerTeamEntity> freelancerTeams = new ArrayList<>();

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FreelancerTeamEntity> myTeams = new ArrayList<>();

    @OneToMany(mappedBy = "freelancer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkExperienceEntity> experiences = new ArrayList<>();

    @OneToMany(mappedBy = "freelancer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AgreementEntity> agreements = new ArrayList<>();
    
//    @PodamExclude
    @OneToOne(mappedBy = "freelancer", cascade = CascadeType.ALL, orphanRemoval = true)
    private CurriculumEntity curriculum;

    /**
     * @generated
     */
    public List<WorkExperienceEntity> getWorkExperiences() {
        return experiences;
    }

    /**
     * @generated
     */
    public void setWorkExperiences(List<WorkExperienceEntity> experiences) {
        this.experiences = experiences;
    }

    /**
     * @generated
     */
    public List<AgreementEntity> getAgreements() {
        return agreements;
    }

    /**
     * @generated
     */
    public void setAgreements(List<AgreementEntity> agreements) {
        this.agreements = agreements;
    }

    /**
     * @generated
     */
    public CurriculumEntity getCurriculum() {
        return curriculum;
    }

    /**
     * @generated
     */
    public void setCurriculum(CurriculumEntity curriculum) {
        this.curriculum = curriculum;
    }

    /**
     * @generated
     */
    public List<BlogEntryEntity> getBlogEntries() {
        return blogEntries;
    }

    /**
     * @generated
     */
    public void setBlogEntries(List<BlogEntryEntity> blogEntries) {
        this.blogEntries = blogEntries;
    }

    /**
     * @generated
     */
    public List<FreelancerTeamEntity> getMyTeams() {
        return myTeams;
    }

    /**
     * @generated
     */
    public void setMyTeams(List<FreelancerTeamEntity> myTeams) {
        this.myTeams = myTeams;
    }

    /**
     * @generated
     */
    public Integer getRate() {
        return rate;
    }

    /**
     * @generated
     */
    public void setRate(Integer rate) {
        this.rate = rate;
    }

    /**
     * @generated
     */
    public Date getBithday() {
        return bithday;
    }

    /**
     * @generated
     */
    public void setBithday(Date bithday) {
        this.bithday = bithday;
    }

    /**
     * @generated
     */
    public String getPicture() {
        return picture;
    }

    /**
     * @generated
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * @generated
     */
    public List<EducationEntity> getTitles() {
        return titles;
    }

    /**
     * @generated
     */
    public void setTitles(List<EducationEntity> titles) {
        this.titles = titles;
    }

    /**
     * @generated
     */
    public List<SkillEntity> getSkills() {
        return skills;
    }

    /**
     * @generated
     */
    public void setSkills(List<SkillEntity> skills) {
        this.skills = skills;
    }

    /**
     * @generated
     */
    public List<FreelancerTeamEntity> getFreelancerTeams() {
        return freelancerTeams;
    }

    /**
     * @generated
     */
    public void setFreelancers(List<FreelancerTeamEntity> freelancerTeams) {
        this.freelancerTeams = freelancerTeams;
    }
}
