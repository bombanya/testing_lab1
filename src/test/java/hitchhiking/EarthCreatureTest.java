package hitchhiking;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EarthCreatureTest {

    private final Dolphin dolphin = new Dolphin("dolphin");
    private final Human human = new Human("vasya");

    @Test
    void testDolphinAchievements(){
        assertNotNull(dolphin.getAchievements());
        assertTrue(dolphin.getAchievements().stream().anyMatch(a -> a.getName().equals("splashing in the water")));
        assertTrue(dolphin.getAchievements().stream().anyMatch(a -> a.getName().equals("play")));
    }

    @Test
    void testHumanAchievements(){
        assertNotNull(human.getAchievements());
        assertTrue(human.getAchievements().stream().anyMatch(a -> a.getName().equals("wheel")));
        assertTrue(human.getAchievements().stream().anyMatch(a -> a.getName().equals("New York")));
        assertTrue(human.getAchievements().stream().anyMatch(a -> a.getName().equals("war")));
    }

    @Test
    void testManHadAlwaysAssumedThatHeWasMoreIntelligentThanDolphins(){
        assertTrue(human.smarterThan(dolphin));
    }

    @Test
    void testTheDolphinHadAlwaysBelievedThatTheyWereFarMoreIntelligentThanMan(){
        assertTrue(dolphin.smarterThan(human));
    }
}