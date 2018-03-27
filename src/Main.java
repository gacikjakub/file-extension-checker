import extensions.NoExtensionInNameException;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("It is program to check your file extension hidden in Magic Number and compare it with extension in path");
        FileChecker fileChecker = new FileChecker();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Insert file path: ");
        String path = scanner.nextLine();
        try {
            fileChecker.openFile(path);
            if (fileChecker.checkFile()) {
                System.out.println("Your file is really " + fileChecker.getExtensionByFilename() + " file");
            }
            else {
                System.out.println("You give file with " + fileChecker.getExtensionByFilename() + " extension in path, but in fact this file has " + fileChecker.getExtensionByHex() + " extension");
            }
        }
        catch (NoExtensionInNameException e) {
            try {
                fileChecker.checkFile();
                System.out.println("You give file with no extension in path, but in fact this file has " + fileChecker.getExtensionByHex() + " extension");
            } catch (Exception ex) {
                System.out.println(ex.getClass().getSimpleName() + " --- " + ex.getMessage());
            }
        }
        catch (Exception e) {
            System.out.println(e.getClass().getSimpleName() + " ---- " + e.getMessage());
        }
    }
}
