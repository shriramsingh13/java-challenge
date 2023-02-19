$(document).ready(function() {

	// Login
	$("#login-btn").click(function() {
		var username = $("#username").val();
		var password = $("#password").val();
		var userLoginModel = {
				username: username,
				password: password
			}
            $("#login-failure").hide();
		$.ajax({
			url: "/api/v1/login",
			type: "POST",
			 data: JSON.stringify(userLoginModel),
             contentType: "application/json",
			success: function(result) {
			if(result){
			localStorage.setItem("token",username+"_"+password);
			//localStorage.token = username+"_"+password;
			window.location.replace("/employee");
			} else {
			$("#login-failure").html("login failed").show();
			}
			},
			error: function(jqXHR, textStatus, errorThrown) {
              $("#login-failure").html("login failed").show();
			}
		});
	});

	// Signup
	$("#signup-btn").click(function() {
		var username = $("#new-username").val();
		var password = $("#new-password").val();
		 $("#signup-success").hide();
		 $("#signup-failure").hide();
		var userLoginModel = {
        				username: username,
        				password: password
        			}
		$.ajax({
			url: "/api/v1/signup",
			type: "POST",
			data: JSON.stringify(userLoginModel),
            contentType: "application/json",
			success: function(result) {
				if(result){
                			$("#signup-failure").html("SignUp successful").show();
                			} else {
                			$("#signup-failure").html("SignUp failed").show();
                			}
			},
			error: function(jqXHR, textStatus, errorThrown) {
				$("#signup-failure").html("SignUp failed").show();
			}
		});
	});
});