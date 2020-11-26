<template>
  <div class="container">
    <div class="row">
      <div class="col-8">
        <div class="card">
          <div class="card-header">
            <strong>Plan Trip</strong>
          </div>
          <div class="card-body">
            <form @submit="addJourneys">
              <fieldset class="form-group">
                <label for="cityName">City</label>
                <select
                  id="cityName"
                  v-model="selectedCity"
                  class="form-control"
                >
                  <option
                    :value="city.name"
                    v-for="(city, index) in cities"
                    :key="index"
                  >
                    {{ city.name }}
                  </option>
                </select>
              </fieldset>
              <fieldset class="form-group">
                <label for="travelDay">Day</label>
                <select
                  id="travelDay"
                  v-model="selectedDay"
                  class="form-control"
                >
                  <option value="0">Today</option>
                  <option value="1">Today+1</option>
                  <option value="2">Today+2</option>
                  <option value="3">Today+3</option>
                  <option value="4">Today+4</option>
                </select>
              </fieldset>
              <button class="btn btn-info" type="submit">Submit</button>
            </form>
          </div>
        </div>
      </div>
      <div class="col-4">
        <div class="card">
          <div class="card-header">
            <strong>Save Itinerary</strong>
          </div>
          <div class="card-body">
            <table class="table" v-if="this.tripJourneys.length > 0">
              <thead>
                <tr>
                  <th>City</th>
                  <th>Day</th>
                </tr>
              </thead>
              <tbody>
                <tr
                  v-for="tripJourney in tripJourneys"
                  v-bind:key="tripJourney.city"
                >
                  <td>{{ tripJourney.city }}</td>
                  <td v-if="tripJourney.day == 0">Today</td>
                  <td v-else>Today+{{ tripJourney.day }}</td>
                </tr>
              </tbody>
            </table>
            <form @submit="validateAndSubmit">
              <div v-if="errors.length">
                <div
                  class="alert alert-warning"
                  v-bind:key="index"
                  v-for="(error, index) in errors"
                >
                  <span>{{ error }}</span>
                </div>
              </div>
              <fieldset class="form-group">
                <label>Name</label>
                <input
                  type="text"
                  class="form-control"
                  v-model="itinerary.name"
                />
              </fieldset>
              <fieldset class="form-group">
                <label>Description</label>
                <input
                  type="text"
                  class="form-control"
                  v-model="itinerary.description"
                />
              </fieldset>

              <button
                class="btn btn-primary button-margin-right"
                type="submit"
                v-if="this.id == -1"
              >
                Save
              </button>
              <button
                class="btn btn-success button-margin-right"
                type="submit"
                v-else
              >
                Update
              </button>
              <button
                class="btn btn-danger button-margin-right"
                v-if="this.id != -1"
                v-on:click="deleteItinerary(itinerary.id)"
              >
                Delete
              </button>
              <button class="btn btn-secondary" v-on:click="goBack()">
                Cancel
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>
    <div class="row weather-data-grid">
      <table class="table" v-if="this.weatherData.length > 0">
        <thead>
          <tr>
            <th>City Name</th>
            <th>Country Code</th>
            <th>Temperature</th>
            <th>Temp. Feel Like</th>
            <th>Temp. Min</th>
            <th>Temp. Max</th>
            <th>Air Pressure</th>
            <th>Humidity</th>
            <th>Clouds</th>
            <th>Wind Speed</th>
            <th>Visibility</th>
            <th>Date</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="data in weatherData" v-bind:key="data.city_id">
            <td>{{ data.name }}</td>
            <td>{{ data.country }}</td>
            <td>{{ data.temp }}</td>
            <td>{{ data.feels_like }}</td>
            <td>{{ data.temp_min }}</td>
            <td>{{ data.temp_max }}</td>
            <td>{{ data.pressure }}</td>
            <td>{{ data.humidity }}</td>
            <td>{{ data.description }}</td>
            <td>{{ data.speed }}</td>
            <td>{{ data.visibility }}</td>
            <td>{{ data.dt_txt }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import ItineraryDataService from "../service/ItineraryDataService";
import WeatherDataService from "../service/WeatherDataService";

export default {
  name: "ItineraryComponent",
  data() {
    return {
      selectedCity: "",
      selectedDay: "",
      itinerary: [],
      errors: [],
      tripJourneys: [],
      weatherData: [],
      cities: [
        {
          id: 1,
          name: "Tokyo",
        },
        {
          id: 2,
          name: "Moscow",
        },
        {
          id: 3,
          name: "London",
        },
        {
          id: 4,
          name: "Adelaide",
        },
        {
          id: 5,
          name: "Sydney",
        },
      ],
    };
  },
  computed: {
    id() {
      return this.$route.params.id;
    },
  },
  methods: {
    refreshItinerary() {
      if (this.id != -1) {
        ItineraryDataService.retrieveItinerary(this.id).then((res) => {
          this.itinerary = res.data;
        });
      }
    },

    deleteItinerary(id) {
      ItineraryDataService.deleteItinerary(id).then(() => {
        this.$router.push("/");
      });
    },

    retrieveWeatherData(cityName, day) {
      WeatherDataService.retrieveWeatherDataFromAPi(cityName, day).then(
        (response) => {
          response.data.forEach((r) => {
            this.weatherData.unshift(r);
          });
        }
      );
    },

    goBack() {
      this.$router.push("/");
    },

    validateAndSubmit(e) {
      e.preventDefault();
      console.log({
        id: this.itinerary.id,
        name: this.itinerary.name,
        description: this.itinerary.description,
      });
      this.errors = [];
      if (!this.itinerary.name) {
        this.errors.push("Name is required");
      } else if (this.itinerary.name.length < 5) {
        this.errors.push("Enter atleast 5 characters in name");
      }

      if (this.errors.length === 0) {
        if (this.id == -1) {
          console.log("creating....");
          ItineraryDataService.createItinerary({
            name: this.itinerary.name,
            description: this.itinerary.description,
          }).then(() => {
            this.$router.push("/");
          });
        } else {
          console.log("updating....");
          ItineraryDataService.updateItinerary(this.id, this.itinerary).then(
            () => {
              this.$router.push("/");
            }
          );
        }
      }
    },
    addJourneys(e) {
      e.preventDefault();
      if (this.selectedDay != "" && this.selectedCity != "") {
        this.tripJourneys.unshift({
          city: this.selectedCity,
          day: this.selectedDay,
        });
        this.tripJourneys = this.tripJourneys.slice().sort(function(a, b) {
          return a.day - b.day;
        });

        this.retrieveWeatherData(this.selectedCity, this.selectedDay);

        this.selectedDay = "";
        this.selectedCity = "";
      }
    },
  },
  created() {
    this.refreshItinerary();
  },
};
</script>

<style scoped>
h3 {
  margin: 40px 0 0;
}
.button-margin-right {
  margin-right: 15px;
}
.weather-data-grid {
  margin-top: 10px;
}
</style>
