package com.gynlog.repository.impl;

import java.util.ArrayList;
import com.gynlog.model.entity.Veiculo;

public class Ordenação {
    public void mergeSort(ArrayList<Veiculo> lista, int inicio, int fim){
        if(inicio<fim){
            int meio = (inicio+fim)/2;
            mergeSort(lista, inicio, meio);
            mergeSort(lista, meio+1, fim);

        }
    }

    public void merge(ArrayList<Veiculo> lista, int inicio, int meio, int fim){
        ArrayList<Veiculo> listaOrdenada = new ArrayList<>(lista);

        int i = inicio;
        int j = meio +1;
        int k = inicio;

        while(i<=meio && j<=fim){
            if(lista.get(i).getModelo().compareTo(lista.get(j).getModelo())){
                
            }
        }
    }
    
}
