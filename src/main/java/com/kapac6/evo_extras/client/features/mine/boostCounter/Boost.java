package com.kapac6.evo_extras.client.features.mine.boostCounter;

public class Boost {
    public String type; // shards money
    public long duration; // 120 secundov * 1000 ms
    public double boost; // x1.5
    public long startStamp;
    public long lastStamp;
    protected long timeLeft; // оставшееся время буста
    public boolean nullable = false;

    public Boost(String type, int duration, double boost) {
        this.type = type;
        this.duration = duration;
        this.boost = boost;

        startStamp = System.currentTimeMillis();
        lastStamp = startStamp;
        timeLeft = duration;
    }

    public void tick() {
        if(lastStamp - startStamp < duration) {
            lastStamp = System.currentTimeMillis();
            timeLeft = duration - (lastStamp - startStamp);
        } else {
            nullable = true;
        }
        if (timeLeft <= 999) nullable = true;
    }
    public long getTimeLeft() {
        return timeLeft;
    }
}
