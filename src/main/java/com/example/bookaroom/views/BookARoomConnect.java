package com.example.bookaroom.views;

import com.example.bookaroom.bookaroom.BookARoom;

public class BookARoomConnect {
    static BookARoom bookARoom;

    public void createConnection(String nomeCampuss, String nomeFuncionario) {
        bookARoom = new BookARoom(nomeCampuss, nomeFuncionario);
    }

    public BookARoom request() { return bookARoom; }
}
