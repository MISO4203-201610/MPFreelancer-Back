package co.edu.uniandes.csw.mpfreelancer.converters;

import co.edu.uniandes.csw.mpfreelancer.dtos.ProjectDTO;
import co.edu.uniandes.csw.mpfreelancer.entities.ProjectEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * @generated
 */
public abstract class ProjectConverter {

    /**
     * Constructor privado para evitar la creación del constructor implícito de Java
     * @generated
     */
    private ProjectConverter() {
    }

    /**
     * Realiza la conversión de ProjectEntity a ProjectDTO.
     * Se invoca cuando otra entidad tiene una referencia a ProjectEntity.
     * Entrega únicamente los atributos proprios de la entidad.
     *
     * @param entity instancia de ProjectEntity a convertir
     * @return instancia de ProjectDTO con los datos recibidos por parámetro
     * @generated
     */
    public static ProjectDTO refEntity2DTO(ProjectEntity entity) {
        if (entity != null) {
            ProjectDTO dtoRef = new ProjectDTO();
            dtoRef.setId(entity.getId());
            dtoRef.setName(entity.getName());
            dtoRef.setDescription(entity.getDescription());
            dtoRef.setPrice(entity.getPrice());
            dtoRef.setDeadLine(entity.getDeadLine());
            dtoRef.setPublicationDate(entity.getPublicationDate());
            dtoRef.setStartDate(entity.getStartDate());

            return dtoRef;
        } else {
            return null;
        }
    }

    /**
     * Realiza la conversión de ProjectDTO a ProjectEntity Se invoca cuando otro DTO
     * tiene una referencia a ProjectDTO Convierte únicamente el ID ya que es el
     * único atributo necesario para guardar la relación en la base de datos
     *
     * @param dto instancia de ProjectDTO a convertir
     * @return instancia de ProjectEntity con los datos recibidos por parámetro
     * @generated
     */
    public static ProjectEntity refDTO2Entity(ProjectDTO dto) {
        if (dto != null) {
            ProjectEntity entity = new ProjectEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de ProjectEntity a ProjectDTO Se invoca cuando se desea
     * consultar la entidad y sus relaciones muchos a uno o uno a uno
     *
     * @param entity instancia de ProjectEntity a convertir
     * @return Instancia de ProjectDTO con los datos recibidos por parámetro
     * @generated
     */
    private static ProjectDTO basicEntity2DTO(ProjectEntity entity) {
        if (entity != null) {
            ProjectDTO dtoBas = new ProjectDTO();
            dtoBas.setId(entity.getId());
            dtoBas.setName(entity.getName());
            dtoBas.setDescription(entity.getDescription());
            dtoBas.setPrice(entity.getPrice());
            dtoBas.setDeadLine(entity.getDeadLine());
            dtoBas.setPublicationDate(entity.getPublicationDate());
            dtoBas.setStartDate(entity.getStartDate());
            dtoBas.setCategory(CategoryConverter.refEntity2DTO(entity.getCategory()));
            dtoBas.setSponsor(ProjectSponsorConverter.refEntity2DTO(entity.getSponsor()));
            dtoBas.setStatus(StatusConverter.refEntity2DTO(entity.getStatus()));

            return dtoBas;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de ProjectDTO a ProjectEntity Se invoca cuando se
     * necesita convertir una instancia de ProjectDTO con los atributos propios de
     * la entidad y con las relaciones uno a uno o muchos a uno
     *
     * @param dto instancia de ProjectDTO a convertir
     * @return Instancia de ProjectEntity creada a partir de los datos de dto
     * @generated
     */
    private static ProjectEntity basicDTO2Entity(ProjectDTO dto) {
        if (dto != null) {
            ProjectEntity entity = new ProjectEntity();
            entity.setId(dto.getId());
            entity.setName(dto.getName());
            entity.setDescription(dto.getDescription());
            entity.setPrice(dto.getPrice());
            entity.setDeadLine(dto.getDeadLine());
            entity.setPublicationDate(dto.getPublicationDate());
            entity.setStartDate(dto.getStartDate());
            entity.setCategory(CategoryConverter.refDTO2Entity(dto.getCategory()));
            entity.setSponsor(ProjectSponsorConverter.refDTO2Entity(dto.getSponsor()));
            entity.setStatus(StatusConverter.refDTO2Entity(dto.getStatus()));

            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte instancias de ProjectEntity a ProjectDTO incluyendo sus relaciones
     * Uno a muchos y Muchos a muchos
     *
     * @param entity Instancia de ProjectEntity a convertir
     * @return Instancia de ProjectDTO con los datos recibidos por parámetro
     * @generated
     */
    public static ProjectDTO fullEntity2DTO(ProjectEntity entity) {
        if (entity != null) {
            ProjectDTO dto = basicEntity2DTO(entity);
            dto.setExpectedskills(SkillConverter.listEntity2DTO(entity.getExpectedskills()));
            dto.setAgreements(AgreementConverter.listEntity2DTO(entity.getAgreements()));
            dto.setProjectSprints(ProjectSprintConverter.listEntity2DTO(entity.getProjectSprints()));
            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de ProjectDTO a ProjectEntity.
     * Incluye todos los atributos de ProjectEntity.
     *
     * @param dto Instancia de ProjectDTO a convertir
     * @return Instancia de ProjectEntity con los datos recibidos por parámetro
     * @generated
     */
    public static ProjectEntity fullDTO2Entity(ProjectDTO dto) {
        if (dto != null) {
            ProjectEntity entity = basicDTO2Entity(dto);
            entity.setExpectedskills(SkillConverter.listDTO2Entity(dto.getExpectedskills()));
            entity.setAgreements(AgreementConverter.listDTO2Entity(dto.getAgreements()));
            entity.setProjectSprints(ProjectSprintConverter.listDTO2Entity(dto.getProjectSprints()));
            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte una colección de instancias de ProjectEntity a ProjectDTO. Para cada
     * instancia de ProjectEntity en la lista, invoca basicEntity2DTO y añade el
     * nuevo ProjectDTO a una nueva lista
     *
     * @param entities Colección de entidades a convertir
     * @return Collección de instancias de ProjectDTO
     * @generated
     */
    public static List<ProjectDTO> listEntity2DTO(List<ProjectEntity> entities) {
        List<ProjectDTO> dtos = new ArrayList<ProjectDTO>();
        if (entities != null) {
            for (ProjectEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    /**
     * Convierte una colección de instancias de ProjectDTO a instancias de
     * ProjectEntity Para cada instancia se invoca el método basicDTO2Entity
     *
     * @param dtos entities Colección de ProjectDTO a convertir
     * @return Collección de instancias de ProjectEntity
     * @generated
     */
    public static List<ProjectEntity> listDTO2Entity(List<ProjectDTO> dtos) {
        List<ProjectEntity> entities = new ArrayList<ProjectEntity>();
        if (dtos != null) {
            for (ProjectDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }
}
