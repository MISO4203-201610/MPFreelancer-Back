/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.ejbs;

import co.edu.uniandes.csw.mpfreelancer.api.IBlogEntryLogic;
import co.edu.uniandes.csw.mpfreelancer.entities.BlogEntryEntity;
import co.edu.uniandes.csw.mpfreelancer.persistence.BlogEntryPersistence;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author ef.nobmann10
 */
public class BlogEntryLogic implements IBlogEntryLogic{
    
    @Inject private BlogEntryPersistence persistence;

    @Override
    public int countEntries() {
        return persistence.count();
    }

    @Override
    public List<BlogEntryEntity> getBlogEntries() {
        return persistence.findAll();
    }

    @Override
    public List<BlogEntryEntity> getBlogEntries(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }

    @Override
    public BlogEntryEntity getBlogEntry(Long id) {
        return persistence.find(id);
    }

    @Override
    public BlogEntryEntity createBlogEntry(BlogEntryEntity entity) {
        persistence.create(entity);
        return entity;
    }
    
    //TODO: Posiblemente toque modificar este método.
    @Override
    public BlogEntryEntity updateBlogEntry(BlogEntryEntity entity) {
        BlogEntryEntity newEntity = entity;
        BlogEntryEntity oldEntity = persistence.find(entity.getId());
        return persistence.update(newEntity);
    }

    @Override
    public void deleteBlogEntry(Long id) {
        persistence.delete(id);
    }
    
}
