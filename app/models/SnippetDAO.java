package models;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
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

    public List<Snippet> findAll() {
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<Snippet> findRecent() {
        final String sql = "SELECT * FROM snippet " +
                "ORDER BY date_added DESC LIMIT 30";

        List<Snippet> snippets = new ArrayList<>();
        List<Map<String, Object>> rows =  jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            Snippet snippet  = new Snippet();
            snippet.id = (Long)row.get("id");
            snippet.title = (String)row.get("title");
            snippet.language = (String)row.get("language");
            snippet.code = (String)row.get("code");
            snippets.add(snippet);
        }

        return snippets;
    }

}
