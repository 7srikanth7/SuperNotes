package com.example.indu.supernotes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.util.HashSet;

public class EditYourText extends AppCompatActivity implements TextWatcher {

    int noteId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        EditText editText = (EditText) findViewById(R.id.textfield);

        Intent i = getIntent();
        noteId = i.getIntExtra("noteid", -1);

        if(noteId != -1)
        {
            editText.setText(MainActivity.note.get(noteId));
        }

        editText.addTextChangedListener(this);




    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        MainActivity.note.set(noteId,String.valueOf(s));
        MainActivity.arrayAdapter.notifyDataSetChanged();
        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.indu.supernotes", Context.MODE_PRIVATE);

        if(MainActivity.set == null)
        {
            MainActivity.set = new HashSet<String>();
        }else{
            MainActivity.set.clear();
        }


        MainActivity.set.addAll(MainActivity.note);
        sharedPreferences.edit().remove("note").apply();
        sharedPreferences.edit().putStringSet("note", MainActivity.set).apply();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
