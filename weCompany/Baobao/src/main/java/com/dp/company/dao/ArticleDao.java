package com.dp.company.dao;

import com.dp.company.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.util.UUID;

/**
 * 文章持久对象
 * Created by dpyang on 2014/10/7.
 */
@Repository
public class ArticleDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String INSERT="INSERT INTO t_article(id,name,nameEn,content,contentEn,keyWord," +
            "keyWordEn,description,descriptionEn,isEnabled,isFirst,viewCount,createDate," +
            "updateDate,categoryID)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    public void insertArticle(final Article article){

        //初始化
        Date date=new Date(new java.util.Date().getTime());

        article.setCreateDate(date);
        article.setUpdateDate(date);
        article.setViewCount(0);

        jdbcTemplate.update(INSERT,new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1,article.getId().toString());
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
                ps.setInt(12,article.getViewCount());
                ps.setDate(13,article.getCreateDate());
                ps.setDate(14,article.getUpdateDate());
                ps.setString(15,article.getCategoryID().toString());
            }
        });
    }
}
