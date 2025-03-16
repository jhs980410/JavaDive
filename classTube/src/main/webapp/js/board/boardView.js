var subBtn = document.getElementById("SubBtu");

subBtn.addEventListener("click", function() {
    alert("게시글이 등록 되었습니다.");
   
});

var contextPath = "${contextPath}"

    function openEditWindow(commentId,postId) {
  // JSP에서 컨텍스트 패스를 JavaScript 변수로 저장
        let url = contextPath + "/CommentView?commentId=" + commentId + "&postId=" + postId;
        let popupOptions = "width=500,height=300,top=200,left=300,resizable=yes,scrollbars=yes";
        window.open(url, "editComment", popupOptions);
    }

	function confirmDelete() {
	    return confirm("정말로 이 댓글을 삭제하시겠습니까?");
	}
	function confirmPostDelete(url) {
	    if (confirm("정말로 이 게시물을 삭제하시겠습니까?")) {
	        location.href = url;
	    }
	}



	 function commentDelete(commentId,postId) {
	// JSP에서 컨텍스트 패스를 JavaScript 변수로 저장
	      let url = contextPath + "/BoardCommentDelete?commentId=" + commentId + "&postId=" + postId;
	    
	  }