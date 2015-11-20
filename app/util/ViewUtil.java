package util;

import play.twirl.api.Html;
import views.html.structure;

/**
 * Created by kevinholland on 11/15/15.
 */
public class ViewUtil {
    public static Html applyToStructure(Html content) {
        return structure.apply(content);
    }
}
