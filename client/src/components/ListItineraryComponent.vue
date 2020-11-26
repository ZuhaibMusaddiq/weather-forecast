<template>
  <div class="container">
    <h3>{{ msg }}</h3>
    <div v-if="message" class="alert alert-success">
      {{ message }}
    </div>
      <table class="table">
        <thead>
          <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Description</th>
            <th>Update</th>
            <th>Delete</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="itinerary in itineraries" v-bind:key="itinerary.id">
            <td>{{ itinerary.id }}</td>
            <td>{{ itinerary.name }}</td>
            <td>{{ itinerary.description }}</td>
            <td>
              <button class="btn btn-success" v-on:click="updateItinerary(itinerary.id)">Update</button></td>
            <td>
              <button class="btn btn-warning" v-on:click="deleteItinerary(itinerary.id)">Delete</button>
            </td>
          </tr>
        </tbody>
      </table>
      <div class="row">
        <button class="btn btn-success" v-on:click="addItinerary()">Add</button>
      </div>
  </div>
</template>

<script>
import ItineraryDataService from "../service/ItineraryDataService";

export default {
  name: "ListItineraryComponent",

  props: {
    msg: String,
  },

  data() {
    return {
      ACCOUNT_ID: 1, //HARDCODED
      itineraries: [],
      message: null,
    };
  },

  methods: {
    refreshItineraries() {
      ItineraryDataService.retrieveAllItineraries(this.ACCOUNT_ID).then(
        (response) => {
          this.itineraries = response.data;
        }
      );
    },
    deleteItinerary(id) {
      ItineraryDataService.deleteItinerary(id).then(() => {
        this.message = `Delete of Itinerary ${id} Successful`;
        this.refreshItineraries();
      });
    },
    updateItinerary(id) {
      this.$router.push(`/itinerary/${id}`);
    },
    addItinerary() {
      this.$router.push(`/itinerary/-1`);
    },
  },

  created() {
    this.refreshItineraries();
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
h3 {
  margin: 40px 0 0;
}
</style>
