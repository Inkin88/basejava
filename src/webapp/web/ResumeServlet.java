package webapp.web;

import webapp.model.Resume;
import webapp.storage.SqlStorage;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static webapp.Config.get;

public class ResumeServlet extends HttpServlet {

    SqlStorage storage;

    @Override
    public void init() {
        storage = new SqlStorage(get().getUrl(), get().getUser(), get().getPassword());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.println("<table>\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<th> UUID <th>\n" +
                "<th> Full_Name <th>\n" +
                "<tr>");
        for (Resume r : storage.getAllSorted()) {
            writer.println("<tr>");
            writer.println("<td>" + r.getUuid() + "</td>");
            writer.println("<td>" + r.getFullName() + "</td>");
            writer.println("</tr>");
        }
        writer.println("<tbody>\n" +
                "<table>\n");
    }
}
