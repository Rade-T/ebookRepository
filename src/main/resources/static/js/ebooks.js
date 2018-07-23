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

function sync(item) {
    id = item.find(".id").html();
    title = item.find(".title").html();
    author = item.find(".author").html();
	year = item.find(".year").html();
	keywords = item.find(".keywords").html();
	language = item.find(".language").html();
	cataloguer = item.find(".cataloguer").html();
	category = item.find(".category").html();
	filename = item.find(".filename").val();
    $("#id").val(id);
    $("#title").val(title);
    $("#author").val(author);
	$("#year").val(year);
	$("#keywords").val(keywords);
	$("#language").val(language);
	$("#cataloguer").val(cataloguer);
	$("#category").val(category);
	$("#filename").val(filename);
}

$(document).ready(function() {
	token = localStorage.getItem('token');
	if (!token) {
        $("#logoutLink").remove();
        $("#newLanguageItem").remove();
        $("#editBookPanel").remove();
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
    		        $("#newLanguageItem").remove();
    		        $("#editBookPanel").remove();
    			}
    		},
    		error: function(error) {
    			console.log(error);
    		}
    	});
    }
    $.ajax({
    	url: "/api/ebooks/",
    	type: "GET",
		beforeSend: function (request) {
            request.setRequestHeader("X-Auth-Token", token);
    	},
    	success: function(data) {
    		console.log("Ucitavanje knjiga");
    		for (i = 0; i < data.length; i++) {
    			var newRow = "<tr>" +
    			"<td class=\"id\">" + data[i].id + "</td>" +
    			"<td class=\"title\">" + data[i].title + "</td>" +
    			"<td class=\"author\">" + data[i].author + "</td>" +
    			"<td class=\"year\">" + data[i].publicationYear + "</td>" +
    			"<td class=\"keywords\">" + data[i].keywords + "</td>" +
    			"<td class=\"language\">" + data[i].language + "</td>" +
    			"<td class=\"category\">" + data[i].category + "</td>" +
    			"<td class=\"cataloguer\">" + data[i].cataloguer + "</td>" +
    			"<td><a class=\"remove btn btn-danger\" href='/api/ebooks/" + data[i].id + "'>Obrisi" +
    			"</a></td>" +
    			"<td><a class=\"download btn\" href='/api/ebooks/downloadFile/" + data[i].filename + "'>Preuzmi" +
    			"</a></td>" +
    			"<input class=\"filename\" type=\"hidden\" value=\"" + data[i].filename + "\"></input>" +
    			"</tr>"

    			$("#dataTable").append(newRow)
    		}
    	}
    });

    $.ajax({
    	url: "/api/categories",
    	type: "GET",
		beforeSend: function (request) {
            request.setRequestHeader("X-Auth-Token", token);
    	},
    	success: function(data) {
    		console.log("Uspeo kategorije")
    		for (i = 0; i < data.length; i++) {
    			var newOption = '<option value="' + data[i].id + '">' +
    			data[i].name + '</option>';
    			$("#category").append(newOption);
    		}
    	}
    });

    $.ajax({
    	url: "/api/languages",
    	type: "GET",
		beforeSend: function (request) {
            request.setRequestHeader("X-Auth-Token", token);
    	},
    	success: function(data) {
    		console.log("Uspeo jezike")
    		for (i = 0; i < data.length; i++) {
    			var newOption = '<option value="' + data[i].id + '">' +
    			data[i].name + '</option>';
    			$("#language").append(newOption);
    		}
    	}
    });

    $.ajax({
    	url: "/api/users",
    	type: "GET",
		beforeSend: function (request) {
            request.setRequestHeader("X-Auth-Token", token);
    	},
    	success: function(data) {
    		console.log("Uspeo korisnike")
    		for (i = 0; i < data.length; i++) {
    			var newOption = '<option value="' + data[i].id + '">' +
    			data[i].username + '</option>';
    			$("#cataloguer").append(newOption);
    		}
    	}
    });

    $('#inputModal').on('shown.bs.modal', function(e) {
    	$.ajax({
    		url: "/api/categories",
    		type: "GET",
    		beforeSend: function (request) {
                request.setRequestHeader("X-Auth-Token", token);
        	},
    		success: function(data) {
    			console.log("Uspeo kategorije")
    			for (i = 0; i < data.length; i++) {
    				var newOption = '<option value="' + data[i].id + '">' +
    				data[i].name + '</option>';
    				$("#categorySelect").append(newOption);
    			}
    		}
    	});

    	$.ajax({
    		url: "/api/languages",
    		type: "GET",
    		beforeSend: function (request) {
    			request.setRequestHeader("X-Auth-Token", token);
    		},
    		success: function(data) {
    			console.log("Uspeo jezike")
    			for (i = 0; i < data.length; i++) {
    				var newOption = '<option value="' + data[i].id + '">' +
    				data[i].name + '</option>';
    				$("#languageSelect").append(newOption);
    			}
    		}
    	});

    	$.ajax({
    		url: "/api/users",
    		type: "GET",
    		beforeSend: function (request) {
                request.setRequestHeader("X-Auth-Token", token);
        	},
    		success: function(data) {
    			console.log("Uspeo korisnike")
    			for (i = 0; i < data.length; i++) {
    				var newOption = '<option value="' + data[i].id + '">' +
    				data[i].username + '</option>';
    				$("#cataloguerSelect").append(newOption);
    			}
    		}
    	});
    });

    $("#add").on('click', function(event) {
        event.preventDefault();
        console.log("start");
        formData = JSON.stringify({
            author: $("#inputForm [name='author']").val(),
            title: $("#inputForm [name='title']").val(),
            publicationYear: $("#inputForm [name='year']").val(),
            keywords: $("#inputForm [name='keywords']").val(),
            language: $("#languageSelect").val(),
            cataloguer: $("#inputForm [name='cataloguerSelect']").val(),
            category: $("#inputForm [name='categorySelect']").val(),
            filename: $("#pdf")[0].files[0].name,
            MIME: ""
        });
        console.log(formData);
        var fileData = new FormData();
        fileData.append('file', $('#pdf')[0].files[0]);
        $.ajax({
            url: "/api/ebooks/uploadFile/",
            type: "POST",
            contentType: false,
            processData: false,
            data: fileData,
    		beforeSend: function (request) {
                request.setRequestHeader("X-Auth-Token", token);
        	},
            success: function(data) {
                console.log("File uploaded!");
                $.ajax({
                    url: "/api/ebooks/",
                    type: "POST",
                    contentType: "application/json",
                    datatype: 'json',
                    data: formData,
                    beforeSend: function (request) {
                        request.setRequestHeader("X-Auth-Token", token);
                	},
                    success: function(data) {
                        var newRow = "<tr>" +
                            "<td class=\"id\">" + data.id + "</td>" +
                            "<td class=\"title\">" + data.title + "</td>" +
                            "<td class=\"author\">" + data.author + "</td>" +
                            "<td class=\"year\">" + data.publicationYear + "</td>" +
                            "<td class=\"keywords\">" + data.keywords + "</td>" +
                            "<td class=\"language\">" + data.language + "</td>" +
                            "<td class=\"category\">" + data.category + "</td>" +
                            "<td class=\"cataloguer\">" + data.cataloguer + "</td>" +
                            "<td><a class=\"remove btn btn-danger\" href='/api/ebooks/" + data.id + "'>Obrisi" +
                            "</a></td>" +
                            "<td><a class=\"download btn\" href='/api/ebooks/downloadFile/" + data.filename + "'>Preuzmi" +
                            "</a></td>" +
                            "<input class=\"filename\" type=\"hidden\" value=\"" + data.filename + "\"></input>" +
                            "</tr>"
        
                        $("#dataTable").append(newRow)
                    },
                    error: function(data) {
                        console.log(data.getResponseHeader());
                    }
                });
            },
            error: function(data) {
                console.log(data.getResponseHeader());
            }
        });
        $('#inputModal').modal('toggle');
        console.log("end");
    });

    $("#potvrda").on("click", function(event) {
        event.preventDefault();
        console.log("Kliknuta potvrda");
		formData = JSON.stringify({
            author: $("#editForm [name='author']").val(),
            title: $("#editForm  [name='title']").val(),
            publicationYear: $("#editForm  [name='year']").val(),
            keywords: $("#editForm  [name='keywords']").val(),
            language: $("#editForm [name='language']").val(),
            cataloguer: $("#editForm  [name='cataloguer']").val(),
            category: $("#editForm  [name='category']").val(),
            filename: $("#filename").val(),
            MIME: "",
        });
		console.log(formData);
        $.ajax({
            url: "/api/ebooks/" + $("#editForm [name='id']").val(),
            type: "POST",
            data: formData,
            contentType: "application/json",
            datatype: 'json',
            beforeSend: function (request) {
                request.setRequestHeader("X-Auth-Token", token);
        	},
            success: function(data) {
                $(".highlighted").find(".title")[0].innerHTML = data.title;
                $(".highlighted").find(".author")[0].innerHTML = data.author;
				$(".highlighted").find(".year")[0].innerHTML = data.publicationYear;
				$(".highlighted").find(".keywords")[0].innerHTML = data.keywords;
				$(".highlighted").find(".language")[0].innerHTML = data.language;
				$(".highlighted").find(".category")[0].innerHTML = data.category;
				$(".highlighted").find(".cataloguer")[0].innerHTML = data.cataloguer;
				$(".highlighted").find(".filename")[0].innerHTML = data.filename;
                $(".highlighted").find(".id")[0].innerHTML = $("#editForm [name='id']").val();
            },
            error: function() {
                console.log("Nije updateovao!")
            }
        });
    });

    $("#rollback").click(function(event) {
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

$(document).on("click", ".remove", function(event) {
    //ne salji get zahtev
    event.preventDefault();
    url = $(this).attr("href")
    //red koji se treba izbrisati ako je brisanje na serveru bilo uspesno
    tr_parent = $(this).closest("tr")
    $.ajax({
        url: url,
        type: "DELETE",
        beforeSend: function (request) {
            request.setRequestHeader("X-Auth-Token", token);
    	},
        success: function() {
            //ukloni i na strani 
            tr_parent.remove()
        },
        error: function(err) {
        	console.log(err);
        }
    });
});