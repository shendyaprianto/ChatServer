/**
 *
 * @author Suyash Mohan
 */
package chatapp;

import java.util.Collection;
import java.util.HashMap;

import com.shephertz.app42.server.idomain.BaseRoomAdaptor;
import com.shephertz.app42.server.idomain.BaseZoneAdaptor;
import com.shephertz.app42.server.idomain.HandlingResult;
import com.shephertz.app42.server.idomain.IRoom;
import com.shephertz.app42.server.idomain.IUser;
import com.shephertz.app42.server.idomain.IZone;

public class ChatRoomAdaptor extends BaseRoomAdaptor implements IZone{
    
    private String[] blacklist = {"fuck", "shit", "asshole", "cunt", "fag", "fuk", "fck", "fcuk", "assfuck", "assfucker", "fucker",
                                "motherfucker", "asscock", "asshead", "asslicker", "asslick", "assnigger", "nigger", "asssucker", "bastard", "bitch", "bitchtits",
                                "bitches", "bitch", "brotherfucker", "bullshit", "bumblefuck", "buttfucka", "fucka", "buttfucker", "buttfucka", "fagbag", "fagfucker",
                                "faggit", "faggot", "faggotcock", "fagtard", "fatass", "fuckoff", "fuckstick", "fucktard", "fuckwad", "fuckwit", "dick",
                                "dickfuck", "dickhead", "dickjuice", "dickmilk", "doochbag", "douchebag", "douche", "dickweed", "dyke", "dumbass", "dumass",
                                "fuckboy", "fuckbag", "gayass", "gayfuck", "gaylord", "gaytard", "nigga", "niggers", "niglet", "paki", "piss", "prick", "pussy",
                                "poontang", "poonany", "porchmonkey","porch monkey", "poon", "queer", "queerbait", "queerhole", "queef", "renob", "rimjob", "ruski",
                                "sandnigger", "sand nigger", "schlong", "shitass", "shitbag", "shitbagger", "shitbreath", "chinc", "carpetmuncher", "chink", "choad", "clitface"
                                , "clusterfuck", "cockass", "cockbite", "cockface", "skank", "skeet", "skullfuck", "slut", "slutbag", "splooge", "twatlips", "twat",
                                "twats", "twatwaffle", "vaj", "vajayjay", "va-j-j", "wank", "wankjob", "wetback", "whore", "whorebag", "whoreface"};
                                
    @Override
    public void onUserLeaveRequest(IUser user){
       // System.out.println(user.getName() + " left room " + user.getLocation().getId());
    }

    @Override
    public void handleUserJoinRequest(IUser user, HandlingResult result){
        System.out.println(user.getName() + " joined room " );
        
    	// user.SendChatNotification("Admin", "gw kick lu" , user.getLocation());
        //removeUser(user);
       
    }
    
    @Override
    public void handleChatRequest(IUser sender, String message, HandlingResult result)
    {
     //   System.out.println(sender.getName() + " says " + message);
        for(String word:blacklist)
        {
        	
            if(message.indexOf(word) != -1)
            {
                sender.SendChatNotification("Admin", "jgn ngomong kasar" , sender.getLocation());
                result.code = 1;
                result.description = "Bad Words Used";
                result.sendResponse = true;
                result.sendNotification = false;
                
                removeUser(sender);
            }
        }
    }

	public IRoom createRoom(String arg0, int arg1, HashMap<String, Object> arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean deleteRoom(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public String getAppKey() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<IRoom> getRooms() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getSecret() {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<IUser> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	public void removeUser(IUser arg0) {
		System.out.println(arg0.getName() + " kicked from room " );
		
	}

	public void sendAddUserResponse(IUser arg0, byte arg1, String arg2) {
		// TODO Auto-generated method stub
		
	}

	public void setAdaptor(BaseZoneAdaptor arg0) {
		// TODO Auto-generated method stub
		
	}
}
