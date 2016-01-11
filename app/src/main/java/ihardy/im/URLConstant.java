package ihardy.im;

public class URLConstant {
    private static String TITLE = "http://im.ihardy.net/";
    private static String AGENT = "agent/";

    public static class USER {
        private static String USER = "User/";
        public static String LOGINGETSTATION = TITLE + AGENT + USER
                + "logingetstation";
        public static String USERDETAIL = TITLE + AGENT + USER + "userdetail";
        public static String GETPASSFIRST = TITLE + AGENT + USER
                + "getpassfirst";
        public static String GETPASSVAILIDECODE = TITLE + AGENT + USER
                + "getpassvalidecode";
        public static String GETPASSVALIDE = TITLE + AGENT + USER
                + "getpassvalide";
        public static String CHANGEPASS = TITLE + AGENT + USER + "changepass";

    }

}
