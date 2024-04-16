package modules.passwordValidator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PasswordTest {

    private Registro registro;
    private Usuario usuario;
    private Controlador controlador;
    private Regex regex;

    @BeforeEach
    public void inicializar(){
        this.regex = new Regex();
        this.controlador = new Controlador(regex);
        this.usuario = new Usuario("usuario", "123456789");
        this.registro = new Registro(usuario, controlador);
    }

    @Test
    @DisplayName("La contraseña 123456789 pertenece a la lista de top peores contraseñas")
    public void contraseniaInvalida(){
        Assertions.assertFalse(this.controlador.noPerteneceAPeoresContrasenias(usuario.getContrasenia()));
    }

    @Test
    @DisplayName("La contraseña hgTy76@3dfr no pertenece a la lista de top peores contraseñas")
    public void contraseniaValida(){
        usuario.setContrasenia("hgTy76@3dfr");
        Assertions.assertTrue(this.controlador.noPerteneceAPeoresContrasenias(usuario.getContrasenia()));
    }

    @Test
    @DisplayName("El usuario no pudo registrarse con la contraseña 123456789")
    public void usuarioNoPuedeRegistrarse(){
        Assertions.assertFalse(this.registro.sePuedeRegistrar());
    }

    @Test
    @DisplayName("El usuario se pudo registrar correctamente con la contraseña hgTy76@3dfr")
    public void usuarioSePuedeRegistrar(){
        this.usuario.setContrasenia("hgTy76@3dfr");
        Assertions.assertTrue(this.registro.sePuedeRegistrar());
    }
}