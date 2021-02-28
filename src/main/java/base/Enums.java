package base;

public class Enums {

    public enum PageTitle{
        LOGIN_PAGE_TITLE("Login - My Store"),
        MY_STORE_PAGE_TITLE("My account - My Store");

        public final String title;

        PageTitle(String title){
            this.title = title;
        }

        public String getTitle(){
            return title;
        }
    }
}
