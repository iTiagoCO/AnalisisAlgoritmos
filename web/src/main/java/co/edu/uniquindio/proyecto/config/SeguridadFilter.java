package co.edu.uniquindio.proyecto.config;

import co.edu.uniquindio.proyecto.bean.SeguridadBean;
import org.springframework.stereotype.Component;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SeguridadFilter implements Filter {


    public static final String PAGINA_INICIO = "../webApp/index.xhtml";

    @Override
    public void doFilter(ServletRequest servletRequest,

                         ServletResponse servletResponse, FilterChain filterChain) {

        try {
            final HttpServletRequest request = (HttpServletRequest) servletRequest;
            final HttpServletResponse response = (HttpServletResponse) servletResponse;
            final String requestURI = request.getRequestURI();
            //Aplicar el filtro a esta carpeta
            if (requestURI.startsWith("../webApp/estudiante/")) {
                //Obtenemos el objeto seguridadBean de la sesión actual
                SeguridadBean userManager = (SeguridadBean)
                        request.getSession().getAttribute("seguridadBean");

                if (userManager != null) {
                    if (userManager.estaLogueado()) {
                        //El usuario está logueado entonces si puede ver la página


                        filterChain.doFilter(servletRequest, servletResponse);
                    } else {
                        //El usuario no está logueado, entonces se redirecciona al


                        response.sendRedirect(request.getContextPath() + PAGINA_INICIO);
                    }
                } else {
                    //El usuario no está logueado, entonces se redirecciona al inicio
                    response.sendRedirect(request.getContextPath() + PAGINA_INICIO);
                }
            } else {
                //La página solicitada no está en la carpeta /usuario entonces el filtro no


                filterChain.doFilter(servletRequest, servletResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}