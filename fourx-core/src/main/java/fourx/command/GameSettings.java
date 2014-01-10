package fourx.command;

/**
 * Immutable object representing game settings. Use {@link Builder} to
 * instantiate.
 */
public class GameSettings {

	public static final int DEFAULT_GALAXY_SIZE = 10;
	public static final int DEFAULT_NUMBER_OF_CLUSTERS = 4;
	public static final GameSettings DEFAULT = new Builder().setGalaxySize(DEFAULT_GALAXY_SIZE).build();

	private int galaxySize;
	private int numberOfClusters;

	private GameSettings() {
		// suppress default constructor
	}

	/**
	 * Gets the game galaxy size.
	 * 
	 * @return number of star systems in the game galaxy.
	 */
	public int getGalaxySize() {
		return galaxySize;
	}

	/**
	 * Gets the number of clusters. The meaning of &quot;cluster&uot; may vary
	 * depending on the game galaxy type.
	 * 
	 * @return number of star clusters in the game galaxy.
	 */
	public int getNumberOfClusters() {
		return numberOfClusters;
	}

	public String toString() {
		return "Game settings: galaxy size " + galaxySize + "; number of clusters " + numberOfClusters;
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
		 * @param starSystems
		 *            number of star systems in the game galaxy.
		 * 
		 * @return builder.
		 */
		public Builder setGalaxySize(int starSystems) {
			settings.galaxySize = starSystems;
			return this;
		}

		public Builder setNumberOfClusters(int numberOfClusters) {
			settings.numberOfClusters = numberOfClusters;
			return this;
		}

		/**
		 * Returns the built game settings.
		 * 
		 * @return game settings.
		 */
		public GameSettings build() {
			return settings;
		}
	}

}
