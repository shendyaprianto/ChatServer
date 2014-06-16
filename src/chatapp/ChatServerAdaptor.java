/**
 *
 * @author Suyash Mohan
 */
package chatapp;

import java.util.ArrayList;
import java.util.List;

import com.shephertz.app42.server.domain.Zone;
import com.shephertz.app42.server.idomain.BaseServerAdaptor;
import com.shephertz.app42.server.idomain.IUser;
import com.shephertz.app42.server.idomain.IZone;

public class ChatServerAdaptor extends BaseServerAdaptor{
	
	public static IZone zone;
public static List<IUser> users = new ArrayList<IUser>();
    @Override
    public void onZoneCreated(IZone _zone)
    {   
    	zone = _zone;
        //System.out.println("Zone Created " + zone.getName() + " with key " + zone.getAppKey());
        zone.setAdaptor(new ChatZoneAdaptor());
        
    }
    
    public static void removeUser(IUser user)
    {
    	 try
          {
             zone.removeUser(user);
          }
          catch(Exception ex)
          {
              
          }
 	
 //	users.remove(user);
   	 // System.out.println("User Removed : "+user.getName());
    
    }
}
