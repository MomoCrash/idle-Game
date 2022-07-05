package org.momocrash.language;

public enum Translation {

    PAUSE("pause", "Game is now paused !"),

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

        return this.defaultText;

    }
    public String getTranslatedText(Language lang, String[] placeHolder) {

        String currentText = this.defaultText;

        for (String holder : placeHolder) {

            String[] combi = holder.split(",");
            currentText = currentText.replace("%" + combi[0] + "%", combi[1]);

        }

        return currentText;

    }

}
