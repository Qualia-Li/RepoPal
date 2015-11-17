package DataAnaylse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

/**
 * Created by liyan on 11/2/15.
 */
public class DataCalculator {

    public static void calculate() throws IOException, ParseException {
        int mSize = 10000;
        long mAdjustment = 3*60*60;
//        int mSize = 10;
//        long mAdjustment = 3600;

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        FileOutputStream mFos = new FileOutputStream(new File("result/result_" + timeStamp + ".txt"));

        Vector<Repository> mVecRepo = new Vector<>();
        double[][] result = new double[mSize][mSize];

        for(int i=0; i<mSize; i++){
            for(int j=0; j<mSize; j++) {
                result[i][j] = 0;
            }
        }

        Date mDateBegin = new Date();

        DataParser.parse(mVecRepo);

        for(int i=0; i<mVecRepo.size(); i++){
            System.out.println(i + " repos calculated");
            if(i % 10 == 0){
                Date mDateEnd = new Date();
                long mPeriod = Math.abs(mDateBegin.getTime() - mDateEnd.getTime()) / 1000;
                System.out.println("This program has been running for " + mPeriod + " seconds");
            }

            for(int j=0; j<mVecRepo.get(i).getVecUserId().size(); j++){
                int mUserId = mVecRepo.get(i).getVecUserId().get(j);
                Date mTime1 = mVecRepo.get(i).getMapUserDate().get(mUserId);
                for(int k=i+1; k<mVecRepo.size(); k++){
                    if(mVecRepo.get(k).getMapUserDate().containsKey(mUserId)) {
                        Date mTime2 = mVecRepo.get(k).getMapUserDate().get(mUserId);
                        long mDiff = Math.abs(mTime1.getTime() - mTime2.getTime()) / 1000;
                        result[i][k] = result[k][i] = result[i][k] + 1.0/(mDiff + mAdjustment);
                    }
                }
            }
        }

        for(int i=0; i<mSize; i++){
            for(int j=0; j<mSize; j++) {
                mFos.write((result[i][j] + " ").getBytes());
            }
            mFos.write("\r\n".getBytes());
        }
    }


}
