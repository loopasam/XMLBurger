package example;


import core.XMLBurger;

/**
 * @author Samuel Croset
 *
 */
public class Example {
    public static void main(String[] args)  {

	XMLBurger burger = new XMLBurger("data/asdexample.xml");

	while(burger.isNotOver()){
	    if(burger.tag("BookCatalogue")){
		while(burger.inTag("BookCatalogue")){
		    
		    if(burger.tag("Book")){
			while(burger.inTag("Book")){
			    
			    if(burger.tag("Date")){
				String date = burger.getTagText();
				System.out.println("Date: " + date);
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
