// 사용할 tag 모음
const list = document.querySelector(`#data-list`);
const likeBtn = document.querySelector(`#btn-like`);
const addressText = document.querySelector(`#select-address`);
const searchBtn = document.querySelector(`#btn-submit`);
const searchText = document.querySelector(`#inputText`);

// 데이터 페이징을 위한 variable
let page = 0;
const amount = 12;

// 주소 변경 이벤트 추가
setReg(null, setAddress).then(()=> {
	document.querySelectorAll("select").forEach(tag => {
		tag.onchange = () => setReg(event.target, setAddress);
	});	
});

searchBtn.onclick = async () => setReg(event.target, setAddress);


//설정한 주소를 바탕으로 매물 및 지도 설정 함수
function setAddress(data) {
	let address = '';	// 주소 텍스트
	let level = 0;		// 확대 레벨
	selectBox.some(({text}) => {
		if(text) address += text+" ";
		else return true;
		level += 1;
	});

	// 매물 데이터 출력 부분
	printList(data);
	
	// 지도 출력 부분
//	setMapLocation(address, level);
	// 우측 상세 주소 노출 부분
	addressText.innerText = address + (level>=3? "" :" 전체");
	// 마커 추가 부분
	setMapMarker(address, data, level);
}


// 상세 매물 목록 출력 함수
function printDetailList(data) {
	let html = "";
	
	data.forEach(item => {
		console.log(data);
		html += `
		    <div class="data-item">
		      <div class="img-box">
		          <img src="/assets/img/${randomImgs[Math.floor(Math.random() * 2)+2]}" alt="apartment">
		      </div>
		      <div>
		        <p>${item.apartmentName}</p>
		        <p>${item.dong} ・ ${item.area}㎡</p>
		        <p>${item.dealAmount}만원</p>
		        <p>${item.dealYear}.${item.dealMonth}.${item.dealDay}</p>
		      </div>
		    </div>`;
	});
	
	list.innerHTML = html;
}

// 아파트별 매물 목록 출력 함수
function printList(data) {
	let html = "";
	
	if(!data){
		list.innerHTML = '<div class="empty-item">거래된 내역이 없습니다.</div>';
		return ;
	}
	
	data.forEach(item => {
		
		// 숫자 콤마 표현으로 변경
		item.minDealAmount = numberToString(item.minDealAmount);
		item.maxDealAmount = numberToString(item.maxDealAmount);
		
		let area = `${item.minArea} ~ ${item.maxArea}`;
		let dealAmount = `${item.minDealAmount} ~ ${item.maxDealAmount}`;
		// min과 max가 같은 경우 합쳐주기
		if(item.minArea===item.maxArea) area = item.maxArea;
		if(item.minDealAmount===item.maxDealAmount) dealAmount = item.maxDealAmount;
		
		html += `
		    <div class="data-item">
		      <div class="img-box">
		          <img src="/assets/img/${randomImgs[Math.floor(Math.random() * 2)+2]}" alt="apartment">
		      </div>
		      <div>
		        <p>${item.apartmentName}</p>
		        <p>${item.dong} ・ ${area}㎡</p>
		        <p>${dealAmount}만원</p>
		      </div>
		    </div>`;
	});
	
	list.innerHTML = html;
}



/*
setList();

document.querySelector("#sido").addEventListener("change", setList);
document.querySelector("#gugun").addEventListener("change", setList);
document.querySelector("#dong").addEventListener("change", setList);
document.querySelector("#btn-submit").addEventListener("click", setList);
document.querySelector("#btn-all").addEventListener("click", onClickNavBtn);
document.querySelector("#btn-apartment").addEventListener("click", onClickNavBtn);
document.querySelector("#btn-house").addEventListener("click", onClickNavBtn);
likeBtn.addEventListener("click", onClickLikeRegion);

// 데이터 세팅 함수
async function setList() {
  if (event) {
	  regcode = event.target.value;
	  let text = '';
	  for(option of event.target.getElementsByTagName("option")){
		  if(option.value===regcode) text = option.label;
	  }
	  switch(event.target.name) {
	  case "sido":
		  sidoText = text;
		  gugunText = "";
		  break;
	  case "gugun":
		  gugunText = text;
		  break;
	  }
  }
  // items 초기화
  items = [];
  const urlParms = new URLSearchParams(location.search);
  let type = urlParms.get("type") ?? "all";

  document.querySelector(`#btn-${type}`)?.setAttribute("selected", true);

  switch (type) {
    case "apartment":
      await addAptDealList()
        .finally(printItem)
        .finally(() => setMapMarker(items));
      break;

    case "house":
      await addHouseDealList()
        .finally(printItem)
        .finally(() => setMapMarker(items));
      break;

    default:
      await addAptDealList()
        .finally(addHouseDealList)
        .finally(printItem)
        .finally(() => setMapMarker(items));
  }
  document.querySelector("#select-address").innerHTML = `${sidoText} ${gugunText}`;
  setLikeRegion();
  setMapLocation();
}

// 지역코드를 통한 아파트 실거래 목록 추가 함수
async function addAptDealList() {
  // 최신 데이터 우선 가져오기

  let num = 10; // 최종 가져올 데이터 개수
  const MAX_TERM = 6; // 최대 탐색 범위(개월)

  for (let i = 0; i < MAX_TERM; i++) {
    if (!num) break;
    await getAptDeal().then((data) => {
      // console.log(data);
      if (!data) return;
      for (item of data) {
        if (num == 0) break;
        num--;
        items.push({
          dealYear: Number(item["dealYear"]),
          dealMonth: Number(item["dealMonth"]),
          dealDay: Number(item["dealYear"]),
          name: item["apartmentName"],
          address: item["dong"],
          area: Number(item["area"]),
          price: item["dealAmount"],
          type: 1,
        });
      }
    });
  }
}

// 지역코드를 통한 주택 실거래 목록 추가 함수
async function addHouseDealList() {

  let num = 10; // 최종 가져올 데이터 개수
  const MAX_TERM = 6; // 최대 탐색 범위(개월)

  for (let i = 0; i < MAX_TERM; i++) {
    if (!num) break;
    await getAptDeal().then((data) => {
        // console.log(data);
        if (!data) return;
        for (item of data) {
          if (num == 0) break;
          num--;
          items.push({
            dealYear: Number(item["dealYear"]),
            dealMonth: Number(item["dealMonth"]),
            dealDay: Number(item["dealYear"]),
            name: item["apartmentName"],
            address: item["dong"],
            area: Number(item["area"]),
            price: item["dealAmount"],
            type: 0,
          });
        }
      });
  }
}

// 데이터 목록 출력 함수
async function printItem() {
  // 날짜순으로 정렬
  items.sort((a, b) =>
    a.dealYear > b.dealYear ? -1 : a.dealMonth > b.dealMonth ? -1 : a.dealDay > b.dealDay ? 1 : 0
  );

let html = "";
  for (item of items) {
    if (!inputText && !item.name.match(inputText) && !item.address.match(inputText)) continue;
    html += `
    <div class="data-item">
      <div class="img-box">
          <img src="${root}${
      randomImgs[Math.floor(Math.random() * 2) + item.type * 2]
    }" alt="apartment">
      </div>
      <div>
        <p>${item.name}</p>
        <p>${item.address} ・ ${item.area}㎡</p>
        <p>${item.price}만원</p>
        <p>${item.dealYear}.${item.dealMonth}.${item.dealDay}</p>
      </div>
    </div>`;
  }

  list.innerHTML = html;
}

// 관심 지역 반영 함수
function setLikeRegion() {
  if (sidoText === "전체") {
    likeBtn.innerHTML = "";
  } else {
    let likeRegions = localStorage.getItem("where-is-my-home-like-region")?.split(",");
    if (likeRegions && likeRegions.find((e) => e === `${sidoText} ${gugunText}`)) {
      likeBtn.innerHTML = `
      <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-heart-fill" viewBox="0 0 16 16">
      <path fill-rule="evenodd" d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"/>
      </svg>
      `;
    } else {
      likeBtn.innerHTML = `
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-heart" viewBox="0 0 16 16">
          <path d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01L8 2.748zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15z"/>
        </svg>
      `;
    }
  }
}

// 관심 지역 설정 함수
function onClickLikeRegion() {
  let likeRegions = localStorage.getItem("where-is-my-home-like-region")?.split(",") ?? [];

  if (likeRegions.find((e) => e === `${sidoText} ${gugunText}`)) {
    likeRegions = likeRegions.filter((e) => e !== `${sidoText} ${gugunText}`);
  } else {
    likeRegions.push(`${sidoText} ${gugunText}`);
  }

  localStorage.setItem("where-is-my-home-like-region", likeRegions);
  setLikeRegion();
}

// 검색 타입 설정 함수
function onClickNavBtn() {
  switch (this.id) {
    case "btn-all":
      location.href = "./homeList.jsp?type=all";
      break;

    case "btn-apartment":
      location.href = "./homeList.jsp?type=apartment";
      break;

    case "btn-house":
      location.href = "./homeList.jsp?type=house";
  }
}
*/