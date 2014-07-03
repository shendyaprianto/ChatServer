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

import java.util.Iterator;
public class ChatZoneAdaptor extends BaseZoneAdaptor {
    @Override
    public void onAdminRoomAdded(IRoom room)
    {
        System.out.println("Room Created " + room.getName() + " with ID " + room.getId() );
        room.setAdaptor(new ChatRoomAdaptor());
        
    }  
    
    @Override
    public void onUserRemoved(IUser user)  
    {
         
       
        System.out.println("User Removed By User : " + user.getName() +" ("+String.valueOf(ChatServerAdaptor.users.size())+")");
    }
    @Override  
    public void handleAddUserRequest(final IUser user, String authData, HandlingResult result){  
    	 System.out.println("User Requested : " + user.getName() + " in Zone : " + ChatServerAdaptor.zone.getName() );
    	user.setCustomData(authData);
        	 	new Thread(new Runnable() {  
                public void run() {      
                    	boolean dupe=false;
                    	IUser userToRemove;
                            Iterator<IUser> iter = ChatServerAdaptor.zone.getUsers().iterator();
                	        while(iter.hasNext()) {
                        		IUser userOnZone = iter.next();
                        	
                        		if(userOnZone.getName().equalsIgnoreCase(user.getName()))
                        		{
                        		    ChatServerAdaptor.zone.sendAddUserResponse(userOnZone, WarpResponseResultCode.CONNECTION_ERR, "Auth Error Recoverable");  
                            		  dupe=true;
                            		  userToRemove = userOnZone;
                        	        break;  
                        		}
                        		
                        	}
                        	if(dupe)
                        	    ChatServerAdaptor.zone.removeUser(userToRemove);
                             ChatServerAdaptor.zone.sendAddUserResponse(user, WarpResponseResultCode.SUCCESS, "Auth success on server");  
                       
               
                }  
            }).start();  
        	 result.code = WarpResponseResultCode.AUTH_PENDING;  
    }  
    
     @Override  
    public void handleResumeUserRequest(IUser user, String authData, HandlingResult result)  
    {
             System.out.println("User Requested Resume Connection : " + user.getName() + " with AUTH DATA : " + authData );
          ChatServerAdaptor.zone.sendAddUserResponse(user, WarpResponseResultCode.SUCCESS_RECOVERED, "Recoverred Success");  
       
        
    }
    
     @Override  
    public void onUserPaused(IUser user)  
    {
        System.out.println("User Paused : " + user.getName() + " with AUTH DATA : " + user.getCustomData() );
         ChatServerAdaptor.zone.sendAddUserResponse(user, WarpResponseResultCode.CONNECTION_ERROR_RECOVERABLE, "Waiting for recover");  
        
    }
  
    
    
}
