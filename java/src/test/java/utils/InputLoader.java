package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class InputLoader {
    public static List<String> readFromFile(String resourceRelativePath){
            List<String> inputs = new ArrayList<>();

            ClassLoader classLoader = InputLoader.class.getClassLoader();
            try(InputStream inputStream = classLoader.getResourceAsStream(resourceRelativePath);
                InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(streamReader);
            ){
                String inputLine;
                while( (inputLine = reader.readLine()) != null ){ inputs.add(inputLine); }
            }catch(IOException e){
                System.err.println("error reading the stream: " + e.getMessage());
            }
            return inputs;
        }
}   
