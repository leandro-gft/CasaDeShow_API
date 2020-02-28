package br.com.gft.casadeshowapi.service.exceptions;

public class CasaExistenteException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6889257125833475790L;

	public CasaExistenteException(String mensagem) {
		super(mensagem);
	}

	public CasaExistenteException(String mensagem, Throwable causa) {
		super(mensagem, causa);
}
}
