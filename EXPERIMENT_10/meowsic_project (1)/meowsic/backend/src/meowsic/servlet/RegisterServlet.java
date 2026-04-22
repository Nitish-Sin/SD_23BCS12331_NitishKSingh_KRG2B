package meowsic.servlet;

import meowsic.dao.UserDAO;
import meowsic.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userId      = request.getParameter("userId").trim();
        String password    = request.getParameter("password");
        String confirmPwd  = request.getParameter("confirmPassword");
        String displayName = request.getParameter("displayName").trim();
        String email       = request.getParameter("email").trim();

        // Basic validation
        if (userId.isEmpty() || password.isEmpty() || displayName.isEmpty()) {
            response.sendRedirect("index.html?error=missing_fields&tab=register");
            return;
        }

        if (userId.length() < 4 || userId.length() > 50) {
            response.sendRedirect("index.html?error=userid_length&tab=register");
            return;
        }

        if (!userId.matches("[a-zA-Z0-9_]+")) {
            response.sendRedirect("index.html?error=userid_format&tab=register");
            return;
        }

        if (password.length() < 6) {
            response.sendRedirect("index.html?error=password_short&tab=register");
            return;
        }

        if (!password.equals(confirmPwd)) {
            response.sendRedirect("index.html?error=password_mismatch&tab=register");
            return;
        }

        try {
            if (userDAO.userExists(userId)) {
                response.sendRedirect("index.html?error=userid_taken&tab=register");
                return;
            }

            User newUser = new User(userId, password, displayName, email);
            boolean success = userDAO.registerUser(newUser);

            if (success) {
                response.sendRedirect("index.html?success=registered");
            } else {
                response.sendRedirect("index.html?error=register_failed&tab=register");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("index.html?error=server_error&tab=register");
        }
    }
}
