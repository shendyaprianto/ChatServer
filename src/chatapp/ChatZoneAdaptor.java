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
         
          ChatServerAdaptor.removeUser(user);
          System.out.println("User Removed By User : " + user.getName() +" ("+String.valueOf(ChatServerAdaptor.users.size())+")");
        /*  Iterator<IUser> iter = ChatServerAdaptor.users.iterator();
                	        while(iter.hasNext()) {
                        		IUser userOnZone = iter.next();
                        		if(userOnZone.equals(user))
                        		{
                                    iter.remove();
                        	      
                        		}
                        	}*/
    }
    @Override  
    public void handleAddUserRequest(final IUser user, String authData, HandlingResult result){  
    	 System.out.println("User Requested : " + user.getName() + " with AUTH DATA : " + authData );
    	user.setCustomData(authData);
        	 	new Thread(new Runnable() {  
                public void run() {      
                    
                            Iterator<IUser> iter = ChatServerAdaptor.users.iterator();
                	        while(iter.hasNext()) {
                        		IUser userOnZone = iter.next();
                        		if(userOnZone.getName().equalsIgnoreCase(user.getName()))
                        		{
                        		    ChatServerAdaptor.zone.sendAddUserResponse(userOnZone, WarpResponseResultCode.CONNECTION_ERR, "Auth Error Recoverable");  
                        	        ChatServerAdaptor.removeUser(userOnZone);
                        	        iter.remove();
                        	      
                        		}
                        	}
                        	
                    //	 System.out.println("Total Users : " + String.valueOf(ChatServerAdaptor.users.size()));    
                    //	
                        ChatServerAdaptor.zone.sendAddUserResponse(user, WarpResponseResultCode.SUCCESS, "Auth success on server");  
                        ChatServerAdaptor.users.add(user);    
               
                }  
            }).start();  
        	 result.code = WarpResponseResultCode.AUTH_PENDING;  
        //	  System.out.println("User PENDING");
    	 //
    }  
    
     @Override  
    public void handleResumeUserRequest(IUser user, String authData, HandlingResult result)  
    {
         System.out.println("User Requested Resume Connection : " + user.getName() + " with AUTH DATA : " + authData );
        
    }
    
     @Override  
    public void onUserPaused(IUser user)  
    {
         System.out.println("User Paused : " + user.getName() + " with AUTH DATA : " + user.getCustomData() );
        
        
    }
  
    
    
}
