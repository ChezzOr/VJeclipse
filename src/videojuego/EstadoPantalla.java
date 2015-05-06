/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videojuego;

/**
 *
 * @author Christopher
 */
public class EstadoPantalla {
    public enum Pantallas{MenuPrincipal,Splash,CargaPartida, IniciarPartida, VerDatos,Opciones,Partida1,
                            Partida2, Partida3, MenuPausa, Batalla,Perder}
    Pantallas estado= Pantallas.MenuPrincipal;
    Pantallas anterior = Pantallas.MenuPrincipal;
    Pantallas partida = Pantallas.Partida1;
    
    public Pantallas actual(){
        return estado;
    }
    
    void cambiar(Pantallas cambiar){
        anterior = actual();
        switch(actual()){
            case Partida1:
                setPartida(Pantallas.Partida1);
                break;
                
            case Partida2:
                setPartida(Pantallas.Partida2);
                break;
                
            case Partida3:
                setPartida(Pantallas.Partida3);
                break;
        }
        estado=cambiar;
    }
    
    public Pantallas anterior(){
        return anterior;
    }

    public Pantallas getEstado() {
        return estado;
    }

    public Pantallas getAnterior() {
        return anterior;
    }

    public Pantallas getPartida() {
        return partida;
    }

    public void setEstado(Pantallas estado) {
        this.estado = estado;
    }

    public void setAnterior(Pantallas anterior) {
        this.anterior = anterior;
    }

    public void setPartida(Pantallas partida) {
        this.partida = partida;
    }
    
    
    
    
}
