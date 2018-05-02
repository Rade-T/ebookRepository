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
    $("#id").val(id);
    $("#title").val(title);
    $("#author").val(author);
	$("#year").val(year);
	$("#keywords").val(keywords);
	$("#language").val(language);
	$("#cataloguer").val(cataloguer);
	$("#category").val(category);
}

$(document).ready(function() {
    $.ajax({
            url: "/api/ebooks/"
        })
        .then(
            function(data) {
                console.log("Ucitiavanje knjiga");
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
                        +"</tr>"

                    $("#dataTable").append(newRow)
                }
			});
			
			$.ajax({
                url: "/api/categories"
            })
            .then(
                function(data) {
                    console.log("Uspeo kategorije")
                    for (i = 0; i < data.length; i++) {
                        var newOption = '<option value="' + data[i].id + '">' +
                            data[i].name + '</option>';
                        $("#category").append(newOption);
                    }
                });

        $.ajax({
                url: "/api/languages"
            })
            .then(
                function(data) {
                    console.log("Uspeo jezike")
                    for (i = 0; i < data.length; i++) {
                        var newOption = '<option value="' + data[i].id + '">' +
                            data[i].name + '</option>';
                        $("#language").append(newOption);
                    }
                });

        $.ajax({
                url: "/api/users"
            })
            .then(
                function(data) {
                    console.log("Uspeo korisnike")
                    for (i = 0; i < data.length; i++) {
                        var newOption = '<option value="' + data[i].id + '">' +
                            data[i].username + '</option>';
                        $("#cataloguer").append(newOption);
                    }
                });

    $('#inputModal').on('shown.bs.modal', function(e) {
        $.ajax({
                url: "/api/categories"
            })
            .then(
                function(data) {
                    console.log("Uspeo kategorije")
                    for (i = 0; i < data.length; i++) {
                        var newOption = '<option value="' + data[i].id + '">' +
                            data[i].name + '</option>';
                        $("#categorySelect").append(newOption);
                    }
                });

        $.ajax({
                url: "/api/languages"
            })
            .then(
                function(data) {
                    console.log("Uspeo jezike")
                    for (i = 0; i < data.length; i++) {
                        var newOption = '<option value="' + data[i].id + '">' +
                            data[i].name + '</option>';
                        $("#languageSelect").append(newOption);
                    }
                });

        $.ajax({
                url: "/api/users"
            })
            .then(
                function(data) {
                    console.log("Uspeo korisnike")
                    for (i = 0; i < data.length; i++) {
                        var newOption = '<option value="' + data[i].id + '">' +
                            data[i].username + '</option>';
                        $("#cataloguerSelect").append(newOption);
                    }
                });
    });

    $("#add").click(function() {
        // pripremamo JSON koji cemo poslati
        console.log("start");
        formData = JSON.stringify({
            author: $("#inputForm [name='author']").val(),
            title: $("#inputForm [name='title']").val(),
            publicationYear: $("#inputForm [name='year']").val(),
            keywords: $("#inputForm [name='keywords']").val(),
            language: $("#languageSelect").val(),
            cataloguer: $("#inputForm [name='cataloguerSelect']").val(),
            category: $("#inputForm [name='categorySelect']").val(),
            MIME: "",
        });
        console.log(formData);
        $.ajax({
            url: "/api/ebooks/",
            type: "POST",
            // saljemo json i ocekujemo json nazad
            contentType: "application/json",
            datatype: 'json',
            data: formData,
            success: function(data) {
                var newRow = "<tr>" +
                    "<td class=\"id\">" + data.id + "</td>" +
                    "<td class=\"title\">" + data.title + "</td>" +
                    "<td class=\"author\">" + data.author + "</td>" +
                    "<td class=\"year\">" + data.publicationYear + "</td>" +
                    "<td><a class=\"remove btn btn-danger\" href='/api/ebooks/" + data.id + "'>Obrisi" +
                    "</a></td>" +
                    +"</tr>"

                $("#dataTable").append(newRow)
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
            MIME: "",
        });
        $.ajax({
            url: "/api/ebooks/" + $("#editForm [name='id']").val(),
            type: "POST",
            data: formData,
            // saljemo json i ocekujemo json nazad
            contentType: "application/json",
            datatype: 'json',
            success: function(data) {
                $(".highlighted").find(".title")[0].innerHTML = data.title;
                $(".highlighted").find(".author")[0].innerHTML = data.author;
				$(".highlighted").find(".year")[0].innerHTML = data.publicationYear;
				$(".highlighted").find(".keywords")[0].innerHTML = data.keywords;
				$(".highlighted").find(".language")[0].innerHTML = data.language;
				$(".highlighted").find(".category")[0].innerHTML = data.category;
				$(".highlighted").find(".cataloguer")[0].innerHTML = data.cataloguer;
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
        success: function() {
            //ukloni i na strani 
            tr_parent.remove()
        }
    });
});