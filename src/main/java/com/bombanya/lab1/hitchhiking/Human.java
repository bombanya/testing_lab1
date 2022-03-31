package com.bombanya.lab1.hitchhiking;

import java.util.ArrayList;
import java.util.List;

import static com.bombanya.lab1.hitchhiking.Achievement.Purpose.*;

public class Human extends EarthCreature{

    private static final List<Achievement> achievements = new ArrayList<>();

    static {
        achievements.add(new Achievement("wheel", MAKE_MONEY, 42));
        achievements.add(new Achievement("New York", MAKE_MONEY, 300));
        achievements.add(new Achievement("war", CONQUER_THE_WORLD, 666));
    }

    public Human(String name){
        super(name);
    }

    @Override
    public List<Achievement> getAchievements() {
        return achievements;
    }

    @Override
    public long countCreatureIcq(EarthCreature creature) {
        List<Achievement> creatureAchievements = creature.getAchievements();
        long icq = 0;
        for (Achievement achievement : creatureAchievements) {
            if (achievement.getPurpose() == MAKE_MONEY) icq += achievement.getProfit() * 100;
            else if (achievement.getPurpose() == CONQUER_THE_WORLD) icq += achievement.getProfit() * 10;
        }
        return icq;
    }
}
