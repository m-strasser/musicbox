package musicbox;

/**
 * A representation of musical intervals including their names, their distance in halftone steps and their musical quality according to the following classification:
 * 	* 1 - Perfect Consonance
 * 	* 0.5 - Imperfect Consonance
 * 	* 0 - Dissonance
 * These categories are only a rough guideline and should be varied according to the achievable results in harmony.
 * @author mstrasser
 *
 */
public enum Interval {
	Unison(0, 1),
	MinorSecond(1, 0),
	Second(2, 0.2),
	MinorThird(3, 0.5),
	MajorThird(4, 0.5),
	PerfectFourth(5, 0.4),
	Tritone(6, 0),
	PerfectFifth(7, 1),
	MinorSixth(8, 0.55),
	MajorSixth(9, 0.6),
	MinorSeventh(10, 0.2),
	MajorSeventh(11, 0),
	Octave(12, 1);
	
	private int steps;
	private double harmony;
	
	Interval(int steps, double harmony) {
		this.steps = steps;
		this.harmony = harmony;
	}
	
	public int getSteps() {
		return steps;
	}
	
	public double getHarmony() {
		return harmony;
	}
	
	/**
	 * Returns an Interval based on a given number of halftone steps modulo 12.
	 * @param steps The number of steps.
	 * @return The Interval with the corresponding number of halftone steps.
	 */
	public static Interval fromInt(int steps) {
		steps = Math.abs(steps % 12);

		switch(steps) {
		case 0: return Unison;
		case 1: return MinorSecond;
		case 2: return Second;
		case 3: return MinorThird;
		case 4: return MajorThird;
		case 5: return PerfectFourth;
		case 6: return Tritone;
		case 7: return PerfectFifth;
		case 8: return MinorSixth;
		case 9: return MajorSixth;
		case 10: return MinorSeventh;
		case 11: return MajorSeventh;
		case 12: return Octave;
		default: return null;
		}
	}
}
