package by.epam.courierexchange.controller.command.impl.user;

import by.epam.courierexchange.controller.command.*;
import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.entity.User;
import by.epam.courierexchange.model.service.impl.UserServiceImpl;
import by.epam.courierexchange.util.MailSender;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

import static by.epam.courierexchange.controller.command.CommandResult.ResponseType.FORWARD;
import static by.epam.courierexchange.controller.command.PagePath.*;
import static by.epam.courierexchange.controller.command.RequestAttribute.EXCEPTION;

public class ForgotPassMailMessageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        String mail = request.getParameter(RequestParameter.MAIL);
        Optional<User> optionalUser;
        UserServiceImpl userService = UserServiceImpl.getInstance();
        CommandResult commandResult;
        try{
            optionalUser = userService.selectByMail(mail);
            if(optionalUser.isPresent()){
                User user = optionalUser.get();
                MailSender.send(user.getMail(), MailSender.forgotPasswordMessage(user.getId()));
                commandResult = new CommandResult(MAIL_MESSAGE_PAGE, FORWARD);
            }else{
                request.setAttribute(RequestAttribute.WRONG_VALIDATION, true);
                commandResult = new CommandResult(FORGOT_PASS_PAGE, FORWARD);
            }
        } catch(ServiceException e){
            request.setAttribute(EXCEPTION, e);
            commandResult = new CommandResult(ERROR_PAGE, FORWARD);
        }
        return commandResult;
    }
}
