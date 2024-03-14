package it.softre.thip.vendite.uds;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.common.BaseComponentsCollection;
import com.thera.thermfw.common.BusinessObject;
import com.thera.thermfw.common.ErrorList;
import com.thera.thermfw.common.ErrorMessage;
import com.thera.thermfw.persist.ConnectionManager;
import com.thera.thermfw.persist.CopyException;
import com.thera.thermfw.persist.Copyable;
import com.thera.thermfw.persist.DB2NetDatabase;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.PersistentObject;
import com.thera.thermfw.persist.Proxy;
import com.thera.thermfw.persist.SQLServerJTDSNoUnicodeDatabase;

import it.thera.thip.base.articolo.Articolo;
import it.thera.thip.base.articolo.ArticoloVersione;
import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.base.cliente.ClienteVendita;
import it.thera.thip.base.comuniVenAcq.GestoreCalcoloCosti;
import it.thera.thip.base.comuniVenAcq.OrdineTestata;
import it.thera.thip.base.comuniVenAcq.TipoCostoRiferimento;
import it.thera.thip.base.comuniVenAcq.TipoRiga;
import it.thera.thip.base.comuniVenAcq.web.CalcoloQuantitaWeb;
import it.thera.thip.base.comuniVenAcq.web.CalcoloQuantitaWrapper;
import it.thera.thip.base.generale.Numeratore;
import it.thera.thip.base.generale.Serie;
import it.thera.thip.base.generale.UnitaMisura;
import it.thera.thip.base.prezziExtra.DocOrdRigaPrezziExtra;
import it.thera.thip.vendite.documentoVE.DocumentoVenRigaPrm;
import it.thera.thip.vendite.documentoVE.DocumentoVendita;
import it.thera.thip.vendite.documentoVE.web.DocumentoVenditaDataCollector;
import it.thera.thip.vendite.generaleVE.CausaleDocumentoVendita;
import it.thera.thip.vendite.generaleVE.PersDatiVen;
import it.thera.thip.vendite.ordineVE.GestoreEvasioneVendita;
import it.thera.thip.vendite.ordineVE.OrdineVenditaRigaPrm;
import it.thera.thip.vendite.ordineVE.OrdineVenditaTestata;
import it.thera.thip.vendite.prezziExtra.DocRigaPrezziExtraVendita;
import it.thera.thip.vendite.prezziExtra.OrdineRigaPrezziExtraVendita;

/**
 * 
 * @author DSSOF3	
 *	70687	DSSOF3	Classe per l'evasione di un uds vendita, contiene i parametri della form.
 *					E contiene inoltre i metodi per eseguire l'evasione.
 */

public class YEvasioneUdsVendita implements BusinessObject{

	protected String iIdAzienda;

	protected String iRSerie;

	protected Date iDataDocumento;

	protected String iRCauDocTes;

	protected String iRCliente;

	protected Date iDataRifIntestatario;

	protected String iNumeroRifIntestatario;

	protected String iRIdNumeratoreSerie;

	protected Proxy iRelRCauDocTes = new Proxy(it.thera.thip.vendite.generaleVE.CausaleDocumentoVendita.class);

	protected Proxy iRelSerie = new Proxy(it.thera.thip.base.generale.Serie.class);

	protected Proxy iRelCliente = new Proxy(it.thera.thip.base.cliente.ClienteVendita.class);

	protected Proxy iRelNumeratore = new Proxy(Numeratore.class);

	public YEvasioneUdsVendita(){
		setIdAzienda(Azienda.getAziendaCorrente());
		setRIdNumeratoreSerie("DOC_VEN");
	}
	public String getIdAzienda() {
		return iIdAzienda;
	}

	public void setIdAzienda(String iIdAzienda) {
		this.iIdAzienda = iIdAzienda;
	}

	public String getRSerie() {
		return KeyHelper.getTokenObjectKey(iRelSerie.getKey(), 3);
	}

	public Date getDataRifIntestatario() {
		return iDataRifIntestatario;
	}

	public void setDataRifIntestatario(Date iDataRifIntestatario) {
		this.iDataRifIntestatario = iDataRifIntestatario;
	}

	public String getNumeroRifIntestatario() {
		return iNumeroRifIntestatario;
	}

	public void setNumeroRifIntestatario(String iNumeroRifIntestatario) {
		this.iNumeroRifIntestatario = iNumeroRifIntestatario;
	}

	public String getRIdNumeratoreSerie() {
		return KeyHelper.getTokenObjectKey(iRelNumeratore.getKey(), 2);
	}

	public void setRIdNumeratoreSerie(String iRIdNumeratoreSerie) {
		iRelSerie.setKey(KeyHelper.replaceTokenObjectKey(iRelSerie.getKey(),2, iRIdNumeratoreSerie));
		iRelSerie.setKey(KeyHelper.replaceTokenObjectKey(iRelSerie.getKey(),1, Azienda.getAziendaCorrente()));
		iRelNumeratore.setKey(KeyHelper.replaceTokenObjectKey(iRelNumeratore.getKey(),2, iRIdNumeratoreSerie));
		iRelNumeratore.setKey(KeyHelper.replaceTokenObjectKey(iRelNumeratore.getKey(),1, Azienda.getAziendaCorrente()));
	}

	public void setNumeratoreKey(String key) {
		iRelNumeratore.setKey(key);
	}

	public String getNumeratoreKey() {
		return iRelNumeratore.getKey();
	}

	public void setNumeratore(Numeratore numRcvFsc) {
		iRelNumeratore.setObject(numRcvFsc);
	}

	public Numeratore getNumeratore() {
		return (Numeratore)iRelNumeratore.getObject();
	}

	public void setRSerie(String iIdSerie) {
		iRelSerie.setKey(KeyHelper.replaceTokenObjectKey(iRelSerie.getKey(),3, iIdSerie));
	}

	public Date getDataDocumento() {
		return iDataDocumento;
	}

	public void setDataDocumento(Date iDataDocumento) {
		this.iDataDocumento = iDataDocumento;
	}

	public String getRCauDocTes() {
		return iRCauDocTes;
	}

	public void setRCauDocTes(String iRCauDocTes) {
		this.iRCauDocTes = iRCauDocTes;
	}

	public String getRCliente() {
		return iRCliente;
	}
	public void setRCliente(String iRCliente) {
		this.iRCliente = iRCliente;
	}

	public CausaleDocumentoVendita getCausale() {
		return (CausaleDocumentoVendita)iRelRCauDocTes.getObject();
	}

	public void setCausale(CausaleDocumentoVendita iCausale) {
		this.iRelRCauDocTes.setObject(iCausale);
	}

	public void setSerie(Serie serie) {
		this.iRelSerie.setObject(serie);
		setOnDB(false);
	}

	public Serie getSerie() {
		return (Serie) iRelSerie.getObject();
	}

	public void setSerieKey(String key) {
		iRelSerie.setKey(key);
		setOnDB(false);
	}

	public String getSerieKey() {
		return iRelSerie.getKey();
	}

	public void setRelcliente(ClienteVendita relcliente) {
		String idAzienda = getIdAzienda();
		if (relcliente != null) {
			idAzienda = KeyHelper.getTokenObjectKey(relcliente.getKey(), 1);
		}
		setIdAzienda(idAzienda);
		this.iRelCliente.setObject(relcliente);
	}

	public ClienteVendita getRelcliente() {
		return (ClienteVendita)iRelCliente.getObject();
	}

	public void setRelclienteKey(String key) {
		iRelCliente.setKey(key);
		String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setIdAzienda(idAzienda);
	}

	public String getRelclienteKey() {
		return iRelCliente.getKey();
	}

	@Override
	public String getKey() {
		return null;
	}

	@Override
	public void setKey(String key) {
	}

	@Override
	public void setEqual(Copyable obj) throws CopyException {
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Vector checkAll(BaseComponentsCollection components) {
		java.util.Vector errors = new java.util.Vector();
		components.runAllChecks(errors);
		ErrorMessage em = checkSerieCausale();
		if(em != null)
			errors.add(em);
		return errors;
	}

	protected ErrorMessage checkSerieCausale() {
		ErrorMessage em = null;
		try {
			CausaleDocumentoVendita cau = (CausaleDocumentoVendita)
					CausaleDocumentoVendita.elementWithKey(CausaleDocumentoVendita.class,
							KeyHelper.buildObjectKey(new String[] {Azienda.getAziendaCorrente(),this.getRCauDocTes()}), 0);
			if(cau != null) {
				if(cau.getTipiGestione().getTPGestioneRiferimentoCli().getTipoGestione() == '1'
						&& (this.getDataRifIntestatario() == null || this.getNumeroRifIntestatario() == null)){
					em = new ErrorMessage("YSOFTRE003","Data e numero riferimento sono obbligatori");
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return em;
	}

	@Override
	public int save() throws SQLException {
		return 0;
	}

	@Override
	public int save(boolean force) throws SQLException {
		return 0;
	}

	@Override
	public boolean retrieve(int lockType) throws SQLException {
		return false;
	}

	@Override
	public void setOnDB(boolean onDB) {
	}

	@Override
	public void unlock() throws SQLException {
	}

	@Override
	public void setObjQueryTimeout(int seconds) {
	}

	/**
	 * DSSOF3	Generazione documento di vendita:
	 * 
	 * 			1.Creazione di un documento di vendita contenente tutte le righe di tutte le testate UDS selezionate.
	 * 			2.Le righe UDS vengono ordinate tramite un Comparator in base all'IdArticolo, cosi da matchare le righe che provengono dagli stessi ordini vendita.
	 * 			3.Se vi sono righe che provengono dallo stesso ordine vendita vado ad accorparle, ovvero sommo la qtaPrm() dell'UDS e la aggiungo alle qta della riga vendita.
	 * 			4.In seguito viene lanciato il metodo ricalcoloQta(), questo metodo sistema le qta in base all'UM.
	 * 			5.Aggiorno i riferimenti al Documento di Vendita sia in riga UDS che in testata.
	 *  		6.Commit solo se tutto � andato a buon fine, o tutto o niente.
	 * 			
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object generaDocumentoVendita(String[] chiaviSel, Date data, String idCausale, String idSerie, String idCliente, Date dataRifClienteDocVe, String numeroRifCliente) {
		ErrorList errori = new ErrorList();
		String chiave = "";
		Object ogg = new Object[] {errori,chiave};
		boolean commit = false;
		try {
			DocumentoVenditaDataCollector docBODC = (DocumentoVenditaDataCollector) Factory.createObject(DocumentoVenditaDataCollector.class);
			docBODC.setAutoCommit(false);
			docBODC.initialize(Factory.getName("DocumentoVendita", Factory.CLASS_HDR), true, PersistentObject.OPTIMISTIC_LOCK);
			DocumentoVendita documentoVendita = creaDocumentoVenditaTestata(data, idCausale, idSerie, idCliente, dataRifClienteDocVe, numeroRifCliente);
			docBODC.setBo(documentoVendita);
			docBODC.loadAttValue();
			docBODC.setAutoCommit(false);
			int rc = docBODC.save();
			if(rc == DocumentoVenditaDataCollector.OK) {
				commit = true;
			}else {
				errori.getErrors().addAll(docBODC.getErrorList().getErrors());
			}
			documentoVendita.retrieve();
			chiave = documentoVendita.getKey();
			for(int i = 0; i < chiaviSel.length ; i++ ) {
				YUdsVendita udsVendita = (YUdsVendita) YUdsVendita.elementWithKey(YUdsVendita.class, chiaviSel[i], 0);
				List listaRigheOrdinata = udsVendita.getRigheUDSVendita();
				if(listaRigheOrdinata.isEmpty()) {
					listaRigheOrdinata = udsVendita.getRigheUDSVenditaDaFigli();
				}
				Collections.sort(listaRigheOrdinata, new YOrdinamentoUdsVenRigComparator().orderByIdArticolo);
				Iterator iteratorRigheUdsVendita = listaRigheOrdinata.iterator();
				YUdsVenRig rigaUds = null;
				YUdsVenRig rigaUdsPrecedente = null;
				DocumentoVenRigaPrm rigaDocumentoVE = null;
				DocumentoVenRigaPrm rigaDocumentoVEPrecedente = null;
				while(iteratorRigheUdsVendita.hasNext()) {
					rigaUds = (YUdsVenRig) iteratorRigheUdsVendita.next();
					if(isAccorpamento(rigaUds,rigaUdsPrecedente)) {
						accorpaQtaDocumentoVenditaRigaPrm(rigaDocumentoVEPrecedente, rigaUds);
					}else {
						rigaDocumentoVE = creaDocumentoVenditaRigaPrm(documentoVendita, rigaUds);
						aggiornaAttributiDaRigaOrdine(rigaDocumentoVE, rigaUds);
						aggiornaRiferimentiDocumentoVenditaRigaUds(rigaUds, rigaDocumentoVE);
					}
					ricalcolaQta(rigaDocumentoVE);
					if(rigaDocumentoVE.save() < 0) {
						commit = false;
					}
					rigaUds.rendiDefinitivaRigaUdsVendita();
					if(rigaUds.save() < 0) {
						commit = false;
					}
					rigaUdsPrecedente = rigaUds;
					rigaDocumentoVEPrecedente = rigaDocumentoVE;
				}
				aggiornaRiferimentiDocumentoVenditaTestataUds(udsVendita, documentoVendita);
				udsVendita.rendiDefinitivaUdsVendita();
				if(udsVendita.save() < 0) {
					commit = false;
				}
			}
			if(commit) {
				ConnectionManager.commit();
			}else {
				ConnectionManager.rollback();
			}
		}catch(Exception e) {
			e.printStackTrace(Trace.excStream);
		}
		ogg = new Object[]{errori,chiave};
		return ogg;
	}

	/**
	 * DSSOF3	Metodo per accorpare le righe uds in una riga vendita.
	 * 			Se ho trovato una riga UDS con gli stessi riferimenti ordine vendita della precedente,
	 * 			accorpo le qt�.
	 * @param docVenRig
	 * @param udsCorrente
	 */
	public static void accorpaQtaDocumentoVenditaRigaPrm(DocumentoVenRigaPrm docVenRig, YUdsVenRig udsCorrente) {
		if(docVenRig.getQtaInUMVen() != null && udsCorrente.getQtaPrm() != null)
			docVenRig.setQtaInUMVen(docVenRig.getQtaInUMVen().add(udsCorrente.getQtaPrm()));

		if(docVenRig.getQtaInUMPrm() != null && udsCorrente.getQtaPrm() != null)
			docVenRig.setQtaInUMPrm(docVenRig.getQtaInUMPrm().add(udsCorrente.getQtaPrm()));
	}

	/**
	 * DSSOF3	Metodo per verificare se gli ordini di vendita di due righe UDS sono uguali.
	 * @return true se sono UGUALI
	 * @return false se sono DIVERSI
	 * @throws SQLException
	 */
	public static boolean isAccorpamento(YUdsVenRig uds1, YUdsVenRig uds2) throws SQLException {
		boolean isDaRaggruppare = false;
		if(uds2 == null) {
			return false;
		}
		if(uds1.getOrdineVenditaObj() == null || uds2.getOrdineVenditaObj() == null) {
			isDaRaggruppare =  false;
		}else {
			if(uds1.getOrdineVenditaObj().equals(uds2.getOrdineVenditaObj())) {
				isDaRaggruppare = true;
			}
		}
		return isDaRaggruppare;
	}

	/**
	 * DSSOF3	Metodo per la creazione della testata Documento di Vendita.
	 * @param data
	 * @param idCausale
	 * @param idSerie
	 * @param idCliente
	 * @param dataRifIntestatario
	 * @param numeroRifCliente
	 * @return
	 */
	public static DocumentoVendita creaDocumentoVenditaTestata(Date data, String idCausale, String idSerie, String idCliente, Date dataRifIntestatario, String numeroRifCliente) {
		DocumentoVendita docVenTes = (DocumentoVendita)Factory.createObject(DocumentoVendita.class);
		docVenTes.setIdAzienda(Azienda.getAziendaCorrente());
		docVenTes.setIdCau(idCausale);
		docVenTes.getNumeratoreHandler().setIdSerie(idSerie);
		docVenTes.getNumeratoreHandler().setDataDocumento(data);
		docVenTes.setIdCliente(idCliente);
		docVenTes.setDataRifIntestatario(dataRifIntestatario);
		docVenTes.setNumeroRifIntestatario(numeroRifCliente);
		docVenTes.completaBO();
		return docVenTes;

	}

	/**
	 * DSSOF3	Metodo per la creazione di una riga Documento di Vendita.
	 * @param docVenTes
	 * @param udsVeRig
	 * @return
	 */
	public static DocumentoVenRigaPrm creaDocumentoVenditaRigaPrm(DocumentoVendita docVenTes,YUdsVenRig udsVeRig) {
		DocumentoVenRigaPrm docVenRig = (DocumentoVenRigaPrm)Factory.createObject(DocumentoVenRigaPrm.class);
		docVenRig.setIdAzienda(Azienda.getAziendaCorrente());
		docVenRig.setTestata(docVenTes);
		docVenRig.setIdCauRig(docVenTes.getCausale().getCausaleRigaDocumVenKey());
		docVenRig.completaBO();
		docVenRig.setIdArticolo(udsVeRig.getRArticolo());
		docVenRig.cambiaArticolo(docVenRig.getArticolo(), docVenRig.getConfigurazione(), false);
		UnitaMisura um = docVenRig.getArticolo().getUMDefaultVendita();
		docVenRig.setUMRif(um);
		docVenRig.setQtaInUMVen(udsVeRig.getQtaPrm());
		docVenRig.setQtaInUMPrm(udsVeRig.getQtaPrm());
		docVenRig.setIdEsternoConfig(udsVeRig.getRConfig());
		docVenRig.setIdVersioneRcs(udsVeRig.getRVersione());
		//ricalcolaQta(docVenRig);
		return docVenRig;
	}

	/**
	 * DSSOF3	Metodo per agggiornare gli attributi tramite la riga ordine presente nei riferimenti dell'uds.
	 * @param docVenRig
	 * @param udsVenRig
	 */
	public static void aggiornaAttributiDaRigaOrdine(DocumentoVenRigaPrm docVenRig, YUdsVenRig udsVenRig) {
		try {
			OrdineVenditaRigaPrm rigaOrdine = udsVenRig.getOrdineVenditaRigaObj();
			if (rigaOrdine != null) {
				docVenRig.setRigaOrdine(rigaOrdine);
				docVenRig.setRRigaOrd(rigaOrdine.getNumeroRigaDocumento());
				docVenRig.setSpecializzazioneRiga(rigaOrdine.getSpecializzazioneRiga());
				docVenRig.setSequenzaRiga(rigaOrdine.getSequenzaRiga());
				docVenRig.setTipoRiga(rigaOrdine.getTipoRiga());
				if (rigaOrdine.getTipoRiga() == TipoRiga.OMAGGIO) {
					docVenRig.setServizioCalcDatiVendita(false);
				}
				docVenRig.setMagazzino(rigaOrdine.getMagazzino());
				docVenRig.setArticolo(rigaOrdine.getArticolo());
				docVenRig.setArticoloVersSaldi(rigaOrdine.getArticoloVersSaldi());
				docVenRig.setArticoloVersRichiesta(rigaOrdine.getArticoloVersRichiesta());
				docVenRig.setConfigurazione(rigaOrdine.getConfigurazione());
				docVenRig.setDescrizioneArticolo(rigaOrdine.getDescrizioneArticolo());
				docVenRig.setDescrizioneExtArticolo(rigaOrdine.getDescrizioneExtArticolo());
				docVenRig.setNota(rigaOrdine.getNota());
				String nota = docVenRig.getNota();
				if (nota != null && !nota.equals("")) {
					if (rigaOrdine.getNota() != null && !rigaOrdine.getNota().equals("")) {
						nota = rigaOrdine.getNota()  + " " + nota;
					}
				} else {
					nota = rigaOrdine.getNota();
				}
				if (nota != null && nota.length() > 250) {
					nota = nota.substring(0, 250);
				}
				docVenRig.setNota(nota);
				docVenRig.setDocumentoMM(rigaOrdine.getDocumentoMM());
				docVenRig.setSpesa(rigaOrdine.getSpesa());
				docVenRig.setImportoPercentualeSpesa(rigaOrdine.getImportoPercentualeSpesa());
				docVenRig.setSpesaPercentuale(rigaOrdine.isSpesaPercentuale());
				docVenRig.setUMRifKey(rigaOrdine.getUMRifKey());
				docVenRig.setUMPrmKey(rigaOrdine.getUMPrmKey());
				docVenRig.setUMSecKey(rigaOrdine.getUMSecKey());
				docVenRig.setRicalcoloQtaFattoreConv(rigaOrdine.isRicalcoloQtaFattoreConv());
				docVenRig.setCoefficienteImpiego(rigaOrdine.getCoefficienteImpiego());
				docVenRig.setBloccoRicalcoloQtaComp(rigaOrdine.isBloccoRicalcoloQtaComp());
				docVenRig.setTipoCostoRiferimento(rigaOrdine.getTipoCostoRiferimento());
				docVenRig.setDataConsegnaRichiesta(rigaOrdine.getDataConsegnaRichiesta());
				docVenRig.setDataConsegnaConfermata(rigaOrdine.getDataConsegnaConfermata());
				docVenRig.setSettConsegnaRichiesta(rigaOrdine.getSettConsegnaRichiesta());
				docVenRig.setSettConsegnaConfermata(rigaOrdine.getSettConsegnaConfermata());
				docVenRig.setStatoConfermaATP(rigaOrdine.getStatoConfermaATP());
				docVenRig.setDataPrevistaATP(rigaOrdine.getDataPrevistaATP());
				docVenRig.setDataTollerataATP(rigaOrdine.getDataTollerataATP());
				docVenRig.setIdListino(rigaOrdine.getIdListino());
				BigDecimal bd = GestoreEvasioneVendita.getBigDecimalZero();
				bd = rigaOrdine.getPrezzo() == null ? GestoreEvasioneVendita.getBigDecimalZero() : rigaOrdine.getPrezzo();
				docVenRig.setPrezzo(bd);
				bd = rigaOrdine.getPrezzoExtra() == null ? GestoreEvasioneVendita.getBigDecimalZero() : rigaOrdine.getPrezzoExtra();
				docVenRig.setPrezzoExtra(bd);
				bd = rigaOrdine.getPrezzoListino() == null ? GestoreEvasioneVendita.getBigDecimalZero() : rigaOrdine.getPrezzoListino();
				docVenRig.setPrezzoListino(bd);
				bd = rigaOrdine.getPrezzoExtraListino() == null ? GestoreEvasioneVendita.getBigDecimalZero() : rigaOrdine.getPrezzoExtraListino();
				docVenRig.setPrezzoExtraListino(bd);
				docVenRig.setRiferimentoUMPrezzo(rigaOrdine.getRiferimentoUMPrezzo());
				docVenRig.setTipoPrezzo(rigaOrdine.getTipoPrezzo());
				docVenRig.setBloccoRclPrzScnFatt(rigaOrdine.isBloccoRclPrzScnFatt());
				docVenRig.setProvenienzaPrezzo(rigaOrdine.getProvenienzaPrezzo());
				docVenRig.setTipoRigaListino(rigaOrdine.getTipoRigaListino());
				docVenRig.setAssoggettamentoIVA(rigaOrdine.getAssoggettamentoIVA());
				docVenRig.setResponsabileVendite(rigaOrdine.getResponsabileVendite());
				docVenRig.setScontoArticolo1(rigaOrdine.getScontoArticolo1());
				docVenRig.setScontoArticolo2(rigaOrdine.getScontoArticolo2());
				docVenRig.setMaggiorazione(rigaOrdine.getMaggiorazione());
				docVenRig.setSconto(rigaOrdine.getSconto());
				docVenRig.setPrcScontoIntestatario(rigaOrdine.getPrcScontoIntestatario());
				docVenRig.setPrcScontoModalita(rigaOrdine.getPrcScontoModalita());
				docVenRig.setScontoModalita(rigaOrdine.getScontoModalita());
				docVenRig.setAgente(rigaOrdine.getAgente());
				docVenRig.setProvvigione1Agente(rigaOrdine.getProvvigione1Agente());
				docVenRig.setProvvigione2Agente(rigaOrdine.getProvvigione2Agente());
				docVenRig.setSubagente(rigaOrdine.getSubagente());
				docVenRig.setProvvigione1Subagente(rigaOrdine.getProvvigione1Subagente());
				docVenRig.setProvvigione2Subagente(rigaOrdine.getProvvigione2Subagente());
				docVenRig.setDifferenzaPrezzoAgente(rigaOrdine.hasDifferenzaPrezzoAgente());
				docVenRig.setDifferenzaPrezzoSubagente(rigaOrdine.hasDifferenzaPrezzoSubagente());
				docVenRig.setCommessa(rigaOrdine.getCommessa());
				docVenRig.setCentroCosto(rigaOrdine.getCentroCosto());
				if (rigaOrdine.getDatiCA() != null){
					docVenRig.getDatiCA().setVoceSpesaCA(rigaOrdine.getDatiCA().getVoceSpesaCA());
					docVenRig.getDatiCA().setVoceCA4(rigaOrdine.getDatiCA().getVoceCA4());
					docVenRig.getDatiCA().setVoceCA5(rigaOrdine.getDatiCA().getVoceCA5());
					docVenRig.getDatiCA().setVoceCA6(rigaOrdine.getDatiCA().getVoceCA6());
					docVenRig.getDatiCA().setVoceCA7(rigaOrdine.getDatiCA().getVoceCA7());
					docVenRig.getDatiCA().setVoceCA8(rigaOrdine.getDatiCA().getVoceCA8());
				}
				docVenRig.setGruppoContiAnalitica(rigaOrdine.getGruppoContiAnalitica());
				docVenRig.setFornitore(rigaOrdine.getFornitore());
				docVenRig.setStatoAccantonamentoPrenot(rigaOrdine.getStatoAccantonamentoPrenot());
				docVenRig.setPriorita(rigaOrdine.getPriorita());
				docVenRig.setStatoInvioEDI(rigaOrdine.getStatoInvioEDI());
				docVenRig.setStatoInvioDatawarehouse(rigaOrdine.getStatoInvioDatawarehouse());
				docVenRig.setStatoInvioLogistica(rigaOrdine.getStatoInvioLogistica());
				docVenRig.setStatoInvioContAnalitica(rigaOrdine.getStatoInvioContAnalitica());
				docVenRig.setNonFatturare(rigaOrdine.isNonFatturare());
				docVenRig.setFlagRiservatoUtente1(rigaOrdine.getFlagRiservatoUtente1());
				docVenRig.setFlagRiservatoUtente2(rigaOrdine.getFlagRiservatoUtente2());
				docVenRig.setFlagRiservatoUtente3(rigaOrdine.getFlagRiservatoUtente3());
				docVenRig.setFlagRiservatoUtente4(rigaOrdine.getFlagRiservatoUtente4());
				docVenRig.setFlagRiservatoUtente5(rigaOrdine.getFlagRiservatoUtente5());
				docVenRig.setAlfanumRiservatoUtente1(rigaOrdine.getAlfanumRiservatoUtente1());
				docVenRig.setAlfanumRiservatoUtente2(rigaOrdine.getAlfanumRiservatoUtente2());
				docVenRig.setNumeroRiservatoUtente1(rigaOrdine.getNumeroRiservatoUtente1());
				docVenRig.setNumeroRiservatoUtente2(rigaOrdine.getNumeroRiservatoUtente2());
				docVenRig.setCostoUnitario(rigaOrdine.getCostoUnitario());
				PersDatiVen pdv = PersDatiVen.getCurrentPersDatiVen();
				if (pdv.getTipoCostoRiferimento() == TipoCostoRiferimento.COSTO_ULTIMO){
					GestoreCalcoloCosti gesCalcoloCosti = (GestoreCalcoloCosti)Factory.createObject(GestoreCalcoloCosti.class);
					gesCalcoloCosti.initialize(rigaOrdine.getIdAzienda(), rigaOrdine.getIdArticolo(), rigaOrdine.getIdVersioneSal(),
							rigaOrdine.getIdConfigurazione(), rigaOrdine.getIdMagazzino());
					gesCalcoloCosti.impostaCostoUnitario();
					docVenRig.setCostoUnitario(gesCalcoloCosti.getCostoUnitario());
				}
				if (rigaOrdine.getRigaPrezziExtra() != null) {
					docVenRig.istanziaRigaPrezziExtra();
					if (docVenRig.getRigaPrezziExtra() != null) {
						DocRigaPrezziExtraVendita rigaPrezzi = (DocRigaPrezziExtraVendita)docVenRig.getRigaPrezziExtra();
						DocOrdRigaPrezziExtra rigaPrezziOrd = rigaOrdine.getRigaPrezziExtra();
						rigaPrezzi.setAnnoContrattoMateriaPrima(rigaPrezziOrd.getAnnoContrattoMateriaPrima());
						rigaPrezzi.setIdAzienda(rigaPrezziOrd.getIdAzienda());
						rigaPrezzi.setIdRigaCondizione(rigaPrezziOrd.getIdRigaCondizione());
						rigaPrezzi.setIdSchemaPrezzo(rigaPrezziOrd.getIdSchemaPrezzo());
						rigaPrezzi.setNumContrattoMateriaPrima(rigaPrezziOrd.getNumContrattoMateriaPrima());
						rigaPrezzi.setRAnnoCantiere(((OrdineRigaPrezziExtraVendita)rigaPrezziOrd).getRAnnoCantiere());
						rigaPrezzi.setRNumeroCantiere(((OrdineRigaPrezziExtraVendita)rigaPrezziOrd).getRNumeroCantiere());
						rigaPrezzi.setRRigaCantiere(((OrdineRigaPrezziExtraVendita)rigaPrezziOrd).getRRigaCantiere());
						rigaPrezzi.setPrezzoRiferimento(((OrdineRigaPrezziExtraVendita)rigaPrezziOrd).getPrezzoRiferimento());
						try {
							rigaPrezzi.getPrezziExtra().setEqual(rigaPrezziOrd.getPrezziExtra());
						}
						catch (Exception ex) {
							ex.printStackTrace();
						}
						rigaPrezzi.setRigaOrdine(rigaOrdine);
						rigaPrezzi.setIdDetRigaOrdine(rigaOrdine.getDettaglioRigaDocumento());
					}
				}
				if (docVenRig.getRigaOrdine() != null) {
					OrdineVenditaTestata ordVen = (OrdineVenditaTestata)docVenRig.getRigaOrdine().getTestata();
					if (ordVen.getTipoEvasioneOrdine() == OrdineTestata.SALDO_AUTOMATICO) {
						docVenRig.setRigaSaldata(true);
					}
				}
				docVenRig.setRifCommessaCli(rigaOrdine.getRifCommessaCli());
				docVenRig.setDifferenzaPrezzoAgente(rigaOrdine.hasDifferenzaPrezzoAgente());
				docVenRig.setDifferenzaPrezzoSubagente(rigaOrdine.hasDifferenzaPrezzoSubagente());
				docVenRig.setPrezzoNetto(rigaOrdine.getPrezzoNetto());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * DSSOF3	Metodo per ricalcolare la qta in UMVen e UMRif in base alle unit� di misura, copiato lo STD.
	 * @param riga
	 */
	public static void ricalcolaQta(DocumentoVenRigaPrm riga) {
		try {
			UnitaMisura umRif = getUnitaMisura(riga.getIdUMRif());
			UnitaMisura umPrm = getUnitaMisura(riga.getIdUMPrm());
			UnitaMisura umSec = getUnitaMisura(riga.getIdUMSec());
			UnitaMisura umOrigine = UnitaMisura.getUM(riga.getIdUMRif());
			Articolo articolo = (Articolo) riga.getArticolo();
			BigDecimal quant = riga.getQtaInUMPrm() != null ? riga.getQtaInUMPrm() : new BigDecimal(0);
			String idVersione = riga.getIdVersioneSal() != null ? riga.getIdVersioneSal().toString() : "";
			ArticoloVersione articoloVersione = getArticoloVersione(Azienda.getAziendaCorrente() ,articolo.getIdArticolo(), idVersione); 
			String dominio = "V";
			String siglaUMOrigin = "R";
			boolean rRicalcoloQta = true;
			String rIdUMDestinazione = riga.getIdUMPrm();
			String rSiglaUMDestinazione = "P";
			String rFlagRigaLotto = "R";
			String selectedRow = null;
			CalcoloQuantitaWeb cqw =  CalcoloQuantitaWrapper.get().calcolaQuantita(
					articolo, 
					articoloVersione, 
					quant, 
					siglaUMOrigin, 
					dominio, 
					umOrigine, umRif, umPrm, umSec, 
					rRicalcoloQta, 
					rIdUMDestinazione, rSiglaUMDestinazione, rFlagRigaLotto, 
					selectedRow);
			String qtaPrmMagStr = cqw.getQuantRigaUMPrmMag().replace(",", ".");
			BigDecimal qtaPrmMag = new BigDecimal(qtaPrmMagStr);
			riga.getQtaAttesaEvasione().setQuantitaInUMRif(qtaPrmMag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * DSSOF3	Metodo copiato dallo standard per prendere la versione dell'articolo.
	 * @param idAzienda
	 * @param idArticolo
	 * @param idVersione
	 * @return
	 */
	public static ArticoloVersione getArticoloVersione(String idAzienda, String idArticolo, String idVersione) {
		ArticoloVersione av = null;
		String azienda = Azienda.getAziendaCorrente();
		if (idAzienda != null) {
			azienda = idAzienda;
		}
		String key = KeyHelper.buildObjectKey(new String[] {azienda, idArticolo, idVersione});
		try {
			av = ArticoloVersione.elementWithKey(key, PersistentObject.NO_LOCK);
		}
		catch(Exception ex) {
			ex.printStackTrace(Trace.excStream);
		}
		return av;
	}

	/**
	 * DSSOF3	Metodo copiato dallo standard per prendere l'unit� di misura.
	 * @param idUM
	 * @return
	 */
	public static UnitaMisura getUnitaMisura(String idUM) {
		UnitaMisura um = null;
		if(idUM == null || idUM.equals(""))
			return um;
		try {
			um = UnitaMisura.getUM(idUM);
		}
		catch(Exception ex) {
			ex.printStackTrace(Trace.excStream);
		}
		return um;
	}

	/**
	 * DSSOF3	Metodo per aggiornare i riferimenti al Documento di Vendita sulla riga uds.
	 * @param udsVeRig
	 * @param docVeRig
	 */
	public static void aggiornaRiferimentiDocumentoVenditaRigaUds(YUdsVenRig udsVeRig, DocumentoVenRigaPrm docVeRig) {
		udsVeRig.setRAnnoDocVen(docVeRig.getTestata().getAnnoDocumento());
		udsVeRig.setRNumDocVen(docVeRig.getTestata().getNumeroDocumento());
		udsVeRig.setRRigaDocVen(docVeRig.getSequenzaRiga());
		if(docVeRig.getDettaglioRigaDocumento() == null) {
			udsVeRig.setRRigaDetDocVen(docVeRig.getDettaglioRigaDocumento());
		}
	}

	/**
	 * DSSOF3	Metodo per aggiornare i riferimenti al Documento di Vendita sulla testata uds.
	 * @param udsVeTes
	 * @param docVenTes
	 */
	public static void aggiornaRiferimentiDocumentoVenditaTestataUds(YUdsVendita udsVeTes, DocumentoVendita docVenTes) {
		udsVeTes.setRAnnoDocVen(docVenTes.getAnnoDocumento());
		udsVeTes.setRNumDocVen(docVenTes.getNumeroDocumento());
	}

	/**
	 * DSSOF3	Metodo per formattare la data da stringa java.sql.Date.
	 * @param data
	 * @return
	 */
	public static Date getDataFormattata(String data) {
		java.sql.Date date = null;
		try {
			SimpleDateFormat formatoWeb = new SimpleDateFormat("dd/MM/yyyy");
			//SimpleDateFormat formatoSql = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat formatoDB2 = new SimpleDateFormat("yyyy-MM-dd");
			String dataOk = "";
			if(ConnectionManager.getCurrentDatabase() instanceof SQLServerJTDSNoUnicodeDatabase) 
				dataOk = formatoDB2.format(formatoWeb.parse(data));
			else if(ConnectionManager.getCurrentDatabase() instanceof DB2NetDatabase) 
				dataOk = formatoDB2.format(formatoWeb.parse(data));
			date = java.sql.Date.valueOf(dataOk);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

}
