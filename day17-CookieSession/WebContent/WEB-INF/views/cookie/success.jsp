<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 성공</title>
</head>
<body>

<%
	// 쿠키는 여러개가 추가되었을 수 있으므로
	// 항상 배열로 얻어낸다.
	Cookie[] cookies = request.getCookies();

	// 쿠키는 key=value 쌍으로 저장되기 때문에
	// map 타입으로 관리하기 편리함
	Map<String, String> cookieMap = new HashMap<>();
	
	// 쿠키에 담겨진 유저아이디, 비밀번호를 저장할 변수
	String userId = null;
	String password = null;
	
	// 이 요청에 쿠키가 존재하면
	if (cookies != null) {
		// 쿠키 값을 추출
		for (Cookie cookie : cookies) {
			// 쿠키 한 개 씩 추출하여 쿠키 이름, 쿠키 값을
			// 맵에 반복적으로 추가
			cookieMap.put(cookie.getName(), cookie.getValue());
			
			// cookie 값 확인용 출력
			out.write(cookie.getName() + ":" + cookie.getValue() + "<br />");
		}
		
		// for 가 끝나면 배열에 있는 쿠키가 맵에 담김
		userId = cookieMap.get("userId");
		password = cookieMap.get("password");
	}
	
	// userId, password 에 담긴 값으로 성공 여부 출력
	if (userId != null && password != null) {
%>
	안녕하세요, <%= userId %>님, 로그인 되었습니다. <br />
	로그인 10초 후 쿠키가 만료되면 자동 로그아웃 됩니다. <br />
<%
	} else {
%>
	로그인 해야합니다... <br />
	<a href="<%= application.getContextPath() + "/cookie/login"%>">로그인하러 가기</a>
<% } %>
</body>
</html>