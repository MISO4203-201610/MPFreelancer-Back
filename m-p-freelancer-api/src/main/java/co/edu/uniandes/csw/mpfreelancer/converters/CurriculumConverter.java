package co.edu.uniandes.csw.mpfreelancer.converters;

import co.edu.uniandes.csw.mpfreelancer.dtos.CurriculumDTO;
import co.edu.uniandes.csw.mpfreelancer.entities.FreelancerEntity;
import co.edu.uniandes.csw.mpfreelancer.entities.CurriculumEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asistente
 */
public abstract class CurriculumConverter {

    private CurriculumConverter() {
    }

    public static CurriculumDTO refEntity2DTO(CurriculumEntity entity) {
        if (entity != null) {
            CurriculumDTO dto = new CurriculumDTO();
            dto.setId(entity.getId());
            dto.setProfile(entity.getProfile());
            dto.setIdentification(entity.getIdentification());
            dto.setEmail(entity.getEmail());
            dto.setMobile(entity.getMobile());
                
            return dto;
        } else {
            return null;
        }
    }

    public static CurriculumEntity refDTO2Entity(CurriculumDTO dto) {
        if (dto != null) {
            CurriculumEntity entity = new CurriculumEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }

    private static CurriculumDTO basicEntity2DTO(CurriculumEntity entity) {
        if (entity != null) {
            CurriculumDTO dto = new CurriculumDTO();
            dto.setId(entity.getId());
            dto.setProfile(entity.getProfile());
            dto.setIdentification(entity.getIdentification());
            dto.setEmail(entity.getEmail());
            dto.setMobile(entity.getMobile());
            dto.setFreelancer(FreelancerConverter.refEntity2DTO(entity.getFreelancer()));

            return dto;
        } else {
            return null;
        }
    }

    private static CurriculumEntity basicDTO2Entity(CurriculumDTO dto) {
        if (dto != null) {
            CurriculumEntity entity = new CurriculumEntity();
            entity.setId(dto.getId());
            entity.setProfile(dto.getProfile());
            entity.setIdentification(dto.getIdentification());
            entity.setEmail(dto.getEmail());
            entity.setMobile(dto.getMobile());
            entity.setFreelancer(FreelancerConverter.refDTO2Entity(dto.getFreelancer()));

            return entity;
        } else {
            return null;
        }
    }

    public static CurriculumDTO fullEntity2DTO(CurriculumEntity entity) {
        if (entity != null) {
            CurriculumDTO dto = basicEntity2DTO(entity);
            return dto;
        } else {
            return null;
        }
    }

    public static CurriculumEntity fullDTO2Entity(CurriculumDTO dto) {
        if (dto != null) {
            CurriculumEntity entity = basicDTO2Entity(dto);
            return entity;
        } else {
            return null;
        }
    }

    public static List<CurriculumDTO> listEntity2DTO(List<CurriculumEntity> entities) {
        List<CurriculumDTO> dtos = new ArrayList<>();
        if (entities != null) {
            for (CurriculumEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    public static List<CurriculumEntity> listDTO2Entity(List<CurriculumDTO> dtos) {
        List<CurriculumEntity> entities = new ArrayList<>();
        if (dtos != null) {
            for (CurriculumDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }

    public static CurriculumEntity childDTO2Entity(CurriculumDTO dto, FreelancerEntity parent){
        CurriculumEntity entity = basicDTO2Entity(dto);
        entity.setFreelancer(parent);
        return entity;
    }

    public static List<CurriculumEntity> childListDTO2Entity(List<CurriculumDTO> dtos, FreelancerEntity parent) {
        List<CurriculumEntity> entities = new ArrayList<>();
        if (dtos != null) {
            for (CurriculumDTO dto : dtos) {
                entities.add(childDTO2Entity(dto, parent));
            }
        }
        return entities;
    }
}