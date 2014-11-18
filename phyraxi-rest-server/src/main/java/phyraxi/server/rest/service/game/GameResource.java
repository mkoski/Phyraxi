package phyraxi.server.rest.service.game;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import phyraxi.engine.commands.NewGame;

@Path("game")
@Produces(MediaType.APPLICATION_JSON)
public class GameResource {
	
	@POST @Path("new") @Consumes(MediaType.APPLICATION_JSON)
	public GameInfo createGame(NewGame newGame) {
		// TODO
		return null;
	}

}
