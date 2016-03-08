package co.edu.uniandes.csw.mpfreelancer.entities;

import co.edu.uniandes.csw.crud.api.podam.strategy.DateStrategy;
import java.io.Serializable;
import javax.persistence.Entity;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import uk.co.jemos.podam.common.PodamExclude;
import java.util.Date;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 * @generated
 */
@Entity
public class BlogEntryEntity extends BaseEntity implements Serializable {

    private String title;
    private String subject;
    private String description;
    private String content;
    
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date publicationDate;

    @PodamExclude
    @ManyToOne
    private FreelancerEntity author;

    /**
     * @generated
     */
    public FreelancerEntity getAuthor(){
        return author;
    }
    
     /**
     * @generated
     */
    public void setAuthor(FreelancerEntity author){
        this.author = author;
    }
    
    /**
     * @generated
     */
    public Date getPublicationDate(){
        return publicationDate;
    }

    /**
     * @generated
     */
    public void setPublicationDate(Date publicationDate){
        this.publicationDate = publicationDate;
    }
    
    /**
     * @generated
     */
    public String getContent(){
        return content;
    }

    /**
     * @generated
     */
    public void setContent(String content){
        this.content = content;
    }
    
    /**
     * @generated
     */
    public String getSubject(){
        return subject;
    }

    /**
     * @generated
     */
    public void setSubject(String subject){
        this.subject = subject;
    }
    
    /**
     * @generated
     */
    public String getDescription(){
        return description;
    }

    /**
     * @generated
     */
    public void setDescription(String description){
        this.description = description;
    }
    
    /**
     * @generated
     */
    public String getTitle(){
        return title;
    }

    /**
     * @generated
     */
    public void setTitle(String title){
        this.title = title;
    }
    
    @Override
    public boolean equals(Object obj){
        return obj == this;
    }
}
