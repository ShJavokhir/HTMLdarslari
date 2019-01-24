//Darslar ro'yhatini saqlovchi class
package uz.dasturlash.html;

import java.util.Arrays;

public class GetLessonList {
    String[] id1 = {"HTML ga kirish","Asosiy teglar","DEMO"};
    String[] id2 = {"DEMO","DEMO","DEMO","DEMO","DEMO"};
    String[] id3 = {"DEMO","DEMO","DEMO","DEMO","DEMO"};



    public String[] GetLessonList(int id){

        int sanoq=0;
        if(id==1){
            sanoq = id1.length;
        }else if(id==2){
            sanoq = id2.length;
        }else if(id==3){
            sanoq = id3.length;
        }
        String[] temp = new String[sanoq];
        if(id==1){
            temp = Arrays.copyOf(id1,id1.length);
        }else if(id==2){
            temp = Arrays.copyOf(id2,id2.length);
        }else if(id==3){
            temp = Arrays.copyOf(id3,id3.length);
        }
        return( temp );
    }
}
