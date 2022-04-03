package GUI;

import java.util.ArrayList;

public class measureinfo {
	
	private ArrayList<NoteAndPos> measure = null;
	private barlineinfo endof = null;
	
	public measureinfo(ArrayList<NoteAndPos> measure, barlineinfo endof) {
		this.measure = measure;
		this.endof = endof;
	}

	public ArrayList<NoteAndPos> getMeasure() {
		return measure;
	}

	public void setMeasure(ArrayList<NoteAndPos> measure) {
		this.measure = measure;
	}

	public barlineinfo getEndof() {
		return endof;
	}

	public void setEndof(barlineinfo endof) {
		this.endof = endof;
	}
	
}
