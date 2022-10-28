package br.com.serratec.exception;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.serratec.status.StatusMessage;


@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EmailException.class)
	public ResponseEntity<Object> handleEmailException(EmailException ex) {
		
		return ResponseEntity.unprocessableEntity().body(ex.getMessage());
	}
	
	@ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException ex) {
        return ResponseEntity.unprocessableEntity().body("CEP INVALIDO AMIGAO!");
    }
	
	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
            List<String> erros = new ArrayList<>();
            for(FieldError erro : ex.getBindingResult().getFieldErrors()) {
                erros.add(erro.getField() + ":" + erro.getDefaultMessage());
            }

        ErroResposta erroResposta = new ErroResposta(status.value(),"Existem campos invalidos. Confira o preenchimento", LocalDateTime.now(),erros);

        return super.handleExceptionInternal(ex, erroResposta, headers, status, request);
    }

	    @ExceptionHandler(ResourceNotFoundException.class)
	    public ResponseEntity<StatusMessage> handleResourceOkException2(ResourceOkException ex) {
	        
	        //Aqui eu estou criando o objeto que vai conter as informações da mensagem personalizada.
	        StatusMessage status = new StatusMessage(HttpStatus.OK.value(), "Finalizado", ex.getMessage());

	        //Aqui eu estou devolvendo o erro personalizado para o usuario.
	        return new ResponseEntity<>(status, HttpStatus.OK);
	    }
	    
	    
	    @ExceptionHandler(ResourceOkException.class)
        public ResponseEntity<StatusMessage> handleResourceOkException(ResourceOkException ex) {
            
            //Aqui eu estou criando o objeto que vai conter as informações da mensagem personalizada.
            StatusMessage status = new StatusMessage(HttpStatus.OK.value(), "Nao Finalizado", ex.getMessage());

            //Aqui eu estou devolvendo o erro personalizado para o usuario.
            return new ResponseEntity<>(status, HttpStatus.OK);
        }
	    
	    @ExceptionHandler(CpfException.class)
	    public ResponseEntity<Object> handleCpfException(CpfException cpfException) {
	        return ResponseEntity.unprocessableEntity().body(cpfException.getMessage());
	    }
	
	
}
