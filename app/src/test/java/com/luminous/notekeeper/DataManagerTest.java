package com.luminous.notekeeper;

import org.junit.Test;

import static org.junit.Assert.*;

public class DataManagerTest {

    @Test
    public void createNewNote() {
        final CourseInfo course = DataManager.getInstance().getCourse("android_async");
        final  String noteTitle = "Test note title";
        final  String noteBody = "Test note body";
    }
}