/* 필립 js 시작 */
function paymentFormCheck(){
    let order_user,order_phone; /*이 부분이 없어도 정상 실행됌*/
    
    order_user=document.getElementById("order_user");
    order_phone=document.getElementById("order_phone");
    order_email=document.getElementById("order_email");
    get_user=document.getElementById("get_user");
    get_address=document.getElementById("get_address");
    get_phone=document.getElementById("get_phone");
    
    if(order_user.value==""){
        alert("주문하시는 분을 기입해주세요.")
        order_user.focus();
        return false;
    }else if(order_phone.value==""){
        alert("주문자 정보의 휴대폰 번호를 기입해주세요.")
        order_phone.focus();
        return false;
    }else if(order_email.value==""){
        alert("이메일을 기입해주세요.")
        order_email.focus();
        return false;
    }else if(get_user.value==""){
        alert("받으실 분을 기입해주세요.");
        get_user.focus();
        return false;
    }else if(get_address.value==""){
        alert("받으실 곳을 기입해주세요.");
        get_address.focus();
        return false;
    }else if(get_phone.value==""){
        alert("배송정보의 휴대폰 번호를 기입해주세요.")
    }
    
}


/* 필립 js 끝 */