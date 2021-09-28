package by.epam.courierexchange.controller.command.impl;

import by.epam.courierexchange.controller.command.Command;
import by.epam.courierexchange.controller.command.CommandResult;
import by.epam.courierexchange.controller.command.SessionAttribute;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


import static by.epam.courierexchange.controller.command.CommandResult.ResponseType.*;
import static by.epam.courierexchange.controller.command.RequestParameter.*;

public class ChangeLocaleCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String currentUrl = request.getParameter("current_url");
        String url = currentUrl.substring(52);
        String locale = request.getParameter(LOCALE);
        if(locale.equals("ru_RU")){
            session.setAttribute(SessionAttribute.LOCALE, "EN_en");
        }else{
            session.setAttribute(SessionAttribute.LOCALE, "ru_RU");
        }
        CommandResult commandResult = new CommandResult(url, FORWARD);
        return commandResult;
    }
}
