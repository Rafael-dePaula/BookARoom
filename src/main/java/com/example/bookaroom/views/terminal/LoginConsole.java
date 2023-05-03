package com.example.bookaroom.views.terminal;

import com.example.bookaroom.views.BookARoomConnect;
import com.example.bookaroom.views.abstractView.FlowComponente;
import com.example.bookaroom.views.abstractView.LoginAbstract;
import com.example.bookaroom.views.terminal.inputs.TextInputConsole;
import com.example.bookaroom.views.terminal.menu.MenuPrincipal;

public class LoginConsole extends LoginAbstract {
    static final String REGEX = "\\b.+?\\b";

    public LoginConsole() {
        super(new TextInputConsole("\tCampus: ", REGEX),
                new TextInputConsole("\tFuncionario: ", REGEX));
    }

    @Override
    public void render() {
        System.out.println("Login");
        String error = auth();
        while(error != null) {
            System.out.println("Falha na autenticação: " + error);
            error = auth();
        }
    }

    @Override
    protected BookARoomConnect bookARoomApi() {
        return Terminal.bookARoomApi();
    }

    @Override
    public FlowComponente next() {
        return new MenuPrincipal();
    }

}
