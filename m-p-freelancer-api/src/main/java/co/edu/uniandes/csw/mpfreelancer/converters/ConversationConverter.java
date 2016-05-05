/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.converters;
import co.edu.uniandes.csw.mpfreelancer.dtos.ConversationDTO;
import co.edu.uniandes.csw.mpfreelancer.entities.ConversationEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mf.calderon
 */
public abstract class ConversationConverter {
    
    private ConversationConverter() {
    }
    /**
     * Realiza la conversión deConversationEntity a ConversationDTO.
     * Se invoca cuando otra entidad tiene una referencia a WorkExperienceEntity.
     * Entrega únicamente los atributos proprios de la entidad.
     *
     * @param entity instancia de ConversationEntity a convertir
     * @return instancia ConversationDTO con los datos recibidos por parámetro
     * @generated
     */
   public static ConversationDTO refEntity2DTO(ConversationEntity entity) {
        if (entity != null) {
            ConversationDTO dto = new ConversationDTO();
            dto.setId(entity.getId());
            dto.setSubject(entity.getSubject());
            
            return dto;
        } else {
            return null;
        }
    }

    /**
     * Realiza la conversión de ConversationDTO a ConversationEntity Se invoca cuando otro DTO
     * tiene una referencia a ConversationDTO Convierte únicamente el ID ya que es el
     * único atributo necesario para guardar la relación en la base de datos
     *
     * @param dto instancia de ConversationDTO a convertir
     * @return instancia de ConversationEntity con los datos recibidos por parámetro
     * @generated
     */
    public static ConversationEntity refDTO2Entity(ConversationDTO dto) {
        if (dto != null) {
            ConversationEntity entity = new ConversationEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
                }
    }

    /**
     * Convierte una instancia de ConversationEntity a ConversationDTO Se invoca cuando se desea
     * consultar la entidad y sus relaciones muchos a uno o uno a uno
     *
     * @param entity instancia de ConversationEntity a convertir
     * @return Instancia de ConversationDTO con los datos recibidos por parámetro
     * @generated
     */
    private static ConversationDTO basicEntity2DTO(ConversationEntity entity) {
        if (entity != null) {
            ConversationDTO dto = new ConversationDTO();
            dto.setId(entity.getId());
            dto.setSubject(entity.getSubject());
            dto.setProject(ProjectConverter.refEntity2DTO(entity.getProject()));
            dto.setFreelancer(FreelancerConverter.refEntity2DTO(entity.getFreelancer()));
            	    
            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de ConversationDTO a ConversationEntity Se invoca cuando se
     * necesita convertir una instancia de ConversationDTO con los atributos propios de
     * la entidad y con las relaciones uno a uno o muchos a uno
     *
     * @param dto instancia de ConversationDTO a convertir
     * @return Instancia de ConversationEntity creada a partir de los datos de dto
     * @generated
     */
    private static ConversationEntity basicDTO2Entity(ConversationDTO dto) {
        if (dto != null) {
            ConversationEntity entity = new ConversationEntity();
            entity.setId(dto.getId());
            entity.setSubject(dto.getSubject());
            entity.setFreelancer(FreelancerConverter.refDTO2Entity(dto.getFreelancer()));
            entity.setProject(ProjectConverter.refDTO2Entity(dto.getProject()));
                        
            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte instancias de ConversationEntity a ConversationDTO incluyendo sus relaciones
     * Uno a muchos y Muchos a muchos
     *
     * @param entity Instancia de ConversationEntity a convertir
     * @return Instancia de ConversationDTO con los datos recibidos por parámetro
     * @generated
     */
    public static ConversationDTO fullEntity2DTO(ConversationEntity entity) {
        if (entity != null) {
            ConversationDTO dto = basicEntity2DTO(entity);
            dto.setMails(MailConverter.listEntity2DTO(entity.getMails()));
            
            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de ConversationDTO a FreelancerEntity.
     * Incluye todos los atributos de FreelancerEntity.
     *
     * @param dto Instancia de ConversationDTO a convertir
     * @return Instancia de FreelancerEntity con los datos recibidos por parámetro
     * @generated
     */
    public static ConversationEntity fullDTO2Entity(ConversationDTO dto) {
        if (dto != null) {
            ConversationEntity entity = basicDTO2Entity(dto);
            entity.setMails(MailConverter.listDTO2Entity(dto.getMails()));
            
            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte una colección de instancias de ConversationEntity a ConversationDTO. Para cada
     * instancia de ConversationEntity en la lista, invoca basicEntity2DTO y añade el
     * nuevo FreelancerDTO a una nueva lista
     *
     * @param entities Colección de entidades a convertir
     * @return Collección de instancias de ConversationDTO
     * @generated
     */
    public static List<ConversationDTO> listEntity2DTO(List<ConversationEntity> entities) {
        List<ConversationDTO> dtos = new ArrayList<ConversationDTO>();
        if (entities != null) {
            for (ConversationEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    /**
     * Convierte una colección de instancias de ConversationDTO a instancias de
     * ConversationEntity Para cada instancia se invoca el método basicDTO2Entity
     *
     * @param dtos entities Colección de ConversationDTO a convertir
     * @return Collección de instancias de ConversationEntity
     * @generated
     */
    public static List<ConversationEntity> listDTO2Entity(List<ConversationDTO> dtos) {
        List<ConversationEntity> entities = new ArrayList<ConversationEntity>();
        if (dtos != null) {
            for (ConversationDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }
    
}
