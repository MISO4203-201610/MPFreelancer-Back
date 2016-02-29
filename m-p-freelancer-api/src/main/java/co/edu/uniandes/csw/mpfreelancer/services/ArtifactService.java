package co.edu.uniandes.csw.mpfreelancer.services;

import co.edu.uniandes.csw.auth.provider.StatusCreated;
import co.edu.uniandes.csw.mp.ann.MPLoCAnn;
import co.edu.uniandes.csw.mpfreelancer.api.IArtifactLogic;
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
import co.edu.uniandes.csw.mpfreelancer.dtos.ArtifactDTO;
import co.edu.uniandes.csw.mpfreelancer.entities.ArtifactEntity;
import co.edu.uniandes.csw.mpfreelancer.converters.ArtifactConverter;

/**
 * @generated
 */
@Path("/artifacts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ArtifactService {

    @Inject private IArtifactLogic artifactLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("maxRecords") private Integer maxRecords;
    
    @GET
    @Path("/sprints/{id: \\d+}")
    public List<ArtifactDTO> getAllArtifactsFromSprint(@PathParam("id") Long id) {        
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", artifactLogic.countArtifacts(id));
            return ArtifactConverter.listEntity2DTO(artifactLogic.getArtifacts(id, page, maxRecords));
        }
        return ArtifactConverter.listEntity2DTO(artifactLogic.getArtifacts(id));
    }

    /**
     * Obtiene los datos de una instancia de Artifact a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de ArtifactDTO con los datos del Artifact consultado y sus Review
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public ArtifactDTO getArtifact(@PathParam("id") Long id) {
        return ArtifactConverter.fullEntity2DTO(artifactLogic.getArtifact(id));
    }

    /**
     * Se encarga de crear un artifact en la base de datos.
     *
     * @param dto Objeto de ArtifactDTO con los datos nuevos
     * @return Objeto de ArtifactDTO con los datos nuevos y su ID.
     * @generated
     */
    @MPLoCAnn(tier = "Service", reqId = "R100")
    @POST
    @StatusCreated
    public ArtifactDTO createArtifact(ArtifactDTO dto) {
        ArtifactEntity entity = ArtifactConverter.fullDTO2Entity(dto);
        return ArtifactConverter.fullEntity2DTO(artifactLogic.createArtifact(entity));
    }

    /**
     * Actualiza la información de una instancia de Artifact.
     *
     * @param id Identificador de la instancia de Artifact a modificar
     * @param dto Instancia de ArtifactDTO con los nuevos datos.
     * @return Instancia de ArtifactDTO con los datos actualizados.
     * @generated
     */
    @MPLoCAnn(tier = "Service", reqId = "R101")
    @PUT
    @Path("{id: \\d+}")
    public ArtifactDTO updateArtifact(@PathParam("id") Long id, ArtifactDTO dto) {
        ArtifactEntity entity = ArtifactConverter.fullDTO2Entity(dto);
        entity.setId(id);
        return ArtifactConverter.fullEntity2DTO(artifactLogic.updateArtifact(entity));
    }

    /**
     * Elimina una instancia de Artifact de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @generated
     */
    @MPLoCAnn(tier = "Service", reqId = "R102")
    @DELETE
    @Path("{id: \\d+}")
    public void deleteArtifact(@PathParam("id") Long id) {
        artifactLogic.deleteArtifact(id);
    }
}
