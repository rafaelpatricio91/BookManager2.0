package util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class AddMensagem
{
	public static void addMsgINFO(FacesMessage msg)
	{
		FacesContext fc = FacesContext.getCurrentInstance();
		
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		fc.addMessage(null, msg);		
	}
	
	public static void addMsgERRO(FacesMessage msg)
	{
		FacesContext fc = FacesContext.getCurrentInstance();
		
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		fc.addMessage(null, msg);		
	}
}
