<%-- 
    Document   : create-new-pass
    Created on : Dec 18, 2025, 3:57:27 PM
    Author     : Leo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Password</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
        <link rel="stylesheet" href="css/forgot-password.css">
    </head>
    <body>
        <button class="home-btn" onclick="window.location.href = 'home'">
            <i class="fa-solid fa-house"></i> Home
        </button>

        <div class="form-container">
            <form action="reset-password" method="POST" onsubmit="return validatePassword()">
                <h1>Reset Password</h1>
                <p style="font-size: 13px; color: #777;">Please enter your new password below.</p>
                
                <div class="input-group" style="width: 100%; position: relative;">
                    <i class="fa fa-lock" style="position: absolute; left: 15px; top: 22px; color: #c0392b;"></i>
                    <input type="password" id="newPassword" name="newPassword" 
                           placeholder="New Password" required 
                           style="padding-left: 40px;" />
                </div>
                
                <div class="input-group" style="width: 100%; position: relative;">
                    <i class="fa fa-shield-alt" style="position: absolute; left: 15px; top: 22px; color: #c0392b;"></i>
                    <input type="password" id="confirmPassword" name="confirmPassword" 
                           placeholder="Confirm New Password" required 
                           style="padding-left: 40px;" />
                </div>
                
                <div id="error-message" style="color: #e74c3c; font-size: 12px; margin-top: 5px; min-height: 15px;"></div>

                <button type="submit" class="submit-btn" style="margin-top: 25px;">Update Password</button>
                
                <a href="login.jsp" class="back-to-login">Cancel and Login</a>
            </form>
        </div>

        <script>
            function validatePassword() {
                const pass = document.getElementById("newPassword").value;
                const confirm = document.getElementById("confirmPassword").value;
                const error = document.getElementById("error-message");

                if (pass.length < 6) {
                    error.innerText = "Password must be at least 6 characters!";
                    return false;
                }
                if (pass !== confirm) {
                    error.innerText = "Passwords do not match!";
                    return false;
                }
                return true;
            }
        </script>
    </body>
</html>