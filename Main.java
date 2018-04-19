
import org.fluttercode.datafactory.impl.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;


public class Main {

    public static void main(String args[]) throws  IOException {

        System.out.println("Creating a CSV file...");

        String x = args[0];
        String myString = args[1];
        int y = Integer.parseInt(myString); //parsing integer to string


        File file = new File(x);
        FileWriter writer = new FileWriter(file);

        String[] SportType = {"Soccer","Tennis","Basketball","Swimming","Lacrosse","American Football","Golf","Fishing",};
        DataFactory df = new DataFactory(); //using data factory to generate random data to csv  file
        for (int i = 0; i < y ; i++) {
            writer.write(df.getFirstName()); //randon first name
            writer.write(',');
            writer.write(df.getLastName()); //random last name
            writer.write(',');
            writer.write(df.getEmailAddress()); //random email address
            writer.write(',');
            writer.write(df.getCity()); //random city
            writer.write(',');
            writer.write(df.getBusinessName()); //randon business name
            writer.write(',');
            writer.write(df.getAddress()); //random address
            writer.write(',');
            writer.write(df.getRandomWord()); //random favorite word
            writer.write("\n");
        }

        writer.close();
        System.out.println("CSV file created successfully.");


    }

}
