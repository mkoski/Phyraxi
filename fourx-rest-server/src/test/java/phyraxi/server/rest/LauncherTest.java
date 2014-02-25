package phyraxi.server.rest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import phyraxi.server.rest.Launcher;

/**
 * Tests for {@link Launcher}.
 * 
 * @author Jani Kaarela (@gmail.com)
 */
public class LauncherTest {

	@Test
	public void shouldReturnDefaultAddress() {
		System.clearProperty(Launcher.ADDRESS_PROPERTY_KEY);
		String address = Launcher.determineBaseAddress();
		assertEquals(Launcher.DEFAULT_ADDRESS, address);
	}

	@Test
	public void shouldReturnDesiredAddress() {
		String desiredAddress = "http://xkcd.com/";
		System.setProperty(Launcher.ADDRESS_PROPERTY_KEY, desiredAddress);
		String returnedAddress = Launcher.determineBaseAddress();
		assertEquals(desiredAddress, returnedAddress);
	}

	@Test
	public void shouldReturnDefaultPort() {
		System.clearProperty(Launcher.PORT_PROPERTY_KEY);
		int port = Launcher.determinePort();
		assertEquals(Launcher.DEFAULT_PORT, port);
	}

	@Test
	public void shouldReturnDesiredPort() {
		int desiredPort = 12345;
		System.setProperty(Launcher.PORT_PROPERTY_KEY, String.valueOf(desiredPort));
		int returnedPort = Launcher.determinePort();
		assertEquals(desiredPort, returnedPort);
	}

}
