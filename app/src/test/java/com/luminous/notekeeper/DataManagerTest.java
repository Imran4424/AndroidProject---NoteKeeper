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
    }
}