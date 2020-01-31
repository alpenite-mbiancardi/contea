package org.jahia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.jcr.PathNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jahia.bin.ActionResult;
import org.jahia.bin.Action;
import org.jahia.bin.Jahia;
import org.jahia.params.ProcessingContext;
import org.jahia.registries.ServicesRegistry;
import org.jahia.services.content.JCRNodeWrapper;
import org.jahia.services.content.JCRSessionWrapper;
import org.jahia.services.mail.MailService;
import org.jahia.services.render.RenderContext;
import org.jahia.services.render.Resource;
import org.jahia.services.render.URLResolver;
import org.jahia.services.sites.JahiaSite;
import org.jahia.services.sites.JahiaSitesService;
import org.jahia.services.usermanager.JahiaGroup;
import org.jahia.services.usermanager.JahiaGroupManagerService;
import org.jahia.services.usermanager.JahiaUser;
import org.jahia.services.usermanager.JahiaUserManagerService;
import org.jahia.userregistration.utils.UserJahiaDB;
import org.json.JSONObject;
import org.slf4j.Logger;

import com.alpenite.tea.communicationLayer.WSReturnManager;
import com.alpenite.tea.communicationLayer.data.Constants;
import com.alpenite.tea.communicationLayer.data.WSReturn;


public class RecuperoUsernameAction extends Action {

    private MailService mailService;
    private static final String USERNAME_TEA = "USERNAME_TEA";
    private JahiaUserManagerService userManagerService;
    private transient static Logger logger = org.slf4j.LoggerFactory.getLogger(RecuperoUsernameAction.class);

    public void setUserManagerService(JahiaUserManagerService userManagerService) {
        this.userManagerService = userManagerService;
    }

    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    public ActionResult doExecute(HttpServletRequest req, RenderContext renderContext, Resource resource,
                                  JCRSessionWrapper session, Map<String, List<String>> parameters, URLResolver urlResolver) throws Exception {
        String mail = getParameter(parameters, "email");
        logger.info("mail " + mail);
        if (mail == null) {
            return ActionResult.BAD_REQUEST;
        }

        String group = parameters.get("group").get(0);

        List<String> usernames = new ArrayList<String>();

        //search list of username in db
		UserJahiaDB userJahiaDb = new UserJahiaDB();
		Connection connection = userJahiaDb.connectedDB();
        Statement stm = connection.createStatement();
        ResultSet rs = stm.executeQuery("" +
                "select user_registration.username " +
                "from user_registration left join user_group ug on user_registration.username = ug.username " +
                "where ug.group_name ='" + group + "' " +
                "and user_registration.email='" + mail + "' " +
                "and user_registration.j_accountLocked='false'"
        );
        if (rs.isBeforeFirst()) {
            logger.info("ALPENITE RecuperoUsernameAction - find users in db");
            while (rs.next()) {
                logger.info("ALPENITE RecuperoUsernameAction - add user '" + rs.getString("username") + "' in db");
                usernames.add(rs.getString("username"));
            }
        } else {
            logger.info("ALPENITE RecuperoUsernameAction - NOT find users in db, search in jahia users and jahia group");
            JahiaGroupManagerService groupManagerService = ServicesRegistry.getInstance().getJahiaGroupManagerService();
            int siteID = renderContext.getSite().getID();
            JahiaGroup TeaGroup = groupManagerService.lookupGroup(siteID, group);

            logger.info("group found");
            logger.info("group: " + TeaGroup.getName());
            List<String> userNameListToVerify = userManagerService.getUsernameList();
            Iterator<String> iterUserList = userNameListToVerify.iterator();


            while (iterUserList.hasNext()) {
                String currentUserResult = iterUserList.next();
                JahiaUser trovatoUser = userManagerService.lookupUser(currentUserResult);
                /*logger.info("user: "+trovatoUser.getUsername());*/
                if (TeaGroup.isMember(trovatoUser) && (trovatoUser.getProperty("j:email") != null && trovatoUser.getProperty("j:email").equalsIgnoreCase(mail)) && trovatoUser.getProperty("j:accountLocked").equals("false"))
                    usernames.add(trovatoUser.getUsername());

            }
        }

        stm.close();
        userJahiaDb.closeConnection(connection);

        if (usernames.size() != 0) {
            //invio mail
            logger.info("Usernames found");
            //String contenutoMail = req.getParameter("contenuto_mail");
            String contenutoMail = "";
            try {
                contenutoMail = session.getNodeByIdentifier(parameters.get("currentNode").get(0)).getNode("j:translation_" + parameters.get("lang").get(0)).getPropertyAsString("mail");
            } catch (PathNotFoundException e) {
                contenutoMail = session.getNodeByIdentifier(parameters.get("currentNode").get(0)).getNode("j:translation_en").getPropertyAsString("mail");
            }
            String mail_tea = contenutoMail.replaceAll(USERNAME_TEA, listOfUsernames(usernames));
            mailService.sendHtmlMessage(parameters.get("from").get(0), mail, null, null, parameters.get("mailSubject").get(0), mail_tea);
        }

        // message preparation
        WSReturnManager.evaluate(new WSReturn<String>(Constants.SUCCESS_REGISTRATION, this.getClass().getName(), "return", new ArrayList<String>()), req.getSession());
        // return the page
        return new ActionResult(HttpServletResponse.SC_ACCEPTED, (session.getNodeByIdentifier(parameters.get("userredirectpage").get(0))).getPath());

    }

    private String listOfUsernames(List<String> usernames) {
        String result = "";
        for (String s : usernames) {
            result += s + "; ";
        }
        return result;
    }

}
