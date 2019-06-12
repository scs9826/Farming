/*刘云杰*/
package com.example.farming.entity;


import android.os.Parcel;
import android.os.Parcelable;

public class LandInfo implements Parcelable {
    private Long id;

    private Double square;

    private Long uid;

    private String region;

    private Double regionSquare;

    private String tag;

    private Double tagSquare;

    private Integer block;

    private String place;

    public LandInfo(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        if (in.readByte() == 0) {
            square = null;
        } else {
            square = in.readDouble();
        }
        if (in.readByte() == 0) {
            uid = null;
        } else {
            uid = in.readLong();
        }
        region = in.readString();
        if (in.readByte() == 0) {
            regionSquare = null;
        } else {
            regionSquare = in.readDouble();
        }
        tag = in.readString();
        if (in.readByte() == 0) {
            tagSquare = null;
        } else {
            tagSquare = in.readDouble();
        }
        if (in.readByte() == 0) {
            block = null;
        } else {
            block = in.readInt();
        }
        place = in.readString();
    }

    public static final Creator<LandInfo> CREATOR = new Creator<LandInfo>() {
        @Override
        public LandInfo createFromParcel(Parcel in) {
            return new LandInfo(in);
        }

        @Override
        public LandInfo[] newArray(int size) {
            return new LandInfo[size];
        }
    };

    public LandInfo() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSquare() {
        return square;
    }

    public void setSquare(Double square) {
        this.square = square;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public Double getRegionSquare() {
        return regionSquare;
    }

    public void setRegionSquare(Double regionSquare) {
        this.regionSquare = regionSquare;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public Double getTagSquare() {
        return tagSquare;
    }

    public void setTagSquare(Double tagSquare) {
        this.tagSquare = tagSquare;
    }

    public Integer getBlock() {
        return block;
    }

    public void setBlock(Integer block) {
        this.block = block;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place == null ? null : place.trim();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        if (square == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(square);
        }
        if (uid == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(uid);
        }
        dest.writeString(region);
        if (regionSquare == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(regionSquare);
        }
        dest.writeString(tag);
        if (tagSquare == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(tagSquare);
        }
        if (block == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(block);
        }
        dest.writeString(place);
    }

    @Override
    public String toString() {
        return "square=" + square +
                ", uid=" + uid +
                ", region='" + region + '\'' +
                ", regionSquare=" + regionSquare +
                ", tag='" + tag + '\'' +
                ", tagSquare=" + tagSquare +
                ", block=" + block +
                ", place='" + place;
    }
}