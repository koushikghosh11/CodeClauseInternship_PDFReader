package com.github.koushikghosh11.pdfreader;

import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class PDFViewActivity extends AppCompatActivity {

    // Stores the path to the PDF file to be displayed
    private String pdfPath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfview);

        // Enable edge-to-edge mode for a more immersive viewing experience (optional)
        EdgeToEdge.enable(this);

        // Set the layout for the activity
        setContentView(R.layout.activity_pdfview);

        // Apply system bar insets to the main view for a cleaner layout
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get a reference to the PDFView element from the layout
        PDFView pdfView = findViewById(R.id.pdfView);

        // Retrieve the PDF path from the Intent that started this activity
        pdfPath = getIntent().getStringExtra("path");

        // Ensure the path is not null before proceeding
        assert pdfPath != null;

        // Create a File object from the path
        File file = new File(pdfPath);

        // Convert the File object to a Uri suitable for PDFView
        Uri pdfUri = Uri.fromFile(file);

        // Load the PDF using the provided Uri
        pdfView.fromUri(pdfUri).load();
    }
}
