<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="JavaDive.dto.board.BoardDto"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 상세페이지</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/board/boardView.css">
</head>
<body>
	<%@ include file="../common/sideBar.jsp"%>

	<div class="container">
		<h2>게시판 상세페이지</h2>
		<c:set var="contextPath" value="${pageContext.request.contextPath}" />
		<c:set var="boardDto" value="${sessionScope.boardDto}" />
		<c:set var="recentPostId" value="${sessionScope.recentPostId}" />
		<div class="form-group"></div>
		<div class="form-group">
			<div class="member">
				작성자 : ${boardDto.writer}
				<c:if test="${not empty boardDto.createDate}">
					<span class="date"> 작성일 : <fmt:formatDate
							value="${boardDto.createDate}" pattern="yyyy-MM-dd HH:mm:ss" />
					</span>
				</c:if>
				<c:set var="boardDto" value="${sessionScope.boardDto}" />
				<c:set var="memberDto" value="${sessionScope.member}" />

				<!-- 현재 로그인한 유저가 게시글 작성자인 경우 -->
				<c:if
					test="${not empty memberDto and memberDto.no == boardDto.memberNo}">
					<button class="updatebutton"
						onclick="location.href='/classTube/boardUpdate?postId=${boardDto.noteNo}'">
						수정</button>
					<button class="deletebutton"
						onclick="location.href='/classTube/boardDelete?postId=${boardDto.noteNo}'">
						삭제</button>

				</c:if>
			</div>
		</div>
		<div class="form-group">
			<span>제목</span> <span class="title">${boardDto.title}</span>
		</div>

		<div class="form-group">
			<span>내용</span> <span class="content">${boardDto.content}</span>
		</div>

		<c:set var="commetnlist" value="${sesionScope.commentList} " />
		<!--댓그리스트-->
		<!-- ✅ 댓글 영역 추가 -->
		<div class="comment-section">
			<h3>댓글</h3>

			<c:forEach var="comment" items="${commentList}">
				<div class="comment">
					<span class="comment-writer">${comment.commentWriter}</span>
					<!-- 댓글 작성자 -->
					<span class="comment-content"> ${comment.commentContent} </span>
					<!-- 댓글 내용 -->
					<div class="comment-buttons">
						<c:if
							test="${not empty memberDto and memberDto.no eq comment.memberNo}">
							<button id="updateConment"
								onclick="openEditWindow(${comment.commentNo},${boardDto.noteNo})">수정</button>
						</c:if>
						<c:if
							test="${not empty memberDto and memberDto.no eq comment.memberNo}">
							<form
								action="${pageContext.request.contextPath}/BoardCommentDelete"
								method="post" onsubmit="return confirmDelete()">
								<input type="hidden" name="commentId"
									value="${comment.commentNo}"> <input type="hidden"
									name="postId" value="${boardDto.noteNo}">
								<button id="deleteConment" type="submit">삭제</button>
							</form>

						</c:if>

						<span class="comment-date"> <fmt:formatDate
								value="${comment.createAt}" pattern="yyyy-MM-dd HH:mm:ss" />
						</span>
						<!-- 댓글 작성일 -->
					</div>
					</div>
			</c:forEach>


			<form action="${pageContext.request.contextPath}/BoardCommentAdd"
				method="post">
				<input type="hidden" name="postId" value="${boardDto.noteNo}">
				<textarea name="content" placeholder="댓글을 입력하세요" required></textarea>
				<button class="commentadd" type="submit">댓글 등록</button>
			</form>

		</div>

		<!-- ✅ 버튼 그룹 -->
		<div class="button-group">
			<button class="but-view"
				onclick="location.href='/classTube/boardList'">게시글 목록</button>

		</div>



	</div>
	<script>
        var contextPath = "${pageContext.request.contextPath}";

    </script>

	<script src="${pageContext.request.contextPath}/js/board/boardView.js"></script>
</body>
</html>
