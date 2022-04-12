package GUI;

import java.util.ArrayList;

public class measureinfo {
	
	private int partnum; 
	private ArrayList<NoteAndPos> measure = null;
	private barlineinfo startof = null;
	private barlineinfo endof = null;
	
	public measureinfo(int partnum, ArrayList<NoteAndPos> measure, barlineinfo startof, barlineinfo endof) {
		this.partnum = partnum;
		this.measure = measure;
		this.endof = endof;
	}
	
	public int getPartnum() {
		return partnum;
	}

	public void setPartnum(int partnum) {
		this.partnum = partnum;
	}

	public ArrayList<NoteAndPos> getMeasure() {
		return measure;
	}

	public void setMeasure(ArrayList<NoteAndPos> measure) {
		this.measure = measure;
	}
	
	public barlineinfo getStartof() {
		return startof;
	}

	public void setStartof(barlineinfo startof) {
		this.startof = startof;
	}

	public barlineinfo getEndof() {
		return endof;
	}

	public void setEndof(barlineinfo endof) {
		this.endof = endof;
	}
	
}
