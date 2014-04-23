package phyraxi.domain;

public enum PlanetType {
	
	CARBON_PLANETOID(ArcheType.PLANETOID),
	SILICATE_PLANETOID(ArcheType.PLANETOID),
	METALLIC_PLANETOID(ArcheType.PLANETOID),
	CORELESS_TERRESTRIAL(ArcheType.TERRESTRIAL),
	CARBON_TERRESTRIAL(ArcheType.TERRESTRIAL),
	SILICATE_TERRESTRIAL(ArcheType.TERRESTRIAL),
	METALLIC_TERRESTRIAL(ArcheType.TERRESTRIAL),
	GAS_GIANT(ArcheType.JOVIAN),
	ICE_GIANT(ArcheType.JOVIAN);
	
	private ArcheType archeType;
	
	private PlanetType(ArcheType archeType) {
		this.archeType = archeType;
	}
	
	public ArcheType getArcheType() {
		return archeType;
	}
	
	public static enum ArcheType {
		
		PLANETOID,
		TERRESTRIAL,
		JOVIAN;
		
	}
}