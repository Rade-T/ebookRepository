var token;
var role;

$(document).ready(function() {
	token = localStorage.getItem('token');
	if (!token) {
        $("#logoutLink").remove();
        $("#categoriesItem").remove();
        $("#languagesItem").remove();
        $("#passwordItem").remove();
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
    			if (role == "ROLE_SUBSCRIBER") {
    		        $("#categoriesItem").remove();
    		        $("#languagesItem").remove();
    			}
    		},
    		error: function(error) {
    			console.log(error);
    		}
    	});
    }
	
	$("#logoutLink").on("click", function(event) {
		event.preventDefault();
		localStorage.removeItem("token");
		window.location.reload();
	});
});
