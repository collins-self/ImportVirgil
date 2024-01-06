package com.collinsself.poems;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

import static com.collinsself.https.HttpsGet.sendGET;

public class GetAllPoems {
    private static String fixText(String input) {
        String removeSpacing = input.replaceAll("&nbsp;", "");
        String removeLineNumbers = removeSpacing.replaceAll("\\s*<span[^>]*>\\d+</span>", "");
        String addLineNumbers = removeLineNumbers.replaceAll("<br>", "\n");
        String removeHTML = addLineNumbers.replaceAll("<[^>]+>", "");
        return removeHTML.replaceAll("\t\t\tVergil\t\tThe Latin Library\t\tThe Classics Page\t", "");
    }

    /**
     * @param input Text with "\n" to count
     * @return an integer with the number of lines, defaults to 0
     */
    private static int countLines(String input) {
        int lineCount = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '\n') {
                lineCount++;
            }
        }
        return lineCount;
    }

    /**
     * @param input String to go through with '\n' characters
     * @param lineNumber Line to find
     * @return The string at that line
     */
    private static String findLine(String input, int lineNumber) {
        Scanner lineScan = new Scanner(input);
        String line = "";
        for (int i = 0; i < lineNumber; ++i) {
            line = lineScan.nextLine();
        }
        lineScan.close();
        return line;
    }

    public static void main(String[] args) throws IOException {
        Random random = new Random();
        int bookNum = random.nextInt(12) + 1;

        String url = "https://www.thelatinlibrary.com/vergil/aen" + bookNum + ".shtml";
        System.out.printf("URL is %s\n", url);

        String poem = fixText(sendGET(url));

        Random randomLine = new Random();
        int lineNumber = randomLine.nextInt(countLines(poem)) + 1;

        System.out.printf("Aenied Book %d, line %d: %s", bookNum, lineNumber, findLine(poem, lineNumber));
    }
}
