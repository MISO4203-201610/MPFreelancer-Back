/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.services;

import co.edu.uniandes.csw.auth.provider.StatusCreated;
import co.edu.uniandes.csw.mpfreelancer.api.ICurriculumLogic;
import co.edu.uniandes.csw.mpfreelancer.converters.CurriculumConverter;
import co.edu.uniandes.csw.mpfreelancer.dtos.CurriculumDTO;
import co.edu.uniandes.csw.mpfreelancer.entities.CurriculumEntity;
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
 * @author Fernando
 */

@Path("/curriculums")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CurriculumService {

    @Inject private ICurriculumLogic CurriculumLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("maxRecords") private Integer maxRecords;

    @GET
    public List<CurriculumDTO> getCurriculums() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", CurriculumLogic.countCurriculums());
            return CurriculumConverter.listEntity2DTO(CurriculumLogic.getCurriculums(page, maxRecords));
        }
        return CurriculumConverter.listEntity2DTO(CurriculumLogic.getCurriculums());
    }

    @GET
    @Path("{id: \\d+}")
    public CurriculumDTO getCurriculum(@PathParam("id") Long id) {
        return CurriculumConverter.fullEntity2DTO(CurriculumLogic.getCurriculum(id));
    }

    @POST
    @StatusCreated
    public CurriculumDTO createCurriculum(CurriculumDTO dto) {
        CurriculumEntity entity = CurriculumConverter.fullDTO2Entity(dto);
        return CurriculumConverter.fullEntity2DTO(CurriculumLogic.createCurriculum(entity));
    }

    @PUT
    @Path("{id: \\d+}")
    public CurriculumDTO updateCurriculum(@PathParam("id") Long id, CurriculumDTO dto) {
        CurriculumEntity entity = CurriculumConverter.fullDTO2Entity(dto);
        entity.setId(id);
        return CurriculumConverter.fullEntity2DTO(CurriculumLogic.updateCurriculum(entity));
    }

    @DELETE
    @Path("{id: \\d+}")
    public void deleteCurriculum(@PathParam("id") Long id) {
        CurriculumLogic.deleteCurriculum(id);
    }

    @GET
    @Path("{freelancerId: \\d+}/freelancer")
    public List<CurriculumDTO> getByFreelancer(@PathParam("freelancerId") Long freelancerId) {

        if (freelancerId != null) {
            return CurriculumConverter.listEntity2DTO(CurriculumLogic.getByFreelancer(freelancerId));
        } else {
            if (page != null && maxRecords != null) {
                this.response.setIntHeader("X-Total-Count", CurriculumLogic.countCurriculums());
                return CurriculumConverter.listEntity2DTO(CurriculumLogic.getCurriculums(page, maxRecords));
            }
            return CurriculumConverter.listEntity2DTO(CurriculumLogic.getCurriculums());
        }
    }
}