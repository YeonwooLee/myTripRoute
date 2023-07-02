import { apiInstance } from "./index.js";
const api = apiInstance();
function getPlanList(tripId, success, fail) {
  api.get(`/plan/biglist/${tripId}`).then(success).catch(fail);
}
function getAttractionDetail(planId, success, fail) {
  api.get(`/attraction/${planId}`).then(success).catch(fail);
}

export { getPlanList, getAttractionDetail };
