$(document).ready(function() {
	// Get all employees
	$.ajax({
		url: "/employees",
		type: "GET"
	})
		.done(function(result) {
			var employees = result;
			var tableBody = $("#employees-table tbody");
			for (var i = 0; i < employees.length; i++) {
				var employee = employees[i];
				var row = $("<tr>");
				row.append($("<td>").text(employee.name));
				row.append($("<td>").text(employee.employeeNumber));
				row.append($("<td>").text(employee.age));
				row.append($("<td>").text(employee.address));
				tableBody.append(row);
			}
		})
		.fail(function(jqXHR, textStatus, errorThrown) {
			Swal.fire({
				title: "Error",
				text: "Unable to get employees",
				icon: "error",
				confirmButtonText: "OK"
			});
		});

	// Create employee
	$("#create-employee-btn").click(function() {
		var name = $("#name").val();
		var employeeNumber = $("#employee-number").val();
		var age = $("#age").val();
		var address = $("#address").val();
		$.ajax({
			url: "/employees",
			type: "POST",
			headers: {
				Authorization: "Bearer " + localStorage.getItem("token")
			},
			data: {
				name: name,
				employeeNumber: employeeNumber,
				age: age,
				address: address
			},
			success: function(result) {
				Swal.fire({
					title: "Success",
					text: "Employee created successfully",
					icon: "success",
					confirmButtonText: "OK"
				});
				$("#employees-table tbody").append(
					"<tr>" +
					"<td>" +
					name +
					"</td>" +
					"<td>" +
					employeeNumber +
					"</td>" +
					"<td>" +
					age +
					"</td>" +
					"<td>" +
					address +
					"</td>" +
					"</tr>"
				);
			},
			error: function(jqXHR, textStatus, errorThrown) {
				Swal.fire({
					title: "Error",
					text: "Unable to create employee",
					icon: "error",
					confirmButtonText: "OK"
				});
			}
		});
	});
});