package models;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Data Access Object class for Snippets
 */
public class SnippetDAO extends DAO {
    public SnippetDAO(DataSource dataSource) {
        super(dataSource);
    }

    public void insert(Snippet snippet) {
        final String sql = "INSERT INTO snippet " +
                "(title, language, code) " +
                "VALUES (?, ?, ?);";

        jdbcTemplate.update(sql, snippet.title, snippet.language, snippet.code);
    }

    public Snippet findById(Long id) {
        final String sql = "SELECT * FROM snippet " +
                "WHERE id=?";
        Map<String, Object> row = jdbcTemplate.queryForMap(sql, id);
        Snippet snippet = new Snippet();
        snippet.id = id;
        snippet.title = (String)row.get("title");
        snippet.language = (String)row.get("language");
        snippet.code = (String)row.get("code");

        return snippet;
    }

    public List<Snippet> findWithLikeTitle(String title) {
        title = String.format("%%%s%%", title);
        final String sql = "SELECT * FROM snippet WHERE title LIKE ? " +
                "ORDER by date_added DESC LIMIT 50";

        return queryForSnippets(sql, title);
    }

    public List<Snippet> findByLanguage(String language) {
        final String sql = "SELECT * FROM snippet WHERE language=? " +
                "ORDER by date_added DESC LIMIT 50";

        return queryForSnippets(sql, language);
    }

    public List<Snippet> findAll() {
        return null;
    }

    public List<Snippet> findRecent() {
        final String sql = "SELECT * FROM snippet " +
                "ORDER BY date_added DESC LIMIT 50";

        return queryForSnippets(sql);
    }

    private List<Snippet> queryForSnippets(String sql, Object... sqlArgs) {
        List<Snippet> snippets = new ArrayList<>();
        List<Map<String, Object>> rows =  jdbcTemplate.queryForList(sql, sqlArgs);
        for (Map row : rows) {
            Snippet snippet  = new Snippet();
            snippet.id = (Long)row.get("id");
            snippet.title = (String)row.get("title");
            snippet.language = (String)row.get("language");
            snippet.code = (String)row.get("code");
            snippet.dateAdded = (Date)row.get("date_added");
            snippets.add(snippet);
        }

        return snippets;
    }

}
