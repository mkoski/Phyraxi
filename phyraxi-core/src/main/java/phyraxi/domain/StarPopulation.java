package phyraxi.domain;

/**
 * Star populations depicting the generation and location of stars.
 * 
 * @author Jani Kaarela (@gmail.com)
 */
public enum StarPopulation {
	/** Very young stars in the core, metal-rich, too young to have a planetary system. */
	CORE_POPULATION_I,
	/** Young stars near the core, metal-rich and likely to have a planetary system. */
	INTERMEDIATE_POPULATION_I,
	/** Young stars in the galactic disc, metal-rich and likely to have a planetary system. */
	DISC_POPULATION_I,
	/** Old stars in the galactic disc, metal-poor and unlikely to have a planetary system. */
	DISC_POPULATION_II,
	/** Old population in the galactic halo; metal-poor, very unlikely to have planets. */
	HALO_POPULATION_II;
}