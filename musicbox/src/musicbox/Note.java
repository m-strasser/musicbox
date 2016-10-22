package musicbox;

public class Note {
	private NoteName pitch;
	private NoteLength duration;
	private double noteOn;
	private double noteOff = -1;
	
	public Note(NoteName pitch, NoteLength duration) {
		this.pitch = pitch;
		this.duration = duration;
	}
	
	public Note(NoteName pitch, NoteLength duration, double noteOn) {
		this(pitch, duration);
		this.noteOn = noteOn;
	}
	
	public Note(Note n) {
		this(n.pitch, n.duration, n.noteOn);
	}
	
	public NoteName getPitch() {
		return this.pitch;
	}
	
	public NoteLength getDuration() {
		return this.duration;
	}
	
	public double getNoteOn() {
		return this.noteOn;
	}
	
	public double getNoteOff() {
		if(noteOff < 0)
			this.noteOff = this.noteOn + this.duration.getLength();
		 return this.noteOff;
	}
	
	public void setNoteOn(double noteOn) {
		this.noteOn = noteOn;
		this.noteOff = -1;
	}
}
