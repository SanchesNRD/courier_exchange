package by.epam.courierexchange.controller.command.impl;

import by.epam.courierexchange.controller.command.Command;
import by.epam.courierexchange.controller.command.CommandResult;
import by.epam.courierexchange.controller.command.SessionAttribute;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


import static by.epam.courierexchange.controller.command.CommandResult.ResponseType.*;
import static by.epam.courierexchange.controller.command.RequestParameter.*;

public class ChangeLocaleCommand implements Command {
    private static final String RU_LOCALE = "ru_RU";
    private static final String EN_LOCALE = "en_EN";
    private static final String CURRENT_URL = "current_url";
    private static final int POSITION_OF_URL = 52;

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String currentUrl = request.getParameter(CURRENT_URL);
        String url = currentUrl.substring(POSITION_OF_URL);
        String locale = request.getParameter(LOCALE);
        if(locale.equals(RU_LOCALE)){
            session.setAttribute(SessionAttribute.LOCALE, EN_LOCALE);
        }else{
            session.setAttribute(SessionAttribute.LOCALE, RU_LOCALE);
        }
        return new CommandResult(url, FORWARD);
    }
}
