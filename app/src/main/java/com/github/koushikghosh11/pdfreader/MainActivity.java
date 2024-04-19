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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "PDF reader for internship at CodeClause", Toast.LENGTH_SHORT).show();

        runtimePermission();
    }

    private void runtimePermission(){
        Dexter.withContext(MainActivity.this).withPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        loadPDFs();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();

    }

    public ArrayList<File> findPDFs(@NonNull File f){
        ArrayList<File> pdfs = new ArrayList<>();

//        FilenameFilter filenameFilter = (file, s) -> (file.isDirectory() && !file.isHidden()) || (s.toLowerCase().endsWith(".pdf") && !file.isDirectory());
        File[] list = f.listFiles();

        assert list != null;
        for(File file : list){
            if (file.isDirectory() && !file.isHidden()){
                pdfs.addAll(findPDFs(file));
            }
            if (file.getName().toLowerCase().endsWith(".pdf"))
                pdfs.add(file);
        }

        return pdfs;
    }

    public void loadPDFs(){
        RecyclerView recyclerView = findViewById(R.id.rec_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        List<File> pdfs = findPDFs(Environment.getExternalStorageDirectory());
        MainViewAdapter adapter = new MainViewAdapter(this, pdfs, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnPDFClick(File file) {
        startActivity(new Intent(MainActivity.this, PDFViewActivity.class)
                .putExtra("path", file.getAbsolutePath()));
    }
}
