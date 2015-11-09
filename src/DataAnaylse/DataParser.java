package DataAnaylse;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

/**
 * Created by liyan on 11/1/15.
 */

public class DataParser {

    public static void parse(Vector<Repository> mVecRepo) throws IOException, ParseException {
        BufferedReader mIn = new BufferedReader(new FileReader(new File("data/TopRepoData.csv")));
        String mBuf = null;
        SimpleDateFormat sdf = new SimpleDateFormat("\"yyyy-MM-dd HH:mm:ss\"");

        Repository mPreRepo = new Repository(-1);

        int i = 0;
        int mSize = 10000;
//        int mSize = 10;

        while(mIn.ready())
        {
            mBuf = mIn.readLine();
            String[] mSplittedData = mBuf.split(",");

            int mRepoId = Integer.parseInt(mSplittedData[0]);
            int mUserId = Integer.parseInt(mSplittedData[1]);
            Date mDate = sdf.parse(mSplittedData[2]);

            if(mRepoId != mPreRepo.getRepoId()){
                Repository mRepo = new Repository(mRepoId);
                if(mPreRepo.getRepoId() != -1){
                    mVecRepo.add(mPreRepo);
                }
                mPreRepo = mRepo;
                i += 1;
                if(i % 100 == 0)
                    System.out.println(i+ " repos loaded.");
            }
            mPreRepo.addWatcher(mUserId, mDate);

            if(i>mSize)
                break;
        }

    }

}
