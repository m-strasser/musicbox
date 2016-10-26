package musicbox;

/**
 * A class that helps you keep track of multiple voices, that shall form
 * a single canon.
 * It has a global cursor that moves along the voices and can return the corresponding
 * notes (e.g. if you have a quarter note in voice1 and two eighth notes in voice 2
 * moving the global cursor by an eighth note will result in returning the quarter note
 * from voice1 and the second eighth note in voice2).
 * As we're working with a canon, we're assuming that the voices will be repeated over
 * and over until the longest voice has reached its end.
 * @author mstrasser
 *
 */
public class Canon {
	public class VoiceNotes {
		public Note v1, v2;
		
		public VoiceNotes(Note v1, Note v2) {
			this.v1 = v1;
			this.v2 = v2;
		}
	}

	private Note[][] voices;
	private Note[] notes;
	private int[] indexes;

	private Note[] voice1;
	private Note[] voice2;
	private Note v1C, v2C; // Current notes at voice 1 & 2
	
	private int v1Index = 0, v2Index = 0;
	private double globalPos=0, globalLength;
	
	public Canon(Note[][] voices, double globalLength) {
		this.voices = voices;
		this.notes = new Note[voices.length];
		this.indexes = new int[voices.length];
		this.globalLength = globalLength;
	}

	@Deprecated
	public Canon(Note[] voice1, Note[] voice2, double globalLength) {
		this.voices = new Note[][]{voice1, voice2};
		this.notes = new Note[2];
		this.indexes = new int[2];
		this.voice1 = voice1;
		this.voice2 = voice2;
		this.globalLength = globalLength;
	}
	
	/**
	 * Moves the global cursor by the given distance.
	 * @param distance The distance.
	 * @return The current note in all voices.
	 */
	@Deprecated
	public VoiceNotes moveCursor(double distance) {
		globalPos += distance;
		if(globalPos >= globalLength)
			return new VoiceNotes(null, null);
		
		Note n1 = v1C, n2 = v2C;
		Note cur1 = voice1[v1Index % voice1.length];
		Note cur2 = voice2[v2Index % voice2.length];

		if(cur1.getNoteOn() <= globalPos && cur1.getNoteOff() > globalPos) {
			n1 = voice1[v1Index % voice1.length];
			v1Index += 1;
			v1C = n1;
		}
		
		if(cur2.getNoteOn() <= globalPos && cur2.getNoteOff() > globalPos) {
			n2 = voice2[v2Index % voice2.length];
			v2Index += 1;
			v2C = n2;
		}
		
		return new VoiceNotes(n1, n2);
	}
	
	public Note[] moveCursorNew(double distance) {
		Note cur;
		Note[] curNotes = new Note[this.notes.length];
		
		globalPos += distance;
		if(globalPos >= globalLength)
			return null;

		for(int i=0; i<this.voices.length; i++) {
			curNotes[i] = this.notes[i];
			cur = this.voices[i][this.indexes[i] % this.voices[i].length];
			
			if(cur.getNoteOn() <= globalPos && cur.getNoteOff() > globalPos) {
				curNotes[i] = cur;
				this.indexes[i] += 1;
				this.notes[i] = cur;
			}
		}
		
		return curNotes;
	}
}
