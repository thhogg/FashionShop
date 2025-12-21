/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.home;

import dal.PasswordResetDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.entity.PasswordResetToken;

/**
 *
 * @author Leo
 */
public class VerifyOtp extends HttpServlet {

    private PasswordResetDAO passResetDao;

    @Override
    public void init() {
        passResetDao = PasswordResetDAO.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        Integer userId = (Integer) session.getAttribute("RESET_USER_ID");
        if (userId == null) {
            response.sendRedirect("forgot-password.jsp");
            return;
        }

        // Lấy 4 số OTP từ form
        String c1 = request.getParameter("code1");
        String c2 = request.getParameter("code2");
        String c3 = request.getParameter("code3");
        String c4 = request.getParameter("code4");

        if (c1 == null || c2 == null || c3 == null || c4 == null) {
            request.setAttribute("error", "OTP không hợp lệ");
            request.getRequestDispatcher("verify-otp.jsp").forward(request, response);
            return;
        }

        String otp = c1 + c2 + c3 + c4;

        System.out.println("DEBUG: UserId từ Session: " + userId);
        System.out.println("DEBUG: OTP nhận từ Form: " + otp);

        PasswordResetToken otpRecord
                = passResetDao.findValidOTP(userId, otp);

        if (otpRecord == null) {
            request.setAttribute("error", "Mã OTP không đúng hoặc đã hết hạn");
            request.getRequestDispatcher("verify-otp.jsp").forward(request, response);
            return;
        }
        
        System.out.println("DEBUG: OTP khớp thành công! TokenID: " + otpRecord.getTokenID());

        // OTP đúng → cho phép đổi mật khẩu
        session.setAttribute("OTP_VERIFIED", true);
        session.setAttribute("OTP_ID", otpRecord.getTokenID());

        response.sendRedirect("create-new-pass.jsp");
    }

}
