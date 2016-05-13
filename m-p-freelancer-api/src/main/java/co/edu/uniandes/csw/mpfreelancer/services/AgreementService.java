/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.services;

import co.edu.uniandes.csw.auth.provider.StatusCreated;
import co.edu.uniandes.csw.mpfreelancer.api.IProjectLogic;
import co.edu.uniandes.csw.mpfreelancer.api.IAgreementLogic;
import co.edu.uniandes.csw.mpfreelancer.api.IFreelancerLogic;
import co.edu.uniandes.csw.mpfreelancer.api.IStatusLogic;
import co.edu.uniandes.csw.mpfreelancer.converters.AgreementConverter;
import co.edu.uniandes.csw.mpfreelancer.dtos.AgreementDTO;
import co.edu.uniandes.csw.mpfreelancer.dtos.FreelancerDTO;
import co.edu.uniandes.csw.mpfreelancer.dtos.ProjectDTO;
import co.edu.uniandes.csw.mpfreelancer.entities.AgreementEntity;
import co.edu.uniandes.csw.mpfreelancer.entities.FreelancerEntity;
import co.edu.uniandes.csw.mpfreelancer.entities.ProjectEntity;
import co.edu.uniandes.csw.mpfreelancer.entities.StatusEntity;
import co.edu.uniandes.csw.mpfreelancer.mail.Mail;
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
    @Inject private IFreelancerLogic freelancerLogic;
    @Inject private IProjectLogic projectLogic;
    @Inject private IAgreementLogic agreementLogic;
    @Inject private IStatusLogic statusLogic;
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
    
    @GET
    @Path("{freelancerId: \\d+}/agreementsFreelancer")
    public List<AgreementDTO> AgreementsFreelancer(@PathParam("freelancerId") Long freelancerId) {

        if (freelancerId != null) {
            return AgreementConverter.listEntity2DTO(agreementLogic.getByFreelancer(freelancerId));
        } else {
            if (page != null && maxRecords != null) {
                this.response.setIntHeader("X-Total-Count", agreementLogic.countAgreements());
                return AgreementConverter.listEntity2DTO(agreementLogic.getAgreements(page, maxRecords));
            }
            return AgreementConverter.listEntity2DTO(agreementLogic.getAgreements());
        }
    }
    
    @GET
    @Path("{projectId: \\d+}/agreementsProject")
    public List<AgreementDTO> AgreementsProject(@PathParam("projectId") Long projectId) {

        if (projectId != null) {
            return AgreementConverter.listEntity2DTO(agreementLogic.getByProject(projectId));
        } else {
            if (page != null && maxRecords != null) {
                this.response.setIntHeader("X-Total-Count", agreementLogic.countAgreements());
                return AgreementConverter.listEntity2DTO(agreementLogic.getAgreements(page, maxRecords));
            }
            return AgreementConverter.listEntity2DTO(agreementLogic.getAgreements());
        }
    }
    
    @GET
    @Path("{projectId: \\d+}/agreementsProjectAcepted")
    public List<AgreementDTO> AgreementsProjectAcepted(@PathParam("projectId") Long projectId) {

        if (projectId != null) {
            return AgreementConverter.listEntity2DTO(agreementLogic.getProjectAcepted(projectId));
        } else {
            if (page != null && maxRecords != null) {
                this.response.setIntHeader("X-Total-Count", agreementLogic.countAgreements());
                return AgreementConverter.listEntity2DTO(agreementLogic.getAgreements(page, maxRecords));
            }
            return AgreementConverter.listEntity2DTO(agreementLogic.getAgreements());
        }
    }
    
    @GET
    @Path("{freelancerId: \\d+}/agreementsStatus1")
    public List<AgreementDTO> AgreementsStatus1(@PathParam("freelancerId") Long freelancerId) {

        if (freelancerId != null) {
            return AgreementConverter.listEntity2DTO(agreementLogic.getByStatus1(freelancerId));
        } else {
            if (page != null && maxRecords != null) {
                this.response.setIntHeader("X-Total-Count", agreementLogic.countAgreements());
                return AgreementConverter.listEntity2DTO(agreementLogic.getAgreements(page, maxRecords));
            }
            return AgreementConverter.listEntity2DTO(agreementLogic.getAgreements());
        }
    }
    
    @GET
    @Path("{freelancerId: \\d+}/agreementsStatus2")
    public List<AgreementDTO> AgreementsStatus2(@PathParam("freelancerId") Long freelancerId) {

        if (freelancerId != null) {
            return AgreementConverter.listEntity2DTO(agreementLogic.getByStatus2(freelancerId));
        } else {
            if (page != null && maxRecords != null) {
                this.response.setIntHeader("X-Total-Count", agreementLogic.countAgreements());
                return AgreementConverter.listEntity2DTO(agreementLogic.getAgreements(page, maxRecords));
            }
            return AgreementConverter.listEntity2DTO(agreementLogic.getAgreements());
        }
    }
    
    @GET
    @Path("{freelancerId: \\d+}/agreementsStatus3")
    public List<AgreementDTO> AgreementsStatus3(@PathParam("freelancerId") Long freelancerId) {

        if (freelancerId != null) {
            return AgreementConverter.listEntity2DTO(agreementLogic.getByStatus3(freelancerId));
        } else {
            if (page != null && maxRecords != null) {
                this.response.setIntHeader("X-Total-Count", agreementLogic.countAgreements());
                return AgreementConverter.listEntity2DTO(agreementLogic.getAgreements(page, maxRecords));
            }
            return AgreementConverter.listEntity2DTO(agreementLogic.getAgreements());
        }
    }
    
    @GET
    @Path("{freelancerId: \\d+}/agreementsStatus4")
    public List<AgreementDTO> AgreementsStatus4(@PathParam("freelancerId") Long freelancerId) {

        if (freelancerId != null) {
            return AgreementConverter.listEntity2DTO(agreementLogic.getByStatus4(freelancerId));
        } else {
            if (page != null && maxRecords != null) {
                this.response.setIntHeader("X-Total-Count", agreementLogic.countAgreements());
                return AgreementConverter.listEntity2DTO(agreementLogic.getAgreements(page, maxRecords));
            }
            return AgreementConverter.listEntity2DTO(agreementLogic.getAgreements());
        }
    }
    
   
    @POST
    @Path("{freelancerId: \\d+}/agreementsInvited/{projectId: \\d+}")
    public AgreementDTO agreementInvited(@PathParam("freelancerId") Long freelancerId,@PathParam("projectId") Long projectId ) {
        FreelancerDTO freelancer = new FreelancerDTO();
        freelancer.setId(freelancerId);
        ProjectDTO project = new ProjectDTO();
        project.setId(projectId);
        AgreementDTO dto = new AgreementDTO();
        dto.setFreelancer(freelancer);
        dto.setProject(project);
        dto.setStatus(1);
        AgreementEntity entity = AgreementConverter.fullDTO2Entity(dto);
        return AgreementConverter.fullEntity2DTO(agreementLogic.createAgreement(entity));
    }
    
    @PUT
    @Path("{agreementsId: \\d+}/{price: \\d+}/agreementsAcept")
    public AgreementDTO agreementAcept(@PathParam("agreementsId") Long id , @PathParam("price") Integer price ) {      
        AgreementEntity entity = agreementLogic.getAgreement(id);
        entity.setId(id);
        entity.setPrice(price);
        entity.setStatus(2);
        return AgreementConverter.fullEntity2DTO(agreementLogic.updateAgreement(entity));
    }
    
    @PUT
    @Path("{agreementsId: \\d+}/agreementsReject")
    public AgreementDTO agreementReject(@PathParam("agreementsId") Long id ) {      
        AgreementEntity entity = agreementLogic.getAgreement(id);
        entity.setId(id);
        entity.setStatus(3);
        return AgreementConverter.fullEntity2DTO(agreementLogic.updateAgreement(entity));
    }
    
    @PUT
    @Path("{agreementsId: \\d+}/agreementsSelected")
    public AgreementDTO agreementSelected(@PathParam("agreementsId") Long id ) {      
        AgreementEntity entity = agreementLogic.getAgreement(id);
        
        // Update the agreement
        entity.setId(id);
        entity.setStatus(4);
        
        // Update the project aswell
        ProjectEntity projectEntity = entity.getProject();
        projectLogic.updateProject(projectEntity);
        
        // Notify all rejected guys
        for (int i = 0; i < projectEntity.getAgreements().size(); i++)
        {
            if (projectEntity.getAgreements().get(i).getId() != id)
                new Mail(projectEntity.getAgreements().get(i).getFreelancer().getEmail(), "Agreement reject", "Your agreement " + projectEntity.getAgreements().get(i).getName() + " was rejected :(");
        }
        
        // Notify choosed guy
        new Mail(entity.getFreelancer().getEmail(), "Agreement accepted", "Your agreement " + entity.getName() + " was accepted! You better start working :)");
        
        // Update it in database and return it
        return AgreementConverter.fullEntity2DTO(agreementLogic.updateAgreement(entity));
    }
    
    @GET
    @Path("{projectId: \\d+}/{freelancerId: \\d+}/agreementsFreelancer")
    public ProjectEntity agreementAssignFreelancer(@PathParam("projectId") Long projectId, @PathParam("freelancerId") Long freelancerId ) {
        
        // Send email
        List<AgreementEntity> agreements = agreementLogic.getByProject(projectId);
        FreelancerEntity freelancer = null;
        
        for (int i = 0; i < agreements.size(); i++)
        {
            if (agreements.get(i).getFreelancer().getId() == freelancerId)
            {
                AgreementEntity agreement = agreements.get(i);
                freelancer = agreement.getFreelancer();
                agreement.setStatus(4);
                new Mail(freelancer.getEmail(), "Agreement accepted", "Your agreement " + agreements.get(i).getName() + " was accepted! You better start working :)");
                agreementLogic.updateAgreement(agreement);
            }
            else
            {
                new Mail(agreements.get(i).getFreelancer().getEmail(), "Agreement reject", "Your agreement " + agreements.get(i).getName() + " was rejected :(");
            }
        }
        
        // Update project status
        /*ProjectEntity project = projectLogic.getProject(projectId);
        boolean termino = false;
        
        if (freelancer != null)
            project.setFreelancer(freelancer);
        
        for (int i = 0; i < statusLogic.getStatuss().size() && !termino; i++)
        {
            if (statusLogic.getStatuss().get(i).getName().equals("Closed"))
            {
                project.setStatus(statusLogic.getStatuss().get(i));
            }
        }
        
        projectLogic.updateProject(project);*/
        
        return null;
    }
}
