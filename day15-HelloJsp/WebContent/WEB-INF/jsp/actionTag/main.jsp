<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
</head>
<body>

	<h1>길동이 닷 컴</h1>
	<h2>길동이 닷 컴에 오신 것을 환영합니다.</h2>
	<!-- <hr> : 화면에 수평선을 그려주는 태그 -->
	<hr size="4" />
	
	<!-- 보통의 메인화면 하단에 copy right를 표기하는 
	     화면 푸터를 액션태그를 사용하여 삽입 -->
	<jsp:include page="/copy">
		<jsp:param value="red" name="color"/>
	</jsp:include>
	<%-- <jsp:include>는 다른 페이지를 삽입해달라는 요청으로 해석 
		 끼워 넣을 페이지에 전달할 값이 있다면
		 요청 파라미터로 전달 가능
		 이 때 <jsp:param>을 사용하여 다른 페이지로 값을 전달
	--%>
</body>
</html>