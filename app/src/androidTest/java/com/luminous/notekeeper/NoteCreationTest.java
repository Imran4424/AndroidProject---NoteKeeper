package com.luminous.notekeeper;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class NoteCreationTest {
    public ActivityTestRule <NoteListActivity> noteListActivityActivityTestRule =
            new ActivityTestRule<>(NoteListActivity.class);
}