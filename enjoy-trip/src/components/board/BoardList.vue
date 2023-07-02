<template>
  <v-container>
    <view-header type="게시판"></view-header>

    <v-container>
      <v-row class="d-flex justify-space-between">
        <v-col cols="2" class="d-flex justify-start">
          <v-btn elevation="2" outlined x-large @click="moveToWrite">
            글쓰기
          </v-btn>
        </v-col>

        <v-col cols="4">
          <v-select
            v-model="value"
            :items="categories"
            item-text="value"
            item-value="key"
            prepend-icon="mdi-format-list-bulleted-type"
            multiple
            chips
          >
            <!-- <template v-slot:selection="{ item, index }">
              <v-chip v-if="index === 0">
                <span>{{ item.value }}</span>
              </v-chip>
              <span v-if="index === 1" class="grey--text text-caption">
                +{{ value.length - 1 }}
              </span>
            </template> -->
          </v-select>
        </v-col>

        <v-col cols="5">
          <v-row class="d-flex justify-end mr-0">
            <v-col cols="3" class="px-0">
              <v-select
                :items="items"
                label="조건"
                outlined
                class="mx-1"
                v-model="searchBar.searchType"
              >
              </v-select>
            </v-col>
            <v-col cols="6" class="px-0">
              <v-text-field
                label="검색어"
                placeholder="검색어"
                outlined
                class="mx-1"
                v-model="searchBar.searchKeyword"
                @keypress.enter="search"
              ></v-text-field>
            </v-col>
            <v-col cols="2" class="px-0">
              <v-btn elevation="2" x-large class="ml-1" @click="search"
                >검색</v-btn
              >
            </v-col>
          </v-row>
        </v-col>
      </v-row>
    </v-container>
    <v-data-table
      :headers="headers"
      :items="boards"
      :items-per-page="10"
      class="elevation-1"
      @click:row="moveToDetail"
      v-if="boards"
    >
      <template v-slot:[`item.category`]="{ item }">
        {{ item.category | getValue }}
      </template>
      <template v-slot:[`item.registerTime`]="{ item }">
        {{ new Date(item.registerTime).toLocaleString() }}
      </template>
    </v-data-table>
    <v-data-table class="elevation-1" :headers="headers" v-else>
      <template slot="no-data">
        <strong>게시글이 없습니다.</strong>
      </template>
    </v-data-table>
  </v-container>
</template>

<script>
import { listArticle } from "@/api/board";
import ViewHeader from "@/components/ViewHeader.vue";

export default {
  name: "BoardList",
  components: {
    ViewHeader,
  },
  data() {
    return {
      items: ["작성자", "제목"],
      headers: [
        { text: "글번호", value: "boardId" },
        { text: "말머리", value: "category" },
        { text: "제목", value: "subject" },
        { text: "작성자", value: "userId" },
        { text: "조회수", value: "hit" },
        { text: "작성일", value: "registerTime" },
      ],
      searchBar: {
        searchType: "",
        searchKeyword: "",
      },
      boards: [],
      categories: [
        { key: "FREE", value: "자유게시판" },
        { key: "QNA", value: "Q&A" },
        { key: "NOTICE", value: "공지사항" },
      ],
      value: ["FREE", "QNA", "NOTICE"],
    };
  },
  created() {
    this.getBoards({
      userId: "%",
      keyword: "%",
      boardId: "0",
      orderDir: "desc",
      orderBy: "register_time",
      categories: ["FREE", "NOTICE", "QNA"],
    });
  },
  methods: {
    moveToDetail({ boardId }) {
      this.$router.push({
        name: "boardDetail",
        params: { articleno: boardId },
      });
    },
    moveToWrite() {
      this.$router.push({ name: "boardWrite" });
    },
    search() {
      let searchParam = {
        userId: "%",
        keyword: "%",
        boardId: "0",
        orderDir: "desc",
        orderBy: "register_time",
        categories: this.value,
      };
      if (this.searchBar.searchType === "작성자") {
        searchParam.userId = this.searchBar.searchKeyword;
      } else if (this.searchBar.searchType === "제목") {
        searchParam.keyword = this.searchBar.searchKeyword;
      }
      this.getBoards(searchParam);
    },
    getBoards(param) {
      listArticle(
        param,
        ({ data }) => {
          this.boards = data;
        },
        (err) => {
          console.error(err);
        }
      );
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
  watch: {
    value() {
      this.search();
    },
  },
};
</script>

<style scope></style>
