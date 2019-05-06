import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;
import com.google.common.hash.PrimitiveSink;

import java.nio.charset.Charset;

public class TestBloomFilter {
    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {
            dealIdBloomFilter.put(String.valueOf(i));
        }
        for (int i = 1001; ; i++) {
            boolean isContained = dealIdBloomFilter.mightContain(String.valueOf(i));
            if (isContained) {
                System.out.println(i);
                break;
            }
        }
        dealIdBloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), 1000);

        boolean isContained = dealIdBloomFilter.mightContain(String.valueOf(1052));

        System.out.println(isContained);

    }

    private static BloomFilter<String> dealIdBloomFilter = BloomFilter.create(new Funnel<String>() {

        private static final long serialVersionUID = 1L;

        @Override
        public void funnel(String arg0, PrimitiveSink arg1) {

            arg1.putString(arg0, Charsets.UTF_8);
        }

    }, 1000);
}
