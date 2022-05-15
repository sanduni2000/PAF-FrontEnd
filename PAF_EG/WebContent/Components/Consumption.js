$(document).ready(function() {
	$("#alertSuccess").hide();
	$("#alertError").hide();
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation-------------------
	var status = validateProjectForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}

	// If valid------------------------
	var type = ($("#hidProjectIDSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "CunsumptionAPI",
		type : type,
		data : $("#formConsumptionManagement").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onProjectSaveComplete(response.responseText, status);
		}
	});
});

function onProjectSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);

		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();

			$("#divProjectGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}

	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}

	$("#hidIDSave").val("");
	$("#formProduct")[0].reset();
}

// UPDATE==========================================
$(document).on(
		"click",
		".btnUpdate",
		function(event) {
			$("#hidIDSave").val(
					$(this).closest("tr").find('#hidIDUpdate').val());
			
			$("#Dname").val($(this).closest("tr").find('td:eq(0)').text());
		
			$("#Month").val($(this).closest("tr").find('td:eq(2)').text());
			
			
		});

// REMOVE===========================================
$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "CunsumptionManagementAPI",
		type : "DELETE",
		data : "id=" + $(this).data("ID"),
		dataType : "text",
		complete : function(response, status) {
			onProjectDeleteComplete(response.responseText, status);
		}
	});
});

function onProjectDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);

		if (resultSet.status.trim() == "success") {

			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();

			$("#divProjectGrid").html(resultSet.data);

		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}

	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

// CLIENT-MODEL=========================================================================
function validateProjectForm() {
	
	if ($("#Month").val().trim() == "") {
		return "Insert Month:";
	}

	
	if ($("#Note").val().trim() == "") {
		return "Insert Note:";
	}
	
	
	
	
	var tmpunit_usage = $("#unit_usage").val().trim();
	 if (!$.isNumeric(tmpunit_usage)) 
	 {
		 return "Insert Unit Usage.";
	 }

	
	 var tmpunit_price = $("#unit_price").val().trim();
	 if (!$.isNumeric(tmpunit_price)) 
	 {
		 return "Insert Price.";
	 }
	 
	 var tmptotal = $("#total").val().trim();
	 if (!$.isNumeric(tmptotal)) 
	 {
		 return "Insert Total.";
	 }


	return true;
}