<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function send(f){
		var name = f.name.value.trim();
		var content = f.content.value.trim();
		var pwd = f.pwd.value.trim();
		
		if(name==''){
			alert('이름을 입력하세요');
			f.name.value="";
			f.name.focus();
			return;
		}
		if(content==''){
			alert('내용을 입력하세요');
			f.content.value="";
			f.content.focus();
			return;
		}
		if(pwd==''){
			alert('비밀번호를 입력하세요');
			f.pwd.value="";
			f.pwd.focus();
			return;
		}
		
		if(confirm('정말 수정하시겠습니까?')==false) return;
		//값체크 완료 후 정송
		f.action = "modify.do"; //VisitInsertAction
		f.submit();
	}

</script>

</head>
<body>
<form>
	<table border="1" align="center">
		<caption>::::방명록 글쓰기::::</caption>
		<tr>
			<th>이름</th>
			<td><input name="name" value="${vo.name }">
			<input name=idx type="hidden" value="${vo.idx}">
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
				<textarea name="content" rows="5" cols="60">${vo.content }</textarea>
			</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td>
			
			<input type="password" name="pwd"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="button" value="수정" onclick="send(this.form)">
				<input type="button" value="목록보기" onclick="location.href='list.do'">
			</td>
		</tr>
	</table>
</form>
</body>
</html>