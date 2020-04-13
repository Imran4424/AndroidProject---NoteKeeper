package com.luminous.notekeeper;

import android.arch.lifecycle.ViewModel;
import android.os.Bundle;

public class NoteActivityViewModel extends ViewModel {
    public static final String ORIGINAL_NOTE_COURSE_ID = "com.luminous.notekeeper.ORIGINAL_NOTE_COURSE_ID";
    public String originalNoteCourseId;
    public String originalNoteTitle;
    public String originalNoteBody;

    public void saveState(Bundle outState) {

    }
}
