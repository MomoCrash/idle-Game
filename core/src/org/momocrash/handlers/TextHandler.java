package org.momocrash.handlers;

import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import org.momocrash.data.Player;
import org.momocrash.object.text.AnimatedText;
import org.momocrash.object.text.BasicText;
import org.momocrash.object.text.Text;

import java.util.UUID;

public class TextHandler {

    private final ObjectList<Text> textObjects;
    private final Object2ObjectMap<UUID, Text> ownedText;

    public TextHandler() {

         textObjects = new ObjectArrayList<>();
         ownedText = new Object2ObjectArrayMap<>();

    }

    public void renderText() {

        for (Text Text : textObjects) {
            Text.drawText();
        }
        for (Text value : ownedText.values()) {
            value.drawText();
        }

    }

    public void updateAnimation() {

        for (Text Text : textObjects) {
            if (!Text.animate()) {
                textObjects.remove(Text);
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
