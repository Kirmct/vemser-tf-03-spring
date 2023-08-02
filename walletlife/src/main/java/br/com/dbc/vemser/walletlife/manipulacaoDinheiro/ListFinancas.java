package br.com.dbc.vemser.walletlife.manipulacaoDinheiro;

import java.util.HashMap;

public class ListFinancas<V> {

    private HashMap<Integer, V> lista;

    private Integer size;

    public ListFinancas() {
        this.lista = new HashMap<>();
        size = 0;
    }

    public HashMap<Integer, V> getLista() {
        return lista;
    }

    public V get(int id) {
        return lista.get(id);
    }

    public void add(V financa) {
        lista.put(size, financa);
        size++;
    }

    public void update(int id, V financa) {
        lista.replace(id, financa);
    }

    public boolean delete(int id, V financa) {
        return lista.remove(id, financa);
    }

    public int size() {
        return size;
    }

}
