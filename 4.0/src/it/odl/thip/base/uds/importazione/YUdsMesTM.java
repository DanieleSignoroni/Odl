package it.odl.thip.base.uds.importazione;

import com.thera.thermfw.persist.*;

import java.sql.*;
import com.thera.thermfw.base.*;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 11/03/2024
 * <br><br>
 * <b>71469	DSSOF3	11/03/2024</b>    
 * <p>Tabella di frontiera popolata dal MES.</p>
 */

public class YUdsMesTM extends TableManager {

	public static final String R_AZIENDA = "ID_AZIENDA";
	
	public static final String STATO = "STATO";

	public static final String TIMESTAMP_CRZ = "TIMESTAMP_CRZ";

	public static final String TIMESTAMP_AGG = "TIMESTAMP_AGG";

	public static final String ID_UDS = "ID_UDS";

	public static final String R_ARTICOLO = "R_ARTICOLO";

	public static final String QTA_PRM = "QTA_PRM";

	public static final String R_ANNO_ORD_PRD = "R_ANNO_ORD_PRD";

	public static final String R_NUMERO_ORD_PRD = "R_NUM_ORD_PRD";

	public static final String R_CODIMBALLO = "R_CODIMBALLO";	

	public static final String SEMAFORO = "SEMAFORO";

	public static final String TABLE_NAME = SystemParam.getSchema("THIPPERS") + "Y_MES_UDS";

	private static TableManager cInstance;

	private static final String CLASS_NAME = it.odl.thip.base.uds.importazione.YUdsMes.class.getName();

	public synchronized static TableManager getInstance() throws SQLException {
		if (cInstance == null) {
			cInstance = (TableManager)Factory.createObject(YUdsMesTM.class);
		}
		return cInstance;
	}

	public YUdsMesTM() throws SQLException {
		super();
	}

	protected void initialize() throws SQLException {
		setTableName(TABLE_NAME);
		setObjClassName(CLASS_NAME);
		init();
	}

	protected void initializeRelation() throws SQLException {
		super.initializeRelation();
		addAttribute("IdUds", ID_UDS);
		addAttribute("QtaPrm", QTA_PRM);
		addAttribute("RAnnoOrdPrd", R_ANNO_ORD_PRD);
		addAttribute("RNumeroOrdPrd", R_NUMERO_ORD_PRD);
		addAttribute("RCodimballo", R_CODIMBALLO);
		addAttribute("RAzienda", R_AZIENDA);
		addAttribute("RArticolo", R_ARTICOLO);
		addAttribute("Semaforo", SEMAFORO);
		addAttribute("TimestampAgg", TIMESTAMP_AGG);
		addAttribute("TimestampCrz", TIMESTAMP_CRZ);

		setKeys(R_AZIENDA + "," + ID_UDS);

	}

	private void init() throws SQLException {
		configure();
	}

}

