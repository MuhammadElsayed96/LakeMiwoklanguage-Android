/*
* Copyright [2017] [Muhammad Elsayed]

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*
* */

package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class NumbersFragment extends Fragment {

    public NumbersFragment() {
        // Required empty public constructor
    }


    private MediaPlayer mMediaPlayer;
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener(){
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };


    private AudioManager mAudioManager;
    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }

        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);


        final ArrayList<Word> Numbers = new ArrayList<Word>();
        Numbers.add(new Word("one", "lutti", R.drawable.number_one, R.raw.number_one));
        Numbers.add(new Word("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        Numbers.add(new Word("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        Numbers.add(new Word("four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        Numbers.add(new Word("five", "massokka", R.drawable.number_five, R.raw.number_five));
        Numbers.add(new Word("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        Numbers.add(new Word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        Numbers.add(new Word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        Numbers.add(new Word("nine", "wo'e", R.drawable.number_nine, R.raw.number_nine));
        Numbers.add(new Word("ten", "na'aacha", R.drawable.number_ten, R.raw.number_ten));


        CustomWordArrayAdapter customAdapter = new CustomWordArrayAdapter(getContext(), Numbers, R.color.category_numbers);

        ListView numbersListView = (ListView) rootView.findViewById(R.id.word_list_view);
        numbersListView.setAdapter(customAdapter);
        numbersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = Numbers.get(position);
                Log.v("NumbersActivity", "Current word: " + word);

                releaseMediaPlayer();
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // We have audio focus now
                    mMediaPlayer = mMediaPlayer.create(getActivity(), word.getmAudioResourceID());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }

            }
        });
        return rootView;
    }


    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();
            // Set the media player back to null. For this code, I've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

}
