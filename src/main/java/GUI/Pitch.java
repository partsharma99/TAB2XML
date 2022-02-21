package GUI;

public class Pitch {

	private char step;
	private int octave;
	
	private Pitch(char step, int octave) {
		this.setStep(step);
		this.setOctave(octave);
	}

	public char getStep() {
		return step;
	}

	public void setStep(char step) {
		this.step = step;
	}

	public int getOctave() {
		return octave;
	}

	public void setOctave(int octave) {
		this.octave = octave;
	}
	
	
	
	
}
