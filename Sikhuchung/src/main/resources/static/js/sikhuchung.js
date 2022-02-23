/******************************************************** 회원가입 유효성 -현균*************************8 */
//아이디 중복검사
function idDuplicateCheck() {
    let idCheckResult = document.getElementById("idCheckResult");
    let id = $('.uid');
    let userId = $('.uid').val(); // .uid에 입력되는 값
    let data = {
        userId: userId
    } // '컨트롤에 넘길 데이터 이름' : '데이터(.uid에 입력되는 값)'
    if (userId == "") {
        alert("아이디 입력 후 중복확인 버튼을 눌러주세요.");
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


    if (id.value == "") {
        alert("아이디를 입력해 주세요");
        id.focus();
        return false;
    } else if (password.value == "") {
        alert("비밀번호를 입력해 주세요");
        password.focus();
        return false;
    } else {
        alert("로그인이 완료되었습니다.");
        document.formLogin.submit();
    }
}
/****************************************************** 아이디찾기 유효성 -현균*************************/
// 아이디찾기 빈값 체크 유효성
function findIdNullCheck() {
    let name = document.getElementById("name");
    let email = document.getElementById("email");


    if (name.value == "") {
        alert("이름을 입력해 주세요");
        id.focus();
        return false;
    } else if (email.value == "") {
        alert("이메일을 입력해 주세요");
        password.focus();
        return false;
    } else {
        document.formFindId.submit();
    }
}
/***************************************************** 비밀번호찾기 유효성 -현균***************************/
// 비밀번호찾기 빈값 체크 유효성
function findPwNullCheck() {
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
// 회원정보변경 빈값 체크 유효성
function userUpdateNullCheck() {
    let password = document.getElementById("password");
    let passwordCheck = document.getElementById("passwordCheck");
    let name = document.getElementById("name");
    let email = document.getElementById("email");
    let tel = document.getElementById("tel");


    if (password.value == "") {
        alert("비밀번호를 입력해 주세요");
        password.focus();
        return false;
    } else if (passwordCheck.value == "") {
        alert("비밀번호를 입력해 주세요");
        passwordCheck.focus();
        return false;
    } else if (name.value == "") {
        alert("이름을 입력해 주세요");
        name.focus();
        return false;
    } else if (email.value == "") {
        alert("이메일을 입력해 주세요");
        email.focus();
        return false;
    } else if (tel.value == "") {
        alert("휴대폰번호를 입력해 주세요");
        tel.focus();
        return false;
    } else {
        alert("회원가입이 완료되었습니다.");
        document.formMemberUpdate.submit();
    }
}
/***************************************************** 회원탈퇴 유효성 -현균***************************/
// 회원탈퇴 비밀번호 확인 빈값 체크 유효성
function userQuitNullCheck() {
    let pw = document.getElementById("password");
    if (pw.value == "") {
        alert("비밀번호를 입력해 주세요");
        pw.focus();
        return false;
    } else {
        document.formUserQuit.submit();
    }
}

