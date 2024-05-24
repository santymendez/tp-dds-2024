package services.security;

import services.security.rules.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestPassword {
    Autenticador autenticador;
    CantidadMaximaDeCaracteres cantidadMaximaDeCaracteres;
    CantidadMinimaDeCaracteres cantidadMinimaDeCaracteres;
    PerteneceAlArchivo perteneceAlArchivo;
    TieneMayusculasMinusculas tieneMayusculasMinusculas;
    TieneNumeros tieneNumeros;
    TieneCaracteresEspeciales tieneCaracteresEspeciales;

    @BeforeEach
    public void inicializar(){
        cantidadMaximaDeCaracteres = new CantidadMaximaDeCaracteres(64);
        cantidadMinimaDeCaracteres = new CantidadMinimaDeCaracteres(8);
        perteneceAlArchivo = new PerteneceAlArchivo("Top10milPeoresContrasenias", "src/main/resources/passwordValidator/top10000.txt");
        tieneMayusculasMinusculas = new TieneMayusculasMinusculas();
        tieneNumeros = new TieneNumeros();
        tieneCaracteresEspeciales = new TieneCaracteresEspeciales();

        autenticador = new Autenticador();
        autenticador.agregarPoliticas(
                cantidadMaximaDeCaracteres,
                cantidadMinimaDeCaracteres,
                perteneceAlArchivo,
            tieneMayusculasMinusculas,
                tieneNumeros,
                tieneCaracteresEspeciales);
    }

    @Test
    @DisplayName("La contraseña '123456789' pertenece a la lista de top peores contraseñas")
    public void contraseniaPerteneceAPeoresContrasenias(){
        Assertions.assertFalse(perteneceAlArchivo.cumpleLaRegla("123456789"));
    }

    @Test
    @DisplayName("La contraseña 'Supercalifragilisticexpialidociousabcdefghijklmnopqrstuvwxyzaa' no cumple la cantidad maxima de caracteres")
    public void contraseniaNoCumpleCantidadMaxima(){
        Assertions.assertFalse(cantidadMaximaDeCaracteres.cumpleLaRegla("Supercalifragilisticexpialidociousabcdefghijklmnopqrstuvwxyzaawdijqadinqdnqdq"));
    }

    @Test
    @DisplayName("La contraseña 'klaT' no cumple la cantidad minima de caracteres")
    public void contraseniaNoCumpleCantidadMinima(){
        Assertions.assertFalse(cantidadMinimaDeCaracteres.cumpleLaRegla("klaT"));
    }

    @Test
    @DisplayName("La contraseña 'klaTYHG' tiene mayusculas y minusculas")
    public void contraseniaTieneMayusculasYMinusculas(){
        Assertions.assertTrue(tieneMayusculasMinusculas.cumpleLaRegla("klaTYHG"));
    }

    @Test
    @DisplayName("La contraseña 'ajsdha' no tiene numeros")
    public void contraseniaNoTieneNumeros(){
        Assertions.assertFalse(tieneNumeros.cumpleLaRegla("ajsdha"));
    }

    @Test
    @DisplayName("La contraseña '<xd>' tiene caracteres especiales")
    public void contraseniaTieneCaracteresEspeciales(){
        Assertions.assertTrue(tieneCaracteresEspeciales.cumpleLaRegla("<xd>"));
    }

    @Test
    @DisplayName("La contraseña 'Hola@123' es valida")
    public void contraseniaQueCumpleLasReglasEsValida(){
        Assertions.assertTrue(autenticador.esValida("Hola@123"));
    }

    @Test
    @DisplayName("La contraseña 'holaComoAndan' no es valida")
    public void contraseniaQueNoCumpleLasReglasNoEsValida(){
        Assertions.assertFalse(autenticador.esValida("holaComoAndan22"));
    }

}
