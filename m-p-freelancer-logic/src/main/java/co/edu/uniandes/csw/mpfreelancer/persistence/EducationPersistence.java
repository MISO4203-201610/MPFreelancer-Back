package co.edu.uniandes.csw.mpfreelancer.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import co.edu.uniandes.csw.mpfreelancer.entities.EducationEntity;
import co.edu.uniandes.csw.crud.spi.persistence.CrudPersistence;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @generated
 */
@Stateless
public class EducationPersistence extends CrudPersistence<EducationEntity> {

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
    protected Class<EducationEntity> getEntityClass() {
        return EducationEntity.class;
    }
    
    public List<EducationEntity> getByFreelancer(Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id",  id );
        return executeListNamedQuery("Education.getByFreelancer", params);
    }
    
    
    
    
}
