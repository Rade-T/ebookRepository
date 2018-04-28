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
	id = item.find(".id").html();
	title = item.find(".title").html();
	author = item.find(".author").html();
	year = item.find(".year").html();
	$("#id").val(id);
	$("#title").val(name);
	$("#author").val(author);
	$("#year").val(year);
}

$(document).ready(function() {
	$.ajax({
		url : "/api/ebooks/"})
		.then(
			function(data) {
				console.log("Ucitiavanje knjiga");
				for (i = 0; i < data.length; i++) {
					var newRow = "<tr>"
					+ "<td class=\"id\">" + data[i].id + "</td>"
					+ "<td class=\"title\">" + data[i].title + "</td>"
					+ "<td class=\"author\">" + data[i].author + "</td>"
					+ "<td class=\"year\">" + data[i].year + "</td>"
					+ "<td><a class=\"remove btn btn-danger\" href='/api/ebooks/" + data[i].id + "'>Obrisi" 
					+ "</a></td>" +
					+ "</tr>"
					
					$("#dataTable").append(newRow)
				}
			});
	
	$("#add").click(function(){
		// pripremamo JSON koji cemo poslati
			console.log("start");
			formData = JSON.stringify({
				author :$("#inputForm [name='author']").val(),
				title :$("#inputForm [name='title']").val(),
				year :$("#inputForm [name='year']").val(),
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
					var newRow = "<tr>"
						+ "<td class=\"id\">" + data.id + "</td>"
						+ "<td class=\"title\">" + data.title + "</td>"
						+ "<td class=\"author\">" + data.author + "</td>"
						+ "<td class=\"year\">" + data.year + "</td>"
						+ "<td><a class=\"remove btn btn-danger\" href='/api/ebooks/" + data.id + "'>Obrisi"
						+ "</a></td>" +
						+ "</tr>"
					
					$("#dataTable").append(newRow)
				  },
				  error: function(data) {
					  console.log(data.getResponseHeader());
				  }
				});
			$('#inputModal').modal('toggle');
			console.log("end");
	 });
	
	$("#potvrda").on("click", function(event){
		event.preventDefault();
		console.log("Kliknuta potvrda");
		formData = JSON.stringify({
            author :$("#inputForm [name='author']").val(),
			title :$("#inputForm [name='title']").val(),
			year :$("#inputForm [name='year']").val(),
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
				$(".highlighted").find(".year")[0].innerHTML = data.year;
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
});

$(document).on("click", "tr", function(event) {
	highlightRow(this)
});

$(document).on("click", ".remove", function(event){
	//ne salji get zahtev
	event.preventDefault(); 
	url = $(this).attr("href")
	//red koji se treba izbrisati ako je brisanje na serveru bilo uspesno
	tr_parent = $(this).closest("tr")
	$.ajax({
    	url: url,
    	type: "DELETE",
    	success: function(){
    		//ukloni i na strani 
			tr_parent.remove()
        }
	});
});