<template>
  <v-container class="text-center">
    <view-header type="ERROR"></view-header>
    <h3>페이지를 찾을 수 없습니다</h3>
  </v-container>
</template>

<script>
import ViewHeader from "@/components/ViewHeader.vue";
export default {
  name: "App404Error",
  components: { ViewHeader },
};
</script>

<style scoped></style>
