/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.persistence;

import co.edu.uniandes.csw.crud.spi.persistence.CrudPersistence;
import co.edu.uniandes.csw.mpfreelancer.entities.BlogEntryEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ef.nobmann10
 */
@Stateless
public class BlogEntryPersistence extends CrudPersistence<BlogEntryEntity> {
    
    @PersistenceContext(unitName="MPFreelancerPU")
    protected EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected Class<BlogEntryEntity> getEntityClass() {
        return BlogEntryEntity.class;
    }
    
}
