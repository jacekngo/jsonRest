package pl.jwrabel.trainings.javandwro3.jsonRest.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by jakubwrabel on 29/05/2017.
 */
public class ServerClient {
    public static void main(String[] args) throws UnirestException {
        setupUnirest();
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("===== MENU ====");
            System.out.println("1. Wyświetl wszystkich klientów");
            System.out.println("2. Wyświetl dane jednego klienta");
            System.out.println("3. Stwórz nowego klienta");
            System.out.println("4. Zaktualizuj dane klienta");
            System.out.println("5. Usuń klienta");
            System.out.println("0. Zakończ program");
            System.out.println("\nPodaj kod operacji: ");

            String operationString = new Scanner(System.in).nextLine();

            switch (operationString) {
                case "0":
                    System.exit(0);

                case "1":
                    showAll(); break;

                case "2":
                    System.out.println("podaj id klienta: ");
                    String tmp = input.nextLine();
                    getOne(tmp); break;

                case "3":
                    System.out.println("podaj id klienta: ");
                    String id = input.nextLine();
                    System.out.println("podaj imię klienta: ");
                    String fName = input.nextLine();
                    System.out.println("podaj nazwisko klienta: ");
                    String lName = input.nextLine();
                    System.out.println("podaj rok urodzenia: ");
                    String bYear = input.nextLine();
                    System.out.println("podaj wzrost ");
                    Double height = input.nextDouble();
                    Customer customer = new Customer(fName,lName, bYear, height,id );
                    String postResponse = Unirest
                            .post("http://195.181.209.160:8080/api/v1/customers")
                            .header("Content-Type", "application/json")
                            .body(customer)
                            .asString()
                            .getBody();
                    System.out.println(postResponse);
                     break;
                case "4":
                    System.out.println("podaj id klienta: ");
                    String idC = input.nextLine();
                    Customer customerToChange = Unirest.get("http://195.181.209.160:8080/api/v1/customers/" + idC).asObject(Customer.class).getBody();


                default:
                    System.out.println("Niepoprawny kod operacji");

            }
        }
    }

    private static void cChange(Customer x)  {
        System.out.println("Jakie dane chcesz zmienić");
        System.out.println("1: Imię");
        System.out.println("2: Nazwisko");
        System.out.println("3. Rok urodzenia");
        System.out.println("4. wzrost");
        Scanner scanner = new Scanner(System.in);
        int yourChoice = scanner.nextInt();
        switch (yourChoice)



    }
        private static void getOne(String id) throws UnirestException {
       {Unirest.get("http://195.181.209.160:8080/api/v1/customers/" + id)
                    .asString().getBody();}
        }


    private static void showAll() throws UnirestException {
        String customersJson = Unirest.get("http://195.181.209.160:8080/api/v1/customers").asString().getBody();
        System.out.println( customersJson.replace("},{", "\n"));
    }

    private static void setupUnirest() {
        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}