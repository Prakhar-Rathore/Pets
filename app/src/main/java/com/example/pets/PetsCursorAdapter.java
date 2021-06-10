package com.example.pets;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.pets.data.PetsContract;

public class PetsCursorAdapter extends CursorAdapter {

    public PetsCursorAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView name_tv = view.findViewById(R.id.name);
        TextView breed_tv = view.findViewById(R.id.breed);

        String name = cursor.getString(cursor.getColumnIndexOrThrow(PetsContract.PetEntry.NAME));
        String breed = cursor.getString(cursor.getColumnIndexOrThrow(PetsContract.PetEntry.BREED));

        if (TextUtils.isEmpty(breed)) {
            breed = context.getString(R.string.unknown_breed);
        }
        if(TextUtils.isEmpty(name)) {
            name = context.getString(R.string.anonymous);
        }

        name_tv.setText(name);
        breed_tv.setText(breed);
    }
}
