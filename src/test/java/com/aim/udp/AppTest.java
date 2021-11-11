package com.aim.udp;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.aim.udp.client.EchoClient;
import com.aim.udp.server.EchoServer;

public class AppTest {

	private EchoClient client;

	@Before
	public void setup() throws SocketException, UnknownHostException {

		new EchoServer().start();
		client = new EchoClient();

	}

	@Test
	public void whenCanSendAndReceivePacket_thenCorrect() throws IOException {
		String echo = client.sendEcho("ping");
		assertEquals("ping", echo);
		echo = client.sendEcho("pong");
		assertFalse(echo.equals("ping"));
	}

	@After
	public void tearDown() throws IOException {

		client.sendEcho("end");

		client.close();

	}
}
