<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- JSTL사용설정 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/visit.css">
<script type="text/javascript">
	function del(f){
		var pwd = f.pwd.value;	//원래 비번
		var c_pwd = f.c_pwd.value;	//입력한 비번
		
		if(pwd!=c_pwd){
			alert('비밀번호가 틀립니다.');
			f.c_pwd.value="";
			f.c_pwd.focus();
			return;
		}
		
		//삭제유무 확인
		if(confirm("정말 삭제하시겠습니까?")==false) return;
		
		//자바스크립트 이용해서 삭제정보 전달
		/* f.action = "delete.do";
		f.submit(); */
		location.href = 'delete.do?idx='+f.idx.value;
	}
	function modify(f){
		var pwd = f.pwd.value;	//원래 비번
		var c_pwd = f.c_pwd.value;	//입력한 비번
		
		if(pwd!=c_pwd){
			alert('비밀번호가 틀립니다.');
			f.c_pwd.value="";
			f.c_pwd.focus();
			return;
		}
		
		
		//자바스크립트 이용해서 삭제정보 전달
		/* f.action = "delete.do";
		f.submit(); */
		location.href = 'modify_form.do?idx='+f.idx.value;
	}

	
</script>
</head>
<body>
<%-- ${pageContext.request.contextPath} --%>
<div id="main_box">
	<h1>:::방명록 보기:::</h1>
	<div style="text-align: right;">
		<input type="button" value="방명록쓰기" onclick="location.href='insert_form.do'">
	</div>
	<!-- Data가 없을 경우 -->
	<c:if test="${empty list}">
		<div id="empty_message">
			작성된 게시글이 없습니다.
		</div>
	</c:if>
	
	<!-- Data가 있는 경우 -->
	
	<c:forEach var="visit" items="${list}">
	<div class="content_box">
		<div class="content">${visit.content}</div>
		<div class="name">작성자 : ${visit.name }</div>
		<div class="regdate">작성일자 : ${fn:substring(visit.regdate,0,16)}(${visit.ip })</div>
		<div class="pwd">
		<form>
			<input type="hidden" name="idx" value="${visit.idx}">
			<input type="hidden" name="pwd" value="${visit.pwd}">
			비밀번호(${visit.pwd }):<input type="password" name="c_pwd">
			<input type="button" value="삭제" onclick="del(this.form)">
			<input type="button" value="수정" onclick="modify(this.form)">
		</form>
		</div>
	</div>		
	</c:forEach>
	
</div>
</body>
</html>






















