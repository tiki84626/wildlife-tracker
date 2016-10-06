import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import org.sql2o.*;
import java.sql.Date;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      model.put("hairsalons", HairSalon.all());
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("stylists", Stylist.all());
      model.put("template", "templates/stylists.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/clients", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("clients", Client.all());
      model.put("template", "templates/clients.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      String location = request.queryParams("location");
      String website = request.queryParams("website");
      String description = request.queryParams("description");
      HairSalon hairsalon = new HairSalon(name, location, website, description);
      hairsalon.save();
      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/hairsalons/:hairsalonid/stylists", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int hairsalonid = Integer.parseInt(request.params(":hairsalonid"));
      String name = request.queryParams("name");
      String email = request.queryParams("email");
      String phonenumber = request.queryParams("phonenumber");
      Stylist stylist = new Stylist(name, email, phonenumber, hairsalonid);
      stylist.save();
      response.redirect("/hairsalons/" + hairsalonid);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/hairsalons/:hairsalonid/stylists/:stylistid/clients", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int hairsalonid = Integer.parseInt(request.params(":hairsalonid"));
      int stylistid = Integer.parseInt(request.params(":stylistid"));
      String name = request.queryParams("name");
      String email = request.queryParams("email");
      String phonenumber = request.queryParams("phonenumber");
      String appointment = request.queryParams("appointment");
      Date appointmentdate = Date.valueOf(appointment);
      Client client = new Client(name, email, phonenumber, appointmentdate, stylistid);
      client.save();
      response.redirect("/hairsalons/" + hairsalonid + "/stylists/" + stylistid);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/hairsalons/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/hairsalon-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/hairsalons/:hairsalonid", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      HairSalon hairsalon = HairSalon.find(Integer.parseInt(request.params(":hairsalonid")));
      model.put("hairsalon", hairsalon);
      model.put("stylists", hairsalon.getStylists());
      model.put("template", "templates/hairsalon.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/hairsalons/:hairsalonid/stylists/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      HairSalon hairsalon = HairSalon.find(Integer.parseInt(request.params(":hairsalonid")));
      model.put("hairsalon", hairsalon);
      model.put("stylists", hairsalon.getStylists());
      model.put("template", "templates/stylist-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/hairsalons/:hairsalonid/stylists/:stylistid", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      HairSalon hairsalon = HairSalon.find(Integer.parseInt(request.params(":hairsalonid")));
      int hairsalonid = Integer.parseInt(request.params(":hairsalonid"));
      int stylistid = Integer.parseInt(request.params(":stylistid"));
      Stylist stylist = Stylist.find(stylistid);
      model.put("hairsalon", hairsalon);
      model.put("stylist", stylist);
      model.put("hairsalonid", hairsalonid);
      model.put("stylistid", stylistid);
      model.put("clients", stylist.getClients());
      model.put("template", "templates/stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/hairsalons/:hairsalonid/stylists/:stylistid/clients/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":stylistid")));
      HairSalon hairsalon = HairSalon.find(Integer.parseInt(request.params(":hairsalonid")));
      model.put("hairsalon", hairsalon);
      model.put("stylist", stylist);
      model.put("clients", stylist.getClients());
      model.put("template", "templates/client-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/hairsalons/:hairsalonid/stylists/:stylistid/clients/:clientid", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      HairSalon hairsalon = HairSalon.find(Integer.parseInt(request.params(":hairsalonid")));
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":stylistid")));
      Client client = Client.find(Integer.parseInt(request.params(":clientid")));
      model.put("hairsalon", hairsalon);
      model.put("stylist", stylist);
      model.put("client", client);
      model.put("template", "templates/client.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


  }
}
