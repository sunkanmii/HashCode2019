import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputModifier {
	public void OutputFile(String path, String value){
		
	        File file = new File(path);
	        FileWriter fr = null;
	        BufferedWriter br = null;
	        try{
	            fr = new FileWriter(file);
	            br = new BufferedWriter(fr);
	            
	            br.append(value);
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }finally{
	            try {
	                br.close();
	                fr.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	}
}