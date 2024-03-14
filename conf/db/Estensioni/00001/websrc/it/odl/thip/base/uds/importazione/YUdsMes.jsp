<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"
                      "file:///W:\PthDev\Projects\Panthera\Odl\WebContent\dtd/xhtml1-transitional.dtd">
<html>
<!-- WIZGEN Therm 2.0.0 as Form - multiBrowserGen = true -->
<%=WebGenerator.writeRuntimeInfo()%>
  <head>
<%@ page contentType="text/html; charset=Cp1252"%>
<%@ page import= " 
  java.sql.*, 
  java.util.*, 
  java.lang.reflect.*, 
  javax.naming.*, 
  com.thera.thermfw.common.*, 
  com.thera.thermfw.type.*, 
  com.thera.thermfw.web.*, 
  com.thera.thermfw.security.*, 
  com.thera.thermfw.base.*, 
  com.thera.thermfw.ad.*, 
  com.thera.thermfw.persist.*, 
  com.thera.thermfw.gui.cnr.*, 
  com.thera.thermfw.setting.*, 
  com.thera.thermfw.collector.*, 
  com.thera.thermfw.batch.web.*, 
  com.thera.thermfw.batch.*, 
  com.thera.thermfw.pref.* 
"%> 
<%
  ServletEnvironment se = (ServletEnvironment)Factory.createObject("com.thera.thermfw.web.ServletEnvironment"); 
  BODataCollector YUdsMesBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebForm YUdsMesForm =  
     new com.thera.thermfw.web.WebForm(request, response, "YUdsMesForm", "YUdsMes", null, "com.thera.thermfw.web.servlet.FormActionAdapter", false, false, true, true, true, true, null, 0, true, "it/odl/thip/base/uds/importazione/YUdsMes.js"); 
  YUdsMesForm.setServletEnvironment(se); 
  YUdsMesForm.setJSTypeList(jsList); 
  YUdsMesForm.setHeader("it.thera.thip.cs.PantheraHeader.jsp"); 
  YUdsMesForm.setFooter("com.thera.thermfw.common.Footer.jsp"); 
  YUdsMesForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = YUdsMesForm.getMode(); 
  String key = YUdsMesForm.getKey(); 
  String errorMessage; 
  boolean requestIsValid = false; 
  boolean leftIsKey = false; 
  boolean conflitPresent = false; 
  String leftClass = ""; 
  try 
  {
     se.initialize(request, response); 
     if(se.begin()) 
     { 
        YUdsMesForm.outTraceInfo(getClass().getName()); 
        String collectorName = YUdsMesForm.findBODataCollectorName(); 
                YUdsMesBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (YUdsMesBODC instanceof WebDataCollector) 
            ((WebDataCollector)YUdsMesBODC).setServletEnvironment(se); 
        YUdsMesBODC.initialize("YUdsMes", true, 0); 
        YUdsMesForm.setBODataCollector(YUdsMesBODC); 
        int rcBODC = YUdsMesForm.initSecurityServices(); 
        mode = YUdsMesForm.getMode(); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           YUdsMesForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = YUdsMesBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              YUdsMesForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 
<% 
  WebMenuBar menuBar = new com.thera.thermfw.web.WebMenuBar("HM_Array1", "150", "#000000","#000000","#A5B6CE","#E4EAEF","#FFFFFF","#000000"); 
  menuBar.setParent(YUdsMesForm); 
   request.setAttribute("menuBar", menuBar); 
%> 
<jsp:include page="/com/thera/thermfw/common/defObjMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="menuBar"/> 
</jsp:include> 
<% 
  menuBar.write(out); 
  menuBar.writeChildren(out); 
%> 
<% 
  WebToolBar myToolBarTB = new com.thera.thermfw.web.WebToolBar("myToolBar", "24", "24", "16", "16", "#f7fbfd","#C8D6E1"); 
  myToolBarTB.setParent(YUdsMesForm); 
   request.setAttribute("toolBar", myToolBarTB); 
%> 
<jsp:include page="/com/thera/thermfw/common/defObjMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="toolBar"/> 
</jsp:include> 
<% 
   myToolBarTB.write(out); 
%> 
</head>
  <body onbeforeunload="<%=YUdsMesForm.getBodyOnBeforeUnload()%>" onload="<%=YUdsMesForm.getBodyOnLoad()%>" onunload="<%=YUdsMesForm.getBodyOnUnload()%>" style="margin: 0px; overflow: hidden;"><%
   YUdsMesForm.writeBodyStartElements(out); 
%> 

    <table width="100%" height="100%" cellspacing="0" cellpadding="0">
<tr>
<td style="height:0" valign="top">
<% String hdr = YUdsMesForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", YUdsMesBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=YUdsMesForm.getServlet()%>" method="post" name="YUdsMesForm" style="height:100%"><%
  YUdsMesForm.writeFormStartElements(out); 
%>

      <table cellpadding="0" cellspacing="0" height="100%" id="emptyborder" width="100%">
        <tr>
          <td style="height:0">
            <% menuBar.writeElements(out); %> 

          </td>
        </tr>
        <tr>
          <td style="height:0">
            <% myToolBarTB.writeChildren(out); %> 

          </td>
        </tr>
        <tr>
          <td>
            <% 
  WebTextInput YUdsMesRAzienda =  
     new com.thera.thermfw.web.WebTextInput("YUdsMes", "RAzienda"); 
  YUdsMesRAzienda.setParent(YUdsMesForm); 
%>
<input class="<%=YUdsMesRAzienda.getClassType()%>" id="<%=YUdsMesRAzienda.getId()%>" maxlength="<%=YUdsMesRAzienda.getMaxLength()%>" name="<%=YUdsMesRAzienda.getName()%>" size="<%=YUdsMesRAzienda.getSize()%>" type="hidden"><% 
  YUdsMesRAzienda.write(out); 
%>

          </td>
        </tr>
        <tr>
          <td>
            <% 
  WebTextInput YUdsMesIdUds =  
     new com.thera.thermfw.web.WebTextInput("YUdsMes", "IdUds"); 
  YUdsMesIdUds.setParent(YUdsMesForm); 
%>
<input class="<%=YUdsMesIdUds.getClassType()%>" id="<%=YUdsMesIdUds.getId()%>" maxlength="<%=YUdsMesIdUds.getMaxLength()%>" name="<%=YUdsMesIdUds.getName()%>" size="<%=YUdsMesIdUds.getSize()%>" type="hidden"><% 
  YUdsMesIdUds.write(out); 
%>

          </td>
        </tr>
        <tr>
          <td height="100%">
            <!--<span class="tabbed" id="mytabbed">-->
<table width="100%" height="100%" cellpadding="0" cellspacing="0" style="padding-right:1px">
   <tr valign="top">
     <td><% 
  WebTabbed mytabbed = new com.thera.thermfw.web.WebTabbed("mytabbed", "100%", "100%"); 
  mytabbed.setParent(YUdsMesForm); 
  mytabbed.write(out); 
%>

     </td>
   </tr>
   <tr>
     <td height="100%"><div class="tabbed_pagine" id="tabbedPagine" style="position: relative; width: 100%; height: 100%;">
            </div><% mytabbed.endTabbed();%> 

     </td>
   </tr>
</table><!--</span>-->
          </td>
        </tr>
        <tr>
          <td style="height:0">
            <% 
  WebErrorList errorList = new com.thera.thermfw.web.WebErrorList(); 
  errorList.setParent(YUdsMesForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>-->
          </td>
        </tr>
      </table>
    <%
  YUdsMesForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = YUdsMesForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", YUdsMesBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>


  <%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              YUdsMesForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, YUdsMesBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, YUdsMesBODC.getErrorList().getErrors()); 
           if(YUdsMesBODC.getConflict() != null) 
                conflitPresent = true; 
     } 
     else 
        errors.add(new ErrorMessage("BAS0000010")); 
  } 
  catch(NamingException e) { 
     errorMessage = e.getMessage(); 
     errors.add(new ErrorMessage("CBS000025", errorMessage));  } 
  catch(SQLException e) {
     errorMessage = e.getMessage(); 
     errors.add(new ErrorMessage("BAS0000071", errorMessage));  } 
  catch(Throwable e) {
     e.printStackTrace(Trace.excStream);
  }
  finally 
  {
     if(YUdsMesBODC != null && !YUdsMesBODC.close(false)) 
        errors.addAll(0, YUdsMesBODC.getErrorList().getErrors()); 
     try 
     { 
        se.end(); 
     }
     catch(IllegalArgumentException e) { 
        e.printStackTrace(Trace.excStream); 
     } 
     catch(SQLException e) { 
        e.printStackTrace(Trace.excStream); 
     } 
  } 
  if(!errors.isEmpty())
  { 
      if(!conflitPresent)
  { 
     request.setAttribute("ErrorMessages", errors); 
     String errorPage = YUdsMesForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", YUdsMesBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = YUdsMesForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>
</html>
