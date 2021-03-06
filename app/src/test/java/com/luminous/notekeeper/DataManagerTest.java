package com.luminous.notekeeper;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class DataManagerTest {
    static DataManager sDataManager;
    @BeforeClass
    public static void classSetUp() {
        sDataManager = DataManager.getInstance();
    }

    @Before
    public void setUp() {
        sDataManager.getNotes().clear();
        sDataManager.initializeExampleNotes();
    }

    @Test
    public void createNewNote() {
        final CourseInfo course = sDataManager.getCourse("android_async");
        final  String noteTitle = "Test note title";
        final  String noteBody = "Test note body";

        int noteIndex = sDataManager.createNewNote();
        NoteInfo newNote = sDataManager.getNotes().get(noteIndex);
        newNote.setCourse(course);
        newNote.setTitle(noteTitle);
        newNote.setBody(noteBody);

        NoteInfo compareNote = sDataManager.getNotes().get(noteIndex);
        assertEquals(compareNote.getCourse(), course);
        assertEquals(compareNote.getTitle(), noteTitle);
        assertEquals(compareNote.getBody(), noteBody);
    }

    @Test
    public void findSimilarNotes() {
        final CourseInfo course = sDataManager.getCourse("android_async");
        final  String noteTitle = "Test note title";
        final  String noteBodyOne = "Test note body of note one";
        final  String noteBodyTwo = "Test note body of note two";

        int noteIndexOne = sDataManager.createNewNote();
        NoteInfo newNoteOne = sDataManager.getNotes().get(noteIndexOne);
        newNoteOne.setCourse(course);
        newNoteOne.setTitle(noteTitle);
        newNoteOne.setBody(noteBodyOne);

        int noteIndexTwo = sDataManager.createNewNote();
        NoteInfo newNoteTwo = sDataManager.getNotes().get(noteIndexTwo);
        newNoteTwo.setCourse(course);
        newNoteTwo.setTitle(noteTitle);
        newNoteTwo.setBody(noteBodyTwo);

        int foundIndexOne = sDataManager.findNote(newNoteOne);
        assertEquals(noteIndexOne, foundIndexOne);

        int foundIndexTwo = sDataManager.findNote(newNoteTwo);
        assertEquals(noteIndexTwo, foundIndexTwo);
    }

    @Test
    public void createNewNoteOneStepCreation() {
        final CourseInfo course = sDataManager.getCourse("android_async");
        final  String noteTitle = "Test note title";
        final  String noteBody = "Test note body";

        int noteIndex = sDataManager.createNewNote(course, noteTitle, noteBody);

        NoteInfo compareNote = sDataManager.getNotes().get(noteIndex);

        assertEquals(course, compareNote.getCourse());
        assertEquals(noteTitle, compareNote.getTitle());
        assertEquals(noteBody, compareNote.getBody());
    }
}