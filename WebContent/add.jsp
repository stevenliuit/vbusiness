<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>微分销管理系统</title>
<!-- Bootstrap -->
<link href="bootstrap-3.3.5/css/bootstrap.min.css" rel="stylesheet">

<style type="text/css">
body {
	padding: 70px;
}

.table {
	width: 50%;
	margin: 0px auto;
	margin-top: 30px;
}

input[type='text'], input[type='password'] {
	width: 250px;
	border: 1px solid #eee;
	height: 40px;
	line-height: 40px;
	border-radius: 3px;
	padding-left: 10px;
}

.spmsg,.spnym,.spbirth,.spcity,.sppass,.spre {
	font-size: 12px;
	color: red;
}
</style>
</head>
<body>

	<!-- 引入我们的头部文件 -->
	<%@ include file="head.jsp"%>
	<div class="container-fluid ">
		<form action="<%=path%>/add.do" method="post">
			<table class="table table-hover table-bordered">
				<tr>
					<td colspan="2"
						style="font-size: 16px; font-weight: bold; text-align: center;">推荐会员</td>
				</tr>
				<tr>
					<td align="right">推荐人：</td>
					<td>${sessionScope.user }</td>
				</tr>
				<tr>
					<td align="right" style="vertical-align: middle;">登录账号：</td>
					<td><input type="text" name="user_id" /><span class="spmsg"></span>
					</td>
				</tr>
				<tr>
					<td align="right" style="vertical-align: middle;">真实姓名：</td>
					<td><input type="text" name="user_name" /><span class="spnym"></span></td>
				</tr>
				<tr>
					<td align="right" style="vertical-align: middle;">出生日期：</td>
					<td><input type="text" name="user_birth"
						onclick="WdatePicker()" /><span class="spbirth"></span></td>
				</tr>
				<tr>
					<td align="right" style="vertical-align: middle;">手机号码：</td>
					<td><input type="text" /></td>
				</tr>
				<tr>
					<td align="right" style="vertical-align: middle;">所在城市：</td>
					<td><input type="text" name="user_city" /><span class="spcity"></span></td>
				</tr>
				<tr>
					<td align="right" style="vertical-align: middle;">登录密码：</td>
					<td><input type="password" name="user_pass" /><span class="sppass"></span></td>
				</tr>
				<tr>
					<td align="right" style="vertical-align: middle;">确认密码：</td>
					<td><input type="password" name="re_pass" /><span class="spre"></span></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button class="btn  btn-primary" type="button">提交保存</button>
					</td>

				</tr>

				<tr>
					<td colspan="2" style="color: red; font-size: 10px;" align="center">
						${msg }</td>
				</tr>
			</table>
		</form>
	</div>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="js/jquery-1.11.2.min.js"></script>
	<script src="bootstrap-3.3.5/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="js/add.js"></script>
</body>
</html>