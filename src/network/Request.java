package network;

/**
 * Request
 *
 * Message class for a request to the server
 *
 * @author Team 7
 * @version 1.0
 */
public class Request {

    private int numChannels;

    public Request() {}

    public Request(int numChannels) {
        this.numChannels = numChannels;
    }

    public int getNumChannels() {
        return numChannels;
    }

    public void setNumChannels(int numChannels) {
        this.numChannels = numChannels;
    }
}
