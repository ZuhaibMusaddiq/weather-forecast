<template>
  <div class="home">
    <ListItineraryComponent msg="Saved Itineraries" />
  </div>
</template>

<script>
import ListItineraryComponent from "@/components/ListItineraryComponent.vue";

export default {
  name: "Home",
  components: {
    ListItineraryComponent
  }
};
</script>
