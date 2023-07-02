import { apiInstance } from "./index.js";

const api = apiInstance();

async function removePlan(param, success, fail) {
  await api.delete(`/plan/${param.planId}`).then(success).catch(fail);
}

async function insertPlan(plan, success, fail) {
  console.log(plan);
  await api.post(`/plan`, JSON.stringify(plan)).then(success).catch(fail);
}

export { removePlan, insertPlan };
