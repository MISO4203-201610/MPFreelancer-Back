/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.api;

import co.edu.uniandes.csw.mpfreelancer.entities.BlogEntryEntity;
import java.util.List;

/**
 *
 * @author ef.nobmann10
 */
public interface IBlogEntryLogic {
    public int countEntries();
    public List<BlogEntryEntity> getBlogEntries();
    public List<BlogEntryEntity> getBlogEntries(Integer page, Integer maxRecords);
    public BlogEntryEntity getBlogEntry(Long id);
    public BlogEntryEntity createBlogEntry(BlogEntryEntity entity);
    public BlogEntryEntity updateBlogEntry(BlogEntryEntity entity);
    public void deleteBlogEntry(Long id);
}
