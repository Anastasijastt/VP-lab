package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.service.ChefService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ChefListServlet", urlPatterns = "/listChefs")
public class ChefListServlet extends HttpServlet {

    private final ChefService chefService;
    private final SpringTemplateEngine springTemplateEngine;

    public ChefListServlet(ChefService chefService, SpringTemplateEngine springTemplateEngine) {
        this.chefService = chefService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        List<Chef> chefs = chefService.listChefs();
        context.setVariable("chefs", chefs);

        springTemplateEngine.process("listChefs.html", context, resp.getWriter());

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String chefId = req.getParameter("chefId");
        System.out.println("Selected chef ID: " + chefId);

        if (chefId == null || chefId.isEmpty()) {
            System.out.println("ERROR: No chef selected!");
        }

        resp.sendRedirect("/listChefs");
    }
}
