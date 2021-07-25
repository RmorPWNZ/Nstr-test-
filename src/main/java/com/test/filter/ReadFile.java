package com.test.filter;

import com.detectlanguage.DetectLanguage;
import com.detectlanguage.Result;
import com.detectlanguage.errors.APIError;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadFile {
    String location;
    List<String> finalData = new ArrayList<String>();
    List<Result> results = null;
    void read() {
        try {
            File myObj = new File(location);
            Scanner myReader = new Scanner(myObj);
            int lineCounter = 0;
            String currentLine;
            Pattern pattern = Pattern.compile("(\\w+)"); //(.*)
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                Matcher matches = pattern.matcher(data);
                data = data.replace("//", "");
                data = data.replace("  ", "");
                // detect language code in string
                DetectLanguage.apiKey = "6cb7e75759b5985466d36eb4c49c42bf";
                try {
                    if (check(data))
                    {
                        results = DetectLanguage.detect(data);
                    } else results = DetectLanguage.detect("English");
                } catch (APIError apiError) {
                    System.out.println("An error occurred.");
                    apiError.printStackTrace();
                }
                Result result = results.get(0);
                if(matches.find()) {
                    lineCounter = lineCounter + 1;
                    currentLine = lineCounter + ": " + result.language + " : " + data.trim() + ": %1\n";
                    finalData.add(currentLine);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    boolean check(String s) {
        int len = s.length();
        for (int i = 0; i < len; i++) {
            //check if there is at least 1 character in string
            if ((Character.isLetter(s.charAt(i)) == true)) {
                return true;
            }
        }
        return false;
    }
}
