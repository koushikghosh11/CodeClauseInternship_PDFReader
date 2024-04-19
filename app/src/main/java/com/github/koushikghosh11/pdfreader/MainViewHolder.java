package com.github.koushikghosh11.pdfreader;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MainViewHolder extends RecyclerView.ViewHolder {
    public TextView pdfTitle;
    public CardView cardView;

    public MainViewHolder(@NonNull View itemView) {
        super(itemView);

        pdfTitle = itemView.findViewById(R.id.pdf_title);
        cardView = itemView.findViewById(R.id.rv_cardView);


    }
}
