/*
 * Copyright (c) 2016 Carmen Alvarez
 *
 * This file is part of Poet Assistant.
 *
 * Poet Assistant is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Poet Assistant is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Poet Assistant.  If not, see <http://www.gnu.org/licenses/>.
 */

package ca.rmen.android.poetassistant.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.jraf.android.prefs.DefaultBoolean;
import org.jraf.android.prefs.DefaultFloat;
import org.jraf.android.prefs.DefaultString;
import org.jraf.android.prefs.DefaultStringSet;
import org.jraf.android.prefs.Name;
import org.jraf.android.prefs.Prefs;

import java.util.Set;

@Prefs
public class Settings {
    @SuppressWarnings("unused")
    public static final String THEME_LIGHT = "Light";
    public static final String THEME_DARK = "Dark";
    public static final String THEME_AUTO = "Auto";
    public static final String VOICE_SYSTEM = "VOICE_SYSTEM";
    static final String PREF_VOICE = "PREF_VOICE";
    // v1 for the speed was a string of values 0.25, 0.5, 1.0, 1.5, or 2.0
    // v2 is a float between 0 and 200
    public static final String PREF_VOICE_SPEED = "PREF_VOICE_SPEED_V2";
    public static final String PREF_VOICE_PITCH = "PREF_VOICE_PITCH";
    @SuppressWarnings("unused")
    private static final float VOICE_SPEED_NORMAL = 100f;
    @SuppressWarnings("unused")
    private static final float VOICE_PITCH_NORMAL = 100f;
    static final String PREF_SYSTEM_TTS_SETTINGS = "PREF_SYSTEM_TTS_SETTINGS";
    static final String PREF_THEME = "PREF_THEME";
    static final String PREF_WOTD_ENABLED = "PREF_WOTD_ENABLED";
    @SuppressWarnings("unused")
    private static final String PREF_FAVORITE_WORDS = "PREF_FAVORITE_WORDS";
    @SuppressWarnings("unused")
    private static final String PREF_SUGGESTED_WORDS = "pref_suggestions";
    @SuppressWarnings("unused")
    private static final String PREF_ALL_RHYMES_ENABLED = "PREF_ALL_RHYMES_ENABLED";

    @SuppressWarnings("unused")
    @Name(PREF_VOICE)
    @DefaultString(VOICE_SYSTEM)
    String voice;

    @SuppressWarnings("unused")
    @Name(PREF_VOICE_SPEED)
    @DefaultFloat(VOICE_SPEED_NORMAL)
    Float voiceSpeed;

    @SuppressWarnings("unused")
    @Name(PREF_VOICE_PITCH)
    @DefaultFloat(VOICE_PITCH_NORMAL)
    Float voicePitch;

    @SuppressWarnings("unused")
    @Name(PREF_THEME)
    String theme;

    @SuppressWarnings("unused")
    @Name(PREF_WOTD_ENABLED)
    @DefaultBoolean(true)
    Boolean isWotdEnabled;

    @SuppressWarnings("unused")
    @Name(PREF_SUGGESTED_WORDS)
    @DefaultStringSet({})
    Set<String> suggestedWords;

    @SuppressWarnings("unused")
    @Name(PREF_FAVORITE_WORDS)
    @DefaultStringSet({})
    Set<String> favoriteWords;

    @SuppressWarnings("unused")
    @Name(PREF_ALL_RHYMES_ENABLED)
    @DefaultBoolean(false)
    Boolean isAllRhymesEnabled;

    public static void migrateSettings(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String deprecatedSpeedPrefKey = "PREF_VOICE_SPEED";
        if (prefs.contains(deprecatedSpeedPrefKey)) {
            float deprecatedSpeedPrefValue = Float.valueOf(prefs.getString(deprecatedSpeedPrefKey, "1.0"));
            float newSpeedPrefValue = deprecatedSpeedPrefValue * 100;
            prefs.edit()
                    .putFloat(PREF_VOICE_SPEED, newSpeedPrefValue)
                    .remove(deprecatedSpeedPrefKey)
                    .apply();
        }
    }

}
