<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/4
  Time: 9:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- start: Main Menu -->
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<div id="sidebar-left" class="span2">
    <div class="nav-collapse sidebar-nav">
        <ul class="nav nav-tabs nav-stacked main-menu">


            <li><a href="#"><i class="icon-home"></i><span class="hidden-tablet"> &nbsp&nbsp&nbsp首页</span></a></li>



                <li>
                    <a  href="/pdusearch"><i class="icon-print"></i><span class="hidden-tablet"> 设备管理</span></a>
                </li>
                <li>
                    <a href="/pduGrouplist"><i class="icon-exclamation-sign"></i><span class="hidden-tablet"> 设备分组</span></a>
                </li>
                <li>
                    <a href="/pduSelfchecklist"><i class="icon-print"></i><span class="hidden-tablet"> 自检管理</span></a>
                </li>
                <li>
                    <a href="/sceneList"><i class="icon-time"></i><span class="hidden-tablet"> 场景管理</span></a>

                </li>


<shiro:hasRole name="管理员">
            <li>
                <a class="dropmenu"  href="#"><i class="icon-user"></i></i><span class="hidden-tablet"> 系统管理</span><span class="label label-important"> 6 </span></a>
                <ul id="userMeun" >

                    <li><a class="submenu" href="/userlist"><i class="icon-file-alt"></i><span class="hidden-tablet"> 用户管理</span></a></li>
                    <li><a class="submenu"  href="/rolelist"><i class="icon-file-alt"></i><span class="hidden-tablet"> 角色管理</span></a></li>
                    <%--<li><a class="submenu"  href="/permissionslist"><i class="icon-file-alt"></i><span class="hidden-tablet"> 权限管理</span></a></li>--%>
                    <li><a class="submenu"  href="/organizationlist"><i class="icon-file-alt"></i><span class="hidden-tablet"> 部门管理</span></a></li>
                    <li><a class="submenu" href="/dictionaryList"><i class="icon-file-alt"></i><span class="hidden-tablet"> 字典管理</span></a></li>
                    <li><a class="submenu"  href="/logList"><i class="icon-file-alt"></i><span class="hidden-tablet"> 日志管理</span></a></li>
                </ul>
            </li>
</shiro:hasRole>

            <li>
                <a class="dropmenu"  href="#"><i class="icon-pencil"></i></i><span class="hidden-tablet"> 数据分析</span>
                    <%--<span class="label label-important">  </span>--%>
                </a>
                <ul>
                    <%--<li><a class="submenu" href="submenu2.html"><i class="icon-file-alt"></i><span class="hidden-tablet"> &nbsp;用电量分析</span></a></li>--%>
                    <li><a class="submenu" href="/PduWarning"><i class="icon-file-alt"></i><span class="hidden-tablet"> &nbsp;预警类型分析</span></a></li>
                </ul>
            </li>
        </ul>
    </div>
</div>
<noscript>
    <div class="alert alert-block span10">
        <h4 class="alert-heading">Warning!</h4>
        <p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a>
            enabled to use this site.</p>
    </div>
</noscript>
</html>
<!-- end: Main Menu -->
