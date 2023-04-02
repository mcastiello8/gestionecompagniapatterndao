package it.prova.gestionecompagniapatterndao.test;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import it.prova.gestionecompagniapatterndao.connection.MyConnection;
import it.prova.gestionecompagniapatterndao.dao.Constants;
import it.prova.gestionecompagniapatterndao.dao.compagnia.CompagniaDAO;
import it.prova.gestionecompagniapatterndao.dao.compagnia.CompagniaDAOImpl;
import it.prova.gestionecompagniapatterndao.dao.impiegato.ImpiegatoDAO;
import it.prova.gestionecompagniapatterndao.dao.impiegato.ImpiegatoDAOImpl;
import it.prova.gestionecompagniapatterndao.model.Compagnia;
import it.prova.gestionecompagniapatterndao.model.Impiegato;

public class TestGestioneCompagniaPatternDAO {

	public static void main(String[] args) {
		CompagniaDAO compagniaDAOInstance = null;
		ImpiegatoDAO impiegatoDAOInstance = null;

		// connessione
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {
			compagniaDAOInstance = new CompagniaDAOImpl(connection);
			impiegatoDAOInstance = new ImpiegatoDAOImpl(connection);

//			testInsertCompagnia(compagniaDAOInstance);
//			System.out.println("In tabella compagnia ci sono "+compagniaDAOInstance.list().size()+" elementi.");

//			testGetCompagnia(compagniaDAOInstance);
//			System.out.println("In tabella compagnia ci sono "+compagniaDAOInstance.list().size()+" elementi.");
//
//			System.out.println(compagniaDAOInstance.list());
//			TestUpdateCompagnia(compagniaDAOInstance);
//			System.out.println(compagniaDAOInstance.list());
//			System.out.println("In tabella compagnia ci sono "+compagniaDAOInstance.list().size()+" elementi.");

//			testDeleteCompagnia(compagniaDAOInstance);
//			System.out.println("In tabella compagnia ci sono " + compagniaDAOInstance.list().size() + " elementi.");

			testFindByExampleCompagnia(compagniaDAOInstance);
			System.out.println("In tabella compagnia ci sono " + compagniaDAOInstance.list().size() + " elementi.");

			testFindAllByDataAssunzioneMaggioreDi(compagniaDAOInstance, impiegatoDAOInstance);
			System.out.println("In tabella compagnia ci sono " + compagniaDAOInstance.list().size() + " elementi.");

			testFindAllByRagioneSocialeContiene(compagniaDAOInstance);
			System.out.println("In tabella compagnia ci sono " + compagniaDAOInstance.list().size() + " elementi.");

			testFindAllByCodFisImpiegatoContiene(compagniaDAOInstance, impiegatoDAOInstance);
			System.out.println("In tabella compagnia ci sono " + compagniaDAOInstance.list().size() + " elementi.");

//			testInsertImpiegato(compagniaDAOInstance, impiegatoDAOInstance);
//			System.out.println("In tabella impiegato ci sono "+impiegatoDAOInstance.list().size()+" elementi.");

//			testGetImpiegato(impiegatoDAOInstance);
//			System.out.println("In tabella impiegato ci sono "+impiegatoDAOInstance.list().size()+" elementi.");

//			testUpdateImpiegato(impiegatoDAOInstance, compagniaDAOInstance);
//			System.out.println("In tabella impiegato ci sono "+impiegatoDAOInstance.list().size()+" elementi.");

			testFindByExampleImpiegato(impiegatoDAOInstance);
			System.out.println("In tabella impiegato ci sono " + impiegatoDAOInstance.list().size() + " elementi.");

			testFindAllByCompagnia(impiegatoDAOInstance, compagniaDAOInstance);
			System.out.println("In tabella impiegato ci sono " + impiegatoDAOInstance.list().size() + " elementi.");

			testCountByDataFondazioneCompagniaGreaterThan(impiegatoDAOInstance, compagniaDAOInstance);
			System.out.println("In tabella impiegato ci sono " + impiegatoDAOInstance.list().size() + " elementi.");

			testFindAllByCompagniaConFatturatoMaggioreDi(impiegatoDAOInstance, compagniaDAOInstance);
			System.out.println("In tabella impiegato ci sono " + impiegatoDAOInstance.list().size() + " elementi.");

			testFindAllErroriAssunzione(impiegatoDAOInstance, compagniaDAOInstance);
			System.out.println("In tabella impiegato ci sono " + impiegatoDAOInstance.list().size() + " elementi.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void testInsertCompagnia(CompagniaDAO compagniaDAOInstance) throws Exception {
		System.out.println(".......testInsertCompagnia inizio......");
		int quantiElementiInseriti = compagniaDAOInstance
				.insert(new Compagnia("Burger King", 8000000, LocalDate.now()));
		if (quantiElementiInseriti < 1)
			throw new RuntimeException("testInsertCompagnia : FAILED");
		System.out.println(".......testInsertCompagnia fine: PASSED......");
	}

	private static void testGetCompagnia(CompagniaDAO compagniaDAOInstance) throws Exception {
		System.out.println(".......testGetCompagnia inizio......");
		List<Compagnia> elencoPresenti = compagniaDAOInstance.list();
		if (elencoPresenti.size() < 1)
			throw new RuntimeException("testGetCompagnia : FAILED, non ci sono voci sul DB");
		Compagnia primoDellaLista = elencoPresenti.get(0);
		Compagnia compagniaDaCercare = compagniaDAOInstance.get(primoDellaLista.getId());
		if (compagniaDaCercare == null
				|| !compagniaDaCercare.getRagioneSociale().equals(primoDellaLista.getRagioneSociale()))
			throw new RuntimeException("testGetCompagnia : FAILED, le ragioni sociali sono diverse");
		System.out.println(compagniaDaCercare);
		System.out.println(".......testGetCompagnia fine: PASSED......");
	}

	private static void TestUpdateCompagnia(CompagniaDAO compagniaDAOInstance) throws Exception {
		System.out.println(".......TestUpdateCompagnia inizio......");
		List<Compagnia> elencoPresenti = compagniaDAOInstance.list();
		if (elencoPresenti.size() < 1)
			throw new RuntimeException("testUpdateCompagnia : FAILED, non ci sono voci sul DB");
		Compagnia compagniaDaAggiornare = elencoPresenti.get(0);
		String nuovoNomeCompagnia = "McDonald's";
		System.out.println("...before update: " + compagniaDaAggiornare);
		compagniaDaAggiornare.setRagioneSociale(nuovoNomeCompagnia);
		compagniaDAOInstance.update(compagniaDaAggiornare);
		Long idCompagniaAggiornata = compagniaDaAggiornare.getId();
		Compagnia compagniaAggiornata = compagniaDAOInstance.get(idCompagniaAggiornata);
		System.out.println("...after update: " + compagniaAggiornata);
		if (compagniaAggiornata == null || !compagniaAggiornata.getRagioneSociale().equals(nuovoNomeCompagnia))
			throw new RuntimeException("TestUpdateCompagnia : FAILED");
		System.out.println(".......TestUpdateCompagnia fine: PASSED......");
	}

	private static void testDeleteCompagnia(CompagniaDAO compagniaDAOInstance) throws Exception {
		System.out.println(".......testDeleteCompagnia inizio......");

		List<Compagnia> elencoVociPresenti = compagniaDAOInstance.list();
		int numeroElementiPresentiPrimaDellaRimozione = elencoVociPresenti.size();
		if (numeroElementiPresentiPrimaDellaRimozione < 1)
			throw new RuntimeException("testDeleteUser : FAILED, non ci sono voci sul DB");
		Compagnia ultimaCompagniaDellaLista = elencoVociPresenti.get(numeroElementiPresentiPrimaDellaRimozione - 1);
		compagniaDAOInstance.delete(ultimaCompagniaDellaLista);
		int numeroElementiPresentiDopoLaRimozione = compagniaDAOInstance.list().size();
		if (numeroElementiPresentiDopoLaRimozione != numeroElementiPresentiPrimaDellaRimozione - 1)
			throw new RuntimeException("testDeleteUser : FAILED, la rimozione non è avvenuta");

		System.out.println(".......testDeleteCompagnia fine: PASSED......");
	}

	private static void testFindByExampleCompagnia(CompagniaDAO compagniaDAOInstance) throws Exception {
		System.out.println(".......testFindByExample inizio......");
		List<Compagnia> elencoVociPresenti = compagniaDAOInstance.list();
		if (elencoVociPresenti.size() < 1)
			throw new RuntimeException("testFindByExample : FAILED, non ci sono voci sul DB");
		Compagnia compagniaExample = new Compagnia("compagnia inesistente");
		List<Compagnia> listaCompagniaLikeExample = compagniaDAOInstance.findByExample(compagniaExample);
		if (listaCompagniaLikeExample.size() < 1) {
			throw new RuntimeException("testFindByExample : FAILED, non ci sono voci sul DB");
		}
		System.out.println("Gli elementi della lista sono: " + listaCompagniaLikeExample.size());
		System.out.println(listaCompagniaLikeExample);
		System.out.println(".......testFindByExample fine: PASSED.............");
	}

	private static void testFindAllByDataAssunzioneMaggioreDi(CompagniaDAO compagniaDAOInstance,
			ImpiegatoDAO impiegatoDAOInstance) throws Exception {
		System.out.println(".......testFindAllByDataAssunzioneMaggioreDi inizio......");
		List<Compagnia> elencoCompagniePresenti = compagniaDAOInstance.list();
		if (elencoCompagniePresenti.size() < 1)
			throw new RuntimeException("testFindAllByDataAssunzioneMaggioreDi : FAILED, non ci sono compagnia sul DB");
		List<Impiegato> elencoImpiegatiPresenti = impiegatoDAOInstance.list();
		if (elencoImpiegatiPresenti.size() < 1)
			throw new RuntimeException("testFindAllByDataAssunzioneMaggioreDi : FAILED, non ci sono impiegati sul DB");

		LocalDate dataDaRicercare = LocalDate.parse("2000-01-01");
		List<Compagnia> listaCompagniaLikeExample = compagniaDAOInstance
				.findAllByDataAssunzioneMaggioreDi(dataDaRicercare);
		if (listaCompagniaLikeExample.size() < 1) {
			throw new RuntimeException("testFindAllByDataAssunzioneMaggioreDi : FAILED, non ci sono voci sul DB");
		}
		System.out.println("Gli elementi della lista sono: " + listaCompagniaLikeExample.size());
		System.out.println(listaCompagniaLikeExample);
		System.out.println(".......testFindAllByDataAssunzioneMaggioreDi fine: PASSED.............");
	}

	private static void testFindAllByRagioneSocialeContiene(CompagniaDAO compagniaDAOInstance) throws Exception {
		System.out.println(".......testFindAllByRagioneSocialeContiene inizio......");
		List<Compagnia> elencoVociPresenti = compagniaDAOInstance.list();
		if (elencoVociPresenti.size() < 1)
			throw new RuntimeException("testFindAllByRagioneSocialeContiene : FAILED, non ci sono voci sul DB");
		String ragioneSocialeContiene = "ome";
		List<Compagnia> listaCompagniaLikeExample = compagniaDAOInstance
				.findAllByRagioneSocialeContiene(ragioneSocialeContiene);
		if (listaCompagniaLikeExample.size() < 1) {
			throw new RuntimeException("testFindAllByRagioneSocialeContiene : FAILED, non ci sono voci sul DB");
		}
		System.out.println("Gli elementi della lista sono: " + listaCompagniaLikeExample.size());
		System.out.println(listaCompagniaLikeExample);
		System.out.println(".......testFindAllByRagioneSocialeContiene fine: PASSED.............");
	}

	private static void testFindAllByCodFisImpiegatoContiene(CompagniaDAO compagniaDAOInstance,
			ImpiegatoDAO impiegatoDAOInstance) throws Exception {
		System.out.println(".......testFindAllByCodFisImpiegatoContiene inizio......");
		List<Compagnia> elencoCompagniePresenti = compagniaDAOInstance.list();
		if (elencoCompagniePresenti.size() < 1)
			throw new RuntimeException("testFindAllByCodFisImpiegatoContiene : FAILED, non ci sono compagnia sul DB");
		List<Impiegato> elencoImpiegatiPresenti = impiegatoDAOInstance.list();
		if (elencoImpiegatiPresenti.size() < 1)
			throw new RuntimeException("testFindAllByCodFisImpiegatoContiene : FAILED, non ci sono impiegati sul DB");

		String codFisDaCercare = "h501";
		List<Compagnia> listaCompagniaLikeExample = compagniaDAOInstance
				.findAllByCodFisImpiegatoContiene(codFisDaCercare);
		if (listaCompagniaLikeExample.size() < 1) {
			throw new RuntimeException("testFindAllByCodFisImpiegatoContiene : FAILED, non ci sono voci sul DB");
		}
		System.out.println("Gli elementi della lista sono: " + listaCompagniaLikeExample.size());
		System.out.println(listaCompagniaLikeExample);
		System.out.println(".......testFindAllByCodFisImpiegatoContiene fine: PASSED.............");
	}

	// METODI TEST CRUD IMPIEGATO
	private static void testInsertImpiegato(CompagniaDAO compagniaDAOInstance, ImpiegatoDAO impiegatoDAOInstance)
			throws Exception {
		System.out.println(".......testInsertImpiegato inizio......");
		// mi serve prima un negozio esistente
		List<Compagnia> elencoVociPresenti = compagniaDAOInstance.list();
		if (elencoVociPresenti.size() < 1)
			throw new RuntimeException("testGetCompagnia : FAILED, non ci sono voci sul DB");

		Compagnia compagniaDaAssegnare = elencoVociPresenti.get(0);
		LocalDate dataNascita = LocalDate.parse("1980-05-19");
		LocalDate dataAssunzione = LocalDate.parse("2023-03-19");
		int quantiElementiInseriti = impiegatoDAOInstance.insert(
				new Impiegato("Mario", "Rossi", "MRARSS80E19C351A", dataNascita, dataAssunzione, compagniaDaAssegnare));
		if (quantiElementiInseriti < 1)
			throw new RuntimeException("testInsertImpiegato : FAILED");
		List<Impiegato> listaImpiegati = impiegatoDAOInstance.list();
		System.out.println(listaImpiegati);
		System.out.println(".......testInsertImpiegato fine: PASSED......");
	}

	private static void testGetImpiegato(ImpiegatoDAO impiegatoDAOInstance) throws Exception {
		System.out.println(".......testGetImpiegato inizio......");
		List<Impiegato> elencoVociPresenti = impiegatoDAOInstance.list();
		if (elencoVociPresenti.size() < 1)
			throw new RuntimeException("testGetCompagnia : FAILED, non ci sono voci sul DB");
		Impiegato primaVoceDellaLista = elencoVociPresenti.get(0);
		Impiegato compagniaDaCercare = impiegatoDAOInstance.get(primaVoceDellaLista.getId());
		if (compagniaDaCercare == null || !compagniaDaCercare.getNome().equals(primaVoceDellaLista.getNome()))
			throw new RuntimeException("testGetImpiegato : FAILED, le ragioni sociali non corrispondono.");
		System.out.println(compagniaDaCercare);
		System.out.println(".......testGetImpiegato fine: PASSED......");
	}

	private static void testUpdateImpiegato(ImpiegatoDAO impiegatoDAOInstance, CompagniaDAO compagniaDAOInstance)
			throws Exception {
		System.out.println(".......TestUpdateImpiegato inizio......");
		List<Compagnia> elencoCompagniePresenti = compagniaDAOInstance.list();
		if (elencoCompagniePresenti.size() < 1)
			throw new RuntimeException("TestUpdateImpiegato : FAILED, non ci sono compagnia sul DB");
		List<Impiegato> elencoImpiegatiPresenti = impiegatoDAOInstance.list();
		if (elencoImpiegatiPresenti.size() < 1)
			throw new RuntimeException("TestUpdateImpiegato : FAILED, non ci sono impiegati sul DB");

		Impiegato impiegatoDaAggiornare = elencoImpiegatiPresenti.get(1);
		String nuovoNomeImpiegato = "MArio";
		Compagnia nuovaCompagniaDaAssegnare = elencoCompagniePresenti.get(2);
		System.out.println(nuovaCompagniaDaAssegnare);
		System.out.println("...before update: " + impiegatoDaAggiornare);
		impiegatoDaAggiornare.setNome(nuovoNomeImpiegato);
		impiegatoDaAggiornare.setCompagnia(nuovaCompagniaDaAssegnare);
		impiegatoDAOInstance.update(impiegatoDaAggiornare);
		Long idImpiegatoAggiornato = impiegatoDaAggiornare.getId();
		Impiegato impiegatoAggiornato = impiegatoDAOInstance.get(idImpiegatoAggiornato);
		System.out.println("...after update: " + impiegatoAggiornato);
		if (impiegatoAggiornato == null || !impiegatoAggiornato.getNome().equals(nuovoNomeImpiegato))
			throw new RuntimeException("TestUpdateImpiegato : FAILED");
		System.out.println(".......TestUpdateImpiegato fine: PASSED......");
	}

	private static void testFindByExampleImpiegato(ImpiegatoDAO impiegatoDAOInstance) throws Exception {
		System.out.println(".......testFindByExampleImpiegato inizio......");
		List<Impiegato> elencoVociPresenti = impiegatoDAOInstance.list();
		if (elencoVociPresenti.size() < 1)
			throw new RuntimeException("testFindByExampleImpiegato : FAILED, non ci sono voci sul DB");
		Impiegato impiegatoExample = new Impiegato("marie", "ro");
		List<Impiegato> listaCompagniaLikeExample = impiegatoDAOInstance.findByExample(impiegatoExample);
		if (listaCompagniaLikeExample.size() < 1) {
			throw new RuntimeException("testFindByExampleImpiegato : FAILED, non ci sono voci sul DB");
		}
		System.out.println("Gli elementi della lista sono: " + listaCompagniaLikeExample.size());
		System.out.println(listaCompagniaLikeExample);
		System.out.println(".......testFindByExampleImpiegato fine: PASSED.............");
	}

	private static void testFindAllByCompagnia(ImpiegatoDAO impiegatoDAOInstance, CompagniaDAO compagniaDAOInstance)
			throws Exception {
		System.out.println(".......testFindAllByCompagnia inizio......");
		List<Compagnia> elencoCompagniePresenti = compagniaDAOInstance.list();
		if (elencoCompagniePresenti.size() < 1)
			throw new RuntimeException("testFindAllByCompagnia : FAILED, non ci sono compagnia sul DB");
		List<Impiegato> elencoImpiegatiPresenti = impiegatoDAOInstance.list();
		if (elencoImpiegatiPresenti.size() < 1)
			throw new RuntimeException("testFindAllByCompagnia : FAILED, non ci sono impiegati sul DB");
		Compagnia compagniaDaRicercare = elencoCompagniePresenti.get(0);
		List<Impiegato> listaImpiegatiByCompagnia = impiegatoDAOInstance.findAllByCompagnia(compagniaDaRicercare);
		if (listaImpiegatiByCompagnia.size() < 1) {
			throw new RuntimeException("testFindAllByCompagnia : FAILED, non ci sono voci sul DB");
		}
		System.out.println("Gli elementi della lista sono: " + listaImpiegatiByCompagnia.size());
		System.out.println(listaImpiegatiByCompagnia);
		System.out.println(".......testFindAllByCompagnia fine: PASSED.............");
	}

	private static void testCountByDataFondazioneCompagniaGreaterThan(ImpiegatoDAO impiegatoDAOInstance,
			CompagniaDAO compagniaDAOInstance) throws Exception {
		System.out.println(".......testCountByDataFondazioneCompagniaGreaterThan inizio......");
		List<Compagnia> elencoCompagniePresenti = compagniaDAOInstance.list();
		if (elencoCompagniePresenti.size() < 1)
			throw new RuntimeException(
					"testCountByDataFondazioneCompagniaGreaterThan : FAILED, non ci sono compagnia sul DB");
		List<Impiegato> elencoImpiegatiPresenti = impiegatoDAOInstance.list();
		if (elencoImpiegatiPresenti.size() < 1)
			throw new RuntimeException(
					"testCountByDataFondazioneCompagniaGreaterThan : FAILED, non ci sono impiegati sul DB");
		LocalDate dataDaRicercare = LocalDate.parse("2005-01-01");
		int countImpiegati = impiegatoDAOInstance.countByDataFondazioneCompagniaGreaterThan(dataDaRicercare);
		System.out.println("Il contatore segna: " + countImpiegati);
		System.out.println(".......testCountByDataFondazioneCompagniaGreaterThan fine: PASSED.............");
	}

	private static void testFindAllByCompagniaConFatturatoMaggioreDi(ImpiegatoDAO impiegatoDAOInstance,
			CompagniaDAO compagniaDAOInstance) throws Exception {
		System.out.println(".......testFindAllByCompagniaConFatturatoMaggioreDi inizio......");
		List<Compagnia> elencoCompagniePresenti = compagniaDAOInstance.list();
		if (elencoCompagniePresenti.size() < 1)
			throw new RuntimeException(
					"testFindAllByCompagniaConFatturatoMaggioreDi : FAILED, non ci sono compagnia sul DB");
		List<Impiegato> elencoImpiegatiPresenti = impiegatoDAOInstance.list();
		if (elencoImpiegatiPresenti.size() < 1)
			throw new RuntimeException(
					"testFindAllByCompagniaConFatturatoMaggioreDi : FAILED, non ci sono impiegati sul DB");

		int fatturatoMaggioreDi = 8000;
		List<Impiegato> listaImpiegatiTrovata = impiegatoDAOInstance
				.findAllByCompagniaConFatturatoMaggioreDi(fatturatoMaggioreDi);
		System.out.println("Gli elementi della lista sono: " + listaImpiegatiTrovata.size());
		System.out.println(listaImpiegatiTrovata);
		System.out.println(".......testFindAllByCompagniaConFatturatoMaggioreDi fine: PASSED.............");
	}

	private static void testFindAllErroriAssunzione(ImpiegatoDAO impiegatoDAOInstance,
			CompagniaDAO compagniaDAOInstance) throws Exception {
		System.out.println(".......testFindAllErroriAssunzione inizio......");
		List<Compagnia> elencoCompagniePresenti = compagniaDAOInstance.list();
		if (elencoCompagniePresenti.size() < 1)
			throw new RuntimeException("testFindAllErroriAssunzione : FAILED, non ci sono compagnia sul DB");
		List<Impiegato> elencoImpiegatiPresenti = impiegatoDAOInstance.list();
		if (elencoImpiegatiPresenti.size() < 1)
			throw new RuntimeException("testFindAllErroriAssunzione : FAILED, non ci sono impiegati sul DB");

		List<Impiegato> listaImpiegatiTrovata = impiegatoDAOInstance.findAllErroriAssunzione();
		System.out.println("Gli elementi della lista sono: " + listaImpiegatiTrovata.size());
		System.out.println(listaImpiegatiTrovata);
		System.out.println(".......testFindAllErroriAssunzione fine: PASSED.............");
	}

}
