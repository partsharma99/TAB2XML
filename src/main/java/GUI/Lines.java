package GUI;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lines {

	private static int lines;
	 static int max=0;
	
	
	public static int getLines(String sml) {
		
		
        Pattern pattern = Pattern.compile("<string>(.*?)</string>");
        Matcher matcher = pattern.matcher(sml);
        while (matcher.find()) {
        	if(Integer.parseInt(matcher.group(1))>max) {
        		max=Integer.parseInt(matcher.group(1));
        		
        	}
        }


		System.out.println(max);
		
		
		
		
		
		
		//System.out.println(sml);
		if(InstrumentType.getInstrumentType(sml)=="guitar") {
			lines=max;
		}
		else if(InstrumentType.getInstrumentType(sml)=="Drumset") {
			
			lines=0;
			
			
		}
		
		return lines;
		
		
	}
}
