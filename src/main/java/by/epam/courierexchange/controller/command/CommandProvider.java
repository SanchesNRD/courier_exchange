package by.epam.courierexchange.controller.command;

import by.epam.courierexchange.controller.command.impl.*;
import by.epam.courierexchange.controller.command.impl.go.*;

import java.util.EnumMap;

import static by.epam.courierexchange.controller.command.CommandType.*;

public class CommandProvider {
    private static CommandProvider INSTANCE;
    private final EnumMap<CommandType, Command> commands = new EnumMap<>(CommandType.class);

    private CommandProvider(){
        commands.put(DEFAULT, new DefaultCommand());
        commands.put(LOGIN, new LoginCommand());
        commands.put(LOGOUT, new LogoutCommand());
        commands.put(START_PAGE, new StartPageCommand());
        commands.put(GO_TO_SIGN_UP, new GoToSignUp());
        commands.put(GO_TO_LOGIN, new GoToLogin());
        commands.put(GO_TO_FORGOT_PASS, new GoToForgotPassword());
        commands.put(GO_TO_USER_PROFILE, new GoToUserProfile());
        commands.put(CHANGE_LOCALE, new ChangeLocaleCommand());
        commands.put(GO_TO_USER_ALL_ORDERS, new GoToUserAllOrders());
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
