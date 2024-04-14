package modulos.validadorContrasenias;

import java.util.ArrayList;
import java.util.List;

public class Controlador {
    private Integer logitudMaxima; //8 caracteres
    private Integer logitudMinima; //64 caracteres
    private List<String> palabrasProhibidas;
    private List<String> top1000PeoresContrasenias;

    public Integer getLogitudMaxima() {
        return logitudMaxima;
    }

    public Integer getLogitudMinima() {
        return logitudMinima;
    }

    public List<String> getPalabrasProhibidas() {
        return palabrasProhibidas;
    }

    public List<String> getTop1000PeoresContrasenias() {
        return top1000PeoresContrasenias;
    }

    public Controlador(Integer logitudMaxima, Integer logitudMinima) {
        this.logitudMaxima = logitudMaxima;
        this.logitudMinima = logitudMinima;
        this.palabrasProhibidas = new ArrayList<>();
        this.top1000PeoresContrasenias = new ArrayList<>();
    }

    public Boolean esValida(String clave){
        return this.cumplePoliticasDeContrasenias(clave) &&
                this.noPerteneceAPeoresContrasenias(clave) &&
                this.noUtilizaCredenciales(clave);
    }

    public Boolean cumplePoliticasDeContrasenias(String clave){
        return this.getLogitudMinima() <= clave.length() &&
                clave.length() <= this.getLogitudMaxima() &&
                !this.getPalabrasProhibidas().contains(clave);
    }

    public Boolean noPerteneceAPeoresContrasenias(String clave){
        return this.getTop1000PeoresContrasenias().contains(clave);
    }

    public Boolean noUtilizaCredenciales(String clave){
        //TODO
        // no se a que se refieren a no uitliza credenciales
        return true;
    }
}

