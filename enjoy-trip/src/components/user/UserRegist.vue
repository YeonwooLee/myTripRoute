<template>
  <v-container>
    <view-header type="회원가입"></view-header>
    <user-write-form type="regist"></user-write-form>
  </v-container>
</template>

<script>
import ViewHeader from "@/components/ViewHeader.vue";
import UserWriteForm from "./UserWriteForm.vue";

export default {
  name: "UserRegist",
  components: {
    ViewHeader,
    UserWriteForm,
  },
};
</script>

<style scoped></style>
