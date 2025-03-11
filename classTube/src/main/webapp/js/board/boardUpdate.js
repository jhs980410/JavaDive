var subBtn = document.getElementById("SubBtn");

if (subBtn) {
       subBtn.addEventListener("click", function (event) {
           var isConfirmed = confirm("수정하시겠습니까?");

           if (isConfirmed) {
               alert("게시글이 수정되었습니다.");
           } else {
               alert("수정이 취소되었습니다.");
               event.preventDefault(); // 기본 제출 동작 방지
           }
       });
   } else {
       console.error("SubBtu 버튼을 찾을 수 없습니다.");
   }