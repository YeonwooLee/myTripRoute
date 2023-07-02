import {
  listComments,
  removeComment,
  updateComment,
  createComment,
} from "@/api/comment";

const commentStore = {
  namespaced: true,
  state: {
    comments: [],
  },
  getters: {},
  mutations: {
    SET_COMMENTS(state, payload) {
      state.comments = payload;
    },
  },
  actions: {
    async writeComment({ commit }, comment) {
      await createComment(
        comment,
        () => {
          commit;
        },
        (err) => {
          console.error(err);
        }
      );
    },
    async getComments({ commit }, payload) {
      await listComments(
        payload,
        ({ data }) => {
          commit("SET_COMMENTS", data);
        },
        (err) => {
          console.error(err);
        }
      );
    },
    async deleteComment({ commit }, payload) {
      await removeComment(
        payload,
        () => {
          commit;
        },
        (err) => {
          console.error(err);
        }
      );
    },
    async modifyComment({ commit }, payload) {
      await updateComment(
        payload,
        () => {
          commit;
        },
        (err) => {
          console.error(err);
        }
      );
    },
  },
};

export default commentStore;
