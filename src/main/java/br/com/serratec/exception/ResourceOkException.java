package br.com.serratec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.OK) // 200
public class ResourceOkException extends RuntimeException {
    
    public ResourceOkException(String mensagem) {
        super(mensagem);
    }

}
