<template>
  <div class="my-4" :id="comment.commentId">
    <div class="d-flex justify-space-between mb-2">
      <div>
        <v-avatar color="grey" size="36" class="mr-2">
          <v-icon dark> mdi-account-circle </v-icon>
        </v-avatar>
        {{ comment.userId }}
        <span class="mx-4">|</span>
        {{ comment.memoTime | formatDate }}
      </div>
      <div v-if="!modifyStatus && this.userInfo.id === comment.userId">
        <span @click="updateComment(comment.commentId)">수정</span>
        <span @click="removeComment(comment.commentId)">삭제</span>
      </div>
    </div>
    <div
      class="pl-10 mb-5"
      v-html="changeMarkdown(comment.comment)"
      v-if="!modifyStatus"
    ></div>
    <div class="mb-5" v-if="modifyStatus">
      <v-textarea
        solo
        counter="500"
        rows="6"
        :rules="rules"
        ref="form"
        v-model="newComment"
      ></v-textarea>
      <div class="d-flex justify-end">
        <v-btn
          class="mx-1"
          @click="updateComment(comment.commentId, comment.userId)"
          >수정</v-btn
        >
        <v-btn class="mx-1" @click="modifyStatus = !modifyStatus">취소</v-btn>
      </div>
    </div>
    <v-divider></v-divider>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import { marked } from "marked";
import TurndownService from "turndown";

export default {
  name: "CommentListItem",
  data() {
    return {
      modifyStatus: false,
      rules: [(v) => v.length <= 500 || "댓글은 500자까지 작성할 수 있습니다"],
      newComment: "",
    };
  },
  props: {
    comment: Object,
  },
  computed: {
    ...mapState("userStore", ["userInfo"]),
  },
  methods: {
    ...mapActions("commentStore", [
      "getComments",
      "deleteComment",
      "modifyComment",
    ]),
    async updateComment(commentId) {
      if (this.modifyStatus) {
        if (this.$refs.form.validate()) {
          this.newComment = this.changeMarkdown(this.newComment);
          let param = {
            commentId: commentId,
            userId: this.userInfo.id,
            comment: this.newComment,
          };
          console.log(param);
          await this.modifyComment(param);
          this.modifyStatus = !this.modifyStatus;
          await this.getComments(this.$route.params.articleno);
        }
      } else {
        var turndownService = new TurndownService();
        this.newComment = turndownService.turndown(this.comment.comment);
        this.modifyStatus = !this.modifyStatus;
      }
    },
    async removeComment(commentId) {
      if (confirm("삭제하시겠습니까?")) {
        let param = {
          commentId: commentId,
          userId: this.userInfo.id,
        };
        await this.deleteComment(param);
        console.log(this.$route.params.articleno);
        await this.getComments(this.$route.params.articleno);
      }
    },
    changeMarkdown(value) {
      marked.setOptions({
        renderer: new marked.Renderer(),
        gfm: true,
        headerIds: false,
        tables: true,
        breaks: true,
        pedantic: false,
        // sanitize: true,
        smartLists: true,
        smartypants: false,
        mangle: false,
      });
      let changedText = marked(value);
      changedText = changedText.replaceAll("&lt;", "<");
      changedText = changedText.replaceAll("&gt;", ">");
      changedText = changedText.replaceAll("&quot;", '"');
      changedText = changedText.replaceAll("\n", "<br>");
      return changedText;
    },
  },
  filters: {
    formatDate(value) {
      return new Date(value).toLocaleString();
    },
  },
};
</script>

<style scoped>
div {
  color: rgb(79, 90, 102);
}
span {
  margin-left: 7px;
  margin-right: 7px;
  color: rgb(79, 90, 102);
  text-decoration: none;
}
</style>
