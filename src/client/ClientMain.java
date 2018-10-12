package client;

import java.io.IOException;

/**
 * Client
 * <p>
 * Client entry point.
 * Starts GUI and connection to server
 *
 * @author Team 7
 * @version 1.0
 */
public class ClientMain {
    /**
     * Starts the client UI
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        ClientUI clientui = new ClientUI();
    }
}