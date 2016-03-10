package co.edu.uniandes.csw.mpfreelancer.services;

import co.edu.uniandes.csw.auth.provider.StatusCreated;
import static co.edu.uniandes.csw.auth.stormpath.Utils.getClient;
import co.edu.uniandes.csw.mp.ann.MPLoCAnn;
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
import co.edu.uniandes.csw.mpfreelancer.api.IProjectLogic;
import co.edu.uniandes.csw.mpfreelancer.api.IProjectSponsorLogic;
import co.edu.uniandes.csw.mpfreelancer.dtos.ProjectDTO;
import co.edu.uniandes.csw.mpfreelancer.entities.ProjectEntity;
import co.edu.uniandes.csw.mpfreelancer.converters.ProjectConverter;
import co.edu.uniandes.csw.mpfreelancer.converters.ProjectSprintConverter;
import co.edu.uniandes.csw.mpfreelancer.dtos.SkillDTO;
import co.edu.uniandes.csw.mpfreelancer.converters.SkillConverter;
import co.edu.uniandes.csw.mpfreelancer.dtos.ProjectSprintDTO;
import co.edu.uniandes.csw.mpfreelancer.entities.ProjectSponsorEntity;
import com.stormpath.sdk.account.Account;
import javax.servlet.http.HttpServletRequest;

/**
 * @generated
 */
@Path("/projects")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProjectService {

    @Inject private IProjectLogic projectLogic;
    @Inject private IProjectSponsorLogic projectSponsorLogic;
    @Context private HttpServletRequest req;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("maxRecords") private Integer maxRecords;

    /**
     * Obtiene la lista de los registros de Book.
     *
     * @return Colección de objetos de ProjectDTO cada uno con sus respectivos Review
     * @generated
     */
    @GET
    public List<ProjectDTO> getProjects() {
        String accountHref = req.getRemoteUser();
        if (accountHref != null) {
            Account account = getClient().getResource(accountHref, Account.class);            
            Integer id=(int)account.getCustomData().get("projectSponsor_id");            
            return ProjectConverter.listEntity2DTO(projectSponsorLogic.listProjects(id.longValue()));
        } else {
            return null;
        }
    }
    
    @GET
    @Path("/all")
    public List<ProjectDTO> getAllProjects() {        
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", projectLogic.countProjects());
            return ProjectConverter.listEntity2DTO(projectLogic.getProjects(page, maxRecords));
        }
        return ProjectConverter.listEntity2DTO(projectLogic.getProjects());
    }

    /**
     * Obtiene los datos de una instancia de Book a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de ProjectDTO con los datos del Book consultado y sus Review
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public ProjectDTO getProject(@PathParam("id") Long id) {
        return ProjectConverter.fullEntity2DTO(projectLogic.getProject(id));
    }

    /**
     * Se encarga de crear un book en la base de datos.
     *
     * @param dto Objeto de ProjectDTO con los datos nuevos
     * @return Objeto de ProjectDTO con los datos nuevos y su ID.
     * @generated
     */
    @MPLoCAnn(tier = "Service", reqId = "R008")
    @POST
    @StatusCreated
    public ProjectDTO createProject(ProjectDTO dto) {
        String accountHref = req.getRemoteUser();
        if (accountHref != null) {
            Account account = getClient().getResource(accountHref, Account.class);            
            Integer id=(int)account.getCustomData().get("projectSponsor_id");  
            ProjectSponsorEntity sponsor= new ProjectSponsorEntity();
            sponsor.setId(id.longValue());
            ProjectEntity entity = ProjectConverter.fullDTO2Entity(dto);
            entity.setSponsor(sponsor);
        return ProjectConverter.fullEntity2DTO(projectLogic.createProject(entity));
        }else{
            return null;
        }
        
    }

    /**
     * Actualiza la información de una instancia de Book.
     *
     * @param id Identificador de la instancia de Book a modificar
     * @param dto Instancia de ProjectDTO con los nuevos datos.
     * @return Instancia de ProjectDTO con los datos actualizados.
     * @generated
     */
    @MPLoCAnn(tier = "Service", reqId = "R009")
    @PUT
    @Path("{id: \\d+}")
    public ProjectDTO updateProject(@PathParam("id") Long id, ProjectDTO dto) {
        ProjectEntity entity = ProjectConverter.fullDTO2Entity(dto);
        entity.setId(id);
        return ProjectConverter.fullEntity2DTO(projectLogic.updateProject(entity));
    }

    /**
     * Elimina una instancia de Book de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @generated
     */
    @MPLoCAnn(tier = "Service", reqId = "R010")
    @DELETE
    @Path("{id: \\d+}")
    public void deleteProject(@PathParam("id") Long id) {
        projectLogic.deleteProject(id);
    }

    /**
     * Obtiene una colección de instancias de SkillDTO asociadas a una
     * instancia de Project
     *
     * @param projectId Identificador de la instancia de Project
     * @return Colección de instancias de SkillDTO asociadas a la instancia de Project
     * @generated
     */
    @GET
    @Path("{projectId: \\d+}/expectedskills")
    public List<SkillDTO> listExpectedskills(@PathParam("projectId") Long projectId) {
        return SkillConverter.listEntity2DTO(projectLogic.listExpectedskills(projectId));
    }

    /**
     * Obtiene una colección de instancias de ProjectSprintDTO asociadas a una
     * instancia de Project
     *
     * @param projectId Identificador de la instancia de Project
     * @return Colección de instancias de ProjectSprintDTO asociadas a la instancia de Project
     * @generated
     */
    @GET
    @Path("{projectId: \\d+}/sprints")
    public List<ProjectSprintDTO> listSprints(@PathParam("projectId") Long projectId) {
        return ProjectSprintConverter.listEntity2DTO(projectLogic.listSprints(projectId));
    }
    
    /**
     * Obtiene una instancia de Skill asociada a una instancia de Project
     *
     * @param projectId Identificador de la instancia de Project
     * @param skillId Identificador de la instancia de Skill
     * @generated
     */
    @GET
    @Path("{projectId: \\d+}/expectedskills/{skillId: \\d+}")
    public SkillDTO getExpectedskills(@PathParam("projectId") Long projectId, @PathParam("skillId") Long skillId) {
        return SkillConverter.fullEntity2DTO(projectLogic.getExpectedskills(projectId, skillId));
    }
    
    /**
     * Asocia un Skill existente a un Project
     *
     * @param projectId Identificador de la instancia de Project
     * @param skillId Identificador de la instancia de Skill
     * @return Instancia de SkillDTO que fue asociada a Project
     * @generated
     */
    @POST
    @Path("{projectId: \\d+}/expectedskills/{skillId: \\d+}")
    public SkillDTO addExpectedskills(@PathParam("projectId") Long projectId, @PathParam("skillId") Long skillId) {
        return SkillConverter.fullEntity2DTO(projectLogic.addExpectedskills(projectId, skillId));
    }

    /**
     * Remplaza las instancias de Skill asociadas a una instancia de Project
     *
     * @param projectId Identificador de la instancia de Project
     * @param skills Colección de instancias de SkillDTO a asociar a instancia de Project
     * @return Nueva colección de SkillDTO asociada a la instancia de Project
     * @generated
     */
    @PUT
    @Path("{projectId: \\d+}/expectedskills")
    public List<SkillDTO> replaceExpectedskills(@PathParam("projectId") Long projectId, List<SkillDTO> skills) {
        return SkillConverter.listEntity2DTO(projectLogic.replaceExpectedskills(projectId, SkillConverter.listDTO2Entity(skills)));
    }

    /**
     * Desasocia un Skill existente de un Project existente
     *
     * @param projectId Identificador de la instancia de Project
     * @param skillId Identificador de la instancia de Skill
     * @generated
     */
    @DELETE
    @Path("{projectId: \\d+}/expectedskills/{skillId: \\d+}")
    public void removeExpectedskills(@PathParam("projectId") Long projectId, @PathParam("skillId") Long skillId) {
        projectLogic.removeExpectedskills(projectId, skillId);
    }
}
