package com.gynlog.repository.ordenacao;

import com.gynlog.model.entity.Veiculo;

import java.util.ArrayList;

public class Ordenardor {
    public void mergeSortMarca(ArrayList<Veiculo> veiculo, int inicio, int fim, String modelo) {
        if (inicio < fim) {
            int meio = (inicio + fim) / 2;

            mergeSortMarca(veiculo, inicio, meio, modelo);
            mergeSortMarca(veiculo, meio + 1, fim, modelo);
            merge(veiculo, inicio, meio, fim, modelo);
        }
    }

    public void merge(ArrayList<Veiculo> veiculo, int inicio, int meio, int fim, String campo) {
        ArrayList<Veiculo> temp = new ArrayList<>();
      

        for (int x = 0; x < veiculo.size(); x++) {
            temp.add(null);
        }

        int i = inicio;
        int j = meio + 1;
        int k = inicio;

        while (i <= meio && j <= fim) {
            if (comparador(veiculo.get(i), veiculo.get(j), campo) < 0) {
                temp.set(k, veiculo.get(i));
                i++;

            } else {
                temp.set(k, veiculo.get(j));
                j++;
            }
            k++;

        }
        while (i <= meio) {
            temp.set(k, veiculo.get(i));
            i++;
            k++;
        }

        while (j <= fim) {
            temp.set(k, veiculo.get(j));
            j++;
            k++;
        }
        for (int x = inicio; x <= fim; x++) {
            veiculo.set(x, temp.get(x));
        }
    }

    private int comparador(Veiculo v1, Veiculo v2, String campo) {
        switch (campo.toUpperCase()) {
            case "MARCA":
                return v1.getMarca().name().compareTo(v2.getMarca().name());

            case "MODELO":
                return v1.getModelo().compareToIgnoreCase(v2.getModelo());


            case "ANO":
                return Integer.compare(v1.getAnoDeFrabicacao(), v2.getAnoDeFrabicacao());

            default:
                return 0;
        }
    }

}
