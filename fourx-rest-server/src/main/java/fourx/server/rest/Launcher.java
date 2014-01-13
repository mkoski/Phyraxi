package fourx.server.rest;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.eclipse.jetty.server.Server;
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
	ResourceConfig config = new RestApplication();
	Server server = JettyHttpContainerFactory.createServer(baseUri, config);
	server.start();
    }
    
}
