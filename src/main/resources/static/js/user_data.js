var token;
var role;

$(document).ready(function() {
	token = localStorage.getItem('token');
	if (!token) {
        window.location.replace("index.html");
    } else {
    	$("#loginLink").remove();
    }
	
	$("#changeData").on("click", function(event) {
		event.preventDefault();
		formData = JSON.stringify({
			firstname :$("#firstname").val(),
            lastname :$("#lastname").val(),
            username :$("#username").val()
        });
		$.ajax({
    		url: "http://localhost:8080/api/users/change-data",
    		type: "POST",
    		data: formData,
			contentType: "application/json",
			datatype: 'json',
    		beforeSend: function (request) {
                request.setRequestHeader("X-Auth-Token", token);
        	},
    		success: function(data) {
    			//console.log(data);
    			//role = data;
    			window.location.replace("index.html");
    		},
    		error: function(error) {
    			if (error.status == 400) {
    				console.log("Username already exists");
    			} else {
    			console.log(error);
    			}
    		}
    	});
	})
	
	$("#logoutLink").on("click", function(event) {
		event.preventDefault();
		localStorage.removeItem("token");
		window.location.reload();
	});
});
