package com.github.koushikghosh11.pdfreader;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MainViewHolder extends RecyclerView.ViewHolder {

    // TextView to display the title of the PDF file
    public TextView pdfTitle;

    // CardView that represents the overall layout for each PDF item
    public CardView cardView;

    public MainViewHolder(@NonNull View itemView) {
        super(itemView);

        // Find the TextView with the id "pdf_title" from the item view
        pdfTitle = itemView.findViewById(R.id.pdf_title);

        // Find the CardView with the id "rv_cardView" from the item view
        cardView = itemView.findViewById(R.id.rv_cardView);
    }
}
