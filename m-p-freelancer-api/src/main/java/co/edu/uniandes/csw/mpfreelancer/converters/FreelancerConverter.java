package co.edu.uniandes.csw.mpfreelancer.converters;

import co.edu.uniandes.csw.mpfreelancer.dtos.FreelancerDTO;
import co.edu.uniandes.csw.mpfreelancer.entities.FreelancerEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * @generated
 */
public abstract class FreelancerConverter {

    /**
     * Constructor privado para evitar la creación del constructor implícito de Java
     * @generated
     */
    private FreelancerConverter() {
    }

    /**
     * Realiza la conversión de FreelancerEntity a FreelancerDTO.
     * Se invoca cuando otra entidad tiene una referencia a FreelancerEntity.
     * Entrega únicamente los atributos proprios de la entidad.
     *
     * @param entity instancia de FreelancerEntity a convertir
     * @return instancia de FreelancerDTO con los datos recibidos por parámetro
     * @generated
     */
    public static FreelancerDTO refEntity2DTO(FreelancerEntity entity) {
        if (entity != null) {
            FreelancerDTO dto = new FreelancerDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setRate(entity.getRate());
            dto.setBithday(entity.getBithday());
            dto.setPicture(entity.getPicture());

            return dto;
        } else {
            return null;
        }
    }

    /**
     * Realiza la conversión de FreelancerDTO a FreelancerEntity Se invoca cuando otro DTO
     * tiene una referencia a FreelancerDTO Convierte únicamente el ID ya que es el
     * único atributo necesario para guardar la relación en la base de datos
     *
     * @param dto instancia de FreelancerDTO a convertir
     * @return instancia de FreelancerEntity con los datos recibidos por parámetro
     * @generated
     */
    public static FreelancerEntity refDTO2Entity(FreelancerDTO dto) {
        if (dto != null) {
            FreelancerEntity entity = new FreelancerEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
                }
    }

    /**
     * Convierte una instancia de FreelancerEntity a FreelancerDTO Se invoca cuando se desea
     * consultar la entidad y sus relaciones muchos a uno o uno a uno
     *
     * @param entity instancia de FreelancerEntity a convertir
     * @return Instancia de FreelancerDTO con los datos recibidos por parámetro
     * @generated
     */
    private static FreelancerDTO basicEntity2DTO(FreelancerEntity entity) {
        if (entity != null) {
            FreelancerDTO dto = new FreelancerDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setRate(entity.getRate());
            dto.setBithday(entity.getBithday());
            dto.setPicture(entity.getPicture());
            dto.setCurriculum(CurriculumConverter.refEntity2DTO(entity.getCurriculum()));

            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de FreelancerDTO a FreelancerEntity Se invoca cuando se
     * necesita convertir una instancia de FreelancerDTO con los atributos propios de
     * la entidad y con las relaciones uno a uno o muchos a uno
     *
     * @param dto instancia de FreelancerDTO a convertir
     * @return Instancia de FreelancerEntity creada a partir de los datos de dto
     * @generated
     */
    private static FreelancerEntity basicDTO2Entity(FreelancerDTO dto) {
        if (dto != null) {
            FreelancerEntity entity = new FreelancerEntity();
            entity.setId(dto.getId());
            entity.setName(dto.getName());
            entity.setRate(dto.getRate());
            entity.setBithday(dto.getBithday());
            entity.setPicture(dto.getPicture());
            entity.setCurriculum(CurriculumConverter.refDTO2Entity(dto.getCurriculum()));
            
            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte instancias de FreelancerEntity a FreelancerDTO incluyendo sus relaciones
     * Uno a muchos y Muchos a muchos
     *
     * @param entity Instancia de FreelancerEntity a convertir
     * @return Instancia de FreelancerDTO con los datos recibidos por parámetro
     * @generated
     */
    public static FreelancerDTO fullEntity2DTO(FreelancerEntity entity) {
        if (entity != null) {
            FreelancerDTO dto = basicEntity2DTO(entity);
            dto.setSkills(SkillConverter.listEntity2DTO(entity.getSkills()));
            dto.setTitles(EducationConverter.listEntity2DTO(entity.getTitles()));
            dto.setAgreements(AgreementConverter.listEntity2DTO(entity.getAgreements()));
            dto.setCurriculum(CurriculumConverter.refEntity2DTO(entity.getCurriculum()));
            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de FreelancerDTO a FreelancerEntity.
     * Incluye todos los atributos de FreelancerEntity.
     *
     * @param dto Instancia de FreelancerDTO a convertir
     * @return Instancia de FreelancerEntity con los datos recibidos por parámetro
     * @generated
     */
    public static FreelancerEntity fullDTO2Entity(FreelancerDTO dto) {
        if (dto != null) {
            FreelancerEntity entity = basicDTO2Entity(dto);
            entity.setSkills(SkillConverter.listDTO2Entity(dto.getSkills()));
            entity.setTitles(EducationConverter.childListDTO2Entity(dto.getTitles(), entity));
            entity.setAgreements(AgreementConverter.listDTO2Entity(dto.getAgreements()));
            entity.setCurriculum(CurriculumConverter.refDTO2Entity(dto.getCurriculum()));
            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte una colección de instancias de FreelancerEntity a FreelancerDTO. Para cada
     * instancia de FreelancerEntity en la lista, invoca basicEntity2DTO y añade el
     * nuevo FreelancerDTO a una nueva lista
     *
     * @param entities Colección de entidades a convertir
     * @return Collección de instancias de FreelancerDTO
     * @generated
     */
    public static List<FreelancerDTO> listEntity2DTO(List<FreelancerEntity> entities) {
        List<FreelancerDTO> dtos = new ArrayList<FreelancerDTO>();
        if (entities != null) {
            for (FreelancerEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    /**
     * Convierte una colección de instancias de FreelancerDTO a instancias de
     * FreelancerEntity Para cada instancia se invoca el método basicDTO2Entity
     *
     * @param dtos entities Colección de FreelancerDTO a convertir
     * @return Collección de instancias de FreelancerEntity
     * @generated
     */
    public static List<FreelancerEntity> listDTO2Entity(List<FreelancerDTO> dtos) {
        List<FreelancerEntity> entities = new ArrayList<FreelancerEntity>();
        if (dtos != null) {
            for (FreelancerDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }

}
