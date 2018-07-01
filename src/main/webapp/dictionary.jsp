<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<%@include file="base.jsp"%>
<html lang="zh">
<head>
    <title>管理系统</title>
</head>
<body>
<!-- start: Header -->
<%--顶部--%>
<%@include file="head.jsp"%>
<%--顶部--%>
<div class="container-fluid-full">
    <div class="row-fluid">
        <jsp:include page="menu.jsp"></jsp:include>
        <div id="content" class="span10">
          <ul class="breadcrumb">
              <li>
                <i class="icon-home"></i>
                <a href="#">系统管理</a>
                <i class="icon-angle-right"></i>
              </li>
            <li><a href="#">字典管理</a></li>
          </ul>
          <div class="row-fluid sortable">
                <div class="box span2">
                    <div class="box-header" data-original-title>
                        <h2><i class="halflings-icon white user"></i><span class="break"></span>分类列表</h2>
                        <div class="box-icon">
                            <%--<a  href="${path}/addUser1" class="btn-setting"><i class="halflings-icon white plus"></i></a>--%>
                            <a href="#" class="btn-setting"><i class="halflings-icon white plus"></i></a>
                            <a href="#" class="btn-minimize"><i class="halflings-icon white chevron-up"></i></a>
                            <a href="#" class="btn-close"><i class="halflings-icon white remove"></i></a>
                        </div>
                    </div>
                    <div class="box-content">
                        <table rules=none>
                            <c:forEach items="${dictionaryList}" var="dictionary">
                                <tr style="height: 30px">
                                    <td>
                                        <input type="hidden" value="${dictionary.id}">
                                    </td>
                                    <td class="align-center">
                                                    <span style="font-size: 16px">
                                                            <a  onclick="findDictionaryByParentid(${dictionary.id}, '${dictionary.text}')">${dictionary.text}</a>
                                                    </span>
                                    </td>
                                    <td class="align-right">
                                        <a class="btn btn-info" data-toggle="modal" data-target="#parentDictionaryEditDialog"
                                           onclick="editParentDictionary(${dictionary.id})">
                                            <i class="halflings-icon white edit" title='编辑'></i>
                                        </a>
                                        <a class="btn btn-danger" onclick="delDictionaryById(${dictionary.id})">
                                            <i class="halflings-icon white trash" title='删除'></i>
                                        </a>
                                        <%--<a class="btn btn-danger" onclick="delcfm('${path}/delDictionaryById?id=${dictionary.id}')">
                                            <i class="halflings-icon white trash" title='删除'></i>
                                        </a>--%>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>

                <div class="box span8">
                    <div class="box-header" data-original-title>
                        <h2><i class="halflings-icon white user"></i><span class="break"></span>字典列表</h2>
                        <div class="box-icon">
                            <%--<a href="#" class="btn-setting"><i class="halflings-icon white wrench"></i></a>--%>
                            <a href="#" data-toggle="modal" data-target="#addDictionaryEditDialog" onclick="findPreantId()">
                                <i class="halflings-icon white plus"></i></a>
                            <a href="#" class="btn-minimize"><i class="halflings-icon white chevron-up"></i></a>
                            <a href="#" class="btn-close"><i class="halflings-icon white remove"></i></a>
                        </div>
                    </div>
                    <div class="box-content">
                        <table class="table table-striped table-bordered">
                            <thead>
                            <tr>
                                <th width="30%">字典名称</th>
                                <th>排序</th>
                                <th width="30%">操作</th>
                            </tr>
                            </thead>
                            <tbody id="dictionaryId">
                               <tr>
                                   <td colspan="3">
                                       <span>请选择分类</span>
                                   </td>
                               </tr>
                            </tbody>
                        </table>
                    </div>
                </div><!--/span-->

          </div><!--/row-->
        </div>
    </div>
</div>


<%--<!-- 分类信息删除确认 -->
<div class="modal hide fade" id="delcfmModel">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">×</button>
        <h3>提示消息</h3>
    </div>
    <div class="modal-body">
        <p>您确认要删除吗？</p>
    </div>
    <div class="modal-footer">
        <input type="hidden" id="url"/>
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <a  onclick="urlSubmit()" class="btn btn-success" data-dismiss="modal">确定</a>
    </div>
</div><!-- /.modal -->--%>


<div class="modal hide fade" id="myModal">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">×</button>
        <h3>新增分类</h3>
    </div>
    <div class="modal-body">
        <%--<p>Here settings can be configured...</p>--%>
        <form id="parentDictionaryForm">
            <div class="control-group">
                <label class="control-label" for="focusedInput">分类名称</label>
                <div class="controls">
                    <input class="input-xlarge focused" id="aPText" type="text" name="text" placeholder="必填(可以为汉字、字母、数字的任意组合)">
                    <span><font color="red" id="aPText2"></font></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="focusedInput">排序</label>
                <div class="controls">
                    <input class="input-xlarge focused" id="aPSorting" type="text" name="sorting" placeholder="必填(必须为数字)">
                    <span><font color="red" id="aPSorting2"></font></span>
                </div>
            </div>
            <%--<input name="text" id="text">--%>
        </form>
    </div>
    <div class="modal-footer">
        <a href="#" class="btn" data-dismiss="modal">关闭</a>
        <a href="#" class="btn btn-primary" onclick="addParentDictionary()">保存</a>
    </div>
</div>

<!--编辑分类-->
<div class="modal hide fade" id="parentDictionaryEditDialog">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">×</button>
        <h3>编辑分类</h3>
    </div>
    <div class="modal-body">
        <%--<p>Here settings can be configured...</p>--%>
        <form id="editparentDictionaryForm">
            <input type="hidden" name="id" id="id">
            <div class="form-group">
                <label class="control-label" for="focusedInput">分类名称</label>
                <div class="controls">
                    <input type="text" class="input-xlarge focused" id="text" name="text" placeholder="必填(可以为汉字、字母、数字的任意组合)">
                    <span><font color="red" id="text2"></font></span>
                </div>
            </div>
            <div class="form-group">
                <label for="focusedInput" class="control-label">排序</label>
                <div class="controls">
                    <input type="text" class="input-xlarge focused" id="sorting" name="sorting" placeholder="必填(必须为数字)">
                    <span><font color="red" id="sorting2"></font></span>
                </div>
            </div>
            <%--<input name="text" id="text">--%>
        </form>
    </div>
    <div class="modal-footer">
        <a href="#" class="btn" data-dismiss="modal">关闭</a>
        <a href="#" class="btn btn-primary" onclick="updateParentDictionary()">保存</a>
    </div>
</div>

<!--新增字典-->
<div class="modal hide fade" id="addDictionaryEditDialog">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">×</button>
        <h3>新增字典</h3>
    </div>
    <div class="modal-body">
        <%--<p>Here settings can be configured...</p>--%>
        <form id="addDictionaryForm">
            <%--<input type="hidden" name="id" id="id">--%>
            <div class="control-group">
                <label class="control-label" for="focusedInput">上级</label>
                <div class="controls">
                    <div class="controls">
                        <input type="hidden" id="addSelectParentId" name="parentid">
                        <input type="text" disabled="disabled" class="input-xlarge focused" id="addParentText" name="parentText">
                    </div>
                    <%--<select class="input-xlarge focused" id="addSelectParentId" name="parentid">
                        <option value="">--请选择--</option>
                        <c:forEach items="${dictionaryList}" var="dictionary">
                            <option value="${dictionary.id}">${dictionary.text}</option>
                        </c:forEach>
                    </select>--%>
                </div>
            </div>
            <div class="form-group">
                <label for="focusedInput" class="control-label">字典名称</label>
                <div class="controls">
                    <input type="text" class="input-xlarge focused" id="addText" name="text" placeholder="必填(可以为汉字、字母、数字的任意组合)">
                    <span><font color="red" id="addText2"></font></span>
                </div>
            </div>
            <div class="form-group">
                <label for="focusedInput" class="control-label">排序</label>
                <div class="controls">
                    <input type="text" class="input-xlarge focused" id="addSorting" name="sorting" placeholder="必填(必须为数字)">
                    <span><font color="red" id="addSorting2"></font></span>
                </div>
            </div>
            <%--<input name="text" id="text">--%>
        </form>
    </div>
    <div class="modal-footer">
        <a href="#" class="btn" data-dismiss="modal">关闭</a>
        <a href="#" class="btn btn-primary" onclick="addDctionary()">保存</a>
    </div>
</div>

<!--编辑字典-->
<div class="modal hide fade" id="dictionaryEditDialog">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">×</button>
        <h3>编辑字典</h3>
    </div>
    <div class="modal-body">
        <%--<p>Here settings can be configured...</p>--%>
        <form id="editDictionaryForm">
            <input type="hidden" name="id" id="editId">
            <input type="hidden" name="parentid" id="editParentId">
            <div class="form-group">
                <label for="focusedInput" class="control-label">字典名称</label>
                <div class="controls">
                    <input type="text" class="input-xlarge focused" id="editText" name="text" placeholder="必填(可以为汉字、字母、数字的任意组合)">
                    <span><font color="red" id="editText2"></font></span>
                </div>
            </div>
            <div class="form-group">
                <label for="focusedInput" class="control-label">排序</label>
                <div class="controls">
                    <input type="text" class="input-xlarge focused" id="editSorting" name="sorting" placeholder="必填(必须为数字)">
                    <span><font color="red" id="editSorting2"></font></span>
                </div>
            </div>
            <%--<input name="text" id="text">--%>
        </form>
    </div>
    <div class="modal-footer">
        <a href="#" class="btn" data-dismiss="modal">关闭</a>
        <a href="#" class="btn btn-primary" onclick="updateDictionary()">保存</a>
    </div>
</div>
<div class="common-modal modal fade" id="common-Modal1" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-content">
        <ul class="list-inline item-details">
            <li><a href="#">Admin templates</a></li>
            <li><a href="http://themescloud.org">Bootstrap themes</a></li>
        </ul>
    </div>
</div>
<%@include file="foot.jsp"%>
<!-- start: JavaScript-->
<!--输入框验证js-->
<script src='js/dictionary/dictionaryCheck.js'></script>
<script type="text/javascript">
    /*function delcfm(url) {
        $('#url').val(url);//给会话中的隐藏属性URL赋值
        $('#delcfmModel').modal();
    }
    function urlSubmit(){
        var url=$.trim($("#url").val());//获取会话中的隐藏属性URL
        window.location.href=url;
    }*/
    function findDictionaryByParentid(pid,ptext) {
        //回写分类id和分类名称到字典新增框
        $("#addSelectParentId").val(pid);
        $("#addParentText").val(ptext);
        $.post("${path}/selectDictionaryByParentid", {"parentid": pid}, function (data) {
            //先清空子标签
            $("#dictionaryId").empty();
            $(data).each(function () {
                //追加子标签
                $("#dictionaryId").append("<tr><td style='display: none'><input type='hidden' name='parentid' value='" + pid + "'></td><td class=‘center’>" + this.text + "</td><td class='center'>" + this.sorting + "</td><td class='enter'><a class='btn btn-info' data-toggle='modal' data-target='#dictionaryEditDialog' onclick='editDictionaryById(" + this.id + ")'><i class='halflings-icon white edit' title='编辑'></i></a><a class='btn btn-danger' onclick='deleteDictionaryByParentid(" + this.id + ", " + pid + ")'><i class='halflings-icon white trash' title='删除'></i></a></td></tr>");
            });
        });
    };
    //删除确认对话
    function delDictionaryById(id) {
        bootbox.confirm("<div style='line-height: 1.5;'>确定要删除吗?</div>", function (result){
            if(result){
                window.location = '${path}/delDictionaryById?id='+id;
            }
        });
    }
    function deleteDictionaryByParentid(id, pid) {
        bootbox.confirm("<div style='line-height: 1.5;'>确定要删除吗?</div>", function (result){
            if(result){
                $.post("${path}/delDictionaryById", {"id": id}, function (data) {
                    $.post("${path}/selectDictionaryByParentid", {"parentid": pid}, function (data) {
                        //先清空子标签
                        $("#dictionaryId").empty();
                        $(data).each(function () {
                            //追加子标签
                            $("#dictionaryId").append("<tr><td style='display: none'><input type='hidden' name='parentid' value='" + pid + "'></td><td class=‘center’>" + this.text + "</td><td class='center'>" + this.sorting + "</td><td class='enter'><a class='btn btn-info' data-toggle='modal' data-target='#dictionaryEditDialog' onclick='editDictionaryById(" + this.id + ")'><i class='halflings-icon white edit' title='编辑'></i></a><a class='btn btn-danger' onclick='deleteDictionaryByParentid(" + this.id + ", " + pid + ")'><i class='halflings-icon white trash' title='删除'></i></a></td></tr>");
                        });
                    });
                });
            }
        });
    };
    function addParentDictionary() {
            if(addParentCheck()){
                $.post("${path}/addDictionary", $("#parentDictionaryForm").serialize(), function (data) {
                    window.location.reload();
                });
            }
    }

    function editParentDictionary(id) {
        $.post("${path}/selectParentDictionaryById", {"id": id}, function (data) {
            //alert(data.text);
            //回写
            $("#id").val(data.id);
            $("#text").val(data.text);
            $("#sorting").val(data.sorting);

        });
    }

    function editDictionaryById(id) {
        $.post("${path}/selectDictionaryById", {"id": id}, function (data) {
            //alert(data.text);
            //回写
            $("#editId").val(data.id);
            $("#editParentId").val(data.parentid);
            $("#editText").val(data.text);
            $("#editSorting").val(data.sorting);
        });
    }

    function updateDictionary() {
        if(editDictionaryCheck()){
            $.post("${path}/updateDictionary", $("#editDictionaryForm").serialize(), function (data) {
                var pid = $("#editParentId").val();
                $.post("${path}/selectDictionaryByParentid", {"parentid": pid}, function (data) {
                    //先清空子标签
                    $("#dictionaryId").empty();
                    $(data).each(function () {
                        //追加子标签
                        $("#dictionaryId").append("<tr><td style='display: none'><input type='hidden' name='parentid' value='" + pid + "'></td><td class=‘center’>" + this.text + "</td><td class='center'>" + this.sorting + "</td><td class='enter'><a class='btn btn-info' data-toggle='modal' data-target='#dictionaryEditDialog' onclick='editDictionaryById(" + this.id + ")'><i class='halflings-icon white edit' title='编辑'></i></a><a class='btn btn-danger' onclick='deleteDictionaryByParentid(" + this.id + ", " + pid + ")'><i class='halflings-icon white trash' title='删除'></i></a></td></tr>");
                    });
                });
                $("#dictionaryEditDialog").modal("hide");
            });
        }
    }

    function updateParentDictionary() {
        if(editParentCheck()){
            $.post("${path}/updateDictionary", $("#editparentDictionaryForm").serialize(), function (data) {
                window.location.reload();
                /*var pid = $("#id").val();
                $.post("${path}/selectDictionaryByParentid", {"parentid": pid}, function (data) {
                //先清空子标签
                $("#dictionaryId").empty();
                $(data).each(function () {
                    //追加子标签
                    $("#dictionaryId").append("<tr><td style='display: none'><input type='hidden' name='parentid' value='" + pid + "'></td><td class=‘center’>" + this.text + "</td><td class='center'>" + this.sorting + "</td><td class='enter'><a class='btn btn-info' data-toggle='modal' data-target='#dictionaryEditDialog' onclick='editDictionaryById(" + this.id + ")'><i class='halflings-icon white edit' title='编辑'></i></a><a class='btn btn-danger' onclick='deleteDictionaryByParentid(" + this.id + ", " + pid + ")'><i class='halflings-icon white trash' title='删除'></i></a></td></tr>");
                });
            });
            $("#parentDictionaryEditDialog").modal("hide");*/
            });
        }
    }

    function addDctionary() {
        if(addDictionaryCheck()){
            $.post("${path}/addDictionary", $("#addDictionaryForm").serialize(), function (data) {
                //获取分类id和分类名称
                var pid = $("#addSelectParentId").val();
                var ptext = $("#addParentText").val();
                $.post("${path}/selectDictionaryByParentid", {"parentid": pid}, function (data) {
                    //先清空子标签
                    $("#dictionaryId").empty();
                    $(data).each(function () {
                        //追加子标签
                        $("#dictionaryId").append("<tr><td style='display: none'><input type='hidden' name='parentid' value='" + pid + "'></td><td class=‘center’>" + this.text + "</td><td class='center'>" + this.sorting + "</td><td class='enter'><a class='btn btn-info' data-toggle='modal' data-target='#dictionaryEditDialog' onclick='editDictionaryById(" + this.id + ")'><i class='halflings-icon white edit' title='编辑'></i></a><a class='btn btn-danger' onclick='deleteDictionaryByParentid(" + this.id + ", " + pid + ")'><i class='halflings-icon white trash' title='删除'></i></a></td></tr>");
                    });
                });
                //添加成功后清空表单内容
                $("#addDictionaryForm")[0].reset();
                //将原来的分类名称回写
                $("#addParentText").val(ptext);
                $("#addDictionaryEditDialog").modal("hide");
            });
        }
    }
</script>
</body>
</html>
