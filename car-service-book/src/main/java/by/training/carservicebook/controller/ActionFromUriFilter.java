package by.training.carservicebook.controller;

import by.training.carservicebook.action.*;
import by.training.carservicebook.action.admin.UserBanAction;
import by.training.carservicebook.action.admin.UserListAction;
import by.training.carservicebook.action.client.*;
import by.training.carservicebook.action.master.CarRecordApprovedAction;
import by.training.carservicebook.action.master.CarRecordDoneAction;
import by.training.carservicebook.action.master.CarRecordTenderListAction;
import by.training.carservicebook.action.master.OfferMakeAction;
import by.training.carservicebook.action.user.UserSaveAction;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Log4j2
public class ActionFromUriFilter implements Filter {

	private static Map<String, Class<? extends Action>> actions = new ConcurrentHashMap<>();

	static {
		actions.put("/", MainAction.class);
		actions.put("/index", MainAction.class);
		actions.put("/login", LoginAction.class);
		actions.put("/logout", LogoutAction.class);
		actions.put("/register", RegisterAction.class);


		actions.put("/car/list", CarListAction.class);
		actions.put("/car/edit", CarEditAction.class);
		actions.put("/car/save", CarSaveAction.class);
		actions.put("/car/delete", CarDeleteAction.class);

		actions.put("/car_record/list", CarRecordListAction.class);
		actions.put("/car_record/edit", CarRecordEditAction.class);
		actions.put("/car_record/save", CarRecordSaveAction.class);
		actions.put("/car_record/delete", CarRecordDeleteAction.class);
		actions.put("/car_record/tender_list", CarRecordTenderListAction.class);
		actions.put("/car_record/offer", OfferMakeAction.class);
		actions.put("/car_record/approved", CarRecordApprovedAction.class);
		actions.put("/car_record/done", CarRecordDoneAction.class);
		actions.put("/car_record/offer_list", OfferListAction.class);
		actions.put("/car_record/history", CarRecordHistoryAction.class);


		actions.put("/user/list", UserListAction.class);
		actions.put("/user/ban", UserBanAction.class);
		actions.put("/user/save", UserSaveAction.class);

		actions.put("/offer/accept", OfferAcceptAction.class);



	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if(request instanceof HttpServletRequest) {
			HttpServletRequest httpRequest = (HttpServletRequest)request;
			String contextPath = httpRequest.getContextPath();
			String uri = httpRequest.getRequestURI();
			log.debug(String.format("Starting of processing of request for URI \"%s\"", uri));
			int beginAction = contextPath.length();
			int endAction = uri.lastIndexOf('.');
			String actionName;
			if(endAction >= 0) {
				actionName = uri.substring(beginAction, endAction);
			} else {
				actionName = uri.substring(beginAction);
			}
			Class<? extends Action> actionClass = actions.get(actionName);
			try {
				Action action = actionClass.getDeclaredConstructor().newInstance();
				action.setName(actionName);
				httpRequest.setAttribute("action", action);
				chain.doFilter(request, response);
			} catch (InstantiationException | IllegalAccessException | NullPointerException | NoSuchMethodException | InvocationTargetException e) {
				log.error("It is impossible to create action handler object", e);
				httpRequest.setAttribute("error", String.format("?????????????????????? ?????????? %s ???? ?????????? ???????? ?????????????????? ????????????????", uri));
				httpRequest.getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
			}
		} else {
			log.error("It is impossible to use HTTP filter");
			request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
		}
	}

	@Override
	public void destroy() {}
}
