package DataAnaylse;

import java.util.Comparator;
import java.util.Vector;

/**
 * Created by liyan on 11/17/15.
 */
class ValueWithIndex {

    public double value;
    public int index;

//    public static final Comparator<ValueWithIndex> VALUE_WITH_INDEX_COMPARATOR = new Comparator<ValueWithIndex>() {
//        @Override
//        public int compare(ValueWithIndex v1, ValueWithIndex v2) {
//            if(v1.getValue() > v2.getValue()){
//                return -1;
//            }
//            else if(v1.getValue() == v2.getValue()){
//                return 0;
//            }
//            else{
//                return 1;
//            }
//        }
//    };

    public ValueWithIndex(double inValue, int inIndex){
        value = inValue;
        index = inIndex;
    }

    public void setValue(double inValue){
        value = inValue;
    }

    public void setIndex(int inIndex){
        index = inIndex;
    }

    public double getValue(){
        return value;
    }

    public int getIndex(){
        return index;
    }
}

class ValueComparator implements Comparator<ValueWithIndex> {

    @Override
    public int compare(ValueWithIndex v1, ValueWithIndex v2){
        if(v1.getValue() > v2.getValue()){
            return -1;
        }
        else if(v1.getValue() == v2.getValue()){
            return 0;
        }
        else{
            return 1;
        }
    }
}