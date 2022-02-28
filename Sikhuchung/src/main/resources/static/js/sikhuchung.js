function Slider(target, type) {
    // 상태
    let index = 1;
    let isMoved = true;
    const speed = 1000; // ms

    // 방향
    const transform = "transform " + speed / 1000 + "s";
    let translate = (i) => "translateX(-" + 100 * i + "%)";
    if (type === "V") {
        translate = (i) => "translateY(-" + 100 * i + "%)";
    }

    // 슬라이더
    const slider = document.querySelector(target);
    const sliderRects = slider.getClientRects()[0];
    slider.style["overflow"] = "hidden";

    // 슬라이더 화면 컨테이너
    const container = document.createElement("div");
    container.style["display"] = "flex";
    container.style["flex-direction"] = type === "V" ? "column" : "row";
    container.style["width"] = sliderRects.width + "px";
    container.style["height"] = sliderRects.height + "px";
    container.style["transform"] = translate(index);

    // 슬라이더 화면 목록
    let boxes = [].slice.call(slider.children);
    boxes = [].concat(boxes[boxes.length - 1], boxes, boxes[0]);

    // 슬라이더 화면 스타일
    const size = boxes.length;
    for (let i = 0; i < size; i++) {
        const box = boxes[i];
        box.style["flex"] = "none";
        box.style["flex-wrap"] = "wrap";
        box.style["height"] = "100%";
        box.style["width"] = "100%";
        container.appendChild(box.cloneNode(true));
    }

    // 처음/마지막 화면 눈속임 이벤트
    container.addEventListener("transitionstart", function() {
        isMoved = false;
        setTimeout(() => {
            isMoved = true;
        }, speed);
    });
    container.addEventListener("transitionend", function() {
        // 처음으로 순간이동
        if (index === size - 1) {
            index = 1;
            container.style["transition"] = "none";
            container.style["transform"] = translate(index);
        }
        // 끝으로 순간이동
        if (index === 0) {
            index = size - 2;
            container.style["transition"] = "none";
            container.style["transform"] = translate(index);
        }
    });

    // 슬라이더 붙이기
    slider.innerHTML = "";
    slider.appendChild(container);

    return {
        move: function(i) {
            if (isMoved === true) {
                index = i;
                container.style["transition"] = transform;
                container.style["transform"] = translate(index);
            }
        },
        next: function() {
            if (isMoved === true) {
                index = (index + 1) % size;
                container.style["transition"] = transform;
                container.style["transform"] = translate(index);
            }
        },
        prev: function() {
            if (isMoved === true) {
                index = index === 0 ? index + size : index;
                index = (index - 1) % size;
                container.style["transition"] = transform;
                container.style["transform"] = translate(index);
            }
        },
    };
}

const s1 = new Slider("#slider1", "H");
const s2 = new Slider("#slider2", "V");

setInterval(() => {
    s1.next();
    s2.next();
}, 1000);
/******************************************************** 회원가입 유효성 -현균*************************8 */
//아이디 중복검사
function idDuplicateCheck() {
    var reg_id = /^[a-z0-9]{4,15}$/; //ID는 영어, 숫자만 사용하여 4~15자 사이로 입력해주세요.
    let idCheckResult = document.getElementById("idCheckResult");
    let id = $('.uid');
    let userId = $('.uid').val(); // .uid에 입력되는 값
    let data = {
        userId: userId
    } // '컨트롤에 넘길 데이터 이름' : '데이터(.uid에 입력되는 값)'
    if (userId == "") {
        alert("아이디 입력 후 중복확인 버튼을 눌러주세요.");
        id.focus();
    } else if (!reg_id.test(userId)) {
        alert("아이디는 영어, 숫자만 사용하여 4~15자 사이로 입력해주세요.")
        id.focus();
    } else {
        $.ajax({
            type: "post",
            url: "/sikhuchung/userIdChk",
            data: data,
            success: function(result) {
                /* console.log("성공 여부" + result); */
                if (result != 'fail') {
                    $('.id_input_re_1').css("display", "inline-block");
                    $('.id_input_re_2').css("display", "none");
                    $('.uid').attr("readonly", true);
                    $('.uid').css("background", "#ccc");
                    $('.btn_idcheck').attr("disabled", true);
                    $('.btn_idcheck').css("background", "#ccc");
                    idCheckResult.value = "1";
                    return true;
                } else {
                    $('.id_input_re_2').css("display", "inline-block");
                    $('.id_input_re_1').css("display", "none");
                    idCheckResult.value = "0";
                    return false;
                }
            }
        });
    }
};
// 회원가입 빈값 체크 유효성
function joinNullCheck() {
    let idCheckResult = document.getElementById("idCheckResult");
    if (!idCheck()) { //아이디 검사
        return false;
    } else if (!pwCheck1()) { //비밀번호 검사
        return false;
    } else if (!pwCheck2()) { //비밀번호 확인 검사
        return false;
    } else if (!nameCheck()) { //이름 검사
        return false;
    } else if (!emailCheck()) { //이메일 검사
        return false;
    } else if (!telCheck()) { //전화번호 검사
        return false;
    } else if (idCheckResult.value == "0") { //중복확인 유무 검사
        alert("아이디 중복확인을 완료해주세요.");
        return false;
    } else { //유효성 검사 완료시 회원가입 진행
        alert("회원가입이 완료되었습니다.");
        document.formJoin.submit();
        return true;
    }
}
// 아이디 유효성 체크
function idCheck() {
    let id = document.getElementById("uid");
    var reg_id = /^[a-z0-9]{4,15}$/; //ID는 영어, 숫자만 사용하여 4~15자 사이로 입력해주세요.
    if (id.value == "") {
        alert("아이디를 입력해 주세요");
        id.focus();
        return false;
    } else if (!reg_id.test(id.value)) {
        alert("아이디는 영어, 숫자만 사용하여 4~15자 사이로 입력해주세요.")
        id.focus();
        return false;
    } else {
        return true;
    }
}
// 비밀번호 유효성 체크
function pwCheck1() {
    let password = document.getElementById("upw");
    var reg_pw = /^(?=.*[a-z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{4,20}/;
    if (password.value == "") {
        alert("비밀번호를 입력해 주세요");
        password.focus();
        return false;
    } else if (!reg_pw.test(password.value)) {
        alert("비밀번호는 영어,특수문자,숫자 1개이상 포함하여 4~20자 사이로 입력해주세요.");
        password.focus();
        return false;
    } else {
        return true;
    }
}
// 비밀번호확인 유효성 체크
function pwCheck2() {
    let password = document.getElementById("upw");
    let passwordCheck = document.getElementById("upwCheck");
    if (passwordCheck.value == "") {
        alert("비밀번호확인을 입력해 주세요");
        passwordCheck.focus();
        return false;
    } else if (password.value != passwordCheck.value) {
        alert("비밀번호와 비밀번호 확인이 다릅니다.");
        passwordCheck.focus();
        return false;
    } else {
        return true;
    }
}
// 이름 유효성 체크
function nameCheck() {
    let name = document.getElementById("uname");
    var reg_name = /^[가-힣a-zA-Z]{2,20}$/;
    if (name.value == "") {
        alert("이름을 입력해 주세요");
        name.focus();
        return false;
    } else if (!reg_name.test(name.value)) {
        alert("이름은 2~20자 사이의 한글과 영어만 입력가능합니다.");
        name.focus();
        return false;
    } else {
        return true;
    }
}
// 이메일 유효성 체크
function emailCheck() {
    let email = document.getElementById("uemail");
    var reg_email = /([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,20})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
    if (email.value == "") {
        alert("이메일을 입력해 주세요");
        email.focus();
        return false;
    } else if (!reg_email.test(email.value)) {
        $('.email_msg').css("display", "none");
        alert("이메일형식이 잘못되었습니다.");
        email.focus();
        return false;
    } else {
        return true;
    }
}
// 핸드폰번호 유효성 체크
function telCheck() {
    let tel = document.getElementById("utel");
    var reg_tel = /^[0-9]{11,}$/;
    if (tel.value == "") {
        alert("휴대폰번호를 입력해 주세요");
        tel.focus();
        return false;
    } else if (!reg_tel.test(tel.value)) {
        alert("휴대폰번호는 최소 11자리 이상의 숫자만 입력 가능합니다.");
        tel.focus();
        $('.tel_msg').css("display", "none");
        return false;
    } else {
        return true;
    }
}
/************************************************** 로그인 유효성 -현균***********************************/
// 로그인 빈값 체크 유효성

function loginNullCheck() {
    let id = document.getElementById("id");
    let password = document.getElementById("pw");
    let userId = $('.id').val(); // .uid에 입력되는 값
    let userPass = $('.pw').val(); // .uid에 입력되는 값
    var param = { userId: userId, userPw: userPass }
    // '컨트롤에 넘길 데이터 이름' : '데이터(.uid에 입력되는 값)'
    if (userId == "") {
        alert("아이디를 입력해 주세요");
        id.focus();
    } else if (userPass == "") {
        alert("비밀번호를 입력해 주세요");
        password.focus();
    } else {
        $.ajax({
            type: "post",
            url: "/sikhuchung/login.do",
            data: param,
            success: function(result) {
                /*  console.log("성공 여부" + result);*/
                if (result != 'fail') {  //성공
                    alert("로그인에 성공하였습니다.\n메인페이지로 이동합니다.")
                    location.href = '/sikhuchung/main.do';
                    return true;
                } else {
                    $('.id_chk').css("display", "inline-block");
                    return false;
                }
            }
        });
    }
};
/****************************************************** 아이디찾기 유효성 -현균*************************/
// 아이디찾기 빈값 체크 유효성
function findIdCheck() {
    let name = document.getElementById("name");
    let email = document.getElementById("email");

    if (name.value == "") {
        alert("이름을 입력해 주세요");
        name.focus();
        return false;
    } else if (email.value == "") {
        alert("이메일을 입력해 주세요");
        email.focus();
        return false;
    } else {
        document.formFindId.submit();
    }
}


/***************************************************** 비밀번호찾기 유효성 -현균***************************/
// 비밀번호찾기 빈값 체크 유효성
function findPwCheck() {
    let id = document.getElementById("id");
    let name = document.getElementById("name");


    if (id.value == "") {
        alert("아이디를 입력해 주세요");
        id.focus();
        return false;
    } else if (name.value == "") {
        alert("이름을 입력해 주세요");
        name.focus();
        return false;
    } else {
        document.formFindPw.submit();
    }
}
/***************************************************** 회원정보변경 유효성 -현균***************************/
// 회원가입 빈값 체크 유효성
function userUpdateNullCheck() {
    if (!pwCheck1()) { //비밀번호 검사
        return false;
    } else if (!pwCheck2()) { //비밀번호 확인 검사
        return false;
    } else if (!nameCheck()) { //이름 검사
        return false;
    } else if (!emailCheck()) { //이메일 검사
        return false;
    } else if (!telCheck()) { //전화번호 검사
        return false;
    } else { //유효성 검사 완료시 회원가입 진행
        var answer = confirm("회원정보를 변경 하시겠습니까?");
        if (answer) {
            alert("회원정보가 변경되었습니다.");
            document.formMemberUpdate.submit();
            return true;
        } else {
            return false;
        }
    }
}
// 회원정보변경 비밀번호 확인 빈값 체크 유효성
function pwCertifyNullCheck() {
    let pw = document.getElementById("pw");
    if (pw.value == "") {
        alert("비밀번호를 입력해 주세요");
        pw.focus();
        return false;
    } else {
        document.formPwCertify.submit();
    }
}

/***************************************************** 회원탈퇴 유효성 -현균***************************/
// 회원탈퇴 비밀번호 확인 빈값 체크 유효성
function userQuitNullCheck() {
    let userId = $('.id').val(); // .uid에 입력되는 값
    let userPass = $('.password').val(); // .uid에 입력되는 값
    var param = { userId: userId, userPw: userPass }
    let pw = document.getElementById("password");
    if (pw.value == "") {
        alert("비밀번호를 입력해 주세요");
        pw.focus();
        return false;
    } else {
        var answer = confirm("회원정보를 변경 하시겠습니까?");
        if (answer) {
            $.ajax({
                type: "post",
                url: "/sikhuchung/mypageMemberQuit.do",
                data: param,
                success: function(result) {
                    /*  console.log("성공 여부" + result);*/
                    if (result != 'fail') {  //성공
                        alert("회원탈퇴처리 되었습니다.\n메인페이지로 이동합니다.")
                        location.href = '/sikhuchung/main.do';
                        return true;
                    } else {
                        $('.memberQuitMsg').css("display", "inline-block");
                        return false;
                    }
                }
            });
        } else {
            return false;
        }
    }
}
/* 필립 js 시작 */
function paymentFormCheck() {
    let order_user, order_phone; /*이 부분이 없어도 정상 실행됌*/

    order_user = document.getElementById("order_user");
    order_phone = document.getElementById("order_phone");
    order_email = document.getElementById("order_email");
    get_user = document.getElementById("get_user");
    get_address = document.getElementById("get_address");
    get_phone = document.getElementById("get_phone");

    if (order_user.value == "") {
        alert("주문하시는 분을 기입해주세요.")
        order_user.focus();
        return false;
    } else if (order_phone.value == "") {
        alert("주문자 정보의 휴대폰 번호를 기입해주세요.")
        order_phone.focus();
        return false;
    } else if (order_email.value == "") {
        alert("이메일을 기입해주세요.")
        order_email.focus();
        return false;
    } else if (get_user.value == "") {
        alert("받으실 분을 기입해주세요.");
        get_user.focus();
        return false;
    } else if (get_address.value == "") {
        alert("받으실 곳을 기입해주세요.");
        get_address.focus();
        return false;
    } else if (get_phone.value == "") {
        alert("배송정보의 휴대폰 번호를 기입해주세요.")
    }

}


/* 필립 js 끝 */

/***************************** 공지사항 ***************************/
/* 공지사항 리스트 */
function movePage(uri, queryString) {
    location.href = uri + queryString;
}

function searchBoard(form) {
    if (form.searchKeyword.value == "") {
        alert("검색어를 입력해 주세요.");
        form.searchKeyword.focus();
        return false;
    }
}
/* 공지사항 글쓰기 */
function registerNotice(form) {
    var result = (
        isValid(form.userId, "작성자", form.userId)
        && isValid(form.noticeTitle, "제목", form.noticeTitle)
        && isValid(form.noticeContent, "내용", form.noticeContent)
    );
    if ( result == false ) {
        return false;
    }
    
var noticeNumber = /*[[ ${notice.noticeNumber} ]]*/"";
    if (isEmpty(noticeNumber) == false) {
        var queryString = /*[[ ${params.makeQueryString(params.currentPageNo)} ]]*/"";
    
        /*[- 쿼리 스트링을 오브젝트 형태로 변환 -]*/
        queryString = new URLSearchParams(queryString);
        queryString.forEach(function(value, key) {
        if (isEmpty(value) == false) {
            $(form).append('<input type="hidden" name="' + key + '" value="' + value + '" />');
        }
        });
    }
}
/*[- end of function -]*/
/* 공지사항 삭제 */
function deleteNotice(noticeNumber, queryString) {

    if (confirm(noticeNumber + "번 게시글을 삭제할까요?")) {
        var uri = /*[[ @{/sikhuchung/delete.do} ]]*/"";
        var html = "";

        html += '<form name="dataForm" action="'+uri+'" method="post">';
        html += '<input type="hidden" name="noticeNumber" value="'+noticeNumber+'" />';
                    
        queryString = new URLSearchParams(queryString);
        queryString.forEach(function(value, key) {
        if (noticeNumber.value == false) {
            html += '<input type="hidden" name="' + key + '" value="' + value + '" />';
        }
        });
                    
        html += '</form>';

        $("body").append(html);
        document.dataForm.submit();
    }
}

/***************************** 후기 ***************************/





















