import { apiInstance } from "./index.js";

const api = apiInstance();

async function listComments(param, success, fail) {
  await api.get(`/comment/${param}`).then(success).catch(fail);
}

async function createComment(comment, success, fail) {
  await api.post(`/comment`, JSON.stringify(comment)).then(success).catch(fail);
}

async function removeComment(payload, success, fail) {
  await api.delete(`/comment`, { params: payload }).then(success).catch(fail);
}

async function updateComment(comment, success, fail) {
  await api
    .patch(`/comment`, JSON.stringify(comment))
    .then(success)
    .catch(fail);
}

export { listComments, createComment, removeComment, updateComment };
