package models;

import play.db.DB;

/**
 * Factory with static methods for creating DAOs
 */
public class DAOFactory {
    public static SnippetDAO getSnippetDAO() {
        return new SnippetDAO(DB.getDataSource());
    }
}
