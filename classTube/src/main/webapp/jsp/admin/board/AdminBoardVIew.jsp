<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin/common/adminHeader.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin/board/adminBoardView.css">

</head>
<body>
	<%@ include file="../common/adminHeader.jsp"%>


	<div class="container">
		<h2>관리자 게시판 상세</h2>

		<c:set var="boardDto" value="${sessionScope.boardDto}" />
		<c:set var="memberDto" value="${sessionScope.member}" />
		<c:set var="recentPostId" value="${sessionScope.recentPostId}" />
		<div class="form-group"></div>
		<div class="form-group">
			<div class="member">
				작성자 : ${boardDto.writer}
				<c:if test="${not empty boardDto.createDate}">
					<span class="date"> 작성일 : <fmt:formatDate value="${boardDto.createDate}" pattern="yyyy-MM-dd HH:mm:ss" /> </span>
				</c:if>
			<button class="updatebutton"
  onclick="location.href='/classTube/admin/boardUpdate?postId=${boardDto.noteNo}'">
  수정
</button>
			</div>
		</div>
		<div class="form-group">
			<span>제목</span> <span class="title">${boardDto.title}</span>
		</div>

		<div class="form-group">
			<span>내용</span> <span class="content">${boardDto.content}</span>
		</div>


		<!-- ✅ 댓글 영역 추가 -->
		<div class="comment-section">
			<h3>댓글</h3>
			<c:forEach var="comment" items="${commentList}">
					<div class="comment">
						<span class="comment-writer">${comment.commentWriter}</span>
						<!-- 댓글 작성자 -->
						<span class="comment-content">${comment.commentContent}</span>
						<div class="comment-buttons">
						<c:if
							test="${not empty memberDto and memberDto.no eq comment.memberNo}">
							<button id="updateConment"
								onclick="openEditWindow(${comment.commentNo},${boardDto.noteNo})">수정</button>
						</c:if>
							<form
								action="${pageContext.request.contextPath}/admin//BoardCommentDelete"
								method="post">
								<input type="hidden" name="commentId"
									value="${comment.commentNo}"> <input type="hidden"
									name="postId" value="${boardDto.noteNo}">
								<button id="deleteConment" type="submit">삭제</button>
							</form>
						</div>
						<!-- 댓글 내용 -->
						<span class="comment-date"><fmt:formatDate value="${comment.createAt}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
						<!-- 댓글 작성일 -->
					</div>
				</c:forEach>
				<form action="${pageContext.request.contextPath}/admin//BoardCommentAdd" method="post">
				<input type="hidden" name="postId" value="${boardDto.noteNo}">
				<textarea name="content"  placeholder="댓글을 입력하세요" required></textarea>
				<button class = "commentadd" type="submit">댓글 등록</button>
			</form>
		</div>

		<!-- ✅ 버튼 그룹 -->
		<div class="button-group">
			<button class="but-view"
				onclick="location.href='/classTube/admin/boardList'">게시글 목록</button>

		</div>
		<script src="${pageContext.request.contextPath}/js/admin/adminBoardView.js"></script>
</body>
</html>