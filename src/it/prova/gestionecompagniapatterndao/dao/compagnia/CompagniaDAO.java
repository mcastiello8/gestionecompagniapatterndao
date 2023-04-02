package it.prova.gestionecompagniapatterndao.dao.compagnia;

import java.time.LocalDate;
import java.util.List;

import it.prova.gestionecompagniapatterndao.dao.IBaseDAO;
import it.prova.gestionecompagniapatterndao.model.Compagnia;

public interface CompagniaDAO extends IBaseDAO<Compagnia> {
	public List<Compagnia> findAllByDataAssunzioneMaggioreDi(LocalDate data) throws Exception;

	public List<Compagnia> findAllByRagioneSocialeContiene(String ragioneSocialeInput) throws Exception;

	public List<Compagnia> findAllByCodFisImpiegatoContiene(String codFisInput) throws Exception;
}
