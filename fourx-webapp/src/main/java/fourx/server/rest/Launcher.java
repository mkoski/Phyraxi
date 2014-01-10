package fourx.server.rest;

import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * 
 * @author Jani Kaarela (@gmail.com)
 */
public class Launcher {
    
    //TODO: configurable port etc...
    
    public static void main(String[] args) throws Exception {
	URI baseUri = UriBuilder.fromUri("http://localhost/").port(8080).build();
	ResourceConfig config = new ResourceConfig(HelloResource.class).register(JacksonFeature.class);
	Server server = JettyHttpContainerFactory.createServer(baseUri, config);
	server.start();
    }
    
    @Path("/hello")
    public static class HelloResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public HelloReply hello() {
	    return HelloReply.INSTANCE;
	}
    }
    
    public static class HelloReply {
	private static final String REPLY = "Hello, galaxy!";
	private static final HelloReply INSTANCE = new HelloReply();
	public String getReply() {
	    return REPLY;
	}
    }
    
}
