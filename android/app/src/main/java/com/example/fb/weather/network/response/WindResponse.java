package com.example.fb.weather.network.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by fb on 2017/5/18.
 */

public class WindResponse implements Parcelable{

    public String deg;    //风向 360°
    public String dir;    //风向 方位
    public String sc;     //风力
    public String spd;    // 风速


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.deg);
        dest.writeString(this.dir);
        dest.writeString(this.sc);
        dest.writeString(this.spd);
    }

    public WindResponse() {
    }

    protected WindResponse(Parcel in) {
        this.deg = in.readString();
        this.dir = in.readString();
        this.sc = in.readString();
        this.spd = in.readString();
    }

    public static final Creator<WindResponse> CREATOR = new Creator<WindResponse>() {
        @Override
        public WindResponse createFromParcel(Parcel source) {
            return new WindResponse(source);
        }

        @Override
        public WindResponse[] newArray(int size) {
            return new WindResponse[size];
        }
    };
}
