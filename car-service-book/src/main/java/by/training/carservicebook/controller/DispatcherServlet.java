package by.training.carservicebook.controller;

import by.training.carservicebook.action.Action;
import by.training.carservicebook.action.ActionManager;
import by.training.carservicebook.action.ActionManagerFactory;
import by.training.carservicebook.dao.connection.ConnectionCreator;
import by.training.carservicebook.dao.exception.DaoException;
import by.training.carservicebook.dao.mysql.TransactionFactoryImpl;
import by.training.carservicebook.dao.pool.ConnectionPool;
import by.training.carservicebook.service.ServiceFactory;
import by.training.carservicebook.service.ServiceFactoryImpl;
import by.training.carservicebook.service.exception.ServiceException;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

@Log4j2
public class DispatcherServlet extends HttpServlet {

	public static final String LOG_FILE_NAME = "log.txt";
	//public static final Level LOG_LEVEL = Level.ALL;
	public static final int DB_POOL_START_SIZE = 10;
	public static final int DB_POOL_MAX_SIZE = 1000;
	public static final int DB_POOL_CHECK_CONNECTION_TIMEOUT = 0;

	public void init() {
		try {
			Properties properties = new Properties();
			properties.load(ConnectionCreator.class.getResourceAsStream("/database.properties"));
			String driverName = (String) properties.get("db.driver");
			Class.forName(driverName);
			ConnectionPool.getInstance().init(driverName, (String) properties.get("db.url"), (String) properties.get("user"),
					(String) properties.get("password"), DB_POOL_START_SIZE, DB_POOL_MAX_SIZE, DB_POOL_CHECK_CONNECTION_TIMEOUT);
		} catch(DaoException | IOException e) {
			log.error("It is impossible to initialize application", e);
			destroy();
		} catch (ClassNotFoundException e) {
			log.error(e);
		}
	}

	public ServiceFactory getFactory() throws ServiceException {
		try {
			return new ServiceFactoryImpl(new TransactionFactoryImpl());
		} catch (DaoException e) {
			log.error(e);
		}
		return null;
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		process(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Action action = (Action)request.getAttribute("action");
		try {
			HttpSession session = request.getSession(false);
			if(session != null) {
				@SuppressWarnings("unchecked")
				Map<String, Object> attributes = (Map<String, Object>)session.getAttribute("redirectedData");
				if(attributes != null) {
					for(String key : attributes.keySet()) {
						request.setAttribute(key, attributes.get(key));
					}
					session.removeAttribute("redirectedData");
				}
			}
			ActionManager actionManager = ActionManagerFactory.getManager(getFactory());
			Action.Forward forward = actionManager.execute(action, request, response);
			actionManager.close();
			if(session != null && forward != null && !forward.getAttributes().isEmpty()) {
				session.setAttribute("redirectedData", forward.getAttributes());
			}
			String requestedUri = request.getRequestURI();
			if(forward != null && forward.isRedirect()) {
				String redirectedUri = request.getContextPath() + forward.getForward();
				log.debug(String.format("Request for URI \"%s\" id redirected to URI \"%s\"", requestedUri, redirectedUri));
				response.sendRedirect(redirectedUri);
			} else {
				String jspPage;
				if(forward != null) {
					jspPage = forward.getForward();
				} else {
					jspPage = action.getName() + ".jsp";
				}
				jspPage = "/WEB-INF/jsp" + jspPage;
				log.debug(String.format("Request for URI \"%s\" is forwarded to JSP \"%s\"", requestedUri, jspPage));
				getServletContext().getRequestDispatcher(jspPage).forward(request, response);
			}
		} catch(ServiceException e) {
			log.error("It is impossible to process request", e);
			request.setAttribute("error", "Ошибка обработки данных");
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
		}
	}
}
