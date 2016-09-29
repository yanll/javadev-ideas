package com.cmp.test.sdk.enums;

/**
 * Created by YLL on 2015/08/03.
 */
public enum BastDictEnum {


    A("dd"){
        @Override
        void doit() {

        }
    };
    private String s;


    private BastDictEnum(String s){
        this.s= s;
    }

    abstract void doit();



}