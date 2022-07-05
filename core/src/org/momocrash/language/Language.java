package org.momocrash.language;

public enum Language {

    FRENCH("Français", 0),
    ENGLISH("English", 1),

    ;

    private final String language;

    Language(final String name, int id) {
        this.language = name;
    }

    public String getLanguage() {
        return language;
    }

}
