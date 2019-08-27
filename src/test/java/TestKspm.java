import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class TestKspm {
    public static void main(String[] args) {
        String productGroupRelationStr = "[{\"pid\":\"1917145\",\"gid\":\"711077\",\"type\":\"0\"},{\"pid\":\"1781148\",\"gid\":\"357404\",\"type\":\"1\"},{\"pid\":\"14711\",\"gid\":\"477975\",\"type\":\"2\"}]";
        List<ProductGroupRelationEntity> productGroupRelationEntityList = JSONObject.parseArray(productGroupRelationStr, ProductGroupRelationEntity.class);
        System.out.println(productGroupRelationEntityList);

    }


    static class ProductGroupRelationEntity {
        public String pid;
        public String gid;
        public String type;

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("ProductGroupRelationEntity{");
            sb.append("pid='").append(pid).append('\'');
            sb.append(", gid='").append(gid).append('\'');
            sb.append(", type='").append(type).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

}
