<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL (3) 다중파라미터의 처리</title>
</head>
<body>

이 페이지를 요청할 때는 다음의 주소를 복사하여 요청합니다. <br />
http://localhost:8088/day15-EL/el/el3.jsp <br />
http://localhost:8088/day15-EL/el/el3.jsp?hobby=야구 <br />
http://localhost:8088/day15-EL/el/el3.jsp?hobby=야구&hobby=축구 <br />
http://localhost:8088/day15-EL/el/el3.jsp?hobby=야구&hobby=축구&hobby=서핑<br />
http://localhost:8088/day15-EL/el/el3.jsp?hobby= <br />


<h3>EL 의 다중 파라미터 처리 : paramValues</h3>
<pre>
	paramValues로 파라미터를 추출하면 배열로 받아짐
	따라서 배열 인덱스를 나타내는 [숫자]로 접근 가능

	1. hobby의 [0]번째 값 : \${paramValues.hobby[0]} = ${paramValues.hobby[0]}
	2. hobby의 [1]번째 값 : \${paramValues.hobby[1]} = ${paramValues.hobby[1]}
	3. hobby의 [2]번째 값 : \${paramValues["hobby"][2]} = ${paramValues["hobby"][2]}
</pre>

<h3>scriptlet, expression Tag 를 사용한 파라미터 처리 : getParameterValues()</h3>
<pre>
<%
	String[] hobbies = request.getParameterValues("hobby");

	if (hobbies != null) {
		try  {
%>

	1. hobby의 [0]번째 값 : <%= hobbies[0] %>
	2. hobby의 [1]번째 값 : <%= hobbies[1] %>
	3. hobby의 [2]번째 값 : <%= hobbies[2] %>
	
</pre>
<% 		} catch (ArrayIndexOutOfBoundsException e) { %>
		모든 파라미터를 전달 받지 못했습니다.
<% 		} %>

<% } else { %>
	파라미터에 hobby 라는 이름으로 전달된 값이 없습니다.
<% } %>
</body>
</html>