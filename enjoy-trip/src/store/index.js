import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

import boardStore from "@/store/modules/boardStore";
import userStore from "@/store/modules/userStore";
import tripStore from "@/store/modules/tripStore";
import planStore from "@/store/modules/planStore";
import commentStore from "@/store/modules/commentStore";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    boardStore,
    userStore,
    tripStore,
    planStore,
    commentStore,
  },
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
});
