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
    
    public int countWorkExperiences();
    public List<WorkExperienceEntity> getWorkExperiences();
    public List<WorkExperienceEntity> getWorkExperiences(Integer page, Integer maxRecords);
    public WorkExperienceEntity getWorkExperiences(Long id);
    public WorkExperienceEntity createWorkExperiences(WorkExperienceEntity entity);
    public WorkExperienceEntity updateWorkExperiences(WorkExperienceEntity entity);
    public void deleteWorkExperiences(Long id);
    
}
