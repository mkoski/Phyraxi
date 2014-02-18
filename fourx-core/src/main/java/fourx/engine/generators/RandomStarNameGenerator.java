package fourx.engine.generators;

import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Random star name generator. Keeps track of generated names to avoid producing
 * duplicates. Generating an insane number of names potentially leads to an
 * indefinite loop, but in practice this should not be a problem.
 * 
 * @author Jani Kaarela (@gmail.com)
 */
public class RandomStarNameGenerator implements StarNameGenerator {
	
	private static String[] ONSETS = {
		"b", "bh", "c", "ch", "d", "dh", "f", "g", "gh", "h", "j", "k", "kh",
		"l", "lh", "m", "n", "p", "ph", "q", "r", "s", "sc", "sh", "sk", "st",
		"t", "th", "v", "w", "x", "y", "z", "zh"
	};
	private static String[] VOWELS = {
		"a", "e", "i", "o", "u", "y"
	};
	private static String[] MULTIVOWELS = {
		"aa", "ae", "ai", "ao", "au", "ay",
		"ea", "ee", "ei", "eo", "eu", "ey",
		"ia", "ie", "ii", "io", "iu", "iy",
		"oa", "oe", "oi", "oo", "ou", "oy",
		"ua", "ue", "ui", "uo", "uu", "uy",
		"ya", "ye", "yi", "yo", "yu", "yy"
	};
	private static String[] CONSONANTS = {
		"b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r",
		"s", "t", "v", "w", "x", "z"
	};
	private static String[] OFFSETS = {
		"bb", "bh", "ch", "ck", "ckh", "dh", "ff", "kh", "kz", "mz", "nt",
		"nz", "ph", "rch", "rk", "rz", "sch", "sck", "sh", "sk", "st", "sz",
		"tch", "th", "tz", "vz", "xx", "zh", "zz"
	};
	
	private final SortedSet<String> generatedNames = new TreeSet<>();
	private final Random random = new Random();
	
	public String generateName() {
		String name = generateCandidateName();
		while (generatedNames.contains(name)) {
			name = generateCandidateName();
		}
		generatedNames.add(name);
		return name;
	}
	
	private String generateCandidateName() {
		StringBuilder sb = new StringBuilder();
		if (random.nextFloat() < 0.35) {
			sb.append(generateWord());
			if (random.nextFloat() < 0.75) {
				sb.append(" ");
			} else {
				sb.append('-');
			}
		}
		sb.append(generateWord());
		return sb.toString();
	}
	
	private String generateWord() {
		StringBuilder sb = new StringBuilder();
		int syllables = 1;
		float randomFloat = random.nextFloat();
		if (randomFloat > 0.3) {
			syllables = 2;
		} else if (randomFloat > 0.7) {
			syllables = 3;
		} else if (randomFloat > 0.85) {
			syllables = 4;
		} else if (randomFloat > 0.95) {
			syllables = 5;
		}
		for(int i = 0; i < syllables; i++) {
			addSyllable(sb, ((i + 1) == syllables));
		}
		String word = sb.toString();
		word = Character.toUpperCase(word.charAt(0)) + word.substring(1);
		return word;
	}
	
	private void addSyllable(StringBuilder sb, boolean isLast) {
		if (random.nextFloat() < 0.8) { // onset
			sb.append(ONSETS[random.nextInt(ONSETS.length)]);
		}
		if (random.nextFloat() < 0.7) { // single vowel
			sb.append(VOWELS[random.nextInt(VOWELS.length)]);
		} else { // multivowel
			sb.append(MULTIVOWELS[random.nextInt(MULTIVOWELS.length)]);
		}
		if (isLast) {
			float randomFloat = random.nextFloat();
			if (randomFloat < 0.5) {
				sb.append(OFFSETS[random.nextInt(OFFSETS.length)]);
			} else if (randomFloat < 0.75){
				sb.append(CONSONANTS[random.nextInt(CONSONANTS.length)]);
			}
		} else if (random.nextBoolean()) { // ends with consonant
			sb.append(CONSONANTS[random.nextInt(CONSONANTS.length)]);
		}
	}

}
