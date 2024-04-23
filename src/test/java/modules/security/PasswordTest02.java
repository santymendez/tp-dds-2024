package modules.security;

import modules.passwordValidator02.Autenticador;
import modules.passwordValidator02.rules.CantidadMaximaDeCaracteres;
import modules.passwordValidator02.rules.CantidadMinimaDeCaracteres;
import modules.passwordValidator02.rules.PerteneceAlArchivo;
import modules.passwordValidator02.rules.TieneMayusculasYMinusculas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PasswordTest02 {
    Autenticador autenticador;
    CantidadMaximaDeCaracteres cantidadMaximaDeCaracteres;
    CantidadMinimaDeCaracteres cantidadMinimaDeCaracteres;
    PerteneceAlArchivo perteneceAlArchivo;
    TieneMayusculasYMinusculas tieneMayusculasYMinusculas;

    @BeforeEach
    public void inicializar(){
        cantidadMaximaDeCaracteres = new CantidadMaximaDeCaracteres(64);
        cantidadMinimaDeCaracteres = new CantidadMinimaDeCaracteres(8);
        perteneceAlArchivo = new PerteneceAlArchivo("Top10milContrasenias", "src/main/resources/passwordValidator/top10000.txt");
        tieneMayusculasYMinusculas = new TieneMayusculasYMinusculas();

        autenticador = new Autenticador();
        autenticador.agregarPoliticas(
                cantidadMaximaDeCaracteres,
                cantidadMinimaDeCaracteres,
                perteneceAlArchivo,
                tieneMayusculasYMinusculas);
    }

    @Test
    @DisplayName("La contraseña 123456789 pertenece a la lista de top peores contraseñas")
    public void contraseniaPertenecienteATopPeoresContrasenias(){
        Assertions.assertTrue(cantidadMinimaDeCaracteres.cumpleLaRegla("123456789"));
        Assertions.assertTrue(cantidadMaximaDeCaracteres.cumpleLaRegla("123456789"));
        Assertions.assertFalse(tieneMayusculasYMinusculas.cumpleLaRegla("123456789"));
        Assertions.assertFalse(perteneceAlArchivo.cumpleLaRegla("123456789"));

        String errorEsperado = "Error: Pertenece al archivo Top10milContrasenias\n" +
                "Error: No tiene letras mayusculas y minusculas\n";
        Assertions.assertEquals(errorEsperado, autenticador.esValida("123456789"));
    }
}
