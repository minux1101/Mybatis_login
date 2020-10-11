<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
	<form method="POST" action="loginresult">
		<input type="text" name="idField" id="idField" placeholder="ID"><br>
		<input type="password" name="pwField" id="pwField" placeholder="PASSWORD"><br>
		<button id="login_process">LOGIN</button>
	</form>	
		<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
		<script>
		$(document).ready(function() {
			$("#login_process").click(function(){
				var json = {
						idField : $("#idField").val(),
						pwField : $("#pwField").val()
				}
				for(var str in json){
					if(json[str].length == 0) {
						alert("PLEASE INPUT " +
								$("#" + str).attr("placeholder"));
						$("#" + str).focus();
						return;
					}
				}
			});
		});
		</script>
	</body>
</html>