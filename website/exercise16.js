function updateTime()                 
{
    //Code for updateTime.
	let currentDate = new Date();
	let hours = currentDate.getHours();
	let minutes = currentDate.getMinutes();
	let seconds = currentDate.getSeconds();
			
	if (minutes < 10) minutes = "0" + minutes;
	if (seconds < 10) seconds = "0" + seconds;
	let th = document.getElementById("timeheader");  //Find the correct element with ID.
    th.textContent = hours + ":" + minutes + ":" + seconds;
			
	setTimeout("updateTime()", 1000);
}