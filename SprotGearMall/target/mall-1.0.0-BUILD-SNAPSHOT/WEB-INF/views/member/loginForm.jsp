<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" 
 rel="stylesheet" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
  <link href="<c:url value="/resources/css/login.css" />" rel="stylesheet">

<link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" crossorigin="anonymous">
</head>
<body>
<header>
<%@ include file="/WEB-INF/views/includes/header.jsp" %>
</header>
	
	
<div class="container">
      <form class="form-signin" method="POST" action="../login">
        <h2 class="form-signin-heading"><a href="/mall/index">로그인</a></h2>
        
       <c:if test="${not empty error}"> 
       <div class="alert alert-danger" role="alert">로그인 실패하였습니다</div>
       </c:if>
		<c:if test="${not empty state}">
        <div class="alert alert-success" role="alert">회원가입에 성공했습니다</div>
       </c:if>
       
        <p><input type="hidden" id="redirect" name="redirect"  value="${uri }"></p>
        <p><input type="text" id="username" name="memberId" class="form-control" placeholder="아이디" required="required"></p>
        <p><input type="password" id="password" name="password" class="form-control" placeholder="비밀번호" required="required"></p>
        &nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" id="remember-me" class="form-check-input" name="remember-me"/>
        <label for="rememberMe">로그인 상태 유지</label>
      	<input type="hidden"name="${_csrf.parameterName}" value="${_csrf.token}"><br>

        <button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>
      </form>
          <!--소셜 로그인 넣을거임-->
    <div class="container" style="text-align : center;">
        <a href="/mall/oauth/naverOAuth" >
            <button class="socal-login-btn">
                <img src="<c:url value='/resources/img/naver-btn.png' />" alt="네이버로그인" />
            </button>
        </a>
         <a href="/mall/oauth/googleOAuth" >
            <button class="socal-login-btn">
                <img src="<c:url value='/resources/img/google-btn.png' />" alt="구글로그인" />
            </button>
        </a>
    </div>
      <div class="container" style="text-align : center;">
      		<a href="register" >회원가입</a>
      </div>
      
      <hr>

      </div>

<footer>
    <%@ include file="/WEB-INF/views/includes/footer.jsp" %>
</footer>

</body>
</html>