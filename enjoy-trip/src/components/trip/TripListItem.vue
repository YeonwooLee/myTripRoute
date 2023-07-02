<template>
  <v-card class="mx-auto my-10" width="400">
    <v-img
      class="white--text align-end"
      height="200px"
      src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
    >
      <v-card-title>{{ trip.tripName }}</v-card-title>
    </v-img>

    <v-card-subtitle class="pb-0">
      {{ trip.userId }}
      <span class="mx-2">|</span>
      {{ trip.createTime | formatDate }}
    </v-card-subtitle>

    <!-- <v-card-text class="text--primary"> </v-card-text> -->

    <v-card-actions>
      <trip-form :tripProp="trip" type="modify"></trip-form>
      <v-btn color="orange" text @click.prevent="moveToDetail"> 자세히 보기 </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import TripForm from "./TripForm.vue";
export default {
  name: "TripListItem",
  components: { TripForm },
  props: {
    trip: Object,
  },
  methods: {
    moveToDetail() {
      this.$router.push({
        name: "tripDetail",
        params: { tripId: this.trip.tripId, tripName: this.trip.tripName },
      });
    },
  },
  filters: {
    formatDate(value) {
      return new Date(value).toLocaleString();
    },
  },
};
</script>

<style scoped></style>
