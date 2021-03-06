import java.io.*;
import java.nio.file.FileSystemException;

import extensions.*;


public class FileChecker {
    private File file;
    private String inNameExtension = "";
    private String inHexExtension = "";
    
    static {
        new GIF();
        new JPEG();
        new TXT();
    }


    public String getExtensionByFilename() {
        return inNameExtension;
    }

    public String getExtensionByHex() {
        return inHexExtension;
    }


    private String fileToHex() throws IOException {
        if (file == null) {
            throw new NullPointerException("File cannot be null");
        }
        InputStream inputStream = new FileInputStream(file);
        StringBuilder hexResult = new StringBuilder();
        int character = 0;
        while ((character = inputStream.read()) != -1) {
            hexResult.append(String.format("%02X ", character));
        }
        return hexResult.toString();
    }
    
    private void matchExtension() throws NoMatchException {
        try {
            String hexFormat = fileToHex();
            for (Extension extension : Extension.getExtensions()) {
                if (extension.checkConditions(hexFormat)) {
                    inHexExtension = extension.getName();
                    return;
                }
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        throw new NoMatchException("File with given extension is not supported by this program");
    }

     public boolean checkFile() throws NoMatchException {
        matchExtension();
        if (inNameExtension.equals(inHexExtension)) return true;
        return false;
    }

    public void openFile(String pathToFile) throws FileNotFoundException, FileSystemException, NoExtensionInNameException {
        file = new File(pathToFile);
        if (!file.exists()) {
            throw new FileNotFoundException("File Not Found - incorrect path");
        }
        if (file.isDirectory()) {
            throw new FileSystemException("Under given path is directory - not file");
        }
        int lastIndexOfDot = file.getName().lastIndexOf(".");
        if (lastIndexOfDot == -1) {
            throw new NoExtensionInNameException("Under given path file has no extension");
        }
        inNameExtension = file.getName().substring(lastIndexOfDot + 1).toLowerCase();
        if (inNameExtension.equals("jpeg")) {
            inNameExtension = "jpg";
        }
    }
    
}