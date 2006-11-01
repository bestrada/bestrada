package BotEnvironment.SearchBot;

public class MapExtensionException extends Exception
{
    private String message;

    public MapExtensionException (String message) {

        super();
        this.message = message;
    }

    public String getMessage() {

        String output =
            "Invalid map file extension.  The extension should be ." + message + ".";

        return output;
    }
}