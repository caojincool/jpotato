package com.dp.company.dao;

import com.dp.company.domain.Article;
import com.dp.company.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

/**
 * 文章持久对象
 * Created by dpyang on 2014/10/7.
 */
@Repository
public class ArticleDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String INSERT="INSERT INTO t_article(_id,_name,_nameEn,_content,_contentEn," +
            "_keyWord,_keyWordEn,_description,_descriptionEn,_isEnabled,_isFirst,_createDate," +
            "_updateDate,_categoryID,_gurl,_zpic,_homePic)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    private final String UPDATE="UPDATE t_article SET _name=?,_nameEn=?,_content=?,_contentEn=?,"+
            "_keyWord=?,_keyWordEn=?,_description=?,_descriptionEn=?,_isEnabled=?,_isFirst=?,"+
            "_updateDate=?,_categoryID=?,_gurl=?,_zpic=?,_homePic=? WHERE _id=?";

    private final String UPDATEVIEWCOUNT="UPDATE t_article SET _viewCount=_viewCount+1 WHERE _id=?";

    private final String DELETE="DELETE FROM t_article where _id=?";

    private final String GET="SELECT * FROM t_article WHERE _id=?";

    private final String QUERYBYCATEGORY="SELECT * FROM t_article WHERE _categoryId=?";

    public void insert(final Article article){

        //初始化
        Date date=new Date(new java.util.Date().getTime());

        article.setCreateDate(date);
        article.setUpdateDate(date);

        jdbcTemplate.update(INSERT,new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1,article.getId()==null?"":article.getId().toString());
                ps.setString(2,article.getName());
                ps.setString(3,article.getNameEn());
                ps.setString(4,article.getContent());
                ps.setString(5,article.getContentEn());
                ps.setString(6,article.getKeyWord());
                ps.setString(7,article.getKeywordEn());
                ps.setString(8,article.getDescription());
                ps.setString(9,article.getDescriptionEn());
                ps.setBoolean(10, article.isEnabled());
                ps.setBoolean(11,article.isFirst());
                ps.setDate(12,article.getCreateDate());
                ps.setDate(13,article.getUpdateDate());
                ps.setString(14,article.getCategoryID().toString());
                ps.setString(15,article.getGurl());
                ps.setString(16,article.getZpic());
                ps.setString(17,article.getHomePic());
            }
        });
    }

    public void update(final Article article){

        //初始化
        Date date=new Date(new java.util.Date().getTime());


        article.setUpdateDate(date);

        jdbcTemplate.update(UPDATE,new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1,article.getName());
                ps.setString(2,article.getNameEn());
                ps.setString(3,article.getContent());
                ps.setString(4,article.getContentEn());
                ps.setString(5,article.getKeyWord());
                ps.setString(6,article.getKeywordEn());
                ps.setString(7,article.getDescription());
                ps.setString(8,article.getDescriptionEn());
                ps.setBoolean(9, article.isEnabled());
                ps.setBoolean(10,article.isFirst());
                ps.setDate(11,article.getUpdateDate());
                ps.setString(12,article.getCategoryID().toString());
                ps.setString(13,article.getGurl());
                ps.setString(14,article.getZpic());
                ps.setString(15,article.getHomePic());
                ps.setString(16,article.getId()==null?"":article.getId().toString());
            }
        });
    }

    public void updateViewCount(final String id){
          jdbcTemplate.update(UPDATEVIEWCOUNT,new Object[]{id});
    }

    public void delete(final String id){
        jdbcTemplate.update(DELETE,new Object[]{id});
    }


    public Article get(final String id){
        final Article article=new Article();
         jdbcTemplate.query(GET,new Object[]{id},new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                article.setId(UUID.fromString(id));
                article.setName(resultSet.getString("_name"));
                article.setNameEn(resultSet.getString("_nameEn"));
                article.setContent(resultSet.getString("_content"));
                article.setContentEn(resultSet.getString("_contentEn"));
                article.setKeyWord(resultSet.getString("_keyWord"));
                article.setKeywordEn(resultSet.getString("_keyWordEn"));
                article.setDescription(resultSet.getString("_description"));
                article.setDescriptionEn(resultSet.getString("_descriptionEn"));
                article.setEnabled(resultSet.getBoolean("_isEnabled"));
                article.setFirst(resultSet.getBoolean("_isFirst"));
                article.setCreateDate(resultSet.getDate("_createDate"));
                article.setUpdateDate(resultSet.getDate("_updateDate"));
                article.setCategoryID(UUID.fromString(resultSet.getString("_categoryID")));
                article.setGurl(resultSet.getString("_gurl"));
                article.setZpic(resultSet.getString("_zpic"));
                article.setHomePic(resultSet.getString("_homePic"));
            }
        });
        return article;
    }

    public List<Article> queryByCategory(final String categoryId){
        return jdbcTemplate.query(QUERYBYCATEGORY,new Object[]{categoryId},new RowMapper<Article>() {
            @Override
            public Article mapRow(ResultSet resultSet, int i) throws SQLException {
                Article article=new Article();
                article.setId(UUID.fromString(resultSet.getString("_id")));
                article.setName(resultSet.getString("_name"));
                article.setNameEn(resultSet.getString("_nameEn"));
                article.setContent(resultSet.getString("_content"));
                article.setContentEn(resultSet.getString("_contentEn"));
                article.setKeyWord(resultSet.getString("_keyWord"));
                article.setKeywordEn(resultSet.getString("_keyWordEn"));
                article.setDescription(resultSet.getString("_description"));
                article.setDescriptionEn(resultSet.getString("_descriptionEn"));
                article.setEnabled(resultSet.getBoolean("_isEnabled"));
                article.setFirst(resultSet.getBoolean("_isFirst"));
                article.setCreateDate(resultSet.getDate("_createDate"));
                article.setUpdateDate(resultSet.getDate("_updateDate"));
                article.setCategoryID(UUID.fromString(resultSet.getString(categoryId)));
                article.setGurl(resultSet.getString("_gurl"));
                article.setZpic(resultSet.getString("_zpic"));
                article.setHomePic(resultSet.getString("_homePic"));
                return article;
            }
        });
    }
}