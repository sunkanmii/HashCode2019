import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Slideshow {
	static Integer photoLen;
	static List<String> horiSlides = new ArrayList<String>();
	static List<String> vertiSlides = new ArrayList<String>();
	static List<String> mainSlideshow = new ArrayList<String>(); 
	
	public void InputModifier(String document) {
		
	try (BufferedReader br = new BufferedReader(new FileReader(document))) {
		String line;
		int counter = 0;
		
		// Read file line by line
		while ((line = br.readLine()) != null) {
			String file = line;
			counter++;
			
			if(counter == 1) {
				photoLen = Integer.parseInt(file);
			}
			else if (file.indexOf("H") == 0){
				file = file.replace("H", Integer.toString(counter - 2));
				horiSlides.add(file);
			}
			else if(file.indexOf("V") == 0) {
				file = file.replace("V", Integer.toString(counter - 2));
				vertiSlides.add(file);
			}
		}
		
		}
	catch(IOException ex) {
		ex.printStackTrace();
	}
	
}
	public List<String> CreateSlideshow(String document){
		InputModifier(document);
		Integer finalInterest = 0;
		Integer tempInterest = 0;
		String chosenHoriPhotoCurr = "";
		String chosenHoriPhotoNext = "";
	
		// Horizontal photos.
		for(int i = 0; i < horiSlides.size(); i++) {
			String[] horiPhotoCurr = horiSlides.get(i).split(" ");
			Integer horiPhotoCurrLen = horiPhotoCurr.length;

			// If photo has already been chosen
			if(mainSlideshow.contains(horiPhotoCurr[0])){
				continue;
			}
			chosenHoriPhotoCurr = horiPhotoCurr[0];
			mainSlideshow.add(chosenHoriPhotoCurr);
			chosenHoriPhotoNext = "";
			finalInterest = 0;
			
			//Search only through the horizontal photos.
			for(int j =  i + 1; j <= horiSlides.size() - 1; j++) {
				String[] horiPhotoNext = horiSlides.get(j).split(" ");
				Integer horiPhotoNextLen = horiPhotoNext.length;
				
				if(mainSlideshow.contains(horiPhotoNext[0])) {
					continue;
				}
				
				tempInterest = 0;
				for(int k = 2; k < horiPhotoCurrLen; k++) {
					for(int l = 2; l < horiPhotoNextLen; l++) {
						
						//Comparing the tags
						if(horiPhotoCurr[k].equals(horiPhotoNext[l])) {
							tempInterest++;
						}
					}
					
					//No similarities
					if(tempInterest == 0) {
						if(horiPhotoCurrLen > horiPhotoNextLen) {
							tempInterest = horiPhotoNextLen;
						}
						else {
							tempInterest = horiPhotoCurrLen;
						}
					}
					else if(horiPhotoCurrLen > horiPhotoNextLen && tempInterest < horiPhotoNextLen) {
						tempInterest = horiPhotoNextLen;
					}
					else if(horiPhotoCurrLen < horiPhotoNextLen && horiPhotoCurrLen > tempInterest) {
						tempInterest = horiPhotoCurrLen;
					}
				}
				//If after you have looped through all tags in next photo, compare interest factors for the 
				//previous photo and next photo.
				//If next interest factor is greater than current interest factor
				//set the next horizontal photo to the next horizontal photo.
				if(tempInterest > finalInterest) {
					finalInterest = tempInterest;
					//Index of photos (photo 0, 1, etc.)
					
					if(mainSlideshow.size() > 1){
						mainSlideshow.remove(mainSlideshow.size() - 1);
					}
					
					chosenHoriPhotoNext = horiPhotoNext[0];					
					mainSlideshow.add(chosenHoriPhotoNext);
				}
				
			}
			
		}
		
		List<String> vertiPhotos;
		return mainSlideshow;
	}
}