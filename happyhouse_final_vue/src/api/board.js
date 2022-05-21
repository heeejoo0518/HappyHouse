/** @format */

import { apiInstance } from "./index.js";

const api = apiInstance();
api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");

function listArticle(success, fail) {
  api.get(`/board`).then(success).catch(fail);
}

function writeArticle(article, success, fail) {
  api.post(`/board`, JSON.stringify(article)).then(success).catch(fail);
}

function getArticle(articleno, success, fail) {
  api.get(`/board/${articleno}`).then(success).catch(fail);
}

function modifyArticle(article, success, fail) {
  api
    .put(`/board/${article.articleno}`, JSON.stringify(article))
    .then(success)
    .catch(fail);
}

function deleteArticle(articleno, success, fail) {
  api.delete(`/board/${articleno}`).then(success).catch(fail);
}

function getTotalCount(success, fail) {
  api.get(`/board/count`).then(success).catch(fail);
}

function listComments(articleno, success, fail) {
  api.get(`/board/${articleno}`).then(success).catch(fail);
}

function createComment(article, success, fail) {
  api.post(`/board/comment`, JSON.stringify(article)).then(success).catch(fail);
}

function deleteComment(commentno, success, fail) {
  api.delete(`board/comment/${commentno}`).then(success).catch(fail);
}

export {
  listArticle,
  writeArticle,
  getArticle,
  modifyArticle,
  deleteArticle,
  getTotalCount,
  listComments,
  createComment,
  deleteComment,
};
