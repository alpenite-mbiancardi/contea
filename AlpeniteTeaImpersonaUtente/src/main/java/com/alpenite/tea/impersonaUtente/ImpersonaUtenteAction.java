package com.alpenite.tea.impersonaUtente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jahia.bin.Action;
import org.jahia.bin.ActionResult;
import org.jahia.registries.ServicesRegistry;
import org.jahia.services.content.JCRSessionWrapper;
import org.jahia.services.render.RenderContext;
import org.jahia.services.render.Resource;
import org.jahia.services.render.URLResolver;
import org.jahia.services.usermanager.JahiaUser;
import org.jahia.services.usermanager.JahiaUserManagerService;

import com.alpenite.tea.communicationLayer.WSClient;
import com.alpenite.tea.communicationLayer.WSReturnManager;
import com.alpenite.tea.communicationLayer.data.Constants;
import com.alpenite.tea.communicationLayer.data.WSReturn;
import org.jahia.userregistration.utils.MD5Util;
import org.jahia.userregistration.utils.UserJahiaDB;
import org.slf4j.Logger;

public class ImpersonaUtenteAction extends Action {

    private transient static Logger logger = org.slf4j.LoggerFactory.getLogger(ImpersonaUtenteAction.class);

    @Override
    public ActionResult doExecute(HttpServletRequest req, RenderContext renderContext, Resource resource, JCRSessionWrapper session, Map<String, List<String>> parameters, URLResolver urlResolver) throws Exception {

        JahiaUserManagerService userManagerService = ServicesRegistry.getInstance().getJahiaUserManagerService();
    	String username = getParameter(parameters, "username");
    	String group = getParameter(parameters, "group");
    	String groupAmmCond = getParameter(parameters, "groupAmmCond");
        String impersonaUtenteMessaggio = "";
        String currentUser = getParameter(parameters, "currentUser");

        if ( username == null  || username.equals("")) {
            impersonaUtenteMessaggio = Constants.ERRORE_GENERICO; //"No user specified";
            WSReturnManager.evaluate(new WSReturn<String>(impersonaUtenteMessaggio, this.getClass().getName(), "return" , null), req.getSession());
            return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("userredirectpage").get(0))).getPath());
        }

        if ( group == null || group.equals("")) {
            impersonaUtenteMessaggio = Constants.ERRORE_GENERICO; //"group not defined";
            WSReturnManager.evaluate(new WSReturn<String>(impersonaUtenteMessaggio, this.getClass().getName(), "return" , null), req.getSession());
            return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("userredirectpage").get(0))).getPath());
        }

        UserJahiaDB userJahiaDB = new UserJahiaDB();
        Connection connection = userJahiaDB.connectedDB();
        Statement stm = connection.createStatement();
        ResultSet rs = stm.executeQuery("" +
                "select user_registration.username, user_registration.j_bp, user_registration.j_tipoBP " +
                "from user_registration left join user_group ug on user_registration.username = ug.username " +
                "where user_registration.username ='" + username + "' " +
                "and ug.group_name ='" + group + "' "
        );
        if (rs.isBeforeFirst()) {
            logger.info("ALPENITE ImpersonaUtenteAction - user find in db");
            if (rs.next()) {
                logger.info("ALPENITE ImpersonaUtenteAction - user '" + rs.getString("username") + "' in db");

                String jBp = rs.getString("j_bp");
                logger.info("ALPENITE ImpersonaUtenteAction - jBp '" + jBp + "' in db");

                req.getSession().removeAttribute("UTINFSESSION");
                req.getSession().removeAttribute("BPCSESSION");
                req.getSession().removeAttribute("BPUSESSION");
                WSClient.getClient(req.getSession()).setBpUtenza(null);
                WSClient.getClient(req.getSession()).setBpCliente(null);

                logger.info("ALPENITE ImpersonaUtenteAction - groupAmmCond: " + groupAmmCond);

                // add bputenza
                WSClient.getClient(req.getSession()).setBpUtenza(jBp);
                req.getSession().setAttribute("BPUSESSION", jBp);
                // add bpcliente se non è amministratore di condominio
                Statement stm2 = connection.createStatement();
                ResultSet rs2 = stm2.executeQuery("" +
                        "select user_group.group_name " +
                        "from user_group " +
                        "where user_group.username='" + username + "' ");
                if (rs2.isBeforeFirst()) {
                    boolean findAmmCondominio = false;
                    while (rs2.next()){
                        if (rs2.getString("group_name").equals(groupAmmCond)){
                            findAmmCondominio = true;
                        }
                    }
                    if (!findAmmCondominio){
                        WSClient.getClient(req.getSession()).setBpCliente(jBp);
                        req.getSession().setAttribute("BPCSESSION", jBp);
                    }
                }

                stm2.close();
                // add tipoBp to root
                userManagerService.lookupUser(currentUser).setProperty("j:tipoBP", rs.getString("j_tipoBP"));

                //String testoHelpDesk = username;
                req.getSession().setAttribute("UTINFSESSION", username);
            }
        }else{
            JahiaUser user = userManagerService.lookupUser(username);
            if ( user == null)
                impersonaUtenteMessaggio = Constants.ERRORE_GENERICO; //"No user found";

            if( user!= null && !user.isMemberOfGroup(renderContext.getSite().getID(), group))
                impersonaUtenteMessaggio = Constants.ERRORE_GENERICO;

            if (!impersonaUtenteMessaggio.equals("")) {
                WSReturnManager.evaluate(new WSReturn<String>(impersonaUtenteMessaggio, this.getClass().getName(), "return" , null), req.getSession());
                return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("userredirectpage").get(0))).getPath());
            }

            req.getSession().removeAttribute("UTINFSESSION");
            req.getSession().removeAttribute("BPCSESSION");
            req.getSession().removeAttribute("BPUSESSION");
            WSClient.getClient(req.getSession()).setBpUtenza(null);
            WSClient.getClient(req.getSession()).setBpCliente(null);

            // add bputenza
            WSClient.getClient(req.getSession()).setBpUtenza(user.getProperty("j:bp"));
            req.getSession().setAttribute("BPUSESSION", user.getProperty("j:bp"));
            // add bpcliente se non è amministratore di condominio
            if(!user.isMemberOfGroup(renderContext.getSite().getID(), groupAmmCond)){
                WSClient.getClient(req.getSession()).setBpCliente(user.getProperty("j:bp"));
                req.getSession().setAttribute("BPCSESSION", user.getProperty("j:bp"));
            }
            // add tipoBp
            userManagerService.lookupUser(currentUser).setProperty("j:tipoBP", user.getProperty("j:tipoBP"));

            //String testoHelpDesk = username;
            req.getSession().setAttribute("UTINFSESSION", user.getUsername());
        }

        stm.close();
        userJahiaDB.closeConnection(connection);

        WSReturnManager.evaluate(new WSReturn<String>(Constants.SUCCESS, this.getClass().getName(), "return" , new ArrayList<Object>() ), req.getSession());

        return new ActionResult(ActionResult.OK.getResultCode(),(session.getNodeByIdentifier(parameters.get("userredirectpage").get(0))).getPath());
    }
}
