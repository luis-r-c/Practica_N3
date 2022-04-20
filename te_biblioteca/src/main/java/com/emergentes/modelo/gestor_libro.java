
package com.emergentes.modelo;

import java.util.ArrayList;
import java.util.Iterator;

public class gestor_libro {
    private ArrayList<libro> lista;

    public gestor_libro() {
        lista = new ArrayList<libro>();
    }

    public ArrayList<libro> getLista() {
        return lista;
    }

    public void setLista(ArrayList<libro> lista) {
        this.lista = lista;
    }
    
    public void insertarlibro (libro item){
        lista.add(item);
    }
    public void modificarlibro (int pos, libro item){
        lista.set(pos,item);
    }
    public void eliminarlibro (int pos){
        lista.remove(pos);
    }
    public int obtieneId() {
        int idaux =0;
        for (libro item : lista) {
            idaux = item.getId();
        }
        return idaux+1;
    }
    public int ubicarlibro(int id) {
        int pos =-1;
        Iterator<libro> it = lista.iterator();
        while(it.hasNext()){
            ++pos;
            libro aux = it.next();
            if(aux.getId()==id){
                break;
            }
        }
        return pos;
    }
}
