package by.training.carservicebook.controller;

import by.training.carservicebook.action.Action;
import by.training.carservicebook.action.MainAction;
import by.training.carservicebook.entity.Role;
import by.training.carservicebook.entity.User;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Logger;

@Log4j2
public class SecurityFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if(request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
			HttpServletRequest httpRequest = (HttpServletRequest)request;
			HttpServletResponse httpResponse = (HttpServletResponse)response;
			Action action = (Action)httpRequest.getAttribute("action");
			Set<Role> allowRoles = action.getAllowRoles();
			String userName = "unauthorized user";
			HttpSession session = httpRequest.getSession(false);
			User user = null;
			if(session != null) {
				user = (User)session.getAttribute("authorizedUser");
				action.setAuthorizedUser(user);
				String errorMessage = (String)session.getAttribute("SecurityFilterMessage");
				if(errorMessage != null) {
					httpRequest.setAttribute("message", errorMessage);
					session.removeAttribute("SecurityFilterMessage");
				}
			}
			boolean canExecute = allowRoles == null;
			if(user != null) {
				userName = "\"" + user.getLogin() + "\" user";
				canExecute = canExecute || allowRoles.contains(user.getRole());
			}
			if(canExecute) {
				chain.doFilter(request, response);
			} else {
				log.info(String.format("Trying of %s access to forbidden resource \"%s\"", userName, action.getName()));
				if(session != null && action.getClass() != MainAction.class) {
					session.setAttribute("SecurityFilterMessage", "Доступ запрещён");
				}
				httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.html");
			}
		} else {
			log.error("It is impossible to use HTTP filter");
			request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
		}
	}

	@Override
	public void destroy() {}
}
