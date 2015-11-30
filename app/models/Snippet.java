package models;

import org.ocpsoft.pretty.time.PrettyTime;
import org.springframework.jdbc.core.JdbcTemplate;
import play.data.validation.ValidationError;
import play.db.DB;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Data model for a code snippet
 */
public class Snippet {
    public Long id;
    public String title;
    public String language;
    public String code;
    public Date dateAdded;

    public Snippet() {
        title = "";
        language = "";
        code = "";
    }

    public List<ValidationError> validate() {
        List<ValidationError> errors = new ArrayList<>();

        if (title == null || title.isEmpty()) {
            System.out.println("no title");
            errors.add(new ValidationError("title", "No title given"));
        }
        if (language == null || language.isEmpty()) {
            System.out.println("no lang");
            errors.add(new ValidationError("language", "No language selected"));
        }
        if (code == null || code.isEmpty()) {
            System.out.println("no code");
            errors.add(new ValidationError("code", "Empty snippet"));
        }

        if (errors.size() == 0) {
            return null;
        } else {
            return errors;
        }
    }

    public String getPrettyTime() {
        PrettyTime p = new PrettyTime();
        return p.format(dateAdded);
    }
}
