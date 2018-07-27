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
	
	$("#changePassword").on("click", function(event) {
		event.preventDefault();
		formData = JSON.stringify({
			oldPassword :$("#oldPasswordConfirm").val(),
            newPassword :$("#newPassword").val()
        });
		$.ajax({
    		url: "http://localhost:8080/api/users/change-password",
    		type: "PUT",
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
    				console.log("Bad password");
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
