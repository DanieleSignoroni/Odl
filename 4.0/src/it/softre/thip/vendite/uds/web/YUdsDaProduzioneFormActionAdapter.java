package it.softre.thip.vendite.uds.web;

import java.io.IOException;

import javax.servlet.ServletException;

import com.thera.thermfw.ad.ClassADCollection;
import com.thera.thermfw.web.ServletEnvironment;
import com.thera.thermfw.web.WebToolBar;
import com.thera.thermfw.web.WebToolBarButton;
import com.thera.thermfw.web.servlet.FormActionAdapter;

/**
 * 
 * @author DSSOF3	
 *	DSSOF3	70687	04/10/2022	Prima stesura.
 *								Serlvet per la creazione dell'uds da OrdineEsecutivo.	
 */

public class YUdsDaProduzioneFormActionAdapter extends FormActionAdapter{

	private static final long serialVersionUID = 1L;

	protected static final String CREA_UDS_DA_PRODUZIONE = "CREA_UDS_DA_PRODUZIONE";
	protected static String CREA_UDS_DA_PRODUZIONE_IMG = "it/softre/thip/vendite/uds/img/btnart.png";
	protected static String CREA_UDS_DA_PRODUZIONE_RES = "it/softre/thip/vendite/uds/resources/YUdsDaProduzione";

	@Override
	public void modifyToolBar(WebToolBar toolBar) {
		super.modifyToolBar(toolBar);
		toolBar.getButtons().clear();
		WebToolBarButton creaUdsDaProduzione = new WebToolBarButton(CREA_UDS_DA_PRODUZIONE, "action_submit", "errorsFrame"
				, "no",CREA_UDS_DA_PRODUZIONE_RES, CREA_UDS_DA_PRODUZIONE, CREA_UDS_DA_PRODUZIONE_IMG, CREA_UDS_DA_PRODUZIONE, "", false);
		toolBar.addButton("tbbtTickler", creaUdsDaProduzione);
	}

	@Override
	protected void otherActions(ClassADCollection cadc, ServletEnvironment se) throws ServletException, IOException {
		String action = se.getRequest().getParameter(ACTION);
		if(action.equals(CREA_UDS_DA_PRODUZIONE)) {
			se.sendRequest(getServletContext(), se.getServletPath() + "it.softre.thip.vendite.uds.web.YUdsDaProduzioneSave", true);
		}
		super.otherActions(cadc, se);
	}
	
}
