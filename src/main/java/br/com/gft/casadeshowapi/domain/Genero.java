package br.com.gft.casadeshowapi.domain;

public enum Genero {

	
    AXE("Axé"),
    BLUES("Blues"),
    COUNTRY("Country"),
    ELETRONICA("Eletronica"),
    FORRO("Forró"),
    FUNK("Funk"),
    GOSPEL("Gospel"),
    HIP("Hip Hop"),
    JAZZ("Jazz"),
    MPB("MPB"),
    CLASSICA("Música Clássica"),
    OUTROS("Outros"),
    PAGODE("Pagode"),
    POP("Pop"),
    RAP("Rap"),
    REGGAE("Reggae"),
    ROCK("Rock"),
    SAMBA("Samba"),
    SERTANEJO("Sertanejo");

	private String descricao;
	
	Genero(String descricao) {
		this.descricao = descricao;

	}
	
	public String getDescricao() {
		
		return descricao;
	}
	
	
}
