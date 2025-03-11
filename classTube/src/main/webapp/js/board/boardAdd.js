var subBtn = document.getElementById("SubBtu");

if (subBtn) {
    subBtn.addEventListener("click", function (event) {
        // 확인 창 표시
        var isConfirmed = confirm("등록하시겠습니까?");

        if (isConfirmed) {
            alert("등록되었습니다.");
            // 폼이 제출되도록 기본 동작 유지
        } else {
            alert("등록이 취소되었습니다.");
            event.preventDefault(); // 기본 제출 동작 방지
        }
    });
} else {
    console.error("SubBtu 버튼을 찾을 수 없습니다.");
}