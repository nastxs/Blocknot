package com.example.blocknot;

import androidx.cardview.widget.CardView;

import com.example.blocknot.Models.Notes;

public interface NotesClickListener {

    void onClick(Notes notes);
    void onLongClick(Notes notes, CardView cardView);



}
