
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div id="dialog-confirm" title="系统提示！">
    <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>确定删除?</p>
</div>

<script type="text/javascript">
    jQuery( "#dialog-confirm").hide();

    function del(){
        jQuery( "#dialog-confirm" ).dialog({
            resizable: false,
            height:140,
            modal: true,
            buttons: {
                "确定": function() {
                    jQuery( "#dialog-confirm").dialog( "close" );
                    jQuery.ajax({ url: "component/${fn:toLowerCase(param.category)}/delete",
                        dataType: "json",
                        type:"POST",
                        data:{pid:'${param.pid}'},
                        success: function(res){
                            location.href = "/component/main/operatingResults?success=true";
                        },
                        error:function(){
                            location.href = "/component/main/operatingResults?success=flase";
                        }

                    });

                },
                "取消": function() {
                    jQuery( this ).dialog( "close" );
                }
            }
        });

    }
    function next(){
        var ele="<input type='hidden' name='flag' value='1'>";
        jQuery("form").append(ele);
    }
    function finish(){
        var ele="<input type='hidden' name='flag' value='2'>";
        jQuery("form").append(ele);
    }
</script>
