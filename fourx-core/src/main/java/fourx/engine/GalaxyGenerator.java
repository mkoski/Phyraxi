package fourx.engine;

import java.util.ArrayList;
import java.util.List;

import fourx.command.GameSettings;
import fourx.domain.Coordinates;
import fourx.domain.LuminosityClass;
import fourx.domain.SpectralType;
import fourx.domain.Star;
import fourx.domain.StarSystem;
import fourx.domain.StarSystem.StarHierarchy;

/**
 * 
 */
public class GalaxyGenerator {

	private static final Star SOL = new Star("Sol", SpectralType.G, 2, LuminosityClass.MAIN_SEQUENCE, 1.0, 1.0, 5778);
	private static final Star ALPHA_CENTAURI_A = new Star("Alpha Centauri A", SpectralType.G, 2,
			LuminosityClass.MAIN_SEQUENCE, 1.1, 1.52, 5800);
	private static final Star ALPHA_CENTAURI_B = new Star("Alpha Centauri B", SpectralType.K, 2,
			LuminosityClass.MAIN_SEQUENCE, 0.91, 0.45, 4900);

	/*
	 * TODO GalaxyGenerator constructor which takes parameters for example: -
	 * x/y/z dimension(s) - number of star systems - clustering parameters -
	 * specific homeworlds to generate
	 */

	public List<StarSystem> createGalaxy() {
		return createGalaxy(GameSettings.DEFAULT);
	}

	public List<StarSystem> createGalaxy(GameSettings gameSettings) {
		// TODO an actual, non-dummy implementation of createGalaxy()
		StarSystem solarSystem = new StarSystem("Solar System", new Coordinates(0, 0, 0),
				StarHierarchy.createSingleStar(SOL));
		StarSystem alphaCentauri = new StarSystem("Alpha Centauri", new Coordinates(4, 0, 1),
				StarHierarchy.createBinaryPair(ALPHA_CENTAURI_A, ALPHA_CENTAURI_B));
		List<StarSystem> galaxy = new ArrayList<>(2);
		galaxy.add(solarSystem);
		galaxy.add(alphaCentauri);
		return galaxy;
	}

}
