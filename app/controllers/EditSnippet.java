package controllers;

import models.DAOFactory;
import models.Snippet;
import models.SnippetDAO;
import play.data.Form;
import play.mvc.Result;
import play.mvc.Controller;
import views.html.newcode;
import views.html.snippetForm;
import views.html.viewSnippet;

import static util.ViewUtil.applyToStructure;

/**
 * Create/Edit Snippet Page controller
 * @author kevinholland
 */
public class EditSnippet extends Controller {
    private Form<Snippet> form = Form.form(Snippet.class);
    private Snippet snippet = new Snippet();

    public Result forkSnippet(Long snippetId) {
        SnippetDAO snippetDAO = DAOFactory.getSnippetDAO();
        Snippet snippet = snippetDAO.findById(snippetId);
        return ok(views.html.structure.apply(views.html.newcode.apply(snippet)));
    }

    public Result newSnippet() {
        return ok(views.html.structure.apply(views.html.newcode.apply(new Snippet())));
    }

    public Result save() {
        // Get the submitted form data from the request object, and run validation.
        Form<Snippet> boundForm = form.bindFromRequest();
        if (boundForm.hasErrors()) {
            return ok(applyToStructure(newcode.apply(snippet)));
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
