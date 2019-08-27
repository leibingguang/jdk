import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class TestConf {
    String s = "[{\"tsgpid\":\"80020000000000000126013\",\"readpId\":\"2081152\"},{\"tsgpid\":\"80020000000000000126014\",\"readpId\":\"2067154\"}]";

    public static void main(String[] args) {
        TestConf testConf = new TestConf();
        testConf.getProductId("sdf");
    }

    private String getProductId(String tsgPid) {
        List<TsgChildProductEntity> tsgChildProductEntityList = JSONObject.parseArray(s, TsgChildProductEntity.class);

        System.out.println(tsgChildProductEntityList);
        return null;
    }

    /**
     * tsg产品id和本地产品id映射实体类
     */
    static class TsgChildProductEntity {
        public String tsgpid;
        public String readpId;

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("TsgChildProductEntity{");
            sb.append("tsgpid='").append(tsgpid).append('\'');
            sb.append(", readpId='").append(readpId).append('\'');
            sb.append('}');
            return sb.toString();
        }

    }
}
