import { apiInstance } from "./index.js";

const api = apiInstance();

function listArticle(param, success, fail) {
  console.log(param);
  api.get(`/board`, { params: param }).then(success).catch(fail);
}

function getArticle(articleno, success, fail) {
  api.get(`/board/${articleno}`).then(success).catch(fail);
}

function deleteArticle(payload, success, fail) {
  api
    .delete(`/board/${payload.boardId}`, { params: { userId: payload.userId } })
    .then(success)
    .catch(fail);
}

function writeArticle(article, success, fail) {
  api.post(`/board`, JSON.stringify(article)).then(success).catch(fail);
}

function modifyArticle(article, success, fail) {
  console.log(article);
  api
    .patch(`/board/${article.boardId}`, JSON.stringify(article))
    .then(success)
    .catch(fail);
}

export { listArticle, getArticle, deleteArticle, writeArticle, modifyArticle };
