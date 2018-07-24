<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내장 객체 request 사용</title>
<style type="text/css">
	table, tr, th, td {
		border : solid 1px black;
	}
</style>
</head>
<body>

<%
	// 요청 주소 : req
	// _jspService() 메소드 내부에 작성되는 부분
	List<String> fruits = new ArrayList<>();

	fruits.add("사과");
	fruits.add("메론");
	fruits.add("수박");
	fruits.add("복숭아");
	fruits.add("포도");
	fruits.add("참외");
	fruits.add("자두");
	
	// 과일 목록을 request 내장 객체에 추가
	// 내장 객체에 데이터를 추가할 때 attribute 로 추가
	// attribute 는 parameter 와 다름을 주의
	
	request.setAttribute("fruits", fruits);
	
	// 추가된 attribute 추출
	// attribute 는 Object 타입으로 추가되므로 추출 후 사용하려면
	// 형변환이 필요함
	List<String> fruitList = (List<String>)request.getAttribute("fruits");
	
	// 브라우저에 출력
%>

	<table>
		<tr>
			<th>과일 이름</th>
		</tr>
	<% for (String fruit : fruitList) { %>
		<tr>
			<td><%= fruit %></td>
		</tr>
	<% } %>
	</table>
</body>
</html>