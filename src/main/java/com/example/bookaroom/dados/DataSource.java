package com.example.bookaroom.dados;

import com.example.bookaroom.bookaroom.campus.Campus;
import com.example.bookaroom.bookaroom.campus.Predio;
import com.example.bookaroom.bookaroom.campus.Sala;
import com.example.bookaroom.bookaroom.equipamentos.AudioVideo;
import com.example.bookaroom.bookaroom.equipamentos.Equipamento;
import com.example.bookaroom.bookaroom.equipamentos.Notbook;
import com.example.bookaroom.bookaroom.periodo.DiaDaSemana;
import com.example.bookaroom.bookaroom.periodo.Horario;
import com.example.bookaroom.bookaroom.periodo.Periodo;
import com.example.bookaroom.bookaroom.campus.*;
import com.example.bookaroom.bookaroom.periodo.Semestre;
import com.example.bookaroom.bookaroom.reserva.Aula;
import com.example.bookaroom.bookaroom.reserva.Reserva;
import com.example.bookaroom.bookaroom.reserva.Reuniao;

import java.io.*;
import java.nio.channels.FileLock;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public final class DataSource {
    static final class Dados implements Serializable {
        public List<Campus> campi;

        public Dados(List<Campus> campi) {
            this.campi = campi;
        }
    }

    private static final String dadosPath = "dados.dat";
    private static final String lockPath = "dados.lock";
    private static FileOutputStream lockFile = null;
    private static FileLock lock = null;

    private static void lockWriteOperations()  {
        try {
            lockFile = new FileOutputStream(lockPath);
            while(lock == null) {
                lock = lockFile.getChannel().tryLock();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void releaseWriteOperations(){
        try {
            if(lock != null && lock.isValid()) {
                lock.release();
            }
            if(lockFile != null && lockFile.getChannel().isOpen()) {
                lockFile.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized static <R> R fetch(Function<List<Campus>, R> fetchFunc) {
        lockWriteOperations();
        try {
            Dados dados = load();
            return fetchFunc.apply(dados.campi);
        }  catch (IllegalStateException e) {
            throw e;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public synchronized static void transaction(Consumer<List<Campus>> func)  {
        lockWriteOperations();
        try {
            Dados dados = load();
            func.accept(dados.campi);
            save(dados);
        }  catch (IllegalStateException e) {
            throw e;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        releaseWriteOperations();
    }

    static synchronized Dados load() throws Exception {
        try {
            FileInputStream fileIn = new FileInputStream(dadosPath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Dados dados = (Dados) in.readObject();
            in.close();
            fileIn.close();
            return dados;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    static synchronized void save(Dados dados) {
        try {
            FileOutputStream fileOut = new FileOutputStream(dadosPath, false);
            FileLock lock = fileOut.getChannel().tryLock();

            while (lock == null) {
                lock = fileOut.getChannel().tryLock();
            }

            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            out.writeObject(dados);

            lock.release();
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void resetDados() {



        String nomeCampus = "MOCHELL";

        String AMANHA = LocalDate.now().plusDays(1).format(Periodo.FORMATO_DATA);
        Dados dados = new Dados(new ArrayList<>());
        // Funcionarios
        Funcionario neila = new Funcionario("Neila", "Professor", "20");
        Funcionario lucio = new Funcionario("Lucio", "Professor", "21");
        Funcionario alberto =  new Funcionario("Alberto", "Professor", "22");
        Funcionario danilo =  new Funcionario("Danilo", "Coordenador", "23");
        List<Funcionario> funcionarios = List.of(neila, lucio, alberto, danilo);

        // Equipamentos
        List<Equipamento> equipamentos = new ArrayList<>();

        int number = 0;
        equipamentos.add(new AudioVideo("Datashow", List.of("hdmi")));
        equipamentos.add(new Notbook("Datashow", "intel i1.5"));
        equipamentos.get(0).setPatrimonio(nomeCampus + "-equip-" + ++number);
        equipamentos.get(1).setPatrimonio(nomeCampus + "-equip-" + ++number);

        // Predios / Salas
        List<Sala> salasTI = List.of(
                new Sala("1", 40),
                new Sala("2", 40),
                new Sala("3", 10)
        );

        List<Sala> salasMedio = List.of(
                new Sala("1", 40),
                new Sala("2", 40),
                new Sala("3", 10)
        );

        List<Sala> salasSecretaria = List.of(
                new Sala("1", 40),
                new Sala("2", 40),
                new Sala("3", 10)
        );

        List<Sala> salasPredio3 = List.of(
                new Sala("1", 40),
                new Sala("2", 40),
                new Sala("3", 10)
        );


        List<Predio> predios = List.of(
                new Predio("TI", salasTI),
                new Predio("Medio", salasMedio),
                new Predio("Secretaria", salasSecretaria),
                new Predio("Predio3", salasPredio3)
        );

        ///Semestres
        List<Semestre> semestres = new ArrayList<>();
        Semestre semestre = new Semestre(LocalDate.of(2023, 2, 10), LocalDate.of(2023, 7, 10), new ArrayList<>());
        semestre.setId(nomeCampus + "_" + semestre.inicio.format(Semestre.FORMATO_MES_ANO));
        semestres.add(semestre);
        Semestre semestre2 = new Semestre(LocalDate.of(2023, 7, 10), LocalDate.of(2023, 12, 10), new ArrayList<>());
        semestre2.setId(nomeCampus + "_" + semestre.inicio.format(Semestre.FORMATO_MES_ANO));
        semestres.add(semestre2);

        // Reservas
        // Reservas Neila

        // -- Calc 1

        List<Reserva> reservas = new ArrayList<>();

        reservas.add(new Reuniao(
                new Periodo(AMANHA, "08:20", "09:00"),
                neila,
                salasTI.get(0),
                "Calc 1"
        ));

        reservas.add(new Reuniao(
                new Periodo(AMANHA, "15:00", "17:40"),
                neila,
                salasTI.get(0),
                "Calc 1"
        ));

        // -- Matematica
        reservas.add(new Reuniao(
                new Periodo(AMANHA, "09:20", "11:00"),
                neila,
                salasMedio.get(1),
                "Matematica"
        ));

        reservas.add(new Reuniao(
                new Periodo(AMANHA, "11:00", "12:40"),
                neila,
                salasMedio.get(1),
                "Matematica"
        ));

        // Reservas Lucio
        reservas.add(new Reuniao(
                new Periodo(AMANHA, "11:00", "12:40"),
                lucio,
                salasMedio.get(0),
                "Banco de Dados"
        ));

        reservas.add(new Reuniao(
                new Periodo(AMANHA, "01:00", "02:40"),
                lucio,
                salasMedio.get(0),
                "Reuniao matinal"
        ));

        reservas.add(new Reuniao(
                new Periodo(AMANHA, "15:20", "17:00"),
                lucio,
                salasTI.get(1),
                "Banco de Dados"
        ));

        Aula aula = new Aula(semestre, DiaDaSemana.TERCA, new Horario("12:30", "13:00"), lucio,
                salasMedio.get(0),
                "Aula da terça");



        dados.campi.add(new Campus(
                nomeCampus,
                "Rua 2",
                predios,
                funcionarios,
                equipamentos,
                reservas,
                semestres
                ));

        dados.campi.get(0).addReserva(aula);

        save(dados);
    }

}
