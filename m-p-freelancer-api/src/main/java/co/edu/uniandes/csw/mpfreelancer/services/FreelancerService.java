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
import co.edu.uniandes.csw.mpfreelancer.api.IFreelancerLogic;
import co.edu.uniandes.csw.mpfreelancer.dtos.FreelancerDTO;
import co.edu.uniandes.csw.mpfreelancer.entities.FreelancerEntity;
import co.edu.uniandes.csw.mpfreelancer.converters.FreelancerConverter;
import co.edu.uniandes.csw.mpfreelancer.dtos.SkillDTO;
import co.edu.uniandes.csw.mpfreelancer.converters.SkillConverter;
import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.group.Group;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 * @generated
 */
@Path("/freelancers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FreelancerService {
    private static final String FREELANCER_HREF = "https://api.stormpath.com/v1/groups/mNePGgZXIo2wW01EvWRzl";
    private static final String ADMIN_HREF = "https://api.stormpath.com/v1/groups/aDD8ajP65mhgxRNvASTfl";    

    @Inject private IFreelancerLogic freelancerLogic;
    @Context private HttpServletRequest req;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("maxRecords") private Integer maxRecords;

    /**
     * Obtiene la lista de los registros de Book.
     *
     * @return Colección de objetos de FreelancerDTO cada uno con sus respectivos Review
     * @generated
     */
    @GET
    public List<FreelancerDTO> getFreelancers() {
        String accountHref = req.getRemoteUser();
        if (accountHref != null) {
            Account account = getClient().getResource(accountHref, Account.class);
            for (Group gr : account.getGroups()) {
                switch (gr.getHref()) {
                    case ADMIN_HREF:
                        if (page != null && maxRecords != null) {
                        this.response.setIntHeader("X-Total-Count", freelancerLogic.countFreelancers());
                        return FreelancerConverter.listEntity2DTO(freelancerLogic.getFreelancers(page, maxRecords));
                        }
                        return FreelancerConverter.listEntity2DTO(freelancerLogic.getFreelancers());                    
                    case FREELANCER_HREF:                     
                        Integer id = (int) account.getCustomData().get("freelancer_id");
                        List<FreelancerDTO> list = new ArrayList();
                        list.add(FreelancerConverter.fullEntity2DTO(freelancerLogic.getFreelancer(id.longValue())));
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
     * @return Instancia de FreelancerDTO con los datos del Book consultado y sus Review
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public FreelancerDTO getFreelancer(@PathParam("id") Long id) {
        return FreelancerConverter.fullEntity2DTO(freelancerLogic.getFreelancer(id));
    }
    
    @GET
    @Path("/current")    
    public FreelancerDTO getCurrentFreelancer() {
        String accountHref = req.getRemoteUser();
        if (accountHref != null) {
            Account account = getClient().getResource(accountHref, Account.class);            
            Integer id=(int)account.getCustomData().get("freelancer_id");            
            return FreelancerConverter.fullEntity2DTO(freelancerLogic.getFreelancer(id.longValue()));
        } else {
            return null;
        }
    }

    /**
     * Se encarga de crear un book en la base de datos.
     *
     * @param dto Objeto de FreelancerDTO con los datos nuevos
     * @return Objeto de FreelancerDTO con los datos nuevos y su ID.
     * @generated
     */
    @POST
    @StatusCreated
    public FreelancerDTO createFreelancer(FreelancerDTO dto) {
        FreelancerEntity entity = FreelancerConverter.fullDTO2Entity(dto);
        return FreelancerConverter.fullEntity2DTO(freelancerLogic.createFreelancer(entity));
    }

    /**
     * Actualiza la información de una instancia de Book.
     *
     * @param id Identificador de la instancia de Book a modificar
     * @param dto Instancia de FreelancerDTO con los nuevos datos.
     * @return Instancia de FreelancerDTO con los datos actualizados.
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public FreelancerDTO updateFreelancer(@PathParam("id") Long id, FreelancerDTO dto) {
        FreelancerEntity entity = FreelancerConverter.fullDTO2Entity(dto);
        entity.setId(id);
        return FreelancerConverter.fullEntity2DTO(freelancerLogic.updateFreelancer(entity));
    }

    /**
     * Elimina una instancia de Book de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteFreelancer(@PathParam("id") Long id) {
        freelancerLogic.deleteFreelancer(id);
    }

    /**
     * Obtiene una colección de instancias de SkillDTO asociadas a una
     * instancia de Freelancer
     *
     * @param freelancerId Identificador de la instancia de Freelancer
     * @return Colección de instancias de SkillDTO asociadas a la instancia de Freelancer
     * @generated
     */
    @GET
    @Path("{freelancerId: \\d+}/skills")
    public List<SkillDTO> listSkills(@PathParam("freelancerId") Long freelancerId) {
        return SkillConverter.listEntity2DTO(freelancerLogic.listSkills(freelancerId));
    }

    /**
     * Obtiene una instancia de Skill asociada a una instancia de Freelancer
     *
     * @param freelancerId Identificador de la instancia de Freelancer
     * @param skillId Identificador de la instancia de Skill
     * @generated
     */
    @GET
    @Path("{freelancerId: \\d+}/skills/{skillId: \\d+}")
    public SkillDTO getSkills(@PathParam("freelancerId") Long freelancerId, @PathParam("skillId") Long skillId) {
        return SkillConverter.fullEntity2DTO(freelancerLogic.getSkills(freelancerId, skillId));
    }

    /**
     * Asocia un Skill existente a un Freelancer
     *
     * @param freelancerId Identificador de la instancia de Freelancer
     * @param skillId Identificador de la instancia de Skill
     * @return Instancia de SkillDTO que fue asociada a Freelancer
     * @generated
     */
    @POST
    @Path("{freelancerId: \\d+}/skills/{skillId: \\d+}")
    public SkillDTO addSkills(@PathParam("freelancerId") Long freelancerId, @PathParam("skillId") Long skillId) {
        return SkillConverter.fullEntity2DTO(freelancerLogic.addSkills(freelancerId, skillId));
    }

    /**
     * Remplaza las instancias de Skill asociadas a una instancia de Freelancer
     *
     * @param freelancerId Identificador de la instancia de Freelancer
     * @param skills Colección de instancias de SkillDTO a asociar a instancia de Freelancer
     * @return Nueva colección de SkillDTO asociada a la instancia de Freelancer
     * @generated
     */
    @PUT
    @Path("{freelancerId: \\d+}/skills")
    public List<SkillDTO> replaceSkills(@PathParam("freelancerId") Long freelancerId, List<SkillDTO> skills) {
        return SkillConverter.listEntity2DTO(freelancerLogic.replaceSkills(freelancerId, SkillConverter.listDTO2Entity(skills)));
    }

    /**
     * Desasocia un Skill existente de un Freelancer existente
     *
     * @param freelancerId Identificador de la instancia de Freelancer
     * @param skillId Identificador de la instancia de Skill
     * @generated
     */
    @DELETE
    @Path("{freelancerId: \\d+}/skills/{skillId: \\d+}")
    public void removeSkills(@PathParam("freelancerId") Long freelancerId, @PathParam("skillId") Long skillId) {
        freelancerLogic.removeSkills(freelancerId, skillId);
    }
}
