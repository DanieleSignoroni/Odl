package it.odl.thip.base.uds.importazione;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.thera.thermfw.base.TimeUtils;
import com.thera.thermfw.base.Trace;
import com.thera.thermfw.common.*;
import com.thera.thermfw.persist.CachedStatement;
import com.thera.thermfw.persist.ConnectionManager;
import com.thera.thermfw.persist.Database;

import it.thera.thip.base.azienda.Azienda;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 11/03/2024
 * <br><br>
 * <b>71469	DSSOF3	11/03/2024</b>    
 * <p>Tabella di frontiera popolata dal MES.</p>
 */

public class YUdsMes extends YUdsMesPO {

	public static final String STMT_UPDATE_STATO = ""
			+ " UPDATE "+YUdsMesTM.TABLE_NAME+" "
			+ "SET "+YUdsMesTM.SEMAFORO+" = ?, "+YUdsMesTM.TIMESTAMP_AGG+" = ? "
			+ "WHERE "+YUdsMesTM.R_AZIENDA+" = ? "
			+ "AND "+YUdsMesTM.ID_UDS+" = ? ";

	protected static CachedStatement cUpdateSemaforino = new CachedStatement(STMT_UPDATE_STATO);

	public ErrorMessage checkDelete() {
		return null;
	}

	/**
	 * @author Daniele Signoroni
	 * <p>
	 * Aggiorna le colonne {@link YUdsMesTM#SEMAFORO},{@link YUdsMesTM#TIMESTAMP_AGG}.<br>
	 * Il timestamp e' quello dell'istante dell'update.<br>
	 * Lo stato e' quello passato.
	 * </p>
	 * @param stato uno tra quelli contenuti in questa classe {@link YStatoImportazioneUdsMES}
	 * @param idUds
	 * @return true se l'update e' stata eseguita con successo, false altrimenti
	 */
	public static boolean aggiornaSemaforino(char stato, String idUds) {
		try {
			PreparedStatement ps = cUpdateSemaforino.getStatement();
			Database db = ConnectionManager.getCurrentDatabase();
			db.setString(ps, 1, String.valueOf(stato));	  
			ps.setTimestamp(2, TimeUtils.getCurrentTimestamp());
			db.setString(ps,3, Azienda.getAziendaCorrente());
			db.setString(ps,4, idUds);  
			int rc = ps.executeUpdate();
			if (rc >= 0){
				return true;
			}
		}catch (SQLException e) {
			e.printStackTrace(Trace.excStream);
		}
		return false;
	}
	
	/**
	 * @author Daniele Signoroni
	 * <p>
	 * </p>
	 * @return una lista di uds passate dal MES da importare in Panthera
	 */
	@SuppressWarnings("unchecked")
	public static List<YUdsMes> listaUdsMesDaImportare(){
		List<YUdsMes> ret = new ArrayList<YUdsMes>();
		try {
			ret = YUdsMes.retrieveList(YUdsMes.class, " "+YUdsMesTM.R_AZIENDA+" = '"+Azienda.getAziendaCorrente()+"' AND "+YUdsMesTM.SEMAFORO+" = '"+YStatoImportazioneUdsMES.DA_PROCESSARE+"' ", "", false);
		} catch (ClassNotFoundException e) {
			e.printStackTrace(Trace.excStream);
		} catch (InstantiationException e) {
			e.printStackTrace(Trace.excStream);
		} catch (IllegalAccessException e) {
			e.printStackTrace(Trace.excStream);
		} catch (SQLException e) {
			e.printStackTrace(Trace.excStream);
		}
		return ret;
	}

}

