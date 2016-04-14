/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.api;
import co.edu.uniandes.csw.mpfreelancer.entities.WorkExperienceEntity;
import java.util.List;

/**
 *
 * @author mf.calderon
 */
public interface IWorkExperienceLogic {
    
    public int countExperience();
    public List<WorkExperienceEntity> getExperience();
    public List<WorkExperienceEntity> getExperience(Integer page, Integer maxRecords);
    public WorkExperienceEntity getExperience(Long id);
    public WorkExperienceEntity createExperience(WorkExperienceEntity entity);
    public WorkExperienceEntity updateExperience(WorkExperienceEntity entity);
    public void deleteExperience(Long id);
    public List<WorkExperienceEntity> getByFreelancer(Long id);
    
}
