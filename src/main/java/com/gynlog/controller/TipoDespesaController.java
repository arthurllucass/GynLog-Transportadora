package com.gynlog.controller;

import com.gynlog.model.entity.TipoDespesa;
import com.gynlog.repository.impl.TipoDespesaRepositoryImpl;

import java.util.List;

public class TipoDespesaController {

    private TipoDespesaRepositoryImpl objDespesasDAO = null;

    public TipoDespesaController() {
        objDespesasDAO = new TipoDespesaRepositoryImpl();
    }

    public void removerTiposDespesa(TipoDespesa obj) throws Exception{

        if(objDespesasDAO.buscarPorId(obj.getIdTipoDespesa()) == null) throw new Exception("Tipo de Despesa não existe para remoção!!");

        objDespesasDAO.remover(obj.getIdTipoDespesa());
    }

    public void editarTipoDespesa(TipoDespesa obj) throws Exception{

        if(objDespesasDAO.buscarPorId(obj.getIdTipoDespesa()) == null) throw new Exception("Tipo de Despesa não existe para edição!!");
        if(objDespesasDAO.buscarPorDescricao(obj.getDescricao()) != null) throw new Exception("Essa Descricao Ja existe!!.");

        objDespesasDAO.atualizar(obj);
    }

    public void adicionarTipoDespesa(TipoDespesa obj) throws Exception{

        if(objDespesasDAO.buscarPorId(obj.getIdTipoDespesa()) != null) throw new Exception("Esse ID ja existe.");
        if(objDespesasDAO.buscarPorDescricao(obj.getDescricao())!= null) throw new Exception("Essa Descricao ja existe.");

        objDespesasDAO.salvar(obj);

    }

    public List<TipoDespesa> listar() throws Exception{
        return objDespesasDAO.listaDeTiposDespesas();
    }

    public TipoDespesa buscarPorId(int id) throws Exception {
        return objDespesasDAO.buscarPorId(id);
    }
}
