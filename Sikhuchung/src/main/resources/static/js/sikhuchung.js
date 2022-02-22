/******************************************************** 회원가입 유효성 -현균*************************8 */
// 회원가입 빈값 체크 유효성
function joinNullCheck() {
    let id = document.getElementById("uid");
    let password = document.getElementById("upw");
    let passwordCheck = document.getElementById("upwCheck");
    let name = document.getElementById("uname");
    let email = document.getElementById("uemail");
    let tel = document.getElementById("utel");


    if (id.value == "") {
        alert("아이디를 입력해 주세요");
        id.focus();
        return false;
    } else if (password.value == "") {
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
        document.formJoin.submit();
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
//아이디 중복검사
$('.btn_idcheck').on("click", function() {
    /* console.log("keyup 테스트"); */
    var userId = $('.uid').val(); // .id_input에 입력되는 값
    var data = {
            userId : userId
    } // '컨트롤에 넘길 데이터 이름' : '데이터(.id_input에 입력되는 값)'
    $.ajax({
        type : "post",
        url : "/sikhuchung/userIdChk",
        data : data,
        success : function(result){
             /* console.log("성공 여부" + result); */
            if(result != 'fail'){
                $('.id_input_re_1').css("display","inline-block");
                $('.id_input_re_2').css("display", "none");             
            } else {
                $('.id_input_re_2').css("display","inline-block");
                $('.id_input_re_1').css("display", "none");             
            }
        }
    }); // ajax 종료
});// function 종료
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

