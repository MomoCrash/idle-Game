package org.momocrash.object.interactive;

import org.momocrash.IdleMain;
import org.momocrash.data.Player;

public class BasicBank extends InteractiveObject {

    private final Player owner;
    private final double money;

    private final long duration;
    private long activated;

    public BasicBank(Player owner, double money, int duration, float x, float y) {
        super((int) x,(int) y, 50, 50, "bank.png");
        this.owner = owner;
        this.money = money;
        this.duration = duration * 1000L;

        activate();
    }
    @Override
    public void activate() {

        if (enabled()) return;

        activated = System.currentTimeMillis();
        owner.getPlayerData().addMoneyPerSecond(money);
        isUse = true;

    }

    @Override
    public void use() {

        if (System.currentTimeMillis() <= (activated + duration)) {

            owner.getPlayerData().addMoney(money);
            IdleMain.getInstance().getTextManager()
                    .createAnimation("+" + money, getX() + 3f, getY() + 60f, 10f, 5f);

        } else if (enabled()) {

            isUse = false;
            owner.getPlayerData().withdrawMoneyPerSecond(money);

        }

    }

    public Player getOwner() {
        return owner;
    }

    public double getMoney() {
        return money;
    }

}
