/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.services;

import co.edu.uniandes.csw.auth.provider.StatusCreated;
import co.edu.uniandes.csw.mpfreelancer.api.IAgreementLogic;
import co.edu.uniandes.csw.mpfreelancer.converters.AgreementConverter;
import co.edu.uniandes.csw.mpfreelancer.dtos.AgreementDTO;
import co.edu.uniandes.csw.mpfreelancer.entities.AgreementEntity;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Context;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author jc.nino11
 */
@Path("/agreements")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AgreementService {
    @Inject private IAgreementLogic agreementLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("maxRecords") private Integer maxRecords;

    @GET
    public List<AgreementDTO> getAgreements() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", agreementLogic.countAgreements());
            return AgreementConverter.listEntity2DTO(agreementLogic.getAgreements(page, maxRecords));
        }
        return AgreementConverter.listEntity2DTO(agreementLogic.getAgreements());
    }

    @GET
    @Path("{id: \\d+}")
    public AgreementDTO getAgreement(@PathParam("id") Long id) {
        return AgreementConverter.fullEntity2DTO(agreementLogic.getAgreement(id));
    }

    @POST
    @StatusCreated
    public AgreementDTO createAgreement(AgreementDTO dto) {
        AgreementEntity entity = AgreementConverter.fullDTO2Entity(dto);
        return AgreementConverter.fullEntity2DTO(agreementLogic.createAgreement(entity));
    }

    @PUT
    @Path("{id: \\d+}")
    public AgreementDTO updateAgreement(@PathParam("id") Long id, AgreementDTO dto) {
        AgreementEntity entity = AgreementConverter.fullDTO2Entity(dto);
        entity.setId(id);
        return AgreementConverter.fullEntity2DTO(agreementLogic.updateAgreement(entity));
    }

    @DELETE
    @Path("{id: \\d+}")
    public void deleteAgreement(@PathParam("id") Long id) {
        agreementLogic.deleteAgreement(id);
    }
}
