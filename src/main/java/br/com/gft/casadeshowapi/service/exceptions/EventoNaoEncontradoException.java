package br.com.gft.casadeshowapi.service.exceptions;

public class EventoNaoEncontradoException extends RuntimeException  {	
		
		//exceção Runtime é uma exceção não checada, isto é, se o livro não for encontrado  poderemos tomar alguma ação em nivel de código, então lançaremos para camadas superiores para o tratamento adequado.
		
		/**
	 * 
	 */
	private static final long serialVersionUID = -6326600218150020543L;

		public EventoNaoEncontradoException(String mensagem) {
			super(mensagem);
		}

		public EventoNaoEncontradoException(String mensagem, Throwable causa) {
			super(mensagem, causa);
		}
		}
