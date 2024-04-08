package utez.edu.mx.MedicalService.utils.encrypt;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class RequestInterceptor extends OncePerRequestFilter {

    @Autowired
    EcryptDecrypt ecryptDecrypt;

    @Override
    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain) throws jakarta.servlet.ServletException, IOException {
        // Obtener el cuerpo de la petición como una cadena
        String dataEncrypt = request.getReader().lines().reduce((a, b) -> a + "\n" + b).orElse("");

        if(dataEncrypt==null || dataEncrypt.isEmpty()){
            filterChain.doFilter(request, response);
        }else{

            // Convertir la cadena JSON a un objeto JSON
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(dataEncrypt, JsonObject.class);


            // Obtener el valor que acompaña a "encryptedData" el cual es el cuerpo de la petición
            String encryptedDataValue = jsonObject.get("encryptedData").getAsString();

            if (encryptedDataValue == null || encryptedDataValue.isEmpty()) {
                // Si no hay valor para "encryptedData", enviar una respuesta de error
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Al parecer tratas de hacer una petion incorrectamente");
                return; // Salir del método
            }



            // Desencriptar la información
            String decryptData = ecryptDecrypt.decrypt(encryptedDataValue);

            // Crear un nuevo HttpServletRequestWrapper con el cuerpo de la solicitud modificado
            HttpServletRequestWrapper wrappedRequest = new HttpServletRequestWrapper(request) {
                @Override
                public BufferedReader getReader() {
                    return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(decryptData.getBytes())));
                }

                @Override
                public ServletInputStream getInputStream() throws IOException {
                    final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(decryptData.getBytes());
                    return new ServletInputStream() {
                        @Override
                        public int read() throws IOException {
                            return byteArrayInputStream.read();
                        }

                        @Override
                        public boolean isFinished() {
                            return false;
                        }

                        @Override
                        public boolean isReady() {
                            return true;
                        }

                        @Override
                        public void setReadListener(ReadListener readListener) {
                            // Do nothing
                        }
                    };
                }
            };

            // Pasar la solicitud envuelta al siguiente filtro en la cadena
            filterChain.doFilter(wrappedRequest, response);

        }
    }
}