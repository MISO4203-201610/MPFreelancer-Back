package co.edu.uniandes.csw.mpfreelancer.converters;

import co.edu.uniandes.csw.mpfreelancer.dtos.ArtifactDTO;
import co.edu.uniandes.csw.mpfreelancer.entities.ArtifactEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * @generated
 */
public abstract class ArtifactConverter {

    /**
     * Constructor privado para evitar la creación del constructor implícito de Java
     * @generated
     */
    private ArtifactConverter() {
    }

    /**
     * Realiza la conversión de ArtifactEntity a ArtifactDTO.
     * Se invoca cuando otra entidad tiene una referencia a ArtifactEntity.
     * Entrega únicamente los atributos proprios de la entidad.
     *
     * @param entity instancia de ArtifactEntity a convertir
     * @return instancia de ArtifactDTO con los datos recibidos por parámetro
     * @generated
     */
    public static ArtifactDTO refEntity2DTO(ArtifactEntity entity) {
        if (entity != null) {
            ArtifactDTO dto = new ArtifactDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setDescription(entity.getDescription());
            dto.setPath(entity.getPath());
            
            return dto;
        } else {
            return null;
        }
    }

    /**
     * Realiza la conversión de ArtifactDTO a ArtifactEntity Se invoca cuando otro DTO
     * tiene una referencia a ArtifactDTO Convierte únicamente el ID ya que es el
     * único atributo necesario para guardar la relación en la base de datos
     *
     * @param dto instancia de ArtifactDTO a convertir
     * @return instancia de ArtifactEntity con los datos recibidos por parámetro
     * @generated
     */
    public static ArtifactEntity refDTO2Entity(ArtifactDTO dto) {
        if (dto != null) {
            ArtifactEntity entity = new ArtifactEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
                }
    }

    /**
     * Convierte una instancia de ArtifactEntity a ArtifactDTO Se invoca cuando se desea
     * consultar la entidad y sus relaciones muchos a uno o uno a uno
     *
     * @param entity instancia de ArtifactEntity a convertir
     * @return Instancia de ArtifactDTO con los datos recibidos por parámetro
     * @generated
     */
    private static ArtifactDTO basicEntity2DTO(ArtifactEntity entity) {
        if (entity != null) {
            ArtifactDTO dto = new ArtifactDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setDescription(entity.getDescription());
            dto.setPath(entity.getPath());
            dto.setProjectSprint(ProjectSprintConverter.refEntity2DTO(entity.getProjectSprint()));

            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de ArtifactDTO a ArtifactEntity Se invoca cuando se
     * necesita convertir una instancia de ArtifactDTO con los atributos propios de
     * la entidad y con las relaciones uno a uno o muchos a uno
     *
     * @param dto instancia de ArtifactDTO a convertir
     * @return Instancia de ArtifactEntity creada a partir de los datos de dto
     * @generated
     */
    private static ArtifactEntity basicDTO2Entity(ArtifactDTO dto) {
        if (dto != null) {
            ArtifactEntity entity = new ArtifactEntity();
            entity.setId(dto.getId());
            entity.setName(dto.getName());
            entity.setDescription(dto.getDescription());
            entity.setPath(dto.getPath());
            entity.setProjectSprint(ProjectSprintConverter.refDTO2Entity(dto.getProjectSprint()));
            
            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte instancias de ArtifactEntity a ArtifactDTO incluyendo sus relaciones
     * Uno a muchos y Muchos a muchos
     *
     * @param entity Instancia de ArtifactEntity a convertir
     * @return Instancia de ArtifactDTO con los datos recibidos por parámetro
     * @generated
     */
    public static ArtifactDTO fullEntity2DTO(ArtifactEntity entity) {
        if (entity != null) {
            ArtifactDTO dto = basicEntity2DTO(entity);
            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de ArtifactDTO a ArtifactEntity.
     * Incluye todos los atributos de ArtifactEntity.
     *
     * @param dto Instancia de ArtifactDTO a convertir
     * @return Instancia de ArtifactEntity con los datos recibidos por parámetro
     * @generated
     */
    public static ArtifactEntity fullDTO2Entity(ArtifactDTO dto) {
        if (dto != null) {
            ArtifactEntity entity = basicDTO2Entity(dto);
            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte una colección de instancias de ArtifactEntity a ArtifactDTO. Para cada
     * instancia de ArtifactEntity en la lista, invoca basicEntity2DTO y añade el
     * nuevo ArtifactDTO a una nueva lista
     *
     * @param entities Colección de entidades a convertir
     * @return Collección de instancias de ArtifactDTO
     * @generated
     */
    public static List<ArtifactDTO> listEntity2DTO(List<ArtifactEntity> entities) {
        List<ArtifactDTO> dtos = new ArrayList<ArtifactDTO>();
        if (entities != null) {
            for (ArtifactEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    /**
     * Convierte una colección de instancias de ArtifactDTO a instancias de
     * ArtifactEntity Para cada instancia se invoca el método basicDTO2Entity
     *
     * @param dtos entities Colección de ArtifactDTO a convertir
     * @return Collección de instancias de ArtifactEntity
     * @generated
     */
    public static List<ArtifactEntity> listDTO2Entity(List<ArtifactDTO> dtos) {
        List<ArtifactEntity> entities = new ArrayList<ArtifactEntity>();
        if (dtos != null) {
            for (ArtifactDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }

}
