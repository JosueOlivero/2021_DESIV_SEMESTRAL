package app;

import java.util.ArrayList;
import java.util.List;

public class ExperiencesGroup {
    List<Experience> experiences = new ArrayList<>();

    public void addExperience(Experience experience) {
        experiences.add(experience);
    }

    public List<Experience> getGroup() {
        return experiences;
    }

    public int getCount() {
        return experiences.size();
    }
}
