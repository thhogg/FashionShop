/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.home;

import Tool.MailUtil;
import dal.PasswordResetDAO;
import dal.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Random;
import model.entity.User;

/**
 *
 * @author Leo
 */
public class ForgotPassword extends HttpServlet {

    private UserDAO userDao;
    private PasswordResetDAO passResetDao;

    @Override
    public void init() {
        userDao = UserDAO.getInstance();
        passResetDao = PasswordResetDAO.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("forgot-password.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");

        User user = userDao.findByEmail(email);
        if (user == null) {
            request.setAttribute("error", "Email không tồn tại!");
            request.getRequestDispatcher("forgot-password.jsp").forward(request, response);
            return;
        }

        String token = String.valueOf(1000 + new Random().nextInt(9000));
        LocalDateTime expiry = LocalDateTime.now().plusMinutes(20);

        passResetDao.saveToken(user.getUserID(), token, expiry);

        MailUtil.send(
            email,
            "OTP Reset Password",
            "<h3>Mã OTP của bạn</h3><h1>" + token + "</h1><p>Hết hạn sau 5 phút</p>"
        );

        request.setAttribute("email", email);
        request.getRequestDispatcher("verify-otp.jsp").forward(request, response);

        request.setAttribute("msg", "Đã gửi link reset qua email");
        request.getRequestDispatcher("forgot-password.jsp").forward(request, response);
    }

}
