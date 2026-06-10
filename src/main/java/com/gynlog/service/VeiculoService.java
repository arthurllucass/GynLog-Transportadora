package com.gynlog.service;

import java.util.ArrayList;

import com.gynlog.model.entity.Veiculo;
import com.gynlog.model.enums.MarcaDeCarro;
import com.gynlog.model.enums.StatusVeiculo;
import com.gynlog.repository.impl.VeiculoRepositoryImpl;

public class VeiculoService {
    private static VeiculoRepositoryImpl dao = new VeiculoRepositoryImpl();

    public ArrayList<Veiculo> listar() throws Exception {
        return dao.listaDeTipoDeVeiculo();

    }

    private void validar(Veiculo objVeiculo) throws Exception {

        if (objVeiculo == null) {
            throw new Exception("Veiculo inválido");

        }
        if (objVeiculo.getPlaca() == null || objVeiculo.getPlaca().trim().isEmpty()) {
            throw new Exception("A placa não pode estar vazio");
        }
        if (!dao.validarPlaca(objVeiculo.getPlaca())) {
            throw new Exception("Modelo de placa inválido");
        }
        if (objVeiculo.getMarca() == null) {
            throw new Exception("O campo marca não pode estar vazio");
        }
        if (objVeiculo.getModelo() == null || objVeiculo.getModelo().trim().isEmpty()) {
            throw new Exception("O campo modelo não pode estar vazio!");
        }
        int ano = objVeiculo.getAnoDeFrabicacao();
        if (ano < 1900 || ano > 2100) {
            throw new Exception("Ano de fabricação invalido");
        }

    }

    private int geradorId() throws Exception {
        int maiorId = 0;
        for (Veiculo veiculo : dao.listaDeTipoDeVeiculo()) {
            if (veiculo.getIdVeiculo() > maiorId) {
                maiorId = veiculo.getIdVeiculo();
            }
        }
        return maiorId + 1;
    }

    public void adicionarVeiculos(Veiculo objVeiculo) throws Exception {
        validar(objVeiculo);

        for (Veiculo tipoVeiculo : dao.listaDeTipoDeVeiculo()) {
            if (tipoVeiculo.getPlaca().equalsIgnoreCase(objVeiculo.getPlaca())) {
                throw new Exception("Placa já cadastrada, insira uma placa válida");
            }
        }
        objVeiculo.setIdVeiculo(geradorId());
        dao.salvar(objVeiculo);

    }

    public void atualizarVeiculo(Veiculo veiculo) throws Exception {
        // validar(veiculo);
        dao.atualizar(veiculo);
    }

    public void excluirVeiculo(int idVeiculo) throws Exception {
        dao.remover(idVeiculo);
    }

    public Veiculo buscarPorIdVeiculo(int id) throws Exception {
        return dao.buscarPorID(id);
    }

    public ArrayList<Veiculo> buscarPorStatus(StatusVeiculo status) throws Exception {
        return dao.buscarPorStatus(status);
    }

    public Veiculo buscarPorPlaca(String placa) throws Exception {
        for (Veiculo veiculo : dao.listaDeTipoDeVeiculo()) {
            if (veiculo.getPlaca().equalsIgnoreCase(placa)) {
                return veiculo;
            }
        }
        return null;
    }

    public ArrayList<Veiculo> buscarPorMarca(MarcaDeCarro marca) throws Exception {
        return dao.buscarPorMarca(marca);
    }
}
