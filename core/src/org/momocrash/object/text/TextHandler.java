package org.momocrash.object.text;

import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import org.momocrash.data.Player;

import java.util.UUID;

public class TextHandler {

    private final ObjectList<IText> textObjects;
    private final Object2ObjectMap<UUID, IText> ownedText;

    public TextHandler() {

         textObjects = new ObjectArrayList<>();
         ownedText = new Object2ObjectArrayMap<>();

    }

    public void renderText() {

        for (IText IText : textObjects) {
            IText.drawText();
        }
        for (IText value : ownedText.values()) {
            value.drawText();
        }

    }

    public void updateAnimation() {

        for (IText IText : textObjects) {
            if (!IText.animate()) {
                textObjects.remove(IText);
            }
        }

    }

    public void createAnimation(String text, float x, float y, float xTranslation, float yTranslation) {

        AnimatedText animatedText = new AnimatedText(text, x, y, xTranslation, yTranslation);
        animatedText.startAnimation(0.3f, 0.3f);
        textObjects.add(animatedText);

    }

    public void showPlayerName(Player player) {

        ownedText.put(player.getUniqueId(), new BasicText(player.getName(),
                player.getX() - (5 + (player.getName().length() * 1.3f)),
                player.getY() + 65)
        );


    }

    public void hidePlayerName(Player player) {

        ownedText.remove(player.getUniqueId());

    }

}
