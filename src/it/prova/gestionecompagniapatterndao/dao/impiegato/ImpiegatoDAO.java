package it.prova.gestionecompagniapatterndao.dao.impiegato;

import java.time.LocalDate;
import java.util.List;

import it.prova.gestionecompagniapatterndao.dao.IBaseDAO;
import it.prova.gestionecompagniapatterndao.model.Compagnia;
import it.prova.gestionecompagniapatterndao.model.Impiegato;

public interface ImpiegatoDAO extends IBaseDAO<Impiegato> {
	public List<Impiegato> findAllByCompagnia(Compagnia compagnia) throws Exception;

	public int countByDataFondazioneCompagniaGreaterThan(LocalDate data) throws Exception;

	public List<Impiegato> findAllByCompagniaConFatturatoMaggioreDi(int fatturatoInput) throws Exception;

	public List<Impiegato> findAllErroriAssunzione() throws Exception;
}