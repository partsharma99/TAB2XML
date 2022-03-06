package GUI;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import xml.to.sheet.converter.POJOClasses.Note2;

public class NoteInfo {
	static double perv;

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
        	////rrrrr
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
public static double notePos(List<Note2> list,Note2 a) {
	double xpos;
	int c = 0;
	if (list.contains(a)){
		 c = list.indexOf(a);
	}
	if(a.getChord()!=null) {
		xpos=perv;
	}
	else {
		xpos=80+(c+1)*30;

	}
	
if( list.indexOf(a)!=0&&(list.get(list.indexOf(a)-1)).getGrace()!=null) {
	if(a.getChord()!=null) {
		xpos=perv;
	}
	else {
	xpos=perv+10.0;
	}
}
	perv=xpos;
	return xpos;
}
	
}
