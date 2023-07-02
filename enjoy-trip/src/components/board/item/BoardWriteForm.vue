<template>
  <v-container>
    <v-form ref="form" v-model="valid">
      <v-container>
        <v-row>
          <v-col cols="2" class="pl-0">
            <v-select
              :items="categories"
              label="말머리"
              v-model="select"
              item-text="value"
              item-value="key"
              required
              :rules="[(v) => !!v || '말머리를 선택해주세요']"
            ></v-select>
          </v-col>
          <v-col class="pr-0">
            <v-text-field
              label="제목"
              v-model="board.subject"
              :rules="title_rule"
              required
              :counter="100"
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-textarea
            name="input-7-1"
            label="내용"
            rows="10"
            auto-grow
            outlined
            v-model="board.content"
            :rules="content_rule"
            required
            :counter="2000"
          ></v-textarea>
        </v-row>
        <v-row class="d-flex justify-end">
          <v-btn
            outlined
            x-large
            class="mx-1"
            v-if="type === 'write'"
            @click="clickWrite"
            >등록</v-btn
          >
          <v-btn
            outlined
            x-large
            class="mx-1"
            v-if="type === 'modify'"
            @click="clickModify"
            >수정</v-btn
          >
          <v-btn outlined x-large class="mx-1" @click="moveToList">취소</v-btn>
        </v-row>
      </v-container>
    </v-form>
  </v-container>
</template>

<script>
import { mapActions, mapMutations, mapState } from "vuex";
import { marked } from "marked";
import TurndownService from "turndown";

export default {
  name: "BoardWriteForm",
  props: {
    type: String,
  },
  data() {
    return {
      categories: [
        { key: "FREE", value: "자유게시판" },
        { key: "QNA", value: "Q&A" },
        { key: "NOTICE", value: "공지사항" },
      ],
      select: null,
      valid: true,
      title_rule: [
        (v) => !!v || "제목을 입력해주세요",
        (v) => (v && v.length <= 100) || "제목은 100자까지 작성할 수 있습니다.",
      ],
      content_rule: [
        (v) => !!v || "내용을 입력해주세요",
        (v) =>
          (v && v.length <= 2000) || "내용은 2000자까지 작성할 수 있습니다.",
      ],
    };
  },
  computed: {
    ...mapState("boardStore", ["board"]),
    ...mapState("userStore", ["userInfo"]),
  },
  created() {
    if (this.type === "write") {
      this.SET_BOARD();
    }
    if (this.type === "modify") {
      var turndownService = new TurndownService();
      const markdown = turndownService.turndown(this.board.content);
      this.HTTP_TO_MD(markdown);

      this.select = this.board.category;
    }
  },
  methods: {
    ...mapMutations("boardStore", ["SET_BOARD", "HTTP_TO_MD"]),
    ...mapActions("boardStore", ["createBoard", "modifyBoard"]),
    clickWrite() {
      if (this.$refs.form.validate()) {
        console.log("작성");
        let convertContent = this.changeMarkdown(this.board.content);
        let board = {
          subject: this.board.subject,
          content: convertContent,
          userId: this.userInfo.id,
          category: this.select,
        };
        console.log(board);
        this.createBoard(board);
      }
    },
    clickModify() {
      if (this.$refs.form.validate()) {
        console.log("수정");
        console.log(this.board);
        let convertContent = this.changeMarkdown(this.board.content);
        let board = {
          boardId: this.board.boardId,
          category: this.select,
          hit: this.board.hit,
          registerTime: this.board.registerTime,
          subject: this.board.subject,
          content: convertContent,
          userId: this.board.userId,
        };
        console.log(board);
        this.modifyBoard(board);
      }
    },
    moveToList() {
      this.$router.push({ name: "boardList" });
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

<style scope></style>
