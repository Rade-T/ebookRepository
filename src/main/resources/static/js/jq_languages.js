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
	naziv = item.find(".naziv").html()
	$("#naziv").val(naziv);
	$("#id").val(id);
}

$(document).ready(function() {
	$.ajax({
		url : "/api/languages/"})
		.then(
			function(data) {
				for (i = 0; i < data.length; i++) {
					var newRow = "<tr>"
					+ "<td class=\"Id\">" + data[i].id + "</td>"
					+ "<td class=\"naziv\">" + data[i].naziv + "</td>"
					+ "<td><a class=\"remove btn btn-danger\" href='/api/languages/" + data[i].id + "'>" 
					+ "</a></td>" +
					+ "</tr>"
					
					$("#dataTable").append(newRow)
				}
			});
	
	$("#first").click(function(){
		goFirst();
	 });
	
	$("#last").click(function(){
		goLast();
	 });
	
	$("#next").click(function(){
		goNext();
	 });
	
	$("#prev").click(function(){
		goPrevious();
	 });
	
	$("#add").click(function(){
		// pripremamo JSON koji cemo poslati
			console.log("start");
			formData = JSON.stringify({
	            name :$("#inputForm [name='naziv']").val(),
	        });
			console.log(formData);
			$.ajax({
				url: "/api/languages/",
				type: "POST",
				// saljemo json i ocekujemo json nazad
				contentType: "application/json",
				datatype: 'json',
				data: formData,
				success: function(data) {
					var newRow = "<tr>"
						+ "<td class=\"Id\">" + data.id + "</td>"
						+ "<td class=\"naziv\">" + data.naziv + "</td>"
						+ "<td><a class=\"remove btn btn-danger\" href='/api/languages/" + data.id + "'>" 
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
            naziv :$("#editForm [name='naziv']").val(),
        });
		$.ajax({
			url: "http://localhost:8080/api/languages/" + $("#editForm [name='Id']").val(),
			type: "POST",
			data: formData,
			// saljemo json i ocekujemo json nazad
			contentType: "application/json",
			datatype: 'json',
			success: function(data) {
				$(".highlighted").find(".naziv")[0].innerHTML = data.naziv;
				$(".highlighted").find(".Id")[0].innerHTML = data.id;
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