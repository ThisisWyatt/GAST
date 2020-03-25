package com.smart.demo.dispose;

import com.smart.demo.domain.A;

import java.math.BigDecimal;

/**
 * Description TODO
 * Author Cloudr
 * Date 2020/3/25 23:59
 **/
public class Aggregations {
    private static void search(int i,int j,BigDecimal value,A[][] a,int M,int N,BigDecimal intervalNum,A aZero){
        if((i>=0&&i<M)&&(j>=0&&j<N)){
            int compare=((a[i][j].getNum().subtract(value)).abs()).compareTo(intervalNum);
            if(!a[i][j].equals(aZero) && (compare < 0 ||compare==0)  ){
                System.out.println("--------------------------------");
                System.out.println("value="+value);
                System.out.println("a["+i+"]["+j+"].getNum()="+a[i][j].getNum());
                System.out.println("a[i][j].getNum().subtract(value)="+a[i][j].getNum().subtract(value));
                System.out.println("a[i][j].getNum().subtract(value).abs()="+a[i][j].getNum().subtract(value).abs());
                System.out.println( "((a[i][j].getNum().subtract(value)).abs())"+((a[i][j].getNum().subtract(value)).abs()) );
                System.out.println("--------------------------------");
//                System.out.println(a[i][j]);
                a[i][j]=aZero;
                search(i,j+1,value,a,M,N,intervalNum,aZero);
                search(i+1,j,value,a,M,N,intervalNum,aZero);
                search(i,j-1,value,a,M,N,intervalNum,aZero);
                search(i-1,j,value,a,M,N,intervalNum,aZero);
                search(i-1,j+1,value,a,M,N,intervalNum,aZero);
                search(i+1,j+1,value,a,M,N,intervalNum,aZero);
                search(i+1,j-1,value,a,M,N,intervalNum,aZero);
                search(i-1,j-1,value,a,M,N,intervalNum,aZero);
            }
        }
    }

    public static void main(String[] args){
        long currentTime=System.currentTimeMillis();

        A[][] a=Sort_differenceAndNumber.setArrays();
        int M=a.length;
        int N=a[1].length;

        BigDecimal intervalNum=new BigDecimal(0.1);

        A aZero=new A(new BigDecimal(0),new BigDecimal(0),new BigDecimal(0));
        for(int i=0;i<M;i++){
            for(int j=0;j<N;j++){
                if(a[i][j]==null){
                    a[i][j]=aZero;
                }
            }
        }

//        for(int j=0;j<N;++j){
//            if(a[0][j].equals(aZero))
//                System.out.println("为空");
//            else{
//                System.out.println("a[0]["+j+"]"+a[0][j]);
//            }
//        }
//        for(int i=0;i<M;++i){
//            if(a[i][0].equals(aZero))
//                System.out.println("为空");
//            else{
//                System.out.println("a["+i+"][0]"+a[i][0]);
//            }
//        }


        BigDecimal values=new BigDecimal(0.147289);

        for(int i=1;i<M;++i){
            for(int j=1;j<N;++j){
                search(i,j,a[i][j].getNum(),a,M,N,intervalNum,aZero);
            }
        }
        System.out.println("Time cost: "+(System.currentTimeMillis()-currentTime));
    }
}
