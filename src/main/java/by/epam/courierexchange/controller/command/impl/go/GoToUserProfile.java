package by.epam.courierexchange.controller.command.impl.go;

import by.epam.courierexchange.controller.command.Command;
import by.epam.courierexchange.controller.command.CommandResult;
import by.epam.courierexchange.controller.command.PagePath;
import by.epam.courierexchange.controller.command.SessionAttribute;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static by.epam.courierexchange.controller.command.CommandResult.ResponseType.FORWARD;

public class GoToUserProfile implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session.getAttribute(SessionAttribute.USER) != null) {
            return new CommandResult(PagePath.PROFILE_PAGE, FORWARD);
        }
        else{
            return new CommandResult(PagePath.LOGIN_PAGE, FORWARD);
        }
    }
}
