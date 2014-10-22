package phyraxi.engine.state;

import java.util.ArrayList;
import java.util.List;

import phyraxi.domain.Player;
import phyraxi.domain.StarSystem;

public class Game {
	
	private final List<StarSystem> universe;
	private final List<Player> players;
	private final int maxPlayers;
	
	public Game(List<StarSystem> universe, int maxPlayers) {
		this.universe = universe;
		this.maxPlayers = maxPlayers; 
		players = new ArrayList<>(maxPlayers);
	}
	
	public List<StarSystem> getUniverse() {
		return universe;
	}
	
	public boolean addPlayer(Player player) {
		if (players.size() < maxPlayers) {
			return players.add(player);
		}
		return false;
	}
	
	public List<Player> getPlayers() {
		return players;
	}

}
