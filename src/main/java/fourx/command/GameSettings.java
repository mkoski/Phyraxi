package fourx.command;

/**
 * Immutable object representing game settings. Use {@link Builder} to instantiate.
 */
public class GameSettings {
    
    private int galaxySize;
    
    private GameSettings() {
	// suppress default constructor
    }
    
    /**
     * Gets the game galaxy size.
     * 
     * @return	number of star systems in the game galaxy.
     */
    public int getGalaxySize() {
	return galaxySize;
    }
    
    public String toString() {
	return "Game settings: galaxy size " + galaxySize;
    }
    
    /**
     * Builder for {@link GameSettings}.
     */
    public static class Builder {
	
	private GameSettings settings;
	
	public Builder() {
	    settings = new GameSettings();
	}
	
	/**
	 * Sets galaxy size to <code>starSystems</code> star systems.
	 * 
	 * @param starSystems	number of star systems in the game galaxy.
	 * 
	 * @return	builder.
	 */
	public Builder setGalaxySize(int starSystems) {
	    settings.galaxySize = starSystems;
	    return this;
	}
    }

}
