
console.log("user.js");

// login cookie check 설정
document.cookie.split(" ").forEach(c => {
    let name = c.split("=")[0];
    if (name === "userid") {
        document.getElementById("rem")?.setAttribute("checked", true);
    }
});

function isPassword(asValue) {
	let regExp = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{4,16}$/;
	return regExp.test(asValue);
}
function isId(asValue) {
	let regExp = /^[a-z]+[a-z0-9]{4,19}$/g;
	return regExp.test(asValue);
}

function isEmail(asValue) {
	let regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i; 
	return regExp.test(asValue);
}
function isPhone(asValue) {
	let regExp = /[0-9]/;
	return regExp.test(asValue);
}
function isName(asValue) {
	let regExp = /^[a-zA-Zㄱ-힣][a-zA-Zㄱ-힣 ]*$/;
	return regExp.test(asValue);
}

function submitLogin() {
    // const form = document["login-form"];
    // let id = form.id.value;
    // let pass = form.pass.value;

    
    // if (!isId(id)) {
    //     alert("잘못된 아이디 형식입니다.");
    //     return false;
    // }
    // if (!isPassword(pass)) {
    //     alert("잘못된 비밀번호 형식입니다.");
    //     return false;
    // }
    return true;
}

function submitRegist() {
    const form = document["regist-form"];
    let id = form.id.value;
    let pass = form.pass.value;
    let passcheck = form.passcheck.value;
    let email = form.email.value;
    let name = form.name.value;
    let phone = form.phone.value;

    if (pass !== passcheck) {
        alert("비밀번호가 일치하지 않습니다!");
        return false;
    }
    if (!isId(id)) {
        alert("아이디는 4 ~ 20자 영문으로 시작하는 영문, 또는 숫자만 가능합니다.");
        return false;
    }
    if (!isPassword(pass)) {
        alert("비밀번호는 4 ~ 16자 영문, 숫자 조합만 가능합니다.");
        return false;
    }
    if (!isEmail(email)) {
        alert("이메일 형식이 잘못되었습니다!");
        return false;
    }
    if (!isName(name)) {
        alert("이름은 한글 및 영문만 가능합니다.");
        return false;
    }
    if (!isPhone(phone)) {
        alert("전화번호는 숫자만 가능합니다.");
        return false;
    }
    return true;
}

function submitUser(root) {
	console.log("onclick : submitUser");
    const form = document["user-form"];
    
    if (event.target.value === "delete") {
        form.action = `${root}/delete`;
        if(confirm("정말 탈퇴하시겠습니까?")) {
        	form.submit();        	
        }
        return;
    }
    
    form.action = `${root}/modify`;
    let id = form.id.value;
    let pass = form.pass.value;
    let passcheck = form.passcheck.value;
    let email = form.email.value;
    let name = form.name.value;
    let phone = form.phone.value;
    let result = true;
    
    if (!!passcheck && pass !== passcheck) {
        alert("비밀번호가 일치하지 않습니다!");
        result = false;
    }
    else if (!isName(name)) {
        alert("이름은 한글 및 영문만 가능합니다.");
        result = false;
    }
    else if (!isPhone(phone)) {
        alert("전화번호는 숫자만 가능합니다.");
        result = false;
    }
    else if (pass==="") {
    	alert("비밀번호를 입력해주세요.");
    	result = false;    	
    }
    if(result){
    	form.submit();    	
    }
}