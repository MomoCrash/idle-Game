package org.momocrash.object.text;

import org.momocrash.Player;
import org.momocrash.language.Translation;

public class TranslatedText extends TextObject {
    public TranslatedText(Player player, Translation translation,
                          String[] replacements, float x, float y) {
        super(translation.getTranslatedText(player.getLang(), replacements), x, y);
    }

    public TranslatedText(Player player, Translation translation, float x, float y) {
        super(translation.getTranslatedText(player.getLang(), new String[0]), x, y);
    }
}
