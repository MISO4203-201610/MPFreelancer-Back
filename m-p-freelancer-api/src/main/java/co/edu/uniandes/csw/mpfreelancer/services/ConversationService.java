/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.services;

import co.edu.uniandes.csw.auth.provider.StatusCreated;
import co.edu.uniandes.csw.mpfreelancer.api.IConversationLogic;
import co.edu.uniandes.csw.mpfreelancer.converters.ConversationConverter;
import co.edu.uniandes.csw.mpfreelancer.dtos.ConversationDTO;
import co.edu.uniandes.csw.mpfreelancer.entities.ConversationEntity;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author mf.calderon
 */
@Path("/conversations")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ConversationService {
    
    @Inject private IConversationLogic conversationLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("maxRecords") private Integer maxRecords;
    
    @GET
    public List<ConversationDTO> getConversations() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", conversationLogic.countConversations());
            return ConversationConverter.listEntity2DTO(conversationLogic.getConversations(page, maxRecords));
        }
        return ConversationConverter.listEntity2DTO(conversationLogic.getConversations());
    }

    @GET
    @Path("{id: \\d+}")
    public ConversationDTO getConversation(@PathParam("id") Long id) {
        return ConversationConverter.fullEntity2DTO(conversationLogic.getConversations(id));
    }

    @POST
    @StatusCreated
    public ConversationDTO createConversation(ConversationDTO dto) {
        ConversationEntity entity = ConversationConverter.fullDTO2Entity(dto);
        return ConversationConverter.fullEntity2DTO(conversationLogic.createConversations(entity));
    }

    @PUT
    @Path("{id: \\d+}")
    public ConversationDTO updateConversation(@PathParam("id") Long id, ConversationDTO dto) {
        ConversationEntity entity = ConversationConverter.fullDTO2Entity(dto);
        entity.setId(id);
        return ConversationConverter.fullEntity2DTO(conversationLogic.updateConversations(entity));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteConversation(@PathParam("id") Long id) {
        conversationLogic.deleteConversations(id);
    }
    
}
