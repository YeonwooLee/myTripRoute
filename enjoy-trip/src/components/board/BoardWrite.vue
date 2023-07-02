<template>
  <v-container>
    <view-header type="게시글 등록"></view-header>
    <board-write-form type="write"></board-write-form>
  </v-container>
</template>

<script>
import BoardWriteForm from "@/components/board/item/BoardWriteForm.vue";
import ViewHeader from "@/components/ViewHeader.vue";
export default {
  name: "BoardWrite",
  components: {
    BoardWriteForm,
    ViewHeader,
  },
};
</script>

<style scope></style>
