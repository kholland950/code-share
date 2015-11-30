package controllers;

import models.DAOFactory;
import models.Snippet;
import models.SnippetDAO;
import play.mvc.Controller;
import play.mvc.Result;
import util.ViewUtil;
import views.html.explore;

import java.util.List;

/**
 * @author kevinholland
 */
public class Explore extends Controller {
    public Result explore(String language) {
        SnippetDAO snippetDAO = DAOFactory.getSnippetDAO();
        List<Snippet> snippets = snippetDAO.findByLanguage(language);
        return ok(ViewUtil.applyToStructure(explore.apply(language, snippets)));
    }
}
