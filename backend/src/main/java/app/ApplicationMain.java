package app;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.options;
import static spark.Spark.post;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.json.JSONObject;

public class ApplicationMain {
  static Logger logger = Logger.getLogger(ApplicationMain.class);

  public static void main(String[] args) {
    BasicConfigurator.configure();
    ExperiencesGroup experiencesGroup = createExperienceGroup();
    SkillsGroup skillsGroup = createSkillGroup();
    RecommendationsGroup recommendationsGroup = createRecommendationGroup();
    // Enable CORS
    enableCors();

    // EXPERIENCIAS

    get("/experiences", (req, res) -> {
      res.type("application/json");
      return new JSONObject(experiencesGroup);
    });

    post("/experiences", (req, res) -> {
      res.type("application/json");
      JSONObject body = new JSONObject(req.body());
      Experience exp = new Experience();
      if (!body.has("title") && !body.has("workPlace") && !body.has("workDescription")) {
        return "Invalid Request";
      }
      exp.setTitle(body.getString("title"));
      exp.setWorkPlace(body.getString("workPlace"));
      exp.setWorkDescription(body.getString("workDescription"));

      experiencesGroup.addExperience(exp);
      return new JSONObject(experiencesGroup);
    });

    // CONOCIMIENTOS

    get("/skills", (req, res) -> {
      res.type("application/json");
      return new JSONObject(skillsGroup);
    });

    post("/skills", (req, res) -> {
      res.type("application/json");
      JSONObject body = new JSONObject(req.body());
      Skill skill = new Skill();
      if (!body.has("name")) {
        return "Invalid Request";
      }
      skill.setName(body.getString("name"));

      skillsGroup.addSkill(skill);
      return new JSONObject(skillsGroup);
    });

    // RECOMENDACIONES
    get("/recommendations", (req, res) -> {
      res.type("application/json");
      return new JSONObject(recommendationsGroup);
    });

    post("/recommendations", (req, res) -> {
      res.type("application/json");
      JSONObject body = new JSONObject(req.body());
      Recommendation recomendation = new Recommendation();
      if (!body.has("name") && !body.has("title") && !body.has("workPlace") && !body.has("recommendation")) {
        return "Invalid Request";
      }
      recomendation.setName(body.getString("name"));
      recomendation.setTitle(body.getString("title"));
      recomendation.setWorkPlace(body.getString("workPlace"));
      recomendation.setRecommendation(body.getString("recommendation"));

      recommendationsGroup.addRecommendation(recomendation);
      return new JSONObject(recommendationsGroup);
    });

  }

  public static void enableCors() {
    options("/*", (request, response) -> {

      String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
      if (accessControlRequestHeaders != null) {
        response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
      }

      String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
      if (accessControlRequestMethod != null) {
        response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
      }

      return "OK";
    });

    before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
  }

  public static ExperiencesGroup createExperienceGroup() {
    ExperiencesGroup group = new ExperiencesGroup();
    Experience experience = new Experience();
    experience.setTitle("Estudiante");
    experience.setWorkPlace("Universidad Tecnológica de Panamá");
    experience.setWorkDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam vitae metus rhoncus, faucibus purus sit amet, aliquet lacus. Nulla massa lacus, gravida vitae tristique eu, varius a dui. Maecenas quis posuere nulla. Etiam faucibus sagittis iaculis. In ultricies leo eget felis fringilla egestas. Fusce vel mattis est. Vestibulum tempus ipsum et ligula fermentum, porta malesuada ligula ornare. Vivamus luctus molestie enim, ullamcorper convallis lacus malesuada vitae. Curabitur vel massa sit amet est pellentesque congue. Donec ac faucibus est. Phasellus vehicula quam ante, et dictum nunc hendrerit quis. Vestibulum pretium cursus tortor, lobortis tempus velit euismod vitae.");

    group.addExperience(experience);
    return group;
  }

  public static SkillsGroup createSkillGroup() {
    SkillsGroup group = new SkillsGroup();
    Skill skill = new Skill();
    skill.setName("Java");

    group.addSkill(skill);
    return group;
  }

  public static RecommendationsGroup createRecommendationGroup() {
    RecommendationsGroup group = new RecommendationsGroup();
    Recommendation recommendation = new Recommendation();
    recommendation.setName("Frank Aguilar");
    recommendation.setTitle("Programador");
    recommendation.setWorkPlace("LeWagon");
    recommendation.setRecommendation("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam vitae metus rhoncus, faucibus purus sit amet, aliquet lacus. Nulla massa lacus, gravida vitae tristique eu, varius a dui. Maecenas quis posuere nulla. Etiam faucibus sagittis iaculis. In ultricies leo eget felis fringilla egestas. Fusce vel mattis est. Vestibulum tempus ipsum et ligula fermentum, porta malesuada ligula ornare. Vivamus luctus molestie enim, ullamcorper convallis lacus malesuada vitae. Curabitur vel massa sit amet est pellentesque congue. Donec ac faucibus est. Phasellus vehicula quam ante, et dictum nunc hendrerit quis. Vestibulum pretium cursus tortor, lobortis tempus velit euismod vitae.");

    group.addRecommendation(recommendation);
    return group;
  }
}