<template>
  <v-container>
    <v-row>
      <!-- 비율조절은 cols 속성을 이용하여 가능(총 너비 12를 기준으로 함) -->
      <v-col cols="6">
        <!-- TODO: 여행 이름으로 변경 -->
        <div class="text-h6">{{ this.$route.params.tripName }}</div>
        <kakao-map :plans="plans"></kakao-map>
      </v-col>

      <v-col class="pa-0">
        <trip-detail-info :plans="plans"></trip-detail-info>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import KakaoMap from "../attraction/kakaomap/KakaoMap.vue";
import TripDetailInfo from "./TripDetailInfo.vue";
import { getPlanList } from "@/api/tripDetail";
export default {
  name: "TripDetail",
  components: { TripDetailInfo, KakaoMap },
  data() {
    return {
      plans: [],
    };
  },
  methods: {
    makePlanList(tripId) {
      getPlanList(
        tripId,
        (res) => {
          const result = res.data;
          this.plans = result;
        },
        (err) => {
          console.log(err);
        }
      );
    },
  },
  created() {
    this.makePlanList(this.$route.params.tripId);
  },
};
</script>

<style scoped></style>
