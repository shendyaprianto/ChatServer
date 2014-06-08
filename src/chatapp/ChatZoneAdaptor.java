/**
 *
 * @author Suyash Mohan
 */
package chatapp;

import com.shephertz.app42.server.domain.Room;
import com.shephertz.app42.server.idomain.BaseZoneAdaptor;
import com.shephertz.app42.server.idomain.HandlingResult;
import com.shephertz.app42.server.idomain.IUser;
import com.shephertz.app42.server.idomain.IZone;
import com.shephertz.app42.server.idomain.IRoom;

public class ChatZoneAdaptor extends BaseZoneAdaptor {
    @Override
    public void onAdminRoomAdded(IRoom room)
    {
        System.out.println("Room Created " + room.getName() + " with ID " + room.getId() );
        room.setAdaptor(new ChatRoomAdaptor());
        
    }  
    
    @Override  
    public void handleAddUserRequest(IUser user, String authData, HandlingResult result){  
    	 System.out.println("User Requested : " + user.getName() + " with AUTH DATA : " + authData);
    	for(IUser userOnZone:ChatServerAdaptor.zone.getUsers())
    	{
    		if(userOnZone.getName().equalsIgnoreCase(user.getName()))
    		{
    		    
    		//	ChatServerAdaptor.removeUser(user);
    		break;
    		}
    		
    	}
    	
    	 
    }  
    
  
    
    
}
