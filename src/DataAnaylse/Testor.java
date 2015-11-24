package DataAnaylse;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by liquanlai on 11/17/15.
 */
public class Testor {

    public static double[] mFileData;
//    public static double[] mAuthorRel;
    public static double[][] mProductResult;
    public static int[][] mRepoRank;

    public static void loadNCalcData() throws IOException{
    	System.out.println("Please input the beginning line and col:");
    	Scanner scn=new Scanner(System.in);
    	int lineBg=scn.nextInt();
    	int colBg=scn.nextInt();
    	System.out.println("Please input the total line and col:");
    	int lineTotal=scn.nextInt();
    	int colTotal=scn.nextInt();
    	System.out.println("Please input the file name:");
    	String filename=scn.nextLine();
        BufferedReader mInFile = new BufferedReader(new FileReader(new File("data/test_data/"+filename)));
//        BufferedReader mInAuthor = new BufferedReader(new FileReader(new File("data/test_data/author_relevance.txt")));
        String mFileBuffer = null;
//        String mAuthorBuf = null;
        mFileData = new double[10000];
//        mAuthorRel = new double[10000];
//        mProductResult = new double[10000][10000];
        int i;

        i = 0;
        while(mInFile.ready()){
            if((i+1) % 1000 == 0){
                System.out.println((i+1) + " rows loaded and calculated");
            }
            
            mFileBuffer = mInFile.readLine();
            if(i<lineBg){
            	i++;
            	continue;
            }
            if(i>=lineBg+lineTotal)
            	break;
            String[] mSplittedData = mFileBuffer.trim().split(" ");
            
            double max=0;
        	int maxIndex=0;
            for(int j=colBg;j<colBg+colTotal;j++){
            	
            	mFileData[j] = Double.parseDouble(mSplittedData[j]);
            	 if(mFileData[j]>max){
            		 maxIndex=j;
            		 max=mFileData[j];
            	 }
            	
            }
            System.out.printf("Line:%d,MostRelevant:%d,Relevance:%f\n",i,maxIndex,max);
          
            i++;
        }
        scn.nextLine();
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

//        String timeStamp2 = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        FileOutputStream mFos2 = new FileOutputStream(new File("result/sort_result.txt"));
        for(int i=0; i<10000; i++){
            for(int j=0; j<10; j++){
                mFos2.write((mRepoRank[i][j] + " ").getBytes());
            }
            mFos2.write("\r\n".getBytes());
        }
    }

}
