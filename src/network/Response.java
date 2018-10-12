package network;

/**
 * Response
 *
 * Message class for a response from the server
 *
 * @author Team 7
 * @version 1.0
 */
public class Response {
    private int[] channelNumbers;
    private int frequency;

    public Response() {}

    public Response(int[] channelNumbers, int frequency) {
        this.channelNumbers = channelNumbers;
        this.frequency = frequency;
    }

    public int[] getChannelNumbers() {
        return channelNumbers;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setChannelNumbers(int[] channelNumbers) {
        this.channelNumbers = channelNumbers;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}
