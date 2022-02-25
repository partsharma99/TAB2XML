package xml.to.sheet.converter.POJOClasses;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;

public class XmlToJava {
	
	public static <T> T unmarshal(String xmlString, Class<T> targetClass) throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(targetClass);
		Unmarshaller unmmarshaller = jc.createUnmarshaller();
		T obj = targetClass.cast(unmmarshaller.unmarshal(new StringReader(xmlString)));
		return obj;
		}
	}
