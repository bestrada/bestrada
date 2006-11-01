package BotEnvironment;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class SBMapFilter extends FileFilter
{
    public static final String SBM = "sbm";

    public boolean accept (File file) {

        if (file.isDirectory()) {

            return true;
        }

        String extension = null;

        String fileName = file.getName();
        int index = fileName.lastIndexOf('.');

        if (index > 0 &&  index < fileName.length() - 1) {

            extension = fileName.substring(index + 1).toLowerCase();
        }

        if (extension != null && extension.equals(SBM)) {

            return true;
        }
        else {

            return false;
        }
    }

    public String getDescription() {

        return "Search Bot Map Files";
    }
}
