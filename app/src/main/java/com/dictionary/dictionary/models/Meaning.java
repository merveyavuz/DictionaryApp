package com.dictionary.dictionary.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Meaning implements Parcelable {
    private DictBaseObject phrase;
    private List<DictBaseObject> meanings;
    private Long meaningId;
    private List<Long> authors;

    public List<DictBaseObject> getMeanings() { return meanings; }
    public void setMeanings(List<DictBaseObject> meanings) { this.meanings = meanings; }
    public Long getMeaningId() { return meaningId; }
    public void setMeaningId(Long meaningId) { this.meaningId = meaningId; }
    public List<Long> getAuthors() { return authors; }
    public void setAuthors(List<Long> authors) { this.authors = authors; }
    public DictBaseObject getPhrase() { return phrase; }
    public void setPhrase(DictBaseObject phrase) { this.phrase = phrase; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeList(this.meanings);
        dest.writeList(this.authors);
        dest.writeLong(this.meaningId);
        dest.writeValue(this.phrase);
    }

    public Meaning() {
    }

    protected Meaning(Parcel in) {

        this.authors = in.readArrayList(this.getClass().getClassLoader());
        this.meaningId = in.readLong();
        this.phrase = (DictBaseObject) in.readValue(DictBaseObject.class.getClassLoader());
        this.meanings = in.readArrayList(this.getClass().getClassLoader());

    }

    public static final Parcelable.Creator<Meaning> CREATOR = new Parcelable.Creator<Meaning>() {
        @Override
        public Meaning createFromParcel(Parcel source) {
            return new Meaning(source);
        }

        @Override
        public Meaning[] newArray(int size) {
            return new Meaning[size];
        }
    };
}

