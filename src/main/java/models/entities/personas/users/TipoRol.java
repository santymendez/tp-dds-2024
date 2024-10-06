package models.entities.personas.users;

import io.javalin.security.RouteRole;

/**
 * Enum para los roles de los usuarios.
 */

public enum TipoRol implements RouteRole {
    PERSONA_FISICA,
    PERSONA_JURIDICA,
    EMPRESA_ASOCIADA, //TODO ???
    ADMINISTRADOR,
    TECNICO
}
