package musicbox;

public enum NoteLength {
	whole(1),
	half(0.5),
	quarter(0.25),
	eigth(0.125),
	sixteenth(0.0625),
	thirtysecond(0.03125);
	
	private double len;
	
	NoteLength(double len) {
		this.len = len;
	}
	
	public double getLength() {
		return len;
	}
}
