package BotEnvironment.SearchBot;

public class InvalidVerticalMapSizeException extends Exception
{
    public InvalidVerticalMapSizeException () {

        super();
    }

    public String getMessage() {

        return "Invalid vertical map size.";
    }
}