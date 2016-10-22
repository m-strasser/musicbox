package musicbox;

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
		double cur = 0, dist = 0, len1, len2;
		double harmony = 0;
		int v1Start, i = 0, j = 0;
		
		Note cur1, cur2;
		
		// calculate starting index of voice1
		// (i.e. move index until we've reached the given distance)
		for(v1Start = 0; cur < distance; v1Start++) {
			cur += voice1[v1Start].getDuration().getLength();
		}
		
		// start comparison
		cur1 = voice1[v1Start];
		cur2 = voice2[0];

		while(j < voice2.length) {
			cur1 = voice1[(v1Start + i) % voice1.length];
			cur2 = voice2[j];
			
			len1 = cur1.getDuration().getLength();
			len2 = cur2.getDuration().getLength();
			
			cur = Interval.fromInt(cur2.getPitch().ordinal() - cur1.getPitch().ordinal()).getHarmony();
			harmony += Interval.fromInt(cur2.getPitch().ordinal() - cur1.getPitch().ordinal()).getHarmony();
			System.out.println(cur1.getPitch().toString() + " - " + cur2.getPitch().toString() + ": " + cur);
			j++;
			i++;
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
