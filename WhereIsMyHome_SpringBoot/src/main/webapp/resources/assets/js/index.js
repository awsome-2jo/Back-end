const aptList = document.querySelector(`#data-list-apartment`);
const houseList = document.querySelector(`#data-list-house`);

// 데이터 페이징을 위한 variable
let page = 0;
const amount = 6;


// 주소 변경 이벤트 추가
setReg(null, setAddress);
document.querySelectorAll("select").forEach(tag => {
	tag.onchange = () => setReg(event.target, setAddress);
});


// 설정한 주소를 바탕으로 매물 및 지도 설정 함수
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
}

// 매물 목록 출력 함수
function printList(data) {
	let html1 = `<h2>아파트 거래정보</h2>`;
	
	data.slice(0, 3).forEach(item => {
		
		// 숫자 콤마 표현으로 변경
		item.minDealAmount = numberToString(item.minDealAmount);
		item.maxDealAmount = numberToString(item.maxDealAmount);
		
		let area = `${item.minArea} ~ ${item.maxArea}`;
		let dealAmount = `${item.minDealAmount} ~ ${item.maxDealAmount}`;
		// min과 max가 같은 경우 합쳐주기
		if(item.minArea===item.maxArea) area = item.maxArea;
		if(item.minDealAmount===item.maxDealAmount) dealAmount = item.maxDealAmount;
		
		html1 += `
		    <div class="data-item">
		      <div>
		        <p>${item.apartmentName}</p>
		        <p>${item.dong} ・ ${area}㎡</p>
		        <p>${dealAmount}만원</p>
		      </div>
			<div class="img-box">
			<img src="/assets/img/${randomImgs[Math.floor(Math.random() * 2)+2]}" alt="apartment">
			</div>
		    </div>`;
	});
	
	let html2 = `<h2></h2><a id="a-more-apt" href="#">전체보기</a>`;
	
	data.slice(3).forEach(item => {
		
		// 숫자 콤마 표현으로 변경
		item.minDealAmount = numberToString(item.minDealAmount);
		item.maxDealAmount = numberToString(item.maxDealAmount);
		
		let area = `${item.minArea} ~ ${item.maxArea}`;
		let dealAmount = `${item.minDealAmount} ~ ${item.maxDealAmount}`;
		// min과 max가 같은 경우 합쳐주기
		if(item.minArea===item.maxArea) area = item.maxArea;
		if(item.minDealAmount===item.maxDealAmount) dealAmount = item.maxDealAmount;
		
		html2 += `
		    <div class="data-item">
		      <div>
		        <p>${item.apartmentName}</p>
		        <p>${item.dong} ・ ${area}㎡</p>
		        <p>${dealAmount}만원</p>
		      </div>
			<div class="img-box">
			<img src="/assets/img/${randomImgs[Math.floor(Math.random() * 2)+2]}" alt="apartment">
			</div>
		    </div>`;
	});
	
	aptList.innerHTML = html1;
	houseList.innerHTML = html2	;
	
	
}
