import axios from "axios";
import qs from "qs";

// local vue api axios instance
function apiInstance() {
  const instance = axios.create({
    baseURL: process.env.VUE_APP_API_BASE_URL,
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
    paramsSerializer: function (params) {
      return qs.stringify(params, { arrayFormat: "brackets" });
    },
  });
  return instance;
}

// house deal API axios instance
function attractionInstance() {
  const instance = axios.create({
    baseURL: process.env.VUE_APP_ATTRACTION_URL,
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
  });
  return instance;
}

export { apiInstance, attractionInstance };
