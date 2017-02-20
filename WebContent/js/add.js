/**
 * 添加用户基本验证
 * 
 */

$(function() {
	var user_id = $("input[name='user_id']");
	$(".btn").click(function() {

		var user_name = $("input[name='user_name']");

		var user_birth = $("input[name='user_birth']");
		
		var user_phone = $("input[name='user_phone']");

		var user_city = $("input[name='user_city']");

		var user_pass = $("input[name='user_pass']");

		var re_pass = $("input[name='re_pass']");

		while(user_id.val() == "") {
			user_id.focus();
			$(".spmsg").html("&nbsp;&nbsp;&nbsp;&nbsp;用户名不能为空");
			return false;
		}
		$(".spmsg").html("");

		while(user_name.val() == "") {
			user_name.focus();
			$(".spnym").html("&nbsp;&nbsp;&nbsp;&nbsp;真实姓名不能为空");
			return false;
		}
		$(".spnym").html("");

		while(user_birth.val() == "") {
			user_birth.focus();
			$(".spbirth").html("&nbsp;&nbsp;&nbsp;&nbsp;出生日期不能为空");
			return false;
		}
		$(".spbirth").html("");
		
		while(user_city.val() == "") {
			user_city.focus();
			$(".spcity").html("&nbsp;&nbsp;&nbsp;&nbsp;所在城市不能为空");
			return false;
		}
		$(".spcity").html("");

		while(user_pass.val() == "") {
			user_pass.focus();
			$(".sppass").html("&nbsp;&nbsp;&nbsp;&nbsp;密码不能为空");
			return false;
		}
		$(".sppass").html("");

		while(re_pass.val() == "") {
			re_pass.focus();
			$(".spre").html("&nbsp;&nbsp;&nbsp;&nbsp;确认密码不能为空");
			return false;
		}
		$(".spre").html("");
		while(user_pass.val() != re_pass.val()) {
			re_pass.select();
			$(".spre").html("&nbsp;&nbsp;&nbsp;&nbsp;密码不一致");
			return false;
		}
		$(".spre").html("");
		$("form").submit();

	});
	
});

