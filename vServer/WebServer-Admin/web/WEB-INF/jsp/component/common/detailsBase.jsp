<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div id="dialog-details" title="请选择...">
    <form>
        <fieldset>
            <table width="100%">
                <tr>
                    <td class="firstColom">实例选择:</td>
                    <td>   <select id="instance" name="instance"  style="width: 170px;" >

                         <c:forEach items="${plateformInstances}" var="instance" >
                             <option value="${instance.address}">${instance.name}</option>
                         </c:forEach>
                    </select>
                    </td>
                </tr>
            </table>
        </fieldset>
    </form>
</div>

<script type="text/javascript">
    var pid='${resource.pid}';
    var category='${fn:toLowerCase(resource.category)}';
    var categorys = ${categorys};
</script>
<script type="text/javascript">
    jQuery( "#dialog-details").hide();
    function show(pid){
        jQuery( "#dialog-details" ).dialog({
            dialogClass: "no-close",
            resizable: false,
            height: 200,
            width: 350,
            modal: true,
            buttons: {
                "确定": function() {
                    var address=jQuery("#instance  option:selected").val();
                    var url = "http://"+address+"/"+ pid.toUpperCase();
                    window.open(url);
                    jQuery( this ).dialog( "close" );
                    jQuery( "#instance").val("");
                },
                "取消": function() {
                    jQuery( "#instance").val("");
                    jQuery( this ).dialog( "close" );
                }
            }
        });
    }
</script>
<div class="one_sixth">
    <span>
        <a href="/help/document/${resource.pid}/effectPicture/view" style=" text-decoration: none;font-size:11px; font-weight:bold;" target="_blank" >
            <img src="/help/document/${resource.pid}/preView?size=1">
        </a>
    </span>
</div>
<div class="five_sixth last">
    <div class="one_fifth">
        <strong>
            组件名: <br>
            组件类型: <br>
            创建人: <br>
            创建日期: <br>
            说明:
        </strong>
    </div><!-- one_half -->

    <div class="two_fifth ">
        ${resource.name} <br>
        ${resource.category} <br>
        ${resource.createUser} <br>
        <fmt:formatDate value='${resource.updateTime}' pattern='yyyy年MM月dd日'/>
        <br>
        ${resource.remark}

    </div><!-- one_half last -->
    <br clear="all"><br>
    <div class="three_fifth last ">
        <a href="/help/document?pid=${resource.pid} " target="_blank">
            <img   src="resource/images/help.png"/>
        </a>
    </div>
</div>
<br clear="all"><br>
<div style="text-align: right;width: 100%;">
    <a title="预览" class="iconlink2"
       href="component/main/defaultPreView/${resource.pid}" target="_blank"><img
            src="resource/images/icons/eye_16x16.png"></a>
    <a title="修改内容" class="iconlink2"
       href="component/${fn:toLowerCase(resource.category)}/${resource.pid}/edit" target="_blank"><img
            src="resource/images/icons/small/black/edit.png"></a>
    <a title="编辑预览图及详情" class="iconlink2"
       href="help/document/${resource.pid}/imageAndDetails/update"  target="_blank"><img
            src="resource/images/icons/small/black/settings.png"></a>
    <a title="设置权限" class="iconlink2"
       href="component/main/${resource.pid}/permission/edit"  target="_blank"><img
            src="resource/images/icons/small/black/settings.png"></a>
    <a title="删除" class="iconlink2" href="javascript:del();"  ><img
            src="resource/images/icons/small/black/minus.png"></a>
</div>
