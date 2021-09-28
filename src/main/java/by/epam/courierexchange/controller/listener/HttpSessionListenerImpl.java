package by.epam.courierexchange.controller.listener;

import by.epam.courierexchange.controller.command.RequestParameter;
import by.epam.courierexchange.controller.command.SessionAttribute;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;


import jakarta.servlet.annotation.WebListener;

@WebListener
public class HttpSessionListenerImpl implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        session.setMaxInactiveInterval(20 * 60);
        session.setAttribute(RequestParameter.LOCALE, "ru_RU");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

    }
}
