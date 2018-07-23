$(document).ready(function() {
    $('#searchBtn').on("click", function (e) {
        e.preventDefault();
    
        var title = $('#title').val();
        var titleCb = $('#titleCb').prop('checked');
    
        var author = $('#author').val();
        var authorCb = $('#authorCb').prop('checked');
    
        var keywords = $('#keywords').val();
        var keywordsCb = $('#keywordsCb').prop('checked');
    
        var content = $('#content').val();
        var contentCb = $('#contentCb').prop('checked');
    
        var language = $('#language').val();
        var languageCb = $('#languageCb').prop('checked');
    
        var type = $("input[name='searchType']:checked").val();
    
        var formData = {
            "title": {
                "value": title,
                "searchType": titleCb
            },
            "author": {
                "value": author,
                "searchType": authorCb
            },
            "keywords": {
                "value": keywords,
                "searchType": keywordsCb
            },
            "content": {
                "value": content,
                "searchType": contentCb
            },
            "language": {
                "value": language,
                "searchType": languageCb
            },
            "type": type
        };
    
        $.ajax({
            type: "POST",
            url: "/api/search",
            data: JSON.stringify(formData),
            contentType: "application/json",
            success: function (data) {
                console.log("Uspela pretraga");
                console.log(data);
                $("#dataTable tbody tr").remove(); 
                
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
//        			"<td><a class=\"remove btn btn-danger\" href='/api/ebooks/" + data[i].id + "'>Obrisi" +
//        			"</a></td>" +
        			"<td><a class=\"download btn\" href='/api/ebooks/downloadFile/" + data[i].filename + "'>Preuzmi" +
        			"</a></td>" +
        			"<input class=\"filename\" type=\"hidden\" value=\"" + data[i].filename + "\"></input>" +
        			"</tr>"

        			$("#dataTable tbody").append(newRow)
        		}
            },
            error: function (err) {
                var json = err.responseJSON;
                console.log(json);
            }
        });
    
    });
});