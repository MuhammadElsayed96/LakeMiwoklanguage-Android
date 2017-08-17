package com.example.android.miwok;
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
* */
public class Word {
    private String english, miwok;
    private static final int NO_IMAGE_PROVIDED = -1;
    private int mImageResourceID = NO_IMAGE_PROVIDED;
    private int mAudioResourceID;

    public Word(String english, String miwok, int audioResourceID) {
        this.english = english;
        this.miwok = miwok;
        mAudioResourceID = audioResourceID;
    }

    public Word(String english, String miwok, int imageResourceID, int audioResourceID) {
        this.english = english;
        this.miwok = miwok;
        this.mImageResourceID = imageResourceID;
        mAudioResourceID = audioResourceID;
    }

    public String getEnglishTranslation() {
        return english;
    }

    public String getMiwokTranslation() {
        return miwok;
    }

    public int getImageResourceID() {
        return mImageResourceID;
    }

    public boolean hasImage() {
        return mImageResourceID != NO_IMAGE_PROVIDED;
    }

    public int getmAudioResourceID() {
        return mAudioResourceID;
    }

    @Override
    public String toString() {
        return "Word{" +
                "english='" + english + '\'' +
                ", miwok='" + miwok + '\'' +
                ", mImageResourceID=" + mImageResourceID +
                ", mAudioResourceID=" + mAudioResourceID +
                '}';
    }


}