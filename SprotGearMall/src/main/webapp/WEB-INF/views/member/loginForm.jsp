<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	<%@ include file="/WEB-INF/views/includes/headerTest.jsp" %>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" 
 rel="stylesheet" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">

<link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" 
 rel="stylesheet" crossorigin="anonymous">
</head>
<body>

	
	
<div class="container">
      <form class="form-signin" method="POST" action="../login">
        <h2 class="form-signin-heading"><a href="/mall/index">로그인</a></h2>
        
       <c:if test="${not empty error}"> 
       <div class="alert alert-danger" role="alert">로그인 실패하였습니다</div>
       </c:if>
       
        <p><input type="text" id="username" name="memberId" class="form-control" placeholder="아이디" required="required"></p>
        <p><input type="password" id="password" name="password" class="form-control" placeholder="비밀번호" required="required"></p>
        <label for="rememberMe">로그인 유지 여부</label>
        <input type="checkbox" id="remember-me" name="remember-me"/><br>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

        <button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>
      </form>
      <div class="container" style="text-align : center;">
      		<a href="register" >회원가입</a>
      </div>
</div>
</body>
</html>