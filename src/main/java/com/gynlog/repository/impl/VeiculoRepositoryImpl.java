package com.gynlog.repository.impl;

import com.gynlog.model.enums.MarcaDeCarro;

import com.gynlog.model.enums.StatusVeiculo;

import java.util.ArrayList;

import com.gynlog.model.entity.Veiculo;
import com.gynlog.repository.VeiculoRepository;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;

public class VeiculoRepositoryImpl implements VeiculoRepository {
    File arquivo;

    public VeiculoRepositoryImpl() {
        try {

            File pasta = new File("database");

            if (!pasta.exists()) {
                pasta.mkdir();
            }

            arquivo = new File(pasta, "TipoDeVeiculos.txt");
            if (!arquivo.exists()) {
                arquivo.createNewFile();
            }

        } catch (Exception erro) {
            String msg = "Persistencia - Construtor- " + erro.getMessage();
            System.out.println(msg);
        }
    }

    private int geradorID() {
        try {
            ArrayList<Veiculo> lista = this.listaDeTipoDeVeiculo();
            int idAux = 0;
            for (Veiculo objVeiculo : lista) {
                if (objVeiculo.getIdVeiculo() > idAux) {
                    idAux = objVeiculo.getIdVeiculo();

                }
            }
            return idAux += 1;
        } catch (Exception erro) {
            String msg = "Metodo - " + erro.getMessage();
            System.out.println(msg);

            return 1;

        }

    }

     public boolean validarPlaca(String placa) {
        placa = placa.toUpperCase(); 

       
        if (placa.length() == 8 && placa.charAt(3) == '-') {

            // Letras nas 3 primeiras posições
            if (!Character.isLetter(placa.charAt(0))) {
                return false;
            }
            if (!Character.isLetter(placa.charAt(1))) {
                return false;
            }
            if (!Character.isLetter(placa.charAt(2))) {
                return false;
            }

            // Dígitos depois do hífen
            if (!Character.isDigit(placa.charAt(4))) {
                return false;
            }
            if (!Character.isDigit(placa.charAt(5))) {
                return false;
            }
            if (!Character.isDigit(placa.charAt(6))) {
                return false;
            }
            if (!Character.isDigit(placa.charAt(7))) {
                return false;
            }

            return true; // passou em tudo
        }

     
        if (placa.length() == 7) {

            // 3 letras no começo
            if (!Character.isLetter(placa.charAt(0))) {
                return false;
            }
            if (!Character.isLetter(placa.charAt(1))) {
                return false;
            }
            if (!Character.isLetter(placa.charAt(2))) {
                return false;
            }

            // 1 número
            if (!Character.isDigit(placa.charAt(3))) {
                return false;
            }

            // 1 letra
            if (!Character.isLetter(placa.charAt(4))) {
                return false;
            }

            // 2 números finais
            if (!Character.isDigit(placa.charAt(5))) {
                return false;
            }
            if (!Character.isDigit(placa.charAt(6))) {
                return false;
            }

            return true;
        }

        return false; 
    }

    public ArrayList<Veiculo> buscarPorStatus(StatusVeiculo statusProcurado) throws Exception {
        try {
            ArrayList<Veiculo> lista = new ArrayList<>();

            FileReader fr = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(fr);

            String linha = "";

            while ((linha = br.readLine()) != null) {
                String vetorSTR[] = linha.split(";");

                StatusVeiculo statusLido = StatusVeiculo.valueOf(vetorSTR[5]);

                if (statusLido == statusProcurado) {
                    int id = Integer.parseInt(vetorSTR[0]);
                    String placa = vetorSTR[1];
                    MarcaDeCarro marca = MarcaDeCarro.valueOf(vetorSTR[2]);
                    String modelo = vetorSTR[3];
                    int anoDeFabricacao = Integer.parseInt(vetorSTR[4]);

                    Veiculo obj = new Veiculo(id, placa, marca, modelo, anoDeFabricacao, statusLido);

                    lista.add(obj);
                }
            }

            br.close();
            return lista;

        } catch (Exception erro) {
            String msg = "Persistencia - Metodo buscarPorStatus - " + erro.getMessage();
            throw new Exception(msg);
        }
    }

    public ArrayList<Veiculo> buscarPorMarca(MarcaDeCarro marcaProcurada) throws Exception {
        try {
            ArrayList<Veiculo> lista = new ArrayList<>();
            FileReader fr = new FileReader(arquivo);
            BufferedReader bw = new BufferedReader(fr);

            String linha = "";
            while ((linha = bw.readLine()) != null) {
                String vetorSTR[] = linha.split(";");

                MarcaDeCarro marca = MarcaDeCarro.valueOf(vetorSTR[2]);

                if (marca == marcaProcurada) {
                    int id = Integer.parseInt(vetorSTR[0]);
                    String placa = vetorSTR[1];
                    String modelo = vetorSTR[3];
                    int anoDeFabricacao = Integer.parseInt(vetorSTR[4]);
                    StatusVeiculo status = StatusVeiculo.valueOf(vetorSTR[5]);

                    Veiculo objVeiculo = new Veiculo(id, placa, marca, modelo,anoDeFabricacao, status);
                    lista.add(objVeiculo);

                }
            }
            bw.close();
            return lista;

        } catch (Exception erro) {
            String msg = "Persistencia - Metodo buscarPorMarca - " + erro.getMessage();
            throw new Exception(msg);

        }

    }

    @Override
    public void salvar(Veiculo tipoDeVeiculo) throws Exception {
        try {
            ArrayList<Veiculo> lista = this.listaDeTipoDeVeiculo();
            for (Veiculo veiculo : lista) {
                if (veiculo.getIdVeiculo() == tipoDeVeiculo.getIdVeiculo()) {
                    throw new Exception("ID já existe!");
                }
            }

            FileWriter fw = new FileWriter(arquivo, true);
            BufferedWriter bw = new BufferedWriter(fw);
            tipoDeVeiculo.setIdVeiculo(geradorID());

            String str = tipoDeVeiculo.getIdVeiculo() + ";";
            str += tipoDeVeiculo.getPlaca() + ";" +
                    tipoDeVeiculo.getMarca() + ";" +
                    tipoDeVeiculo.getModelo() + ";" +
                    tipoDeVeiculo.getAnoDeFrabicacao() + ";" +
                    tipoDeVeiculo.getStatusVeiculo() + "\n";

            bw.write(str);
            bw.close();

        } catch (Exception erro) {
            String msg = "Metodo Salvar: " + erro.getMessage();
            throw new Exception(msg);
        }
    }

    @Override
    public ArrayList<Veiculo> listaDeTipoDeVeiculo() throws Exception {
        try {
            ArrayList<Veiculo> lista = new ArrayList<>();
            FileReader fr = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(fr);

            String linha = "";

            while ((linha = br.readLine()) != null) {
                String vetorSTR[] = linha.split(";");
                Veiculo objVeiculo = null;
                int idTipoVeiculo = Integer.parseInt(vetorSTR[0]);
                String placa = vetorSTR[1];
                MarcaDeCarro marca = MarcaDeCarro.valueOf(vetorSTR[2]);
                String modelo = vetorSTR[3];
                int anoDeFrabricacao = Integer.parseInt(vetorSTR[4]);
                StatusVeiculo statusVeiculo = StatusVeiculo.valueOf(vetorSTR[5]);

                objVeiculo = new Veiculo(idTipoVeiculo, placa, marca, modelo,anoDeFrabricacao ,statusVeiculo);
                lista.add(objVeiculo);

            }
            br.close();
            return lista;

        } catch (Exception erro) {
            String msg = "Metodo Lista: " + erro.getMessage();
            throw new Exception(msg);

        }
    }

    @Override
    public Veiculo buscarPorID(int idVeiculo) throws Exception {
        try {
            FileReader fr = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(fr);
            String linha = "";
            while ((linha = br.readLine()) != null) {
                String vetorSTR[] = linha.split(";");
                int idAux = Integer.parseInt(vetorSTR[0]);
                if (idAux == idVeiculo) {
                    String placa = vetorSTR[1];
                    MarcaDeCarro marca = MarcaDeCarro.valueOf(vetorSTR[2]);
                    String modelo = vetorSTR[3];
                    int anoDeFabricacao = Integer.parseInt(vetorSTR[4]);
                    StatusVeiculo status = StatusVeiculo.valueOf(vetorSTR[5]);
                    Veiculo obVeiculo = new Veiculo(idVeiculo, placa, marca, modelo,anoDeFabricacao, status);
                    return obVeiculo;

                }
            }
            br.close();
            return null;

        } catch (Exception erro) {
            String msg = "Persistencia - Metodo Buscar - " + erro.getMessage();
            throw new Exception(msg);
        }
    }

    @Override
    public void atualizar(Veiculo tipVeiculo) throws Exception {
        try {
            ArrayList<Veiculo> lista = this.listaDeTipoDeVeiculo();
            FileWriter fw = new FileWriter(arquivo);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Veiculo objVeiculo : lista) {
                Veiculo registro;

                if (objVeiculo.getIdVeiculo() == tipVeiculo.getIdVeiculo()) {
                    registro = tipVeiculo;
                } else {
                    registro = objVeiculo;
                }
                String str = registro.getIdVeiculo() + ";" +
                        registro.getPlaca() + ";" +
                        registro.getMarca() + ";" +
                        registro.getModelo() + ";" +
                        registro.getAnoDeFrabicacao() + ";" +
                        registro.getStatusVeiculo() + "\n";

                bw.write(str);
            }
            bw.close();

        } catch (Exception erro) {
            throw new Exception("Persistencia - Metodo Atualizar - " + erro.getMessage());

        }
    }

    @Override
    public void remover(int idVeiculo) throws Exception {
        try {
            ArrayList<Veiculo> lista = this.listaDeTipoDeVeiculo();
            FileWriter fw = new FileWriter(arquivo);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Veiculo obVeiculo : lista) {
                if (obVeiculo.getIdVeiculo() != idVeiculo) {
                    String str = obVeiculo.getIdVeiculo() + ";" +
                            obVeiculo.getPlaca() + ";" +
                            obVeiculo.getMarca() + ";" +
                            obVeiculo.getModelo() + ";" +
                            obVeiculo.getAnoDeFrabicacao() + ";" +
                            obVeiculo.getStatusVeiculo() + "\n";

                    bw.write(str);

                }
            }
            bw.close();

        } catch (Exception erro) {
            String msg = "Persistencia - Metodo Remover - " + erro.getMessage();
            throw new Exception(msg);
        }
    }
}
