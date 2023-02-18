$(document).ready(function() {
	// Login
	$("#login-btn").click(function() {
		var username = $("#username").val();
		var password = $("#password").val();
		$.ajax({
			url: "/login",
			type: "POST",
			data: {
				username: username,
				password: password
			},
			success: function(result) {
				localStorage.setItem("token", result.token);
				window.location.href = "/employee.html";
			},
			error: function(jqXHR, textStatus, errorThrown) {
				Swal.fire({
					title: "Error",
					text: "Invalid username or password",
					icon: "error",
					confirmButtonText: "OK"
				});
			}
		});
	});

	// Signup
	$("#signup-btn").click(function() {
		var username = $("#new-username").val();
		var password = $("#new-password").val();
		$.ajax({
			url: "/signup",
			type: "POST",
			data: {
				username: username,
				password: password
			},
			success: function(result) {
				Swal.fire({
					title: "Success",
					text: "User created successfully",
					icon: "success",
					confirmButtonText: "OK"
				});
			},
			error: function(jqXHR, textStatus, errorThrown) {
				Swal.fire({
					title: "Error",
					text: "Unable to create user",
					icon: "error",
					confirmButtonText: "OK"
				});
			}
		});
	});
});