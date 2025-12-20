<%-- 
    Document   : forgot-pasword
    Created on : Dec 18, 2025, 3:19:53 PM
    Author     : Leo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forgot Password</title>
        <link rel="stylesheet" href="css/forgot-password.css">
    </head>
    <body>
        <button class="home-btn" onclick="window.location.href = 'home'">
            <i class="fa-solid fa-house"></i> Home
        </button>

        <div class="form-container">
            <form action="#">
                <h1>Forgot Password</h1>
 
                <span>Please Enter Your Email Address To Receive a Verification Code</span>
                
                <div class="input-group">
                    <input type="email" placeholder="Email Address" required />
                </div>             
                <button type="submit" class="submit-btn">Send Code</button>
                
                <a href="login.jsp" class="back-to-login">Back to Login</a>
            </form>
        </div>
    </body>
</html>