// variable
let regcode = `*00000000`;

// 주소 선택 박스
const selectBox = [
    { tag: document.getElementById("sido"), defaultOpt: `도/광역시`, text:`` },
    { tag: document.getElementById("gugun"), defaultOpt: `시/구/군`, text:`` },
    { tag: document.getElementById("dong"), defaultOpt: `동/읍/면`, text:`` }
];


// 지역코드 설정 함수
async function setReg(tagObj, callback) {
    // 지역코드 제공 사이트
    const url = `https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes?`;
    const options = [`regcode_pattern=`, `&is_ignore_zero=true`];

    // 선택박스 데이터 선택한 경우
    if (tagObj?.tagName==='SELECT') {
    	let sb = selectBox.find(({ tag }) => tag.id === tagObj.id);
        sb.text = tagObj[tagObj.selectedIndex].text;

        if (sb.text.match('/')) sb.text = ``;
        regcode = tagObj.value;
        
        
        if(window.hasOwnProperty('searchText')) searchText.value = '';
    }
    else if(tagObj?.tagName==='BUTTON') {
    	return getAptList(callback, searchText.value);
    }
    
    await fetch(url + options[0] + regcode + options[1])
    .then(res => res.json())
    .then(data => setOption(data.regcodes));
    
    await getAptList(callback);
}

// 선택박스 옵션 설정 함수
function setOption(regcodes) {
    let target = null;
    
    if (regcodes?.length !== 0) {
        if (regcode.match(/00000000$/)) target = 0;
        else if (regcode.match(/00000$/)) target = 1;
        else if (regcode.match(/[*]$/)) target = 2;
        else return;

        for (let i = target; i < 3; i++){
            selectBox[i].tag.innerHTML = `<option value=${regcode}>${selectBox[i].defaultOpt}</option>`;
            selectBox[i].text = '';
        }

        let html = selectBox[target].tag.innerHTML;
        regcodes.forEach(e => {
        	// 중복되는 주소 문자열 제거
            if (target > 0) e.name = e.name.split(selectBox[target - 1].text)[1];
            
            // 지역코드 수정
            if(target == 0) e.code = e.code.substring(0, 2) + `*00000`;
            if(target == 1) e.code = e.code.substring(0, 5) + `*`;
            
            html += `<option value='${e.code}'>${e.name}</option>`;
        });

        selectBox[target].tag.innerHTML = html;
    }
}

// apt API 호출 함수
async function getAptList(callback, keyward) {
	console.log("getAptList");
	
	// Apt 요청 주소
    let url = `apt/list?regcode=${regcode.split("*")[0]}&page=${page}&amount=${amount}`;
    
    if(keyward) url += `&keyword=${keyward}`;
    
    const options = {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    };


    await fetch(url)
        .then(res => res.json())
        .then(data => callback(data))
        .catch(() => callback(false))
}