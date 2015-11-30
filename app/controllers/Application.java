package controllers;

import models.DAOFactory;
import play.mvc.Controller;
import play.mvc.Result;
import play.twirl.api.Html;
import util.ViewUtil;
import views.html.about;
import views.html.index;
import views.html.structure;

import static util.ViewUtil.applyToStructure;

public class Application extends Controller {

    public Result index() {
        Html content = index.apply(DAOFactory.getSnippetDAO().findRecent());
        return ok(applyToStructure(content));
    }

    public Result about() {
        return ok(applyToStructure(about.apply()));
    }
}
