package dtos;

import lombok.Data;

/**
 * Representa el Input de un Colaborador.
 */

@Data
public class ColaboradorInputDto {
  //Persona fisica
  private String nombre;
  private String apellido;
  private String tipoDocumento;
  private String numeroDocumento;

  //Persona Juridica
  private String razonSocial;
  private String tipo;
  private String rubro;

//  //Direccion
////  private String nombreUbicacion;
////  private String longitud;
////  private String latitud;
//  private String nombreBarrio;
//  private String calle;
//  private String numero;
//  private String nombreCiudad;
//  private String nombreProvincia;

  private String contacto;
  private String tipoContacto;

  private String tipoColaborador;
}
