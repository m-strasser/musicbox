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
	 * Moves a second voice a given distance relative to the first voice and calculates the
	 * resulting harmony points.
	 * @param distance The distance of the movement (i.e. move voice 2 a quarter note to the right)
	 * @param voice1 The first voice.
	 * @param voice2 The second, additional voice (needs at least 1 entry).
	 * @return The calculated harmony points for the given distance.
	 */
	public static double move(double distance, Note[] voice1, Note[] voice2) {
		double wholemovement = 0; // by which length did we move so far?
		double voice1movement = 0; // how far did we move in voice1?
		double voice2movement = 0; // how far did we move in voice2?
		double cur = 0, dist = 0, len1 = 0, len2 = 0;
		double harmony = 0;
		double stepsize=0, remainder = -1;
		int v1Start, i = 0, j = 0;
		
		Note cur1 = null, cur2 = null;
		Note[] moved1 = new Note[voice1.length];
		
		// calculate starting index of voice1
		// (i.e. move index until we've reached the given distance)
		for(v1Start = 0; cur < distance; v1Start++) {
			cur += voice1[v1Start].getDuration().getLength();
		}
		
		// Correct positions in moved voice1
		for(int k=0; k<moved1.length; k++) {
			double noteOn = 0;
			moved1[k] = voice1[(v1Start + k) % voice1.length];

			if(k > 0)
				noteOn = moved1[k-1].getNoteOff();
			moved1[k].setNoteOn(noteOn);
		}
		
		voice1 = moved1;
		
		// start comparison
		Partition p = new Partition(voice1, voice2, 8);
		Partition.VoiceNotes curNotes;

		curNotes = p.moveCursor(0);
		 while(true) {
			cur1 = curNotes.v1;
			cur2 = curNotes.v2;

			if(cur1 == null && cur2 == null) break;
			len1 = cur1.getDuration().getLength();
			len2 = cur2.getDuration().getLength();

			stepsize = Math.min(len1, len2);
			if(remainder > 0){
				stepsize = remainder;
				remainder = -1;
			}

			cur = Interval.fromInt(cur2.getPitch().ordinal() - cur1.getPitch().ordinal()).getHarmony();
			harmony += cur * stepsize;
			System.out.println(cur1.getPitch().toString() + " - " + cur2.getPitch().toString() + ": " + cur);
			curNotes = p.moveCursor(stepsize);

			if(curNotes.v1 == cur1) {
				remainder = len1 - stepsize;
				System.out.println("We didn't move on Voice 1. " + remainder + " " + len1 + " " + stepsize);
			}

			if(curNotes.v2 == cur2) {
				remainder = len2 - stepsize;
				System.out.println("We didn't move on Voice 2." + remainder + " " + len2 + " " + stepsize);
			}
		}

		System.out.println("---------------------------------------------------------");
		System.out.println(harmony);
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
