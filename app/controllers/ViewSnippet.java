package controllers;

import models.DAOFactory;
import models.Snippet;
import play.mvc.Result;
import play.mvc.Controller;
import views.html.viewSnippet;

import static util.ViewUtil.applyToStructure;

/**
 * Created by kevinholland on 11/15/15.
 */
public class ViewSnippet extends Controller {
    public Result view(Long id) {
        Snippet snippet = DAOFactory.getSnippetDAO().findById(id);

        return ok(applyToStructure(viewSnippet.apply(snippet)));
    }
}
