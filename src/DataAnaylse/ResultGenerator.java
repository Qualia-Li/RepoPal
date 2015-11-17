package DataAnaylse;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by liyan on 11/17/15.
 */
public class ResultGenerator {

    public static double[] mUserRel;
    public static double[] mAuthorRel;
    public static double[][] mProductResult;
    public static int[][] mRepoRank;

    public static void loadNCalcData() throws IOException{
        BufferedReader mInUser = new BufferedReader(new FileReader(new File("data/test_data/user_relevance.txt")));
        BufferedReader mInAuthor = new BufferedReader(new FileReader(new File("data/test_data/author_relevance.txt")));
        String mUserBuf = null;
        String mAuthorBuf = null;
        mUserRel = new double[10000];
        mAuthorRel = new double[10000];
        mProductResult = new double[10000][10000];
        int i;

        i = 0;
        while(mInUser.ready() && mInAuthor.ready()){
            if((i+1) % 1000 == 0){
                System.out.println((i+1) + " rows loaded and calculated");
            }
            mUserBuf = mInUser.readLine();
            String[] mUserSplittedData = mUserBuf.trim().split(" ");
            mAuthorBuf = mInAuthor.readLine();
            String[] mAuthorSplittedData = mAuthorBuf.trim().split(" ");

            for(int j=0; j<10000; j++) {
                mUserRel[j] = Double.parseDouble(mUserSplittedData[j]);
                mAuthorRel[j] = Double.parseDouble(mAuthorSplittedData[j]);
                if(0 == mUserRel[j]){
                    mUserRel[j] = 1E-10;
                }
                if(0 == mAuthorRel[j]){
                    mAuthorRel[j] = 1E-10;
                }
                mProductResult[i][j] = mUserRel[j] * mAuthorRel[j];
            }
            i += 1;
        }
    }

    public static void sort(){
        mRepoRank = new int[10000][10];
        ValueComparator mComparator = new ValueComparator();
        for(int i=0; i<10000; i++)
        {
            List<ValueWithIndex> mValueVector = new ArrayList<>();
            for(int j=0; j<10000; j++) {
                ValueWithIndex mValueWithIndex = new ValueWithIndex(mProductResult[i][j], j);
                mValueVector.add(mValueWithIndex);
            }
            Collections.sort(mValueVector, mComparator);
            for(int j=0; j<10; j++){
                mRepoRank[i][j] = mValueVector.get(j).getIndex();
            }
            if((i+1) % 1000 == 0){
                System.out.println((i+1) + " vectors sorted");
            }
        }
    }

    public static void main(String[] args) throws IOException {

        loadNCalcData(); // load the data from files

//        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
//        FileOutputStream mFos = new FileOutputStream(new File("result/product_result_" + timeStamp + ".txt"));
//        for(int i=0; i<10000; i++){
//            if( (i+1) % 1000 == 0){
//                System.out.println((i+1) + " rows calculated");
//            }
//            for(int j=0; j<10000; j++){
//                mFos.write((mProductResult[i][j]+" ").getBytes());
//            }
//            mFos.write("\r\n".getBytes());
//        }

        sort(); // sort every vector with index

        String timeStamp2 = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        FileOutputStream mFos2 = new FileOutputStream(new File("result/sort_result_" + timeStamp2 + ".txt"));
        for(int i=0; i<10000; i++){
            for(int j=0; j<10; j++){
                mFos2.write((mRepoRank[i][j] + " ").getBytes());
            }
            mFos2.write("\r\n".getBytes());
        }
    }

}
