package GUI;

public class Note {

	private int duration;
	private int voice;
	private String type;
	private Pitch pitch;
	private Notations notation;
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getVoice() {
		return voice;
	}
	public void setVoice(int voice) {
		this.voice = voice;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Pitch getPitch() {
		return pitch;
	}
	public void setPitch(Pitch pitch) {
		this.pitch = pitch;
	}
	public Notations getNotation() {
		return notation;
	}
	
	public int getStringFromNotation() {
		return notation.getTechnical().getString();
	}
	public void setNotation(Notations notation) {
		this.notation = notation;
	}
	
	
}
