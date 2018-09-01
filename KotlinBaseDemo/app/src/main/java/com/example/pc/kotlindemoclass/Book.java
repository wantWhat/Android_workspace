package com.example.pc.kotlindemoclass;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/**
 * 实现序列化，此方式较Serializable更优，内存开销小，
 * Serializable因使用反射机制实现序列化，会产生大量的变量，消耗内存，
 * 而Parcelable 直接操作内存，效率要高
 * read 和write 要严格按照相同的顺序
 * */
public class Book implements Parcelable{
    private String name;
    private String price;
    private ArrayList keyWorld;
    private Author author;//对象必须也要实现序列化

    public ArrayList<String> getKeyWorld() {
        return keyWorld;
    }

    public void setKeyWorld(ArrayList<String> keyWorld) {
        this.keyWorld = keyWorld;
    }

    public Book() {

    }
    protected Book(Parcel in) {
        name = in.readString();
        price =  in.readString();
        author = in.readParcelable(Author.class.getClassLoader());
        keyWorld = in.readArrayList(String.class.getClassLoader());
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", keyWorld=" + keyWorld +
                ", author=" + author +
                '}';
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(price);
        parcel.writeParcelable(author, i);//写对象成员
        parcel.writeList(keyWorld);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
