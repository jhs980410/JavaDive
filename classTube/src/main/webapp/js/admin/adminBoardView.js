var subBtn = document.getElementById("SubBtu");

subBtn.addEventListener("click", function() {
	alert("게시글이 등록 되었습니다.");

});

var contextPath = "${contextPath}"

function openEditWindow(commentId, postId) {
	let basePath = window.location.pathname.includes("/admin") ? "/admin/CommentView" : "/CommentView";
	let url = "/classTube"+basePath + "?commentId=" + commentId + "&postId=" + postId;

	console.log("Final URL:", url);

	let popupOptions = "width=500,height=300,top=200,left=300,resizable=yes,scrollbars=yes";
	window.open(url, "editComment", popupOptions);
}



function confirmDelete() {
    return confirm("정말로 이 댓글을 삭제하시겠습니까?");
}


