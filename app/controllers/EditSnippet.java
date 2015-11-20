package controllers;

import models.DAOFactory;
import models.Snippet;
import models.SnippetDAO;
import play.data.Form;
import play.mvc.Result;
import play.mvc.Controller;
import util.ViewUtil;
import views.html.structure;
import views.html.viewSnippet;

import static util.ViewUtil.applyToStructure;

/**
 * Create/Edit Snippet Page controller
 * @author kevinholland
 */
public class EditSnippet extends Controller {
    private Form<Snippet> snippetForm = Form.form(Snippet.class);

    public Result editSnippet(Long snippetId) {
        return ok(views.html.structure.apply(views.html.newcode.apply(snippetForm)));
    }

    public Result newSnippet() {
        return ok(views.html.structure.apply(views.html.newcode.apply(snippetForm)));
    }

    public Result save() {
        // Get the submitted form data from the request object, and run validation.
        Form<Snippet> boundForm = snippetForm.bindFromRequest();
        if (boundForm.hasErrors()) {
            flash("error", "correct errors");
            return ok();
        }

        Snippet snippet = boundForm.get();
        System.out.println("Got snippet");
        System.out.println(snippet.title);
        System.out.println("adding to db");

        //put snippet in database
        SnippetDAO snippetDAO = DAOFactory.getSnippetDAO();
        snippetDAO.insert(snippet);

        return ok(applyToStructure(viewSnippet.apply(snippet)));
    }
}
