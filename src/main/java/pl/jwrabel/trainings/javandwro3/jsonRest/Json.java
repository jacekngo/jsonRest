package pl.jwrabel.trainings.javandwro3.jsonRest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by jakubwrabel on 25/05/2017.
 */
public class Json {
	public static void main(String[] args) throws IOException {
		{
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.enable(SerializationFeature.INDENT_OUTPUT);


			// ZAMIANA OBIEKTU NA JSON (String)
			Point point = new Point();
			point.setX(100);
			point.setY(300);


			String pointJson = objectMapper.writeValueAsString(point);
			System.out.println(pointJson);


			// ZAPIS JSONA DO PLIKU
			objectMapper.writeValue(new File("points.json"), point);

			List<Point> pointList = new ArrayList<>();
			pointList.add(new Point());
			pointList.add(new Point());
			pointList.add(new Point());
			objectMapper.writeValue(new File("pointsList.json"), pointList);


			// ZAMIANA JSONa (Stringa, napisu) na obiekt Javovy
			String pointsJsonString = "{\n" +
					"  \"x\" : 100,\n" +
					"  \"y\" : 300\n" +
					"}";

			Point point1 = objectMapper.readValue(pointsJsonString, Point.class);

			// ZAMIANA JSONA Z PLIKU NA OBIEKT
			Point point2 = objectMapper.readValue(new File("points.json"), Point.class);
			List<Point> pointsListFromFile = objectMapper.readValue(new File("pointsList.json"), List.class);

			// UWAGA
			// 1. musimy mieć gettery i settery
			// 2. musimy mieć konstruktor bezparametrowy

			// 1. Stworzyć klasę odwzorowującą poniższego JSONA
//		Customer/Person
			String jsonString = "{\"imie\":\"Adam\",\"nazwisko\":\"Kowalski\",\"birthYear\":1980,\"idNumber\":\"ABC\"}";

			// zamiana ze Stringa
			Customer customer = objectMapper.readValue(jsonString, Customer.class);
//			Customer customer = objectMapper.readValue(new File("customer.json"), Customer.class);
			System.out.println("--- przeczytany customer ---");
			System.out.println(customer);

//			// zamiasna JSONa na listę obiektów klasy Point
			List<Point> list = objectMapper.readValue(new File("pointsList.json"),
					TypeFactory.defaultInstance().constructCollectionType(List.class, Point.class));


			// zamiana JSONa place.json na obiekt klasy Place
			Place place = objectMapper.readValue(new File("place.json"), Place.class);
			System.out.println(place);

			// ustawienie objectMapperowi zachowania - ignoruj nieznane
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			Weather weather = objectMapper.readValue(new File("weather.json"), Weather.class);
			System.out.println(weather);

			// mapowanie na pole o innej nazwie
			// adnotacja nad polem @JsonProperty(value = "date_a")

			Customer customer1 = new Customer();
			customer1.setLastName("Kowalski");
			customer1.setFirstName("Adam");
			String customerAsJson = objectMapper.writeValueAsString(customer1);
			System.out.println(customerAsJson);


			// ignorowanie nieznanych pól tylko dla jednej klasy
			// adnotacja nad klasą
			//	@JsonIgnoreProperties(ignoreUnknown = true)

			// ignorowanie pola przy zamianie na JSON
			// adnotacja nad polem
			//		@JsonIgnore


			// format daty
//		@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm a z")
//		private Date date;

			Person person = new Person("Jan B", new Date());
			String personAsJson = objectMapper.writeValueAsString(person);
			System.out.println(personAsJson);
		}
	}
}
