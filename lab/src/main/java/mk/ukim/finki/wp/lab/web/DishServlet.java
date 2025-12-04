package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "DishServlet", urlPatterns = "/dish")
public class DishServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final DishService dishService;
    private final ChefService chefService;

    public DishServlet(SpringTemplateEngine springTemplateEngine, DishService dishService, ChefService chefService) {
        this.springTemplateEngine = springTemplateEngine;
        this.dishService = dishService;
        this.chefService = chefService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        String chefId = req.getParameter("chefId");
        if (chefId != null && !chefId.isEmpty()) {
            Chef selectedChef = this.chefService.findById(Long.parseLong(chefId));
            context.setVariable("chef", selectedChef);
        }

        context.setVariable("dishes", this.dishService.listDishes());

        springTemplateEngine.process("listDishes.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String chefId = req.getParameter("chefId");
        String dishId = req.getParameter("dishId");

        System.out.println("========== DISH SERVLET DEBUG ==========");
        System.out.println("chefId: " + chefId);
        System.out.println("dishId: " + dishId);
        System.out.println("========================================");


        if (chefId != null && !chefId.isEmpty() && (dishId == null || dishId.isEmpty())) {
            IWebExchange webExchange = JakartaServletWebApplication
                    .buildApplication(getServletContext())
                    .buildExchange(req, resp);

            WebContext context = new WebContext(webExchange);

            Chef selectedChef = this.chefService.findById(Long.parseLong(chefId));
            context.setVariable("chef", selectedChef);
            context.setVariable("dishes", this.dishService.listDishes());

            springTemplateEngine.process("listDishes.html", context, resp.getWriter());
            return;
        }

        if (chefId != null && dishId != null && !chefId.isEmpty() && !dishId.isEmpty()) {
            req.getSession().setAttribute("selectedChefId", chefId);
            req.getSession().setAttribute("selectedDishId", dishId);
            resp.sendRedirect("/chefDetails");
            return;
        }

        resp.sendRedirect("/listChefs");
    }
}