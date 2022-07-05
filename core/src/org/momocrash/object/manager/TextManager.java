package org.momocrash.object.manager;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import org.momocrash.object.text.AnimatedText;
import org.momocrash.object.text.TextObject;

public class TextManager {

    private final ObjectList<TextObject> textObjects;

    public TextManager() {

         textObjects = new ObjectArrayList<>();

    }

    public void renderText() {

        for (TextObject textObject : textObjects) {
            textObject.drawText();
        }

    }

    public void updateAnimation() {

        for (TextObject textObject : textObjects) {
            if (!textObject.animate()) {
                textObjects.remove(textObject);
            }
        }

    }

    public void createAnimation(String text, float x, float y, float xTranslation, float yTranslation) {

        AnimatedText animatedText = new AnimatedText(text, x, y, xTranslation, yTranslation);
        animatedText.startAnimation(0.3f, 0.3f);
        textObjects.add(animatedText);

    }

}
