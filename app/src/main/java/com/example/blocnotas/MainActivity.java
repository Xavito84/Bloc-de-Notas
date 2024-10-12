package com.example.blocnotas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Note> noteList;
    private NoteAdapter noteAdapter;
    private ListView listViewNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewNotes = findViewById(R.id.listViewNotes);
        noteList = new ArrayList<>();

        noteAdapter = new NoteAdapter(this, noteList);
        listViewNotes.setAdapter(noteAdapter);

        FloatingActionButton fabAddNote = findViewById(R.id.fabAddNote);
        fabAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditNoteActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        listViewNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Note note = noteList.get(position);
                Intent intent = new Intent(MainActivity.this, EditNoteActivity.class);
                intent.putExtra("noteId", note.getId());
                intent.putExtra("noteTitle", note.getTitle());
                intent.putExtra("noteContent", note.getContent());
                startActivityForResult(intent, 2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                String title = data.getStringExtra("noteTitle");
                String content = data.getStringExtra("noteContent");
                Note newNote = new Note(noteList.size(), title, content);
                noteList.add(newNote);
            } else if (requestCode == 2) {
                int id = data.getIntExtra("noteId", -1);
                String title = data.getStringExtra("noteTitle");
                String content = data.getStringExtra("noteContent");
                Note note = noteList.get(id);
                note.setTitle(title);
                note.setContent(content);
            }
            noteAdapter.notifyDataSetChanged();
        }
    }
}
