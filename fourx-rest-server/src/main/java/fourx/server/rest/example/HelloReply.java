package fourx.server.rest.example;

public class HelloReply {
	private static final String REPLY = "Hello, galaxy!";
	static final HelloReply INSTANCE = new HelloReply();

	public String getReply() {
		return REPLY;
	}
}