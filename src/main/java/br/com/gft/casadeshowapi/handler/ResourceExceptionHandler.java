package br.com.gft.casadeshowapi.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.gft.casadeshowapi.domain.DetalhesErro;
import br.com.gft.casadeshowapi.service.exceptions.CasaExistenteException;
import br.com.gft.casadeshowapi.service.exceptions.CasaNaoEncontradaException;
import br.com.gft.casadeshowapi.service.exceptions.CompraExistenteException;
import br.com.gft.casadeshowapi.service.exceptions.CompraNaoEncontradaException;
import br.com.gft.casadeshowapi.service.exceptions.EventoNaoEncontradoException;
import br.com.gft.casadeshowapi.service.exceptions.UserNaoEncontradoException;
import br.com.gft.casadeshowapi.service.exceptions.UsuarioExistenteException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(EventoNaoEncontradoException.class) //captura qualquer lugar que chame essa classe 
	public ResponseEntity<DetalhesErro> handleEventoNaoEncontradoException(EventoNaoEncontradoException e, HttpServletRequest request) {
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo("O evento não pôde ser encontrado!");
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/404");
		erro.setTimestamp(System.currentTimeMillis());					
		
	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(CompraNaoEncontradaException.class) //captura qualquer lugar que chame essa classe 
	public ResponseEntity<DetalhesErro> handleCompraNaoEncontradaExceptionn(CompraNaoEncontradaException e, HttpServletRequest request) {
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo("A compra não pôde ser encontrado!");
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/404");
		erro.setTimestamp(System.currentTimeMillis());					
		
	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	
	@ExceptionHandler(CasaExistenteException.class) //captura qualquer lugar que chame essa classe 
	public ResponseEntity<DetalhesErro> handleCasaExistenteException(CasaExistenteException e, HttpServletRequest request) {
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(409l);
		erro.setTitulo("Casa de show já existente!");
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/409");
		erro.setTimestamp(System.currentTimeMillis());					
		
	return ResponseEntity.status(HttpStatus.CONFLICT ).body(erro);
	}
	
	@ExceptionHandler(UsuarioExistenteException.class) //captura qualquer lugar que chame essa classe 
	public ResponseEntity<DetalhesErro> handleUsuarioExistenteException(UsuarioExistenteException e, HttpServletRequest request) {
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(409l);
		erro.setTitulo("Usuário já existente!");
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/409");
		erro.setTimestamp(System.currentTimeMillis());					
		
	return ResponseEntity.status(HttpStatus.CONFLICT ).body(erro);
	}
	
	@ExceptionHandler(CompraExistenteException.class) //captura qualquer lugar que chame essa classe 
	public ResponseEntity<DetalhesErro> handleCompraExistenteException(CompraExistenteException e, HttpServletRequest request) {
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(409l);
		erro.setTitulo("Compra já existente!");
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/409");
		erro.setTimestamp(System.currentTimeMillis());					
		
	return ResponseEntity.status(HttpStatus.CONFLICT ).body(erro);
	}
	
	@ExceptionHandler(CasaNaoEncontradaException.class) //captura qualquer lugar que chame essa classe 
	public ResponseEntity<DetalhesErro> handleCasaNaoEncontradaException(CasaNaoEncontradaException e, HttpServletRequest request) {
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo("A casa não pôde ser encontrada!");
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/404");
		erro.setTimestamp(System.currentTimeMillis());					
		
	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class) //captura qualquer lugar que chame essa classe 
	public ResponseEntity<DetalhesErro> handleDataIntegrityViolationException(DataIntegrityViolationException e, HttpServletRequest request) {
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(400l);
		erro.setTitulo("Requisição inválida.");
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/400");
		erro.setTimestamp(System.currentTimeMillis());					
		
	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
		
	@ExceptionHandler(UserNaoEncontradoException.class) //captura qualquer lugar que chame essa classe 
	public ResponseEntity<DetalhesErro> handleUserNaoEncontradoException(UserNaoEncontradoException e, HttpServletRequest request) {
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo("O usuário não pôde ser encontrado!");
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/404");
		erro.setTimestamp(System.currentTimeMillis());					
		
	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
}

