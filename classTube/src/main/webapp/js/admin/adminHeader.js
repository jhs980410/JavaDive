function setActive(element) {
    console.log("클릭된 메뉴 항목:", element);  // 클릭된 메뉴 확인
    // 모든 nav-link에서 active 클래스를 제거
    const links = document.querySelectorAll('.nav-link');
    links.forEach(link => {
        link.classList.remove('active');
    });

    // 클릭한 메뉴 항목에 active 클래스를 추가
    element.classList.add('active');
}