/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.api;

import co.edu.uniandes.csw.mpfreelancer.entities.CurriculumEntity;
import java.util.List;

/**
 *
 * @author Fernando
 */
public interface ICurriculumLogic {
    public int countCurriculums();
    public List<CurriculumEntity> getCurriculums();
    public List<CurriculumEntity> getCurriculums(Integer page, Integer maxRecords);
    public CurriculumEntity getCurriculum(Long id);
    public CurriculumEntity createCurriculum(CurriculumEntity entity);
    public CurriculumEntity updateCurriculum(CurriculumEntity entity);
    public void deleteCurriculum(Long id);
    public List<CurriculumEntity> getByFreelancer(Long id);
}
