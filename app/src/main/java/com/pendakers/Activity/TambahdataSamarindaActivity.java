package com.pendakers.Activity;


import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Build.VERSION.SDK_INT;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.pendakers.API.APIRequestData;
import com.pendakers.API.RetroServer;
import com.pendakers.Manager.Const;
import com.pendakers.Manager.PrefManager;
import com.pendakers.Model.ResponseData;
import com.pendakers.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahdataSamarindaActivity extends AppCompatActivity {
    final static int REQUEST_CODE = 333;
    EditText ino_mou, ino_pks, itgl_pks, ithn, ijw, iuk, imk, itt;
    TextView idf;
    Spinner ithp;
    TextView tket, labelUpload;
    Button binput, uploadFile, btnUpdate, btnCancel;
    String mou, pks, tanggal, tahun, jangka_waktu, unitkerja, mitrakerja, tentang, tahapan;
    String mouValue, pksValue, tanggalValue, tahunValue, jangkaWaktuValue, unitkerjaValue, mitrakerjaValue, tentangValue, fileValue, idValue;
    String codeAcces, fromActivity;
    RequestBody rbMou;
    RequestBody rbPks;
    RequestBody rbTanggal;
    RequestBody rbTahun;
    RequestBody rbJangkaWaktu;
    RequestBody rbUnitkerja;
    RequestBody rbMitrakerja;
    RequestBody rbTentang;
    RequestBody rbTahapan;
    RequestBody rbKabKota;
    Uri uri = null;
    MultipartBody.Part filePdf = null;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambahdata_samarinda);
        codeAcces = getIntent().getStringExtra("codeAccess");
        fromActivity = getIntent().getStringExtra("from");
        idValue = getIntent().getStringExtra("id");
        mouValue = getIntent().getStringExtra("mou");
        pksValue = getIntent().getStringExtra("pks");
        tanggalValue = getIntent().getStringExtra("tanggal");
        tahunValue = getIntent().getStringExtra("tahun");
        jangkaWaktuValue = getIntent().getStringExtra("jangkaWaktu");
        unitkerjaValue = getIntent().getStringExtra("unitKerja");
        mitrakerjaValue = getIntent().getStringExtra("mitrakerja");
        tentangValue = getIntent().getStringExtra("tentang");
        fileValue = getIntent().getStringExtra("file");
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ino_mou = findViewById(R.id.nomorMOU);
        ino_pks = findViewById(R.id.nomorPKS);
        itgl_pks = findViewById(R.id.tglPKS);
        itgl_pks.setInputType(InputType.TYPE_NULL);
        ithn = findViewById(R.id.tahun);
        ijw = findViewById(R.id.jangkawaktu);
        iuk = findViewById(R.id.unitkerja);
        imk = findViewById(R.id.mitrakerja);
        itt = findViewById(R.id.tentang);
        idf = findViewById(R.id.nameFile);
        binput = findViewById(R.id.btninput);
        btnUpdate = findViewById(R.id.btnupdate);
        btnCancel = findViewById(R.id.btncancel);
        labelUpload = findViewById(R.id.nameFile);
        uploadFile = findViewById(R.id.uploadFile);

        ino_mou.setText(mouValue);
        ino_pks.setText(pksValue);
        ithn.setText(tahunValue);
        ijw.setText(jangkaWaktuValue);
        iuk.setText(unitkerjaValue);
        imk.setText(mitrakerjaValue);
        itt.setText(tentangValue);
        itgl_pks.setText(tanggalValue);
        labelUpload.setText(fileValue);

        if (fromActivity.equals("add")){
            btnCancel.setVisibility(View.VISIBLE);
            binput.setVisibility(View.VISIBLE);
        }else {
            btnCancel.setVisibility(View.VISIBLE);
            btnUpdate.setVisibility(View.VISIBLE);
        }
        ithp = findViewById(R.id.tahapan);
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override public void onPermissionsChecked(MultiplePermissionsReport report) {/* ... */}

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                    }
                }).check();
        if (permission()) {
        } else {
            requestPermissionDialog();
        }
        itgl_pks.setOnClickListener(v -> showDateDialog(itgl_pks));

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TambahdataSamarindaActivity.this, PendakerActivity.class);
                intent.putExtra("codeAccess",codeAcces);
                intent.putExtra("from", "end");
                startActivity(intent);
            }
        });

        binput.setOnClickListener(view -> {
            mou = ino_mou.getText().toString();
            pks = ino_pks.getText().toString();
            tahun = ithn.getText().toString();
            jangka_waktu = ijw.getText().toString();
            unitkerja = iuk.getText().toString();
            mitrakerja = imk.getText().toString();
            tentang = itt.getText().toString();
            tanggal = itgl_pks.getText().toString();
            tahapan = String.valueOf(ithp.getSelectedItem());
            if (mou.trim().equals("")){
                ino_mou.setError("Nomor MOU harus diisi");
            }else if(pks.trim().equals("")){
                ino_pks.setError("Nomor PKS harus diisi");
            }else if(tahun.trim().equals("")){
                ithn.setError("Tahun harus diisi");
            }else if(jangka_waktu.trim().equals("")){
                ijw.setError("Jangka Waktu harus diisi");
            }else if(tentang.trim().equals("")){
                itt.setError("Nama Judul harus diisi");
            }else if(filePdf==null){
                idf.setError("Silahkan Upload File");
            }else{
                inputdataSamarinda();
            }
        });
        btnUpdate.setOnClickListener(view -> {
            mou = ino_mou.getText().toString();
            pks = ino_pks.getText().toString();
            tahun = ithn.getText().toString();
            jangka_waktu = ijw.getText().toString();
            unitkerja = iuk.getText().toString();
            mitrakerja = imk.getText().toString();
            tentang = itt.getText().toString();
            tanggal = itgl_pks.getText().toString();
            tahapan = String.valueOf(ithp.getSelectedItem());
            if (mou.trim().equals("")){
                ino_mou.setError("Nomor MOU harus diisi");
            }else if(pks.trim().equals("")){
                ino_pks.setError("Nomor PKS harus diisi");
            }else if(tahun.trim().equals("")){
                ithn.setError("Tahun harus diisi");
            }else if(jangka_waktu.trim().equals("")){
                ijw.setError("Jangka Waktu harus diisi");
            }else if(tentang.trim().equals("")){
                itt.setError("Nama Judul harus diisi");
            }else{
                updateDataSamarinda();
            }
        });
        uploadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFile();
            }
        });
    }
    // Request code for selecting a PDF document.
    private static final int PICK_PDF_FILE = 2;

    private void openFile() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("application/pdf|zip/*");
        startActivityForResult(intent, PICK_PDF_FILE);
    }
    @Override

    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {
        super.onActivityResult(requestCode, resultCode, resultData);
        if (requestCode == PICK_PDF_FILE
                && resultCode == Activity.RESULT_OK) {
            // The result data contains a URI for the document or directory that
            // the user selected.
            if (resultData != null) {
                uri = resultData.getData();
                Log.e("uriii",""+uri);
                String filePath = getRealPathFromUri(uri);
                labelUpload.setText(filePath);
                parsingFile();
                // Perform operations on the document using its URI.
            }
        }
    }
    public void parsingFile(){
        try {
            if (uri!=null) {
                String filePath = getRealPathFromUri(uri);
                if (filePath != null && !filePath.isEmpty()) {
                    File file = new File(filePath);
                    if (file.exists()) {
                        // creates RequestBody instance from file
                        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                        // MultipartBody.Part is used to send also the actual filename
                        filePdf = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
                    }
                }
            }
        }
        catch (Exception e) {
            Log.e("error", String.valueOf(e));
        }
    }
    public String getRealPathFromUri(final Uri uri) {
        // DocumentProvider
        if (DocumentsContract.isDocumentUri(this, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(this, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(this, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(this, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    private String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    private boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    private boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    private boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    private boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }
    public void showDateDialog(EditText itgl_pks){
        Calendar calendar = Calendar.getInstance();
        int mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH);
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        itgl_pks.setText(dateFormatter.format(newDate.getTime()));
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
    public void requestPermissionDialog() {
        if (SDK_INT >= Build.VERSION_CODES.R) {
            try {
//                Intent intent = new Intent();
//                intent.setAction(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
//                intent.setData(Uri.parse(String.format("package:%s", getApplicationContext().getPackageName())));
//                startActivity(intent);
            } catch (Exception e) {
                Intent obj = new Intent();
                obj.setAction(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                startActivity(obj);

            }
        } else {
            ActivityCompat.requestPermissions(TambahdataSamarindaActivity.this, new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, REQUEST_CODE);
        }
    }
    public boolean permission() {
        if (SDK_INT >= Build.VERSION_CODES.R) { // R is Android 11
            return Environment.isExternalStorageManager();
        } else {
            int write = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
            int read = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);

            return write == PackageManager.PERMISSION_GRANTED
                    && read == PackageManager.PERMISSION_GRANTED;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0) {
                    boolean storage = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean read = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if (storage && read) {
                        //next activity
                    } else {
                        //show msg kai permission allow nahi havai
                    }
                }
                break;
        }
    }

    public void inputdataSamarinda() {
        APIRequestData ardDatasmd = RetroServer.konekRetrofit().create(APIRequestData.class);
        rbTentang = RequestBody.create(MultipartBody.FORM, String.valueOf(tentang));
        rbMou = RequestBody.create(MultipartBody.FORM, String.valueOf(mou));
        rbPks = RequestBody.create(MultipartBody.FORM, String.valueOf(pks));
        rbTanggal = RequestBody.create(MultipartBody.FORM, String.valueOf(tanggal));
        rbJangkaWaktu = RequestBody.create(MultipartBody.FORM, String.valueOf(jangka_waktu));
        rbUnitkerja = RequestBody.create(MultipartBody.FORM, String.valueOf(unitkerja));
        rbMitrakerja = RequestBody.create(MultipartBody.FORM, String.valueOf(mitrakerja));
        rbTahapan = RequestBody.create(MultipartBody.FORM, String.valueOf(tahapan));
        rbTahun = RequestBody.create(MultipartBody.FORM, String.valueOf(tahun));
        rbKabKota = RequestBody.create(MultipartBody.FORM, String.valueOf(codeAcces));
        PrefManager prf = new PrefManager(this);
        Call<ResponseData> inputdata = ardDatasmd.ardInsertsmd("Bearer "+
                prf.getString(Const.TOKEN),rbKabKota,rbTentang,rbMou,rbPks,rbTanggal, rbJangkaWaktu,rbUnitkerja,rbMitrakerja,rbTahapan,rbTahun, filePdf);

        inputdata.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if(response.isSuccessful()){
                    String pesan = response.body().getPesan();
                    Toast.makeText(TambahdataSamarindaActivity.this, pesan,
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TambahdataSamarindaActivity.this, PendakerActivity.class);
                    intent.putExtra("codeAccess", codeAcces);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable throwable) {
                Toast.makeText(TambahdataSamarindaActivity.this,
                        "Gagal Menghubungi Server | "+throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateDataSamarinda() {
        APIRequestData ardDatasmd = RetroServer.konekRetrofit().create(APIRequestData.class);
        rbTentang = RequestBody.create(MultipartBody.FORM, String.valueOf(tentang));
        rbMou = RequestBody.create(MultipartBody.FORM, String.valueOf(mou));
        rbPks = RequestBody.create(MultipartBody.FORM, String.valueOf(pks));
        rbTanggal = RequestBody.create(MultipartBody.FORM, String.valueOf(tanggal));
        rbJangkaWaktu = RequestBody.create(MultipartBody.FORM, String.valueOf(jangka_waktu));
        rbUnitkerja = RequestBody.create(MultipartBody.FORM, String.valueOf(unitkerja));
        rbMitrakerja = RequestBody.create(MultipartBody.FORM, String.valueOf(mitrakerja));
        rbTahapan = RequestBody.create(MultipartBody.FORM, String.valueOf(tahapan));
        rbTahun = RequestBody.create(MultipartBody.FORM, String.valueOf(tahun));
        rbKabKota = RequestBody.create(MultipartBody.FORM, String.valueOf(codeAcces));
        PrefManager prf = new PrefManager(this);
        Call<ResponseData> inputdata = ardDatasmd.ardUpdatesmd(Long.valueOf(idValue),"Bearer "+prf.getString(Const.TOKEN),rbKabKota,rbTentang,rbMou,rbPks,rbTanggal,rbJangkaWaktu,rbUnitkerja,rbMitrakerja,rbTahapan,rbTahun, filePdf);

        inputdata.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if(response.isSuccessful()){
                    String pesan = response.body().getPesan();
                    Toast.makeText(TambahdataSamarindaActivity.this, pesan, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TambahdataSamarindaActivity.this, PendakerActivity.class);
                    intent.putExtra("codeAccess", codeAcces);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable throwable) {
                Toast.makeText(TambahdataSamarindaActivity.this, "Gagal Menghubungi Server | "+throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}