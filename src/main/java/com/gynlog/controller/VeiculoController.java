package com.gynlog.controller;

import java.lang.reflect.Array;
import java.util.ArrayList;

import com.gynlog.repository.impl.VeiculoRepositoryImpl;

import com.gynlog.model.entity.Veiculo;
import com.gynlog.model.enums.MarcaDeCarro;
import com.gynlog.model.enums.StatusVeiculo;
import com.gynlog.service.VeiculoService;

public class VeiculoController {

    private VeiculoService service = new VeiculoService();

    public ArrayList<Veiculo> listar() throws Exception {
        return service.listar();
    }

    public void adicionarVeiculo(Veiculo veiculo) throws Exception {
        service.adicionarVeiculos(veiculo);
    }

    public void removerVeiculo(int id) throws Exception {
        service.excluirVeiculo(id);
    }

    public void atualizarVeiculo(Veiculo veiculo) throws Exception {
        service.atualizarVeiculo(veiculo);
    }

    public Veiculo buscarPorId(int id) throws Exception {
        return service.buscarPorIdVeiculo(id);
    }

    public ArrayList<Veiculo> buscarPorStatus(StatusVeiculo status) throws Exception {
        return service.buscarPorStatus(status);
    }

    public Veiculo buscarPorPlaca(String placa) throws Exception {
        return service.buscarPorPlaca(placa);
    }

    public ArrayList<Veiculo> buscarPorMarca(MarcaDeCarro marca) throws Exception {
        return service.buscarPorMarca(marca);
    }

}
