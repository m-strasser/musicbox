/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicbox;

import java.util.Arrays;

import javax.sound.midi.Instrument;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Track;

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
    			new Note(NoteName.C, q, 0),
    			new Note(NoteName.D, q, 0.25),
    			new Note(NoteName.E, q, 0.5),
    			new Note(NoteName.C, q, 0.75),
    			new Note(NoteName.C, q, 1),
    			new Note(NoteName.D, q, 1.25),
    			new Note(NoteName.E, q, 1.5),
    			new Note(NoteName.C, q, 1.75),
    			new Note(NoteName.E, q, 2),
    			new Note(NoteName.F, q, 2.25),
    			new Note(NoteName.G, NoteLength.half, 2.5),
    			new Note(NoteName.E, q, 3),
    			new Note(NoteName.F, q, 3.25),
    			new Note(NoteName.G, NoteLength.half, 3.5),
    			new Note(NoteName.G, NoteLength.eigth, 4),
    			new Note(NoteName.A, NoteLength.eigth, 4.125),
    			new Note(NoteName.G, NoteLength.eigth, 4.25),
    			new Note(NoteName.F, NoteLength.eigth, 4.375),
    			new Note(NoteName.E, NoteLength.quarter, 4.5),
    			new Note(NoteName.C, NoteLength.quarter, 4.75),
    			new Note(NoteName.G, NoteLength.eigth, 5),
    			new Note(NoteName.A, NoteLength.eigth, 5.125),
    			new Note(NoteName.G, NoteLength.eigth, 5.25),
    			new Note(NoteName.F, NoteLength.eigth, 5.375),
    			new Note(NoteName.E, NoteLength.quarter, 5.5),
    			new Note(NoteName.C, NoteLength.quarter, 5.75),
    			new Note(NoteName.C, NoteLength.quarter, 6),
    			new Note(NoteName.G, NoteLength.quarter, 6.25),
    			new Note(NoteName.C, NoteLength.half, 6.5),
    			new Note(NoteName.C, NoteLength.quarter, 7),
    			new Note(NoteName.G, NoteLength.quarter, 7.25),
    			new Note(NoteName.C, NoteLength.half, 7.5),
    	};

    	Note[] voice2 = new Note[voice1.length];
    	for(int i=0; i<voice2.length; i++) {
    		voice2[i] = new Note(voice1[i]);
    	}
    	
    	double h = Canonizer.move(NoteLength.quarter.getLength(), voice1, voice2);
    	double h2 = Canonizer.move(NoteLength.half.getLength(), voice1, voice2);
    	double h3 = Canonizer.move(0.75, voice1, voice2);
    	double h4 = Canonizer.move(NoteLength.whole.getLength(), voice1, voice2);
    	double h5 = Canonizer.move(1.25, voice1, voice2);
    	double h6 = Canonizer.move(1.5, voice1, voice2);
    	double h7 = Canonizer.move(1.75, voice1, voice2);
    	
    	System.out.println("1 Q " + h);
    	System.out.println("2 Q " + h2);
    	System.out.println("3 Q " + h3);
    	System.out.println("4 Q " + h4);
    	System.out.println("5 Q " + h5);
    	System.out.println("6 Q " + h6);
    	System.out.println("7 Q " + h7);
    	
    	double eighth = 300, fourth = 600, second = 1200, first = 2400;
    	try {
			Sequence s = new Sequence(javax.sound.midi.Sequence.PPQ, 24);
			Track t = s.createTrack();
			
			addNote(t, 0, 24, NoteHelper.getNote(NoteName.A, 3), 120);
			addNote(t, 0, 24, NoteHelper.getNote(NoteName.C, 3), 120);
		} catch (InvalidMidiDataException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
    
    private static void addNote(Track track, int startTick, int tickLength, int key, int velocity) throws InvalidMidiDataException {
    	ShortMessage on = new ShortMessage();
    	on.setMessage(ShortMessage.NOTE_ON, 0, key, velocity);
    	ShortMessage off = new ShortMessage();
    	off.setMessage(ShortMessage.NOTE_OFF, 0, key, velocity);
    	
    	track.add(new MidiEvent(on, startTick));
    	track.add(new MidiEvent(off, startTick + tickLength));
    	
    }
}
