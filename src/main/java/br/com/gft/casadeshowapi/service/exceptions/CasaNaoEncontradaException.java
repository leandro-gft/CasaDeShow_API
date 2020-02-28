package br.com.gft.casadeshowapi.service.exceptions;

public class CasaNaoEncontradaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1825048785032326758L;

	//exceção Runtime é uma exceção não checada, isto é, se o livro não for encontrado  poderemos tomar alguma ação em nivel de código, então lançaremos para camadas superiores para o tratamento adequado.
	
	public CasaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public CasaNaoEncontradaException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	}
