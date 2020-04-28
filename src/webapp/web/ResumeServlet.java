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
        writer.println("<html>\n" +
                "<head>\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">" +
                "<title>Список всех резюме.</title>\n" +
                "<link rel=\"stylesheet\" href=\"css/style.css\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<table>\n" +
                "<caption>Список резюме</caption>\n" +
                "<tr>\n" +
                "<th>UUID</th>\n" +
                "<th>Full_Name</th>\n" +
                "</tr>\n");
        for (Resume r : storage.getAllSorted()) {
            writer.println("<tr>\n");
            writer.println("<td>" + r.getUuid() + "</td>\n");
            writer.println("<td>" + r.getFullName() + "</td>\n");
            writer.println("</tr>\n");
        }
        writer.println("</table>\n" +
                "</body>\n" +
                "</html>\n");
    }
}
