<template>
  <v-container>
    <div class="mb-3">
      총 <strong>{{ comments.length }}</strong
      >건의 댓글 있습니다.
    </div>
    <comment-list-item
      v-for="comment in comments"
      :key="comment.commentId"
      :comment="comment"
    ></comment-list-item>
  </v-container>
</template>

<script>
import CommentListItem from "@/components/comment/CommentListItem.vue";
import { mapActions, mapState } from "vuex";
export default {
  components: { CommentListItem },
  name: "CommentList",
  data() {
    return {};
  },
  computed: {
    ...mapState("commentStore", ["comments"]),
  },
  created() {
    this.getComments(this.$route.params.articleno);
  },
  methods: {
    ...mapActions("commentStore", ["getComments"]),
  },
};
</script>

<style scoped></style>
