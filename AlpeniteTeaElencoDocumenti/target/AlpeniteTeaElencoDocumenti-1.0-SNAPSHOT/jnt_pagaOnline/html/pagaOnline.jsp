<%@page import="java.util.zip.ZipException"%>
<%@page import="java.lang.reflect.Array"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="java.util.zip.ZipEntry"%>
<%@page import="java.util.zip.ZipOutputStream"%>
<%@page import="org.jahia.registries.ServicesRegistry"%>
<%@page import="com.alpenite.tea.communicationLayer.data.File"%>
<%@page import="com.alpenite.tea.communicationLayer.WSClient"%>
<%@page import="org.jahia.services.content.JCRNodeWrapper"%>
<%@page import="it.netsw.apps.igfs.cg.coms.api.init.IgfsCgInit" %>
<%@page import="it.netsw.apps.igfs.cg.coms.api.init.IgfsCgInit.*" %>
<%@page import="it.netsw.apps.igfs.cg.coms.api.BaseIgfsCg.CountryCode" %>
<%@page import="it.netsw.apps.igfs.cg.coms.api.BaseIgfsCg" %>
<%@page import="java.net.URL" %>
<%@page import="java.security.SecureRandom" %>
<%@page import="java.util.HashMap" %>
<%@page import="java.util.Map" %>
<%@page import="java.util.Properties" %>
<%@page import="java.io.InputStream" %>

<%
String serverURL= "https://testeps.netswgroup.it/UNI_CG_SERVICES/services";
int timeout = 15000;

String tid= "UNI_ECOM";
// String tid= "UNI_MYBK"; MY BANK

String kSig= "UNI_TESTKEY";
String shopID= "522160000881";
String email= "user@customer.it";
TrType trType = TrType.AUTH;

BaseIgfsCg.CurrencyCode curCode = BaseIgfsCg.CurrencyCode.EUR;
BaseIgfsCg.LangID  langID = BaseIgfsCg.LangID.IT;
long amount= 100;
String errorURL= "https://merchant/error.jsp";
String notifyURL= "https://merchant/notify.jsp";
String AddInfo1 = "numerofattura";
String AddInfo2 = "Azienda";

IgfsCgInit init = new IgfsCgInit();
init.setServerURL(new URL(serverURL));
init.setTimeout(timeout);
init.setTid(tid);
init.setKSig(kSig);
init.setAddInfo1(AddInfo1);
init.setAddInfo2(AddInfo2);
init.setShopID(shopID);
init.setShopUserRef(email);
init.setTrType(trType);
init.setCurrencyCode(curCode);
init.setLangID(langID);
init.setAmount(amount);
init.setErrorURL(new URL(errorURL));
init.setNotifyURL(new URL(notifyURL));
//====================================================================
//=              esecuzione richiesta di inizializzazione            =
//====================================================================
if (!init.execute()) {
  // ====================================================================
  // = redirect del client su pagina di errore definita dall esercente  =
  // ====================================================================
  response.sendRedirect(errorURL + "?rc=" + init.getRc() + "&errorDesc=" +
init.getErrorDesc());
 return;
}
String paymentID = init.getPaymentID();
//NOTA: Salvo il paymentID relativo alla richiesta (es. sul DB)...
//====================================================================
//=              redirect del client verso URL Pagonline BuyNow           =
//====================================================================
URL redirectURL = init.getRedirectURL();
response.sendRedirect(redirectURL.toString());
%>
