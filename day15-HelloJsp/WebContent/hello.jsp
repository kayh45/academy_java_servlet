<%--
	1. JSP 주석 : JSP 가 Servlet으로 변환될 때 무시됨
	2. <%@      : Directive Tag : 현재 페이지에 대한 설정을 지정할 때 사용
			      이 페이지에서 필요한 import 등이 있으면 처리
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>01 Hello JSP</title>
</head>
<body>
	<%! 
	// 5. <%!		: Declaration Tag : 멤버 변수 선언, 메소드 선언이 들어가는 위치
	
	// 멤버 변수 선언
		private int result;
	// int 값 2개를 받아서 add() 하고 결과를 리턴하는 메소드 정의
		
		public int add(int x, int y) {
			return x + y;
		}
	%>

	<% 
	// 3. <% ... : Scriptlet Tag : JSP 안에서 순수 자바코드를 쓸 수 있는 태그
    //							   이 태그 안에는 완전한 자바 문장이 들어감
    //                             너무 많이 사용하면 가독성을 떨어뜨림
    //                             문장(statement) : ;으로 종료되는 한 줄
    //                                 (1) 할당문	: 변수에 값 저장
    //							       (2) 메소드 호출문 : object.toString()
    //								   (3) 제어구조 : if, while, for
    //								   (4) 지역 변수 선언문
    
    //							   문장이 아닌 것 : 메소드 선언, 클래스 선언 등)은 불가능

		int age = 55;
    	
    	List<Integer> ages = new ArrayList<>();
    	ages.add(12);
		ages.add(20);
		ages.add(37);
		ages.add(45);
		ages.add(age);
		
		for (int age2 : ages) {
			System.out.println("구성원의 나이 :" + age2);
	%>
	<!-- 여기에는 화면(브라우저)에 반복 출력할 HTML 을 구성 -->
	<%-- <%=   : Expression Tag : 변수 값 하나를 출력할 때 사용
	                              리턴이 있는 메소드 호출 구문 사용 가능
								  ; 을 쓰지 않는다.
	--%>
			구성원의 나이 : <%= age2 %> <br />
	<% 
		} // 스크립틀릿 안에서는 자바 주석 사용 가능
		// for 구조가 스클립틀릿 안에서 시작하므로
		// 닫을 때도 스크립틀릿 안에서 닫혀야 한다.
		
		for (int idx = 0; idx < 5; idx++) {

	%>
	<!-- Scriptlet 의 바깥 부분 출력할 HTML 을 구성할 수 있다 -->
			안녕하세요, JSP! 반복실행 <%= idx %> <br />
	<%
		}
	%>
	
	<% 
		// 메소드 선언된 add()를 사용
		result = add(100, 200);
	
	%>
	
		add(100, 200) = <%= result %>

</body>
</html>