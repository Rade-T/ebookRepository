baseUrl = "localhost:8080"

$(document).ready(function (){
	$.ajax({
        type: "GET",
        url: baseUrl + "/api/categories/",
        dataType: "json",
        success: function (data) {
            $("#categoryTable").append("<tr>" + data[i].name + "</tr>");
        }
    });
});

$("#addCategoryBtn").on("click", function(event) {
	event.preventDefault();
	var data = {
		"name" : $("#nameInput").val()
	};
	$.ajax({
		type: "POST",
		url: baseUrl + "/api/categories/",
		data: JSON.stringify(data),
		contentType: "application/json",
		success: function(response) {
			location.reload();

		},
		error: function (err) {
            var text = err.responseJSON;
            alert(text['message']);
        }
	});
});