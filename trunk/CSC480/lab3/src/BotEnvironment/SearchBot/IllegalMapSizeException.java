package BotEnvironment.SearchBot;

public class IllegalMapSizeException extends Exception
{
    private String message;

    public IllegalMapSizeException (String message) {

        super();
        this.message = message;
    }

    public String getMessage() {

        String output =
            "Invalid map size.  Both the horizontal and vertical size should " +
            "be an integer value greater than 1.\r\n\r\n" + message;

        return output;
    }
}