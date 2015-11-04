package DataAnaylse;

import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

/**
 * Created by liyan on 11/2/15.
 */
public class Repository {

    int repoId;
    Vector<Integer> vecUserId;
    HashMap<Integer, Date> mapUserDate;

    public int getRepoId(){
        return repoId;
    }

    public Vector<Integer> getVecUserId(){
        return vecUserId;
    }

    public HashMap<Integer, Date> getMapUserDate(){
        return mapUserDate;
    }

    public Repository(int inRepoId){
        repoId = inRepoId;
        vecUserId = new Vector<Integer>();
        mapUserDate = new HashMap<Integer, Date>();
    }

    public void addWatcher(int inUserId, Date inAddTime){
        vecUserId.add(inUserId);
        mapUserDate.put(inUserId, inAddTime);
    }
}
