package GUI;


import utility.MusicXMLCreator;
public class Noteamount {
	private static String type;

	

	public static int getSize(String sml) {
		
		
		
		String findStr = "</note>";

		
		return sml.split(findStr, -1).length-1;
		
		
		
		
		
	}
}
