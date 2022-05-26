package lecture4.TCPServer;

import java.net.Socket;
import java.util.ArrayList;


public class arrays {

    private String note ;
    private Socket incoming;
    private int team ;
    private String NickName;

    public arrays(String note, Socket incoming, int team, String NickName) {
        this.note = note;
        this.incoming = incoming;
        this.team = team;
        this.NickName = NickName;

    }

    public String getNote() {
        return note;
    }



    public Socket getIncoming() {
        return incoming;
    }



    public int getTeam() {
        return team;
    }

    public String getNickName()
    {
        return NickName;
    }
    public void setNickName(String nick)
    {
        this.NickName = nick;
    }






}
