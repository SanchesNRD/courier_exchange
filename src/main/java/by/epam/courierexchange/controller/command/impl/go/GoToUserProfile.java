package by.epam.courierexchange.controller.command.impl.go;

import by.epam.courierexchange.controller.command.Command;
import by.epam.courierexchange.controller.command.CommandResult;
import by.epam.courierexchange.controller.command.PagePath;
import by.epam.courierexchange.controller.command.SessionAttribute;
import by.epam.courierexchange.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static by.epam.courierexchange.controller.command.CommandResult.ResponseType.FORWARD;

public class GoToUserProfile implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(SessionAttribute.USER);
        if(user != null) {
            return new CommandResult(PagePath.PROFILE_PAGE, FORWARD);
        }
        else{
            return new CommandResult(PagePath.LOGIN_PAGE, FORWARD);
        }
    }
}
