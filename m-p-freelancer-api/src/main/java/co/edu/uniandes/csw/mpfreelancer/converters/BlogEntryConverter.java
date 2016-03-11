/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.converters;

import co.edu.uniandes.csw.mpfreelancer.dtos.BlogEntryDTO;
import co.edu.uniandes.csw.mpfreelancer.entities.BlogEntryEntity;
import co.edu.uniandes.csw.mpfreelancer.entities.FreelancerEntity;
import java.util.ArrayList;
import java.util.List;

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
    
    public static BlogEntryDTO refEntity2DTO(BlogEntryEntity entity) {
        if (entity != null) {
            BlogEntryDTO dto = new BlogEntryDTO();
            
            dto.setId(entity.getId());
            dto.setTitle(entity.getTitle());
            dto.setSubject(entity.getSubject());
            dto.setContent(entity.getContent());
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
            
            entity.setId(dto.getId());
            
            return entity;
        } else {
            return null;
        }
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
            
            dto.setId(entity.getId());
            dto.setTitle(entity.getTitle());
            dto.setSubject(entity.getSubject());
            dto.setDescription(entity.getDescription());
            dto.setContent(entity.getContent());
            dto.setPublicationDate(entity.getPublicationDate());
            dto.setFreelancer(FreelancerConverter.refEntity2DTO(entity.getFreelancer()));
            
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
            
            entity.setId(dto.getId());
            entity.setTitle(dto.getTitle());
            entity.setSubject(dto.getSubject());
            entity.setDescription(dto.getDescription());
            entity.setContent(dto.getContent());
            entity.setPublicationDate(dto.getPublicationDate());
            entity.setFreelancer(FreelancerConverter.refDTO2Entity(dto.getFreelancer()));

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
    
    public static BlogEntryEntity fullDTO2Entity(BlogEntryDTO dto) {
        if (dto != null) {
            BlogEntryEntity entity = basicDTO2Entity(dto);
            return entity;
        } else {
            return null;
        }
    }
    
    public static List<BlogEntryDTO> listEntity2DTO(List<BlogEntryEntity> entities) {
        List<BlogEntryDTO> dtos = new ArrayList<>();
        if (entities != null) {
            for (BlogEntryEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    public static List<BlogEntryEntity> listDTO2Entity(List<BlogEntryDTO> dtos) {
        List<BlogEntryEntity> entities = new ArrayList<>();
        if (dtos != null) {
            for (BlogEntryDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }
    
    public static BlogEntryEntity childDTO2Entity(BlogEntryDTO dto, FreelancerEntity parent){
        BlogEntryEntity entity = basicDTO2Entity(dto);
        entity.setFreelancer(parent);
        return entity;
    }

    public static List<BlogEntryEntity> childListDTO2Entity(List<BlogEntryDTO> dtos, FreelancerEntity parent) {
        List<BlogEntryEntity> entities = new ArrayList<>();
        if (dtos != null) {
            for (BlogEntryDTO dto : dtos) {
                entities.add(childDTO2Entity(dto, parent));
            }
        }
        return entities;
    }
       
}
