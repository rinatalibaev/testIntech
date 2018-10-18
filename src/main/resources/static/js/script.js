var deleteDocumentId;

//ADD DOCUMENT
$( "#targetAdd" ).submit(function( event ) {
	
	  event.preventDefault();
	  	var formData = {
	  		title : $("#targetAdd #title").val(),
	  		author :  $("#targetAdd #author").val(),
	  		src: $("#targetAdd #src").val()
	  	}
	  	
	  	$.ajax({
				type : "POST",
				contentType : "application/json",
				url : "http://localhost:8088/documents/create",
				data : JSON.stringify(formData),
				dataType : 'json',
				success : function(result) {
					
				},
				  error: function() {
					window.location = "/addDocument";
				    console.log('error', arguments);
				  },
				  complete: function() {
				    console.log('complete', arguments);
				  }
				}).done(function() {
				  window.location = "/documents";
				  console.log('done', arguments);
				});
	});

//SHOW DOCUMENT
function openDocumentProperties(index) {
	$.ajax({
		  url: 'http://localhost:8088/documents/getDocById/'+index,
		  type: 'GET',
		  dataType: 'json',
		  success: function(data){
				    var left  = ($(window).width()/2)-(900/2),
					    top   = ($(window).height()/2)-(600/2),
					    w = window.open ("", "popup", "width=900, height=600, top="+top+", left="+left);
				    var table = $("<table/>");
				    var thead = $("<thead/>");
				    var tbody = $("<tbody/>");
				    
				    var row = $("<tr/>");
				        $.each(data, function(colIndex, c) { 
				            row.append($("<th/>").text(colIndex));
				        });
				    thead.append(row);
				    table.append(thead);
				    var row = $("<tr/>");
			        $.each(data, function(colIndex, c) { 
			            row.append($("<td/>").text(c));
			        });
			        tbody.append(row);
			        table.append(tbody);
			        $(w.document.body).html("");
				    $(w.document.body).append("<html><head><style type='text/css'>" +
				    		"table {border: 1px solid grey;width:100%;}" +
				    		"table td, th {border: 1px solid grey; padding: 3px;} </style></head><body>");
				    $(w.document.body).append(table);
				    $(w.document.body).append("</body></html>");										
				console.log("Success: ", data);
		    console.log('success', arguments);
		  },
		  error: function() {
		    console.log('error', arguments);
		  },
		  complete: function() {
		    console.log('complete', arguments);
		  }
		}).done(function() {
		  console.log('done', arguments);
		});
}

//FIND DOCUMENT

$( "#targetFind" ).submit(function( event ) {
	event.preventDefault();

  	var formData = {
  		id : $("#targetFind #id").val(),	
  		title : $("#targetFind #title").val(),
  		author :  $("#targetFind #author").val()
  	}

  	console.log("formData: " + formData);
  	
  	var requestParams = "?";
  	$.each(formData, function(colIndex, value) {
  		if (value!='') {
  			requestParams = requestParams + colIndex + "=" + value + "&";
  		}
  	});
  	requestParams = requestParams.slice(0,-1);
  	
  	$.ajax({
  		    url: 'http://localhost:8088/documents/findDocument'+ requestParams,
			type : "GET",
			contentType : "application/json",
			dataType : 'json',
			success : function(data) {
				console.log('data: ' + data);
				var w = window.open("", "_self");
				var table = $("<table/>");
			    var thead = $("<thead/>");
			    var tbody = $("<tbody/>");
			    var row = $("<tr/>");
			    	$.each(data[0], function(colIndex, c) {
				    	row.append($("<th/>").text(colIndex));	
			    });
  				thead.append(row);
  				table.append(thead);
  				
  				$.each(data, function(index, doc){
				tbody.append('<tr><td>' 
							+ doc.id + '</td><td>' 
							+ doc.title + '</td><td>' 
							+ doc.author + '</td><td>' 
							+ doc.src + '</td><td>' 
							+ doc.createTime + '</td><td>' 
							+ doc.editTime + '</td>' 
							+ '<td><div class="buttons">SHOW</div></td>'
							+ '<td><div class="buttons">EDIT</div></td>'
							+ '<td><div class="buttons">DELETE</div></td>'
							+ '</tr>');
				});

  				table.append(tbody);
  				$(w.document.body).append(table);
				console.log("Success: ", data);
		    console.log('success', arguments);
		  },
		  error: function() {
		    console.log('error', arguments);
		  },
		  complete: function() {
		    console.log('complete', arguments);
		  }
		}).done(function() {
		  console.log('done', arguments);
		});
});

//OPEN WINDOW WITH DOCUMENT TO EDIT
function getDocAndPopulateTable (documentId,task) {
	$.ajax({
		  url: 'http://localhost:8088/documents/getDocById/'+documentId,
		  type: 'GET',
		  dataType: 'json',
		  success: function(data){
			  	var requestParams = "?";
			  	$.each(data, function(colIndex, value) {
			  		requestParams = requestParams + colIndex + "=" + value + "&";
			  	});
		  		requestParams = requestParams.slice(0,-1);
		  		var left  = ($(window).width()/2)-(900/2),
		  			top   = ($(window).height()/2)-(600/2),
		  			popup = window.open ("http://localhost:8088/" + task + requestParams, "popup", "width=900, height=600, top="+top+", left="+left);
				console.log("Success: ", data);

		    console.log('success', arguments);
		  },
		  error: function() {
		    console.log('error', arguments);
		  },
		  complete: function() {
		    console.log('complete', arguments);
		  }
		}).done(function() {
		  console.log('done', arguments);
		});
}

//UPDATE DOCUMENT
$( "#targetEdit" ).submit(function( event ) {

	event.preventDefault();

  	var formData = {
  		id : $("#targetEdit #id").val(),	
  		title : $("#targetEdit #title").val(),
  		author :  $("#targetEdit #author").val(),
  		src: $("#targetEdit #src").val(),
  		createTime : $("#targetEdit #createTime").val(),
  		editTime : $("#targetEdit #editTime").val()
  	}

  	console.log("formData: " + formData);
  	
  	$.ajax({
  		    url: 'http://localhost:8088/documents/updateDocument',
			type : "POST",
			contentType : "application/json",
			data : JSON.stringify(formData),
			dataType : 'json',
			success : function(result) {
				opener.location.reload();
				window.close();
			},
			  error: function() {
				window.location = "/error";
			    console.log('error', arguments);
			  },
			  complete: function() {
			    console.log('complete', arguments);
			  }
			}).done(function() {
			  
			  console.log('done', arguments);
			});
});

//CONFIRMATION DIALOG on DOCUMENT DELETION
function deleteEntity(documentId) {
	var res = showPrompt('Вы хотите удалить документ номер ' + documentId + '?');
    res.then(function(ret) {
      ret ? (deleteDocument(documentId), window.location.reload()) : window.location.reload();
    });

}

// DELETE DOCUMENT
function deleteDocument(documentId) {
	$.ajax({
		url: 'http://localhost:8088/documents/deleteDocument/' + documentId,
		type : "DELETE",
		success : function(result) {
			
		},
		  error: function() {
//			   window.location = "/error";
//			   console.log('error', arguments);
			  },
			   complete: function() {
			   console.log('complete', arguments);
			  }
			}).done(function() {
			  window.location = "/documents";
			  console.log('done', arguments);
	});
}
                
//BUTTONS LISTENERS
$("#docTable").on("click", ".buttons", function() {
    var actionName = $( this ).get(0).textContent;
    var documentId = $( this ).closest('tr').find('td:first-child').text();
    if (actionName == "SHOW") {
    	openDocumentProperties(documentId);
    }
    if (actionName == "EDIT") {
    	getDocAndPopulateTable(documentId,"editDocument");
    }
    if (actionName == "DELETE") {
    	deleteEntity(documentId);
    }
});

$("#addDocBtn").click(function(){
	window.location ="/addDocument";
});

$("#plusPict").click(function(){
	window.location ="/addDocument";
});

$("#findBtn").click(function(){
	var left  = ($(window).width()/2)-(900/2),
		top   = ($(window).height()/2)-(600/2),
		popup = window.open ("http://localhost:8088/findForm", "popup", "width=900, height=600, top="+top+", left="+left);
});

$("#findPict").click(function(){
	var left  = ($(window).width()/2)-(900/2),
	top   = ($(window).height()/2)-(600/2),
	popup = window.open ("http://localhost:8088/findForm", "popup", "width=900, height=600, top="+top+", left="+left);
});

$("#confirmYes").on("click", function () {
	deleteDocument(deleteDocumentId);
	window.location.reload();
	$("#confirmYes").css('display','none');
});

$("#confirmNo").on("click", function () {
	window.close();
});

