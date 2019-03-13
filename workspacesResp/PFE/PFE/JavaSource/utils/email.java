/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author ASUS
 */
public class email {
    
    
    
        final String username="trabelsisofien9@gmail.com" ;
	final String password="hendaadriss";
	public boolean internet(){boolean internet=false;
		try{
	            URL url=new URL("http://www.google.com");
		    URLConnection connection = url.openConnection();
	        connection.connect();  
	        System.out.println("connecté");
	        internet=true;
	       
		}catch(Exception e){
			System.out.println("non connecté");
		}
		return internet ;
	}
    
    
    
    
    private String from;
    private String subject;
    private String to;
    private String content;

    public email() {
    }

    public email(String from, String to,String subject, String content) {
        this.from = from;
        this.subject = subject;
        this.to = to;
        this.content = content;
    }

    public String getFrom() {
        return from;
    }

    public String getSubject() {
        return subject;
    }

    public String getTo() {
        return to;
    }

    public String getContent() {
        return content;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    
   public void envoyerDec(String aut,String desc,String mail){

	Properties props=new Properties();
	 props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.port", "587");
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	    
	    Session session=Session.getInstance(props,
	    		new Authenticator(){
	    	protected PasswordAuthentication getPasswordAuthentication(){
	    	return new PasswordAuthentication(username,password);
	    }}
	    );
	    try{
	    	Message message=new MimeMessage(session);
	    	message.setFrom(new InternetAddress(username));
	    	message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
	    	
	    	message.setSubject("Activation de compte ");
	    	message.setContent("<h:body>"+"<h1>bienvenue a attijari recrutement </h1><b> "+aut+"</b>"+desc+"</body>","text/html ; charset=utf-8");
	    	Transport.send(message);
	    	
	    }catch(MessagingException e){
	    	throw new RuntimeException(e);
             
	    }
}

    
}
