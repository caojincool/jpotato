package com.dp.baobao.dao;

import com.dp.baobao.domain.CategoryType;
import com.dp.baobao.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * Created by dp on 14-11-11.
 */
@Repository
public class CategoryDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insert(final Category category){
        String sql="insert into t_category(_id,_orderId,_name,_nameEn,_content,_contentEn,_keyWord,_keyWordEn,_description,_descriptionEn,_isEnable,_categoryType,_forum_id)" +
                "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

        jdbcTemplate.update(sql,new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, category.getId() == null ? "" : category.getId().toString());
                preparedStatement.setInt(2,category.getOrderId());
                preparedStatement.setString(3,category.getName());
                preparedStatement.setString(4,category.getNameEn());
                preparedStatement.setString(5,category.getContent());
                preparedStatement.setString(6,category.getContentEn());
                preparedStatement.setString(7,category.getKeyWord());
                preparedStatement.setString(8,category.getKeyWordEn());
                preparedStatement.setString(9,category.getDescription());
                preparedStatement.setString(10,category.getDescriptionEn());
                preparedStatement.setBoolean(11, category.isEnabled());
                preparedStatement.setString(12, category.getCategoryType().name());
                preparedStatement.setString(13,category.getForumId().toString());
            }
        });
    }

    public void update(final Category category){
        String sql="update t_category set _orderId=?,_name=?,_nameEn=?,_content=?,_contentEn=?,_keyWord=?,_keyWordEn=?,_description=?" +
                ",_descriptionEn=?,_description=?,_descriptionEn=?,_isEnable=?,_categoryType=?,_forum_id=? where _id=?";

        jdbcTemplate.update(sql,new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1,category.getOrderId());
                preparedStatement.setString(2,category.getName());
                preparedStatement.setString(3,category.getNameEn());
                preparedStatement.setString(4,category.getContent());
                preparedStatement.setString(5,category.getContentEn());
                preparedStatement.setString(6,category.getKeyWord());
                preparedStatement.setString(7,category.getKeyWordEn());
                preparedStatement.setString(8,category.getDescription());
                preparedStatement.setString(9,category.getDescriptionEn());
                preparedStatement.setBoolean(10, category.isEnabled());
                preparedStatement.setString(11, category.getCategoryType().name());
                preparedStatement.setString(12, category.getForumId().toString());
                preparedStatement.setString(13,category.getId().toString());
            }
        });
    }

    public Category get(final UUID id){
        String sql="select * from t_category where _id=?";
        final Category category=new Category();

        jdbcTemplate.query(sql, new Object[]{id}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {

                category.setId(id);
                category.setOrderId(resultSet.getInt("_orderId"));
                category.setName(resultSet.getString("_name"));
                category.setNameEn(resultSet.getString("_nameEn"));
                category.setContent(resultSet.getString("_content"));
                category.setContentEn(resultSet.getString("_contentEn"));
                category.setKeyWord(resultSet.getString("_keyWord"));
                category.setKeyWordEn(resultSet.getString("_keyWordEn"));
                category.setDescription(resultSet.getString("_description"));
                category.setDescriptionEn(resultSet.getString("_descriptionEn"));
                category.setEnabled(resultSet.getBoolean("_isEnable"));
                category.setCategoryType(CategoryType.valueOf(resultSet.getString("_categoryType")));
                category.setForumId(UUID.fromString(resultSet.getString("_forum_id")));
            }
        });
        return category;
    }

    public void delete(final UUID id){
        String sql="delete from t_category where _id=?";

        jdbcTemplate.update(sql,new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1,id.toString());
            }
        });
    }

    public List<Category> queryAll(){
        String sql="select * from t_category";


        return jdbcTemplate.query(sql, new RowMapper<Category>() {

            @Override
            public Category mapRow(ResultSet resultSet, int i) throws SQLException {
                Category category = new Category();
                category.setId(UUID.fromString(resultSet.getString("_id")));
                category.setOrderId(resultSet.getInt("_orderId"));
                category.setName(resultSet.getString("_name"));
                category.setNameEn(resultSet.getString("_nameEn"));
                category.setContent(resultSet.getString("_content"));
                category.setContentEn(resultSet.getString("_contentEn"));
                category.setKeyWord(resultSet.getString("_keyWord"));
                category.setKeyWordEn(resultSet.getString("_keyWordEn"));
                category.setDescription(resultSet.getString("_description"));
                category.setDescriptionEn(resultSet.getString("_descriptionEn"));
                category.setEnabled(resultSet.getBoolean("_isEnable"));
                category.setCategoryType(CategoryType.valueOf(resultSet.getString("_categoryType")));
                category.setForumId(UUID.fromString(resultSet.getString("_forum_id")));

                return category;
            }
        });


    }

    public List<Category> queryByForumId(String forumId){
        String sql="select * from t_category where _forum_id=?";

        return jdbcTemplate.query(sql,new Object[]{forumId},new RowMapper<Category>() {
            @Override
            public Category mapRow(ResultSet resultSet, int i) throws SQLException {
                Category category = new Category();
                category.setId(UUID.fromString(resultSet.getString("_id")));
                category.setOrderId(resultSet.getInt("_orderId"));
                category.setName(resultSet.getString("_name"));
                category.setNameEn(resultSet.getString("_nameEn"));
                category.setContent(resultSet.getString("_content"));
                category.setContentEn(resultSet.getString("_contentEn"));
                category.setKeyWord(resultSet.getString("_keyWord"));
                category.setKeyWordEn(resultSet.getString("_keyWordEn"));
                category.setDescription(resultSet.getString("_description"));
                category.setDescriptionEn(resultSet.getString("_descriptionEn"));
                category.setEnabled(resultSet.getBoolean("_isEnable"));
                category.setCategoryType(CategoryType.valueOf(resultSet.getString("_categoryType")));
                category.setForumId(UUID.fromString(resultSet.getString("_forum_id")));

                return category;
            }
        });

    }

    //todo 分页数据暂时没有实现
    public List<Category> queryAllByPage(){
        return null;
    }
}
