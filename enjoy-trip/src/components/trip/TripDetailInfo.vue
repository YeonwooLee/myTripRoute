<template>
  <v-container class="pa-0">
    <div class="d-flex justify-end mt-3">
      총 예산 : {{ tripBudget | price }}
    </div>
    <plan-list :plans="plans"></plan-list>
  </v-container>
</template>

<script>
import PlanList from "./PlanList.vue";
export default {
  name: "TripDetailInfo",
  components: { PlanList },

  props: {
    plans: Array,
  },
  data() {
    return {
      trip: null,
    };
  },
  created() {
    this.tripId = this.$route.params.tripId;
    // TODO: trip 가져오기
  },
  computed: {
    tripBudget() {
      let budget = 0;
      this.plans.forEach((plan) => {
        budget += Number(plan.planBudget);
      });
      return budget;
    },
  },
  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "원";
    },
  },
};
</script>

<style scoped></style>
