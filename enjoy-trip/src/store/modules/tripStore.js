import { listTrips, removeTrip, createTrip, updateTrip } from "@/api/trip";

const tripStore = {
  namespaced: true,
  state: {
    trips: [],
  },
  getters: {},
  mutations: {
    SET_TRIPS(state, payload) {
      state.trips = payload;
    },
  },
  actions: {
    async getTrips({ commit }, param) {
      await listTrips(
        param,
        ({ data }) => {
          console.log(data);
          commit("SET_TRIPS", data);
        },
        (err) => {
          console.error(err);
        }
      );
    },
    async makeTrip({ commit }, param) {
      await createTrip(
        param,
        ({ data }) => {
          console.log(data);
          commit;
        },
        (err) => {
          console.error(err);
        }
      );
    },
    async deleteTrip({ commit }, param) {
      await removeTrip(
        param,
        () => {
          alert("여행을 삭제했습니다.");
          commit;
        },
        (err) => {
          console.error(err);
        }
      );
    },
    async modifyTrip({ commit }, param) {
      console.log(param);
      await updateTrip(
        param,
        () => {
          // alert("여행을 삭제했습니다,");
          commit;
        },
        (err) => {
          console.error(err);
        }
      );
    },
  },
};

export default tripStore;
