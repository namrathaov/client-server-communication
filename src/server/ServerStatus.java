package server;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;

import javax.swing.JPanel;
import javax.swing.Timer;
/**
 *    Class to set the blinker color that blinks at an interval of 500ms
 *
 */

public class ServerStatus extends JPanel {

    private final Color GREEN = new Color(0, 128, 0);
    private final Color RED = new Color(255, 0, 0);
    private final int BLINK_INTERVAL = 500;

    private Timer blinkAnimation;
    private String statusText;
    private Color circleColor;
    private boolean isVisible;

    /**
     * ServerStatus is updated after very 500ms whenever object is created
     */
    public ServerStatus() {
        this.blinkAnimation = new Timer(BLINK_INTERVAL, event -> {
            this.isVisible = !this.isVisible;
            this.repaint();
        });
        this.stopBlinking();
    }

    /**
     * Blinker value sets to
     *
     * @param
     *            - Server status flag
     */
    public void startBlinking() {

            this.blinkAnimation.start();
            this.circleColor = GREEN;
            this.statusText = "Server is UP...";
            this.isVisible = false;
        this.repaint();
    }

    /**
     * Stops the blinker
     */
    public void stopBlinking()
    {
        System.out.print(" Stop ---- 12334");
        this.blinkAnimation.stop();
        this.circleColor = RED;
        this.statusText = "Server is Down..";
        this.isVisible = true;


        this.repaint();

    }

    /**
     * Paints the status circle with relevant colors
     *
     * @param graphics
     *            - Graphics object to change the color the circle
     */
    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        graphics.setColor(circleColor);
      //  graphics.drawString(this.statusText, 10, 20);

        if (isVisible) {
            graphics.fillOval(150, 100, 100, 100);
        }
    }

    public static void stopMessage(){
        if(!isServerRunning(8080)){
        }
    }
    public static boolean isServerRunning(int port) {
        ServerSocket server_socket = null;
        DatagramSocket datagram_socket = null;
        try {
            server_socket = new ServerSocket(port);
            server_socket.setReuseAddress(true);
            datagram_socket = new DatagramSocket(port);
            datagram_socket.setReuseAddress(true);
            return false;
        } catch (IOException e) {
            return true;
        } finally {
            if (datagram_socket != null) {
                datagram_socket.close();
            }

            if (server_socket != null) {
                try {
                    server_socket.close();
                } catch (IOException e) {
	                /* should not be thrown */
                    return true;
                }
            }
        }
    }

}