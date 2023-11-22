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


.trsize {
    min-height: 50px; /* 원하는 최소 높이 */
    height: auto;
    overflow: hidden; /* 내용이 넘칠 경우 숨김 처리 */
    
    
}

.trsize td {
    padding: 10px; /* 원하는 패딩 값 */
}
.accordion-collapse {
    height: auto;
    overflow: hidden;
}

.table-head tr th{
text-align: center;

}
.my-column{
 white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}



.large-column {
    width: 52%;

}

.pre-line {
	height: auto;
    white-space: pre-line;
    overflow: hidden; /* 내용이 넘칠 경우 숨김 처리 */
    
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
            	<tbody id="prdQnaBody">
            	<c:forEach var="qna" items="${qnaList }">
   				 <tr id="accordion-${qna.qna.prdQnaId}" data-target="#accordion${qna.qna.prdQnaId}" class="accordion-toggle">
            			<td style="text-align: center;">${qna.qna.prdQnaCategory }</td>
            			<c:if test="${qna.qna.prdQnaState == 'Y'}" >
            			<td style="text-align: center;"><span class="state">답변완료</span></td>
            			</c:if>
            			<c:if test="${qna.qna.prdQnaState == 'N'}" >
            			<td style="text-align: center;"><span class="state">미답변</span></td>
            			</c:if>
            			 <c:choose>
        					<c:when test="${qna.qna.prdQnaSecret == 0}">
           					 <td class="accordion-content">${qna.qna.prdQnaContent}</td>
        					</c:when>
  							<c:when test="${qna.qna.prdQnaSecret == 1 && (qna.qna.admin || qna.qna.author)}">
            				<td><i class="fa-solid fa-lock-open accordion-content"></i>${qna.qna.prdQnaContent}</td>
        					</c:when>
       						 <c:otherwise>
            				<td class="no-click accordion-content"><i class="fa-solid fa-lock"></i>비밀글입니다</td>
        					</c:otherwise>
    					</c:choose>
    					<td>${qna.qna.memberId }</td>
            			<fmt:formatDate value="${qna.qna.prdQnaCreatedDate }" pattern="yy.MM.dd" var="qnaDate"/>
            			<td style="text-align: center;">${qnaDate }</td>
            		</tr>
            		<!-- 아코디언 -->
    <tr id="accordionContent-${qna.qna.prdQnaId }"class="hidden-row trsize">
        <td colspan="5">
            <div id="accordion${qna.qna.prdQnaId}" class="accordion-collapse collapse">
                <div class="accordion-body pre-line">
                    <!-- Q&A 상세 내용 -->        
                   <c:if test="${qna.qna.prdQnaSecret == 0 || qna.qna.admin || qna.qna.author}">
                     ${qna.qna.prdQnaContent}
                    </c:if>
                    <!-- 대댓글 시작할거임 -->
                    <c:if test="${qna.qna.prdQnaState == 'Y' }">
                    <hr>
                    <div class="pre-line">
                    ${qna.reply.pqrContent }
                    </div>
                    </c:if>
                    
                   <sec:authorize access="hasRole('ROLE_ADMIN')">
                   <!-- 대댓글 기능 -->
                   <div class="d-flex justify-content-end">
                        <button class="btn btn-secondary" onclick="answerArea(${qna.qna.prdQnaId })">답변</button>
                    	<button class="btn btn-danger" onclick="qnaDelete(this)" data-qna-id="${qna.qna.prdQnaId }">삭제</button>
                    	</div>
					</sec:authorize>    
                </div>
            </div>
        <div id="answer-${qna.qna.prdQnaId}" class="answer">
        </div>
        </td>
    </tr>
            		</c:forEach>
            	</tbody>
            </table>
            <div style="text-align : right;">
           		<button class="btn btn-outline-primary" onclick="location.href='/mall/qnaBoard/qnaBoard'">고객센터 문의하기</button>
           		<button class="btn btn-primary" onclick="openPrdQnaPopup()">상품 문의하기</button>
           	</div>
           	<!-- 버튼 배치 -->
           	<nav id="nav">
				<ul class="pagination justify-content-center">
				 <c:if test="${pageMaker.hasPrev }">
					<li class="page-item"><a class="page-link" href="javascript:void(0);" data-page="${pageMaker.startPageNo - 1 }">이전</a></li>
					</c:if>
					<c:forEach begin="${pageMaker.startPageNo }" end="${pageMaker.endPageNo }" var="num">
					<li class="page-item ${num == pageMaker.criteria.page ? 'active' : ''}">
					<a class="page-link" href="javascript:void(0);" data-page="${num }">${num }</a></li>
					</c:forEach>
				<c:if test="${pageMaker.hasNext }">
					<li class="page-item"><a class="page-link" href="javascript:void(0);" data-page="${pageMaker.endPageNo + 1 }">다음</a></li>
				</c:if>
					
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

$(()=>{
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
	
	$(document).on('click', '.page-link', function(e) {
	    e.preventDefault(); // 기본 동작(링크 이동) 방지
	    var pageNum = $(this).data('page');
	    var productId = $('#productId').val();
	    console.log("pageNum",pageNum);
	    loadPageContent(productId, pageNum);
	});

})//end document.ready
    

   
    function openPrdQnaPopup() {
    	var productId = $('#productId').val();
        var url = "/mall/product/prdQna?productId="+ productId;
        var windowName = "상품 문의하기";
        var windowSize = "width=800, height=600";
        console.log("productId :", productId );
        window.open(url, windowName, windowSize);
    }
    

$(document).on('click', '.accordion-toggle', function(event) {
    if ($(event.target).hasClass('no-click')) {
        event.preventDefault(); 
        return; 
    }

    var targetId = $(this).attr('data-target');
    var accordion = $(targetId);
    var accordionRow = accordion.closest('tr');

    // 현재 클릭된 아코디언이 닫혀있는 경우에만 실행
    if (!accordion.hasClass('show')) {
        // 모든 아코디언 닫기
        $('.accordion-collapse').removeClass('show');
        $('.accordion-collapse').closest('tr').addClass('hidden-row');

        // 클릭된 아코디언 열기
        accordion.addClass('show');
        accordionRow.removeClass('hidden-row');
    } else {
        // 클릭된 아코디언이 이미 열려있는 경우, 닫기
        accordion.removeClass('show');
        accordionRow.addClass('hidden-row');
    }
});
    
	   function qnaDelete(element) {
	        var prdQnaId = parseInt($(element).data('qna-id'),10);
	        var csrfToken = $('#csrfToken').val();
	        
	        var headers =  {
				'Content-Type' : 'application/json',
				'X-CSRF-TOKEN': csrfToken
			}
	        console.log('prdQnaId',prdQnaId);
	        console.log(typeof(prdQnaId));
	        confirm("문의를 삭제하겠습니까?");
	        if(!confirm){
	        	return;
	        }
	        $.ajax({
	            type: 'delete',
	            url: 'deleteQna',
	            headers: headers,
	            data: JSON.stringify({
	                "prdQnaId": prdQnaId
	            }),
	            statusCode:{
	                200: (result)=>{
	                    alert("삭제성공");
	                    $('#accordion-' + prdQnaId).closest('tr').remove();
	                    $('#accordionContent-' + prdQnaId).remove()
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
	    
	    function loadPageContent(productId,pageNum){
	        var csrfToken = $('#csrfToken').val();
	        var url = 'prdQna?page=' + pageNum;
	        var headers={
	            'Content-Type' : 'application/json',
	    		'X-CSRF-TOKEN': csrfToken
	        }
	        $.ajax({
	            type: 'GET',
	            url: 'prdQnaPaging?page=' + pageNum + '&productId=' + productId,
	            headers : headers,
	            success: (result) =>{
	            	console.log("result", result)
	            	var qnaList = result.qnaList;
	            	var pageMaker = result.pageMaker;
	            	var isAdmin = result.isAdmin;
	                updateTableBody(qnaList, isAdmin);
	                updatePageItem(pageMaker);
	            },
	            error:(error) =>{
	                alert("에러 발생")
	            }

	        })//end ajax 
	      }//end loadPagecOntent
 
	function updateTableBody(qnaList, isAdmin){
		 var newTbodyContent = '';
		 qnaList.forEach(function(list) {
			 
			 	console.log("QnAId:", list.qna.prdQnaId);
			    console.log("Reply:", list.reply);
		        newTbodyContent += '<tr id="accordion-' + list.qna.prdQnaId + '" data-target="#accordion' + list.qna.prdQnaId + '" class="accordion-toggle">';
		        newTbodyContent += '<td style="text-align: center;">' + list.qna.prdQnaCategory + '</td>';
		        
		        // 답변 상태에 따른 처리
		        if (list.qna.prdQnaState == 'Y') {
		            newTbodyContent += '<td style="text-align: center;"><span class="state">답변완료</span></td>';
		        } else {
		            newTbodyContent += '<td style="text-align: center;"><span class="state">미답변</span></td>';
		        }

		        // 비밀글
		         if (list.qna.prdQnaSecret == 0) {
        				newTbodyContent += '<td class="accordion-content">' + list.qna.prdQnaContent + '</td>';
    			} else if (list.qna.prdQnaSecret == 1 &&(isAdmin ||list.qna.isAuthor)) {
        				newTbodyContent += '<td><i class="fa-solid fa-lock-open accordion-content"></i>' + list.qna.prdQnaContent + '</td>';
    			} else {
        			newTbodyContent += '<td class="no-click accordion-content"><i class="fa-solid fa-lock"></i>비밀글입니다</td>';
   				 }

		        // 작성자 정보 처리
		        // 컨트롤러에서처리함
		     	 newTbodyContent += '<td>' + list.qna.memberId +  '</td>';
		       

		        // 작성일 처리
		        newTbodyContent += '<td style="text-align: center;">' + formatDate(list.qna.prdQnaCreatedDate) + '</td>';


		        newTbodyContent += '</tr>';
		        // 아코디언 내용
		        newTbodyContent += '<tr id="accordionContent-' + list.qna.prdQnaId + '" class="hidden-row trsize">';
		        newTbodyContent += '<td colspan="5">';
		        newTbodyContent += '<div id="accordion' + list.qna.prdQnaId + '" class="accordion-collapse collapse">';
		        newTbodyContent += '<div class="accordion-body pre-line">';
		        newTbodyContent += list.qna.prdQnaContent + '<br><br>';
		        // 관리자 버튼 추가도 권한이 필요함
		        if(isAdmin){
		        	newTbodyContent += '<div class="d-flex justify-content-end">'
		        	newTbodyContent += '<button class="btn btn-secondary" data-qna-id="' + list.qna.prdQnaId + '">답변</button>';
		            newTbodyContent += '<button class="btn btn-danger" onclick="qnaDelete(this)" data-qna-id="' + list.qna.prdQnaId + '">삭제</button>';
		            newTbodyContent += '</div>'
		        }
		        newTbodyContent += '</div></div></td></tr>';
		    });
		    $('#prdQnaBody').html(newTbodyContent);
	      }
	      
	function formatDate(timestamp) {
	    var date = new Date(timestamp);
	    var year = date.getFullYear().toString().substr(-2); // 년도의 마지막 두 자리
	    var month = ('0' + (date.getMonth() + 1)).slice(-2); // 월 (0부터 시작하므로 +1 필요)
	    var day = ('0' + date.getDate()).slice(-2); // 일

	    return year + '.' + month + '.' + day;
	}
	
	function updatePageItem(pageMaker){
		var newPageItem = "";
	    // 이전 버튼
	    if (pageMaker.hasPrev) {
	        newPageItem += '<li class="page-item" id="prevBtn"><a class="page-link" href="javascript:void(0);" data-page="' + (pageMaker.startPageNo - 1) + '">이전</a></li>';
	    }
	    // 페이지 번호
	    for (var num = pageMaker.startPageNo; num <= pageMaker.endPageNo; num++) {
	        var isActive = num === pageMaker.criteria.page ? 'active' : '';
	        newPageItem += '<li class="page-item ' + isActive + '"><a class="page-link" href="javascript:void(0);" data-page="' + num + '">' + num + '</a></li>';
	    }
	    // 다음 버튼
	    if (pageMaker.hasNext) {
	        newPageItem += '<li class="page-item" id="nextBtn" ><a class="page-link" href="javascript:void(0);" data-page="' + (pageMaker.endPageNo + 1) + '">다음</a></li>';
	    }
	   // 추가
	    $('#nav .pagination').html(newPageItem);
	}
	
	function answerArea(qnaId){
		var answerDivId = "answer-" + qnaId;
		console.log('answerArea qnaId', qnaId);
		var answerBody = 
			'<div class="answer-input-area">' +
        	'<textarea class="form-control" id="answerText-' + qnaId + '" rows="3" placeholder="답변"></textarea>' +
       		'<button class="btn btn-primary mt-2" onclick="submitAnswer(' + qnaId + ')">답변하기</button>' +
    		'</div>';
		$('#' + answerDivId).html(answerBody);
	}
	
	function submitAnswer(qnaId){
		//내가 보내야하는거 내용, 멤버아이디(컨트롤러 처리),문의ID, productId
		console.log('submitAnswer qnaId', qnaId);
	}

</script>
</body>
</html>