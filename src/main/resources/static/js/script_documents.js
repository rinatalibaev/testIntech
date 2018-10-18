$( document ).ready(function() {
	$.ajax({
		  url: 'http://localhost:8088/documents/getAll',
		  type: 'GET',
		  dataType: 'json',
		  success: function(data){
				$.each(data, function(index, doc){
					$('#docTable > tbody:last-child')
					.append('<tr><td>' 
							+ doc.id + '</td><td>' 
							+ doc.title + '</td><td>' 
							+ doc.author + '</td>' 
							+ '<td class="buttons">SHOW</td>'
							+ '<td class="buttons">EDIT</td>'
							+ '<td class="buttons">DELETE</td>'
							+ '</tr>');
				});
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