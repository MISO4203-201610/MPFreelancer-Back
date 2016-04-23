/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.api;
import co.edu.uniandes.csw.mpfreelancer.entities.EducationEntity;
import java.util.List;

/**
 *
 * @author mf.calderon
 */
public interface IEducationLogic {
   
    public int countTitles();
    public List<EducationEntity> getTitles();
    public List<EducationEntity> getTitles(Integer page, Integer maxRecords);
    public EducationEntity getTitles(Long id);
    public EducationEntity createTitles(EducationEntity entity);
    public EducationEntity updateTitles(EducationEntity entity);
    public void deleteTitles(Long id);
    public List<EducationEntity> getByFreelancer(Long id);
}
