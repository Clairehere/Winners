package fr.wildcodeschool.winners;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wilder on 05/04/18.
 */

public class Model implements Parcelable {
    public Model(String name, int life, int speed, int attaque, String image) {
        this.name = name;
        this.life = life;
        this.speed = speed;
        this.attaque = attaque;
        this.image = image;
    }

    protected Model(Parcel in) {
        name = in.readString();
        life = in.readInt();
        speed = in.readInt();
        attaque = in.readInt();
        image = in.readString();
    }

    public static final Creator<Model> CREATOR = new Creator<Model>() {
        @Override
        public Model createFromParcel(Parcel in) {
            return new Model(in);
        }

        @Override
        public Model[] newArray(int size) {
            return new Model[size];
        }
    };

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getAttaque() {
        return attaque;
    }

    public void setAttaque(int attaque) {
        this.attaque = attaque;
    }

    private String name;
    private int life;
    private int speed;
    private int attaque;
    private String image;



    public Model(String name, int life, int speed, int attaque) {
        this.name = name;
        this.life = life;
        this.speed = speed;
        this.attaque = attaque;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(life);
        dest.writeInt(speed);
        dest.writeInt(attaque);
        dest.writeString(image);
    }
}
