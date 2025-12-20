<%-- 
    Document   : password-success
    Created on : Dec 18, 2025, 4:00:28 PM
    Author     : Leo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Success</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
        <link rel="stylesheet" href="css/forgot-password.css">
        <style>
            .success-icon {
                font-size: 80px;
                color: #2ecc71; /* Màu xanh lá hiện đại */
                margin-bottom: 20px;
                animation: scaleIn 0.5s cubic-bezier(0.175, 0.885, 0.32, 1.275);
            }

            @keyframes scaleIn {
                0% { transform: scale(0); }
                100% { transform: scale(1); }
            }

            .success-message {
                font-size: 18px;
                color: #333;
                margin-bottom: 30px;
                font-weight: 500;
            }
        </style>
    </head>
    <body>
        <div class="form-container" style="min-height: 350px;">
            <div class="form" style="display: flex; flex-direction: column; align-items: center; justify-content: center; height: 100%; text-align: center; padding: 40px;">
                <div class="success-icon">
                    <i class="fa-solid fa-circle-check"></i>
                </div>
                <h1>Success!</h1>
                <p class="success-message">Your password has been reset successfully. You can now log in with your new password.</p>
                
                <button onclick="window.location.href='login.jsp'" class="submit-btn">
                    Go to Login
                </button>
            </div>
        </div>
    </body>
</html>
