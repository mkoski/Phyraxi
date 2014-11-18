package phyraxi.server.rest;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import phyraxi.server.rest.example.HelloResource;
import phyraxi.server.rest.service.game.GameResource;
import phyraxi.server.rest.service.starmap.StarMapResource;


/**
 * Resource configuration for running the REST server.
 * 
 * @author Jani Kaarela (@gmail.com)
 */
public class RestApplication extends ResourceConfig {

	public RestApplication() {
		register(HelloResource.class);
		register(StarMapResource.class);
		register(GameResource.class);
		register(JacksonFeature.class);
	}

}
