package it.prova.gestionecompagniapatterndao.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Compagnia {
	private Long id;
	private String ragioneSociale;
	private int fatturatoAnnuo;
	private LocalDate dataFondazione;
	private List<Impiegato> impiegati = new ArrayList<>();

	public Compagnia() {
	}

	public Compagnia(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	public Compagnia(Long id, String ragioneSociale) {
		this.id = id;
		this.ragioneSociale = ragioneSociale;
	}

	public Compagnia(String ragioneSociale, int fatturatoAnnuo) {
		this.ragioneSociale = ragioneSociale;
		this.fatturatoAnnuo = fatturatoAnnuo;
	}

	public Compagnia(String ragioneSociale, int fatturatoAnnuo, LocalDate dataFondazione) {
		this.ragioneSociale = ragioneSociale;
		this.fatturatoAnnuo = fatturatoAnnuo;
		this.dataFondazione = dataFondazione;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRagioneSociale() {
		return ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	public int getFatturatoAnnuo() {
		return fatturatoAnnuo;
	}

	public void setFatturatoAnnuo(int fatturatoAnnuo) {
		this.fatturatoAnnuo = fatturatoAnnuo;
	}

	public LocalDate getDataFondazione() {
		return dataFondazione;
	}

	public void setDataFondazione(LocalDate dataFondazione) {
		this.dataFondazione = dataFondazione;
	}

	public List<Impiegato> getImpiegati() {
		return impiegati;
	}

	public void setImpiegati(List<Impiegato> impiegati) {
		this.impiegati = impiegati;
	}

	@Override
	public String toString() {
		return "Compagnia [id=" + this.id + ", ragioneSociale=" + ragioneSociale + ", fatturatoAnnuo=" + fatturatoAnnuo
				+ " data fondazione=" + dataFondazione + "]";
	}

}
