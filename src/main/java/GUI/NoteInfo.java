package GUI;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NoteInfo {

	 private static int lines;
	 static int max=0;
	static int i = 1;
	static ArrayList<Integer> x = new ArrayList<Integer>();
	static ArrayList<Integer> y = new ArrayList<Integer>();
	static ArrayList<Integer> f = new ArrayList<Integer>();


	//for guitar
	public static ArrayList<Integer> GgetY(String sml) {
		
		
        Pattern pattern = Pattern.compile("<string>(.*?)</string>");
        Matcher matcher = pattern.matcher(sml);
        while (matcher.find()) {
        	//x.add(i);
//        	()
        	y.add(Integer. parseInt(matcher.group(1)));
        	i++;
        }

		
		return y;
		
		
	}
	
public static ArrayList<Integer> GgetX(String sml) {
		
		
        Pattern pattern = Pattern.compile("<string>(.*?)</string>");
        Matcher matcher = pattern.matcher(sml);
        while (matcher.find()) {
        	x.add(i);
        	//y.add(matcher.group(1));
        	i++;
        }

		
		
		return x;
		
		
	}
public static ArrayList<Integer> Ggetfret(String sml) {
	
	
    Pattern pattern = Pattern.compile("<fret>(.*?)</fret>");
    Matcher matcher = pattern.matcher(sml);
    while (matcher.find()) {
    	f.add(Integer. parseInt(matcher.group(1)));
    	//y.add(matcher.group(1));
    	i++;
    }

	
	
	return f;
	
	
}
	
}
