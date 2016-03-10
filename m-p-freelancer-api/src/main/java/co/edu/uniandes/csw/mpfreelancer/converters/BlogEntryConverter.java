/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.converters;

import co.edu.uniandes.csw.mpfreelancer.dtos.BlogEntryDTO;
import co.edu.uniandes.csw.mpfreelancer.entities.BlogEntryEntity;

/**
 *
 * @author ef.nobmann10
 */
public abstract class BlogEntryConverter {
    
    /**
     * Constructor privado para evitar la creación del constructor implícito de Java
     * @generated
     */
    private BlogEntryConverter() {
    }
    
    /**
     * Realiza la conversión de BlogEntryEntity a BlogEntryDTO.
     *
     * @param entity instancia de BlogEntryEntity a convertir
     * @return instancia de BlogEntryDTO con los datos recibidos por parámetro
     */
    public static BlogEntryDTO basicEntity2DTO(BlogEntryEntity entity) {
        if (entity != null) {
            
            BlogEntryDTO dto = new BlogEntryDTO();
            
            dto.setTitle(entity.getTitle());
            dto.setSubject(entity.getSubject());
            dto.setDescription(entity.getDescription());
            dto.setContent(entity.getContent());
            dto.setPublicationDate(entity.getPublicationDate());
            
            return dto;
        } else {
            return null;
        }
    }
    
    
    /**
     * Realiza la conversión de BlogEntryDTO a BlogEntryEntity.
     *
     * @param dto instancia de BlogEntryDTO a convertir
     * @return instancia de BlogEntryEntity con los datos recibidos por parámetro
     */
    public static BlogEntryEntity basicDTO2Entity(BlogEntryDTO dto) {
        if (dto != null) {
            BlogEntryEntity entity = new BlogEntryEntity();
            
            entity.setTitle(dto.getTitle());
            entity.setSubject(dto.getSubject());
            entity.setDescription(dto.getDescription());
            entity.setContent(dto.getContent());
            entity.setPublicationDate(dto.getPublicationDate());

            return entity;
        } else {
            return null;
        }
    }
    
    public static BlogEntryDTO refEntity2DTO(BlogEntryEntity entity) {
        if (entity != null) {
            BlogEntryDTO dto = new BlogEntryDTO();
            
            dto.setTitle(entity.getTitle());
            dto.setDescription(entity.getDescription());
            dto.setPublicationDate(entity.getPublicationDate());

            return dto;
        } else {
            return null;
        }
    }
    
    public static BlogEntryEntity refDTO2Entity(BlogEntryDTO dto) {
        if (dto != null) {
            BlogEntryEntity entity = new BlogEntryEntity();
            
            entity.setTitle(dto.getTitle());
            entity.setDescription(dto.getDescription());
            entity.setPublicationDate(dto.getPublicationDate());
            
            return entity;
        } else {
            return null;
        }
    }
    
    public static BlogEntryEntity fullDTO2Entity(BlogEntryDTO dto) {
        if (dto != null) {
            BlogEntryEntity entity = basicDTO2Entity(dto);
            return entity;
        } else {
            return null;
        }
    }
    
    public static BlogEntryDTO fullEntity2DTO(BlogEntryEntity entity) {
        if (entity != null) {
            BlogEntryDTO dto = basicEntity2DTO(entity);
            return dto;
        } else {
            return null;
        }
    }
       
}
