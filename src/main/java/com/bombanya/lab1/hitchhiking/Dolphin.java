package com.bombanya.lab1.hitchhiking;

import java.util.ArrayList;
import java.util.List;

import static com.bombanya.lab1.hitchhiking.Achievement.Purpose.*;

public class Dolphin extends EarthCreature{

    private static final List<Achievement> achievements = new ArrayList<>();

    static {
        achievements.add(new Achievement("splashing in the water", HAVE_FUN, 0));
        achievements.add(new Achievement("play", HAVE_FUN, 0));
    }

    public Dolphin(String name) {
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
            if (achievement.getPurpose() == HAVE_FUN) icq += 4242 + achievement.getProfit() * 4242;
        }
        return icq;
    }
}
