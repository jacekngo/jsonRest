package pl.jwrabel.trainings.javandwro3.jsonRest;

import java.util.List;
import java.util.Map;

/**
 * Created by jakubwrabel on 26/05/2017.
 */
public class Place {
	private String name;
	private String city;
	private List<Point> points;
	private Map<String, String> favorites;

	public Place() {
	}

	public Place(String name, String city, List<Point> points, Map<String, String> favorites) {
		this.name = name;
		this.city = city;
		this.points = points;
		this.favorites = favorites;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<Point> getPoints() {
		return points;
	}

	public void setPoints(List<Point> points) {
		this.points = points;
	}

	public Map<String, String> getFavorites() {
		return favorites;
	}

	public void setFavorites(Map<String, String> favorites) {
		this.favorites = favorites;
	}

	@Override
	public String toString() {
		return "Place{" +
				"name='" + name + '\'' +
				", city='" + city + '\'' +
				", points=" + points +
				", favorites=" + favorites +
				'}';
	}
}
