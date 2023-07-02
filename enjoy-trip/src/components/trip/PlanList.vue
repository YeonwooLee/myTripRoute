<template>
  <div style="height: 700px; overflow-y: scroll">
    <v-timeline align-top dense>
      <v-timeline-item
        v-for="plan in orderedPlan"
        :key="plan.planId"
        color="amber lighten-3"
        small
        class="pr-5"
      >
        <v-card class="elevation-2" color="amber lighten-3">
          <v-card-title class="text-h6 mb-3">
            {{ plan.attractionTitle }}
          </v-card-title>
          <v-card-subtitle class="white pt-2">
            <div class="d-flex justify-space-between">
              <div>
                <strong>DAY {{ plan.planDate }} </strong>
                [ {{ plan.planOrder }} ]
              </div>
              <div>{{ plan.planBudget | price }}</div>
            </div>
          </v-card-subtitle>
          <v-card-text class="white text--primary">
            <div min-width="500px">{{ plan.planDesc }}</div>
            <div class="ma-0">
              <plan-modify :planProp="plan"></plan-modify>
            </div>
          </v-card-text>
        </v-card>
      </v-timeline-item>
    </v-timeline>
  </div>
</template>

<script>
import PlanModify from "./PlanModify.vue";
export default {
  name: "PlanList",
  data() {
    return {
      orderedPlan: [],
    };
  },
  components: {
    PlanModify,
  },
  props: {
    plans: Array,
  },
  created() {
    setTimeout(() => {
      console.log(this.plans);

      var plansByDate = {};

      for (var i = 0; i < this.plans.length; i++) {
        var plan = this.plans[i];
        var planDate = plan.planDate;

        if (!Object.prototype.hasOwnProperty.call(plansByDate, planDate)) {
          plansByDate[planDate] = [];
        }

        plansByDate[planDate].push(plan);
      }

      for (let i = 1; i < this.plans.length - 1; i++) {
        // date 정리
        if (
          !Object.prototype.hasOwnProperty.call(plansByDate, i) &&
          Object.prototype.hasOwnProperty.call(plansByDate, i + 1)
        ) {
          plansByDate[i] = plansByDate[i + 1];
          delete plansByDate[i + 1];
          plansByDate[i].forEach((plan) => {
            plan["planDate"] = i;
          });
        }

        // order 정리
        if (plansByDate[i]) {
          let cnt = 1;
          plansByDate[i].forEach((plan) => {
            plan["planOrder"] = cnt++;
            this.orderedPlan.push(plan);
          });
        }
      }
    }, 500);
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
