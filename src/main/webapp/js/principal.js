$(document)
	.ready(
		function() {
           var dataBase;
           var table;
           
       
           
           $("#db").on("change", function() { 
        	   $('#t').empty();
               dataBase = this.value;
               
               $.post("/tablas/consulta", {
					dbName : this.value
					}, function(tablas, status) {
						if (tablas == '') {
							alert("No se pudo acceder al contenido de la Base de datos");
						} else {
							$('#tablas').show();
							tablas.forEach(function(tabla, index) {
					        $('#t').append('<option value='+tabla+'>'+ tabla+ '</option>');});
							   }

					   }).fail(function(jqXHR, textStatus, errorThrown) {
							console.log(errorThrown);
                });

		   });

                      
           $("#t").on("change", function() {
        	   
						$('#tabla').empty();
					    $(".container-lg").show();
					    table =  this.value;
					 
					    $.post("/tablas/campos", {
						   dbName : dataBase,
						   tableName : this.value
						}, function(campos, status) {
							$('#campos').show();
							$('#post').show();
							
							
							campos.forEach(function(campo, index) {
                                  $('#tabla').append(
									'<tr><td><input style="opacity:0.7" type="text" class="campo" value='+campo.columna+' readonly="readonly"></input></td>'
								  + '<td><input type="text" class="titulol"></td>'
								  + '<td><input type="number" class="ancho"></td>'
							      + '<td><input type="text" class="tipo" style="opacity:0.7" value='+campo.tipo+'></td>'
								  + '<td><select name="select" class="visible rounded-pill"><option value="false" selected>False</option><option value="true" selected="selected">True</option></select></td>'
								  + '<td><select name="select" class="filtrar rounded-pill"><option value="false" selected>False</option><option value="true">True</option></select></td>'
								  + '</tr>');
                            });
						$(".container-lg").show();
						}).fail(function(jqXHR, textStatus, errorThrown) {
							console.log(errorThrown);
                        });

			});
           

					
            $("#post").click( function() {
            	
            	$("input").css('border-color', "");
        		$("input").attr("placeholder", "");
        		$("#alertaconsulta").hide();
        		
        		if($("#consulta").val() == '') {
                   $("#alertaconsulta").show();
                }

				 var consulta = "";
				 var separador = [];
				 				 
                 $('table > tbody  > tr').each( function(index, tr) {
                	 
					separador[index] = new Array();
					
                    $(this).children("td").each(function(index2, td) {
                    	                    	
                    	if($(this).children("input").val() == "") {
                    		
                    		$(this).children("input").css('border-color', 'red');
                    		$(this).children("input").attr("placeholder", "xxxxxxxxxxx");
                    		
                    	} else {
                    	
                            if ($(this).children("input").val()) {
                        	                     	
							   separador[index].push($(this).children("input").val());
					        }

                            if ($(this).children("select").val()) {
																						
                        	   separador[index].push($(this).children("select").val());
                        	   
					        }
                    	}

					});
				 });
                 
                 var titulo = $("#consulta").val();
                
                  $.ajax({ type : "post",
					      url : "/tablas/form?titulo="+titulo+"",
						  contentType : "application/json",
						  data :  JSON.stringify(separador),
						  success: function(result){
							  alert(result);							   
							  if(result == "La insercción se realizó correctamente") {
								  location.reload()
							  }
						  }
				  });
                 
                 
		    });
	   });