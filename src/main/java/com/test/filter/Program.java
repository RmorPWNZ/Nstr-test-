package com.test.filter;

public class Program {
    public static void main(String[] args) {
        ReadFile reader = new ReadFile();
        //path to required file
        reader.location = "C:\\Users\\Batman\\Desktop\\ObjectModule.bsl";
        reader.read();
        WriteToFile writer = new WriteToFile();
        writer.writeStringArray(reader.finalData);
    }
}
