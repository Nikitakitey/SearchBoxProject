/**
 * 
 */


var searchSuggestions = [];

$(document).ready(
		function(){
			populateSearchSuggestions();
		}
);

function populateSearchSuggestions() {
	// load all search keywords from backend using AJAX call
	$.ajax({
		type: "GET",
		url: "/SearchBoxProject/getAllSearchKeywords",
		data: {},
		success: function(data) {
			console.log("Succes retrieving getAllSearchKeywords");
			console.log(data);
			searchSuggestions = data.split(" ");
			$( "#searchTextBoxId" ).autocomplete({
			      source: searchSuggestions
			});
			console.log("Search box suggestions are populated.");
		},
		error: function (jqXHR, textStatus, errorThrown) {
            console.log("Error retrieving /getAllSearchKeywords data....." + jqXHR.status + ": " + errorThrown);
        }
	
	});
}

function searchText(){
	// get the text from input box and send to backend to update the count and display the same
	var keyword = $("#searchTextBoxId").val();
	$.ajax({
		type: "GET",
		url: "/SearchBoxProject/getSearchedKeywordCount",
		data: {keyword: keyword},
		success: function(data) {
			console.log("Succes retrieving getSearchedKeywordCount");
			console.log(data);
			$("#searchKeywordCount").html(data);
			populateSearchSuggestions();
		},
		error: function (jqXHR, textStatus, errorThrown) {
            console.log("Error retrieving /getSearchedKeywordCount data....." + jqXHR.status + ": " + errorThrown);
        }
	
	});
}