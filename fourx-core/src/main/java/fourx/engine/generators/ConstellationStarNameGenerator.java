package fourx.engine.generators;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * A star name generator that randomly combines a Greek alphabet and a constellation
 * name to generate a star name.
 * 
 * @author Jani Kaarela (@gmail.com)
 */
public class ConstellationStarNameGenerator implements StarNameGenerator {
	
	private static final String[] GREEK_APLHABET = {
		"Alpha", "Beta", "Gamma", "Delta", "Epsilon", "Zeta", "Eta", "Theta",
		"Iota", "Kappa", "Lambda", "Mu", "Nu", "Xi", "Omicron", "Pi", "Rho",
		"Sigma", "Tau", "Upsilon", "Phi", "Chi", "Psi", "Omega"
	};
	private static final String[] CONSTELLATIONS = {
		"Andromedae", "Antliae", "Apodis", "Aquarii", "Aquilae", "Arae",
		"Arietis", "Aurigae", "Boötis", "Caeli", "Camelopardalis", "Cancri",
		"Canum Venaticorum", "Canis Majoris", "Canis Minoris", "Capricorni",
		"Carinae", "Cassiopeiae", "Centauri", "Cephei", "Ceti",
		"Chamaeleontis", "Circini", "Columbae", "Comae Berenices",
		"Coronae Australis", "Coronae Borealis", "Corvi", "Crateris",
		"Crucis", "Cygni", "Delphini", "Doradus", "Draconis", "Equulei",
		"Eridani", "Fornacis", "Geminorum", "Gruis", "Herculis", "Horologii",
		"Hydrae", "Hydri", "Indi", "Lacertae", "Leonis", "Leonis Minoris",
		"Leporis", "Librae", "Lupi", "Lyncis", "Lyrae", "Mensae",
		"Microscopii", "Monocerotis", "Muscae", "Normae", "Octantis",
		"Ophiuchi", "Orionis", "Pavonis", "Pegasi", "Persei", "Phoenicis",
		"Pictoris", "Piscium", "Piscis Austrini", "Puppis", "Pyxidis",
		"Reticuli", "Sagittae", "Sagittarii", "Scorpii", "Sculptoris",
		"Scuti", "Serpentis", "Sextantis", "Tauri", "Telescopii", "Trianguli",
		"Trianguli Australis", "Tucanae", "Ursae Majoris", "Ursae Minoris",
		"Velorum", "Virginis", "Volantis", "Vulpeculae"
	};
	private static final int MAX_RETRIES = 10;
	private final Set<String> generatedNames = new HashSet<>(50);

	public String generateName() {
		String generatedName = null;
		Random random = new Random();
		for (int i = 0; i < MAX_RETRIES; i++) {
			int alphabetIndex = random.nextInt(GREEK_APLHABET.length);
			int constellationIndex = random.nextInt(CONSTELLATIONS.length);
			generatedName = GREEK_APLHABET[alphabetIndex] + ' ' + CONSTELLATIONS[constellationIndex];
			if (generatedNames.contains(generatedName)) {
				generatedName = null;
			} else {
				generatedNames.add(generatedName);
				break;
			}
		}
		if (generatedName == null) {
			// TODO: implement a "full random" fall back mechanism?
			throw new IllegalStateException("Failed to generate a unique name!");
		}
		return generatedName;
	}

}
