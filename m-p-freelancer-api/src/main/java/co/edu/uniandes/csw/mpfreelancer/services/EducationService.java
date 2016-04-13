/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.services;

import co.edu.uniandes.csw.auth.provider.StatusCreated;
import co.edu.uniandes.csw.mpfreelancer.api.IEducationLogic;
import co.edu.uniandes.csw.mpfreelancer.converters.EducationConverter;
import co.edu.uniandes.csw.mpfreelancer.dtos.EducationDTO;
import co.edu.uniandes.csw.mpfreelancer.entities.EducationEntity;
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

@Path("/educations")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EducationService {

    @Inject private IEducationLogic EducationLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("maxRecords") private Integer maxRecords;

    @GET
    public List<EducationDTO> getTitles() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", EducationLogic.countTitles());
            return EducationConverter.listEntity2DTO(EducationLogic.getTitles(page, maxRecords));
        }
        return EducationConverter.listEntity2DTO(EducationLogic.getTitles());
    }

    @GET
    @Path("{id: \\d+}")
    public EducationDTO getTitles(@PathParam("id") Long id) {
        return EducationConverter.fullEntity2DTO(EducationLogic.getTitles(id));
    }

    @POST
    @StatusCreated
    public EducationDTO createTitles(EducationDTO dto) {
        EducationEntity entity = EducationConverter.fullDTO2Entity(dto);
        return EducationConverter.fullEntity2DTO(EducationLogic.createTitles(entity));
    }

    @PUT
    @Path("{id: \\d+}")
    public EducationDTO updateTitles(@PathParam("id") Long id, EducationDTO dto) {
        EducationEntity entity = EducationConverter.fullDTO2Entity(dto);
        entity.setId(id);
        return EducationConverter.fullEntity2DTO(EducationLogic.updateTitles(entity));
    }

    @DELETE
    @Path("{id: \\d+}")
    public void deleteTitles(@PathParam("id") Long id) {
        EducationLogic.deleteTitles(id);
    }
    
    @GET
    @Path("{freelancerId: \\d+}/educationsFreelancer")
    public List<EducationDTO> EducationsFreelancer(@PathParam("freelancerId") Long freelancerId) {

        if (freelancerId != null) {
            return EducationConverter.listEntity2DTO(EducationLogic.getByFreelancer(freelancerId));
        } else {
            if (page != null && maxRecords != null) {
                this.response.setIntHeader("X-Total-Count", EducationLogic.countTitles());
                return EducationConverter.listEntity2DTO(EducationLogic.getTitles(page, maxRecords));
            }
            return EducationConverter.listEntity2DTO(EducationLogic.getTitles());
        }
    }

}