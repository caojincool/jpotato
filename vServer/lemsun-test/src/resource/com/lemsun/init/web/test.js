/**
 * Created with IntelliJ IDEA.
 * User: dp
 * Date: 13-5-27
 * Time: 上午9:15
 * To change this template use File | Settings | File Templates.
 */
/**
 * 获取全部数据
 */
function all() {

    writer.write('<table>');

    //获取查询到的二维的数组数据
    var t = formula.run('C000001223!(A,B,C,D)');

    for (var i = 0; i < t.getRowCount(); i++) {
        var r = t.getData().get(i);
        writer.write('<tr>');
        for (var j = 0; j < t.getColCount(); j++) {
            writer.write('<td>' + r.get(j) + '</td>')
        }
        writer.write('</tr>')
    }

    writer.write('</table>');
}


/**
 * 根据表组件编码,文章编码返回文章内容及标题样式
 */
function showarticle() {

    var tablepid, articleid;
    if (request.getParameter('tbpid') == null)
        tablepid = 'C000001223';     //获取表格组件

    if (request.getParameter('articled') == null)
        articleid = '100';
    else
        articleid = request.getParameter('articleid');

    //获取查询到的二维的数组数据
    var t = formula.run(tablepid + '!(C,D)(A=' + articleid + ')');

    writer.write(t.getData().get(0).get(0));
    writer.write('<br/>');
    writer.write(t.data.get(0).get(1));
}

//显示一片文章
var article = {
    title: '标题',
    content: '内容'
}

function getArticle() {
    var id = 100, tableid = 'C000001223';
    if (request.getParameter('id') != null) {
        id = request.getParameter('id');
    }
    if (request.getParameter('tableid') != null) {
        tableid = request.getParameter('tableid');
    }

    var t = formula.run(tableid + '!(C,D)(A=' + id + ')');

    article.title = t.data.get(0).get(0);
    article.content = t.data.get(0).get(1);
    return;
}


function list() {
    //获取传递过来的栏目名称
    if (request.getParameter('lanmu') != null) {
        lanmu = request.getParameter('lanmu');
    }
    //获取类别名称
    if (request.getParameter('leibie') != null) {
        leibie = request.getParameter('leibie');
    }
    //获取传递的表组件编码
    if (request.getParameter('tableid') != null) {
        tableid = request.getParameter('tableid');
    }


    var t;
    //判断是否传递了类别名称
    if (leibie == '默认类别')
        t = formula.run(tableid + '!(A,C)(B=' + lanmu + ')');
    else
        t = formula.run(tableid + '!(A,C)(B=' + lanmu + ' & E=' + leibie + ')')

    //输出链接和标题
    writer.write('<ul>')
    for (var i = 0, j = 0; i < t.getRowCount(); i++, j++) {
        var row = t.data.get(i);
        writer.write('<li>')
        writer.write("<a href='@{href(123)}?id='+row.get(0)+'>");
        writer.write(row.get(1));
        writer.write("</a>");
        writer.write('</li>')
    }
    writer.write("</ul>")

    return '';
}

