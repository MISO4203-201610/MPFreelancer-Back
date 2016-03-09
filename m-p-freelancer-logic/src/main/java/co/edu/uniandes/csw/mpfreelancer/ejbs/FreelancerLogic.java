package co.edu.uniandes.csw.mpfreelancer.ejbs;

import co.edu.uniandes.csw.mp.ann.MPLoCAnn;
import co.edu.uniandes.csw.mpfreelancer.api.IFreelancerLogic;
import co.edu.uniandes.csw.mpfreelancer.entities.FreelancerEntity;
import co.edu.uniandes.csw.mpfreelancer.persistence.FreelancerPersistence;
import co.edu.uniandes.csw.mpfreelancer.entities.SkillEntity;
import co.edu.uniandes.csw.mpfreelancer.api.ISkillLogic;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @generated
 */
@Stateless
public class FreelancerLogic implements IFreelancerLogic {

    @Inject private FreelancerPersistence persistence;

    @Inject private ISkillLogic skillLogic;
    
    /**
     * @generated
     */
    @Override
    public int countFreelancers() {
        return persistence.count();
    }

    /**
     * @generated
     */
    @Override
    public List<FreelancerEntity> getFreelancers() {
        return persistence.findAll();
    }

    /**
     * @generated
     */
    @Override
    public List<FreelancerEntity> getFreelancers(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }
    /**
     * @generated
     */
    @Override
    public FreelancerEntity getFreelancer(Long id) {
        return persistence.find(id);
    }

    /**
     * @generated
     */
    @Override
    public FreelancerEntity createFreelancer(FreelancerEntity entity) {
        persistence.create(entity);
        return entity;
    }

    /**
     * @generated
     */
    @Override
    public FreelancerEntity updateFreelancer(FreelancerEntity entity) {
        FreelancerEntity newEntity = entity;
        FreelancerEntity oldEntity = persistence.find(entity.getId());
        newEntity.setSkills(oldEntity.getSkills());
        return persistence.update(newEntity);
    }

    /**
     * @generated
     */
    @Override
    public void deleteFreelancer(Long id) {
        persistence.delete(id);
    }

    /**
     * @generated
     */
    @Override
    public List<SkillEntity> listSkills(Long freelancerId) {
        return persistence.find(freelancerId).getSkills();
    }

    /**
     * @generated
     */
    @Override
    public SkillEntity getSkills(Long freelancerId, Long skillsId) {
        List<SkillEntity> list = persistence.find(freelancerId).getSkills();
        SkillEntity skillsEntity = new SkillEntity();
        skillsEntity.setId(skillsId);
        int index = list.indexOf(skillsEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }

    /**
     * @generated
     */
    @Override
    public SkillEntity addSkills(Long freelancerId, Long skillsId) {
        skillLogic.addFreelancers(skillsId, freelancerId);
        return skillLogic.getSkill(skillsId);
    }

    /**
     * @generated
     */
    @Override
    public List<SkillEntity> replaceSkills(Long freelancerId, List<SkillEntity> list) {
        FreelancerEntity freelancerEntity = persistence.find(freelancerId);
        List<SkillEntity> skillList = skillLogic.getSkills();
        for (SkillEntity skill : skillList) {
            if (list.contains(skill)) {
                if (!skill.getFreelancers().contains(freelancerEntity)) {
                    skillLogic.addFreelancers(skill.getId(), freelancerId);
                }
            } else {
                skillLogic.removeFreelancers(skill.getId(), freelancerId);
            }
        }
        freelancerEntity.setSkills(list);
        return freelancerEntity.getSkills();
    }

    /**
     * @generated
     */
    @Override
    public void removeSkills(Long freelancerId, Long skillsId) {
        skillLogic.removeFreelancers(skillsId, freelancerId);
    }
    
 
}
