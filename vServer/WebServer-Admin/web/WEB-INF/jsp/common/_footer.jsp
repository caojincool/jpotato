<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<DIV class="footerinner">© 2013 <a href="http://www.lemsun.com">www.lemsun.com</a> 版权所有</DIV><!-- footerinner -->
<div id="dialog-form" title="设置帐套和操作日期">
    <form>
        <fieldset>
            <table width="100%">
                <tr>
                    <td class="firstColom">帐套选择:</td>
                    <td>   <select id="categorytype" name="categorytype"  style="width: 170px;" >
                        <option value="">请帐套选择</option>
                        <option value="0">测试2</option>
                        <option value="1">测试3</option>
                        <option value="2">测试4</option>
                        <option value="3">测试5</option>
                    </select>
                    </td>
                </tr>
                <tr>
                    <td class="firstColom">操作日期:</td>
                    <td><input type="text" id="operationDate"  name="name"
                               tabindex="1"/>
                    </td>

                </tr>
            </table>


        </fieldset>
    </form>
</div>
<div id="dialog-message" title="系统提示！">
    <p>
        <span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
    <p id="message">

    </p>
    </p>
</div>
<style type="text/css">
    .no-close .ui-dialog-titlebar-close {
        display: none;
    }
</style>
<script type="text/javascript">
    jQuery( "#dialog-message").hide();
    jQuery( "#dialog-form").hide();
    jQuery(function() {
        jQuery( "#operationDate" ).datepicker();
    });

    function setofbooks(){
        jQuery( "#dialog-form" ).dialog({
            dialogClass: "no-close",
            resizable: false,
            height: 200,
            width: 350,
            modal: true,
            buttons: {
                "确定": function() {


                    jQuery( "#setofbooks").html(jQuery( "#operationDate").val()+' '+ jQuery("#categorytype  option:selected").text());
                    jQuery( this ).dialog( "close" );
                    jQuery( "#operationDate").val("");
                    jQuery( "#categorytype").val("");
                },
                "取消": function() {
                    jQuery( "#operationDate").val("");
                    jQuery( "#categorytype").val("");
                    jQuery( this ).dialog( "close" );
                }
            }
        });
    }

</script>