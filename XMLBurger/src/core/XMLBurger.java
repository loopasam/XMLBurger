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

import org.codehaus.stax2.XMLStreamReader2;



/**
 * Library for efficient parsing of odd XML files. Particularly useful while dealing with odd XML document, where the mapping to Java Object is difficult.
 * @author Samuel Croset
 * @version 1.0
 *
 */
public class XMLBurger {

    private FileInputStream fileInputStream;
    private XMLStreamReader2 reader;
    private int eventType;


    public void setEventType(int eventType) {
	this.eventType = eventType;
    }

    public int getEventType() {
	return eventType;
    }

    public void setReader(XMLStreamReader2 reader) {
	this.reader = reader;
    }

    public XMLStreamReader2 getReader() {
	return reader;
    }

    public void setFileInputStream(FileInputStream fileInputStream) {
	this.fileInputStream = fileInputStream;
    }

    public FileInputStream getFileInputStream() {
	return fileInputStream;
    }


    /**
     * Create an XMLBurger object from an XML file. 
     * @param pathOfXML
     */
    public XMLBurger(String pathOfXML) {

	try {
	    this.fileInputStream = new FileInputStream(pathOfXML);
	} catch (FileNotFoundException e) {
	    System.err.println("Cannot find the XML document: " + pathOfXML);
	    e.printStackTrace();
	}
	try {
	    this.reader = (XMLStreamReader2) XMLInputFactory.newInstance().createXMLStreamReader(fileInputStream);
	} catch (XMLStreamException e) {
	    System.err.println("Problem while reading the XML document");
	    e.printStackTrace();
	} catch (FactoryConfigurationError e) {
	    System.err.println("Problem with the XML instatiation");
	    e.printStackTrace();
	}
    }

    /**
     * Main loop, over the content of the entire XML.<br>
     * <Usage:<br>
     * while(burger.isNotOver()){<br>
     * 	//Do things and check for tags<br>
     * }<br>
     * @return whether the XML document is over or not.
     * @throws XMLStreamException 
     */
    public boolean isNotOver() {
	try {
	    if(this.reader.hasNext()){
		this.eventType = this.reader.next();
		return true;
	    }
	} catch (XMLStreamException e) {
	    System.err.println("The XML document has no next elements.");
	    e.printStackTrace();
	}
	return false;
    }

    /**
     * Check if the tag passed as parameter is encountered.<br>
     * Usage:<br>
     * if(burger.tag("NameOfYourTag")){<br>
     *	//Do something, like instantiating of of your object or looping over the tag using the inTag("") method<br>
     * }<br>
     * @param tag
     * @return whether or not the current parsed tag is the tag entered as input
     */
    public boolean tag(String tag) {
	if(this.eventType == XMLStreamConstants.START_ELEMENT && this.reader.getName().getLocalPart().equals(tag)){
	    return true;
	}
	return false;
    }

    /**
     * Stay inside the tag passed as parameter.<br>
     * Usage:<br>
     * while(burger.inTag("NameOfYourTag")){<br>
     * 	//Do something while staying inside the loop, usually looking for over tags via the tag() method.<br>
     * }<br>
     * @param tag
     * @return whether or not the cursor is still in the tag entered in input.
     * @throws XMLStreamException 
     */
    public boolean inTag(String tag) {
	if(this.eventType == XMLStreamConstants.END_ELEMENT){
	    if(this.reader.getName().getLocalPart().equals(tag)){
		try {
		    this.eventType = this.reader.next();
		} catch (XMLStreamException e) {
		    System.err.println("The reader has no next element. Check whether you are correctly using the methods.");
		    e.printStackTrace();
		}
		return false;
	    }
	}
	try {
	    this.eventType = this.reader.next();
	} catch (XMLStreamException e) {
	    System.err.println("The reader has no next element. Check whether you are correctly using the methods.");
	    e.printStackTrace();
	}
	return true;
    }


    /**
     * Return the text associated with the current tag. The construct has to be like this: [yourTag]the text[/yourTag]<br>
     * Usage:<br>
     * if(burger.tag("NameOfYourTag")){<br>
     * 	//Do something, for example instantiate one of your object.<br>
     * 	//Retrieve the text inside the tag "NameOfYourTag".<br>
     * 	String text = burger.getTagText();<br>
     * 	//You can do something with this value, for example setting a property of an object.<br>
     * }<br>
     * @return the text contained by the tag.
     */
    public String getTagText() {
	try {
	    String text = this.reader.getElementText();
	    return text;
	} catch (XMLStreamException e) {
	    System.err.println("There is not text on this element, please correct your code accordingly.");
	    e.printStackTrace();
	    return null;
	}
    }

    /**
     * Return the value associated with the current tag attribute. The construct has to be like this: [yourTag attribute="value"]the text[/yourTag]<br>
     * Usage:<br>
     * if(burger.tag("NameOfYourTag")){<br>
     * 	//Do something, for example instantiate one of your object.<br>
     * 	//Retrieve the value of the specified attribute.<br>
     * 	String value = burger.getTagAttribute("attribute");<br>
     * 	//You can do something with this value, for example setting a property of an object.<br>
     * }<br>
     * @param attribute
     * @return the value of the attribute.
     */
    public String getTagAttribute(String attribute) {
	int numerOfAttribute = this.reader.getAttributeCount();
	for (int i = 0; i < numerOfAttribute; i++) {
	    if(this.reader.getAttributeLocalName(i).equals(attribute)){
		return this.reader.getAttributeValue(i);
	    }
	}
	return null;
    }
}
