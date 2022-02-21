package GUI;


import utility.MusicXMLCreator;
public class InstrumentType {
	private static String type;

	

	public static String getInstrumentType(String sml) {
		
		
		
		if(sml.contains("Drumset")) {
			type="Drumset";
			
			
			
		}
		else if(sml.contains("Guitar")) {
			type="guitar";
			
			
		}
		
		
		
		System.out.println(type);
		return type;
		
		
		
		
		
	}
}
