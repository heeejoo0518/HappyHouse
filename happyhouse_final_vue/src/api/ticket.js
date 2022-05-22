/** @format */

import { apiInstance } from "./index.js";

const api = apiInstance();
function listTickets(success, fail) {
  api.get(`/ticket`).then(success).catch(fail);
}
function getTotalCount(success, fail) {
  api.get(`/ticket/count`).then(success).catch(fail);
}

export { listTickets, getTotalCount };
