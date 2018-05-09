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
    
        var data = {
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
            data: JSON.stringify(data),
            contentType: "application/json",
            success: function (response) {
                console.log("Uspela pretraga");
                //var lista = groupBy(response);
                console.log(response);
                //console.log(lista);
            },
            error: function (err) {
                var json = err.responseJSON;
                console.log("Pretraga nije uspela");
            }
        });
    
    });
});