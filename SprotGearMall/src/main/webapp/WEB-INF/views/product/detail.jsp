<%@page import="edu.spring.mall.domain.ProductVO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/includes/header.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${product.productName}</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css" rel="stylesheet">
    <link href="<c:url value='/resources/css/detail.css' />" rel="stylesheet">
    <script src="<c:url value='/resources/js/detail.js' />"></script>
    <script>
        var isLiked = ${isLiked}; 
    </script>
    <style type="text/css">
   .small-column {
    width: 12%; 
   
}

.table-head tr th{
text-align: center;

}
.my-column{
 white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}

.accordion-content {

}

.large-column {
    width: 52%;

}

.pre-line {
    white-space: pre-line;
}

.hidden-row {
    display: none;
}

.page-item.active .page-link {
    background-color: #007bff; 
    color: white; 
    font-weight: bold;
}
    </style>
</head>
<body>

    <div class="container">
        <!-- 이미지와 물건 정보 -->
        <div class="row">
            <!-- 이미지 들어가는곳 -->
            <div class="col-md-6">
                <div style="height: 400px;">
               
                    <img src="https://storage.googleapis.com/edu-mall-img/${product.productImgPath }" alt="Product Image" class="img-fluid h-100">
                </div>
            </div>
            <!-- 물건 정보 -->
            <div class="col-md-6">
                <h1>${product.productName}</h1>
                <p class="h6 my-2">제조사 : ${product.productMaker}</p>
                <hr>
                <p class="price h4 my-2">판매가 : <fmt:formatNumber value="${product.productPrice}" type="number" pattern="#,###"/>원</p>
                <hr>
                <p>리뷰수 <b>${reviewCount}</b><span class="dot"> 사용자 총 평점 <b>${avg}/5</b></span></p>
                <!-- 버튼 컨테이너 -->
                <div class="d-flex justify-content-between align-items-center mt-4 gx-2">
                    <!-- 구매하기 버튼 -->
                    <div class="col-6 px-0">
                        <a href="payment?productId=${product.productId}" class="btn btn-success btn-lg w-100">구매하기</a>
                    </div>
                    <!-- 좋아요 버튼 -->
                    <div class="col-3 px-1">
                        <button class="like-btn btn btn-outline-danger btn-lg w-100">
                            <span class="heart"></span>
                        </button>
                    </div>
                    <!-- 장바구니 버튼 -->
                    <div class="col-3 px-1">
                        <button class="btn btn-outline-primary btn-lg w-100" id="addToCart"><i class="bi bi-cart3"></i></button>
                    </div>
                </div>
            </div>
        </div>
        <!-- 상품 상세 정보 탭 -->
       <ul class="nav nav-tabs">
  			<li class="nav-item col-md-4 px-0">
    			<a class="nav-link active text-dark" data-bs-toggle="tab" href="#description">상품 상세설명</a>
  			</li>
  			<li class="nav-item col-md-4 px-0">
    			<a class="nav-link text-dark" data-bs-toggle="tab" href="#reviews">상품 리뷰</a>
  			</li>
  			<li class="nav-item col-md-4 px-0">
    			<a class="nav-link text-dark" data-bs-toggle="tab" href="#inquiry">상품 문의</a>
  			</li>
		</ul>


        <div class="tab-content">
            <!-- 상품 설명 -->
            <div class="tab-pane container active" id="description">
                ${product.productContent }
            </div>
            <!-- 리뷰 -->
            <div class="tab-pane container fade" id="reviews">
				별점 평균 : ${avg} <br>
                전체 리뷰(${reviewCount}) <br>
                <hr>
                <c:forEach var="reviewList" items="${review}">
                  	<div id="review-${reviewList.reviewId}">
                        <fmt:formatDate value="${reviewList.reviewCreatedDate}" pattern="yy.MM.dd" var="formattedDate"/>
                        ${reviewList.reviewRating} 점<br>
                        ${fn:substring(reviewList.memberId, 0, 3)}<c:forEach begin="1" end="${fn:length(reviewList.memberId) - 3}" var="i">*</c:forEach>
                        ${formattedDate}<br>
                        ${reviewList.reviewContent}<br>
       					<sec:authorize access="hasRole('ROLE_ADMIN')">
                    	<button class="btn btn-danger reviewDelete" data-review-id="${reviewList.reviewId }">삭제</button>
						</sec:authorize>                    
                    </div>
                    <hr>
                </c:forEach>
            </div>
            <!-- 상품 문의 -->
            <div class="tab-pane container fade my-column" id="inquiry">
            <table class="table">
            	<thead class= "table-head">
            	<tr>
            	<th class="small-column">문의유형</th>
            	<th class="small-column">답변상태</th>
            	<th class="large-column">문의/답변</th>
            	<th class="small-column">작성자</th>
            	<th class="small-column">작성일</th>
            	</tr>
            	</thead>
            	<tbody>
            	<c:forEach var="qna" items="${qnaList }">
   				 <tr data-target="#accordion${qna.prdQnaId}" class="accordion-toggle">
            			<td style="text-align: center;">${qna.prdQnaCategory }</td>
            			<c:if test="${qna.prdQnaState == 'Y'}" >
            			<td style="text-align: center;"><span class="state">답변완료</span></td>
            			</c:if>
            			<c:if test="${qna.prdQnaState == 'N'}" >
            			<td style="text-align: center;"><span class="state">미답변</span></td>
            			</c:if>
            			 <c:choose>
        					<c:when test="${qna.prdQnaSecret == 0}">
           					 <td class="accordion-content">${qna.prdQnaContent}</td>
        					</c:when>
        					<c:when test="${qna.prdQnaSecret == 1 && (isAdmin || qna.memberId == principal)}">
            				<td><i class="fa-solid fa-lock-open accordion-content"></i>${qna.prdQnaContent}</td>
        					</c:when>
       						 <c:otherwise>
            				<td class="no-click accordion-content"><i class="fa-solid fa-lock"></i>비밀글입니다</td>
        					</c:otherwise>
    					</c:choose>
    					<c:choose>
    					<c:when test="${qna.memberId == principal }">
    					<td>${qna.memberId }</td>
    					</c:when>
    					<c:otherwise>
            			<td>${fn:substring(qna.memberId, 0, 3)} 
            			<c:forEach begin="1" end="${fn:length(qna.memberId) - 3}" var="i">*</c:forEach></td>
            			</c:otherwise>
            			</c:choose>
            			<fmt:formatDate value="${qna.prdQnaCreatedDate }" pattern="yy.MM.dd" var="qnaDate"/>
            			<td style="text-align: center;">${qnaDate }</td>
            		</tr>
            		   <tr class="hidden-row">
        <td colspan="5">
            <div id="accordion${qna.prdQnaId}" class="accordion-collapse collapse">
                <div class="accordion-body pre-line">
                    <!-- Q&A 상세 내용 -->
                    ${qna.prdQnaContent}<br><br>
                   <sec:authorize access="hasRole('ROLE_ADMIN')">
                   <!-- 대댓글 기능 -->
                        <button class="btn btn-secondary" data-review-id="${qna.prdQnaId }">답변</button>
                    	<button class="btn btn-danger" id="qnaDelete" onclick="qnaDelete()" data-qna-id="${qna.prdQnaId }">삭제</button>
					</sec:authorize>    
                </div>
            </div>
        </td>
    </tr>
            		</c:forEach>
            	</tbody>
            </table>
            <div style="text-align : right;">
           		<button class="btn btn-outline-secondary" onclick="location.href='/mall/qnaBoard/qnaBoard'">고객센터 문의하기</button>
           		<button class="btn btn-secondary" onclick="openPrdQnaPopup()">상품 문의하기</button>
           	</div>
           	<!-- 버튼 배치 -->
           	<nav id="nav">
				<ul class="pagination justify-content-center">
					<li  class="page-item"><a class="page-link" href="#">이전</a></li>
					<li class="page-item active"><a class="page-link" href="#">1</a></li>
					<li class="page-item"><a class="page-link" href="#">2</a></li>	
					<li class="page-item"><a class="page-link" href="#">3</a></li>	
					<li class="page-item"><a class="page-link" href="#">다음</a></li>
				</ul>
			</nav>
            </div>
        </div>
    </div>

    <a href="update?productId=${product.productId}&page=${page}"><input type="button" value="상품 수정"></a>
    <form action="delete" method="POST">
        <input type="hidden" id="productId" name="productId" value="${product.productId}">
        <input type="hidden" id="memberId" name="memberId" value="${pageContext.request.userPrincipal.name}">
        <input type="submit" value="상품 삭제">
    </form>
    
<script type="text/javascript">
    document.getElementById('addToCart').addEventListener('click', function () {
    // 필요한 데이터 가져오기
    var memberId = "${pageContext.request.userPrincipal.name}";
    var productId = "${product.productId}";
    var productPrice = "${product.productPrice}";
    var productQuantity = '1';
    var csrfToken = $("#csrfToken").val();

    // 서버로 보낼 데이터 객체 생성
    var obj = {
      'memberId' : memberId,
      'productId' : productId,
      'productPrice' : productPrice,
      'productQuantity' : productQuantity
    };
    console.log(obj);

    // 제품을 장바구니에 추가하기 위해 서버로 AJAX 요청 보내기
    $.ajax({
      type: 'POST',
      url: '../cart/cartlists', // 서버 엔드포인트와 일치하도록 URL 업데이트
      headers : {
			'Content-Type' : 'application/json',
			'X-CSRF-TOKEN': csrfToken
		},
      data: JSON.stringify(obj),
      contentType: 'application/json',
      success: function (result) {
    	console.log(result);
    	if(result == 1) {
        // 서버에서의 응답 처리 (예: 성공 메시지 표시)
        alert('제품이 성공적으로 장바구니에 추가되었습니다.');
    	} else {
    	alert('에러');
    	}
      }
    });
  });
    

   
    function openPrdQnaPopup() {
    	var productId = $('#productId').val();
        var url = "/mall/product/prdQna?productId="+ productId;
        var windowName = "상품 문의하기";
        var windowSize = "width=800, height=600";
        console.log("productId :", productId );

        window.open(url, windowName, windowSize);
    }
    
    document.querySelectorAll('.accordion-toggle').forEach(function(row) {
        row.addEventListener('click', function(event) {
            // event.target을 사용하여 실제 클릭된 요소 확인
            if (event.target.classList.contains('no-click')) {
                event.preventDefault(); // 기본 동작 중단
                return; // 이벤트 실행 중단
            }

            var targetId = this.getAttribute('data-target');
            var accordion = document.querySelector(targetId);
            var accordionRow = accordion.closest('tr');
            accordion.classList.toggle('show');

            if (accordion.classList.contains('show')) {
                accordionRow.classList.remove('hidden-row');
            } else {
                accordionRow.classList.add('hidden-row');
            }
        });
    });
    
    document.addEventListener("DOMContentLoaded", function() {
        // 특정 클래스 또는 id가 있는 요소를 대상으로 선택합니다.
        var elements = document.querySelectorAll('.accordion-content'); 

        elements.forEach(function(element) {
            var htmlContent = element.innerHTML;
            var brIndex = htmlContent.indexOf('<br>');

            if (brIndex !== -1) {
                element.innerHTML = htmlContent.substring(0, brIndex);
            }
        });
    });
    
    $('.pagination .page-link').on('click', function() {
        $('.pagination .page-item').removeClass('active'); 
        $(this).parent().addClass('active'); 
    });
    
    function qnaDelete() {
        var reviewId = $(this).data('qna-id'); // data-review-id 속성 값 가져오기
        var prdQnaId = $('#prdQnaId').val();
        var csrfToken = $('#csrfToken').val();
        var headers =  {
			'Content-Type' : 'application/json',
			'X-CSRF-TOKEN': csrfToken
		}
        confirm("문의를 삭제하겠습니까?");
        if(!confirm){
        	return;
        }
        $.ajax({
            type: 'delete',
            url: deleteQna,
            headers: headers,
            data: JSON.stringify({
                "prdQnaId": prdQnaId
            }),
            statusCode:{
                200: (result)=>{
                    alert("삭제성공");
                    $('#accordion' + reviewId).closest('tr').remove();
                },
                400: (result)=>{
                    alert("삭제 실패")
                }
            },
            error: function(xhr, status, error) {
        console.error('오류 발생:', error);
    }
        })//end ajax

    }//end qnaDelete
    

</script>
</body>
</html>