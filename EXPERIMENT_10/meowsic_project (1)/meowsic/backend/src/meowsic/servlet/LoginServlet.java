package meowsic.servlet;

import meowsic.dao.UserDAO;
import meowsic.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userId   = request.getParameter("userId").trim();
        String password = request.getParameter("password");

        if (userId.isEmpty() || password == null || password.isEmpty()) {
            request.setAttribute("error", "Please fill in all fields.");
            request.getRequestDispatcher("index.html").forward(request, response);
            return;
        }

        try {
            User user = userDAO.loginUser(userId, password);

            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("loggedInUser", user);
                session.setMaxInactiveInterval(30 * 60); // 30 minutes
                response.sendRedirect("dashboard.jsp");
            } else {
                response.sendRedirect("index.html?error=invalid_credentials");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("index.html?error=server_error");
        }
    }
}
