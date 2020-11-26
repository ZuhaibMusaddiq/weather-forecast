import axios from "axios";
 
const SERVER_API_URL = "http://localhost:8080/api/v1";

class ItineraryDataService {
    retrieveAllItineraries() {
        return axios.get(`${SERVER_API_URL}/itinerary`);
    }
    
    deleteItinerary(id) {
        return axios.delete(`${SERVER_API_URL}/itinerary/${id}`);
    }

    retrieveItinerary(id){
        return axios.get(`${SERVER_API_URL}/itinerary/${id}`);
    }

    updateItinerary(id, itinerary) {
        return axios.put(`${SERVER_API_URL}/itinerary/${id}`, itinerary);
    }
  
    createItinerary(itinerary) {
        return axios.post(`${SERVER_API_URL}/itinerary`, itinerary);
    }
}

export default new ItineraryDataService();