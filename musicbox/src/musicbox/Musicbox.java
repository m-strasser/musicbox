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
import javax.sound.midi.Sequencer;
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
    	
    	double best = 0, second = 0, moveBy=0, moveBy2nd = 0;
    	double[] harmonicPoints = new double[7];
    	int currentTick = 0;
    	
    	harmonicPoints[0] = Canonizer.move(NoteLength.quarter.getLength(), voice1, voice2);
    	harmonicPoints[1] = Canonizer.move(NoteLength.half.getLength(), voice1, voice2);
    	harmonicPoints[2] = Canonizer.move(0.75, voice1, voice2);
    	harmonicPoints[3] = Canonizer.move(NoteLength.whole.getLength(), voice1, voice2);
    	harmonicPoints[4] = Canonizer.move(1.25, voice1, voice2);
    	harmonicPoints[5] = Canonizer.move(1.5, voice1, voice2);
    	harmonicPoints[6] = Canonizer.move(1.75, voice1, voice2);
    	
    	for(int i=0; i<harmonicPoints.length; i++) {
    		if(harmonicPoints[i] > best) {
    			best = harmonicPoints[i];
    			moveBy = (i+1)*0.25;
    		} else if(harmonicPoints[i] > second) {
    			second = harmonicPoints[i];
    			moveBy2nd = (i+1) * 0.25;
    		}
    	}
    	
    	voice2 = Canonizer.moveBy(moveBy, voice1);
    	Note[] voice3 = Canonizer.moveBy(moveBy2nd, voice1);
    	double eighth = 300, fourth = 600, scnd = 1200, first = 600;
    	try {
			Sequence s = new Sequence(javax.sound.midi.Sequence.PPQ, 100);
			Sequencer seq = MidiSystem.getSequencer();
			Track t = s.createTrack();
			Track t2 = s.createTrack();
			Track t3 = s.createTrack();
			
			for(int i=0; i<voice1.length; i++) {
				addNote(t, currentTick, (int)(voice1[i].getDuration().getLength() * first), NoteHelper.getNote(voice1[i].getPitch(), 3), 120);
				currentTick += (int)(voice1[i].getDuration().getLength() * first);
			}

			currentTick = (int)(moveBy * first);
			for(int i=0; i<voice1.length; i++) {
				addNote(t2, currentTick, (int)(voice1[i].getDuration().getLength() * first), NoteHelper.getNote(voice1[i].getPitch(), 2), 120);
				currentTick += (int)(voice1[i].getDuration().getLength() * first);
			}

			currentTick = (int)(moveBy2nd * first);
			for(int i=0; i<voice1.length; i++) {
				addNote(t3, currentTick, (int)(voice1[i].getDuration().getLength() * first), NoteHelper.getNote(voice1[i].getPitch(), 4), 120);
				currentTick += (int)(voice1[i].getDuration().getLength() * first);
			}
			
			seq.open();
			seq.setSequence(s);
			seq.start();
			//seq.stop();
			//seq.close();
		} catch (InvalidMidiDataException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	/**
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
		**/
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
