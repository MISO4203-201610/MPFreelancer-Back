package co.edu.uniandes.csw.mpfreelancer.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import co.edu.uniandes.csw.mpfreelancer.entities.ProjectSponsorEntity;
import co.edu.uniandes.csw.crud.spi.persistence.CrudPersistence;

/**
 * @generated
 */
@Stateless
public class ProjectSponsorPersistence extends CrudPersistence<ProjectSponsorEntity> {

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
    protected Class<ProjectSponsorEntity> getEntityClass() {
        return ProjectSponsorEntity.class;
    }
}
