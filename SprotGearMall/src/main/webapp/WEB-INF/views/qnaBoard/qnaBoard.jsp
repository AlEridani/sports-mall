
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/views/includes/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
table, th, td {
	border-style : solid;
	border-width : 1px;
	text-align : center;
}

</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>QNA 게시판입니다</h1>
	
	<input type="hidden" id="memberId" name="memberId" value="${memberId }">

	<a href="qnaRegister?memberId=${memberId }"><input type="button" value="글 작성"></a>
	<hr>
	<table>
		<thead>
			<tr>
				<th style="width : 100px">게시글번호</th>
				<th style="width : 100px">작성자</th>
				<th style="width : 500px">게시글제목</th>
				<th style="width : 250px">작성일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="vo" items="${list }">
				<tr>
					<td>${vo.qnaBoardId }</td>
					<td>${vo.memberId }</td>
					<td><a href="qnaDetail?qnaBoardId=${vo.qnaBoardId }&page=${pageMaker.criteria.page}&memberId=${memberId }">${vo.qnaBoardTitle }</a></td>
					<fmt:formatDate value="${vo.qnaBoardCreatedDate }"
					pattern="yyyy-MM-dd HH:mm:ss" var="qnaBoardCreatedDate"/>
					<td>${qnaBoardCreatedDate }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<nav aria-label="Page navigation example">
	<ul class="pagination">
		<c:if test="${pageMaker.hasPrev }">
			<li  class="page-item"><a  class="page-link" href="qnaBoard?page=${pageMaker.startPageNo - 1 }">이전</a></li>
		</c:if>
		<c:forEach begin="${pageMaker.startPageNo }" end="${pageMaker.endPageNo }"
			var="num">
			<li class="page-item"><a class="page-link" href="qnaBoard?page=${num }">${num }</a></li>	
		</c:forEach>
		<c:if test="${pageMaker.hasNext }">
			<li class="page-item"><a class="page-link" href="qnaBoard?page=${pageMaker.endPageNo + 1 }">다음</a></li>
		</c:if>

	</ul>
	</nav>
</body>
</html>