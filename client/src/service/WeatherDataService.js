import axios from "axios";

const SERVER_API_URL = "http://localhost:8080/api/v1";

class WeatherDataService {
    retrieveWeatherDataFromAPi(cityName, day) {
        return axios.get(`${SERVER_API_URL}/forecast/${cityName}?days=${day}`);
    }
}

export default new WeatherDataService();
