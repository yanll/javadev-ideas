package com.yanll.framework.util.enums;

import java.util.Map;

public interface IEnum {


    /**
     * ID和数据库表b_index_data_def对应
     * E***：易推
     */
    public enum IndexDefinition {
        INDEX_LOOP(1L, "轮播"),
        INDEX_NAVI(2L, "导航");
        private Long value;
        private String desc;

        private IndexDefinition(Long value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public Long getValue() {
            return this.value;
        }

        public String getDesc() {
            return desc;
        }

        public static IndexDefinition getInstance(Long value) {
            IndexDefinition instance = null;
            for (IndexDefinition v : IndexDefinition.values()) {
                if (v.value == value) {
                    return v;
                }
            }
            return null;
        }
    }

    public enum YESNO {
        Y(1, "是"), N(0, "否");
        private Integer value;
        private String desc;

        private YESNO(Integer value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public Integer getValue() {
            return this.value;
        }

        public String getDesc() {
            return desc;
        }

    }

    public static void main(String[] args) {
        Map<Integer, String> map = EnumUtil.toMap(YESNO.class);

        System.out.println(EnumUtil.exists(100L, IndexDefinition.class));

    }

}


