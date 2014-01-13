package fourx.server.rest;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import fourx.server.rest.example.HelloResource;

/**
 * Resource configuration for running the REST server.
 * 
 * @author Jani Kaarela (@gmail.com)
 */
public class RestApplication extends ResourceConfig {
    
    public RestApplication() {
	register(HelloResource.class);
	register(JacksonFeature.class);
    }
    
}
