/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.converters;
import co.edu.uniandes.csw.mpfreelancer.dtos.MailDTO;
import co.edu.uniandes.csw.mpfreelancer.entities.ConversationEntity;
import co.edu.uniandes.csw.mpfreelancer.entities.MailEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mf.calderon
 */
public abstract class MailConverter {
    
    private MailConverter() {
    }

    public static MailDTO refEntity2DTO(MailEntity entity) {
        if (entity != null) {
            MailDTO dto = new MailDTO();
            dto.setId(entity.getId());
            dto.setWho(entity.getWho());
            dto.setMessage(entity.getMessge());
            dto.setDatemail(entity.getDatemail());
                            
            return dto;
        } else {
            return null;
        }
    }

    public static MailEntity refDTO2Entity(MailDTO dto) {
        if (dto != null) {
            MailEntity entity = new MailEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }

    private static MailDTO basicEntity2DTO(MailEntity entity) {
        if (entity != null) {
            MailDTO dto = new MailDTO();
            dto.setId(entity.getId());
            dto.setWho(entity.getWho());
            dto.setMessage(entity.getMessge());
            dto.setDatemail(entity.getDatemail());
            dto.setConversation(ConversationConverter.refEntity2DTO(entity.getConversation()));

            return dto;
        } else {
            return null;
        }
    }

    private static MailEntity basicDTO2Entity(MailDTO dto) {
        if (dto != null) {
            MailEntity entity = new MailEntity();
            entity.setId(dto.getId());
            entity.setWho(dto.getWho());
            entity.setMessage(dto.getMessge());
            entity.setDatemail(dto.getDatemail());
            entity.setConversation(ConversationConverter.refDTO2Entity(dto.getConversation()));

            return entity;
        } else {
            return null;
        }
    }

    public static MailDTO fullEntity2DTO(MailEntity entity) {
        if (entity != null) {
            MailDTO dto = basicEntity2DTO(entity);
            return dto;
        } else {
            return null;
        }
    }

    public static MailEntity fullDTO2Entity(MailDTO dto) {
        if (dto != null) {
            MailEntity entity = basicDTO2Entity(dto);
            return entity;
        } else {
            return null;
        }
    }

    public static List<MailDTO> listEntity2DTO(List<MailEntity> entities) {
        List<MailDTO> dtos = new ArrayList<>();
        if (entities != null) {
            for (MailEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    public static List<MailEntity> listDTO2Entity(List<MailDTO> dtos) {
        List<MailEntity> entities = new ArrayList<>();
        if (dtos != null) {
            for (MailDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }

    public static MailEntity childDTO2Entity(MailDTO dto, ConversationEntity parent){
        MailEntity entity = basicDTO2Entity(dto);
        entity.setConversation(parent);
        return entity;
    }

    public static List<MailEntity> childListDTO2Entity(List<MailDTO> dtos, ConversationEntity parent) {
        List<MailEntity> entities = new ArrayList<>();
        if (dtos != null) {
            for (MailDTO dto : dtos) {
                entities.add(childDTO2Entity(dto, parent));
            }
        }
        return entities;
    }
}
    
