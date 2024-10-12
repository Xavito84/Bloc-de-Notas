package com.example.blocnotas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EditNoteActivity extends AppCompatActivity {

    private EditText editTextTitle;
    private EditText editTextContent;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextContent = findViewById(R.id.editTextContent);
        buttonSave = findViewById(R.id.buttonSave);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("noteId")) {
            editTextTitle.setText(intent.getStringExtra("noteTitle"));
            editTextContent.setText(intent.getStringExtra("noteContent"));
        }

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("noteId", intent.getIntExtra("noteId", -1));
                resultIntent.putExtra("noteTitle", editTextTitle.getText().toString());
                resultIntent.putExtra("noteContent", editTextContent.getText().toString());
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
