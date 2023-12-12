<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ include file="/WEB-INF/views/includes/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<meta charset="UTF-8">
<title>글 작성 페이지</title>
</head>
<body>
<div class="container-fluid">
 <div class="row">
  <!-- 사이드바 메뉴 -->
  <jsp:include page="../includes/qna-sidebar.jsp" />
   <!-- 메인 콘텐츠 -->
   <div class="col-md-10">
   <h1>QNA게시판</h1>
	<h2>글 작성 페이지</h2>
	<form id="qnaUpdateForm" action="register" method="POST">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<div>
			<p>제목 : </p>
			<input type="text" name="qnaBoardTitle" placeholder="제목 입력" required>
		</div>
		<div>
			<p>작성자 : </p>
			<input type="text" name="memberId" value="${pageContext.request.userPrincipal.name}" readonly="readonly">
		</div>
		<div>
			<p>내용 : </p>
			<textarea rows="20" cols="120" name="qnaBoardContent" placeholder="내용 입력"></textarea>
		</div>
		<div>
			<input type="submit" value="등록" onclick="validateForm()">
		</div>
	</form>
</div>
</div>
</div>



<script type="text/javascript">
function validateForm() {
    var content = $('#qnaUpdateForm textarea[name="qnaBoardContent"]').val();
    if (!content.trim()) {
        alert('내용을 입력하세요.');
        event.preventDefault(); // 폼 제출 막기
    }
}
</script>
</body>
</html>