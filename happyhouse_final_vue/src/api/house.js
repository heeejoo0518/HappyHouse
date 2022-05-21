/** @format */

// import { apiInstance, houseInstance } from "./index.js";
import { apiInstance } from "./index.js";

const api = apiInstance();
// const house = houseInstance();

function sidoList(success, fail) {
  api.get(`/map/sido`).then(success).catch(fail);
}
function gugunList(params, success, fail) {
  api.get(`/map/gugun`, { params: params }).then(success).catch(fail);
}
function dongList(data, success, fail) {
  api.post(`/map/dong`, data).then(success).catch(fail);
}
function houseList(data, success, fail) {
  api.post(`/map/apt`, data).then(success).catch(fail);
}

function houseDetail(aptCode, success, fail) {
  api.get(`/map/apt/${aptCode}`).then(success).catch(fail);
}

export { sidoList, gugunList, dongList, houseList, houseDetail };
