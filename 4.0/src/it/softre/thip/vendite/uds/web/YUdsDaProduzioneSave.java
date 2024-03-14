package it.softre.thip.vendite.uds.web;

import java.io.IOException;

import javax.servlet.ServletException;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.collector.BODataCollector;
import com.thera.thermfw.web.ServletEnvironment;
import com.thera.thermfw.web.servlet.Save;

public class YUdsDaProduzioneSave extends Save { 

	private static final long serialVersionUID = 1L;

	@Override
	public void actionOnObject(BODataCollector boDC, ServletEnvironment se) {
		try {
			if (se.isErrorListEmpity()){
				int ret = boDC.save();
				if ( ret != BODataCollector.OK) {
					se.addErrorMessages(boDC.getErrorList().getErrors());
				}else {
					se.sendRequest(getServletContext(), "com/thera/thermfw/common/ErrorListHandler.jsp", true);
				}

			}else {
				se.sendRequest(getServletContext(), "com/thera/thermfw/common/ErrorListHandler.jsp", true);
			}
		}
		catch (Exception e) {
			e.printStackTrace(Trace.excStream);
		}
	}
	
	@Override
	public void afterProcessAction(BODataCollector boDC, ServletEnvironment se) throws ServletException, IOException {
		super.afterProcessAction(boDC, se);
	}
	
}
