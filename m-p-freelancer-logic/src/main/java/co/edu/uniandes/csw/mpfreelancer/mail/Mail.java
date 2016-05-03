package co.edu.uniandes.csw.mpfreelancer.mail;

import javax.ws.rs.core.MediaType;

/**
 *
 * @author Nicolas
 */
public class Mail {
    
    public Mail(String email, String subject, String body) {
        
        /*Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("api", System.getenv("MAILAPI")));
        
        WebResource webResource =
            client.resource("https://api.mailgun.net/v3/sandbox04d8a2fbbb8946f4bccdba3e4dbed84f.mailgun.org/messages");
        
        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        formData.add("from", "Mailgun Sandbox <postmaster@sandbox04d8a2fbbb8946f4bccdba3e4dbed84f.mailgun.org>");
        formData.add("to", "Freelancers " + email + ">");
        formData.add("subject", subject);
        formData.add("text", body);
        webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, formData);*/
    }
}
