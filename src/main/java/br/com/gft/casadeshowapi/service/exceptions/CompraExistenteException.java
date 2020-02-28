package br.com.gft.casadeshowapi.service.exceptions;

public class CompraExistenteException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6889257125833475790L;

	public CompraExistenteException(String mensagem) {
		super(mensagem);
	}

	public CompraExistenteException(String mensagem, Throwable causa) {
		super(mensagem, causa);
}
}
