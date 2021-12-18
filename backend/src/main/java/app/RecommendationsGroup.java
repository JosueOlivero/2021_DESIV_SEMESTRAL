package app;

import java.util.ArrayList;
import java.util.List;

public class RecommendationsGroup {
    List<Recommendation> recommendations = new ArrayList<>();

    public void addRecommendation(Recommendation recommendation) {
        recommendations.add(recommendation);
    }

    public List<Recommendation> getGroup() {
        return recommendations;
    }

    public int getCount() {
        return recommendations.size();
    }
}
