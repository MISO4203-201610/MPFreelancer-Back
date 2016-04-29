/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.services;

import co.edu.uniandes.csw.auth.provider.StatusCreated;
import co.edu.uniandes.csw.mpfreelancer.api.IMailLogic;
import co.edu.uniandes.csw.mpfreelancer.converters.MailConverter;
import co.edu.uniandes.csw.mpfreelancer.dtos.MailDTO;
import co.edu.uniandes.csw.mpfreelancer.entities.MailEntity;
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
@Path("/mails")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MailService {
    
    @Inject private IMailLogic mailLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("maxRecords") private Integer maxRecords;

    @GET
    public List<MailDTO> getMails() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", mailLogic.countMails());
            return MailConverter.listEntity2DTO(mailLogic.getMails(page, maxRecords));
        }
        return MailConverter.listEntity2DTO(mailLogic.getMails());
    }

    @GET
    @Path("{id: \\d+}")
    public MailDTO getMail(@PathParam("id") Long id) {
        return MailConverter.fullEntity2DTO(mailLogic.getMails(id));
    }

    @POST
    @StatusCreated
    public MailDTO createMail(MailDTO dto) {
        MailEntity entity = MailConverter.fullDTO2Entity(dto);
        return MailConverter.fullEntity2DTO(mailLogic.createMails(entity));
    }

    @PUT
    @Path("{id: \\d+}")
    public MailDTO updateMail(@PathParam("id") Long id, MailDTO dto) {
        MailEntity entity = MailConverter.fullDTO2Entity(dto);
        entity.setId(id);
        return MailConverter.fullEntity2DTO(mailLogic.updateMails(entity));
    }

    @DELETE
    @Path("{id: \\d+}")
    public void deleteMail(@PathParam("id") Long id) {
        mailLogic.deleteMails(id);
    }
    
}
