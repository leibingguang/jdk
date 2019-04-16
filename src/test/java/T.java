public class T {
    public static void main(String[] args) throws Exception {
        Response response = new Response();
        response.setResultCode("366112");
        int count = 0;
        for (int i = 0; i < 3; i++) {
            count++;
            System.out.println("execute times " + (i + 1));
//            System.out.println(String.valueOf(366112).equals(response.getResultCode()));
//            System.out.println(String.valueOf(366119).equals(response.getResultCode()));
            boolean flag = String.valueOf(366112).equals(response.getResultCode())
                    || String.valueOf(366119).equals(response.getResultCode());
            System.out.println(flag);
            if (count == 3
                    ||
                    (null != response && !flag)) {
                System.out.println("i = " + i + " cancle");
                break;
            }
        }
    }
}
