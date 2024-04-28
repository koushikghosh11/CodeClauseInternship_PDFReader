package com.github.koushikghosh11.pdfreader;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnPDFClickListener {

    // Display a toast message upon app launch [Can be modified for custom messages]
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "PDF reader for internship at CodeClause", Toast.LENGTH_SHORT).show();

        // Request permission to access external storage for reading PDFs
        runtimePermission();
    }

    // Function to request READ_EXTERNAL_STORAGE permission at runtime
    private void runtimePermission() {
        Dexter.withContext(MainActivity.this)
                .withPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        // If permission is granted, proceed to load PDFs
                        loadPDFs();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        // Provide an explanation for why the permission is needed
                        // before requesting it again
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
    }

    // Recursive function to search for PDF files within a directory
    public ArrayList<File> findPDFs(@NonNull File f) {
        ArrayList<File> pdfs = new ArrayList<>();

        // Using a FilenameFilter (alternative approach)
        // FilenameFilter filenameFilter = (file, s) -> (file.isDirectory() && !file.isHidden()) || (s.toLowerCase().endsWith(".pdf") && !file.isDirectory());

        // Get list of files in the current directory
        File[] list = f.listFiles();

        // Ensure the list is not null before iterating
        assert list != null;
        for (File file : list) {
            // Recursively search subdirectories for PDFs
            if (file.isDirectory() && !file.isHidden()) {
                pdfs.addAll(findPDFs(file));
            }
            // Add any PDF files found in the current directory
            if (file.getName().toLowerCase().endsWith(".pdf")) {
                pdfs.add(file);
            }
        }

        return pdfs;
    }

    // Function to load and display PDFs in the RecyclerView
    public void loadPDFs() {
        // Get the RecyclerView element from the layout
        RecyclerView recyclerView = findViewById(R.id.rec_view);

        // Improve performance by setting the RecyclerView size to be fixed
        recyclerView.setHasFixedSize(true);

        // Set the layout manager for the RecyclerView (grid layout with 2 columns)
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // Find all PDF files on the external storage
        List<File> pdfs = findPDFs(Environment.getExternalStorageDirectory());

        // Create an adapter to handle the PDF data and clicks
        MainViewAdapter adapter = new MainViewAdapter(this, pdfs, this);

        // Set the adapter for the RecyclerView to display the PDFs
        recyclerView.setAdapter(adapter);
    }

    // Handle PDF click events from the adapter
    @Override
    public void OnPDFClick(File file) {
        // Create an Intent to open the PDFViewActivity class
        Intent intent = new Intent(MainActivity.this, PDFViewActivity.class);

        // Add the path of the clicked PDF file as an extra
        intent.putExtra("path", file.getAbsolutePath());

        // Start the PDFViewActivity to display the selected PDF
        startActivity(intent);
    }
}

