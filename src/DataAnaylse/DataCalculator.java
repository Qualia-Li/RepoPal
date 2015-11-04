package DataAnaylse;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Vector;

/**
 * Created by liyan on 11/2/15.
 */
public class DataCalculator {

    public static void main(String[] args) throws IOException, ParseException {
        int mSize = 10000;
        long mAdjustment = 3*60*60;

        Vector<Repository> mVecRepo = new Vector<Repository>();
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
                        result[i][k] = result[k][i] = result[i][k] + 1/(mDiff + mAdjustment);
                    }
                }
            }
        }

        for(int i=0; i<mSize; i++){
            for(int j=0; j<mSize; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }


}
