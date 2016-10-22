package musicbox;

public class IntervalHelper {
	/**
	 * Returns the perceived "tonality" of the Interval, ranging from 0
	 * (strongly dissonant) to 1 (perfect consonant).
	 * @param i The interval.
	 * @return "Tonality" ranging from 0 to 1.
	 */
	public double getTonality(Interval i) {
		switch(i) {
		case Unison:
			return 1;
		case MinorSecond:
			return 0;
		case Second:
			return 0.35;
		case MinorThird:
			return 1;
		case MajorThird:
			return 1;
		case PerfectFourth:
			return 1;
		case Tritone:
			return 0.5;
		case PerfectFifth:
			return 1;
		case MinorSixth:
			return 0.65;
		case MajorSixth:
			return 0.9;
		case MinorSeventh:
			return 0.65;
		case MajorSeventh:
			return 0.5;
		case Octave:
			return 1;
		default:
			return -1;
		}
	}
}
