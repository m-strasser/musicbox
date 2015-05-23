/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicbox;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.ShortMessage;

/**
 *
 * @author Michael Strasser
 */
public class MIDIHelper {
    /**
     * Wrapper for MidiEvent
     * @param note The midi note.
     * @param velocity The velocity of the note.
     * @param tick The tick at which the event shall occur.
     * @return A MidiEvent instance
     * @throws InvalidMidiDataException 
     */
    public static MidiEvent createNoteOnEvent( int note, int velocity, long tick) throws InvalidMidiDataException {
        return new MidiEvent(createNoteOnMessage(note, velocity), tick);
    }
    
    /**
     * Wrapper for ShortMessage
     * @param note The midi note.
     * @param velocity The velocity of the note.
     * @return A ShortMessage instance.
     * @throws InvalidMidiDataException 
     */
    public static ShortMessage createNoteOnMessage( int note, int velocity ) throws InvalidMidiDataException {
        return new ShortMessage(ShortMessage.NOTE_ON, 0, note, velocity);
    }
}
