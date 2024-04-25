package com.example.talent3demoresepmakanan;

public class LiveCoding {
    public static void main(String[] args) {
        String name = "Wahyu Wiradarma";

        char ch;

        String unik ="";
        for(int i=0; i<name.length(); i++){
            ch = name.charAt(i);
            if(ch == 'a' || ch == 'u' || ch=='i'){
                unik+=ch;

            }

        }

        System.out.println(unik);

//
//        if(name.equals("a") && name.equals("i") && name.equals("u")){
//            System.out.println(name);
//        }



    }
}
