package it.softre.thip.vendite.uds.web;

import java.math.BigDecimal;
import java.sql.SQLException;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.collector.BODataCollector;
import com.thera.thermfw.common.ErrorMessage;
import com.thera.thermfw.persist.ConnectionManager;

import it.softre.thip.base.uds.YTipoUds;
import it.softre.thip.vendite.uds.YUdsDaProduzione;
import it.softre.thip.vendite.uds.YUdsVenRig;
import it.softre.thip.vendite.uds.YUdsVendita;
import it.thera.thip.cs.ThipDataCollector;
import it.thera.thip.produzione.ordese.OrdineEsecutivo;
import it.thera.thip.vendite.proposteEvasione.CreaMessaggioErrore;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 14/03/2024
 * <br><br>
 * <b></b>    
 * <p>Nuova stesura.<br>
 * </p>
 */

public class YUdsDaProduzioneDataCollector extends ThipDataCollector{

	@Override
	public int save() {
		int ret = check();
		if(ret == BODataCollector.OK) {
			creaUdsDaProduzione();
		}
		if(!getErrorList().getErrors().isEmpty()) {
			ret = BODataCollector.ERROR;
			return ret;
		}
		return super.save();
	}

	@SuppressWarnings("unchecked")
	public void creaUdsDaProduzione() {
		YUdsDaProduzione bo = (YUdsDaProduzione) this.getBo();
		try {
			OrdineEsecutivo ordEsec = bo.getOrdineEsecutivo();
			YTipoUds tipoUds = bo.getTipoUds();
			if(ordEsec != null && tipoUds != null) {
				BigDecimal quantita = new BigDecimal(bo.getQuantita());
				BigDecimal quantitaPerConf = new BigDecimal(bo.getQuantitaConfezione());
				int numberOfPackage = quantita.divide(quantitaPerConf).intValue();
				for(int i = 0; i < numberOfPackage; i++) {
					YUdsVendita udsVE = new YUdsVendita(ordEsec, tipoUds);
					udsVE.setAssegnaNumeratoreAutomatico(false);
					udsVE.setIdUds(YUdsVendita.getNewProgressivoForScatola());
					int rc = udsVE.save();
					if(rc < 0) {
						getErrorList().addError(CreaMessaggioErrore.daRcAErrorMessage(rc, (SQLException) udsVE.getException()));
					}
					YUdsVenRig udsVenRig = new YUdsVenRig(ordEsec, quantitaPerConf);
					if(ordEsec.getOrdineVenditaRiga() != null) {
						udsVenRig.setRAnnoOrdVen(ordEsec.getAnnoOrdineCliente());
						udsVenRig.setRNumOrdVen(ordEsec.getNumeroOrdineCliente());
						udsVenRig.setRRigaOrdVen(ordEsec.getRigaOrdineCliente());
					}
					if(ordEsec.getArticolo().getPesoNetto() != null) {
						udsVenRig.setPesoNetto(ordEsec.getArticolo().getPesoNetto().multiply(quantitaPerConf));
					}
					if(ordEsec.getArticolo().getPeso() != null) {
						udsVenRig.setPesoLordo(ordEsec.getArticolo().getPeso().multiply(quantitaPerConf));
					}
					udsVenRig.setParent(udsVE);
					udsVE.getRigheUDSVendita().add(udsVenRig);
					udsVE.setRicalcoloPesi(true);
					if(udsVE.save() >= 0) {
						ConnectionManager.commit();
					}else {
						ConnectionManager.rollback();
					}
				}
			}else {
				if(ordEsec == null) {
					getErrorList().addError(new ErrorMessage("YSOFTRE001","Ordine esecutivo obbligatorio"));
				}else {
					getErrorList().addError(new ErrorMessage("YSOFTRE001","Tipo UDS obbligatorio"));
				}
			}
		}catch (SQLException e) {
			e.printStackTrace(Trace.excStream);
		}
	}
}
