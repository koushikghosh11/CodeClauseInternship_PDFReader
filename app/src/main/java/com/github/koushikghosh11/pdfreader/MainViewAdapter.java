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

    private Context context;
    private List<File> pdfs;
    private OnPDFClickListener listener;

    public MainViewAdapter(Context context, List<File> pdfs, OnPDFClickListener listener) {
        this.context = context;
        this.pdfs = pdfs;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(context).inflate(R.layout.rec_view_elems, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.pdfTitle.setText(pdfs.get(position).getName());
        holder.pdfTitle.setSelected(true);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnPDFClick(pdfs.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return pdfs.size();
    }
}
