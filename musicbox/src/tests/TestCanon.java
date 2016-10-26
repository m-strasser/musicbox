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
		
		Canon p = new Canon(new Note[][]{v1, v2}, 0.25);
		Note[] vn = p.moveCursorNew(0);
		
		assertEquals(vn[0], v1[0]);
		assertEquals(vn[1], v2[0]);
		
		vn = p.moveCursorNew(0);

		assertEquals(vn[0], v1[0]);
		assertEquals(vn[1], v2[0]);
		
		vn = p.moveCursorNew(0.1);

		assertEquals(vn[0], v1[0]);
		assertEquals(vn[1], v2[0]);
		
		vn = p.moveCursorNew(0.15);

		assertNull(vn);
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
		
		Canon p = new Canon(new Note[][]{v1, v2}, 0.75);
		Note[] vn = p.moveCursorNew(0);	
		
		assertEquals(vn[0], v1[0]);
		assertEquals(vn[1], v2[0]);

		vn = p.moveCursorNew(0.25);	
		
		assertEquals(vn[0], v1[1]);
		assertEquals(vn[1], v2[0]);

		vn = p.moveCursorNew(0.25);	
		
		assertEquals(vn[0], v1[1]);
		assertEquals(vn[1], v2[0]);

		Note[] v3 = { 
				new Note(NoteName.C, NoteLength.quarter, 0),
		};
		Note[] v4 = {
				new Note(NoteName.C, NoteLength.quarter, 0),
				new Note(NoteName.D, NoteLength.half, 0.25),
		};
		
		Canon p2 = new Canon(new Note[][]{v3, v4}, 0.75);
		vn = p2.moveCursorNew(0);	
		
		assertEquals(vn[0], v3[0]);
		assertEquals(vn[1], v4[0]);

		vn = p2.moveCursorNew(0.25);	
		
		assertEquals(vn[0], v3[0]);
		assertEquals(vn[1], v4[1]);

		vn = p2.moveCursorNew(0.25);	
		
		assertEquals(vn[0], v3[0]);
		assertEquals(vn[1], v4[1]);
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
		
		Note[][] voices = {v1, v2};
		
		Canon p = new Canon(voices, NoteLength.eigth.getLength());
		Note[] vn = p.moveCursorNew(0);
		
		assertEquals(vn[0], v1[0]);
		assertEquals(vn[1], v2[0]);
		
		vn = p.moveCursorNew(NoteLength.thirtysecond.getLength());
		assertEquals(vn[0], v1[1]);
		assertEquals(vn[1], v2[0]);
		
		vn = p.moveCursorNew(NoteLength.thirtysecond.getLength());
		assertEquals(vn[0], v1[1]);
		assertEquals(vn[1], v2[1]);
		
		vn = p.moveCursorNew(NoteLength.thirtysecond.getLength());
		assertEquals(vn[0], v1[2]);
		assertEquals(vn[1], v2[2]);
		
		vn = p.moveCursorNew(NoteLength.thirtysecond.getLength());
		assertNull(vn);
	}
}
