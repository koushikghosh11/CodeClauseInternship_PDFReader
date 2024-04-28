package com.github.koushikghosh11.pdfreader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.List;

public class MainViewAdapter extends RecyclerView.Adapter<MainViewHolder> {

    // Context to access application resources
    private Context context;

    // List of PDF files to be displayed
    private List<File> pdfs;

    // Interface for handling PDF click events
    private OnPDFClickListener listener;

    public MainViewAdapter(Context context, List<File> pdfs, OnPDFClickListener listener) {
        this.context = context;
        this.pdfs = pdfs;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each item in the RecyclerView using LayoutInflater
        View itemView = LayoutInflater.from(context).inflate(R.layout.rec_view_elems, parent, false);
        // Create a new MainViewHolder instance and return it
        return new MainViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        // Get the PDF file at the current position
        File pdf = pdfs.get(position);

        // Set the text of the PDF title TextView to the name of the PDF file
        holder.pdfTitle.setText(pdf.getName());

        // Set the title text to be selected (likely for visual effect)
        holder.pdfTitle.setSelected(true);

        // Set an onClickListener for the cardView
        holder.cardView.setOnClickListener(view -> {
            // Call the listener's OnPDFClick method when the card is clicked, passing the clicked PDF file
            listener.OnPDFClick(pdfs.get(position));
        });
    }

    @Override
    public int getItemCount() {
        // Return the total number of PDF files in the list
        return pdfs.size();
    }
}
