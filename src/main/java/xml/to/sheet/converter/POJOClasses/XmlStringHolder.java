package xml.to.sheet.converter.POJOClasses;

//Xml string is held and can be accessed via the class name;
//no new instance of a converter object needs to be created

public class XmlStringHolder {
	private static String strHolder;
	
	public static String getXmlString() {
		return strHolder;
	}
	
	public static void setXmlString(String str) {
		strHolder = str;
	}

}
