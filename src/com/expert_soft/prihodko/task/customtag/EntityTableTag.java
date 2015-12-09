package com.expert_soft.prihodko.task.customtag;

import com.expert_soft.prihodko.task.entity.Contact;
import org.apache.log4j.Level;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class EntityTableTag extends TagSupport {
    public final static String LOCALE = "com.expert_soft.prihodko.task/localization.locale";
    private final static Logger logger = Logger.getLogger("EntityTableTag");
    private Collection collection;
    private String lang;

    public void setCollection(Collection collection){
        this.collection=collection;
    }

    public Collection getCollection(){
        return  collection;
    }

    public void setLang(String lang){
        this.lang=lang;
    }
    public String getLang(){return lang;}
    @Override
    public int doStartTag() throws JspTagException {
        int size = collection.size();
        String language = getLang().substring(0, 2);
        String country = getLang().substring(3,5);
        Locale locale = new Locale(language,country);
        ResourceBundle resourceBundle = ResourceBundle.getBundle(LOCALE, locale);
        try{
            JspWriter out = pageContext.getOut();
            if(size==0){
                String noRecords = resourceBundle.getString("locale.message.NoRecords");
                out.write("<h4>"+noRecords+"</h4>");
            }
            else {
                out.write("<table id=\"myTable\" align =\"center\"  class=\"tablesorter\" class=\"table table-bordered table-striped\">");

                String name = resourceBundle.getString("locale.message.Name");
                String surname = resourceBundle.getString("locale.message.Surname");
                String login = resourceBundle.getString("locale.message.Login");
                String email = resourceBundle.getString("locale.message.Email");
                String phone = resourceBundle.getString("locale.message.Phone");
                out.write("<thead class=\"text-center\"><tr>" +
                        "<th>"+name+"</th>" +
                        "<th>"+surname+"</th>"+
                        "<th>"+login+"</th>"+
                        "<th>"+email+"</th>"+
                        "<th>"+phone+"</th>" +
                        "</tr></thead>");
                out.write("<tbody>");
                Iterator<Contact> iterator = collection.iterator();
                Contact contact = null;
                while(iterator.hasNext()){
                    contact=iterator.next();
                    out.write("<tr>"+contact.toString()+"</tr>");
                }
            }
            out.write("</tbody>");
            out.write("</table>");

        }
        catch (IOException e) {
            logger.info(Level.ERROR+" EntityTaleTag has problem "+e);
        }
        return SKIP_BODY;
    }

}
