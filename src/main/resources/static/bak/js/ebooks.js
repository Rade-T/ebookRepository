$(document).ready(function (){
	$("#addEbookPanel").hide();
	$("#editEbookPanel").hide();
});

$("#newEbookButton").on("click", function(event) {
	event.preventDefault();
	$("#addEbookPanel").show();
	console.log("Test");
});