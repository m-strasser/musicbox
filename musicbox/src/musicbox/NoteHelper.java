/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicbox;

/**
 *
 * @author Michael Strasser
 */
public class NoteHelper {
    /**
     * Calculates the midi number of a given note.
     * @param note A valid note from the enum Note
     * @param octave The octave of the note (0-8)
     * @param sharp If true, the given note is sharp (e.g. "A#", "C#", etc.)
     * If, for example, E is supplied as a note and sharp is true, the resulting
     * note will be F and no error will occur.
     * @return The midi note number (21-108)
     * @throws Exception If an invalid note or octave is given.
     */
    public static int getNote( Note note, int octave, boolean sharp ) throws Exception {
        int midi = -1;
        
        switch( note ) {
            case A:
                midi = 21;
                break;
            case B:
                midi = 23;
                break;
            case C:
                midi = 24;
                break;
            case D:
                midi = 26;
                break;
            case E:
                midi = 28;
                break;
            case F:
                midi = 29;
                break;
            case G:
                midi = 31;
                break;
            default:
                throw new Exception("Invalid note given.");
        }
        
        if( sharp ) midi++;
        
        if( octave >= 0 && octave <= 8 ) midi += 12*octave;
        else throw new Exception("Invalid octave given.");
        
        return midi;
    }
    
    /**
     * Calculates the midi number of a given note.
     * @param note A valid note from the enum Note
     * @param octave The octave of the note (0-8)
     * @return The midi note number (21-108)
     * @throws Exception If an invalid note or octave is given.
     */
    public static int getNote( Note note, int octave ) throws Exception {
        return NoteHelper.getNote(note, octave, false);
    }
}
