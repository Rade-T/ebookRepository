var token;
var role;

function highlightRow(row) {
	// ne reagujemo na klik na header tabele, samo obicne redove
	// this sadrzi red na koji se kliknulo
	if (!$(row).hasClass("header")) {
		// klasa highlighted postavlja pozadinu na plavu
		// njenim dodavanjem ili uklanjanjem oznacavamo da neki red
		// dobija, odnosno gubi selekciju
		// uklanjamo sa trenutno selektovanog
		$(".highlighted").removeClass("highlighted");
		// dodajemo na novi selektovani
		$(row).addClass("highlighted");
		// pozivamo sinhronizaciju, prosledjujemo dati red
		sync($(row));
	}
}

function sync(item){
	id = item.find(".id").html()
	name = item.find(".name").html()
	$("#name").val(name);
	$("#id").val(id);
}

$(document).ready(function() {
	token = localStorage.getItem('token');
	if (!token) {
        $("#logoutLink").remove();
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
    			if (role == "ROLE_SUBSCRIBER") {
    		        window.location.replace("/index.html");
    			}
    		},
    		error: function(error) {
    			console.log(error);
    		}
    	});
    }
	$.ajax({
		url : "/api/languages/",
		type: "GET",
		beforeSend: function (request) {
            request.setRequestHeader("X-Auth-Token", token);
    	},
		success: function(data) {
			console.log("Ucitiavanje jezika");
			for (i = 0; i < data.length; i++) {
				var newRow = "<tr>"
					+ "<td class=\"id\">" + data[i].id + "</td>"
					+ "<td class=\"name\">" + data[i].name + "</td>"
					+ "<td><a class=\"remove btn btn-danger\" href='/api/languages/" + data[i].id + "'>Obrisi" 
					+ "</a></td>" +
					+ "</tr>"

					$("#dataTable").append(newRow)
			}
		}
	});
	
	
	$("#add").click(function(){
		// pripremamo JSON koji cemo poslati
			console.log("start");
			formData = JSON.stringify({
	            name :$("#inputForm [name='name']").val()
	        });
			console.log(formData);
			$.ajax({
				url: "/api/languages/",
				type: "POST",
				// saljemo json i ocekujemo json nazad
				contentType: "application/json",
				datatype: 'json',
				data: formData,
				beforeSend: function (request) {
		            request.setRequestHeader("X-Auth-Token", token);
		    	},
				success: function(data) {
					var newRow = "<tr>"
						+ "<td class=\"id\">" + data.id + "</td>"
						+ "<td class=\"name\">" + data.name + "</td>"
						+ "<td><a class=\"remove btn btn-danger\" href='/api/languages/" + data.id + "'>Obrisi"
						+ "</a></td>" +
						+ "</tr>"
					
					$("#dataTable").append(newRow)
				  },
				  error: function(data) {
					  console.log(data);
				  }
				});
			$('#inputModal').modal('toggle');
			console.log("end");
	 });
	
	$("#potvrda").on("click", function(event){
		event.preventDefault();
		console.log("Kliknuta potvrda");
		formData = JSON.stringify({
            name :$("#editForm [name='name']").val(),
        });
		$.ajax({
			url: "/api/languages/" + $("#editForm [name='id']").val(),
			type: "POST",
			data: formData,
			contentType: "application/json",
			datatype: 'json',
			beforeSend: function (request) {
	            request.setRequestHeader("X-Auth-Token", token);
	    	},
			success: function(data) {
				$(".highlighted").find(".name")[0].innerHTML = data.name;
				$(".highlighted").find(".id")[0].innerHTML = $("#editForm [name='id']").val();
			  },
			error: function() {
				console.log("Nije updateovao!")
			}
		});
	});
	
	$("#rollback").click(function(event){
		event.preventDefault();
		sync($(".highlighted"));
	});
	
	$("#logoutLink").on("click", function(event) {
		event.preventDefault();
		localStorage.removeItem("token");
		window.location.replace("/index.html");
	});
});

$(document).on("click", "tr", function(event) {
	highlightRow(this)
});

$(document).on("click", ".remove", function(event){
	// ne salji get zahtev
	event.preventDefault(); 
	url = $(this).attr("href")
	// red koji se treba izbrisati ako je brisanje na serveru bilo uspesno
	tr_parent = $(this).closest("tr")
	$.ajax({
    	url: url,
    	type: "DELETE",
    	beforeSend: function (request) {
            request.setRequestHeader("X-Auth-Token", token);
    	},
    	success: function(){
    		// ukloni i na strani
			tr_parent.remove()
        }
	});
});