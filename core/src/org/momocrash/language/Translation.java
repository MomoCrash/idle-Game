package org.momocrash.language;

public enum Translation {

    PAUSE("pause", "Game is now paused ! (Click to return)"),

    MONEY ("money", "Money %money% - Money/s %money_pers%"),
    ENERGY ("energy", "Energy %energy% - Energy/s %energy_pers%"),

    ;

    private final String id;
    private final String defaultText;

    private Translation(String translationId, String defaultText) {

        this.id = translationId;
        this.defaultText = defaultText;

    }


    public String getTranslatedText(Language lang) {

        return lang.getTranslation(getId()) == null ? getDefaultText() : lang.getTranslation(getId());

    }
    public String getTranslatedText(Language lang, String[] placeHolder) {

        String currentText = getTranslatedText(lang);

        for (String holder : placeHolder) {

            String[] combi = holder.split(",");
            currentText = currentText.replace("%" + combi[0] + "%", combi[1]);

        }

        return currentText;

    }

    public String getId() {
        return id;
    }

    public String getDefaultText() {
        return defaultText;
    }
}
