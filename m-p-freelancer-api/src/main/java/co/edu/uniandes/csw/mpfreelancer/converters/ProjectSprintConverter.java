package co.edu.uniandes.csw.mpfreelancer.converters;

import co.edu.uniandes.csw.mpfreelancer.dtos.ProjectSprintDTO;
import co.edu.uniandes.csw.mpfreelancer.entities.ProjectSprintEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * @generated
 */
public abstract class ProjectSprintConverter {

    /**
     * Constructor privado para evitar la creación del constructor implícito de Java
     * @generated
     */
    private ProjectSprintConverter() {
    }

    /**
     * Realiza la conversión de ProjectSprintEntity a ProjectSprintDTO.
     * Se invoca cuando otra entidad tiene una referencia a ProjectSprintEntity.
     * Entrega únicamente los atributos proprios de la entidad.
     *
     * @param entity instancia de ProjectSprintEntity a convertir
     * @return instancia de ProjectSprintDTO con los datos recibidos por parámetro
     * @generated
     */
    public static ProjectSprintDTO refEntity2DTO(ProjectSprintEntity entity) {
        if (entity != null) {
            ProjectSprintDTO dto = new ProjectSprintDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setDescription(entity.getDescription());
            dto.setPrice(entity.getPrice());
            dto.setStartDate(entity.getStartDate());
            dto.setDeadLine(entity.getDeadLine());
            return dto;
        } else {
            return null;
        }
    }

    /**
     * Realiza la conversión de ProjectSprintDTO a ProjectSprintEntity Se invoca cuando otro DTO
     * tiene una referencia a ProjectSprintDTO Convierte únicamente el ID ya que es el
     * único atributo necesario para guardar la relación en la base de datos
     *
     * @param dto instancia de ProjectSprintDTO a convertir
     * @return instancia de ProjectSprintEntity con los datos recibidos por parámetro
     * @generated
     */
    public static ProjectSprintEntity refDTO2Entity(ProjectSprintDTO dto) {
        if (dto != null) {
            ProjectSprintEntity entity = new ProjectSprintEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
                }
    }

    /**
     * Convierte una instancia de ProjectSprintEntity a ProjectSprintDTO Se invoca cuando se desea
     * consultar la entidad y sus relaciones muchos a uno o uno a uno
     *
     * @param entity instancia de ProjectSprintEntity a convertir
     * @return Instancia de ProjectSprintDTO con los datos recibidos por parámetro
     * @generated
     */
    private static ProjectSprintDTO basicEntity2DTO(ProjectSprintEntity entity) {
        if (entity != null) {
            
            ProjectSprintDTO dto = new ProjectSprintDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setDescription(entity.getDescription());
            dto.setPrice(entity.getPrice());
            dto.setStartDate(entity.getStartDate());
            dto.setDeadLine(entity.getDeadLine());
            dto.setProject(ProjectConverter.fullEntity2DTO(entity.getProject()));
            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de ProjectSprintDTO a ProjectSprintEntity Se invoca cuando se
     * necesita convertir una instancia de ProjectSprintDTO con los atributos propios de
     * la entidad y con las relaciones uno a uno o muchos a uno
     *
     * @param dto instancia de ProjectSprintDTO a convertir
     * @return Instancia de ProjectSprintEntity creada a partir de los datos de dto
     * @generated
     */
    private static ProjectSprintEntity basicDTO2Entity(ProjectSprintDTO dto) {
        if (dto != null) {
            
            ProjectSprintEntity entity = new ProjectSprintEntity();
            entity.setId(dto.getId());
            entity.setName(dto.getName());
            entity.setDescription(dto.getDescription());
            entity.setPrice(dto.getPrice());
            entity.setStartDate(dto.getStartDate());
            entity.setDeadLine(dto.getDeadLine());
            entity.setProject(ProjectConverter.refDTO2Entity(dto.getProject()));
            
            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte instancias de ProjectSprintEntity a ProjectSprintDTO incluyendo sus relaciones
     * Uno a muchos y Muchos a muchos
     *
     * @param entity Instancia de ProjectSprintEntity a convertir
     * @return Instancia de ProjectSprintDTO con los datos recibidos por parámetro
     * @generated
     */
    public static ProjectSprintDTO fullEntity2DTO(ProjectSprintEntity entity) {
        if (entity != null) {
            ProjectSprintDTO dto = basicEntity2DTO(entity);
            dto.setArtifacts(ArtifactConverter.listEntity2DTO(entity.getArtifacts()));
            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de ProjectSprintDTO a ProjectSprintEntity.
     * Incluye todos los atributos de ProjectSprintEntity.
     *
     * @param dto Instancia de ProjectSprintDTO a convertir
     * @return Instancia de ProjectSprintEntity con los datos recibidos por parámetro
     * @generated
     */
    public static ProjectSprintEntity fullDTO2Entity(ProjectSprintDTO dto) {
        if (dto != null) {
            ProjectSprintEntity entity = basicDTO2Entity(dto);
            entity.setArtifacts(ArtifactConverter.listDTO2Entity(dto.getArtifacts()));
            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte una colección de instancias de ProjectSprintEntity a ProjectSprintDTO. Para cada
     * instancia de ProjectSprintEntity en la lista, invoca basicEntity2DTO y añade el
     * nuevo ProjectSprintDTO a una nueva lista
     *
     * @param entities Colección de entidades a convertir
     * @return Collección de instancias de ProjectSprintDTO
     * @generated
     */
    public static List<ProjectSprintDTO> listEntity2DTO(List<ProjectSprintEntity> entities) {
        List<ProjectSprintDTO> dtos = new ArrayList<ProjectSprintDTO>();
        if (entities != null) {
            for (ProjectSprintEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    /**
     * Convierte una colección de instancias de ProjectSprintDTO a instancias de
     * ProjectSprintEntity Para cada instancia se invoca el método basicDTO2Entity
     *
     * @param dtos entities Colección de ProjectSprintDTO a convertir
     * @return Collección de instancias de ProjectSprintEntity
     * @generated
     */
    public static List<ProjectSprintEntity> listDTO2Entity(List<ProjectSprintDTO> dtos) {
        List<ProjectSprintEntity> entities = new ArrayList<ProjectSprintEntity>();
        if (dtos != null) {
            for (ProjectSprintDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }

}
