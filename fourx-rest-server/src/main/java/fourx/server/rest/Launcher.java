package fourx.server.rest;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Launcher for running REST server in standalone mode.
 * 
 * @author Jani Kaarela (@gmail.com)
 */
public class Launcher {
    
    public static final String ADDRESS_PROPERTY_KEY = "fourx.server.rest.baseAddress";
    public static final String PORT_PROPERTY_KEY = "fourx.server.rest.port";
    static final String DEFAULT_ADDRESS = "http://localhost/";
    static final int DEFAULT_PORT = 8080;
    
    public static void main(String[] args) throws Exception {
	URI baseUri = UriBuilder.fromUri(determineBaseAddress()).port(determinePort()).build();
	ResourceConfig config = new RestApplication();
	Server server = JettyHttpContainerFactory.createServer(baseUri, config);
	server.start();
    }
    
    static String determineBaseAddress() {
	return System.getProperty(ADDRESS_PROPERTY_KEY, DEFAULT_ADDRESS);
    }
    
    static int determinePort() {
	return Integer.parseInt(System.getProperty(PORT_PROPERTY_KEY, String.valueOf(DEFAULT_PORT)));
    }
    
}
