import { removePlan, insertPlan } from "@/api/plan";

const planStore = {
  namespaced: true,
  state: {},
  getters: {},
  mutations: {},
  actions: {
    async deletePlan({ commit }, payload) {
      console.log(payload);
      await removePlan(
        payload,
        () => {
          alert("일정을 삭제했습니다,");
          commit;
        },
        (err) => {
          console.error(err);
        }
      );
    },
    async addPlan({ commit }, payload) {
      console.log(payload);
      await insertPlan(
        payload,
        () => {
          alert("일정을 추가했습니다.");
          commit;
        },
        (err) => {
          console.error(err);
        }
      );
    },
  },
};

export default planStore;
