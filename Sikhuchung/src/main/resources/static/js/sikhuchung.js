function checkNumber() {
	let choice = document.getElementById("theNumberOfPrice");
	let number = choice.options[choice.selectIndex].value;
	if(number == "0") {
		console.log("수량을 입력해 주세요.");
		return false;
	} else {
		return true;
	}
}