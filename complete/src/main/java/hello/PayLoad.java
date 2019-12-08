package hello;

public class PayLoad {

    public static String AddBook(String isbn, String aisle)
    {

        String payload = "{\n" +
                "\t\"name\": \"Learn to code Automation with Java\",\n" +
                "\t\"isbn\": \""+isbn+"\",\n" +
                "\t\"aisle\": \""+aisle+"\",\n" +
                "\t\"author\": \"assdgg\"\n" +
                "}";
        return payload;

    }


}
