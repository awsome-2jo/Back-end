const localURL = `http://127.0.0.1:5500/front_whereismyhome`;
const params = new URL(window.location.href).searchParams;
let user = localStorage.getItem("user");

// 임시 대체 이미지
const randomImgs = [
  `house-1.jpg`,
  `house-2.jpg`,
  `apartment-1.jpg`,
  `apartment-2.jpg`,
];

// 숫자에 콤마 붇이는 함수
function numberToString(number) {
	if(typeof number !== 'Number') number = Number(number);
	return number.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
}

// xml을 json으로 변환해주는 xmlToJson함수 선언
const xmlToJson = (xml) => {
  // Create the return object
  var obj = {};

  if (xml.nodeType == 1) {
    // element
    // do attributes
    if (xml.attributes.length > 0) {
      obj["@attributes"] = {};
      for (var j = 0; j < xml.attributes.length; j++) {
        var attribute = xml.attributes.item(j);
        obj["@attributes"][attribute.nodeName] = attribute.nodeValue;
      }
    }
  } else if (xml.nodeType == 3) {
    // text
    obj = xml.nodeValue;
  }

  // do children
  // If all text nodes inside, get concatenated text from them.
  var textNodes = [].slice.call(xml.childNodes).filter(function (node) {
    return node.nodeType === 3;
  });
  if (xml.hasChildNodes() && xml.childNodes.length === textNodes.length) {
    obj = [].slice.call(xml.childNodes).reduce(function (text, node) {
      return text + node.nodeValue;
    }, "");
  } else if (xml.hasChildNodes()) {
    for (var i = 0; i < xml.childNodes.length; i++) {
      var item = xml.childNodes.item(i);
      var nodeName = item.nodeName;
      if (typeof obj[nodeName] == "undefined") {
        obj[nodeName] = xmlToJson(item);
      } else {
        if (typeof obj[nodeName].push == "undefined") {
          var old = obj[nodeName];
          obj[nodeName] = [];
          obj[nodeName].push(old);
        }
        obj[nodeName].push(xmlToJson(item));
      }
    }
  }
  return obj;
}


// 날짜 문자 출력 함수
function getDateString() {
    function leftPad(value) {
        if (value >= 10) {
            return value;
        }
    
        return `0${value}`;
    }
    const today = new Date();
    const delimiter = '-';
    const year = today.getFullYear();
    const month = leftPad(today.getMonth() + 1);
    const day = leftPad(today.getDate());

    return [year, month, day].join(delimiter);
}
