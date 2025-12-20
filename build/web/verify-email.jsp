<%-- 
    Document   : verify-email
    Created on : Dec 18, 2025, 3:40:22 PM
    Author     : Leo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Verify Email</title>
        <link rel="stylesheet" href="css/forgot-password.css">
    </head>
    <body>
        <button class="home-btn" onclick="window.location.href = 'home'">
            <i class="fa-solid fa-house"></i> Home
        </button>

        <div class="form-container">
            <form action="verify" method="POST">
                <h1>Verify Your Email</h1>
                <p>
                    Please enter the 4-digit code sent to <br>
                    <strong style="color: #c0392b;">${youremail}</strong>
                </p>

                <div class="otp-container">
                    <input type="number" class="otp-input" maxlength="1" oninput="moveNext(this, 'otp2')" id="otp1" name="code1" required>
                    <input type="number" class="otp-input" maxlength="1" oninput="moveNext(this, 'otp3')" id="otp2" name="code2" required>
                    <input type="number" class="otp-input" maxlength="1" oninput="moveNext(this, 'otp4')" id="otp3" name="code3" required>
                    <input type="number" class="otp-input" maxlength="1" id="otp4" name="code4" required>
                </div>

                <div>
                    <span>Didn't receive the code?</span>
                    <a href="resend-code" style="color:#c0392b;">Resend code</a>
                </div>

                <button type="submit" class="submit-btn">Verify</button>
                
                <a href="login.jsp" class="back-to-login">Back to Login</a>
            </form>
        </div>

        <script>
            // Hàm tự động nhảy sang ô tiếp theo khi nhập xong 1 số
            function moveNext(current, nextId) {
                if (current.value.length >= 1) {
                    document.getElementById(nextId).focus();
                }
            }
        </script>
    </body>
</html>
