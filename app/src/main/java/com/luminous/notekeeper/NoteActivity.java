package com.luminous.notekeeper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

public class NoteActivity extends AppCompatActivity {
    public static final String NOTE_POSITION = "com.luminous.notekeeper.NOTE_POSITION";
    public static final int POSITION_NOT_SET = -1;
    public static final String MIME_FOR_EMAIL = "message/rfc2822";
    Spinner spinnerCourses;
    private NoteInfo note;
    EditText textNoteTitle;
    EditText textNoteBody;
    private boolean isNewNote;
    private int newNotePosition;
    private boolean isCancelling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        spinnerCourses = findViewById(R.id.spinnerCourses);
        List <CourseInfo> courses = DataManager.getInstance().getCourses();
        ArrayAdapter <CourseInfo> adapterCourses =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, courses);

        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourses.setAdapter(adapterCourses);

        readDisplayStateValues();
        saveOriginalNoteValues();

        textNoteTitle = findViewById(R.id.textNoteTitle);
        textNoteBody = findViewById(R.id.textNoteBody);

        if(!isNewNote) {
            displayNote(spinnerCourses, textNoteTitle, textNoteBody);
        }
    }

    private void saveOriginalNoteValues() {
        if(isNewNote) {
            return;
        }

        originalNoteCourseId = note.getCourse().getCourseId();
    }

    private void displayNote(Spinner spinnerCourses, EditText textNoteTitle, EditText textNoteBody) {
        List <CourseInfo> courses = DataManager.getInstance().getCourses();
        int courseIndex = courses.indexOf(note.getCourse());
        textNoteTitle.setText(note.getTitle());
        textNoteBody.setText(note.getText());
    }

    private void readDisplayStateValues() {
        Intent noteIntent = getIntent();
        // default value indicates null is found, POSITION_NOT_SET
        int position = noteIntent.getIntExtra(NOTE_POSITION, POSITION_NOT_SET);
        isNewNote = position == POSITION_NOT_SET;

        if(isNewNote) {
            createNewNote();
        } else {
            note = DataManager.getInstance().getNotes().get(position);
        }
    }

    private void createNewNote() {
        DataManager dm = DataManager.getInstance();
        newNotePosition = dm.createNewNote();
        note = dm.getNotes().get(newNotePosition);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_note, menu);
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isCancelling && isNewNote) {
            DataManager.getInstance().removeNote(newNotePosition);
        } else {
            saveNote();
        }
    }

    private void saveNote() {
        note.setCourse((CourseInfo) spinnerCourses.getSelectedItem());
        note.setTitle(textNoteTitle.getText().toString());
        note.setText(textNoteBody.getText().toString());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.actionSendMail) {
            sendEmail();
            return true;
        } else if (id == R.id.actionCancel) {
            isCancelling = true;
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void sendEmail() {
        CourseInfo course = (CourseInfo) spinnerCourses.getSelectedItem();
        String subject = textNoteTitle.getText().toString();
        String body = textNoteBody.getText().toString();

        Intent mailIntent = new Intent(Intent.ACTION_SEND);
        mailIntent.setType(MIME_FOR_EMAIL);
        mailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        mailIntent.putExtra(Intent.EXTRA_TEXT, body);
        startActivity(mailIntent);
    }
}
