<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
    pageContext.setAttribute("path", request.getContextPath());
%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<!-- saved from url=(0138)https://www.wconcept.cn/customer/address/new/is_checkout/1/isRedirect/1/is_gomonpage/1/sendtype/1,1/item-id/3057636,3057720/send-type/1,1/ -->
<html lang="zh"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Bootstrap Metro Dashboard by Dennis Ji for ARM demo</title>
    <meta name="description" content="Bootstrap Metro Dashboard">
    <meta name="author" content="Dennis Ji">
    <meta name="keyword" content="Metro, Metro UI, Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
    <!-- end: Meta -->

    <!-- start: Mobile Specific -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- end: Mobile Specific -->

    <!-- start: CSS -->
    <link id="bootstrap-style" href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/bootstrap-responsive.min.css" rel="stylesheet">
    <link id="base-style" href="css/style.css" rel="stylesheet">
    <link id="base-style-responsive" href="css/style-responsive.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800&subset=latin,cyrillic-ext,latin-ext' rel='stylesheet' type='text/css'>
    <!-- end: CSS -->


    <!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <link id="ie-style" href="css/ie.css" rel="stylesheet">
    <![endif]-->

    <!--[if IE 9]>
    <link id="ie9style" href="css/ie9.css" rel="stylesheet">
    <![endif]-->

    <!-- start: Favicon -->
    <link rel="shortcut icon" href="img/favicon.ico">
    <!-- end: Favicon -->




</head>

<body>
<!-- start: Header -->
<div class="navbar">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".top-nav.nav-collapse,.sidebar-nav.nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a class="brand" href="index.html"><span>JANUX</span></a>

            <!-- start: Header Menu -->
            <div class="nav-no-collapse header-nav">
                <ul class="nav pull-right">
                    <li class="dropdown hidden-phone">
                        <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="icon-bell"></i>
                            <span class="badge red">
								7 </span>
                        </a>
                        <ul class="dropdown-menu notifications">
                            <li class="dropdown-menu-title">
                                <span>You have 11 notifications</span>
                                <a href="#refresh"><i class="icon-repeat"></i></a>
                            </li>

                            <li class="dropdown-menu-sub-footer">
                                <a>View all notifications</a>
                            </li>
                        </ul>
                    </li>
                    <!-- start: Notifications Dropdown -->
                    <li class="dropdown hidden-phone">
                        <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="icon-calendar"></i>
                            <span class="badge red">
								5 </span>
                        </a>

                    </li>
                    <!-- end: Notifications Dropdown -->
                    <!-- start: Message Dropdown -->


                    <!-- start: User Dropdown -->
                    <li class="dropdown">
                        <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="halflings-icon white user"></i> Dennis Ji
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li class="dropdown-menu-title">
                                <span>Account Settings</span>
                            </li>
                            <li><a href="#"><i class="halflings-icon user"></i> Profile</a></li>
                            <li><a href="login.html"><i class="halflings-icon off"></i> Logout</a></li>
                        </ul>
                    </li>
                    <!-- end: User Dropdown -->
                </ul>
            </div>
            <!-- end: Header Menu -->

        </div>
    </div>
</div>
<!-- start: Header -->

<div class="container-fluid-full">
    <div class="row-fluid">
        <!-- start: Main Menu -->
        <div id="sidebar-left" class="span2">
            <div class="nav-collapse sidebar-nav">
                <ul class="nav nav-tabs nav-stacked main-menu">


                    <li><a href="#"><i class="icon-home"></i><span class="hidden-tablet"> &nbsp&nbsp&nbsp首页</span></a></li>



                    <li>
                        <a class="dropmenu" href="#"><i class="icon-print"></i></i><span class="hidden-tablet"> 设备管理</span><span class="label label-important"> 4</span></a>
                        <ul>

                            <li><a class="submenu" href="#"><i class="icon-file-alt"></i><span class="hidden-tablet"> 设备修改</span></a></li>
                            <li><a class="submenu" href="#"><i class="icon-file-alt"></i><span class="hidden-tablet"> 预警设置</span></a></li>
                            <li><a class="submenu" href="#"><i class="icon-file-alt"></i><span class="hidden-tablet"> 分组管理</span></a></li>
                            <li><a class="submenu" href="#"><i class="icon-file-alt"></i><span class="hidden-tablet"> 自检管理</span></a></li>
                        </ul>
                    </li>



                    <li>
                        <a class="dropmenu"  href="#"><i class="icon-user"></i></i><span class="hidden-tablet"> 系统管理</span><span class="label label-important"> 6 </span></a>
                        <ul>

                            <li><a class="submenu" href="user.html"><i class="icon-file-alt"></i><span class="hidden-tablet"> 用户管理</span></a></li>
                            <li><a class="submenu" href="role.html"><i class="icon-file-alt"></i><span class="hidden-tablet"> 角色管理</span></a></li>
                            <li><a class="submenu" href="permissions.html"><i class="icon-file-alt"></i><span class="hidden-tablet"> 权限管理</span></a></li>
                            <li><a class="submenu" href="organization.html"><i class="icon-file-alt"></i><span class="hidden-tablet"> 组织管理</span></a></li>
                            <li><a class="submenu" href="dictionary.html"><i class="icon-file-alt"></i><span class="hidden-tablet"> 字典管理</span></a></li>
                            <li><a class="submenu" href="log.html"><i class="icon-file-alt"></i><span class="hidden-tablet"> 日志管理</span></a></li>
                        </ul>
                    </li>



                    <li>
                        <a href="#"><i class="icon-exclamation-sign"></i><span class="hidden-tablet"> 预警管理</span></a>

                    </li>



                    <li>
                        <a href="#"><i class="icon-time"></i><span class="hidden-tablet"> 场景管理</span></a>

                    </li>



                    <li>
                        <a class="dropmenu"  href="#"><i class="icon-pencil"></i></i><span class="hidden-tablet"> 数据分析</span><span class="label label-important"> 2 </span></a>
                        <ul>
                            <li><a class="submenu" href="submenu2.html"><i class="icon-file-alt"></i><span class="hidden-tablet"> &nbsp用电量分析</span></a></li>
                            <li><a class="submenu" href="submenu2.html"><i class="icon-file-alt"></i><span class="hidden-tablet"> &nbsp预警类型分析</span></a></li>
                        </ul>
                    </li>



                </ul>
            </div>
        </div>
        <!-- end: Main Menu -->

        <noscript>
            <div class="alert alert-block span10">
                <h4 class="alert-heading">Warning!</h4>
                <p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a> enabled to use this site.</p>
            </div>
        </noscript>

        <!-- start: Content -->
        <div id="content" class="span10">



            <ul class="breadcrumb">
                <li>
                    <i class="icon-home"></i>
                    <a href="index.html">组织管理</a>
                    <i class="icon-angle-right"></i>
                </li>
                <li><a href="user.html">组织管理</a>
                    <i class="icon-angle-right"></i>
                </li>
                <li><a href="#">组织修改</a></li>
            </ul>

            <div class="row-fluid sortable">
                <div class="box span12">
                    <div class="box-header" data-original-title>
                        <h2><i class="halflings-icon white edit"></i><span class="break"></span>组织修改</h2>
                        <div class="box-icon">
                            <a href="#" class="btn-setting"><i class="halflings-icon white wrench"></i></a>
                            <a href="#" class="btn-minimize"><i class="halflings-icon white chevron-up"></i></a>
                            <a href="#" class="btn-close"><i class="halflings-icon white remove"></i></a>
                        </div>
                    </div>
                    <div class="box-content">
                        <form  id="modify" class="form-horizontal" action="">
                            <fieldset>

                                <div class="control-group">
                                    <label class="control-label" for="focusedInput">组织名称</label>
                                    <div class="controls">
                                        <input class="input-xlarge focused" id="focusedInput" type="text" placeholder="组织名称 .." name="name">
                                    </div>
                                </div>

                                <div class="control-group">
                                    <label class="control-label" for="focusedInput">描述</label>
                                    <div class="controls">
                                        <input class="input-xlarge focused" id="focusedInput" type="text" placeholder="描述" name="remark">
                                    </div>
                                </div>
                                <%--<div class="control-group">--%>
                                    <%--<label class="control-label" for="focusedInput">上级部门id</label>--%>
                                    <%--<div class="controls">--%>
                                        <%--<input class="input-xlarge focused" id="focusedInput" type="text" placeholder="描述" name="parentid">--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <div class="control-group">
                                    <label class="control-label" for="selectError3">上级部门</label>
                                    <div class="controls">
                                        <select id="selectError3" name="parentid">
                                            <c:forEach  var="Organization" items="${OrganizationLists}"  >
                                                <option value="${Organization.id}">${Organization.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-actions">
                                    <button type="submit" class="btn btn-primary" onclick="addUser()"> 提交 </button>
                                    <button class="btn"> 返回 </button>
                                </div>
                            </fieldset>
                        </form>

                    </div>
                </div><!--/span-->

            </div><!--/row-->


        </div><!--/.fluid-container-->

        <!-- end: Content -->

        <div class="modal hide fade" id="myModal">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">×</button>
                <h3>Settings</h3>
            </div>
            <div class="modal-body">
                <p>Here settings can be configured...</p>
            </div>
            <div class="modal-footer">
                <a href="#" class="btn" data-dismiss="modal">Close</a>
                <a href="#" class="btn btn-primary">Save changes</a>
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
        <div class="clearfix"></div>

        <footer>

            <p>
                <span style="text-align:left;float:left">&copy; 2013 <a href="downloads/janux-free-responsive-admin-dashboard-template/" alt="Bootstrap_Metro_Dashboard">JANUX Responsive Dashboard</a></span>

            </p>

        </footer>

        <!-- start: JavaScript-->
        <script type="text/javascript">
            function addUser() {
                var form = document.forms[0];
                form.action = "<%=basePath %>addOrganization";
                form.method = "post";
                form.submit();
            }
        </script>

        <script src="js/checkBox.js"></script>
        <script src="js/jquery-1.9.1.min.js"></script>
        <script src="js/jquery-migrate-1.0.0.min.js"></script>

        <script src="js/jquery-ui-1.10.0.custom.min.js"></script>

        <script src="js/jquery.ui.touch-punch.js"></script>

        <script src="js/modernizr.js"></script>

        <script src="js/bootstrap.min.js"></script>

        <script src="js/jquery.cookie.js"></script>

        <script src='js/fullcalendar.min.js'></script>

        <script src='js/jquery.dataTables.min.js'></script>

        <script src="js/excanvas.js"></script>
        <script src="js/jquery.flot.js"></script>
        <script src="js/jquery.flot.pie.js"></script>
        <script src="js/jquery.flot.stack.js"></script>
        <script src="js/jquery.flot.resize.min.js"></script>

        <script src="js/jquery.chosen.min.js"></script>

        <script src="js/jquery.uniform.min.js"></script>

        <script src="js/jquery.cleditor.min.js"></script>

        <script src="js/jquery.noty.js"></script>

        <script src="js/jquery.elfinder.min.js"></script>

        <script src="js/jquery.raty.min.js"></script>

        <script src="js/jquery.iphone.toggle.js"></script>

        <script src="js/jquery.uploadify-3.1.min.js"></script>

        <script src="js/jquery.gritter.min.js"></script>

        <script src="js/jquery.imagesloaded.js"></script>

        <script src="js/jquery.masonry.min.js"></script>

        <script src="js/jquery.knob.modified.js"></script>

        <script src="js/jquery.sparkline.min.js"></script>

        <script src="js/counter.js"></script>

        <script src="js/retina.js"></script>

        <script src="js/custom.js"></script>
        <!-- end: JavaScript-->
        <!--
    <script>

     $(function(){
     function initTableCheckbox() {
     var $thr = $('table thead tr');
     var $checkAllTh = $('<th><input type="checkbox" id="checkAll" name="checkAll" /></th>');
     /*将全选/反选复选框添加到表头最前，即增加一列*/
     $thr.prepend($checkAllTh);
     /*“全选/反选”复选框*/
     var $checkAll = $thr.find('input');
     $checkAll.click(function(event){
      /*将所有行的选中状态设成全选框的选中状态*/
      $tbr.find('input').prop('checked',$(this).prop('checked'));
      /*并调整所有选中行的CSS样式*/
      if ($(this).prop('checked')) {
      $tbr.find('input').parent().parent().addClass('warning');
      } else{
      $tbr.find('input').parent().parent().removeClass('warning');
      }
      /*阻止向上冒泡，以防再次触发点击操作*/
      event.stopPropagation();
     });
     /*点击全选框所在单元格时也触发全选框的点击操作*/
     $checkAllTh.click(function(){
      $(this).find('input').click();
     });
     var $tbr = $('table tbody tr');
     var $checkItemTd = $('<td><input type="checkbox" name="checkItem" /></td>');
     /*每一行都在最前面插入一个选中复选框的单元格*/
     $tbr.prepend($checkItemTd);
     /*点击每一行的选中复选框时*/
     $tbr.find('input').click(function(event){
      /*调整选中行的CSS样式*/
      $(this).parent().parent().toggleClass('warning');
      /*如果已经被选中行的行数等于表格的数据行数，将全选框设为选中状态，否则设为未选中状态*/
      $checkAll.prop('checked',$tbr.find('input:checked').length == $tbr.length ? true : false);
      /*阻止向上冒泡，以防再次触发点击操作*/
      event.stopPropagation();
     });
     /*点击每一行时也触发该行的选中操作*/
     $tbr.click(function(){
      $(this).find('input').click();
     });
     }
     initTableCheckbox();
     });
     </script>
    -->
</body>
</html>
