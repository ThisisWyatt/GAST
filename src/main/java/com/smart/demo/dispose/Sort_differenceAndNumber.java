package com.smart.demo.dispose;

import com.smart.demo.domain.A;
import com.smart.demo.test.GastJsonTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description TODO  按照Num Lat Lng的大小分别进行排序 输出相邻的差值 并统计相同差值的个数
 * Author Cloudr
 * Date 2020/3/25 0:33
 **/
public class Sort_differenceAndNumber {
    /**
     *@Descriptuion TODO 按照Num值排序
     **/
    private static void sortByDifferenceOfNum(){
        List<A> list2= GastJsonTest.readOriginalDate();
        A[] aArrays=new A[list2.size()];
        for(int i=0;i<list2.size();++i){
            aArrays[i]=list2.get(i);
        }

        A.SortByNum sortByNum=new A.SortByNum();
        Arrays.sort(aArrays,sortByNum);

        for(A a:aArrays){
            System.out.println(a);
        }

        Map<BigDecimal,Integer> map=new HashMap<>();
        for(int i=1;i<aArrays.length;++i){
            BigDecimal differenceLatValue=new BigDecimal(String.valueOf(aArrays[i].getNum().subtract(aArrays[i-1].getNum())));
            map.put(differenceLatValue,map.getOrDefault(differenceLatValue,0)+1);
        }

        System.out.println();
        System.out.println("sortByDifferenceOfNum---------------");
        for(Map.Entry<BigDecimal,Integer> entry:map.entrySet()){
            System.out.println("difference: "+entry.getKey()+" num="+entry.getValue());
        }

    }

    /**
     *@Descriptuion TODO 按照Lng值排序
     **/
    private static A[] sortByDifferenceOfLng(){
        List<A> list2=GastJsonTest.readOriginalDate();
        A[] aArrays=new A[list2.size()];
        for(int i=0;i<list2.size();++i){
            aArrays[i]=list2.get(i);
        }

        A.SortByLng sortByLng=new A.SortByLng();
        Arrays.sort(aArrays,sortByLng);

//        for(A a:aArrays){
//            System.out.println(a.getLng());
//        }


        Map<BigDecimal,Integer> map=new HashMap<>();
        for(int i=1;i<aArrays.length;++i){
            BigDecimal differenceLatValue=new BigDecimal(String.valueOf(aArrays[i].getLng().subtract(aArrays[i-1].getLng())));
            map.put(differenceLatValue,map.getOrDefault(differenceLatValue,0)+1);
        }

        System.out.println();
//        System.out.println("sortByDifferenceOfLng---------------");
//        for(Map.Entry<BigDecimal,Integer> entry:map.entrySet()){
//            System.out.println("difference: "+entry.getKey()+" num="+entry.getValue());
//        }
        return aArrays;

    }

    /**
     *@Descriptuion TODO 按照Lat值排序
     **/
    private static A[] sortByDifferenceOfLat(){
        List<A> list2=GastJsonTest.readOriginalDate();
        A[] aArrays=new A[list2.size()];
        for(int i=0;i<list2.size();++i){
            aArrays[i]=list2.get(i);
        }

        A.SortByLat sortByLat=new A.SortByLat();
        Arrays.sort(aArrays,sortByLat);

//        for(A a:aArrays){
//            System.out.println(a.getLat());
//        }


        Map<BigDecimal,Integer> map=new HashMap<>();
        for(int i=1;i<aArrays.length;++i){
            BigDecimal differenceLatValue=new BigDecimal(String.valueOf(aArrays[i].getLat().subtract(aArrays[i-1].getLat())));
            map.put(differenceLatValue,map.getOrDefault(differenceLatValue,0)+1);
        }

        System.out.println();
//        System.out.println("sortByDifferenceOfLat---------------");
//        for(Map.Entry<BigDecimal,Integer> entry:map.entrySet()){
//            System.out.println("difference: "+entry.getKey()+" num="+entry.getValue());
//        }

        return aArrays;
    }


    public static A[][] setArrays(){
        long currentTime=System.currentTimeMillis();

//        按照0.000028的间隔将lat值从小到大分成M份
        A[] sortByLat=sortByDifferenceOfLat();
        BigDecimal miniLat=sortByLat[0].getLat();
        BigDecimal intervalLat=new BigDecimal(String.valueOf(0.000028));
        BigDecimal bigDecimalM=( sortByLat[sortByLat.length-1].getLat().subtract(sortByLat[0].getLat()) )
                .divide(intervalLat,5, RoundingMode.HALF_UP);
        double doubleM=bigDecimalM.doubleValue();
        int M=(int)(Math.ceil(doubleM));
        System.out.println("M="+M);

//        按照0.000034的间隔将lng值从小到大分成N份
        A[] sortByLng=sortByDifferenceOfLng();
        BigDecimal miniLng=sortByLng[0].getLng();
        BigDecimal intervalLng=new BigDecimal(String.valueOf(0.000034));
        BigDecimal bigDecimalN=( sortByLng[sortByLng.length-1].getLng().subtract(sortByLng[0].getLng()) )
                .divide(intervalLng,5, RoundingMode.HALF_UP);
        double doubleN=bigDecimalN.doubleValue();
        int N=(int)(Math.ceil(doubleN));
        System.out.println("N="+N);


        System.out.println("-----------------------");


        List<A> AList= GastJsonTest.readOriginalDate();
        A[][] aArrays=new A[M+1][N+1];
        for(A a:AList){
            BigDecimal bigDecimalI=( a.getLat().subtract(miniLat) ).divide(intervalLat,5, RoundingMode.HALF_UP);
            double doubleI=bigDecimalI.doubleValue();
            int i0=(int)(Math.ceil(doubleI));
            BigDecimal bigDecimalJ=( a.getLng().subtract(miniLng) ).divide(intervalLng,5, RoundingMode.HALF_UP);
            double doubleJ=bigDecimalJ.doubleValue();
            int j0=(int)(Math.ceil(doubleJ));
//            System.out.println("i="+i0+"j="+j0);
            aArrays[i0][j0]=a;
        }

//        for(int i=0;i<M;++i){
//            for(int j=0;j<N;++j){
//                if(aArrays[i][j]!=null){
//                    System.out.println(aArrays[i][j]);
//                }
//            }
//        }


        System.out.println("-----------------------");
        System.out.println("setArrays cost: "+(System.currentTimeMillis()-currentTime)/1000+"s");

        return aArrays;
    }

    public static void main(String[] args){

        long all=System.currentTimeMillis();

        long currentTime=System.currentTimeMillis();
        sortByDifferenceOfLat();
        System.out.println("sortByDifferenceOfLat cost: "+(System.currentTimeMillis()-currentTime)/1000+"s");

        long currentTime2=System.currentTimeMillis();
        sortByDifferenceOfLng();
        System.out.println("sortByDifferenceOfLng cost: "+(System.currentTimeMillis()-currentTime2)/1000+"s");
        System.out.println("-------------------------------------------");

        System.out.println("sortByDifferenceOfLng cost: "+(System.currentTimeMillis()-all)/1000+"s");
    }
}
