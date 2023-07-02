<template>
  <v-container>
    <comment-form></comment-form>
    <v-divider class="mt-5 mb-5"></v-divider>
    <comment-list></comment-list>
  </v-container>
</template>

<script>
import CommentList from "@/components/comment/CommentList.vue";
import CommentForm from "@/components/comment/CommentForm.vue";
export default {
  name: "CommentComponent",
  components: {
    CommentList,
    CommentForm,
  },
};
</script>

<style></style>
