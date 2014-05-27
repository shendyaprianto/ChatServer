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
    public void onZoneCreated(IZone _zone)
    {   
    	this.zone = zone;
        System.out.println("Zone Created " + _zone.getName() + " with key " + _zone.getAppKey());
        _zone.setAdaptor(new ChatZoneAdaptor());
    }
    
    public static void removeUser(IUser user)
    {
    	zone.removeUser(user);
    
    }
}
