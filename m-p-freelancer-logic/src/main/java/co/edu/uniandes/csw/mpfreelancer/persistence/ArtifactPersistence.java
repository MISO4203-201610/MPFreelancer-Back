/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.persistence;

import co.edu.uniandes.csw.crud.spi.persistence.CrudPersistence;
import co.edu.uniandes.csw.mpfreelancer.entities.ArtifactEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Alex Chacon (av.chacon10@uniandes.edu.co)
 */
@Stateless
public class ArtifactPersistence extends CrudPersistence<ArtifactEntity>{
    @PersistenceContext(unitName="MPFreelancerPU")
    protected EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected Class<ArtifactEntity> getEntityClass() {
        return ArtifactEntity.class;
    }
    
     /**
     * @param projectSprintId project identification
     * @return  count of sprints associated with the project
     */
    public int count(Long projectSprintId) {
        Query count = this.em.createQuery("select count(u) from ArtifactEntity u where u.projectSprint.id=" + projectSprintId);
        return Integer.parseInt(count.getSingleResult().toString());
    }
    
    /**
     * Retrieves a collection of all instances of handled entity
     * @param projectSprintId project identification
     * @return Colecction of instances of handled entity
     */
    public List<ArtifactEntity> findAll(Long projectSprintId) 
    {
        Query q = this.em.createQuery("select u from ArtifactEntity u where u.projectSprint.id=" + projectSprintId);
        return q.getResultList();
    }
    
    /**
     * Retrieves a collection of all instances of handled entity allowing pagination.
     * @param page Number of page to retrieve
     * @param maxRecords Number of records per page
     * @param projectSprintId project identification
     * @return Colecction of instances of handled entity
     */
    public List<ArtifactEntity> findAll(Long projectSprintId, Integer page, Integer maxRecords) {
        Query q = getEntityManager().createQuery("select u from ArtifactEntity u where u.projectSprint.id=" + projectSprintId);
        if (page != null && maxRecords != null) {
            q.setFirstResult((page - 1) * maxRecords);
            q.setMaxResults(maxRecords);
        }
        return q.getResultList();
    }
}