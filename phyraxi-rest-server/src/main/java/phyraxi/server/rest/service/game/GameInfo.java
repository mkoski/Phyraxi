package phyraxi.server.rest.service.game;

import java.util.List;

import phyraxi.domain.game.Player;

public class GameInfo {
	
	public final int maxPlayers;
	public final List<Player> players;
	
	public GameInfo(int maxPlayers, List<Player> players) {
		this.maxPlayers = maxPlayers;
		this.players = players;
	}

}
