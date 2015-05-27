package musicbox;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Michael Strasser
 */
public class Calculator {
    private TextHelper th = null;
    private String input;
    private int words = -1;
    private int tempo = 0, lowerTempo, upperTempo;
    private int lowerWLThreshold, upperWLThreshold;
    
    public Calculator( TextHelper th, Settings s ) {
        this.th = th;
        this.input = th.getInput();
        this.lowerTempo = s.getLowerTempo();
        this.upperTempo = s.getUpperTempo();
        this.lowerWLThreshold = s.getLowerWLThreshold();
        this.upperWLThreshold = s.getUpperWLThreshold();
    }
    
    /**
     * 5.7 is average wordlength in german according to duden
     * 5 is average wordlength in english according to yahoo answers
     * tempo is set between 80 and 180 BPM
     * 
     * Calculates the tempo of the composition based on the average wordlength.
     * Thresholds and tempo limits are defined in the settings object.
     * 
     * This method calculates the difference between the upper wordlength
     * threshold and the lower wordlength threshold and the difference between
     * the upper tempo limit and the lower tempo limit. Then it calculates the
     * average wordlength of the input text. The resulting value is then handled
     * as a percentage value between the upper tempo (100%) and the lower tempo
     * (0%). So if your upper wordlength threshold is 6 and your lower
     * wordlength threshold is 4 and the average wordlength is 5, the resulting
     * tempo will be exactly in the middle of the upper tempo limit and the
     * lower one.
     * 
     * @return The calculated tempo.
     */
    public int calculateTempo() {
        if( tempo > 0 ) return this.tempo;
        
        Pattern pattern = Pattern.compile("\\w+");
        Matcher matcher = pattern.matcher(this.input);
        int wordC = 0, sum = 0;
        float tempoDiff = this.upperTempo - this.lowerTempo;
        float thresholdDiff = this.upperWLThreshold - this.lowerWLThreshold;
        float averageWL = 0, exacttempo;
        
        while(matcher.find()) {
            wordC++;
            sum += matcher.group().length();
        }
        
        averageWL = (float)sum/wordC;
        exacttempo = this.upperTempo 
                - (averageWL-this.lowerWLThreshold)/(thresholdDiff/tempoDiff);
        
        this.tempo = Math.round(exacttempo);
        return this.tempo;
    }
}
