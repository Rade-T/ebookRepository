var token;
var role;

$(document).ready(function() {
	token = localStorage.getItem('token');
	if (!token) {
        window.location.replace("index.html");
    } else {
    	$("#loginLink").remove();
    	$.ajax({
    		url: "http://localhost:8080/api/users/role",
    		type: "GET",
    		beforeSend: function (request) {
                request.setRequestHeader("X-Auth-Token", token);
        	},
    		success: function(data) {
    			console.log(data);
    			role = data;
    		},
    		error: function(error) {
    			console.log(error);
    		}
    	});
    	$("#changePassword").prop("disabled", true);
    }
	
	$("#oldPasswordConfirm").on("keyup", function(event) {
		var oldPassword = $("#oldPassword").val();
		var oldPasswordConfirm = $("#oldPasswordConfirm").val();
		
		if (oldPassword == oldPasswordConfirm) {
			$("#changePassword").prop("disabled", false);
		} else {
			$("#changePassword").prop("disabled", true);
		}
	});
	
	$("#logoutLink").on("click", function(event) {
		event.preventDefault();
		localStorage.removeItem("token");
		window.location.reload();
	});
});
