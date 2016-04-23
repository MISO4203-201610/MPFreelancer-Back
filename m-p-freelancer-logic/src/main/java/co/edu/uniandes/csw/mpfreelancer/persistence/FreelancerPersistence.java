package co.edu.uniandes.csw.mpfreelancer.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import co.edu.uniandes.csw.mpfreelancer.entities.FreelancerEntity;
import co.edu.uniandes.csw.crud.spi.persistence.CrudPersistence;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @generated
 */
@Stateless
public class FreelancerPersistence extends CrudPersistence<FreelancerEntity> {

    @PersistenceContext(unitName="MPFreelancerPU")
    protected EntityManager em;

    /**
     * @generated
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * @generated
     */
    @Override
    protected Class<FreelancerEntity> getEntityClass() {
        return FreelancerEntity.class;
    }
    
    public List<FreelancerEntity> unSkill(Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return executeListNamedQuery("Freelancer.unSkill", params);
    }
    public List<FreelancerEntity> totalSkills(Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return executeListNamedQuery("Freelancer.totalSkills", params);
    }
    
    
}
