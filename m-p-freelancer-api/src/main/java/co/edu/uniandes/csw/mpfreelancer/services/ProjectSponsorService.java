package co.edu.uniandes.csw.mpfreelancer.services;

import co.edu.uniandes.csw.auth.provider.StatusCreated;
import static co.edu.uniandes.csw.auth.stormpath.Utils.getClient;
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
import co.edu.uniandes.csw.mpfreelancer.api.IProjectSponsorLogic;
import co.edu.uniandes.csw.mpfreelancer.dtos.ProjectSponsorDTO;
import co.edu.uniandes.csw.mpfreelancer.entities.ProjectSponsorEntity;
import co.edu.uniandes.csw.mpfreelancer.converters.ProjectSponsorConverter;
import co.edu.uniandes.csw.mpfreelancer.dtos.ProjectDTO;
import co.edu.uniandes.csw.mpfreelancer.converters.ProjectConverter;
import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.group.Group;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 * @generated
 */
@Path("/projectSponsors")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProjectSponsorService {
    
    private static final String PROYECT_SPONSOR_HREF = "https://api.stormpath.com/v1/groups/w94UQuPl5386Zk91Numiv";    
    private static final String ADMIN_HREF = "https://api.stormpath.com/v1/groups/aDD8ajP65mhgxRNvASTfl";    

    @Inject private IProjectSponsorLogic projectSponsorLogic;
    @Context private HttpServletRequest req;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("maxRecords") private Integer maxRecords;

    /**
     * Obtiene la lista de los registros de Book.
     *
     * @return Colección de objetos de ProjectSponsorDTO cada uno con sus respectivos Review
     * @generated
     */
    @GET
    public List<ProjectSponsorDTO> getProjectSponsors() {
        String accountHref = req.getRemoteUser();
        if (accountHref != null) {
            Account account = getClient().getResource(accountHref, Account.class);
            for (Group gr : account.getGroups()) {
                switch (gr.getHref()) {
                    case ADMIN_HREF:
                        if (page != null && maxRecords != null) {
                        this.response.setIntHeader("X-Total-Count", projectSponsorLogic.countProjectSponsors());
                        return ProjectSponsorConverter.listEntity2DTO(projectSponsorLogic.getProjectSponsors(page, maxRecords));
                    }
                        return ProjectSponsorConverter.listEntity2DTO(projectSponsorLogic.getProjectSponsors());
                    case PROYECT_SPONSOR_HREF:
                        Integer id = (int) account.getCustomData().get("projectSponsor_id");
                        List<ProjectSponsorDTO> list = new ArrayList();
                        list.add(ProjectSponsorConverter.fullEntity2DTO(projectSponsorLogic.getProjectSponsor(id.longValue())));
                        return list;                    
                }
            }

        } 
        return null;

    }

    /**
     * Obtiene los datos de una instancia de Book a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de ProjectSponsorDTO con los datos del Book consultado y sus Review
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public ProjectSponsorDTO getProjectSponsor(@PathParam("id") Long id) {
        return ProjectSponsorConverter.fullEntity2DTO(projectSponsorLogic.getProjectSponsor(id));
    }
    
    @GET
    @Path("/current")    
    public ProjectSponsorDTO getCurrentProjectSponsor() {
        String accountHref = req.getRemoteUser();
        if (accountHref != null) {
            Account account = getClient().getResource(accountHref, Account.class);            
            Integer id=(int)account.getCustomData().get("projectSponsor_id");            
            return ProjectSponsorConverter.fullEntity2DTO(projectSponsorLogic.getProjectSponsor(id.longValue()));
        } else {
            return null;
        }
    }

    /**
     * Se encarga de crear un book en la base de datos.
     *
     * @param dto Objeto de ProjectSponsorDTO con los datos nuevos
     * @return Objeto de ProjectSponsorDTO con los datos nuevos y su ID.
     * @generated
     */
    @POST
    @StatusCreated
    public ProjectSponsorDTO createProjectSponsor(ProjectSponsorDTO dto) {
        ProjectSponsorEntity entity = ProjectSponsorConverter.fullDTO2Entity(dto);
        return ProjectSponsorConverter.fullEntity2DTO(projectSponsorLogic.createProjectSponsor(entity));
    }

    /**
     * Actualiza la información de una instancia de Book.
     *
     * @param id Identificador de la instancia de Book a modificar
     * @param dto Instancia de ProjectSponsorDTO con los nuevos datos.
     * @return Instancia de ProjectSponsorDTO con los datos actualizados.
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public ProjectSponsorDTO updateProjectSponsor(@PathParam("id") Long id, ProjectSponsorDTO dto) {
        ProjectSponsorEntity entity = ProjectSponsorConverter.fullDTO2Entity(dto);
        entity.setId(id);
        return ProjectSponsorConverter.fullEntity2DTO(projectSponsorLogic.updateProjectSponsor(entity));
    }

    /**
     * Elimina una instancia de Book de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteProjectSponsor(@PathParam("id") Long id) {
        projectSponsorLogic.deleteProjectSponsor(id);
    }

    /**
     * Obtiene una colección de instancias de ProjectDTO asociadas a una
     * instancia de ProjectSponsor
     *
     * @param projectSponsorId Identificador de la instancia de ProjectSponsor
     * @return Colección de instancias de ProjectDTO asociadas a la instancia de ProjectSponsor
     * @generated
     */
    @GET
    @Path("{projectSponsorId: \\d+}/projects")
    public List<ProjectDTO> listProjects(@PathParam("projectSponsorId") Long projectSponsorId) {
        return ProjectConverter.listEntity2DTO(projectSponsorLogic.listProjects(projectSponsorId));
    }

    /**
     * Obtiene una instancia de Project asociada a una instancia de ProjectSponsor
     *
     * @param projectSponsorId Identificador de la instancia de ProjectSponsor
     * @param projectId Identificador de la instancia de Project
     * @generated
     */
    @GET
    @Path("{projectSponsorId: \\d+}/projects/{projectId: \\d+}")
    public ProjectDTO getProjects(@PathParam("projectSponsorId") Long projectSponsorId, @PathParam("projectId") Long projectId) {
        return ProjectConverter.fullEntity2DTO(projectSponsorLogic.getProjects(projectSponsorId, projectId));
    }

    /**
     * Asocia un Project existente a un ProjectSponsor
     *
     * @param projectSponsorId Identificador de la instancia de ProjectSponsor
     * @param projectId Identificador de la instancia de Project
     * @return Instancia de ProjectDTO que fue asociada a ProjectSponsor
     * @generated
     */
    @POST
    @Path("{projectSponsorId: \\d+}/projects/{projectId: \\d+}")
    public ProjectDTO addProjects(@PathParam("projectSponsorId") Long projectSponsorId, @PathParam("projectId") Long projectId) {
        return ProjectConverter.fullEntity2DTO(projectSponsorLogic.addProjects(projectSponsorId, projectId));
    }

    /**
     * Remplaza las instancias de Project asociadas a una instancia de ProjectSponsor
     *
     * @param projectSponsorId Identificador de la instancia de ProjectSponsor
     * @param projects Colección de instancias de ProjectDTO a asociar a instancia de ProjectSponsor
     * @return Nueva colección de ProjectDTO asociada a la instancia de ProjectSponsor
     * @generated
     */
    @PUT
    @Path("{projectSponsorId: \\d+}/projects")
    public List<ProjectDTO> replaceProjects(@PathParam("projectSponsorId") Long projectSponsorId, List<ProjectDTO> projects) {
        return ProjectConverter.listEntity2DTO(projectSponsorLogic.replaceProjects(projectSponsorId, ProjectConverter.listDTO2Entity(projects)));
    }

    /**
     * Desasocia un Project existente de un ProjectSponsor existente
     *
     * @param projectSponsorId Identificador de la instancia de ProjectSponsor
     * @param projectId Identificador de la instancia de Project
     * @generated
     */
    @DELETE
    @Path("{projectSponsorId: \\d+}/projects/{projectId: \\d+}")
    public void removeProjects(@PathParam("projectSponsorId") Long projectSponsorId, @PathParam("projectId") Long projectId) {
        projectSponsorLogic.removeProjects(projectSponsorId, projectId);
    }
}
