<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<script>
	function checking() {
		if(document.getElementById("memberId").value == "" || document.getElementById("passwd").value == "" || document.getElementById("name").value == ""
				|| document.getElementById("gender").value == "" || document.getElementById("age").value == "") {
			alert("필수 입력 항목을 채워주십시오");
		} else if(document.getElementById("name").value.length > 20) {
			alert("이름은 20글자를 넘을 수 없습니다.");
		} else if(document.getElementById("memberId").value.length != 11) {
			alert("휴대폰 번호를 11자로 입력해주십시오");
		} else if(document.getElementById("gender").value == "none" || document.getElementById("gender").value == null) {
			alert("성별을 선택해 주세요.");
		} else {
		document.join.action = "memberList";
		document.join.submit();
		}
	}
</script>
<meta charset="UTF-8">
<title>회원 가입</title>
</head>
<body>
<h2>
회원가입
</h2>
	<form method = "post" name = "join" >
	  <table border-collapse=collapse;>	
	  <tr>
			<td>핸드폰 번호*</td>
			<td><input type = "text" name = "memberId" id = "memberId"></td>
		</tr>
		<tr>
			<td>비밀번호*</td>
			<td><input type = "password" name = "passwd" id = "passwd"></td>
		</tr>	  
		<tr>	
			<td>이름*</td>
			<td><input type = "text" name = "name" id = "name"></td>
		</tr>
		<tr>	
			<td>성별*</td>
			<td><select style="width:120px; height:25px;" name="gender" id="gender">
					<option value="none"></option>
      				<option value="남자">남자</option>
      				<option value="여자">여자</option>     
    				</select>
    		</td>
		</tr>
		<tr>	
			<td>나이*</td>
			<td><input type = "text" name = "age" id = "age"></td>	
	    </tr>
	    <tr>	
			<td>가입일</td>
			<td><input type = "text" name = "joinDate" id = "joinDate" value="<fmt:formatDate value="${joinDate}" pattern="yyyy-MM-dd" />"></td>	
	    </tr>
	  </table>
	  <br>
		<input type="button" onclick="checking()" value = "입력" >
	</form>
</body>
</html>
