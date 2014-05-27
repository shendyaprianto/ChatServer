/**
 *
 * @author Suyash Mohan
 */
package chatapp;

import com.shephertz.app42.server.domain.Zone;
import com.shephertz.app42.server.idomain.BaseServerAdaptor;
import com.shephertz.app42.server.idomain.IUser;
import com.shephertz.app42.server.idomain.IZone;

public class ChatServerAdaptor extends BaseServerAdaptor{
	
	public static IZone zone;
    @Override
    public void onZoneCreated(IZone zone)
    {   
    	this.zone = zone;
        System.out.println("Zone Created " + zone.getName() + " with key " + zone.getAppKey());
        zone.setAdaptor(new ChatZoneAdaptor());
    }
    
    public static void removeUser(IUser user)
    {
    	zone.removeUser(user);
    
    }
}
