package org.momocrash.object.text;

import org.momocrash.data.Player;
import org.momocrash.language.Translation;

public class TranslatedIText extends IText {
    public TranslatedIText(Player player, Translation translation,
                           String[] replacements, float x, float y) {
        super(translation.getTranslatedText(player.getLang(), replacements), x, y);
    }

    public TranslatedIText(Player player, Translation translation, float x, float y) {
        super(translation.getTranslatedText(player.getLang(), new String[0]), x, y);
    }
}
