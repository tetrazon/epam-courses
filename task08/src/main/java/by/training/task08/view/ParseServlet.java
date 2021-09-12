package by.training.task08.view;

import by.training.task08.controller.FlowerController;
import by.training.task08.controller.exception.FlowerControllerException;
import by.training.task08.controller.impl.FlowerControllerImpl;
import by.training.task08.entity.Flower;
import lombok.extern.log4j.Log4j2;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

@Log4j2
@WebServlet("/parser")
public class ParseServlet extends HttpServlet {
    private FlowerController flowerController= new FlowerControllerImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String parseType = request.getParameter("parseType");
        String fileName = request.getParameter("fileName");
        Set<Flower> flowerSet;
        try {
            flowerSet = flowerController.parseFlowers(parseType, fileName);
            HttpSession session = request.getSession(true);
            session.setAttribute("flowers", flowerSet);
            log.info("file to parse: " + fileName);
            log.info("flowers: " + flowerSet);
            response.sendRedirect("result.jsp");
        } catch (FlowerControllerException e) {
            response.sendRedirect("error.jsp");
        }
    }

}
