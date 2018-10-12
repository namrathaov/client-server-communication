package server;

/**
 * Server
 *
 * Server entry point.
 * Starts GUI, listens for client connections and sends responses to client
 *
 * @author Team 7
 * @version 1.0
 */
public class ServerMain {
    /**
     * Starts the server UI
     * @param args
     */
    public static void main(String[] args) {
         ServerUI serverUI = new ServerUI();
         serverUI.initialize();
    }
}
