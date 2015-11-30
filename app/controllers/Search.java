package controllers;

import models.DAOFactory;
import models.Snippet;
import models.SnippetDAO;
import play.mvc.Result;
import play.mvc.Controller;
import play.twirl.api.Html;
import util.ViewUtil;
import views.html.snippetList;

import java.util.List;

/**
 * @author kevinholland
 */
public class Search extends Controller {
    public Result doSearch(String term) {
        SnippetDAO snippetDAO = DAOFactory.getSnippetDAO();
        List<Snippet> snippets = snippetDAO.findWithLikeTitle(term);

        String html = String.format("<h1>search: %s</h1>", term);
        return ok(ViewUtil.applyToStructure(snippetList.apply(snippets, new Html(html))));
    }
}
