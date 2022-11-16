const form = document["find-pass-form"];

async function findPass() {
	
	if(form.pass.value !== form.passcheck.value) {
		alert("비밀번호를 확인해주세요!");
		return;
	}
	
	let url = '/user/findPass';
	let data = {
			id : form.id.value,
			name : form.name.value,
			email : form.email.value,
			pass : form.pass.value
			
	};
	let options = {
	        method: "PUT",
	        headers: {
	            "Content-Type": "application/json",
	        },
	        body : JSON.stringify(data)
	};
	fetch(url, options)
	.then(res => res.json())
	.then(({msg, res}) => {
		alert(msg);
		
		if(res==1)
			location.href = '/user/login';
	});
}