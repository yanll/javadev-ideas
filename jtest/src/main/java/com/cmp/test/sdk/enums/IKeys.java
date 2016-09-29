package com.cmp.test.sdk.enums;

/**
 * Created by breez on 2016/01/08.
 */
public class IKeys {


    public enum Cache {


        IndexData(1, "首页数据") {
            @Override
            void doit() {

            }
        },
        HotWords(2, "热词") {
            @Override
            void doit() {

            }
        };

        private int key;
        private String memo;

        private Cache(int key, String memo) {
            this.key = key;
            this.memo = memo;
        }

        private int getKey() {
            return key;
        }

        public String getMemo() {
            return memo;
        }

        abstract void doit();


    }


}
