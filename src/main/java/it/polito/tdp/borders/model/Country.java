package it.polito.tdp.borders.model;

public class Country {
	
	private String abbreviazione;
	private int codice;
	private String nome;
	
	public Country(String abbreviazione, int codice, String nome) {
		super();
		this.abbreviazione = abbreviazione;
		this.codice = codice;
		this.nome = nome;
	}

	public String getAbbreviazione() {
		return abbreviazione;
	}

	public void setAbbreviazione(String abbreviazione) {
		this.abbreviazione = abbreviazione;
	}

	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((abbreviazione == null) ? 0 : abbreviazione.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (abbreviazione == null) {
			if (other.abbreviazione != null)
				return false;
		} else if (!abbreviazione.equals(other.abbreviazione))
			return false;
		return true;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nome;
	}
/*
	public String toStringBello() {
		return nome;
	}*/
}
