package com.example.blocknot.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blocknot.Models.Notes;
import com.example.blocknot.NotesClickListener;
import com.example.blocknot.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NotesListAdapter extends RecyclerView.Adapter<NotesViewHolder> {

    Context context;
    List<Notes> list;

    NotesClickListener listener;

    public NotesListAdapter(Context context, List<Notes> list, NotesClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.notes_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {

        holder.Textview_title.setText(list.get(position).getTitle());
        holder.Textview_title.setSelected(true);

        holder.Textview_notes.setText(list.get(position).getNotes());

        holder.Textview_date.setText(list.get(position).getDate());
        holder.Textview_date.setSelected(true);

        if(list.get(position).isPinned())
        {
            holder.ImageView_pin.setImageResource(R.drawable.tack_ico);
        }
        else
        {
            holder.ImageView_pin.setImageResource(0);
        }
        int color_code = getRandomColor();
        holder.notes_container.setCardBackgroundColor(holder.itemView.getResources().getColor(color_code, null));
        holder.notes_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(list.get(holder.getAdapterPosition()));

            }
        });
        holder.notes_container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onLongClick(list.get(holder.getAdapterPosition()), holder.notes_container);
                return false;
            }
        });


    }

    private int getRandomColor()
        {
        List<Integer> colorCode = new ArrayList<>();
        colorCode.add(R.color.color1);
            colorCode.add(R.color.color2);
            colorCode.add(R.color.color3);
            colorCode.add(R.color.color4);
            colorCode.add(R.color.color5);

            Random random = new Random();
            int random_color = random.nextInt(colorCode.size());
            return colorCode.get(random_color);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void filterList(List<Notes> filteredList){
        list = filteredList;
        notifyDataSetChanged();

    }
}






class NotesViewHolder extends RecyclerView.ViewHolder {

    CardView notes_container;
    TextView Textview_title;
    ImageView ImageView_pin;
    TextView Textview_notes;
    TextView Textview_date;


    public NotesViewHolder(@NonNull View itemView) {
        super(itemView);

        notes_container = itemView.findViewById(R.id.notes_container);
        Textview_title = itemView.findViewById(R.id.Textview_title);
        ImageView_pin = itemView.findViewById(R.id.ImageView_pin);
        Textview_notes = itemView.findViewById(R.id.Textview_notes);
        Textview_date = itemView.findViewById(R.id.Textview_date);
    }
}
