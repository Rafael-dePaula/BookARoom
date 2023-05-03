package com.example.bookaroom.views.terminal.equipamentos;

import com.example.bookaroom.bookaroom.equipamentos.AudioVideo;
import com.example.bookaroom.views.BookARoomConnect;
import com.example.bookaroom.views.abstractView.FlowComponente;
import com.example.bookaroom.views.terminal.Terminal;
import com.example.bookaroom.views.terminal.inputs.TextInputConsole;

import java.sql.SQLOutput;
import java.util.List;

public class CadastrarEquipamento implements FlowComponente {

    public void cadastrar() {
        String nome = new TextInputConsole("Digite nome: ").get();
        bookARoomApi().request().cadastrarEquipamento(new AudioVideo(nome, List.of("HDMI")));
    }

    @Override
    public void render() {
        System.out.println("Cadastrar Equipamento");
    }

    @Override
    public FlowComponente next() {
        return null;
    }

    @Override
    public FlowComponente run() {
        render();
        cadastrar();
        return next();
    }

    protected BookARoomConnect bookARoomApi() {
        return new BookARoomConnect();
    }
}
