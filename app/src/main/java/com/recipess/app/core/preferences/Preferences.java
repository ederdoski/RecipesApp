package com.recipess.app.core.preferences;

import static com.adrenastudies.trufisapp.core.commons.ConstantsKt.EMPTY_STRING;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

    private static final String TAG = "com.recipes.app";
    private static final String RAPID_KEY = TAG + "_RAPID_KEY";
    private static final String RAPID_HOST = TAG + "_RAPID_HOST";
    private final Context context;

    Preferences(Context ctx) {
        context = ctx;
    }

    //----------------------------- Set Preferences ---------------------------------------------

    public void setRapidKey(String data) { setValue(context, RAPID_KEY, data); }
    public void setRapidHost(String data) { setValue(context, RAPID_HOST, data); }

    //----------------------------- Get Preferences ---------------------------------------------

    public String getRapidKey() { return getStringValue(context, RAPID_KEY, "c155fa45bbmshc56e1743aa4d94fp10b94cjsn083f93254542"); }
    public String getRapidHost() { return getStringValue(context, RAPID_HOST, "tasty.p.rapidapi.com"); }

    /*---------------------------------------------------------------------------*/

    private static void setValue(Context ctx, String key, String value) {
        SharedPreferences prefs = getPreferences(ctx);
        prefs.edit().putString(key, value).apply();
    }

    private static void setValue(Context ctx, String key, int value) {
        SharedPreferences prefs = getPreferences(ctx);
        prefs.edit().putInt(key, value).apply();
    }

    private static String getStringValue(Context ctx, String key, String value) {
        SharedPreferences prefs = getPreferences(ctx);
        return prefs.getString(key, value);
    }

    private static int getIntValue(Context ctx, String key, int value) {
        SharedPreferences prefs = getPreferences(ctx);
        return prefs.getInt(key, value);
    }

    private static SharedPreferences getPreferences(Context ctx) {
        return ctx.getSharedPreferences(TAG, Context.MODE_PRIVATE);
    }

    public static void deletePreference(Context context, String key) {
        SharedPreferences prefs = getPreferences(context);
        prefs.edit().remove(key).commit();
    }

}
