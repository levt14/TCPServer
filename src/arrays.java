import java.net.Socket;

public class arrays {

	private String note ; 
	private Socket incoming;
	private int team ;
	private String nickname;
	
	arrays(String note , Socket incoming , int team, String nickname )
	{
		this.note = note;
		this.incoming = incoming;
		this.team = team ; 
		this.nickname = nickname;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	

	public String getNote() {
		return note;
	}

	public void setTeam(int team)
	{
		this.team = team;
	}

	public Socket getIncoming() {
		return incoming;
	}

	

	public int getTeam() {
		return team;
	}


}
