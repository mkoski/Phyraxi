package fourx.engine.generators;

import java.util.List;

import fourx.domain.Coordinates;

/**
 * 
 */
public interface ClusterGenerator {

	/**
	 * Generates clusters.
	 * 
	 * @param clusters
	 *            number of clusters.
	 * @param stars
	 *            total number of stars.
	 * 
	 * @return list of clusters.
	 */
	List<Cluster> generateClusters(int clusters, int stars);

	static class Cluster {
		final Coordinates center;
		final List<Coordinates> stars;

		Cluster(Coordinates center, List<Coordinates> stars) {
			this.center = center;
			this.stars = stars;
		}
	}

}
