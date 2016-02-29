package co.edu.uniandes.csw.mpfreelancer.services;

import co.edu.uniandes.csw.auth.provider.StatusCreated;
import co.edu.uniandes.csw.mp.ann.MPLoCAnn;
import co.edu.uniandes.csw.mpfreelancer.api.IProjectSprintLogic;
import co.edu.uniandes.csw.mpfreelancer.converters.ArtifactConverter;
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
import co.edu.uniandes.csw.mpfreelancer.dtos.ProjectSprintDTO;
import co.edu.uniandes.csw.mpfreelancer.entities.ProjectSprintEntity;
import co.edu.uniandes.csw.mpfreelancer.converters.ProjectSprintConverter;
import co.edu.uniandes.csw.mpfreelancer.dtos.ArtifactDTO;

/**
 * @generated
 */
@Path("/sprints")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProjectSprintService {

    @Inject private IProjectSprintLogic projectSprintLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("maxRecords") private Integer maxRecords;
    
    @GET
    @Path("/projects/{id: \\d+}")
    public List<ProjectSprintDTO> getAllSprintsFromProject(@PathParam("id") Long id) {        
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", projectSprintLogic.countProjectSprints(id));
            return ProjectSprintConverter.listEntity2DTO(projectSprintLogic.getProjectSprints(id, page, maxRecords));
        }
        return ProjectSprintConverter.listEntity2DTO(projectSprintLogic.getProjectSprints(id));
    }

    /**
     * Obtiene los datos de una instancia de ProjectSprint a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de ProjectSprintDTO con los datos del ProjectSprint consultado y sus Review
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public ProjectSprintDTO getProjectSprint(@PathParam("id") Long id) {
        return ProjectSprintConverter.fullEntity2DTO(projectSprintLogic.getProjectSprint(id));
    }

    /**
     * Se encarga de crear un artifact en la base de datos.
     *
     * @param dto Objeto de ProjectSprintDTO con los datos nuevos
     * @return Objeto de ProjectSprintDTO con los datos nuevos y su ID.
     * @generated
     */
    @MPLoCAnn(tier = "Service", reqId = "R200")
    @POST
    @StatusCreated
    public ProjectSprintDTO createProjectSprint(ProjectSprintDTO dto) {
        ProjectSprintEntity entity = ProjectSprintConverter.fullDTO2Entity(dto);
        return ProjectSprintConverter.fullEntity2DTO(projectSprintLogic.createProjectSprint(entity));
    }

    /**
     * Actualiza la información de una instancia de ProjectSprint.
     *
     * @param id Identificador de la instancia de ProjectSprint a modificar
     * @param dto Instancia de ProjectSprintDTO con los nuevos datos.
     * @return Instancia de ProjectSprintDTO con los datos actualizados.
     * @generated
     */
    @MPLoCAnn(tier = "Service", reqId = "R201")
    @PUT
    @Path("{id: \\d+}")
    public ProjectSprintDTO updateProjectSprint(@PathParam("id") Long id, ProjectSprintDTO dto) {
        ProjectSprintEntity entity = ProjectSprintConverter.fullDTO2Entity(dto);
        entity.setId(id);
        return ProjectSprintConverter.fullEntity2DTO(projectSprintLogic.updateProjectSprint(entity));
    }

    /**
     * Elimina una instancia de ProjectSprint de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @generated
     */
    @MPLoCAnn(tier = "Service", reqId = "R202")
    @DELETE
    @Path("{id: \\d+}")
    public void deleteProjectSprint(@PathParam("id") Long id) {
        projectSprintLogic.deleteProjectSprint(id);
    }

    /**
     * Asocia un Artifact existente a un Project
     *
     * @param projectSprintId Identificador de la instancia de ProjectSprint
     * @param artifactId Identificador de la instancia de Artifact
     * @return Instancia de SkillDTO que fue asociada a Project
     * @generated
     */
    @POST
    @Path("{projectSprintId: \\d+}/artifacts/{artifactId: \\d+}")
    public ArtifactDTO addArtifacts(@PathParam("projectSprintId") Long projectSprintId, @PathParam("artifactId") Long artifactId) {
        return ArtifactConverter.fullEntity2DTO(projectSprintLogic.addArtifacts(projectSprintId, artifactId));
    }

    /**
     * Desasocia un Artifact existente de un Project existente
     *
     * @param projectSprintId Identificador de la instancia de ProjectSprint
     * @param artifactId Identificador de la instancia de Artifact
     * @generated
     */
    @DELETE
    @Path("{projectSprintId: \\d+}/artifacts/{artifactId: \\d+}")
    public void removeArtifacts(@PathParam("projectSprintId") Long projectSprintId, @PathParam("artifactId") Long artifactId) {
        projectSprintLogic.removeArtifacts(projectSprintId, projectSprintId);
    }
}
