import { attractionInstance } from "./index.js";

const api = attractionInstance();
function listSido(param, success, fail) {
  api.get(`/areaCode1`, { params: param }).then(success).catch(fail);
}
function listGugun(param, success, fail) {
  api.get(`/areaCode1`, { params: param }).then(success).catch(fail);
}
function listAttraction(param, success, fail) {
  api.get(`/searchKeyword1`, { params: param }).then(success).catch(fail);
}
function detailCommon(param, item, success, fail) {
  api.get(`/detailCommon1`, { params: param }).then(success).catch(fail);
}
export { listSido, listGugun, listAttraction, detailCommon };
