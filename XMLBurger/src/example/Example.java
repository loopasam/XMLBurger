package example;

import java.io.FileNotFoundException;
import java.util.Date;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;

import core.XMLBurger;

/**
 * @author Samuel Croset
 *
 */
public class Example {
    public static void main(String[] args)  {

	XMLBurger burger = new XMLBurger("data/example.xml");

	while(burger.isNotOver()){
	    if(burger.tag("BookCatalogue")){
		while(burger.inTag("BookCatalogue")){
		    
		    if(burger.tag("Book")){
			while(burger.inTag("Book")){
			    
			    if(burger.tag("Date")){
				String dateString = burger.getTagText();
				
			    }
			    
			    if(burger.tag("Publisher")){
				System.out.println(burger.getTagText());
			    }
			    
			    if(burger.tag("Cost")){
				System.out.println(burger.getTagAttribute("currency"));
			    }
			}
		    }
		    
		}
	    }
	}
	
    }

}
