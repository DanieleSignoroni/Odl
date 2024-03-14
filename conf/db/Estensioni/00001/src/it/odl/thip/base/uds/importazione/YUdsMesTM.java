/*
 * @(#)YUdsMesTM.java
 */

/**
 * YUdsMesTM
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 11/03/2024 at 10:35:37
 */
/*
 * Revisions:
 * Date          Owner      Description
 * 11/03/2024    Wizard     Codice generato da Wizard
 *
 */
package it.odl.thip.base.uds.importazione;
import com.thera.thermfw.persist.*;
import com.thera.thermfw.common.*;
import java.sql.*;
import com.thera.thermfw.base.*;

public class YUdsMesTM extends TableManager {

  
  /**
   * Attributo R_AZIENDA
   */
  public static final String R_AZIENDA = "R_AZIENDA";

  /**
   * Attributo ID_UDS
   */
  public static final String ID_UDS = "ID_UDS";

  /**
   * Attributo R_ARTICOLO
   */
  public static final String R_ARTICOLO = "R_ARTICOLO";

  /**
   * Attributo QTA_PRM
   */
  public static final String QTA_PRM = "QTA_PRM";

  /**
   * Attributo R_ANNO_ORD_PRD
   */
  public static final String R_ANNO_ORD_PRD = "R_ANNO_ORD_PRD";

  /**
   * Attributo R_NUMERO_ORD_PRD
   */
  public static final String R_NUMERO_ORD_PRD = "R_NUMERO_ORD_PRD";

  /**
   * Attributo R_CODIMBALLO
   */
  public static final String R_CODIMBALLO = "R_CODIMBALLO";

  /**
   *  TABLE_NAME
   */
  public static final String TABLE_NAME = SystemParam.getSchema("THIPPERS") + "Y_CC";

  /**
   *  instance
   */
  private static TableManager cInstance;

  /**
   *  CLASS_NAME
   */
  private static final String CLASS_NAME = it.odl.thip.base.uds.importazione.YUdsMes.class.getName();

  
  /**
   *  getInstance
   * @return TableManager
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/03/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  public synchronized static TableManager getInstance() throws SQLException {
    if (cInstance == null) {
      cInstance = (TableManager)Factory.createObject(YUdsMesTM.class);
    }
    return cInstance;
  }

  /**
   *  YUdsMesTM
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/03/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  public YUdsMesTM() throws SQLException {
    super();
  }

  /**
   *  initialize
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/03/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  protected void initialize() throws SQLException {
    setTableName(TABLE_NAME);
    setObjClassName(CLASS_NAME);
    init();
  }

  /**
   *  initializeRelation
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/03/2024    Wizard     Codice generato da Wizard
   *
   */
  protected void initializeRelation() throws SQLException {
    super.initializeRelation();
    addAttribute("IdUds", ID_UDS);
    addAttribute("QtaPrm", QTA_PRM);
    addAttribute("RAnnoOrdPrd", R_ANNO_ORD_PRD);
    addAttribute("RNumeroOrdPrd", R_NUMERO_ORD_PRD);
    addAttribute("RCodimballo", R_CODIMBALLO);
    addAttribute("RAzienda", R_AZIENDA);
    addAttribute("RArticolo", R_ARTICOLO);
    
    setKeys(R_AZIENDA + "," + ID_UDS);
  }

  /**
   *  init
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/03/2024    Wizard     Codice generato da Wizard
   *
   */
  private void init() throws SQLException {
    configure(ID_UDS + ", " + QTA_PRM + ", " + R_ANNO_ORD_PRD + ", " + R_NUMERO_ORD_PRD
         + ", " + R_CODIMBALLO + ", " + R_AZIENDA + ", " + R_ARTICOLO);
  }

}

