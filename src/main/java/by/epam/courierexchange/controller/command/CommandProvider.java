package by.epam.courierexchange.controller.command;

import by.epam.courierexchange.controller.command.impl.*;
import by.epam.courierexchange.controller.command.impl.admin.*;
import by.epam.courierexchange.controller.command.impl.client.*;
import by.epam.courierexchange.controller.command.impl.courier.CompleteOrderCommand;
import by.epam.courierexchange.controller.command.impl.courier.TakeCourierOrderCommand;
import by.epam.courierexchange.controller.command.impl.courier.UpdateCourierTransportCommand;
import by.epam.courierexchange.controller.command.impl.go.*;
import by.epam.courierexchange.controller.command.impl.user.*;

import java.util.EnumMap;

import static by.epam.courierexchange.controller.command.CommandType.*;

public class CommandProvider {
    private static CommandProvider INSTANCE;
    private final EnumMap<CommandType, Command> commands = new EnumMap<>(CommandType.class);

    private CommandProvider(){
        commands.put(DEFAULT, new DefaultCommand());
        commands.put(LOGIN, new LoginCommand());
        commands.put(LOGOUT, new LogoutCommand());
        commands.put(SIGN_UP, new SignUpCommand());
        commands.put(CHANGE_LOCALE, new ChangeLocaleCommand());
        commands.put(START_PAGE, new StartPageCommand());
        commands.put(UPDATE_PROFILE, new UpdateProfileCommand());
        commands.put(CREATE_NEW_ORDER, new CreateNewOrderCommand());
        commands.put(UPDATE_PASSWORD, new UpdatePasswordCommand());
        commands.put(CHANGE_PASSWORD, new ChangePasswordCommand());
        commands.put(CONFIRM_PROFILE, new ConfirmProfileCommand());
        commands.put(CHANGE_USER_ROLE, new ChangeRoleCommand());
        commands.put(UPDATE_CLIENT_ADDRESS, new UpdateClientAddressCommand());
        commands.put(UPDATE_COURIER_TRANSPORT, new UpdateCourierTransportCommand());
        commands.put(TAKE_COURIER_ORDER, new TakeCourierOrderCommand());
        commands.put(COMPLETE_ORDER, new CompleteOrderCommand());
        commands.put(DELETE_CLIENT_PRODUCT, new DeleteClientProductCommand());
        commands.put(BAN_USER, new BanUserCommand());
        commands.put(DELETE_PRODUCT, new DeleteProductCommand());
        commands.put(DELETE_TRANSPORT, new DeleteTransportCommand());
        commands.put(CONFIRM_MAIL_MESSAGE, new ConfirmMailMessageCommand());
        commands.put(FORGOT_PASS_MAIL_MESSAGE, new ForgotPassMailMessageCommand());
        commands.put(DELETE_ADMIN_CLIENT_PRODUCT, new DeleteAdminClientProductCommand());

        commands.put(GO_TO_MAIL_MESSAGE, new GoToMailMessage());
        commands.put(GO_TO_ADMIN_PRODUCTS, new GoToAdminProducts());
        commands.put(GO_TO_ADMIN_CLIENT_TEMPLATES, new GoToAdminClientTemplates());
        commands.put(GO_TO_ADMIN_TRANSPORTS, new GoToAdminTransports());
        commands.put(GO_TO_ADMIN_PAGE, new GoToAdminPage());
        commands.put(GO_TO_ADMIN_ALL_USERS, new GoToAdminAllUsers());
        commands.put(GO_TO_MY_TEMPLATES, new GoToClientTemplates());
        commands.put(GO_TO_COURIER_HISTORY, new GoToCourierHistory());
        commands.put(GO_TO_COURIER_ORDER, new GoToCourierOrder());
        commands.put(GO_TO_COURIER_ALL_ORDERS, new GoToCourierAllOrders());
        commands.put(GO_TO_COURIER_TRANSPORT, new GoToCourierTransport());
        commands.put(GO_TO_CLIENT_ADDRESS, new GoToClientAddress());
        commands.put(GO_TO_NEW_PASS, new GoToNewPassword());
        commands.put(GO_TO_SIGN_UP, new GoToSignUp());
        commands.put(GO_TO_LOGIN, new GoToLogin());
        commands.put(GO_TO_FORGOT_PASS, new GoToForgotPassword());
        commands.put(GO_TO_USER_PROFILE, new GoToUserProfile());
        commands.put(GO_TO_USER_ALL_ORDERS, new GoToUserAllOrders());
        commands.put(GO_TO_NEW_ORDER_PAGE, new GoToClientNewOrder());
        commands.put(GO_TO_MY_ORDERS_PAGE, new GoToClientOrders());
        commands.put(GO_TO_HISTORY_PAGE, new GoToClientHistory());
    }

    public static CommandProvider getInstance(){
        if(INSTANCE==null){
            INSTANCE = new CommandProvider();
        }
        return INSTANCE;
    }

    public Command getCommand(String commandName){
        if(commandName == null){
            return commands.get(DEFAULT);
        }
        CommandType commandType;
        try{
            commandType = valueOf(commandName.toUpperCase());
        }catch (IllegalArgumentException e){
            commandType = DEFAULT;
        }
        return commands.get(commandType);
    }
}
