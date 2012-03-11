/**
 * 
 */
package core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * @author Samuel Croset
 *
 */
public class XMLBurgerDebug {

    private FileInputStream fileInputStream;
    private XMLStreamReader reader;
    private int eventType;



    public XMLBurgerDebug(String path) throws FileNotFoundException, XMLStreamException, FactoryConfigurationError {
	this.fileInputStream = new FileInputStream(path);
	this.reader = XMLInputFactory.newInstance().createXMLStreamReader(fileInputStream);
    }


    /**
     * @param string
     * @return
     * @throws XMLStreamException 
     */
    public boolean isInTag(String tag) throws XMLStreamException {
	System.out.println("Enter the in In method for tag: " + tag);
	// TODO Auto-generated method stub
	if(this.eventType == XMLStreamConstants.START_ELEMENT && this.reader.getName().getLocalPart().equals(tag)){
	    System.out.println("Reached the start of element: " + tag);
	}



	if(this.eventType == XMLStreamConstants.END_ELEMENT && this.reader.getName().getLocalPart().equals(tag)){
	    System.out.println("Reached the end of element: " + tag);
	    return false;
	}

	this.eventType = this.reader.next();
	System.out.println("event type: " + eventType);
	return true;
    }


    /**
     * @throws XMLStreamException 
     * 
     */
    public void start() throws XMLStreamException {
	while(this.reader.hasNext()){
	    this.eventType = this.reader.next();

	    if(tag("NCI_PID_XML")){
		System.out.println("Begining of NCI_PID_XML");
		while(inTag("NCI_PID_XML")){
		    
		    if(tag("Created")){
			String value = this.reader.getElementText();
			System.out.println("name: " + value);
		    }
		    
		    if(tag("Model")){
			System.out.println("Begining of Model");
			while(inTag("Model")){
			    
				    if(tag("Molecule")){
					System.out.println("Begining of Molecule");
					String attribute = this.reader.getAttributeValue(1);
					System.out.println("attribute: " + attribute);
					
					while(inTag("Molecule")){
					    if(tag("Name")){
						System.out.println("Begining of Name");
						String attribute1 = this.reader.getAttributeValue(2);
						System.out
						.println("attribute: " + attribute1);
						System.out.println("out of Name element");
					    }					    
					}
					System.out.println("out of Molecule element");
				    }

				}
			System.out.println("out of model element");
		    }
		}
		System.out.println("out of NCI element");
	    }


	}
    }


    /**
     * @param string
     * @return
     */
    private boolean tag(String tag) {
	// TODO Auto-generated method stub
	if(this.eventType == XMLStreamConstants.START_ELEMENT && this.reader.getName().getLocalPart().equals(tag)){
	    return true;
	}
	return false;
    }


    private boolean inTag(String tag) throws XMLStreamException {
	if(this.eventType == XMLStreamConstants.END_ELEMENT){
	    if(this.reader.getName().getLocalPart().equals(tag)){
		this.eventType = this.reader.next();

		return false;
	    }
	}

	this.eventType = this.reader.next();

	return true;
    }


    /**
     * @throws XMLStreamException 
     * 
     */
    public void ontology() throws XMLStreamException {
	// TODO Auto-generated method stub
	while(this.reader.hasNext()){
	    this.eventType = this.reader.next();
	    
	    if(tag("NCI_PID_XML")){
		while(inTag("NCI_PID_XML")){
		    
		    if(tag("Created")){
			String value = this.reader.getElementText();
			System.out.println("Created: " + value);
		    }
		    
		    if(tag("Ontology")){
			while(inTag("Ontology")){
			    if(tag("LabelType")){
				String name = this.reader.getAttributeValue(0);
				System.out.println("name label: " + name);
				while(inTag("LabelType")){
				    
				    if(tag("LabelValue")){
					String nameLabel = this.reader.getAttributeValue(0);
					System.err.println("--labelValue: " + nameLabel);
				    }
				    
				    
				}
			    }
			    
			}
		    }
		    
		}
		
	    }
	}
    }



}
