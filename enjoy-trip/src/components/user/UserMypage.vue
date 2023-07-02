<template>
  <v-container>
    <view-header type="마이페이지"></view-header>
    <user-write-form type="modify"></user-write-form>
  </v-container>
</template>

<script>
import ViewHeader from "@/components/ViewHeader.vue";
import UserWriteForm from "./UserWriteForm.vue";

export default {
  name: "userMypage",
  components: {
    ViewHeader,
    UserWriteForm,
  },
};
</script>

<style scoped></style>
