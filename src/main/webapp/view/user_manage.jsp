<%--
  Created by IntelliJ IDEA.
  User: jfchen
  Date: 12/24/14
  Time: 12:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="common/path.jsp"></jsp:include>
    <jsp:include page="common/header.jsp"></jsp:include>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Project name</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Home</a></li>
                <li><a href="#about">About</a></li>
                <li><a href="#contact">Contact</a></li>
            </ul>
        </div>
        <!--/.nav-collapse -->
    </div>
</nav>
<div class="container" id="content" style="margin-top: 30px; height:100%" >

    <!--标签页定义-->
    <ul class="nav nav-tabs" role="tablist" id="nav-tab">
        <li class="active" role="presentation">
            <a id="user-list-tab-li" href="#user-list-tab-pane">用户列表</a>
        </li>

        <li class="" role="presentation">
            <a id="user-new-tab-li" href="#user-new-tab-pane">
                <button class="close closeTheTab" type="button">×</button>
                新建用户</a>
        </li>
    </ul>
    <!-- 标签页面容器DIV -->
    <div class="tab-content">
        <!-- 用户列表标签页 -->
        <div class="tab-pane active" id="user-list-tab-pane">

            <!-- 主显示区域开始 -->
            <div class="navbar">
                <div class="navbar-inner">
                    <form id="form-user-query" class="navbar-form pull-left">
                        <input name="account" type="text" class="" placeholder="账号">
                        <input value="user/query/by/map" type="hidden" name="action"/>

                        <select class="span2" name="roleId">
                            <option value="">所有角色</option>
                            <c:forEach var="role" items="${roles}">
                                <option value="${role.id}">${role.name}</option>
                            </c:forEach>
                        </select>

                        <button type="button" class="btn" onclick="queryUserList(1, 20)"><i class="icon-search"></i> 查询
                        </button>
                        <button type="button" class="btn btn-primary" onclick="openNewTab()"><i class="icon-plus"></i>
                            新建
                        </button>
                    </form>
                </div>
            </div>
            <div class="control-group">
                <div class="controls">
                    <table id="user-table" class="table table-bordered table-striped table-hover"></table>
                </div>
            </div>

            <!-- 分页显示区域 -->
            <div id="user-page"></div>
        </div>

        <!-- 新建用户标签页 -->
        <div class="tab-pane" id="user-new-tab-pane">
            <form class="form-horizontal" id="userNewForm">
                <div class="control-group">
                    <!-- Text input-->
                    <label class="control-label">
                        账号
                    </label>

                    <div class="controls">
                        <input class="input-xlarge" name="account" id="userId" value="" type="text" placeholder="">
                    </div>
                </div>

                <div class="control-group">
                    <!-- Text input-->
                    <label class="control-label" for="userPwd">
                        登录密码
                    </label>

                    <div class="controls">
                        <input class="input-xlarge" name="password" id="password" value="" type="text" placeholder="">
                    </div>
                </div>
                <div class="control-group">
                    <!-- Text input-->
                    <label class="control-label" for="userName">
                        真实姓名
                    </label>

                    <div class="controls">
                        <input class="input-xlarge" name="name" id="userName" value="" type="text" placeholder="">
                    </div>
                </div>

                <div class="control-group">
                    <!-- Text input-->
                    <label class="control-label" >
                        角色
                    </label>
                    <div class="controls">
                        <select class="span2"  name="roleId">
                            <c:forEach var="role" items="${roles}">
                                <option value="${role.id}">${role.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="control-group">
                    <!-- Text input-->
                    <label class="control-label">

                    </label>

                    <div class="controls">
                        <button type="reset" class="btn" onclick=""> 重置</button>
                        <button type="button" class="btn btn-primary" onclick="saveNewUser()"> 提交</button>
                    </div>
                </div>
                <hr>
            </form>
        </div>
    </div>


</div>
<!-- 必须包含通用页面底部JSP -->
<jsp:include page="common/bottom.jsp"></jsp:include>
<%--模块js--%>
<script src="../res/js/user_manage.js"></script>
</body>
</html>
