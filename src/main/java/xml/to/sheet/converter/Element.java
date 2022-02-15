package xml.to.sheet.converter;

import java.awt.Graphics;

import org.jfugue.theory.Note;

public class Element {

	/*
	 * To describe a given note (or other element) as mentioned in a music xml file
	 */
	
	private Note note;
	
	public Element(Note note) {
		this.note = note;
	}
	
	public void draw(Graphics g) {
		/* look at methods of the note to
		 *  deduce how to draw it then do so here
		 *  eg:
		 *  - note.getDuration()
		 *  - note.getvalue()
		 */

	}

}
