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
import com.shephertz.app42.server.message.WarpResponseResultCode;

public class ChatZoneAdaptor extends BaseZoneAdaptor {
    @Override
    public void onAdminRoomAdded(IRoom room)
    {
        System.out.println("Room Created " + room.getName() + " with ID " + room.getId() );
        room.setAdaptor(new ChatRoomAdaptor());
        
    }  
    
    @Override  
    public void handleAddUserRequest(final IUser user, String authData, HandlingResult result){  
    	 System.out.println("User Requested : " + user.getName() + " with AUTH DATA : " + authData);
    
    	/* if(!ChatServerAdaptor.zone.getUsers().isEmpty())
    	 {
    	     	for(IUser userOnZone:ChatServerAdaptor.zone.getUsers())
            	{
            	//	if(userOnZone.getName().equalsIgnoreCase(user.getName()))
            	//	{
            	        System.out.println("User Connected : " + userOnZone.getName());    
            	
            	//	}
            		
            	}
    	
    	 
    	 }
        	 ChatServerAdaptor.removeUser(user);*/
        	 /*	new Thread(new Runnable() {  
                public void run() {                 
                 	for(IUser userOnZone:ChatServerAdaptor.zone.getUsers())
                    	{
                    	//	if(userOnZone.getName().equalsIgnoreCase(user.getName()))
                    	//	{
                    	        System.out.println("User Connected : " + userOnZone.getName());    
                    	
                    	//	}
                    		
                    	}
                    	 System.out.println("Total Users : " + String.valueOf(ChatServerAdaptor.zone.getUsers().size()));    
                    	
                        ChatServerAdaptor.zone.sendAddUserResponse(user, WarpResponseResultCode.SUCCESS, "Auth success on server");  
                                  
                }  
            }).start();  */
        	 result.code = WarpResponseResultCode.SUCCESS;  
    
    	 
    }  
    
  
    
    
}
