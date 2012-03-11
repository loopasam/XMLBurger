/**
 * 
 */
package core;

import java.io.FileNotFoundException;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;

/**
 * @author Samuel Croset
 *
 */
public class Example {
    public static void main(String[] args) throws FileNotFoundException, XMLStreamException, FactoryConfigurationError  {

//	XMLBurger burger = new XMLBurger("data/dev.xml");
	XMLBurger burger = new XMLBurger("data/NCI-Nature_Curated.xml");

	while(burger.isInTag("Model")){

	    while(burger.isInTag("MoleculeList")){

		while(burger.isInTag("Molecule")){

		    if(burger.hasTag("Name")){
			Tag tagName = burger.getCurrentTag();
			System.out.println("text:" + tagName.getText());
			for (String attribute : tagName.getAttributes().keySet()) {
			    System.out.println("attribute: " + attribute + " - " + tagName.getAttributes().get(attribute));
			}
			
		    }
		}
	    }
	}
    }

}
