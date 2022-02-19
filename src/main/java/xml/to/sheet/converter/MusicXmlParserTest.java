//package xml.to.sheet.converter;
//
//import java.io.IOException;
//import java.util.ArrayList;
////import .musicXMLparserDH;
////import xml.to.sheet.converter.*;
//
///**
// * Created by Dorien Herremans on 05/02/15.
// */
//
//public class MusicXmlParserTest {
//
//        public static void main (String[]args)throws IOException {
//
//            System.out.println("Reading in file... ");
//            String[] songSequence = null;
//            String[] songSequenceParsed = null;
//            String filename = "demo1.musicxml";
//            filename = "./data/test.xml";
//            if (args.length > 0) {
//                filename = args[0];
//
//            } else {
//                System.out.print("You did not specify any filename as on option.");
//                System.exit(0);
//            }
//         
//
//            MusicXmlParser parser = new MusicXmlParser(filename);
//
//
//
//            //prints out the note sounding at the same slice (each division of the musicxml file
//            String[] flatSong = parser.parseMusicXML();
//
//            //print out the songI j
//            if (args.length > 1) {
//                if (args[1] == "-print") {
//                    for (int i = 0; i < flatSong.length; i++) {
//                        System.out.println(flatSong[i]);
//                    }
//                }
//            }
//
//
//
//
//            //returns an ArrayList containing all the note objects
//            ArrayList<Note> songSequenceOfNoteObjects = parser.getNotesOfSong();
//
//            System.out.print("test");
//
//
//        }
//}
//    
