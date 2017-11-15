package cz.muni.fi.pv256.movio.uco_396035.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by fabian on 19.10.2017.
 */

public class Movie implements Parcelable {

    private int mCoverId;
    private long mReleaseDate;
    private String mCoverPath;
    private String mTitle;

    public Movie(int coverId, long releaseDate, String coverPath, String title) {
        this.mCoverId = coverId;
        this.mReleaseDate = releaseDate;
        this.mCoverPath = coverPath;
        this.mTitle = title;
    }

    public int getCoverId() {
        return mCoverId;
    }

    public void setCoverId(int mCoverId) {
        this.mCoverId = mCoverId;
    }

    public long getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(long mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }

    public String getCoverPath() {
        return mCoverPath;
    }

    public void setCoverPath(String mCoverPath) {
        this.mCoverPath = mCoverPath;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public Movie(Parcel in) {
        this.mCoverId = in.readInt();
        this.mReleaseDate = in.readLong();
        this.mCoverPath = in.readString();
        this.mTitle = in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mCoverId);
        parcel.writeLong(mReleaseDate);
        parcel.writeString(mCoverPath);
        parcel.writeString(mTitle);
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {

        @Override
        public Movie createFromParcel(Parcel parcel) {
            return new Movie(parcel);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
