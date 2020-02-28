package br.com.gft.casadeshowapi.service.exceptions;

public class UsuarioExistenteException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6889257125833475790L;

	public UsuarioExistenteException(String mensagem) {
		super(mensagem);
	}

	public UsuarioExistenteException(String mensagem, Throwable causa) {
		super(mensagem, causa);
}
}
