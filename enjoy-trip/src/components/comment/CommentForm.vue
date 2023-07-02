<template>
  <v-container>
    <div>
      <v-textarea
        solo
        counter="500"
        name="input-7-4"
        label="댓글을 입력해주세요"
        prepend-icon="mdi-comment"
        rows="6"
        v-model="newComment"
        no-resize
        :rules="rules"
        ref="form"
      ></v-textarea>
    </div>
    <div class="d-flex justify-end">
      <v-btn large @click="createComment"
        ><v-icon left>mdi-pencil</v-icon>댓글 등록</v-btn
      >
    </div>
  </v-container>
</template>

<script>
import { mapActions, mapState } from "vuex";
import { marked } from "marked";

export default {
  name: "CommentForm",
  data() {
    return {
      rules: [(v) => v.length <= 500 || "댓글은 500자까지 작성할 수 있습니다"],
      newComment: "",
    };
  },
  computed: {
    ...mapState("userStore", ["userInfo"]),
  },
  methods: {
    ...mapActions("commentStore", ["writeComment", "getComments"]),
    async createComment() {
      if (this.$refs.form.validate()) {
        console.log(this.newComment);
        this.newComment = this.changeMarkdown(this.newComment);
        let comment = {
          comment: this.newComment,
          userId: this.userInfo.id,
          boardId: this.$route.params.articleno,
        };
        console.log(comment);
        await this.writeComment(comment);
        this.newComment = "";
        await this.getComments(this.$route.params.articleno);
      } else {
        console.log("글자수오버");
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
};
</script>

<style scoped></style>
