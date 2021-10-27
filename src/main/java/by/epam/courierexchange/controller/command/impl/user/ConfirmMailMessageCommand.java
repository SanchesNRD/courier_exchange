package by.epam.courierexchange.controller.command.impl.user;

import by.epam.courierexchange.controller.command.Command;
import by.epam.courierexchange.controller.command.CommandResult;
import by.epam.courierexchange.controller.command.PagePath;
import by.epam.courierexchange.controller.command.SessionAttribute;
import by.epam.courierexchange.model.entity.User;
import by.epam.courierexchange.util.MailSender;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ConfirmMailMessageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(SessionAttribute.USER);
        MailSender.send(user.getMail(), MailSender.confirmYourProfileMessage(user.getId()));
        return new CommandResult(PagePath.PROFILE_PAGE, CommandResult.ResponseType.FORWARD);
    }
}
