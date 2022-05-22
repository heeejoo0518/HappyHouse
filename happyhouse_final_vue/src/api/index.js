import axios from "axios";
import { API_BASE_URL, HPT_URL } from "@/config";

// axios 객체 생성
function apiInstance() {
  const instance = axios.create({
    baseURL: API_BASE_URL,
    headers: {
      "Content-type": "application/json",
    },
  });
  return instance;
}

function hospitalInstance() {
  const instance = axios.create({
    baseURL: HPT_URL,
    headers: {
      "Content-type": "application/json",
    },
  });
  return instance;
}

export { apiInstance, hospitalInstance };
