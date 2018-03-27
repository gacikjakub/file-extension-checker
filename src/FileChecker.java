import java.io.*;
import java.nio.file.FileSystemException;

import extensions.*;


public class FileChecker {
    private File file;
    private String inNameExtension;
    private String inHexExtension;


    public String getExtensionByFilename() {
        return inNameExtension;
    }

    public String getExtensionByHex() {
        return inHexExtension;
    }


    private String fileToHex(){
       return null;
    }

    public boolean checkFile() {
        return false;
    }

    public void openFile(String pathToFile) {
        
    }
}