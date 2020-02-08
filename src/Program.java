import java.util.List;
import java.util.ArrayList;

public class Program {
	
	public static void main(String[] args) {
		Slideshow slideObj = new Slideshow();
		InputModifier inpObj = new InputModifier();
		
		
		String file = "./Inputs/c_memorable_moments.txt";
		List<String> slideshow1 = slideObj.CreateSlideshow(file);
		for(String element : slideshow1) {
			System.out.println(element);
			inpObj.OutputFile("./Outputs/b_lovely_landscapes.txt", element);			
		}
		
	}
}
