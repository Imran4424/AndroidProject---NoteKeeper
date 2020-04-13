package com.luminous.notekeeper;

import android.arch.lifecycle.ViewModel;
import android.os.Bundle;

public class NoteActivityViewModel extends ViewModel {
    public String originalNoteCourseId;
    public String originalNoteTitle;
    public String originalNoteBody;

    public void saveState(Bundle outState) {
        
    }
}
