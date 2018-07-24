<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL (1) 연산자</title>
</head>
<body>

<%-- 
	EL : 웹 컨테이너 (서블릿 컨테이너 : tomcat) 이 있으면 해석이 가능
	${ } 구문안에 표현식을 기술하는 방법으로 사용
	
	산술, 논리 연산 수행 가능
	객체를 편리하게 . 연산자로 접근 가능
	
	null 처리가 자동으로 이루어짐
--%>

<h3>EL 의 산술 연산자</h3>

<pre>
1. EL의 덧셈   : 10 + 55 = ${10 + 55}
2. EL의 뺄셈   : 10 - 55 = ${10 - 55}
3. EL의 곱셈   : 10 x 55 = ${10 * 55}
3. EL의 나눗셈 : 10 / 55 = ${10 / 55}
3. EL의 나머지 : 10 % 55 = ${10 % 55}
</pre>

<h3>EL 의 관계 연산자</h3>
<pre>
1. ~ 보다 크다(&gt;) : 10 &gt; 55 = ${10 > 55}, ${10 gt 55}
2. ~ 보다 작다(&lt;) : 10 &lt; 55 = ${10 < 55}, ${10 lt 55}
3. ~ 보다 크거나 같다(&ge;) : 10 &ge; 55 = ${10 >= 55}, ${10 ge 55}
4. ~ 보다 작거나 같다(&le;) : 10 &le; 55 = ${10 <= 55}, ${10 le 55}
5. ~ 과 같다(==) : 10  ==  55 = ${10 == 55}, ${10 eq 55} 
6. ~ 과 같지않다(!=) : 10  !=  55 = ${10 != 55}, ${10 ne 55}
</pre>

<h3>EL 의 논리 연산자</h3>
<pre>
1. 논리 AND : true && false = ${true && false}, ${true and false}
2. 논리 OR  : true || false = ${true || false}, ${true or false}
3. 논리 NOT : !true = ${not true}
              !false = ${not false}
</pre>

</body>
</html>