$(document).ready(function () {
    // Get all employees
    var tableRowCount = 1;
    var token = localStorage.getItem("token");
    if ( typeof(token) == "undefined" || token == null || token =='null' ){
    alert("Please Login or SignUp to view Employee page");
    window.location.replace("/login");
    }
        $.ajax({
            url: "/api/v1/employees",
            type: "GET"
        })
            .done(function (result) {
                var employees = result;
                var tableBody = $("#employees-table > tbody");
                for (var i = 0; i < employees.length; i++) {
                    var employee = employees[i];
                    var row = $("<tr>");
                    row.append($("<th scope='row'>").text(employee.id));
                    row.append($("<td>").text(employee.name));
                    row.append($("<td>").text(employee.salary));
                    row.append($("<td>").text(employee.department));
                    tableBody.append(row);
                    tableRowCount++;
                }
            })
            .fail(function (jqXHR, textStatus, errorThrown) {
            });

    // Create employee
    $("#create-employee-btn").click(function (e) {
     e.preventDefault();
        var name = $("#name").val();
        var salary = $("#salary").val();
        $("#create-emp-data-success").hide();
        $("#create-emp-data-failure").hide();
        var department = $("#department").val();
        var employee = {
            name: name,
            salary: salary,
            department: department
        }
        $.ajax({
            url: "/api/v1/saveEmployee",
            type: "POST",
            headers: {
                        				authorization:  localStorage.getItem("token")
                        			},
            data: JSON.stringify(employee),
            contentType: "application/json",
            success: function (result) {
                if (result) {
                    $("#create-emp-data-success").html("Employee data created successfully").show();
                } else {
                    $("#create-emp-data-failure").html("Employee data creation failed").show();
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $("#create-emp-data-success").html("Employee data creation failed").show();
            }
        });
    });

    // Update employee
    $("#update-employee-btn").click(function (e) {
     e.preventDefault();
        var id = $("#update-by-id").val();
        var name = $("#update-name").val();
        var salary = $("#update-salary").val();
        var department = $("#update-department").val();
        $("#update-emp-data-success").hide();
        $("#update-emp-data-failure").hide();
        var updateEmployee = {
            id: id,
            name: name,
            salary: salary,
            department: department
        }
        $.ajax({
            url: "/api/v1/updateEmployee",
            type: "PUT",
            headers: {
                        				authorization:  localStorage.getItem("token")
                        			},
            data: JSON.stringify(updateEmployee),
            contentType: "application/json",
            success: function (result) {
                if (result) {
                    $("#update-emp-data-success").html("Employee data updated successfully").show();
                } else {
                    $("#update-emp-data-failure").html("Employee data updation failed").show();
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $("#update-emp-data-failure").html("Employee data updation failed").show();
            }
        });
    });

    // Delete employee
    $("#delete-employee-btn").click(function (e) {
     e.preventDefault();
        var id = $("#delete-by-id").val();
        $("#delete-emp-data-success").hide();
        $("#delete-emp-data-failure").hide();
        $.ajax({
            url: "/api/v1/deleteEmployees",
            type: "DELETE",
           headers: {
                       				authorization:  localStorage.getItem("token")
                       			},
            data: JSON.stringify({ id: id }),
            contentType: "application/json",
            success: function (result) {
                if (result) {
                    $("#delete-emp-data-success").html("Employee data deleted successfully").show();
                } else {
                    $("#delete-emp-data-failure").html("Employee data deletion failed").show();
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $("#delete-emp-data-failure").html("Employee data deletion failed").show();
            }
        });
    });
    // Search employee
    $("#search-employee-btn").click(function (e) {
    e.preventDefault();
        var str = $("#search-by-id-or-name").val();
        $("#search-emp-data-success").hide();
        $("#search-emp-data-failure").hide();
        $("#search-table > tbody > tr").remove();
        var id = null;
        var name = null;
        if (!isNaN(parseFloat(str)) && !isNaN(str- 0)) {
            id = str;
        } else {
            name = str;
        }
        var updateEmployee = {
            id: id,
            name: name
        }
        $.ajax({
            url: "/api/v1/searchEmployees",
            type: "POST",
            data: JSON.stringify(updateEmployee),
            contentType: "application/json",
            success: function (result) {
                var employees = result;
                var tableBody = $("#search-table > tbody");
                for (var i = 0; i < employees.length; i++) {
                    var employee = employees[i];
                    var row = $("<tr>");
                    row.append($("<th scope='row'>").text(employee.id));
                    row.append($("<td>").text(employee.name));
                    row.append($("<td>").text(employee.salary));
                    row.append($("<td>").text(employee.department));
                    tableBody.append(row);
                    tableRowCount++;
                }
                if (employees.length === 0) {
                    $("#search-emp-data-failure").html("No data found").show();
                } else {
                    $("#search-emp-data-success").html("Search data found").show();
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $("#search-emp-data-failure").html("Search failed due to some issue, please retry").show();
            }
        });
    });
    	$("#logout").click(function() {
         localStorage.setItem("token",null);
          window.location.replace("/login");
    	});
});