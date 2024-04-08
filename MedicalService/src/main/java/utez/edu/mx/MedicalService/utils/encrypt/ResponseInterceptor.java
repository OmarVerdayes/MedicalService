package utez.edu.mx.MedicalService.utils.encrypt;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;


@Component
public class ResponseInterceptor  extends OncePerRequestFilter {

    @Autowired
    EcryptDecrypt ecryptDecrypt;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Capturar el contenido del response antes de enviarlo
        ContentCaptureServletResponse wrappedResponse = new ContentCaptureServletResponse(response);

        filterChain.doFilter(request, wrappedResponse);

        // Obtener el contenido capturado
        String content = wrappedResponse.getContentAsString();

        // Escribir el contenido capturado en la respuesta incriptado
        response.getOutputStream().write(ecryptDecrypt.encrypt(content).getBytes());
    }

    private static class ContentCaptureServletResponse extends HttpServletResponseWrapper {
        private final ByteArrayOutputStream content = new ByteArrayOutputStream();

        public ContentCaptureServletResponse(HttpServletResponse response) {
            super(response);
        }

        @Override
        public ServletOutputStream getOutputStream() throws IOException {
            return new ServletOutputStream() {
                @Override
                public boolean isReady() {
                    return false;
                }

                @Override
                public void setWriteListener(WriteListener listener) {
                }

                @Override
                public void write(int b) throws IOException {
                    content.write(b);
                }

                @Override
                public void flush() throws IOException {
                    content.flush();
                }
            };
        }

        @Override
        public PrintWriter getWriter() throws IOException {
            return new PrintWriter(content, true);
        }

        public String getContentAsString() {
            return content.toString();
        }
    }
}