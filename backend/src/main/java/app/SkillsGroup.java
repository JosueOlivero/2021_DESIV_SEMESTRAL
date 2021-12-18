package app;

import java.util.ArrayList;
import java.util.List;

public class SkillsGroup {
    List<Skill> skills = new ArrayList<>();

    public void addSkill(Skill skill) {
        skills.add(skill);
    }

    public List<Skill> getGroup() {
        return skills;
    }

    public int getCount() {
        return skills.size();
    }
}
