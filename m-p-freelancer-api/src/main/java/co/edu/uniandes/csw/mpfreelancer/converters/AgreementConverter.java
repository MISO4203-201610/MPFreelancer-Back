/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.converters;

import co.edu.uniandes.csw.mpfreelancer.dtos.AgreementDTO;
import co.edu.uniandes.csw.mpfreelancer.entities.AgreementEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jc.nino11
 */
public abstract class AgreementConverter {
    
    public AgreementConverter(){        
    }
    
    public static AgreementDTO refEntity2DTO(AgreementEntity entity) {
        if (entity != null) {
            AgreementDTO dto = new AgreementDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dto.setRate(entity.getRate());
            dto.setSelected(entity.getSelected());

            return dto;
        } else {
            return null;
        }
    }
    
    public static AgreementEntity refDTO2Entity(AgreementDTO dto) {
        if (dto != null) {
            AgreementEntity entity = new AgreementEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }
     
    private static AgreementDTO basicEntity2DTO(AgreementEntity entity) {
        if (entity != null) {
            AgreementDTO dto = new AgreementDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dto.setRate(entity.getRate());
            dto.setSelected(entity.getSelected());
            dto.setFreelancer(FreelancerConverter.refEntity2DTO(entity.getFreelancer()));
            dto.setProject(ProjectConverter.refEntity2DTO(entity.getProject()));

            return dto;
        } else {
            return null;
        }
    }

    private static AgreementEntity basicDTO2Entity(AgreementDTO dto) {
        if (dto != null) {
            AgreementEntity entity = new AgreementEntity();
            entity.setId(dto.getId());
            entity.setName(dto.getName());
            entity.setPrice(dto.getPrice());
            entity.setRate(dto.getRate());
            entity.setSelected(dto.getSelected());
            entity.setFreelancer(FreelancerConverter.refDTO2Entity(dto.getFreelancer()));
            entity.setProject(ProjectConverter.refDTO2Entity(dto.getProject()));

            return entity;
        } else {
            return null;
        }
    }

    public static AgreementDTO fullEntity2DTO(AgreementEntity entity) {
        if (entity != null) {
            AgreementDTO dto = basicEntity2DTO(entity);
            return dto;
        } else {
            return null;
        }
    }

    public static AgreementEntity fullDTO2Entity(AgreementDTO dto) {
        if (dto != null) {
            AgreementEntity entity = basicDTO2Entity(dto);
            return entity;
        } else {
            return null;
        }
    }

    public static List<AgreementDTO> listEntity2DTO(List<AgreementEntity> entities) {
        List<AgreementDTO> dtos = new ArrayList<>();
        if (entities != null) {
            for (AgreementEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    public static List<AgreementEntity> listDTO2Entity(List<AgreementDTO> dtos) {
        List<AgreementEntity> entities = new ArrayList<>();
        if (dtos != null) {
            for (AgreementDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }
}
