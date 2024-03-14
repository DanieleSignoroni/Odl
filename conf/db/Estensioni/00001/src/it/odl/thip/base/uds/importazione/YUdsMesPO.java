/*
 * @(#)YUdsMesPO.java
 */

/**
 * null
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
import java.sql.*;
import java.util.*;
import it.thera.thip.base.articolo.Articolo;
import java.math.*;
import com.thera.thermfw.common.*;
import it.thera.thip.base.azienda.Azienda;
import com.thera.thermfw.security.*;

public abstract class YUdsMesPO extends PersistentObject implements BusinessObject, Authorizable, Deletable, Conflictable {

  
  /**
   *  instance
   */
  private static YUdsMes cInstance;

  /**
   * Attributo iIdUds
   */
  protected String iIdUds;

  /**
   * Attributo iQtaPrm
   */
  protected BigDecimal iQtaPrm;

  /**
   * Attributo iRAnnoOrdPrd
   */
  protected String iRAnnoOrdPrd;

  /**
   * Attributo iRNumeroOrdPrd
   */
  protected String iRNumeroOrdPrd;

  /**
   * Attributo iRCodimballo
   */
  protected String iRCodimballo;

  /**
   * Attributo iArticolo
   */
  protected Proxy iArticolo = new Proxy(it.thera.thip.base.articolo.Articolo.class);

  
  /**
   *  retrieveList
   * @param where
   * @param orderBy
   * @param optimistic
   * @return Vector
   * @throws SQLException
   * @throws ClassNotFoundException
   * @throws InstantiationException
   * @throws IllegalAccessException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/03/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  public static Vector retrieveList(String where, String orderBy, boolean optimistic) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    if (cInstance == null)
      cInstance = (YUdsMes)Factory.createObject(YUdsMes.class);
    return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
  }

  /**
   *  elementWithKey
   * @param key
   * @param lockType
   * @return YUdsMes
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/03/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  public static YUdsMes elementWithKey(String key, int lockType) throws SQLException {
    return (YUdsMes)PersistentObject.elementWithKey(YUdsMes.class, key, lockType);
  }

  /**
   * YUdsMesPO
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public YUdsMesPO() {
    setRAzienda(Azienda.getAziendaCorrente());
  }

  /**
   * Valorizza l'attributo. 
   * @param idUds
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setIdUds(String idUds) {
    this.iIdUds = idUds;
    setDirty();
    setOnDB(false);
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getIdUds() {
    return iIdUds;
  }

  /**
   * Valorizza l'attributo. 
   * @param qtaPrm
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setQtaPrm(BigDecimal qtaPrm) {
    this.iQtaPrm = qtaPrm;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return BigDecimal
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public BigDecimal getQtaPrm() {
    return iQtaPrm;
  }

  /**
   * Valorizza l'attributo. 
   * @param rAnnoOrdPrd
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setRAnnoOrdPrd(String rAnnoOrdPrd) {
    this.iRAnnoOrdPrd = rAnnoOrdPrd;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getRAnnoOrdPrd() {
    return iRAnnoOrdPrd;
  }

  /**
   * Valorizza l'attributo. 
   * @param rNumeroOrdPrd
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setRNumeroOrdPrd(String rNumeroOrdPrd) {
    this.iRNumeroOrdPrd = rNumeroOrdPrd;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getRNumeroOrdPrd() {
    return iRNumeroOrdPrd;
  }

  /**
   * Valorizza l'attributo. 
   * @param rCodimballo
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setRCodimballo(String rCodimballo) {
    this.iRCodimballo = rCodimballo;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getRCodimballo() {
    return iRCodimballo;
  }

  /**
   * Valorizza l'attributo. 
   * @param articolo
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setArticolo(Articolo articolo) {
    String oldObjectKey = getKey();
    this.iArticolo.setObject(articolo);
    setDirty();
    if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
      setOnDB(false);
    }
  }

  /**
   * Restituisce l'attributo. 
   * @return Articolo
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public Articolo getArticolo() {
    return (Articolo)iArticolo.getObject();
  }

  /**
   * setArticoloKey
   * @param key
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setArticoloKey(String key) {
    String oldObjectKey = getKey();
    iArticolo.setKey(key);
    setDirty();
    if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
      setOnDB(false);
    }
  }

  /**
   * getArticoloKey
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getArticoloKey() {
    return iArticolo.getKey();
  }

  /**
   * Valorizza l'attributo. 
   * @param rAzienda
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setRAzienda(String rAzienda) {
    String key = iArticolo.getKey();
    iArticolo.setKey(KeyHelper.replaceTokenObjectKey(key , 1, rAzienda));
    setDirty();
    setOnDB(false);
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getRAzienda() {
    String key = iArticolo.getKey();
    String objRAzienda = KeyHelper.getTokenObjectKey(key,1);
    return objRAzienda;
    
  }

  /**
   * Valorizza l'attributo. 
   * @param rArticolo
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setRArticolo(String rArticolo) {
    String key = iArticolo.getKey();
    iArticolo.setKey(KeyHelper.replaceTokenObjectKey(key , 2, rArticolo));
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getRArticolo() {
    String key = iArticolo.getKey();
    String objRArticolo = KeyHelper.getTokenObjectKey(key,2);
    return objRArticolo;
  }

  /**
   * setEqual
   * @param obj
   * @throws CopyException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setEqual(Copyable obj) throws CopyException {
    super.setEqual(obj);
    YUdsMesPO yUdsMesPO = (YUdsMesPO)obj;
    iArticolo.setEqual(yUdsMesPO.iArticolo);
  }

  /**
   * checkAll
   * @param components
   * @return Vector
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public Vector checkAll(BaseComponentsCollection components) {
    Vector errors = new Vector();
    components.runAllChecks(errors);
    return errors;
  }

  /**
   *  setKey
   * @param key
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setKey(String key) {
    setRAzienda(KeyHelper.getTokenObjectKey(key, 1));
    setIdUds(KeyHelper.getTokenObjectKey(key, 2));
  }

  /**
   *  getKey
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getKey() {
    String rAzienda = getRAzienda();
    String idUds = getIdUds();
    Object[] keyParts = {rAzienda, idUds};
    return KeyHelper.buildObjectKey(keyParts);
  }

  /**
   * isDeletable
   * @return boolean
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public boolean isDeletable() {
    return checkDelete() == null;
  }

  /**
   * toString
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public String toString() {
    return getClass().getName() + " [" + KeyHelper.formatKeyString(getKey()) + "]";
  }

  /**
   *  getTableManager
   * @return TableManager
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/03/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  protected TableManager getTableManager() throws SQLException {
    return YUdsMesTM.getInstance();
  }

}

