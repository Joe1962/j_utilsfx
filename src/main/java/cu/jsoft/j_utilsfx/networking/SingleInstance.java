/*
 * Copyright Joe1962
 * https://github.com/Joe1962
 */
package cu.jsoft.j_utilsfx.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author joe1962
 */
public class SingleInstance extends Thread {

	ServerSocket serverSocket = null;
	Socket clientSocket = null;

	public void StartServer(final int MyPort) throws IOException {
		while (true) {
		// Wait for a connection
			// Create the server socket
			serverSocket = new ServerSocket(MyPort, 1);
			//System.out.println("*** Server socket started... ");
			// Run forever, accepting and servicing connections:
			for (;;) {
				clientSocket = serverSocket.accept();
				//System.out.println("*** Got a connection! ");
			}
			// System.out.println("*** Got a connection! ");
			//clientSocket.close();
		}
	}

	public int getInstances(int MyPort) {
		try {
			Socket MySocket = new Socket("localhost", MyPort);
			//System.out.println("*** Already running!");
			MySocket.close();
			return 1;
		} catch (UnknownHostException uhe) {
			return -1;
		} catch (IOException ioe) {
			return 0;
		} catch (SecurityException se) {
			return -1;
		} catch (IllegalArgumentException iae) {
			return -1;
		}
	}
}
