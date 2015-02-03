package com.dp.test;

import com.dp.baobao.domain.*;
import com.dp.baobao.domain.query.ArticleQuery;
import com.dp.baobao.domain.query.CategoryQuery;
import com.dp.baobao.domain.query.CompanyQuery;
import com.dp.baobao.domain.query.ForumQuery;
import com.dp.baobao.service.IBaobaoService;
import com.sun.javafx.tk.quantum.QuantumRenderer;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sun.misc.Cache;

import java.util.List;

import static org.junit.Assert.*;

public class BaobaoServiceTest extends SuperTest {

    @Autowired
    private IBaobaoService iBaobaoService;

    private IQuery companyQuery;

    private ForumQuery forumQuery;

    private CategoryQuery categoryQuery;

    private ArticleQuery articleQuery;

    @Before
    public void before(){
        companyQuery =new CompanyQuery("6d0972b4-a2da-11e4-9d68-8d15c28d7bc0");
        forumQuery =new ForumQuery("001");

        categoryQuery=new CategoryQuery();
        categoryQuery.setCode("002");

        articleQuery=new ArticleQuery();
        articleQuery.setCode("001");
    }

    @Test
    public void testEditCompany() throws Exception {
        Company company=new Company("","002","洋芋网","洋芋博客");

        iBaobaoService.editCompany(company);
    }

    @Test
    public void testUpdate(){
        Company company=iBaobaoService.getCompany(companyQuery);
        company.setRemark("test");
        iBaobaoService.editCompany(company);
    }

    @Test
    public void testEditForum(){
        Forum forum=new Forum();
        Company company=iBaobaoService.getCompany(companyQuery);
        forum.setCode("004");
        forum.setName("周4");
        forum.setNameEn("absj");
        forum.setEnabled(true);
        forum.setCompany(company);

        Forum eForum=iBaobaoService.getForum(forumQuery);
        eForum.setCode("001");
        iBaobaoService.editForum(eForum);
    }

    @Test
    public void testGetForum(){
        Forum forum=iBaobaoService.getForum(forumQuery);

        assertNotNull(forum);
    }

    @Test
    public void testLoadForums(){
        List<Forum> forums=iBaobaoService.loadForums();

        assertEquals(1,forums.size());
    }

    @Test
    public void testLoadCampanies() throws Exception {
        List<Company> companies=iBaobaoService.loadCampanies();

        assertEquals(3,companies.size());
    }

    @Test
    public void testGetCompany() throws Exception {
        Company company=iBaobaoService.getCompany(companyQuery);

        assertNotNull(company);
    }

    @Test
    public void testLoadForumsByCompanyId() throws Exception {
        List<Forum> forums=iBaobaoService.loadForumsByCompanyId(companyQuery);

        assertEquals(1,forums.size());
    }

    @Test
    public void testEditCategory() throws Exception {
        Forum forum=iBaobaoService.getForum(forumQuery);
        Category category=new Category();
        category.setForum(forum);
        category.setCode("003");
        category.setName("企业文化");
        category.setNameEn("qiyedstai");
        category.setCategoryType(Category.CategoryType.ARTICLELIST);
        category.setDescription("企业文化");
        category.setEnabled(true);
        category.setKeyWord("林木森|最新闻");
        category.setDescriptionEn("haha");

        categoryQuery=new CategoryQuery();
        categoryQuery.setCode("002");

        Category category1=iBaobaoService.getCategory(categoryQuery);
        category1.setKeyWord("嘻嘻哈哈");

        iBaobaoService.editCategory(category1);
    }

    @Test
    public void testGetCategory() throws Exception {


        Category category=iBaobaoService.getCategory(categoryQuery);

        assertNotNull(category);
    }

    @Test
    public void testLoadCategories() throws Exception {
        List<Category> categories=iBaobaoService.loadCategories();

        assertEquals(3,categories.size());
    }

    @Test
    public void testLoadCategoriesByForumId() throws Exception {

    }

    @Test
    public void testEditArticle() throws Exception {
        Category category=iBaobaoService.getCategory(categoryQuery);

        Article article=new Article();
        article.setCode("006");
        article.setName("热烈庆祝西山煤电集团配件信息化管理系统验收通过");
        article.setKeyWord("mysql|limit|用法");
        article.setKeyWordEn("mysql|limit|嘻哈");
        article.setNameEn("Mysqlasonglimitdeysfaxtjz");
        article.setEnabled(true);
        article.setDescription("Mysql中limit的用法：在我们使用查询语句的时候，经常要返回前几条或者中间某几行数据，这个时候怎么办呢？不用担心，mysql已经为我们提供了这样一个功能。");
        article.setDescriptionEn("mysql as limit de ys fa");
        article.setCategory(category);
        article.setFirst(false);
        article.setContent("Mysql中limit的用法：在我们使用查询语句的时候，经常要返回前几条或者中间某几行数据，这个时候怎么办呢？不用担心，mysql已经为我们提供了这样一个功能。\n" +
                "\n" +
                "SELECT * FROM table   LIMIT [offset,] rows | rows OFFSET offset\n" +
                "\n" +
                "LIMIT 子句可以被用于强制 SELECT 语句返回指定的记录数。LIMIT 接受一个或两个数字参数。参数必须是一个整数常量。如果给定两个参数，第一个参数指定第一个返回记录行的偏移量，第二个参数指定返回记录行的最大数目。初始记录行的偏移量是 0(而不是 1)： 为了与 PostgreSQL 兼容，MySQL 也支持句法： LIMIT # OFFSET #。\n" +
                "\n" +
                "mysql> SELECT * FROM table LIMIT 5,10; // 检索记录行 6-15\n" +
                "\n" +
                "//为了检索从某一个偏移量到记录集的结束所有的记录行，可以指定第二个参数为 -1： \n" +
                "mysql> SELECT * FROM table LIMIT 95,-1; // 检索记录行 96-last.\n" +
                "\n" +
                "//如果只给定一个参数，它表示返回最大的记录行数目： \n" +
                "mysql> SELECT * FROM table LIMIT 5;     //检索前 5 个记录行\n" +
                "\n" +
                "//换句话说，LIMIT n 等价于 LIMIT 0,n。\n" +
                "\n" +
                "注意limit 10和limit 9，1的不同：");
        article.setNameEn("mq va hk vo de");

        iBaobaoService.editArticle(article);

        Article article1=iBaobaoService.getArticle(articleQuery);
        article.setNameEn("ma va ka");
        //iBaobaoService.editArticle(article1);
    }

    @Test
    public void testGetArticle() throws Exception {
        Article article=iBaobaoService.getArticle(articleQuery);

        assertNotNull(article);
    }

    @Test
    public void testLoadPageArticle() throws Exception {
        Page<Article> articlePage=iBaobaoService.loadPageArticle(articleQuery);

        assertEquals(3,articlePage.getContent().size());
    }

    @Test
    public void testLoadPageArticleByCategoryId() throws Exception {

    }
}