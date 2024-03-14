package it.odl.thip.base.uds.importazione;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 11/03/2024
 * <br><br>
 * <b>71469	DSSOF3	11/03/2024</b>    
 * <p>Enumerato YStatoImportazioneUdsMES.<br>
 * {@value #DA_PROCESSARE} --> non e' ancora stato processato dal lavoro.<br>
 * {@value #IN_CORSO} --> sta venendo processato dal lavoro.<br>
 * {@value #PROCESSATO} --> e' stato processato e ha creato correttamente un uds di vendita.
 * </p>
 */

public class YStatoImportazioneUdsMES {

	public static final char DA_PROCESSARE = '0';
	public static final char IN_CORSO = '1';
	public static final char PROCESSATO = '2';
	
}
