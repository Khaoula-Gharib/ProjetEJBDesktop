package test;

import java.util.Date;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import dao.IdaoRemote;
import entities.User;


public class Client {
	public static IdaoRemote<User> lookUp() throws NamingException {
		final Hashtable jndiProperties = new Hashtable();
		
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://127.0.0.1:8080");
		final Context context = new InitialContext(jndiProperties);

		return (IdaoRemote<User>) context.lookup("ejb:/ServerG/UserService!dao.IdaoRemote");
	}

	public static void main(String[] args) {
		try {
			IdaoRemote<User> service = lookUp();
			
//			User u3 = new User("test", "vnojhhgvm", "tebgbhcve", "egmail", new Date());
//			service.update(3); no
//			service.delete(2); yes
//			service.create(u3);
//			service.find... no
			for(User u : service.findAll()){
                            System.out.println(u);
                        }

		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
