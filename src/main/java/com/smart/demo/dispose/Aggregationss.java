package com.smart.demo.dispose;

import com.smart.demo.domain.A;

import java.math.BigDecimal;

/**
 * Description TODO
 * Author Cloudr
 * Date 2020/3/26 0:30
 **/
public class Aggregationss {
    private static void search(int i, int j, BigDecimal value, A[][] a, int M, int N, BigDecimal intervalNum, A aZero){
        if((i>0&&i<M)&&(j>0&&j<N)){
            if(!a[i][j].equals(aZero) &&( ((a[i][j].getNum().subtract(value)).abs()).compareTo(intervalNum)<0 ||((a[i][j].getNum().subtract(value)).abs()).compareTo(intervalNum)==0 )){
                System.out.println("---------------------------------");
                System.out.println("value="+value);
                System.out.println("a["+i+"]["+j+"].getNum()="+a[i][j].getNum());
                System.out.println("a[i][j].getNum().subtract(value)="+a[i][j].getNum().subtract(value));
                System.out.println("a[i][j].getNum().subtract(value).abs()="+a[i][j].getNum().subtract(value).abs());
                System.out.println( "((a[i][j].getNum().subtract(value)).abs())"+((a[i][j].getNum().subtract(value)).abs()) );
                System.out.println(a[i][j]);
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



        A[][] a=new A[4][4];
        int M=4;
        int N=4;

        a[1][1]=new A(new BigDecimal(1),new BigDecimal(1),new BigDecimal(1));
        a[1][2]=new A(new BigDecimal(2),new BigDecimal(1),new BigDecimal(2));
        a[1][3]=new A(new BigDecimal(1),new BigDecimal(1),new BigDecimal(3));
        a[2][1]=new A(new BigDecimal(2),new BigDecimal(2),new BigDecimal(1));
        a[2][2]=new A(new BigDecimal(3),new BigDecimal(2),new BigDecimal(2));
        a[2][3]=new A(new BigDecimal(3),new BigDecimal(2),new BigDecimal(3));
        a[3][1]=new A(new BigDecimal(3),new BigDecimal(3),new BigDecimal(1));
        a[3][2]=new A(new BigDecimal(4),new BigDecimal(3),new BigDecimal(2));
        a[3][3]=new A(new BigDecimal(5),new BigDecimal(3),new BigDecimal(3));

        BigDecimal intervalNum=new BigDecimal(1);

        A aZero=new A(new BigDecimal(0),new BigDecimal(0),new BigDecimal(0));




        for(int i=1;i<M;++i){
            for(int j=1;j<N;++j){
               search(i,j,a[i][j].getNum(),a,M,N,intervalNum,aZero);
            }
        }

//        int ix=2;
//        int jx=2;
//
//        search(ix,jx,a[ix][jx].getNum(),a,M,N,intervalNum,aZero);

        System.out.println("Time cost: "+(System.currentTimeMillis()-currentTime));
    }
}
