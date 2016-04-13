/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpfreelancer.services;

import co.edu.uniandes.csw.auth.model.UserDTO;
import co.edu.uniandes.csw.auth.service.AuthService;
import co.edu.uniandes.csw.mpfreelancer.api.IFreelancerLogic;
import co.edu.uniandes.csw.mpfreelancer.api.IProjectSponsorLogic;
import co.edu.uniandes.csw.mpfreelancer.entities.FreelancerEntity;
import co.edu.uniandes.csw.mpfreelancer.entities.ProjectSponsorEntity;
import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.group.Group;
import com.stormpath.sdk.resource.ResourceException;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author asistente
 */
public class UserService extends AuthService {
    
    @Inject private IFreelancerLogic freelancerLogic;
    @Inject private IProjectSponsorLogic sponsorLogic;
    private static final String FREELANCER_HREF = "https://api.stormpath.com/v1/groups/mNePGgZXIo2wW01EvWRzl";
    private static final String PROYECT_SPONSOR_HREF = "https://api.stormpath.com/v1/groups/w94UQuPl5386Zk91Numiv";    
    private static final String FREELANCER_CD = "freelancer_id";
    private static final String PROYECT_SPONSOR_CD = "projectSponsor_id";
    
    @Override
    public void register(UserDTO user) {
        try {
           Account acct = createUser(user);
        for (Group gr : acct.getGroups()) {
            switch(gr.getHref()){
                case FREELANCER_HREF:
                FreelancerEntity client = new FreelancerEntity();
                client.setName(user.getUserName());
                client = freelancerLogic.createFreelancer(client);
                acct.getCustomData().put(FREELANCER_CD, client.getId());
                break;
                case PROYECT_SPONSOR_HREF:
                ProjectSponsorEntity provider = new ProjectSponsorEntity();
                provider.setName(user.getUserName());                
                provider = sponsorLogic.createProjectSponsor(provider);
                acct.getCustomData().put(PROYECT_SPONSOR_CD, provider.getId());  
                break;
                default:
                 throw new WebApplicationException("Group link doen's match ");
            }
                
            }
        acct.getCustomData().save();
        } catch (ResourceException e) {
            throw new WebApplicationException(e, e.getStatus());
        }
    }
    
}
