package com.luminous.notekeeper;

import android.arch.lifecycle.ViewModel;
import android.os.Bundle;

public class NoteActivityViewModel extends ViewModel {
    public static final String ORIGINAL_NOTE_COURSE_ID = "com.luminous.notekeeper.ORIGINAL_NOTE_COURSE_ID";
    public static final String ORIGINAL_NOTE_TITLE = "com.luminous.notekeeper.ORIGINAL_NOTE_TITLE";
    public static final String ORIGINAL_NOTE_BODY = "com.luminous.notekeeper.ORIGINAL_NOTE_BODY";
    public String originalNoteCourseId;
    public String originalNoteTitle;
    public String originalNoteBody;

    public void saveState(Bundle outState) {
        outState.putString(ORIGINAL_NOTE_COURSE_ID, originalNoteCourseId);
        outState.putString(ORIGINAL_NOTE_TITLE, originalNoteTitle);
        outState.putString(ORIGINAL_NOTE_BODY, originalNoteBody);
    }

    public void restoreState(Bundle inState) {
        originalNoteCourseId = inState.getString(ORIGINAL_NOTE_COURSE_ID);
        originalNoteTitle = inState.getString(ORIGINAL_NOTE_TITLE)
        originalNoteBody = inState.getString(ORIGINAL_NOTE_BODY);
    }
}
