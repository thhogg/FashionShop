/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.home;

import dal.PasswordResetDAO;
import dal.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Leo
 */
public class CreateNewPassword extends HttpServlet {
    
    private PasswordResetDAO passResetDao;
    private UserDAO userDao;

    @Override
    public void init() {
        passResetDao = PasswordResetDAO.getInstance();
        userDao = UserDAO.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        Boolean verified = (Boolean) session.getAttribute("OTP_VERIFIED");
        Integer userId = (Integer) session.getAttribute("RESET_USER_ID");
        Integer otpId = (Integer) session.getAttribute("OTP_ID");

        if (verified == null || !verified || userId == null) {
            response.sendRedirect("forgot-password.jsp");
            return;
        }

        String password = request.getParameter("newPassword");
        String confirm = request.getParameter("confirmPassword");

        if (!password.equals(confirm)) {
            request.setAttribute("error", "Mật khẩu không khớp");
            request.getRequestDispatcher("create-new-pass.jsp")
                    .forward(request, response);
            return;
        }

        userDao.updatePassword(userId, password);

//        passResetDao.markUsed(otpId);

        // Xóa session reset
        session.removeAttribute("OTP_VERIFIED");
        session.removeAttribute("RESET_USER_ID");
        session.removeAttribute("OTP_ID");

        response.sendRedirect("password-success.jsp");
    }

}
