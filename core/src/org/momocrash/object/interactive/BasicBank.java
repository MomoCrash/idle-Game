package org.momocrash.object.interactive;

import org.momocrash.Player;

import java.util.UUID;

public class BasicBank extends InteractiveObject {

    private final Player owner;
    private final double money;

    private final long duration;
    private long activated;

    public BasicBank(Player owner, double money, int duration, float x, float y) {
        super((int) x,(int) y, 75, 75, "bank.png");
        this.owner = owner;
        this.money = money;
        this.duration = duration * 1000L;

        activate();
    }
    @Override
    public void activate() {

        if (enabled()) return;

        activated = System.currentTimeMillis();
        owner.getData().addMoneyPerSecond(money);
        isUse = true;

    }

    @Override
    public void use() {

        if (System.currentTimeMillis() <= (activated + duration)) {

            owner.getData().addMoney(money);

        } else if (enabled()) {

            isUse = false;
            owner.getData().withdrawMoneyPerSecond(money);

        }

    }

    public Player getOwner() {
        return owner;
    }

    public double getMoney() {
        return money;
    }

}
