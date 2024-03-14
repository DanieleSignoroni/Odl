package it.softre.thip.vendite.uds;

import java.math.BigDecimal;

import com.thera.thermfw.common.BusinessObjectAdapter;
import com.thera.thermfw.common.ErrorMessage;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.Proxy;

import it.softre.thip.base.uds.YTipoUds;
import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.produzione.ordese.OrdineEsecutivo;

/**
 * 
 * @author DSSOF3	
 *	DSSOF3	70687	04/10/2022	Prima stesura.	
 *								Classe per la form YUdsDaProduzione.
 */

public class YUdsDaProduzione extends BusinessObjectAdapter {

	protected String iIdAzienda;

	protected String iRAnnoOrd;

	protected String iRNumeroOrd;

	protected String iQuantita;

	protected String iQuantitaConfezione;
	
	protected String iRTipoUds;
	
	protected Proxy iRelTipoUds = new Proxy(it.softre.thip.base.uds.YTipoUds.class);

	protected Proxy iRelOrdineEsecutivo = new Proxy(it.thera.thip.produzione.ordese.OrdineEsecutivo.class);
	
	public YUdsDaProduzione() {
		setIdAzienda(Azienda.getAziendaCorrente());
	}
	
	public void setIdAziendaInternal(String idAzienda) {
		iRelTipoUds.setKey(KeyHelper.replaceTokenObjectKey(iRelTipoUds.getKey(), 1, idAzienda));
		iRelOrdineEsecutivo.setKey(KeyHelper.replaceTokenObjectKey(iRelOrdineEsecutivo.getKey(), 1, idAzienda));
	}
	
	public void setRelordprd(OrdineEsecutivo iRelOrdineEsecutivo) {
		String oldObjectKey = getKey();
		if (iRelOrdineEsecutivo != null) {
		}
		this.iRelOrdineEsecutivo.setObject(iRelOrdineEsecutivo);
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public String getIdAzienda() {
		return iIdAzienda;
	}

	public void setIdAzienda(String iIdAzienda) {
		this.iIdAzienda = iIdAzienda;
		setIdAziendaInternal(iIdAzienda);
	}

	public String getRAnnoOrd() {
		return iRAnnoOrd;
	}

	public void setRAnnoOrd(String iRAnnoOrd) {
		 String key = iRelOrdineEsecutivo.getKey();
		 iRelOrdineEsecutivo.setKey(KeyHelper.replaceTokenObjectKey(key , 2, iRAnnoOrd));
	}

	public String getRNumeroOrd() {
		return iRNumeroOrd;
	}

	public void setRNumeroOrd(String iRNumeroOrd) {
		String key = iRelOrdineEsecutivo.getKey();
		 iRelOrdineEsecutivo.setKey(KeyHelper.replaceTokenObjectKey(key , 3, iRNumeroOrd));
	}

	public String getQuantita() {
		return iQuantita;
	}

	public void setQuantita(String iQuantita) {
		this.iQuantita = iQuantita;
	}

	public String getQuantitaConfezione() {
		return iQuantitaConfezione;
	}

	public void setQuantitaConfezione(String iQuantitaConfezione) {
		this.iQuantitaConfezione = iQuantitaConfezione;
	}

	public OrdineEsecutivo getOrdineEsecutivo() {
		return (OrdineEsecutivo)iRelOrdineEsecutivo.getObject();
	}

	public void setOrdineEsecutivoKey(String key) {
		String oldObjectKey = getKey();
		iRelOrdineEsecutivo.setKey(key);
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public String getRTipoUds() {
		return iRTipoUds;
	}

	public void setRTipoUds(String iRTipoUds) {
		String key = iRelTipoUds.getKey();
		iRelTipoUds.setKey(KeyHelper.replaceTokenObjectKey(key , 2, iRTipoUds));
	}

	public String getOrdineEsecutivoKey() {
		return iRelOrdineEsecutivo.getKey();
	}
	
	public YTipoUds getTipoUds() {
		return (YTipoUds)iRelTipoUds.getObject();
	}

	public void setTipoUdsKey(String key) {
		iRelTipoUds.setKey(key);
	}

	public String getTipoUdsKey() {
		return iRelTipoUds.getKey();
	}
	
	public ErrorMessage checkQuantitaConfezione() {
		if(getQuantitaConfezione() == null) {
			return new ErrorMessage("YSOFTRE001", "Quantita per confezione obbligatoria");
		}else {
			BigDecimal quantita = new BigDecimal(getQuantita());
			BigDecimal quantitaPerConf = new BigDecimal(getQuantitaConfezione());
			if(quantita.compareTo(BigDecimal.ZERO) < 0) {
				return new ErrorMessage("YSOFTRE001", "Quantita obbligatoriamente positiva");
			}else {
				if(quantita.remainder(quantitaPerConf).compareTo(BigDecimal.ZERO) != 0) {
					return new ErrorMessage("YSOFTRE001", "Quantita NON divisibile correttamente!");
				}
			}
		}
		return null;
	}

}
