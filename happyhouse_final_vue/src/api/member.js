import { apiInstance } from "./index.js";

const api = apiInstance();

async function login(user, success, fail) {
  await api.post(`/user/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function findById(userid, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/user/info/${userid}`).then(success).catch(fail);
}

function register(data, success, fail) {
  api.post("/user/register", JSON.stringify(data)).then(success).catch(fail);
}

function modify(data, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  api.put("/user/modify", data).then(success).catch(fail);
}

function deleteUser(userid, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  api.delete(`/user/delete/${userid}`).then(success).catch(fail);
}

async function checkUser(data, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.post("/user/check", data).then(success).catch(fail);
}

async function idCheck(id, success, fail) {
  await api.get(`/user/idcheck/${id}`).then(success).catch(fail);
}

export { login, findById, register, modify, deleteUser, checkUser, idCheck };
