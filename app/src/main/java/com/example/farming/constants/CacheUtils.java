package com.example.farming.constants;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.farming.entity.LandInfo;
import com.example.farming.entity.UserInfo;
import com.example.farming.util.StringUtil;

public class CacheUtils {

    private CacheUtils() {
        throw new AssertionError();
    }

    public static void putUserInfo(UserInfo userInfo, Context context) {
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = context.getSharedPreferences("user_info", Context.MODE_PRIVATE).edit();

        if (userInfo.getId() != null && userInfo.getId() != 0) {
            editor.putLong("userId", userInfo.getId());
        }
        if (userInfo.getIdentity() != null && userInfo.getIdentity() != 0) {
            editor.putInt("userIdentity", userInfo.getIdentity());
        }
        if (userInfo.getuName() != null && !userInfo.getuName().equals("")) {
            editor.putString("userName", userInfo.getuName());
        }
        if (userInfo.getuPassword() != null && !userInfo.getuPassword().equals("")) {
            editor.putString("pwd", userInfo.getuPassword());
        }
        editor.apply();
    }

    public static UserInfo getUserInfo(Context context) {
        UserInfo userInfo = new UserInfo();
        SharedPreferences preferences = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        userInfo.setId(preferences.getLong("userId", 0));
        userInfo.setIdentity((byte) preferences.getInt("userIdentity", 0));
        userInfo.setuName(preferences.getString("userName", ""));
        userInfo.setuPassword(preferences.getString("pwd", ""));
        return userInfo;
    }

    public static void putLandInfo(LandInfo landInfo, Context context) {
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = context.getSharedPreferences("land_info", Context.MODE_PRIVATE).edit();

        if (StringUtil.isEmpty(landInfo.getRegion())) {
            editor.putString("region", landInfo.getRegion());
        }
        if (landInfo.getRegionSquare() != null && landInfo.getRegionSquare() != 0) {
            editor.putFloat("regionSquare", landInfo.getRegionSquare().floatValue());
        }
        if (StringUtil.isEmpty(landInfo.getTag())) {
            editor.putString("tag", landInfo.getTag());
        }
        if (landInfo.getUid() != null && landInfo.getUid() != 0) {
            editor.putLong("u_id", landInfo.getUid());
        }
        if (landInfo.getSquare() != null && landInfo.getSquare() != 0) {
            editor.putFloat("square", landInfo.getSquare().floatValue());
        }
        if (landInfo.getTagSquare() != null && landInfo.getTagSquare() != 0) {
            editor.putFloat("tag_square", landInfo.getTagSquare().floatValue());
        }
        if (landInfo.getBlock() != null && landInfo.getBlock() != 0) {
            editor.putLong("block", landInfo.getBlock());
        }
        if (StringUtil.isEmpty(landInfo.getPlace())) {
            editor.putString("place", landInfo.getPlace());
        }
        editor.apply();
    }

    public static LandInfo getLandInfo(Context context) {
        LandInfo landInfo = new LandInfo();
        SharedPreferences preferences = context.getSharedPreferences("land_info", Context.MODE_PRIVATE);
        landInfo.setBlock(preferences.getInt("block", 0));
        landInfo.setPlace(preferences.getString("place", null));
        landInfo.setUid(preferences.getLong("u_id", 0));
        landInfo.setRegion(preferences.getString("region", null));
        landInfo.setRegionSquare((double) preferences.getFloat("regionSquare", 0));
        landInfo.setSquare((double) preferences.getFloat("square", 0));
        landInfo.setTagSquare((double) preferences.getFloat("tag_square", 0));
        return landInfo;
    }
}
