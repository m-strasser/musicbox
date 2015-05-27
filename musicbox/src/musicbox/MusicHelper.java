/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicbox;

import java.util.HashMap;

/**
 * Helper class for calculating musical scales, chords, etc.
 * 
 * @TODO: scales beim init definieren
 * @author Michael Strasser
 */
public class MusicHelper {

    
    /**
     * Definition of valid scales.
     */
    public enum Scale {
        major,
        minor
    }
    
    /**
     * Defines a building pattern for a musical scale.
     */
    public class ScalePattern {
        private ToneStep[] steps;
        
        public ScalePattern( ToneStep[] steps ) {
            this.steps = steps;
        }
        
        public ToneStep[] getPattern() {
            return this.steps;
        }
    }
    
    private HashMap<Scale, ScalePattern> scales =
            new HashMap<Scale, ScalePattern>();
    
    public MusicHelper() {
        ToneStep[] majSteps = new ToneStep[]{ 
            ToneStep.W, ToneStep.W, ToneStep.H, ToneStep.W, 
            ToneStep.W, ToneStep.W, ToneStep.H };
        this.scales.put(Scale.major, new ScalePattern(majSteps));
    }
}
