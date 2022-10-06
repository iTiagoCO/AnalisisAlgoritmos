package co.edu.uniquindio.proyecto.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendEmail {


    private String toEmail, subject,  body, from;
}
