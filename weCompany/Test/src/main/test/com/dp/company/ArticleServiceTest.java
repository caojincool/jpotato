package com.dp.company;

import com.dp.company.domain.Article;
import com.dp.company.service.ArticleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * Created by dpyang on 2014/11/12.
 */
public class ArticleServiceTest extends SuperTest {

    @Autowired
    private ArticleService articleService;

    @Test
    public void testAddArticle1(){
        Article article=new Article();
        article.setName("佛山举办比基尼推手大赛 俊男美女\"肉搏\"(图)");
        article.setNameEn("foshanjubanbijinituishoudasaijunnanmeinv\"rbbo\"(tu)");
        article.setContent("北京时间11月11日，在全民欢度“双11”节日的时候，广东省佛山市正在庆祝它们获得被国家体育总局武术运动管理中心授予“中国武术之城”称号十周年。佛山市武术协会举办了一系列庆祝活动，“11.11光棍节比基尼”推手大赛正是此次纪念活动之一。\n");
        article.setContentEn("北京时间11月11日，在全民欢度“双11”节日的时候，广东省佛山市正在庆祝它们获得被国家体育总局武术运动管理中心授予“中国武术之城”称号十周年。佛山市武术协会举办了一系列庆祝活动，“11.11光棍节比基尼”推手大赛正是此次纪念活动之一。\n");
        article.setDescription("广东省第十届运动会太极拳冠军、佛山市武术协会副秘书长黄伟忠介绍，今年“双十一”晚上18：00～21：00在佛山市禅城区普君新城设立比基尼推手擂台PK赛，来自高等院校、白领青年和未婚女青年参加了这场中泰武术高手的挑战。此外，还有来自泰国的拳击选手也报名参加“11.11光棍节比基尼”佛山推手大赛，与中国年轻人一道庆祝武术名城十周年纪念活动。\n" +
                "\n" +
                "据了解，不分男女老幼，只要是对推手功夫感兴趣，均可报名参加。参赛选手穿着比基尼进行一对一PK赛，在教练的指导下运用推手功夫制胜对手，最终胜利者将获得由大会提供的2000元奖金。\n");
        article.setDescriptionEn("广东省第十届运动会太极拳冠军、佛山市武术协会副秘书长黄伟忠介绍，今年“双十一”晚上18：00～21：00在佛山市禅城区普君新城设立比基尼推手擂台PK赛，来自高等院校、白领青年和未婚女青年参加了这场中泰武术高手的挑战。此外，还有来自泰国的拳击选手也报名参加“11.11光棍节比基尼”佛山推手大赛，与中国年轻人一道庆祝武术名城十周年纪念活动。\n" +
                "\n" +
                "据了解，不分男女老幼，只要是对推手功夫感兴趣，均可报名参加。参赛选手穿着比基尼进行一对一PK赛，在教练的指导下运用推手功夫制胜对手，最终胜利者将获得由大会提供的2000元奖金。\n");
        article.setKeyWord("佛山,比基尼,推手大赛");
        article.setKeywordEn("foshan,bijini,tuishoudasai");
        article.setEnabled(true);
        article.setCategoryID(UUID.fromString("6016ceda-6982-11e4-9e75-003067974107"));

        articleService.addArticle(article);
        getLog().info("新增成功！");
    }

    @Test
    public void testAddArticle2(){
        Article article=new Article();
        article.setName("中方回应日本外相钓鱼岛言论 敦促日方谨言慎行");
        article.setNameEn("foshanjubanbijinituishoudasaijunnanmeinv\"rbbo\"(tu)");
        article.setContent("中新网11月12日电 据中国驻日本大使馆网站消息，中国驻日使馆发言人12日回应日本外相岸田文雄此前关于钓鱼岛言论，再次强调钓鱼岛是中国固有领土，中方维护国家领土主权的决心和意志坚定不移，敦促日方在钓鱼岛问题上谨言慎行，停止一切损害中国领土主权的行为。\n" +
                "\n" +
                "据日本媒体报道，11月11日上午，日本外相岸田文雄在记者会上针对中日四点原则共识中涉及钓鱼岛内容应询称，日本政府在钓鱼岛问题上关于不存在领土问题的立场没有变化，并列举中方划设东海防空识别区和东海资源开发问题称东海处于紧张状态，日中双方主张不同。\n" +
                "\n" +
                " \n" +
                "\n" +
                " \n" +
                "\n" +
                "对此，中国驻日本使馆发言人表示，对日方有关言论表示严重关切和不满。钓鱼岛是中国固有领土。中日双方日前发表的四点原则共识的含义和精神是清楚的。近年来日方无视中方在钓鱼岛问题上的立场，执意采取单方面挑衅行动，是导致当前钓鱼岛紧张局势的根源。\n" +
                "\n" +
                "发言人称，中方维护国家领土主权的决心和意志坚定不移，同时始终致力于通过对话磋商管控和解决钓鱼岛问题。我们敦促日方正视历史和事实，信守承诺，按照原则共识精神同中方相向而行，在钓鱼岛问题上谨言慎行，停止一切损害中国领土主权的行为。\n");
        article.setContentEn("北京时间11月11日，在全民欢度“双11”节日的时候，广东省佛山市正在庆祝它们获得被国家体育总局武术运动管理中心授予“中国武术之城”称号十周年。佛山市武术协会举办了一系列庆祝活动，“11.11光棍节比基尼”推手大赛正是此次纪念活动之一。\n");
        article.setDescription("广东省第十届运动会太极拳冠军、佛山市武术协会副秘书长黄伟忠介绍，今年“双十一”晚上18：00～21：00在佛山市禅城区普君新城设立比基尼推手擂台PK赛，来自高等院校、白领青年和未婚女青年参加了这场中泰武术高手的挑战。此外，还有来自泰国的拳击选手也报名参加“11.11光棍节比基尼”佛山推手大赛，与中国年轻人一道庆祝武术名城十周年纪念活动。\n" +
                "\n" +
                "据了解，不分男女老幼，只要是对推手功夫感兴趣，均可报名参加。参赛选手穿着比基尼进行一对一PK赛，在教练的指导下运用推手功夫制胜对手，最终胜利者将获得由大会提供的2000元奖金。\n");
        article.setDescriptionEn("广东省第十届运动会太极拳冠军、佛山市武术协会副秘书长黄伟忠介绍，今年“双十一”晚上18：00～21：00在佛山市禅城区普君新城设立比基尼推手擂台PK赛，来自高等院校、白领青年和未婚女青年参加了这场中泰武术高手的挑战。此外，还有来自泰国的拳击选手也报名参加“11.11光棍节比基尼”佛山推手大赛，与中国年轻人一道庆祝武术名城十周年纪念活动。\n" +
                "\n" +
                "据了解，不分男女老幼，只要是对推手功夫感兴趣，均可报名参加。参赛选手穿着比基尼进行一对一PK赛，在教练的指导下运用推手功夫制胜对手，最终胜利者将获得由大会提供的2000元奖金。\n");
        article.setKeyWord("佛山,比基尼,推手大赛");
        article.setKeywordEn("foshan,bijini,tuishoudasai");
        article.setEnabled(true);
        article.setCategoryID(UUID.fromString("6016ceda-6982-11e4-9e75-003067974107"));

        articleService.addArticle(article);
        getLog().info("新增成功！");
    }

    @Test
    public void testUpdate(){
        Article article=articleService.getArticle(UUID.fromString("3139fb91-6a7a-11e4-acbe-ce6db74f650d"));
        article.setViewCount(1);
        articleService.editArticle(article);
        getLog().info("广告宣传成功！");
    }

    @Test
    public void testUpdateViewCount(){

        Article article=articleService.getArticle(UUID.fromString("3139fb91-6a7a-11e4-acbe-ce6db74f650d"));
        getLog().info("新闻"+article.getName()+"点击率"+article.getViewCount());
    }
}
