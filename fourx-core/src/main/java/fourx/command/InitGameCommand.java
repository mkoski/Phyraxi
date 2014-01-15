package fourx.command;

/**
 * Command for initializing a new game session.
 */
public class InitGameCommand extends BaseCommand {

	private GameSettings settings;

	/**
	 * Creates an initialize game command with given game settings.
	 * 
	 * @param settings
	 *            game settings
	 */
	public InitGameCommand(GameSettings settings) {
		this.settings = settings;
		/*
		 * TODO: should assign a generated unique game session id here? or
		 * generate it after generating the galaxy and session, return the id in
		 * the reply and store game settings in a map as the id as the key?
		 */
	}

	@Override
	public CommandType getType() {
		return CommandType.INIT_GAME;
	}

	@Override
	public String toString() {
		return getType().toString() + " [" + "\n" + settings.toString()
		// + ",\n" + game session id
				+ "\n]";
	}

}
