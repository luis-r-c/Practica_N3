
package com.emergentes.modelo;

import java.util.ArrayList;
import java.util.Iterator;

public class gestor_categoria {
    private ArrayList<categoria> lista;

    public gestor_categoria() {
        lista = new ArrayList<categoria>();
    }

    public ArrayList<categoria> getLista() {
        return lista;
    }

    public void setLista(ArrayList<categoria> lista) {
        this.lista = lista;
    }
    
    public void insertarcategoria (categoria item){
        lista.add(item);
    }
    public void modificarcategoria (int pos, categoria item){
        lista.set(pos,item);
    }
    public void eliminarcategoria (int pos){
        lista.remove(pos);
    }
    public int obtieneIdcategoria() {
        int idaux =0;
        for (categoria item : lista) {
            idaux = item.getId();
        }
        return idaux+1;
    }
    public int ubicarcategoria(int id) {
        int pos =-1;
        Iterator<categoria> it = lista.iterator();
        while(it.hasNext()){
            ++pos;
            categoria aux = it.next();
            if(aux.getId()==id){
                break;
            }
        }
        return pos;
    }
}
