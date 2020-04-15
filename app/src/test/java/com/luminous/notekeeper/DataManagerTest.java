package com.luminous.notekeeper;

import org.junit.Test;

import static org.junit.Assert.*;

public class DataManagerTest {

    @Test
    public void createNewNote() {
        DataManager dm = DataManager.getInstance();
        final CourseInfo course = dm.getCourse("android_async");
        final  String noteTitle = "Test note title";
        final  String noteBody = "Test note body";

        int noteIndex = dm.createNewNote();
        NoteInfo newNote = dm.getNotes().get(noteIndex);
        newNote.setCourse(course);
        newNote.setTitle(noteTitle);
        newNote.setText(noteBody);

        NoteInfo compareNote = dm.getNotes().get(noteIndex);
        assertEquals(compareNote.getCourse(), course);
        assertEquals(compareNote.getTitle(), noteTitle);
        assertEquals(compareNote.getText(), noteBody);
    }

    public void findSimilarNotes() {
        DataManager dm = DataManager.getInstance();
        final CourseInfo course = dm.getCourse("android_async");
        final  String noteTitle = "Test note title";
        final  String noteBodyOne = "Test note body of note one";
        final  String noteBodyTwo = "Test note body of note two";

        int noteIndexOne = dm.createNewNote();
        NoteInfo newNoteOne = dm.getNotes().get(noteIndexOne);
        newNoteOne.setCourse(course);
        newNoteOne.setTitle(noteTitle);
        newNoteOne.setText(noteBodyOne);

        int noteIndexTwo = dm.createNewNote();
        NoteInfo newNoteTwo = dm.getNotes().get(noteIndexTwo);
        newNoteTwo.setCourse(course);
        newNoteTwo.setTitle(noteTitle);
        newNoteTwo.setText(noteBodyTwo);

        int foundIndexOne = dm.findNote(newNoteOne);
        assertEquals(noteIndexOne, foundIndexOne);

        int foundIndexTwo = dm.findNote(newNoteTwo);
    }
}