<template>
  <v-container>
    <view-header type="여행 계획"></view-header>
    <v-row>
      <v-col cols="3">
        <v-card
          class="mx-auto my-10 d-flex align-center justify-center"
          width="400"
          height="290"
          elevation="0"
        >
          <v-card-actions>
            <trip-form :tripProp="trip" type="create"></trip-form>
          </v-card-actions>
        </v-card>
      </v-col>
      <v-col cols="3" v-for="trip in trips" :key="trip.tripId">
        <trip-list-item :trip="trip"></trip-list-item>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import ViewHeader from "../ViewHeader.vue";
import TripListItem from "./TripListItem.vue";
import TripForm from "./TripForm.vue";
import { mapActions, mapState } from "vuex";
export default {
  name: "TripList",
  components: {
    TripListItem,
    ViewHeader,
    TripForm,
  },
  data() {
    return {
      trip: {
        tripName: "",
      },
    };
  },
  computed: {
    ...mapState("userStore", ["userInfo"]),
    ...mapState("tripStore", ["trips"]),
  },
  created() {
    let param = {
      userId: this.userInfo.id,
      orderBy: "create_time",
      orderDir: "desc",
    };
    console.log(param);
    this.getTrips(param);
  },
  methods: {
    ...mapActions("tripStore", ["getTrips"]),
  },
};
</script>

<style scoped></style>
