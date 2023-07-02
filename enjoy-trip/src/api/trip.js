import { apiInstance } from "./index.js";

const api = apiInstance();

async function listTrips(condition, success, fail) {
  console.log(condition);
  await api.get(`/trip`, { params: condition }).then(success).catch(fail);
}

async function createTrip(trip, success, fail) {
  console.log(trip);
  await api.post(`/trip`, JSON.stringify(trip)).then(success).catch(fail);
}

async function removeTrip(param, success, fail) {
  await api
    .delete(`/trip/${param.tripId}`, { params: { userId: param.userId } })
    .then(success)
    .catch(fail);
}

async function updateTrip(trip, success, fail) {
  console.log(trip);
  await api.patch(`/trip`, JSON.stringify(trip)).then(success).catch(fail);
}

export { listTrips, removeTrip, createTrip, updateTrip };
