package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import musicbox.Note;
import musicbox.NoteLength;
import musicbox.NoteName;
import musicbox.Canon;

public class TestCanon {

	@Test
	public void testMoveCursorSingleNote() {
		Note[] v1 = { 
				new Note(NoteName.C, NoteLength.quarter, 0),
		};
		Note [] v2 = {
				new Note(NoteName.C, NoteLength.quarter, 0),
		};
		
		Canon p = new Canon(v1, v2, 0.25);
		Canon.VoiceNotes vn = p.moveCursor(0);
		
		assertEquals(vn.v1, v1[0]);
		assertEquals(vn.v2, v2[0]);
		
		vn = p.moveCursor(0);

		assertEquals(vn.v1, v1[0]);
		assertEquals(vn.v2, v2[0]);
		
		vn = p.moveCursor(0.1);

		assertEquals(vn.v1, v1[0]);
		assertEquals(vn.v2, v2[0]);
		
		vn = p.moveCursor(0.15);

		assertEquals(vn.v1, null);
		assertEquals(vn.v2, null);
		
	}

	@Test
	public void testMoveCursorDifferentVoiceLengths() {
		Note[] v1 = { 
				new Note(NoteName.C, NoteLength.quarter, 0),
				new Note(NoteName.D, NoteLength.half, 0.25),
		};
		Note [] v2 = {
				new Note(NoteName.C, NoteLength.quarter, 0),
		};
		
		Canon p = new Canon(v1, v2, 0.75);
		Canon.VoiceNotes vn = p.moveCursor(0);	
		
		assertEquals(vn.v1, v1[0]);
		assertEquals(vn.v2, v2[0]);

		vn = p.moveCursor(0.25);	
		
		assertEquals(vn.v1, v1[1]);
		assertEquals(vn.v2, v2[0]);

		vn = p.moveCursor(0.25);	
		
		assertEquals(vn.v1, v1[1]);
		assertEquals(vn.v2, v2[0]);

		Note[] v3 = { 
				new Note(NoteName.C, NoteLength.quarter, 0),
		};
		Note[] v4 = {
				new Note(NoteName.C, NoteLength.quarter, 0),
				new Note(NoteName.D, NoteLength.half, 0.25),
		};
		
		Canon p2 = new Canon(v3, v4, 0.75);
		vn = p2.moveCursor(0);	
		
		assertEquals(vn.v1, v3[0]);
		assertEquals(vn.v2, v4[0]);

		vn = p2.moveCursor(0.25);	
		
		assertEquals(vn.v1, v3[0]);
		assertEquals(vn.v2, v4[1]);

		vn = p2.moveCursor(0.25);	
		
		assertEquals(vn.v1, v3[0]);
		assertEquals(vn.v2, v4[1]);
	}
	
	@Test
	public void testMoveCursorDifferentNoteLenghts() {
		Note[] v1 = {
				new Note(NoteName.A, NoteLength.thirtysecond, 0),
				new Note(NoteName.A, NoteLength.sixteenth, NoteLength.thirtysecond.getLength()),
				new Note(NoteName.A, NoteLength.thirtysecond, NoteLength.sixteenth.getLength() + NoteLength.thirtysecond.getLength())
		};
		
		Note[] v2 = {
				new Note(NoteName.B, NoteLength.sixteenth, 0),
				new Note(NoteName.B, NoteLength.thirtysecond, NoteLength.sixteenth.getLength()),
				new Note(NoteName.B, NoteLength.thirtysecond, NoteLength.thirtysecond.getLength() + NoteLength.sixteenth.getLength())
		};
		
		Canon p = new Canon(v1, v2, NoteLength.eigth.getLength());
		Canon.VoiceNotes vn = p.moveCursor(0);
		
		assertEquals(vn.v1, v1[0]);
		assertEquals(vn.v2, v2[0]);
		
		vn = p.moveCursor(NoteLength.thirtysecond.getLength());
		assertEquals(vn.v1, v1[1]);
		assertEquals(vn.v2, v2[0]);
		
		vn = p.moveCursor(NoteLength.thirtysecond.getLength());
		assertEquals(vn.v1, v1[1]);
		assertEquals(vn.v2, v2[1]);
		
		vn = p.moveCursor(NoteLength.thirtysecond.getLength());
		assertEquals(vn.v1, v1[2]);
		assertEquals(vn.v2, v2[2]);
		
		vn = p.moveCursor(NoteLength.thirtysecond.getLength());
		assertEquals(vn.v1, null);
		assertEquals(vn.v2, null);
	}
}
