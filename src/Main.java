import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<City> cities = readCitiesFromFile("cities.txt");

        if (cities != null) {
            cities.sort(Comparator.comparing((City c) -> c.name.toLowerCase()).reversed());

            System.out.println("Сортировка по наименованию:");
            cities.forEach(System.out::println);
            System.out.println();

            cities.sort(Comparator.comparing((City c) -> c.district)
                    .thenComparing(Comparator.comparing((City c) -> c.name)));

            System.out.println("Сортировка по федеральному округу и наименованию:");
            cities.forEach(System.out::println);
        } else {
            System.out.println("Не удалось прочитать данные из файла.");
        }
    }

    public static List<City> readCitiesFromFile(String filename) {
        List<City> cities = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 5) {
                    String name = parts[0].trim();
                    String region = parts[1].trim();
                    String district = parts[2].trim();
                    int population = Integer.parseInt(parts[3].trim());
                    String foundation = parts[4].trim();
                    cities.add(new City(name, region, district, population, foundation));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return cities;
    }
}
