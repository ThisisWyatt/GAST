package com.smart.demo.dispose;

import com.smart.demo.domain.A;

import java.math.BigDecimal;

/**
 * Description TODO
 * Author Cloudr
 * Date 2020/3/25 22:04
 **/
public class Aggregation {
    private static void search(int i, int j, BigDecimal value, A[][] a, int M, int N,BigDecimal intervalNum,A aSingle){

        if((i>=0&&i<M)&&(j>=0&&j<N)){
            int compare=((a[i][j].getNum().subtract(value)).abs()).compareTo(intervalNum);
            if( !a[i][j].equals(aSingle)  && (compare < 0 ||compare==0)  ){
                System.out.println(a[i][j]);
                a[i][j]=aSingle;
                search(i,j+1,value,a,M,N,intervalNum,aSingle);
                search(i+1,j,value,a,M,N,intervalNum,aSingle);
                search(i,j-1,value,a,M,N,intervalNum,aSingle);
                search(i-1,j,value,a,M,N,intervalNum,aSingle);
                search(i-1,j+1,value,a,M,N,intervalNum,aSingle);
                search(i+1,j+1,value,a,M,N,intervalNum,aSingle);
                search(i+1,j-1,value,a,M,N,intervalNum,aSingle);
                search(i-1,j-1,value,a,M,N,intervalNum,aSingle);
            }
        }
    }

    public static void main(String[] args){

        long currentTime=System.currentTimeMillis();

        A aSingle=new A(new BigDecimal(0),new BigDecimal(0),new BigDecimal(0));
        A[][] a=Sort_differenceAndNumber.setArrays();
        int M=a.length;
        int N=a[0].length;
        for(int i=0;i<M;++i){
            for(int j=0;j<N;++j){
                if(a[i][j]==null)
                    a[i][j]=aSingle;
            }
        }


        BigDecimal intervalNum=new BigDecimal(0.001);

        for(int i=0;i<M;++i){
            for(int j=0;j<N;++j){
                search(i,j,a[i][j].getNum(),a,M,N,intervalNum,aSingle);
            }
        }


        System.out.println("Aggregation cost: "+(System.currentTimeMillis()-currentTime)/1000);
    }

}
