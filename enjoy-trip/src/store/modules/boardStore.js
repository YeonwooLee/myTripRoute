import {
  getArticle,
  deleteArticle,
  writeArticle,
  modifyArticle,
} from "@/api/board";
import router from "@/router";

const boardStore = {
  namespaced: true,
  state: {
    board: {
      category: "",
      subject: "",
      content: "",
    },
  },
  getters: {},
  mutations: {
    SET_BOARD(state) {
      state.board = {
        category: "",
        subject: "",
        content: "",
      };
    },
    GET_BOARD(state, payload) {
      state.board = payload;
    },
    DELETE_BOARD(state) {
      state.board = {};
    },
    CREATE_BOARD(state, payload) {
      state.board = payload;
    },
    MODIFY_BOARD(state, payload) {
      state.board = payload;
    },
    HTTP_TO_MD(state, payload) {
      state.board.content = payload;
    },
  },
  actions: {
    getBoard({ commit }, payload) {
      getArticle(
        payload,
        ({ data }) => {
          if (data) {
            commit("GET_BOARD", data);
          } else {
            router.push({ name: "NotFound404" });
          }
        },
        (err) => {
          console.error(err);
        }
      );
    },
    deleteBoard({ commit }, payload) {
      deleteArticle(
        payload,
        () => {
          commit("DELETE_BOARD");
          router.push({ name: "boardList" });
        },
        (err) => {
          console.error(err);
        }
      );
    },
    createBoard({ commit }, payload) {
      writeArticle(
        payload,
        () => {
          commit("CREATE_BOARD", payload);
          router.push({ name: "boardList" });
        },
        (err) => {
          console.error(err);
        }
      );
    },
    modifyBoard({ commit }, payload) {
      modifyArticle(
        payload,
        () => {
          commit("MODIFY_BOARD", payload);
          router.push({ name: "boardList" });
        },
        (err) => {
          console.error(err);
        }
      );
    },
  },
};

export default boardStore;
