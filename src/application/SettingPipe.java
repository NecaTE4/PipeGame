//Selin Aydýn 150120061
//Necati Koçak 150120053
// CLASSIN AMACI
package application;

import java.io.FileNotFoundException;
import javafx.scene.image.Image;

public class SettingPipe { //We set each cell with this class.
	
		 
	 public  SettingPipe() //We created one constructor.
	 {
		
	}
           
		public Image setPipe(String[] word,int i) throws FileNotFoundException //With the Image method here, we aimed to place the appropriate image for the values entered in the method.
		{
			
        while( i < 63 )	{   //We put it in a while loop to read each value from the edited input and put it in the appropriate if-else. We examine word for word.
       
      
        if (word[i+1].equals("Starter")) { // if the word in each i+1 equals "Starter" it enters here.
        	if (word[i+2].equals("Vertical")) { // if the word in each i+2 equals "Vertical" it enters here.
        		return new Image("starter V.png"); //Here it returns the image "starter V.png" to the cell.
        		
       	  	
        	}	
        	else if (word[i+2].equals("Horizontal")) { // if the word in each i+2 equals "Horizontal" it enters here.
        		return new Image("starter H.png");//Here it returns the image "starter H.png" to the cell.
			
        }
        	}

			
		 else if (word[i+1].equals("Empty")) {// if the word in each i+1 equals "Empty" it enters here.
			if (word[i+2].equals("none")) { // if the word in each i+2 equals "none" it enters here.
				return new Image("Empty_Tiles.png");//Here it returns the image "Empty_Tiles.png" to the cell.
			
			}
			else if(word[i+2].equals("Free")) { // if the word in each i+2 equals "Free" it enters here.
				return new Image("Empty_Tiles free.png");//Here it returns the image "Empty_Tiles free.png" to the cell.
		}
			 
			  } 
        
		 else if (word[i+1].equals("End")) {// if the word in each i+1 equals "End" it enters here.
			if (word[i+2].equals("Vertical")) { // if the word in each i+2 equals "Vertical" it enters here.
				return new Image("end v.png");//Here it returns the image "end v.png" to the cell.
       	  	
        	}	
        	else if(word[i+2].equals("Horizontal")) { // if the word in each i+2 equals "Horizontal" it enters here.
        		return new Image("end h.png");  //Here it returns the image "end h.png" to the cell.
        	}
               }
        
		 else if (word[i+1].equals("Pipe")) { // if the word in each i+1 equals "Pipe" it enters here.
			 
			 if(word[i+2].equals("Vertical")) { // if the word in each i+2 equals "Vertical" it enters here.
				 return new Image("pipe v.png");//Here it returns the image "pipe v.png" to the cell.
		 }
			 else if(word[i+2].equals("Horizontal")) { // if the word in each i+2 equals "Horizontal" it enters here.
				return new Image("pipe h.png"); //Here it returns the image "pipe h.png" to the cell.
		 }
			 else if(word[i+2].equals("00")){ // if the word in each i+2 equals "00" it enters here.
				return  new Image("00 new.png"); //Here it returns the image "00 new.png" to the cell.
		 }
			 else if(word[i+2].equals("01")){ // if the word in each i+2 equals "01" it enters here.
				return new Image("01 new.png"); //Here it returns the image "01 new.png" to the cell.
		 }
			 else if (word[i+2].equals("10")){ // if the word in each i+2 equals "10" it enters here.
				return new Image("10 new.png"); //Here it returns the image "10 new.png" to the cell.
		 }
			 else if (word[i+2].equals("11")){ // if the word in each i+2 equals "11" it enters here.
				return  new Image("11 new.png"); //Here it returns the image "11 new.png" to the cell.
		 }
			}
			 
		  else if (word[i+1].equals("PipeStatic")) { // if the word in each i+1 equals "PipeStatic" it enters here.
				 	if(word[i+2].equals("Vertical")) { // if the word in each i+2 equals "Vertical" it enters here.
				 		return new  Image("pipe static v.png"); //Here it returns the image "pipe static v.png" to the cell.
			 }
				 else if(word[i+2].equals("Horizontal")) { // if the word in each i+2 equals "Horizontal" it enters here.
					 return new Image("pipe static h.png"); //Here it returns the image "pipe static h.png" to the cell.
			 }
				 else if(word[i+2].equals("00")){ // if the word in each i+2 equals "00" it enters here.
						return  new Image("00 static.png"); //Here it returns the image "00 static.png" to the cell.
				 }
					 else if(word[i+2].equals("01")){ // if the word in each i+2 equals "01" it enters here.
						return new Image("01 static.png"); //Here it returns the image "01 static.png" to the cell.
				 }
					 else if (word[i+2].equals("10")){ // if the word in each i+2 equals "10" it enters here. 
						return new Image("10 static.png"); //Here it returns the image "10 static.png" to the cell.
				 }
					 else if (word[i+2].equals("11")){ // if the word in each i+2 equals "11" it enters here. 
						return  new Image("11 static.png"); //Here it returns the image "11 static.png" to the cell.
				 }				
		} 
        
        }	
        return null;
}
}