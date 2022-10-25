/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniquindio.proyecto.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PreguntaRespuesta {
    
    private int codPregunta;
    private int codRespuesta;
    private String respuesta;
    private String pregunta;
    private boolean correcta;
    private int valor;
}
