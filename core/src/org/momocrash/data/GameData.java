package org.momocrash.data;

import java.util.UUID;

public class GameData {

    private double money = 1000;
    private double moneyPerSecond;
    private double energy;
    private double energyPerSecond;
    private int time = 0;

    public void addMoney(double amount) {
        money += amount;
    }
    public void withdrawMoneyPerSecond(double amount) {
        moneyPerSecond -= amount;
    }
    public boolean hasEnoughMoney(int toSpent) {
        if (money >= toSpent) {
            return true;
        }
        return false;
    }
    public void addMoneyPerSecond(double amount) {
        moneyPerSecond += amount;
    }

    public void incrementTime() {

        time++;

    }

    // GETTER
    public double getPerSecondEnergy() {
        return energyPerSecond;
    }

    public double getPerSecondMoney() {
        return moneyPerSecond;
    }

    public double getEnergy() {
        return energy;
    }

    public double getMoney() {
        return money;
    }

    public int getTime() {
        return time;
    }

}
