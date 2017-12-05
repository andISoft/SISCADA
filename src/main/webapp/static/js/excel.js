 
var json_ws;
var string_ws;


	function parseAndSend (event) {
	 
        loadBinaryFile(event,function(data){
        	
            var workbook = XLSX.read(data,{type:'binary'});
            
            var first_sheet_name = workbook.SheetNames[0];

            var worksheet = workbook.Sheets[first_sheet_name]; 
            
            json_ws = XLSX.utils.sheet_to_json(worksheet);
            
            string_ws = JSON.stringify(json_ws);
            
        });
    }

    function loadBinaryFile(path, success) {
            var files = path.target.files;
            var reader = new FileReader();
            var name = files[0].name;
            reader.onload = function(e) {
                var data = e.target.result;
                success(data);
            };
            reader.readAsBinaryString(files[0]);
    }
    
    
    function SendDataAlumno(){
    	$.ajax({
             url: '/sismanweb/alumno/addBulk',
             type: 'POST', 
             contentType: "application/json; charset=utf-8",
             // el tipo de información que se espera de respuesta
             dataType: "html",
             // la información a enviar
             // (también es posible utilizar una cadena de datos)
             data: string_ws,
             // código a ejecutar si la petición es satisfactoria;
             // la respuesta es pasada como argumento a la función
             success: function(data) {            	             	 
            	 console.log("se entrego datos");
            	 $("#fileData").val("");
            	 $("#cargaExterna").html(data);
             },
             // código a ejecutar si la petición falla;
             // son pasados como argumentos a la función
             // el objeto de la petición en crudo y código de estatus de la petición
             error : function(xhr, status) {
                 alert('Disculpe, existio un problema -- '+xhr+" -- "+status);
             },
             
           });
    	
    	
    	
    	
    	
    	/*
    	$(document).ready(function()
    	{
    		$("#botonExcel").click(function(){
    	        	$.post("/sismanweb/alumno/addBulk", string_ws, function(htmlexterno){
    	        		$("#cargaExterna").html(htmlexterno);
    	    		});
    		});
    	});
    	*/
    }
    
    
    function SendDataDocente(){
    	 $.ajax({
             url: '/sismanweb/docente/addBulk',
             type: 'POST', 
             contentType: "application/json; charset=utf-8",
             dataType: "html",  
             data: string_ws,
             success: function(data) {            	             	 
            	 console.log("se entrego datos");
            	 $("#fileData").val("");
            	 $("#cargaExterna").html(data);
             },
             error : function(xhr, status) {
                 alert('Disculpe, existio un problema -- '+xhr+" -- "+status);
             },
           });
    }
    
 