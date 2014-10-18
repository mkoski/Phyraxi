package phyraxi.domain.technology;

import java.util.Set;

/* 
 * Technology for technology tree
 * TODO: add technology advantage attributes
 */

public class Tech {

	private final TechType type;
	private final Set<TechType> preRequirements;
	private final String name;
	private final int researchTime; // Time unit = one round
	
	public Tech(TechType type, Set<TechType> preRequirements, String name, int researchTime) {
		this.type = type;
		this.preRequirements = preRequirements;
		this.name = name;
		this.researchTime = researchTime;
	}

	public TechType getType() {
		return type;
	}

	public Set<TechType> getPreRequirements() {
		return preRequirements;
	}

	public String getName() {
		return name;
	}

	public int getResearchTime() {
		return researchTime;
	}
	
 }
