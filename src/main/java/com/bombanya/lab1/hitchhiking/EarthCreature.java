package com.bombanya.lab1.hitchhiking;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public abstract class EarthCreature {

    private final String name;

    public abstract List<Achievement> getAchievements();

    public abstract long countCreatureIcq(EarthCreature creature);

    public boolean smarterThan(EarthCreature creature){
        return countCreatureIcq(this) > countCreatureIcq(creature);
    }
}
