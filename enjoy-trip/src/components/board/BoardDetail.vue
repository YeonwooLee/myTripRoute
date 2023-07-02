<template>
  <v-container>
    <view-header type="게시글"></view-header>
    <v-divider color="black"></v-divider>
    <div class="d-flex justify-space-between my-3 mx-5">
      <div class="d-flex flex-column">
        <div class="d-flex flex-row">
          <div class="d-flex align-center">
            <h3 class="mr-1" style="color: rgb(79, 90, 102)">
              [{{ board.category | getValue }}]
            </h3>
          </div>
          <div class="d-flex align-center">
            <h2 class="ml-1">
              {{ board.subject }}
            </h2>
          </div>
        </div>
        <p class="my-auto">
          <font-awesome-icon :icon="['fas', 'circle-user']" />
          {{ board.userId }}
        </p>
      </div>
      <div class="d-flex flex-column">
        <p class="my-auto">
          <font-awesome-icon :icon="['fas', 'calendar-days']" />
          {{ new Date(board.registerTime).toLocaleString() }}
        </p>
        <p class="my-auto">
          <font-awesome-icon :icon="['fas', 'eye']" />
          {{ board.hit }}
        </p>
      </div>
    </div>

    <v-divider></v-divider>

    <div
      class="my-3 mx-5"
      style="min-height: 100px"
      v-html="changeMarkdown"
    ></div>

    <div class="d-flex justify-end my-3 mx-5">
      <v-btn class="mx-1" @click="moveToList">목록으로</v-btn>
      <div v-if="userInfo.id === board.userId">
        <v-btn class="mx-1" @click="modifyBoard">수정</v-btn>
        <v-btn class="mx-1" @click="removeBoard">삭제</v-btn>
      </div>
    </div>

    <v-divider></v-divider>

    <comment-component></comment-component>
  </v-container>
</template>

<script>
import { mapActions, mapState } from "vuex";
import ViewHeader from "@/components/ViewHeader.vue";
import CommentComponent from "../comment/CommentComponent.vue";
import { marked } from "marked";

export default {
  name: "BoardDetail",
  components: {
    ViewHeader,
    CommentComponent,
  },
  computed: {
    ...mapState("boardStore", ["board"]),
    ...mapState("userStore", ["userInfo"]),
    changeMarkdown() {
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
      let changedText = marked(this.board.content);
      changedText = changedText.replaceAll("&lt;", "<");
      changedText = changedText.replaceAll("&gt;", ">");
      changedText = changedText.replaceAll("&quot;", '"');
      changedText = changedText.replaceAll("\n", "<br>");
      return changedText;
    },
  },
  created() {
    let param = this.$route.params.articleno;
    this.getBoard(param);
  },
  methods: {
    ...mapActions("boardStore", ["getBoard", "deleteBoard"]),
    modifyBoard() {
      console.log("modify");
      this.$router.push({ name: "boardModify" });
    },
    removeBoard() {
      if (confirm("게시글을 삭제하시겠습니까?")) {
        this.deleteBoard({
          boardId: this.board.boardId,
          userId: this.userInfo.id,
        });
      }
    },
    moveToList() {
      this.$router.push({ name: "boardList" });
    },
  },
  filters: {
    getValue(value) {
      if (value === "FREE") {
        return "자유게시판";
      }
      if (value === "NOTICE") {
        return "공지사항";
      }
      if (value === "QNA") {
        return "Q&A";
      }
    },
  },
};
</script>

<style scope></style>
