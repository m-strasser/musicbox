/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicbox;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

/**
 *
 * @author mstrasser
 */
public class Musicbox {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    	// Simple MIDI test
    	NoteLength q = NoteLength.quarter;
    	Note[] voice1 = {
    			new Note(NoteName.C, q),
    			new Note(NoteName.D, q),
    			new Note(NoteName.E, q),
    			new Note(NoteName.C, q),
    			new Note(NoteName.C, q),
    			new Note(NoteName.D, q),
    			new Note(NoteName.E, q),
    			new Note(NoteName.C, q)
    	};
    	Note[] voice2 = voice1;
    	
    	double h = Canonizer.move(NoteLength.quarter.getLength(), voice1, voice2);
    	double h2 = Canonizer.move(NoteLength.half.getLength(), voice1, voice2);
    	double h3 = Canonizer.move(0.75, voice1, voice2);
    	double h4 = Canonizer.move(NoteLength.whole.getLength(), voice1, voice2);
    	double h5 = Canonizer.move(1.25, voice1, voice2);
    	double h6 = Canonizer.move(1.5, voice1, voice2);
    	double h7 = Canonizer.move(1.75, voice1, voice2);
    	
    	System.out.println(h);
    	System.out.println(h2);
    	System.out.println(h3);
    	System.out.println(h4);
    	System.out.println(h5);
    	System.out.println(h6);
    	System.out.println(h7);
    	
    	Synthesizer synth;
		try {
			synth = MidiSystem.getSynthesizer();
			synth.open();
			
			final MidiChannel[] mc = synth.getChannels();
			Instrument[] instr = synth.getDefaultSoundbank().getInstruments();
			synth.loadInstrument(instr[90]);
			mc[5].noteOn(60, 100);
			Thread.sleep(5000);
			mc[5].noteOff(60);
			synth.close();
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}
