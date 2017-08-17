package com.example.android.miwok;
/*
Copyright [2017] [Muhammad Elsayed]

        Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

        Unless required by applicable law or agreed to in writing, software
        distributed under the License is distributed on an "AS IS" BASIS,
        WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
        See the License for the specific language governing permissions and
        limitations under the License.
**/
import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.android.miwok.R.id.imageView;

public class CustomWordArrayAdapter extends ArrayAdapter<Word> {

    private int mColorResourceId;

    public CustomWordArrayAdapter(Context context, ArrayList<Word> words, int ColorResourceId) {
        super(context, 0, words);
        mColorResourceId = ColorResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        final Word currentWord = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView miwokTranslation = (TextView) convertView.findViewById(R.id.miwok_text_view);
        TextView englishTranslation = (TextView) convertView.findViewById(R.id.english_text_view);
        miwokTranslation.setText(currentWord.getMiwokTranslation());
        englishTranslation.setText(currentWord.getEnglishTranslation());

        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
        if (currentWord.hasImage()) {
            imageView.setImageResource(currentWord.getImageResourceID());
        } else {
            imageView.setVisibility(View.GONE);
        }

        View textContainer = convertView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        textContainer.setBackgroundColor(color);


        return convertView;
    }
}
