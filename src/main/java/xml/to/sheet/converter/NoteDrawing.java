package xml.to.sheet.converter;

import java.awt.Graphics;

import org.jfugue.theory.Note;

public class NoteDrawing implements ThingToDraw {

	private Note note;
	
	public NoteDrawing(Note note) {
		this.note = note;
	}
	@Override
	public void draw(Graphics g) {
		/* look at methods of the note to
		 *  deduce how to draw it then do so here
		 *  eg:
		 *  - note.getDuration()
		 *  - note.getvalue()
		 */

	}

}
