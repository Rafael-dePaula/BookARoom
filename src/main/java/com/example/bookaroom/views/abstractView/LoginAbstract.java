package com.example.bookaroom.views.abstractView;

import com.example.bookaroom.views.BookARoomConnect;
import com.example.bookaroom.views.abstractView.inputs.InputComponente;

public abstract class LoginAbstract implements FlowComponente {
    protected final InputComponente<String> campusInput;
    protected final InputComponente<String> funcionarioInput;

    protected LoginAbstract(InputComponente<String> campusInput, InputComponente<String> funcionarioInput) {
        this.campusInput = campusInput;
        this.funcionarioInput = funcionarioInput;
    }

    protected String auth() {
        String campusText = campusInput.get();
        String funcionarioText = funcionarioInput.get();

        try {
            bookARoomApi().createConnection(campusText, funcionarioText);
            return null;
        } catch (IllegalStateException e) {
            return e.getMessage();
        }
    }

    protected BookARoomConnect bookARoomApi() {
        return new BookARoomConnect();
    }
}
