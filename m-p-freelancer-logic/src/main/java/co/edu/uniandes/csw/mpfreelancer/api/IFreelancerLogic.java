package co.edu.uniandes.csw.mpfreelancer.api;

import co.edu.uniandes.csw.mpfreelancer.entities.FreelancerEntity;
import co.edu.uniandes.csw.mpfreelancer.entities.SkillEntity;
import co.edu.uniandes.csw.mpfreelancer.entities.WorkExperienceEntity;
import java.util.List;

public interface IFreelancerLogic {
    public int countFreelancers();
    public List<FreelancerEntity> getFreelancers();
    public List<FreelancerEntity> getFreelancers(Integer page, Integer maxRecords);
    public FreelancerEntity getFreelancer(Long id);
    public FreelancerEntity createFreelancer(FreelancerEntity entity);
    public FreelancerEntity updateFreelancer(FreelancerEntity entity);
    public void deleteFreelancer(Long id);
    public List<SkillEntity> listSkills(Long freelancerId);
    public SkillEntity getSkills(Long freelancerId, Long skillsId);
    public SkillEntity addSkills(Long freelancerId, Long skillsId);
    public List<SkillEntity> replaceSkills(Long freelancerId, List<SkillEntity> list);
    public void removeSkills(Long freelancerId, Long skillsId);
    public List<WorkExperienceEntity> listWorkExperiences(Long freelancerId);
    public WorkExperienceEntity getWorkExperiences(Long freelancerId, Long experienceId);
    //Falta el addExperiences, replaceExperiences, removeExperiences
}
