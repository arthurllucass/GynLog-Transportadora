package com.gynlog.repository;
import java.time.temporal.ValueRange;

import com.gynlog.model.entity.Veiculo;
import java.util.ArrayList;

public interface VeiculoRepository {
    void salvar(Veiculo tipoDeVeiculo) throws Exception;
    ArrayList<Veiculo> listaDeTipoDeVeiculo() throws Exception;
    Veiculo buscarPorID(int idVeiculo) throws Exception;
    void atualizar(Veiculo tipVeiculo) throws Exception;
    void remover(int idVeiculo) throws Exception;
}
