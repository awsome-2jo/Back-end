<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<select id="sido" onchange="getCode('gugun', this.value)">
		<option value="">선택하세요</option>
	</select>
	<select id="gugun" onchange="getCode('dong', this.value)">
		<option value="">선택하세요</option>
	</select>
	<select id="dong">
		<option value="">선택하세요</option>
	</select>
	<script>
		function getCode(type = "sido", code = "") {
			console.log(type, code)
			if (type == "gugun") {
				document.getElementById("dong").options.length = 1;
			}
			});
		}

		getCode();
		
	</script>
</body>
</html>