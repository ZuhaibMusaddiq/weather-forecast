<template>
  <div class="home">
    <!-- <img alt="Vue logo" src="../assets/logo.png" /> -->
    <ListItineraryComponent msg="Hello world!" />
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
