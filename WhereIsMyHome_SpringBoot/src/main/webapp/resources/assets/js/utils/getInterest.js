const dataItem = document.querySelector(`#data-item-list`);


function printList(arr) {
  let likeRegions = arr ?? (localStorage.getItem("where-is-my-home-like-region")?.split(",") ?? []);
  let html = "";

  likeRegions.forEach(e => {
    html += `
            <li class="data-item">
              <div>
                <p>${e}</p>
              </div>
            </li>`;
  });

  dataItem.innerHTML = html;
}
function regist() {
  let likeRegions = localStorage.getItem("where-is-my-home-like-region")?.split(",") ?? [];
  console.log(`${sidoText} ${gugunText}`);
  // 전체인 경우 등록 불가하므로 종료
  if (sidoText === "전체") return;
  // 이미 관심지역이면 종료
  if (likeRegions.find((e)=> e===`${sidoText} ${gugunText}`)) return;

  likeRegions.push(`${sidoText} ${gugunText}`);
  localStorage.setItem("where-is-my-home-like-region", likeRegions);
  printList(likeRegions);
}

function show() {
  document.querySelector(".background").className = "background show";
}
function close() {
  document.querySelector(".background").className = "background";
}
document.querySelector("#show").addEventListener("click", show);
document.querySelector("#close").addEventListener("click", close);
