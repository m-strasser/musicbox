/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicbox;

/**
 * @author Michael Strasser
 */
public class NoteHelper {
    /**
     * Calculates the midi number of a given note.
     * @param note A valid note from the enum Note
     * @param octave The octave of the note (0-8)
     * @return The midi note number (21-108)
     * @throws Exception If an invalid note or octave is given.
     */
    public static int getNote( Note note, int octave ) throws Exception {
        int midi = -1;
        
        switch( note ) {
            case A:
                midi = 21;
                break;
            case Ais:
                midi = 22;
                break;
            case B:
                midi = 23;
                break;
            case C:
                midi = 24;
                break;
            case Cis:
                midi = 25;
                break;
            case D:
                midi = 26;
                break;
            case Dis:
                midi = 27;
                break;
            case E:
                midi = 28;
                break;
            case F:
                midi = 29;
                break;
            case Fis:
                midi = 30;
                break;
            case G:
                midi = 31;
                break;
            case Gis:
                midi = 32;
                break;
            default:
                throw new Exception("Invalid note given.");
        }
        
        if( octave >= 0 && octave <= 8 ) midi += 12*octave;
        else throw new Exception("Invalid octave given.");
        
        return midi;
    }

    /**
     * Given a note and a tonestep the resulting note is calculated.
     * @param note Starting note (i.e. C)
     * @param step Tone step (i.e. W)
     * @return Resulting note (i.e. D)
     */
    public static Note makeStep( Note note, ToneStep step ) {
        Note[] notes = Note.values();
        Note finalNote;
        int notePos = 0, steplen = 0;
        
        switch( step ){
            case H:
                steplen = 1;
                break;
            case W:
                steplen = 2;
                break;
            case WH:
                steplen = 3;
                break;
        }
        
        for(int i=0; i<notes.length; i++){
            if(notes[i] == note){
                notePos = i;
                break;
            }
        }
        
        finalNote = notes[(notePos+steplen)%notes.length];
        return finalNote;
    }
}
