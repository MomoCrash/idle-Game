package org.momocrash.language;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;

import javax.xml.transform.Transformer;
import java.io.IOException;

public enum Language {

    FRENCH("fr", "Français"),
    ENGLISH("en", "English"),

    ;

    private final String language;
    private final String path;
    private Object2ObjectMap<String, String> translations = new Object2ObjectArrayMap<>();

    Language(final String path, final String name) {
        this.language = name;
        this.path = path;
    }

    public void loadLanguage() {

        FileHandle file = Gdx.files.internal(path + ".txt");
        String[] text = file.readString().split("@@");

        for (String toFormat : text) {
            String[] format = toFormat.replace("\n", "")
                    .replace("\r", "")
                    .split(":");
            if (format.length < 2) continue;
            translations.put(format[0], format[1]);
        }

    }

    public String getTranslation(String id) {

        return translations.get(id);

    }

}
