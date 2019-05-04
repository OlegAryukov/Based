package ru.aryukov.web.servlets;

import ru.aryukov.domain.Tarif;
import ru.aryukov.service.TarifService;
import ru.aryukov.utils.ConnectionUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class TarifServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text / html;charset=UTF-8");
        PrintWriter pw = resp.getWriter();
        pw.println("<B> Список тарифов</B>");
        pw.println("<table border = 1>");
        try {
            ConnectionUtils inst = ConnectionUtils.getInstance();
            List tarifsList = inst.getTarifs();
            for (Iterator it = tarifsList.iterator(); it.hasNext();) {
                Tarif tarif = (Tarif) it.next();
                pw.println("<tr>");
                pw.println("<td>" + tarif.getId() + "</td>");
                pw.println("<td>" + tarif.getName() + "</td>");
                pw.println("<td>" + tarif.getPrice() + "</td>");
                pw.println("</tr >");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
        pw.println("</table>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        TarifService service = new TarifService();
        String tarifId = request.getParameter("tarifId");
        response.sendRedirect("main.jsp");
    }
    private int checkAction(HttpServletRequest req) {
        if (req.getParameter("Change") != null) {
            return 1;
        }
        if (req.getParameter("Delete") != null) {
            return 2;
        }
        if (req.getParameter("Add") != null) {
            return 3;
        }
        return 0;
    }
}
