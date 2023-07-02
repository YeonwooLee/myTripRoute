import { apiInstance } from "./index.js";

const api = apiInstance();

function regist(param, success, fail) {
  console.log(JSON.stringify(param));
  api.post(`/user/regist`, JSON.stringify(param)).then(success).catch(fail);
}

function update(param, success, fail) {
  api.patch(`/user/update`, JSON.stringify(param)).then(success).catch(fail);
}

function withdraw(param, success, fail) {
  api.delete(`/user`, { params: param }).then(success).catch(fail);
}

async function login(user, success, fail) {
  await api.post(`/user/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function findById(userid, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/user/info/${userid}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  api.defaults.headers["refresh-token"] =
    sessionStorage.getItem("refresh-token"); //axios header에 refresh-token 셋팅
  await api.post(`/user/refresh`, user).then(success).catch(fail);
}

async function logout(userid, success, fail) {
  await api.get(`/user/logout/${userid}`).then(success).catch(fail);
}

export { login, regist, update, logout, withdraw, findById, tokenRegeneration };
