/**
 * 
 */

function proc1(){
	var now = new Date();
	
	dateString = now.toLocaleString();
	date = now.toLocaleDateString();
	time = now.toLocaleTimeString();
	
	elediv = document.getElementById("result1");
	elediv.innerHTML = dateString + "<br>";
	elediv.innerHTML += date + "<br>";
	elediv.innerHTML += time + "<br>";
}