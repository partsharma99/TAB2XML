package GUI;

public class barlineinfo {
	
	private double topofstaff;
	private int staffnum;
	private int measureNum;
	private String emptyorfullornot;
	private double xpos;
	
	public barlineinfo() {
	}

	public barlineinfo(double topofstaff, int staffnum, int measureNum, String emptyorfullornot, double xpos) {
		this.topofstaff = topofstaff;
		this.staffnum = staffnum;
		this.measureNum = measureNum;
		this.emptyorfullornot = emptyorfullornot;
		this.xpos = xpos;
	}

	public double getTopofstaff() {
		return topofstaff;
	}

	public void setTopofstaff(double topofstaff) {
		this.topofstaff = topofstaff;
	}

	public int getStaffnum() {
		return staffnum;
	}

	public void setStaffnum(int staffnum) {
		this.staffnum = staffnum;
	}

	public int getMeasureNum() {
		return measureNum;
	}

	public void setMeasureNum(int measureNum) {
		this.measureNum = measureNum;
	}

	public String getEmptyorfullornot() {
		return emptyorfullornot;
	}

	public void setEmptyorfull(String emptyorfullornot) {
		this.emptyorfullornot = emptyorfullornot;
	}

	public double getXpos() {
		return xpos;
	}

	public void setXpos(double xpos) {
		this.xpos = xpos;
	}
	
}
