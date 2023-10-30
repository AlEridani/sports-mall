<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="<c:url value="/resources/js/headers.js" />"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" crossorigin="anonymous">
<link href="<c:url value="/resources/css/headers.css" />" rel="stylesheet">
	



	<main>
		<h1><a href="${pageContext.request.contextPath}/index">스포츠용품 쇼핑몰</a></h1>
	<div class="header-container">
    	<ul class="nav-list">
        	<li><a href="${pageContext.request.contextPath}/product/list">메뉴</a></li>
       		<li><a href="#">들어</a></li>
        	<li><a href="${pageContext.request.contextPath}/product/productListTest">갈곳</a></li>
        	<li><a href="${pageContext.request.contextPath}/qnaBoard/qnaBoard">QNA</a></li>
    	</ul>
    
    <div class="search-bar">
		<form action="${pageContext.request.contextPath}/search" method="get">
        <input type="text" placeholder="검색창">
        <button id="searchBtn" class="menu-button">
        	<img src="<c:url value="/resources/img/search_icon.png" />" alt="검색" >
        </button>
	</form>
    </div>
    
  	<div class="menu">
    <button class="menu-button" type="button">
        <img src="<c:url value='/resources/img/user-logo.png' />" alt="메뉴아이콘" />
    </button>
    <div class="dropdown-menu dropdown-content dropdown-menu-right" aria-labelledby="dropdownMenuButton">
        <sec:authorize access="isAnonymous()">
            <a class="dropdown-item" href="#" onclick="targetURL()">로그인</a>
            <a class="dropdown-item" href="${pageContext.request.contextPath}/member/register">회원가입</a>
        </sec:authorize>
        <a class="dropdown-item" href="#">주문내역</a>
        <a class="dropdown-item" href="${pageContext.request.contextPath}/member/mypage">내 정보</a>
        <a class="dropdown-item" href="#">고객센터</a>
        <sec:authorize access="isAuthenticated()">
            <div class="dropdown-divider"></div>
            <form action="${pageContext.request.contextPath}/logout" method="post">
                &nbsp;&nbsp;<input type="submit" class="btn btn-danger btn-block" value="로그아웃" />
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </sec:authorize>
    </div>
</div>
	</div>
</main>

