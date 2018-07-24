<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

<%-- 
	메인화면 하단에 삽입되어서 저작권 표기(copyright)를 
	나타내는 푸터로 사용되는 페이지
	
	main.jsp에 포함(<jsp:include>)되어 화면에 나타남

	main.jsp에서 이 페이지 요청할 때, <jsp:param>으로
	전달한 파라미터를 추출하여 화면에 적용		
--%>

<%
	// JSP 의 내장 객체인 request로 부터 main.jsp 에서 설정된
	// 요청 파라미터를 추출할 수 있다.
	String color = request.getParameter("color");
	
%>
	<font color="<%= color %>">
		copyright &copy; gildong2.com
	</font>


</body>
</html>