/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.converters;
   
import co.edu.uniandes.csw.mpfreelancer.dtos.WorkExperienceDTO;
import co.edu.uniandes.csw.mpfreelancer.entities.WorkExperienceEntity;
import co.edu.uniandes.csw.mpfreelancer.entities.FreelancerEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mf.calderon
 */
public class WorkExperienceConverter {


    private WorkExperienceConverter() {
    }

    public static WorkExperienceDTO refEntity2DTO(WorkExperienceEntity entity) {
        if (entity != null) {
            WorkExperienceDTO dto = new WorkExperienceDTO();
            dto.setId(entity.getId());
            dto.setProjectName(entity.getProjectName());
            dto.setProjectDescription(entity.getProjectDescription());
            dto.setStartDate(entity.getStartDate());
            dto.setEndDate(entity.getEndDate());
            dto.setSponsorCompany(entity.getSponsorCompany());

            return dto;
        } else {
            return null;
        }
    }

    public static WorkExperienceEntity refDTO2Entity(WorkExperienceDTO dto) {
        if (dto != null) {
           WorkExperienceEntity entity = new WorkExperienceEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }

    private static WorkExperienceDTO basicEntity2DTO(WorkExperienceEntity entity) {
        if (entity != null) {
            WorkExperienceDTO dto = new WorkExperienceDTO();
            dto.setId(entity.getId());
            dto.setProjectName(entity.getProjectName());
            dto.setProjectDescription(entity.getProjectDescription());
            dto.setStartDate(entity.getStartDate());
            dto.setEndDate(entity.getEndDate());
            dto.setSponsorCompany(entity.getSponsorCompany());
            dto.setFreelancerExperience(FreelancerConverter.refEntity2DTO(entity.getFreelancer()));

            return dto;
        } else {
            return null;
        }
    }

    private static WorkExperienceEntity basicDTO2Entity(WorkExperienceDTO dto) {
        if (dto != null) {
            WorkExperienceEntity entity = new WorkExperienceEntity();
            entity.setId(dto.getId());
            entity.setProjectName(dto.getProjectName());
            entity.setProjectDescription(dto.getProjectDescription());
            entity.setStartDate(dto.getStartDate());
            entity.setEndtDate(dto.getEndDate());
            entity.setSponsorCompany(dto.getSponsorCompany());
            entity.setFreelancer(FreelancerConverter.refDTO2Entity(dto.getFreelancerExperience()));

            return entity;
        } else {
            return null;
        }
    }

    public static WorkExperienceDTO fullEntity2DTO(WorkExperienceEntity entity) {
        if (entity != null) {
            WorkExperienceDTO dto = basicEntity2DTO(entity);
            return dto;
        } else {
            return null;
        }
    }

    public static WorkExperienceEntity fullDTO2Entity(WorkExperienceDTO dto) {
        if (dto != null) {
            WorkExperienceEntity entity = basicDTO2Entity(dto);
            return entity;
        } else {
            return null;
        }
    }

    public static List<WorkExperienceDTO> listEntity2DTO(List<WorkExperienceEntity> entities) {
        List<WorkExperienceDTO> dtos = new ArrayList<>();
        if (entities != null) {
            for (WorkExperienceEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    public static List<WorkExperienceEntity> listDTO2Entity(List<WorkExperienceDTO> dtos) {
        List<WorkExperienceEntity> entities = new ArrayList<>();
        if (dtos != null) {
            for (WorkExperienceDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }
    
    public static WorkExperienceEntity childDTO2Entity(WorkExperienceDTO dto, FreelancerEntity parent){
            WorkExperienceEntity entity= basicDTO2Entity(dto);
            entity.setFreelancer(parent);
            return entity;
    }
    
    public static List<WorkExperienceEntity> childListDTO2Entity(List<WorkExperienceDTO> dtos, FreelancerEntity parent) {
        List<WorkExperienceEntity> entities = new ArrayList<WorkExperienceEntity>();
        if (dtos != null) {
            for (WorkExperienceDTO dto : dtos) {
                entities.add(childDTO2Entity(dto, parent));
            }
        }
        return entities;
    }
    
    
}

