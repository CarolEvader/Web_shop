<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>尚硅谷会员注册页面</title>
		<%@include file="/pages/common/head.jsp"%>
		<style type="text/css">
			.login_form{
				height:420px;
				margin-top: 25px;
			}
		</style>
		<script type="text/javascript">
			$(function () {
				$("#username").blur(function () {
					var name = this.value;
					$.getJSON("http://localhost:8080/book/userServlet", "action=ajaxExistsUsername&username=" + name, function (data) {
						if(data.existsUsername) {
							$("span.errorMsg").text("用户名已存在！");
						} else {
							$("span.errorMsg").text("用户名可用！");
						}
					});
				});

				$(":submit").click(function () {
					var userName = $("#username").val();
					var passWord = $("#password").val();
					var rePwd = $("#repwd").val();
					var email = $("#email").val();
					var confirm = $("#code").val();
					var isNameOrPwd = /^\w{5,12}$/;
					var isEmail = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;


					if(!isNameOrPwd.test(userName)) {
						$("span.errorMsg").text("用户名不合法");
						return false;
					}

					if(!isNameOrPwd.test(passWord)) {
						$("span.errorMsg").text("密码不合法");
						return false;
					}

					if(rePwd != passWord) {
						$("span.errorMsg").text("两次密码不一致或确认密码为空");
						return false;
					}

					if(!isEmail.test(email)) {
						$("span.errorMsg").text("邮箱不合法");
						return false;
					}

					confirm = $.trim(confirm);

					if(confirm == null || confirm == "") {
						$("span.errorMsg").text("验证码错误");
						return false;
					}

					$("span.errorMsg").text("");

				});
				
				$("#code_jpg").click(function () {
					this.src = "${basePath}kaptcha.jpg?d=" + new Date();
				});


			});
		</script>
	</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								<span class="errorMsg">
									${empty requestScope.msg ? "" : requestScope.msg}
								</span>
							</div>
							<div class="form">
								<form action="userServlet" method="post">
									<input type="hidden" name="action" value="regist">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" id="username" value="${requestScope.username}" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮箱：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1" name="email" id="email" value="${requestScope.email}" />
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 80px;" name="code" id="code"/>
									<img alt="" src="kaptcha.jpg" style="float: right; margin-right: 40px; width: 110px; height: 30px" id="code_jpg">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<%@include file="/pages/common/footer.jsp"%>
</body>
</html>