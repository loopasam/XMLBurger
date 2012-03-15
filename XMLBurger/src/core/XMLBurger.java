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
public class XMLBurger {

    private FileInputStream fileInputStream;
    private XMLStreamReader reader;
    private int eventType;


    public void setEventType(int eventType) {
	this.eventType = eventType;
    }

    public int getEventType() {
	return eventType;
    }

    public void setReader(XMLStreamReader reader) {
	this.reader = reader;
    }

    public XMLStreamReader getReader() {
	return reader;
    }

    public void setFileInputStream(FileInputStream fileInputStream) {
	this.fileInputStream = fileInputStream;
    }

    public FileInputStream getFileInputStream() {
	return fileInputStream;
    }



    /**
     * @param path
     */
    public XMLBurger(String path) {
	// TODO Auto-generated constructor stub
	try {
	    this.fileInputStream = new FileInputStream(path);
	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	try {
	    this.reader = XMLInputFactory.newInstance().createXMLStreamReader(fileInputStream);
	} catch (XMLStreamException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (FactoryConfigurationError e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

    /**
     * @return
     * @throws XMLStreamException 
     */
    public boolean isNotOver() throws XMLStreamException {
	// TODO Auto-generated method stub
	if(this.reader.hasNext()){
	    this.eventType = this.reader.next();
	    return true;
	}
	return false;
    }

    /**
     * @param tag
     * @return
     */
    public boolean tag(String tag) {
	// TODO Auto-generated method stub
	if(this.eventType == XMLStreamConstants.START_ELEMENT && this.reader.getName().getLocalPart().equals(tag)){
	    return true;
	}
	return false;
    }

    /**
     * @param tag
     * @return
     * @throws XMLStreamException 
     */
    public boolean inTag(String tag) throws XMLStreamException {
	// TODO Auto-generated method stub
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
     * @return
     */
    public String getTagText() {
	// TODO Auto-generated method stub
	try {
	    String text = this.reader.getElementText();
	    return text;
	} catch (XMLStreamException e) {
	    // TODO Auto-generated catch block
	    //	    e.printStackTrace();
	    return null;
	}
    }

    /**
     * @param attribute
     * @return
     */
    public String getTagAttribute(String attribute) {
	// TODO Auto-generated method stub

	int numerOfAttribute = this.reader.getAttributeCount();
	for (int i = 0; i < numerOfAttribute; i++) {
	    //Deal with the exeption, like trying to get the attribute out of the start tag
	    if(this.reader.getAttributeLocalName(i).equals(attribute)){
		return this.reader.getAttributeValue(i);
	    }
	}
	return null;
    }
}
