
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div  class="dataTables_length">
    <label>每页显示 <select id="dataTables_length" name="pageSize" size="1" onchange="sub();">
        <option value="10"  ${data.size==10?'selected="selected"':''}>10</option>
        <option value="25" ${data.size==25?'selected="selected"':''} >25</option>
        <option value="50"  ${data.size==50?'selected="selected"':''}>50</option>
        <option value="100"  ${data.size==100?'selected="selected"':''}>100</option>
    </select> 项记录</label>
    <label>选择排序方式
        <select id="sortProperty" name="sortProperty" size="1" onchange="sub();">
            <option value="pid"  ${query.sortProperty=='pid'?'selected="selected"':''} >
                组件编码
            </option>
            <option value="name"  ${query.sortProperty=='name'?'selected="selected"':''} >
                组件名称
            </option>
            <option value="state"  ${query.sortProperty=='state'?'selected="selected"':''} >
                状态
            </option>

            <option value="updateTime"  ${query.sortProperty=='updateTime'?'selected="selected"':''}>
                修改日期
            </option>

        </select>
        <img style="vertical-align: middle;margin-right: 5px;" name="img" src="resource/images/icons/sort_ascend.png" />
    </label>
</div>
