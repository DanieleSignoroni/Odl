package it.odl.thip.base.uds.importazione;

import com.thera.thermfw.persist.*;
import java.sql.*;
import java.util.*;
import it.thera.thip.base.articolo.Articolo;
import java.math.*;
import com.thera.thermfw.common.*;
import it.thera.thip.base.azienda.Azienda;

import com.thera.thermfw.security.*;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 11/03/2024
 * <br><br>
 * <b>71469	DSSOF3	11/03/2024</b>    
 * <p>Tabella di frontiera popolata dal MES.</p>
 */

public abstract class YUdsMesPO extends PersistentObject implements BusinessObject, Authorizable, Deletable, Conflictable {

	private static YUdsMes cInstance;

	protected String iIdUds;

	protected BigDecimal iQtaPrm;

	protected String iRAnnoOrdPrd;

	protected String iRNumeroOrdPrd;

	protected String iRCodimballo;
	
	protected char iSemaforo;
	
	protected Timestamp iTimestampAgg;
	
	protected Timestamp iTimestampCrz;

	protected Proxy iArticolo = new Proxy(it.thera.thip.base.articolo.Articolo.class);

	@SuppressWarnings("rawtypes")
	public static Vector retrieveList(String where, String orderBy, boolean optimistic) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		if (cInstance == null)
			cInstance = (YUdsMes)Factory.createObject(YUdsMes.class);
		return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
	}

	public static YUdsMes elementWithKey(String key, int lockType) throws SQLException {
		return (YUdsMes)PersistentObject.elementWithKey(YUdsMes.class, key, lockType);
	}

	public YUdsMesPO() {
		setRAzienda(Azienda.getAziendaCorrente());
	}

	public void setIdUds(String idUds) {
		this.iIdUds = idUds;
		setDirty();
		setOnDB(false);
	}

	public String getIdUds() {
		return iIdUds;
	}

	public void setQtaPrm(BigDecimal qtaPrm) {
		this.iQtaPrm = qtaPrm;
		setDirty();
	}

	public BigDecimal getQtaPrm() {
		return iQtaPrm;
	}

	public void setRAnnoOrdPrd(String rAnnoOrdPrd) {
		this.iRAnnoOrdPrd = rAnnoOrdPrd;
		setDirty();
	}

	public String getRAnnoOrdPrd() {
		return iRAnnoOrdPrd;
	}

	public void setRNumeroOrdPrd(String rNumeroOrdPrd) {
		this.iRNumeroOrdPrd = rNumeroOrdPrd;
		setDirty();
	}

	public String getRNumeroOrdPrd() {
		return iRNumeroOrdPrd;
	}

	public void setRCodimballo(String rCodimballo) {
		this.iRCodimballo = rCodimballo;
		setDirty();
	}

	public String getRCodimballo() {
		return iRCodimballo;
	}
	
	public char getSemaforo() {
		return iSemaforo;
	}
	
	public Timestamp getTimestampAgg() {
		return iTimestampAgg;
	}

	public void setTimestampAgg(Timestamp iTimestampAgg) {
		this.iTimestampAgg = iTimestampAgg;
	}

	public Timestamp getTimestampCrz() {
		return iTimestampCrz;
	}

	public void setTimestampCrz(Timestamp iTimestampCrz) {
		this.iTimestampCrz = iTimestampCrz;
	}

	public void setSemaforo(char iSemaforo) {
		this.iSemaforo = iSemaforo;
	}

	public void setArticolo(Articolo articolo) {
		String oldObjectKey = getKey();
		this.iArticolo.setObject(articolo);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public Articolo getArticolo() {
		return (Articolo)iArticolo.getObject();
	}

	public void setArticoloKey(String key) {
		String oldObjectKey = getKey();
		iArticolo.setKey(key);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public String getArticoloKey() {
		return iArticolo.getKey();
	}

	public void setRAzienda(String rAzienda) {
		String key = iArticolo.getKey();
		iArticolo.setKey(KeyHelper.replaceTokenObjectKey(key , 1, rAzienda));
		setDirty();
		setOnDB(false);
	}

	public String getRAzienda() {
		String key = iArticolo.getKey();
		String objRAzienda = KeyHelper.getTokenObjectKey(key,1);
		return objRAzienda;

	}

	public void setRArticolo(String rArticolo) {
		String key = iArticolo.getKey();
		iArticolo.setKey(KeyHelper.replaceTokenObjectKey(key , 2, rArticolo));
		setDirty();
	}

	public String getRArticolo() {
		String key = iArticolo.getKey();
		String objRArticolo = KeyHelper.getTokenObjectKey(key,2);
		return objRArticolo;
	}

	public void setEqual(Copyable obj) throws CopyException {
		super.setEqual(obj);
		YUdsMesPO yUdsMesPO = (YUdsMesPO)obj;
		iArticolo.setEqual(yUdsMesPO.iArticolo);
	}

	@SuppressWarnings("rawtypes")
	public Vector checkAll(BaseComponentsCollection components) {
		Vector errors = new Vector();
		components.runAllChecks(errors);
		return errors;
	}

	public void setKey(String key) {
		setRAzienda(KeyHelper.getTokenObjectKey(key, 1));
		setIdUds(KeyHelper.getTokenObjectKey(key, 2));
	}

	public String getKey() {
		String rAzienda = getRAzienda();
		String idUds = getIdUds();
		Object[] keyParts = {rAzienda, idUds};
		return KeyHelper.buildObjectKey(keyParts);
	}

	public boolean isDeletable() {
		return checkDelete() == null;
	}

	public String toString() {
		return getClass().getName() + " [" + KeyHelper.formatKeyString(getKey()) + "]";
	}

	protected TableManager getTableManager() throws SQLException {
		return YUdsMesTM.getInstance();
	}

}

