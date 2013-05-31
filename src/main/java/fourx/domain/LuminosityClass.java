package fourx.domain;

/**
 * Star luminosity classes.
 */
public enum LuminosityClass {
    
    HYPERGIANT(Constants.HYPERGIANT_SYMBOL),
    SUPERGIANT(Constants.SUPERGIANT_SYMBOL),
    BRIGHT_GIANT(Constants.BRIGHT_GIANT_SYMBOL),
    GIANT(Constants.GIANT_SYMBOL),
    SUBGIANT(Constants.SUBGIANT_SYMBOL),
    MAIN_SEQUENCE(Constants.MAIN_SEQUENCE_SYMBOL),
    SUBDWARF(Constants.SUBDWARF_SYMBOL),
    WHITE_DWARF(Constants.WHITE_DWARF_SYMBOL);
    
    private String symbol;
    
    private LuminosityClass(String symbol) {
	this.symbol = symbol;
    }
    
    public String getSymbol() {
	return symbol;
    }

    private static class Constants {
	private static final String HYPERGIANT_SYMBOL = "0";
	private static final String SUPERGIANT_SYMBOL = "I";
	private static final String BRIGHT_GIANT_SYMBOL = "II";
	private static final String GIANT_SYMBOL =  "III";
	private static final String SUBGIANT_SYMBOL = "IV";
	private static final String MAIN_SEQUENCE_SYMBOL = "V";
	private static final String SUBDWARF_SYMBOL = "VI";
	private static final String WHITE_DWARF_SYMBOL = "VII";
    }
}
