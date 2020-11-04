import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.*;
import java.io.FileWriter;
import java.io.IOException;

public class pptxreader {

    public static void main(String[] args) {
        pptxreader programm = new pptxreader();
        try {
            programm.start();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static void write(String str) {
        try {
            FileWriter myWriter = new FileWriter("D:\\Projects\\filledout.txt", true);
            myWriter.write(str + System.lineSeparator());
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String transformString(String str) {
        str = str.trim();
        if (str.length() >= 3) {
            if (str.substring(0, 1).matches("[0-9]")) {
                str = str.substring(3);
                str = str.trim();
                return str;
            }
        }
        return str;
    }

    public void start() throws java.io.IOException {
        String currentHeader = "default";
        for (String worksheetLine : Files.readAllLines(Paths.get("D:\\Projects\\worksheet.txt"))){
            String untransformed = worksheetLine;
            boolean thing = true;
            worksheetLine = transformString(worksheetLine);

            if ((worksheetLine != null) && (!worksheetLine.trim().isEmpty())) {
                for (String line : Files.readAllLines(Paths.get("D:\\Projects\\gn.txt"))){
                    line = line.trim();
                    if ((line != null) && (!line.trim().isEmpty())) {
                        if (isNumeric(line) != true) {
                            if (line.equals(worksheetLine)) { // if its a header at all
                                if (!currentHeader.equals(line)) { // if new header (not the same as old)
                                    currentHeader = line;
                                    //write(untransformed);
                                    System.out.println(untransformed);
                                }
                            } else {
                                //write(untransformed + " " + line);
                                System.out.println(untransformed + " " + line);
                            }
                        }
                    }
                }
            }
        }
    }
}