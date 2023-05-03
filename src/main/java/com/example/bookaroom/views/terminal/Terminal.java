package com.example.bookaroom.views.terminal;

import com.example.bookaroom.views.BookARoomConnect;
import com.example.bookaroom.views.abstractView.FlowComponente;

public class Terminal {
    static BookARoomConnect bookARoomConnect = new BookARoomConnect();

    public static void run() {
        initApplicationFlow(new LoginConsole());
    }

    private static void initApplicationFlow(FlowComponente componente) {
        FlowComponente next = componente.run();
        while(next != null) {
            next = next.run();
        }
    }

    public static BookARoomConnect bookARoomApi() {
        return bookARoomConnect;
    }
}
