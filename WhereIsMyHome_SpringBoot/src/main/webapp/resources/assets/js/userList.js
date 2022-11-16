const editBox = document.getElementById("edit-box");
const editForm = document["edit-form"];
const inputs = editForm.getElementsByTagName("input");
const root = document["root"];
const rootPath = root.action + "/user";

//let _root = "/whereIsMyHome";

//fetch(rootPath)
//	.then(res => res.json())
//	.then(data => makeList(data));

//fetch(`${_root}/user/user`)
//	.then(res => res.json())
//	.then(data => {
//		alert("들어옴!")
//	})

function setFormData(idx) {
	for(input of inputs){
		if(input.name == "action") {
			input.value = "modifyUser";
		}
		else {
			input.value = users[idx][input.name];			
		}
	}
}
function openEditBox(idx) {
	setFormData(idx);
    editBox.style.display = "block";
}

function closeEditBox() {
	editBox.style.display = "none";
}

function onClickDel(idx) {	
	setFormData(idx);
	inputs[0].value = "delete";
	if(confirm("정말로 삭제하시겠습니까?")) onSubmit();
}
function onEdit(el) {
    if(confirm("변경 사항을 적용하시겠습니까?")) {
    	let formData = new FormData(editForm);
		let objectBody = Object.fromEntries(formData.entries());
    	let config = {
    			method: "PUT",
    			headers: {
			    	"Content-Type": "application/json",
			    },
    			body: JSON.stringify(objectBody)
    	};
    	
    	fetch(editForm.action, config)
    	.then(res => res.json())
    	.then(data => {
    		alert(data.msg);
    		makeList(data.users);
    	});
    }
}
function makeList(users) {
	let tbody = ``;
	users.forEach(function (user, index) {
		tbody += `
				<tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.email}</td>
                    <td>${user.phone}</td>
                    <td>
                        <button onClick="openEditBox(${index})">수정</button>
                        <button type="submit" onClick="onClickDel(${index})" form="edit-form" name="edit" value="delete">삭제</button>
                    </td>
                </tr>
		`;
	});
	document.querySelector("#user-list").innerHTML = tbody;
}
