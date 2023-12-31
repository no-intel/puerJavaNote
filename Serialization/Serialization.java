package Serialization;

public class Serialization {
    public static void main(String[] args) {
        //"{"name": "도라에몽","age": "54","weight": "129.3"}"
        String json = "{"+
                "\"name\": \"도라에몽\","+
                "\"age\": \"54\","+
                "\"weight\": \"129.3\""+
                "}";
        Converter converter = new Converter();
        //Deserialize
        Emong emong = converter.jsonToObject(Emong.class, json);
        System.out.println(emong.toString());

        //Serialize
        String toJson = converter.objectToJson(emong);
        System.out.println(toJson);
    }
}
