package com.example.pets;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import com.example.pets.data.PetsContract;
import com.example.pets.data.PetsContract.PetEntry;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    PetsCursorAdapter mCursorAdapter;
    static final int PET_LOADER = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView list = findViewById(R.id.list);
        View emptyView = findViewById(R.id.empty_view);
        list.setEmptyView(emptyView);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, EditorActivity.class);
            startActivity(intent);
        });
        mCursorAdapter = new PetsCursorAdapter(this, null);
        list.setAdapter(mCursorAdapter);
        getSupportLoaderManager().initLoader(PET_LOADER, null, this);
        list.setOnItemClickListener((parent, view, position, id) -> {
            Intent i = new Intent(MainActivity.this, EditorActivity.class);
            Uri currentPet = ContentUris.withAppendedId(PetEntry.CONTENT_URI, id);
            i.setData(currentPet);
            startActivity(i);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    private void insertPet() {
        ContentValues values = new ContentValues();
        values.put(PetEntry.NAME, "Toto");
        values.put(PetEntry.BREED, "Terriar");
        values.put(PetEntry.GENDER, 1);
        values.put(PetEntry.WEIGHT, 7);
        Uri uri = getContentResolver().insert(PetsContract.PetEntry.CONTENT_URI, values);

        if(uri == null) {
            Toast.makeText(this, "Failed to add dummy data", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Dummy Data Added", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteAllPets() {
        int res = getContentResolver().delete(PetsContract.PetEntry.CONTENT_URI, null, null);
        if(res == -1) {
            Toast.makeText(this, "Failed to delete data", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Data Deleted", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_insert_dummy_data:
                insertPet();
                return true;
            case R.id.action_delete_all_entries:
                deleteAllPets();
                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        String[] projection = {
                PetEntry._ID,
                PetEntry.NAME,
                PetEntry.BREED
        };

        return new CursorLoader(this,
                PetsContract.PetEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);
    }
}