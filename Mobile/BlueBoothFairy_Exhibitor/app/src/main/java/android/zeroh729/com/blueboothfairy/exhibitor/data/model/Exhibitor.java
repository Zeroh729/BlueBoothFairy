package android.zeroh729.com.blueboothfairy.exhibitor.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Exhibitor implements Parcelable{
    private String id;
    private String name;
    private String imageUrl;

    public Exhibitor() {
        id = "";
        name = "";
        imageUrl = "";
    }

    protected Exhibitor(Parcel in) {
        id = in.readString();
        name = in.readString();
        imageUrl = in.readString();
    }

    public static final Creator<Exhibitor> CREATOR = new Creator<Exhibitor>() {
        @Override
        public Exhibitor createFromParcel(Parcel in) {
            return new Exhibitor(in);
        }

        @Override
        public Exhibitor[] newArray(int size) {
            return new Exhibitor[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getInitials(){
        String[] words = name.split(" ");
        String initials = "";
        for(String word : words){
            initials += word.toUpperCase().charAt(0);
        }
        return initials;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(imageUrl);
    }
}
