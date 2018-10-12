package server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import network.Register;
import network.Request;
import network.Response;
import util.Constants;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * ServerConnection
 *
 * Creates socket server and listens for client requests
 *
 * @author Team 7
 * @version 1.0
 */
public class ServerConnection {

    private Server server;
    private ServerData serverData;

    /**
     * constructor to initialize server client connection
     */
    public ServerConnection() {
        serverData = new ServerData();
        server = new Server();
    }

    public void setFrequency(int frequency) {
        serverData.setFrequency(frequency);
    }

    public int getFrequency() {
        return serverData.getFrequency();
    }

    public void setMax(int max) {
        serverData.setMax(max);
    }

    public void setMin(int min) {
        serverData.setMin(min);
    }

    public int getMax() {
        return serverData.getMax();
    }

    public int getMin() {
        return serverData.getMin();
    }

    /**
     * Start server, throws exception if port is already in use
     *
     */
    public void start() {
        server.start();

        try {
            server.bind(Constants.TCP_PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Register.register(server);

        addListener();
    }

    /**
     * Stop server and close connection
     *
     */
    public void stop() {
        server.close();
        server.stop();
    }

    /**
     * Starts listener for receiving client requests
     *
     */
    private void addListener() {
        server.addListener(new Listener() {
            public void received (Connection connection, Object object) {
                if (object instanceof Request) {
                    Request request = (Request) object;

                    System.out.println("Server received the request: " + request.getNumChannels());

                    buildResponse(connection.getID(), request.getNumChannels());
                }
            }
        });
    }

    /**
     * Creates the response to send back to the client
     *
     */
    private void buildResponse(int connectionId, int numChannels) {
        Timer timer = new Timer();
        timer.schedule(new SendResponse(connectionId, numChannels), 0, serverData.getResponseSeconds());
    }

    /**
     * Send Response
     *
     * Send a response to the client based on the frequency
     *
     * @author Team 7
     * @version 1.0
     */
    private class SendResponse extends TimerTask {
        private int connectionId;
        private int numChannels;

        public SendResponse(int connectionId, int numChannels) {
            this.connectionId = connectionId;
            this.numChannels = numChannels;
        }

        public void run() {
            Response response = new Response(serverData.generateChannelNumbers(numChannels),  serverData.getFrequency());
            server.sendToTCP(connectionId, response);
        }
    }
}
