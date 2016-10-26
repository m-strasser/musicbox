package musicbox;

import java.util.Arrays;

/**
 * Composes a musical canon based on variations of a given subject.
 * @author mstrasser
 *
 */
public class Canonizer {
	private NoteName[] subject = null;

	public Canonizer(NoteName[] subject) {
		this.subject = subject;
	}
	
	private int findEntryPoint(NoteName[] voice1, NoteName[] voice2){

		return -1;
	}
	
	/**
	 * Moves a note sequence by the specified distance and returns it.
	 * @param distance The distance of the shift (i.e. 0.5 to move a half note to the right).
	 * @param seq The sequence to be moved.
	 * @return The moved sequence.
	 */
	public static Note[] moveBy(double distance, Note[] seq) {
		Note[] moved = new Note[seq.length];
		int start;
		double cur = 0;

		// calculate starting index of seq
		// (i.e. move index until we've reached the given distance)
		for(start = 0; cur < distance; start++) {
			cur += seq[start].getDuration().getLength();
		}

		for(int k=0; k<moved.length; k++) {
			double noteOn = 0;
			moved[k] = seq[(start + k) % seq.length];

			if(k > 0)
				noteOn = moved[k-1].getNoteOff();
			moved[k].setNoteOn(noteOn);
		}
		
		return moved;
	}
	
	/**
	 * Moves a second voice a given distance relative to the first voice and calculates the
	 * resulting harmony points.
	 * @param distance The distance of the movement (i.e. move voice 2 a quarter note to the right)
	 * @param voice1 The first voice.
	 * @param voice2 The second, additional voice (needs at least 1 entry).
	 * @return The calculated harmony points for the given distance.
	 */
	public static double move(double distance, Note[] voice1, Note[] voice2) {
		double cur = 0, len1 = 0, len2 = 0;
		double harmony = 0;
		double stepsize=0, remainder = -1;
		
		Note cur1 = null, cur2 = null;
		Note[] moved1 = Canonizer.moveBy(distance, voice1);
		
		voice1 = moved1;
		
		// start comparison
		Canon p = new Canon(voice1, voice2, 8);
		Canon.VoiceNotes curNotes;

		curNotes = p.moveCursor(0);
		 while(true) {
			cur1 = curNotes.v1;
			cur2 = curNotes.v2;

			// If both of our notes are null, we are at the end of the partition
			if(cur1 == null && cur2 == null) break;

			len1 = cur1.getDuration().getLength();
			len2 = cur2.getDuration().getLength();

			stepsize = Math.min(len1, len2);
			if(remainder > 0){
				stepsize = remainder;
				remainder = -1;
			}

			// Calculate the harmony points of the current interval
			cur = Interval.fromInt(cur2.getPitch().ordinal() - cur1.getPitch().ordinal()).getHarmony();
			harmony += cur * stepsize;

			// Get the next notes
			curNotes = p.moveCursor(stepsize);

			if(curNotes.v1 == cur1) {
				// The note of voice 1 remained the same, this means the cursor is located at a point
				// where the note is still ringing and we have to step to the end of this note in the
				// next cycle of our loop
				remainder = len1 - stepsize;
			}

			if(curNotes.v2 == cur2) {
				remainder = len2 - stepsize;
			}
		}

		/**
		while(j < voice2.length) {
			cur1 = voice1[v1Start + i];
			cur2 = voice2[j];
			len1 = cur1.getDuration().getLength();
			len2 = cur2.getDuration().getLength();
			harmony += Interval.fromInt(cur2.getPitch().ordinal() - cur1.getPitch().ordinal()).getHarmony();

			if(wholemovement + len1 >= wholemovement + len2) {
				if((j+1) < voice2.length) {
					j += 1;
				}
				wholemovement += len1;
			}

			if(wholemovement + len2 >= wholemovement + len1) {
				if((v1Start + i+1) < voice1.length) {
					i += 1;
				} else {
					return harmony/voice2.length;
				}
				wholemovement += len2;
			}
		}
		**/
		
		//return harmony / voice2.length;
		return harmony;
	}
}
