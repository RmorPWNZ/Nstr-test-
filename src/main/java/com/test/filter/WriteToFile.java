package com.test.filter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteToFile {
     void writeStringArray(List<String> args) {
        try {
            FileWriter myWriter = new FileWriter("test.txt");
            for (int i = 0; i < args.size(); i++)
            {
                myWriter.write(args.get(i));
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
