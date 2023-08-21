package cz.cvut.fel.omo.SmartHome.alive;

import cz.cvut.fel.omo.SmartHome.util.RandomUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Class for generating names of the people in the house
 */
public class NameGenerator {
    private static final List<String> names = new ArrayList<>(Arrays.asList("Liam", "Noah", "Oliver", "William", "James", "Benjamin", "Lucas", "Henry", "Alexander", "Olivia",
            "Emma", "Ava", "Charlotte", "Sophia", "Amelia", "Isabella", "Mia", "Evelyn", "Harper"));

    /**
     * Returns a random name from a string list.
     * @return String name of person
     */
    public static String getRandomName(){
        Collections.shuffle(names);
        String name = names.get(RandomUtil.getRandomIntInRange(0, names.size() - 1));
        names.remove(name);
        return name;
    }
}
