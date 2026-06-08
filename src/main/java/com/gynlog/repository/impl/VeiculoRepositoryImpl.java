package com.gynlog.repository.impl;
import com.gynlog.model.enums.MarcaDeCarro;
import com.gynlog.model.enums.ModeloDeCarro;
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

    public VeiculoRepositoryImpl(){
        try{
            String home = System.getProperty("user.home");
            File pasta = new File(home, "Veiculos");

            if(!pasta.exists()){
                pasta.mkdir();
            }

            arquivo = new File(pasta, "TipoDeVeiculos.txt");
            if(!arquivo.exists()){
                arquivo.createNewFile();
            }
            

        }catch(Exception erro){
            String msg = "Persistencia - Construtor- " + erro.getMessage();
            System.out.println(msg);
        }
    }

    private int geradorID(){
        try{
            ArrayList<Veiculo> lista = this.listaDeTipoDeVeiculo();
            int idAux = 0;
            for(Veiculo objVeiculo : lista){
                if(objVeiculo.getIdVeiculo() > idAux){
                    idAux = objVeiculo.getIdVeiculo();


                }
            }
            return idAux +=1;
        }catch(Exception erro){
            String msg = "Metodo - " + erro.getMessage();
            System.out.println(msg);

            return 1;

        }
        
        
       

    }

    @Override
    public void salvar(Veiculo tipoDeVeiculo) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'salvar'");
    }

    @Override
    public ArrayList<Veiculo> listaDeTipoDeVeiculo() throws Exception {
       try{
        ArrayList<Veiculo> lista = new ArrayList<>();
        FileReader fr = new FileReader(arquivo);
        BufferedReader br = new BufferedReader(fr);

        String linha = "";

        while((linha = br.readLine()) !=null){
            String vetorSTR[] = linha.split(";");
            Veiculo objVeiculo = null;
            int idTipoVeiculo = Integer.parseInt(vetorSTR[0]);
            String placa = vetorSTR[1];
            MarcaDeCarro marca = MarcaDeCarro.valueOf(vetorSTR[2]);
            String modelo = vetorSTR[3];
            int anoDeFrabricacao = Integer.parseInt(vetorSTR[4]);
            StatusVeiculo statusVeiculo = StatusVeiculo.valueOf(vetorSTR[5]);

            objVeiculo = 


            


            
        
        }


       }catch(Exception erro){

       }
    }

    @Override
    public Veiculo buscarPorID(int idTipoDeDespesa) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorID'");
    }

    @Override
    public void atualizar(Veiculo tipVeiculo) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    @Override
    public void remover(int idVeiculo) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remover'");
    }
}
