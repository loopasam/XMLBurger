package example;


import core.XMLBurger;

/**
 * Example on how to use XMLBurger.
 * The XML file we want to convert into Java Objects: https://github.com/loopasam/XMLBurger/blob/master/XMLBurger/data/example.xml
 * The XML file is available within the "data" folder of the source code.
 * The Java Objects are to be defined according to your needs, in this case let's assume we want to populate a library object with some books objects.
 * This example is just to get you started with XMLBurger style of programming, in this case, Xpath would have been more suitable :-)
 * 
 * @author Samuel Croset
 */
public class Example {

    //Main method
    public static void main(String[] args)  {

	//Step 1: Create a new XMLBurger, by passing the path to your XML file as argument.
	XMLBurger burger = new XMLBurger("data/example.xml");

	//Step 2: First loop around the XML structure
	while(burger.isNotOver()){

	    //Step 3, the "tag" method: If the tag of interest is encountered (in this case "BookCatalogue"), we want to do something with it.
	    if(burger.tag("BookCatalogue")){

		//For example you could create you own object you want to populate out of the XML:
		//Library library = new Library();

		//Step 4, the "inTag" method: While we are still in this tag, we will do things.
		while(burger.inTag("BookCatalogue")){

		    //Step 3 again, as we are inside the tag "BookCatalogue" (Step 4), if we see a tag called "Book", we could do something. 
		    if(burger.tag("Book")){

			//For instance, you could create an instance of your own Book object, that you are going to populate with some values coming from the XML:
			//Book book = new Book();
			System.out.println("New book created");

			//Step 4 again, while we will be inside the tag "Book", we are going to execute more business logic.
			while(burger.inTag("Book")){

			    //Step 3 again, if a "Date" tag is spotted, we would like to retrieve some info about it.
			    if(burger.tag("Date")){
				//Use the getTagText() method to retrieve the value in between to tags: <tag>text returned by the method</tag>
				String date = burger.getTagText();
				//Show the value
				System.out.println("\tDate: " + date);
				//Let's populate our book with it:
				//book.setDate(date);
				//Notice that you have full and easy control (type, etc...) on how you populate the object.
			    }

			    //Step 3 again, for the tag "Cost"
			    if(burger.tag("Cost")){
				//Use the getTagAttribute method to retrieve the value of an attribute.
				String currency = burger.getTagAttribute("currency");
				//Show the retrieved value.
				System.out.println("\tCurrency: " + currency);
				//Let's populate the book object created earlier with it:
				//book.setCurrency(currency);
				
				//Retrieve the text of the tag. Note how we convert the text in an integer (traditional way).
				//Important note, always retrieve the attribute's values before the text, otherwise you will get an exception.
				int price = Integer.parseInt(burger.getTagText());
				System.out.println("\tPrice: " + price);
				//The price id added to our book object:
				//book.setPrice(price);

			    }
			}
			//We exit of the "Book" tag, we had our book object into the library object:
			//library.addBook(book);
		    }
		    //Exit of the tag "Book"

		}
		//Exit of the "BookCatalogue" tag, more business logic could happen, such as saving the library object for instance.
	    }

	    //End of step 2, the XML document is fully parsed
	}

    }

}
