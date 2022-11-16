const imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
const mapContainer = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스

let map = null;
let clusterer = null;
let geocoder = null;
let imageSize = null;
let markerImage = null;
let markers = [];
let placeOverlay = null;
let contentNode = document.createElement('div'); // 커스텀 오버레이의 컨텐츠 엘리먼트 입니다 
let placeMarkers = []; // 마커를 담을 배열입니다
let currCategory = ''; // 현재 선택된 카테고리를 가지고 있을 변수입니다
let ps = null;

document.write(
  `<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=${keys.kakao}&libraries=services"></script>`
);

this.onload = () => {
	
  let options = {
    //지도를 생성할 때 필요한 기본 옵션
    center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
    level: 10, //지도의 레벨(확대, 축소 정도)
  };
  map = new kakao.maps.Map(mapContainer, options); //지도 생성 및 객체 리턴
  geocoder = new kakao.maps.services.Geocoder();
  imageSize = new kakao.maps.Size(24, 35);
  markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
  setMapLocation("서울특별시", 0);
  
  
  // 여기서부터 유용한 건물 붙이는 코드
  placeOverlay = new kakao.maps.CustomOverlay({zIndex:1});
  currCategory = '';
  ps = new kakao.maps.services.Places(map);
  
  kakao.maps.event.addListener(map, 'idle', searchPlaces);
  contentNode.className = 'placeinfo_wrap';
  
  contentNode.mousedown =  kakao.maps.event.preventMap;
  contentNode.touchstart = kakao.maps.event.preventMap;
  
  placeOverlay.setContent(contentNode);
  addCategoryClickEvent();
};

//카테고리 검색을 요청하는 함수입니다
function searchPlaces() {
    if (!currCategory) {
        return;
    }
    
    // 커스텀 오버레이를 숨깁니다 
    placeOverlay.setMap(null);

    // 지도에 표시되고 있는 마커를 제거합니다
    removeMarker();
    
    ps.categorySearch(currCategory, placesSearchCB, {useMapBounds:true}); 
}

//장소검색이 완료됐을 때 호출되는 콜백함수 입니다
function placesSearchCB(data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {

        // 정상적으로 검색이 완료됐으면 지도에 마커를 표출합니다
        displayPlaces(data);
    } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        // 검색결과가 없는경우 해야할 처리가 있다면 이곳에 작성해 주세요

    } else if (status === kakao.maps.services.Status.ERROR) {
        // 에러로 인해 검색결과가 나오지 않은 경우 해야할 처리가 있다면 이곳에 작성해 주세요
        
    }
}

// 지도에 마커를 표출하는 함수입니다
function displayPlaces(places) {

    // 몇번째 카테고리가 선택되어 있는지 얻어옵니다
    // 이 순서는 스프라이트 이미지에서의 위치를 계산하는데 사용됩니다
    var order = document.getElementById(currCategory).getAttribute('data-order');

    

    for ( var i=0; i<places.length; i++ ) {

            // 마커를 생성하고 지도에 표시합니다
            var marker = addMarker(new kakao.maps.LatLng(places[i].y, places[i].x), order);

            // 마커와 검색결과 항목을 클릭 했을 때
            // 장소정보를 표출하도록 클릭 이벤트를 등록합니다
            (function(marker, place) {
                kakao.maps.event.addListener(marker, 'click', function() {
                    displayPlaceInfo(place);
                });
            })(marker, places[i]);
    }
}

// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
function addMarker(position, order) {
    var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new kakao.maps.Size(27, 28),  // 마커 이미지의 크기
        imgOptions =  {
            spriteSize : new kakao.maps.Size(72, 208), // 스프라이트 이미지의 크기
            spriteOrigin : new kakao.maps.Point(46, (order*36)), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
            offset: new kakao.maps.Point(11, 28) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
        },
        markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
            marker = new kakao.maps.Marker({
            position: position, // 마커의 위치
            image: markerImage 
        });

    marker.setMap(map); // 지도 위에 마커를 표출합니다
    placeMarkers.push(marker);  // 배열에 생성된 마커를 추가합니다

    return marker;
}

// 지도 위에 표시되고 있는 마커를 모두 제거합니다
function removeMarker() {
    for ( var i = 0; i < placeMarkers.length; i++ ) {
        placeMarkers[i].setMap(null);
    }   
    placeMarkers = [];
}

// 클릭한 마커에 대한 장소 상세정보를 커스텀 오버레이로 표시하는 함수입니다
function displayPlaceInfo (place) {
    var content = '<div class="placeinfo">' +
                    '   <a class="title" href="' + place.place_url + '" target="_blank" title="' + place.place_name + '">' + place.place_name + '</a>';   

    if (place.road_address_name) {
        content += '    <span title="' + place.road_address_name + '">' + place.road_address_name + '</span>' +
                    '  <span class="jibun" title="' + place.address_name + '">(지번 : ' + place.address_name + ')</span>';
    }  else {
        content += '    <span title="' + place.address_name + '">' + place.address_name + '</span>';
    }                
   
    content += '    <span class="tel">' + place.phone + '</span>' + 
                '</div>' + 
                '<div class="after"></div>';

    contentNode.innerHTML = content;
    placeOverlay.setPosition(new kakao.maps.LatLng(place.y, place.x));
    placeOverlay.setMap(map);  
}


// 각 카테고리에 클릭 이벤트를 등록합니다
function addCategoryClickEvent() {
    var category = document.getElementById('category'),
        children = category.children;

    for (var i=0; i<children.length; i++) {
        children[i].onclick = onClickCategory;
    }
}

// 카테고리를 클릭했을 때 호출되는 함수입니다
function onClickCategory() {
    var id = this.id,
        className = this.className;

    placeOverlay.setMap(null);

    if (className === 'on') {
        currCategory = '';
        changeCategoryClass();
        removeMarker();
    } else {
        currCategory = id;
        changeCategoryClass(this);
        searchPlaces();
    }
}

// 클릭된 카테고리에만 클릭된 스타일을 적용하는 함수입니다
function changeCategoryClass(el) {
    var category = document.getElementById('category'),
        children = category.children,
        i;

    for ( i=0; i<children.length; i++ ) {
        children[i].className = '';
    }

    if (el) {
        el.className = 'on';
    } 
} 

// 선택한 지역으로 이동 함수
function setMapLocation(address, level) {
  if (address) {
	  var callback = function (result, status) {
		  if (status === kakao.maps.services.Status.OK) {
			  map.panTo(new kakao.maps.LatLng(result[0].y, result[0].x));
			  map.setLevel(10 - level*3);
		  }
	  };
	  geocoder.addressSearch(address, callback);
  } else {
	  map.setCenter(new kakao.maps.LatLng(37.5, 127));
	  map.setLevel(10);
  }
}

// 마커 생성 함수
function setMapMarker(address, items, level) {
  markers.forEach((marker) => {
    marker.setMap(null);
  });
  markers = [];
  
  let lat = 0;
  let lng = 0;
  
//  address = address? address : "서울특별시";
  
  console.log(items);
  if(!items) return;
  items.forEach((item) => {

	  let marker = new kakao.maps.Marker({
		  map: map, // 마커를 표시할 지도
		  position: new kakao.maps.LatLng(item.lat, item.lng), // 마커를 표시할 위치
		  title: item.name, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
		  image: markerImage, // 마커 이미지
	  });
	  
	  lat += +item.lat;
	  lng += +item.lng;
	  
	  marker.setMap(map);
	  markers.push(marker);
  });
  
  if(markers.length>0){
	  lat /= markers.length;
	  lng /= markers.length;
	  console.log(lat, lng);
	  map.setCenter(new kakao.maps.LatLng(lat, lng));
	  map.setLevel(7 - level);
  }
}
